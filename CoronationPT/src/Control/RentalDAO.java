package Control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.RentalVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RentalDAO {
	// 대관자의 대여등록
	public RentalVO getRentalInfo(RentalVO rvo) throws Exception {
		// 데이터 처리를 위한 sql문
		StringBuffer sql = new StringBuffer();
		sql.append("insert into Rental ");
		sql.append(
				"(R_NO , C_NO1, P_NAME, RENTAL_STARTDATE, RENTAL_ENDDATE, DEVICE_P, LIGHT_P, SOUND_P, RENTAL_TOTAL, RENTAL_PAY, RENTAL_PAYMENT, RENTAL_OK )");
		sql.append(" values (rental_seq.nextval, ?, ?,sysdate,sysdate,?,?,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		RentalVO rVo = rvo;

		try {
			// dbutil이라는 클래스의 getconnection메소드로 데이터베이스와 연결
			con = DBUtil.getConnection();
			// 입력받은 대여정보를 처리하기 위하여 sql문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, rVo.getC_No1());
			pstmt.setString(2, rVo.getP_Name());
			pstmt.setInt(3, rVo.getDevice_P());
			pstmt.setInt(4, rVo.getLight_P());
			pstmt.setInt(5, rVo.getSound_P());
			pstmt.setInt(6, rVo.getRental_Total());
			pstmt.setInt(7, rVo.getRental_Pay());
			pstmt.setString(8, rVo.getRental_PayMent());
			pstmt.setString(9, rVo.getRental_Ok());
			// sql문을 수행 후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젯트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
		return rVo;
	}

	// 대관자의 전체리스트
	public ArrayList<RentalVO> getRentalTotal() {
		ArrayList<RentalVO> list = new ArrayList<RentalVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select *");
		sql.append(" from rental");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RentalVO rVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rVo = new RentalVO();
				rVo.setR_No(rs.getInt("r_No"));
				rVo.setC_No1(rs.getInt("c_No1"));
				rVo.setP_Name(rs.getString("p_Name"));
				rVo.setRental_StartDate(rs.getDate("rental_StartDate") );
				rVo.setRental_EndDate(rs.getDate("rental_EndDate"));
				rVo.setDevice_P(rs.getInt("device_P"));
				rVo.setLight_P(rs.getInt("light_P"));
				rVo.setSound_P(rs.getInt("sound_P"));
				rVo.setRental_Total(rs.getInt("rental_Total"));
				rVo.setRental_Pay(rs.getInt("rental_Pay"));
				rVo.setRental_PayMent(rs.getString("rental_PayMent"));
				rVo.setRental_Ok(rs.getString("rental_Ok"));

				list.add(rVo);
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
		return list;
	}

	// 데이터베이스에서 대여테이블의 컬럼의 갯수
	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from Rental");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSetMetaData 객체 변수 선언
		ResultSetMetaData rsmd = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
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
		return columnName;
	}

	//
	public RentalVO getRentalCheck(String p_Name) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from  rental where p_name like ");
		sql.append("? order by c_no1 desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RentalVO rVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			// String p_Name = null;
			System.out.println(p_Name);
			pstmt.setString(1, "%" + p_Name + "%");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				rVo = new RentalVO();
				rVo.setC_No1(rs.getInt("c_No1"));
				rVo.setR_No(rs.getInt("r_No"));
				rVo.setP_Name(rs.getString("p_Name"));
				rVo.setRental_StartDate(rs.getDate("rental_StartDate"));
				rVo.setRental_EndDate(rs.getDate("rental_EndDate") );
				rVo.setDevice_P(rs.getInt("device_P"));
				rVo.setLight_P(rs.getInt("light_P"));
				rVo.setSound_P(rs.getInt("sound_P"));
				rVo.setRental_Total(rs.getInt("rental_Total"));
				rVo.setRental_Pay(rs.getInt("rental_Pay"));
				rVo.setRental_PayMent(rs.getString("rental_PayMent"));
				rVo.setRental_Ok(rs.getString("rental_Ok"));

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
		return rVo;

	}

	// 대여탭에서 선택한 리스트 삭제
	public void getRentalDelete(int rno) throws Exception {
		// 데이터 처리를 위한 sql문
		StringBuffer sql = new StringBuffer();
		sql.append("delete from RENTAL where r_no = ? ");
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("삭제좀1");

		try {
			// DBUtil이라는 클래스의 getconnection메소드로 데이터베이스 연동
			con = DBUtil.getConnection();

			// sql문을 수행 후 처리 결과를 얻어옴
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, rno);
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

	// 대여총액 결제 (미납 ->완납)변경
	public void getRentalPay(int r_Num) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update rental set ");
		sql.append("rental_payment = '완 납' ");
		sql.append("where r_no = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		// CoronationVO retval = null;

		try {
			// dbutil이라는 클래스의 getconnection메소드로 데이터베이스와 연동
			con = DBUtil.getConnection();

			// 미납 -> 완납 , 대관총액결제내용 수정하기 위하여 sql문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, r_Num);

			int i = pstmt.executeUpdate();

			// sql문을 수행 후 처리 결과를 얻어옴
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("장비대여 결제 안내");
				alert.setHeaderText("장비대여액이 완납되었습니다..");

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
	public RentalVO getRentalPaysearch(int r_Num) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select rental_payment from rental where r_no = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RentalVO rVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			// String p_Name = null;
			pstmt.setInt(1, r_Num);
			System.out.println("뿌1");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				rVo = new RentalVO();
				rVo.setRental_PayMent(rs.getString("rental_PayMent"));

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
		return rVo;

	}

	// 장비대여->반납 변경
	public void getRentalReturn(int r_no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update rental set ");
		sql.append("rental_ok = '반 납' ");
		sql.append("where r_no = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		// CoronationVO retval = null;

		try {
			// dbutil이라는 클래스의 getconnection메소드로 데이터베이스와 연동
			con = DBUtil.getConnection();

			// 미납 -> 완납 , 대관총액결제내용 수정하기 위하여 sql문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, r_no);

			int i = pstmt.executeUpdate();

			// sql문을 수행 후 처리 결과를 얻어옴
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("장비대여 안내");
				alert.setHeaderText("대여한 장비들이 반납되었습니다..");

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

	// 대관번호를 통해 대여,반납 검색
	public RentalVO getRentalReturnsearch(int r_no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select rental_ok from rental where r_no = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RentalVO rVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			// String p_Name = null;
			pstmt.setInt(1, r_no);
			System.out.println("뿌1");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				rVo = new RentalVO();
				rVo.setRental_Ok(rs.getString("rental_Ok"));

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
		return rVo;

	}

	// 장비대여신청한 대관자의 장비대여등록
	public RentalVO getRentalUpdate(RentalVO rvo) throws Exception {
		// 데이터 처리를 위한 sql문
		StringBuffer sql = new StringBuffer();
		sql.append("update rental set ");
		sql.append(" rental_startdate = ?, rental_enddate = ?, device_p=?, light_p=?, sound_p=?, rental_Total=?, rental_Pay=? ");
		sql.append(" where p_name = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		RentalVO retval = null;

		try {
			// dbutill이라는 클래스의 getconnection메소드로 데이터베이스와 연동
			con = DBUtil.getConnection();

			// 수정한 학생 정보를 수정하기 위하여 sql문장을 생성.
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setDate(1,  rvo.getRental_StartDate());
			pstmt.setDate(2,  rvo.getRental_EndDate());
			pstmt.setInt(3, rvo.getDevice_P());
			pstmt.setInt(4, rvo.getLight_P());
			pstmt.setInt(5, rvo.getSound_P());
			pstmt.setInt(6, rvo.getRental_Total());
			pstmt.setInt(7, rvo.getRental_Pay());
			pstmt.setString(8, rvo.getP_Name());


			// sql문을 수행 후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("대관자 정보 수정!");
				alert.setHeaderText("대관자 정보 수정 완료.");
				alert.showAndWait();
				retval = new RentalVO();

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("대관자 정보 수정!");
				alert.setHeaderText("대관자 정보 수정 실패.");
				alert.showAndWait();
				retval = new RentalVO();

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
		return retval;

	}

}
