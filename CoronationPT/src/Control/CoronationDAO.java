package Control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.CoronationVO;
import Model.SuperlativesVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CoronationDAO {

	// 대관자의 대관정보등록
	public CoronationVO getCoronationInfo(CoronationVO cvo) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into CORONATION ");
		sql.append("(C_NO, P_NAME, HALL, START_DATE, END_DATE, C_TOTAL, C_PAYMENT)");
		sql.append(" VALUES(CORONATION_SEQ.NEXTVAL,?,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		CoronationVO cVo = cvo;

		try {
			// dbutill이라는 클래스의 getconnection메소드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 입력받은 대관세부정보를 처리하기 위하여 sql문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, cVo.getP_Name());
			pstmt.setString(2, cVo.getHall());
			pstmt.setDate(3, cVo.getStart_Date());
			pstmt.setDate(4, cVo.getEnd_Date());
			pstmt.setInt(5, cVo.getC_Total());
			pstmt.setString(6, cVo.getC_PayMent());

			// sql문을 수행 후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");

		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
		return cVo;
	}

	// '데이터 베이스'에서 '대관자의 대관 테이블 컬럼의 갯수'
	public ArrayList<String> getColumnName() {
		ArrayList<String> columName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from CORONATION");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// resultsetmetadata 객체 변수 선언
		ResultSetMetaData rsmd = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columName.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {

			}
		}
		return columName;
	}

	// 대관현황 전체 리스트
	public ArrayList<CoronationVO> getCoronativesTotal() {
		ArrayList<CoronationVO> list = new ArrayList<CoronationVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from CORONATION");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CoronationVO cVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cVo = new CoronationVO();
				cVo.setC_No(rs.getInt("c_No"));
				cVo.setP_Name(rs.getString("p_Name"));
				cVo.setHall(rs.getString("hall"));
				cVo.setStart_Date(rs.getDate("start_Date"));
				cVo.setEnd_Date(rs.getDate("end_Date"));
				cVo.setC_Total(rs.getInt("c_Total"));
				cVo.setC_PayMent(rs.getString("c_PayMent"));

				list.add(cVo);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {

			}
		}
		System.out.println(list);
		return list;
	}
	
	

	// 대관현황에서 p_Name을 입력받아 대관자의 대관세부사항 검색
	public CoronationVO getCoronationCheck(String p_Name) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from coronation where p_name like ");
		sql.append("? order by c_no desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CoronationVO cVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			// String p_Name = null;
			System.out.println(p_Name);
			pstmt.setString(1, "%" + p_Name + "%");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cVo = new CoronationVO();
				cVo.setC_No(rs.getInt("c_No"));
				cVo.setP_Name(rs.getString("p_Name"));
				cVo.setHall(rs.getString("hall"));
				cVo.setStart_Date(rs.getDate("start_Date"));
				cVo.setEnd_Date(rs.getDate("end_Date"));
				cVo.setC_Total(rs.getInt("c_Total"));
				cVo.setC_PayMent(rs.getString("c_PayMent"));

			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {

			}
		}
		return cVo;

	}

	// 대관정보->전체정보삭제
	public void getCoronationDelete(int no) throws Exception {
		// 데이터 처리를 위한 sql문
		StringBuffer sql = new StringBuffer();
		sql.append("delete from Coronation where c_no = ? ");
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("삭제좀1");

		try {
			// DBUtil이라는 클래스의 getconnection메소드로 데이터베이스 연동
			con = DBUtil.getConnection();

			// sql문을 수행 후 처리 결과를 얻어옴
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			System.out.println("삭제좀2");

			// sql문을 수행 후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();
			System.out.println("삭제좀3");

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("대관자 정보 삭제");
				alert.setHeaderText("대관자 정보 삭제 완료.");
				alert.setContentText("대관자 정보 삭제 성공.");
				alert.showAndWait();

			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("대관자 정보 삭제");
				alert.setHeaderText("대관자 정보 삭제 실패.");
				alert.setContentText("대관자 정보 삭제 실패.");
				alert.showAndWait();

			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}

		}

	}

	// 대관총액 결제 (미납 ->완납)변경
	public void getCoronationPay(int c_Num) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update coronation set ");
		sql.append("c_payment = '완 납' ");
		sql.append("where c_no = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		// CoronationVO retval = null;

		try {
			// dbutil이라는 클래스의 getconnection메소드로 데이터베이스와 연동
			con = DBUtil.getConnection();

			// 미납 -> 완납 , 대관총액결제내용 수정하기 위하여 sql문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, c_Num);

			int i = pstmt.executeUpdate();

			// sql문을 수행 후 처리 결과를 얻어옴
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("대관결제 안내");
				alert.setHeaderText("대관액이 완납되었습니다..");

				alert.showAndWait();

			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}

		}
	}

	// 대관번호를 통해 미납,완납 검색 
	public CoronationVO getCoronationPaysearch(int c_Num) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select c_payment from coronation where c_no = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CoronationVO cVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			// String p_Name = null;
			pstmt.setInt(1, c_Num );
			System.out.println("뿌1");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cVo = new CoronationVO();
				cVo.setC_PayMent(rs.getString("c_PayMent"));

			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {

			}
		}
		return cVo;

	}
	// 대관번호를 통해 날짜검색 
		public CoronationVO getCoronationDateSearch(int c_Num) throws Exception {
			StringBuffer sql = new StringBuffer();
			sql.append("select start_date, end_date from coronation where c_no = ? ");

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			CoronationVO cVo = null;

			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				// String p_Name = null;
				pstmt.setInt(1, c_Num );
				System.out.println("뿌1");

				rs = pstmt.executeQuery();

				if (rs.next()) {
					cVo = new CoronationVO();
					cVo.setStart_Date(rs.getDate("start_date"));
					cVo.setEnd_Date(rs.getDate("end_date"));

				}
			} catch (SQLException se) {
				System.out.println(se);
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();

				} catch (SQLException se) {

				}
			}
			return cVo;

		}
}


