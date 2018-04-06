package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.SuperlativesVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SuperlativesDAO {
	// 신규 대관자 정보 등록
	public SuperlativesVO getSuperlativesInfo(SuperlativesVO svo) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into SUPERLATIVES ");
		sql.append("(P_NAME, GROUPNAME, TITLE, ADDRESS, contactall, PRIZE)");
		sql.append(" values(?,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		SuperlativesVO sVo = svo;

		try {

			// DBUtil이라는 클래스의 getConnection()메소드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 정보를 처리하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, sVo.getP_Name());
			pstmt.setString(2, sVo.getGroupName());
			pstmt.setString(3, sVo.getTitle());
			pstmt.setString(4, sVo.getAddress());
			pstmt.setString(5, sVo.getContactAll());
			pstmt.setString(6, sVo.getPrize());

			// SQL문을 수행후 처리 결과를 얻어옴
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
		return sVo;
	}

	// '데이터 베이스'에서 '대관자 테이블 컬럼의 갯수'
	public ArrayList<String> getColumnName() {
		ArrayList<String> columName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from SUPERLATIVES");
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

	// 대관자 전체 리스트
	public ArrayList<SuperlativesVO> getSuperlativesTotal() {
		ArrayList<SuperlativesVO> list = new ArrayList<SuperlativesVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select *");
		sql.append(" from superlatives");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SuperlativesVO sVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sVo = new SuperlativesVO();
				sVo.setP_Name(rs.getString("p_name"));
				sVo.setGroupName(rs.getString("groupName"));
				sVo.setTitle(rs.getString("title"));
				sVo.setAddress(rs.getString("address"));
				sVo.setContactAll(rs.getString("contactAll"));
				sVo.setPrize(rs.getString("prize"));

				list.add(sVo);
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

	// 대관자의 p_name을 입력받아 정보 조회
	public SuperlativesVO getSuperlativesCheck(String p_Name) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from superlatives where p_name like ");
		sql.append("? order by p_name desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SuperlativesVO sVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + p_Name + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				sVo = new SuperlativesVO();
				sVo.setP_Name(rs.getString("p_Name"));
				sVo.setGroupName(rs.getString("groupName"));
				sVo.setTitle(rs.getString("title"));
				sVo.setAddress(rs.getString("address"));
				sVo.setContactAll(rs.getString("contactAll"));
				sVo.setPrize(rs.getString("prize"));
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
		return sVo;
	}
	
	//대관자 수정등록
	public SuperlativesVO getSuperlativesUpdate(SuperlativesVO svo)throws Exception{
		//데이터 처리를 위한 sql문
		StringBuffer sql = new StringBuffer();
		sql.append("update superlatives set ");
		sql.append(" p_name=?, groupname=?, title=?, address=?, contactall=?, prize=? ");
		sql.append(" where p_name = ?");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		SuperlativesVO retval = null;
		
		try {
			//dbutill이라는 클래스의 getconnection메소드로 데이터베이스와 연동
			con = DBUtil.getConnection();
			
			//수정한 학생 정보를 수정하기 위하여 sql문장을 생성.
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, svo.getP_Name());
			pstmt.setString(2, svo.getGroupName());
			pstmt.setString(3, svo.getTitle());
			pstmt.setString(4, svo.getAddress());
			pstmt.setString(5, svo.getContactAll());
			pstmt.setString(6, svo.getPrize());
			pstmt.setString(7, svo.getP_Name());
			
			//sql문을 수행 후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();
			
			if(i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("대관자 정보 수정!");
				alert.setHeaderText("대관자 정보 수정 완료.");
				alert.showAndWait();
				retval = new SuperlativesVO();
				
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("대관자 정보 수정!");
				alert.setHeaderText("대관자 정보 수정 실패.");
				alert.showAndWait();
				retval = new SuperlativesVO();
				
			}
			
		}catch(SQLException e) {
			System.out.println("e=[" + e + "]");
			
		}catch(Exception e) {
			System.out.println("e=[" + e + "]");
		}finally {
			try {
				//데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}catch(SQLException e) {
		}
	}
		return retval;

	}

	//대관자 정보 삭제 이벤트
	public void getSuperlativesDelete(String name) throws Exception {
		// 데이터 처리를 위한 sql문
				StringBuffer sql = new StringBuffer();
				sql.append("delete from superlatives where p_name = ? ");
				Connection con = null;
				PreparedStatement pstmt = null;

				try {
					// DBUtil이라는 클래스의 getconnection메소드로 데이터베이스 연동
					con = DBUtil.getConnection();

					// sql문을 수행 후 처리 결과를 얻어옴
					pstmt = con.prepareStatement(sql.toString());
					pstmt.setString(1, name);

					// sql문을 수행 후 처리 결과를 얻어옴
					int i = pstmt.executeUpdate();

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
		
}
