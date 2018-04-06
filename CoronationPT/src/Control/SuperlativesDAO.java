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
	// �ű� ����� ���� ���
	public SuperlativesVO getSuperlativesInfo(SuperlativesVO svo) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into SUPERLATIVES ");
		sql.append("(P_NAME, GROUPNAME, TITLE, ADDRESS, contactall, PRIZE)");
		sql.append(" values(?,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		SuperlativesVO sVo = svo;

		try {

			// DBUtil�̶�� Ŭ������ getConnection()�޼ҵ�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// ������ ó���ϱ� ���Ͽ� SQL������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, sVo.getP_Name());
			pstmt.setString(2, sVo.getGroupName());
			pstmt.setString(3, sVo.getTitle());
			pstmt.setString(4, sVo.getAddress());
			pstmt.setString(5, sVo.getContactAll());
			pstmt.setString(6, sVo.getPrize());

			// SQL���� ������ ó�� ����� ����
			int i = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {

			}
		}
		return sVo;
	}

	// '������ ���̽�'���� '����� ���̺� �÷��� ����'
	public ArrayList<String> getColumnName() {
		ArrayList<String> columName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from SUPERLATIVES");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// resultsetmetadata ��ü ���� ����
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

	// ����� ��ü ����Ʈ
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

	// ������� p_name�� �Է¹޾� ���� ��ȸ
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
	
	//����� �������
	public SuperlativesVO getSuperlativesUpdate(SuperlativesVO svo)throws Exception{
		//������ ó���� ���� sql��
		StringBuffer sql = new StringBuffer();
		sql.append("update superlatives set ");
		sql.append(" p_name=?, groupname=?, title=?, address=?, contactall=?, prize=? ");
		sql.append(" where p_name = ?");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		SuperlativesVO retval = null;
		
		try {
			//dbutill�̶�� Ŭ������ getconnection�޼ҵ�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();
			
			//������ �л� ������ �����ϱ� ���Ͽ� sql������ ����.
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, svo.getP_Name());
			pstmt.setString(2, svo.getGroupName());
			pstmt.setString(3, svo.getTitle());
			pstmt.setString(4, svo.getAddress());
			pstmt.setString(5, svo.getContactAll());
			pstmt.setString(6, svo.getPrize());
			pstmt.setString(7, svo.getP_Name());
			
			//sql���� ���� �� ó�� ����� ����
			int i = pstmt.executeUpdate();
			
			if(i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����� ���� ����!");
				alert.setHeaderText("����� ���� ���� �Ϸ�.");
				alert.showAndWait();
				retval = new SuperlativesVO();
				
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����� ���� ����!");
				alert.setHeaderText("����� ���� ���� ����.");
				alert.showAndWait();
				retval = new SuperlativesVO();
				
			}
			
		}catch(SQLException e) {
			System.out.println("e=[" + e + "]");
			
		}catch(Exception e) {
			System.out.println("e=[" + e + "]");
		}finally {
			try {
				//�����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}catch(SQLException e) {
		}
	}
		return retval;

	}

	//����� ���� ���� �̺�Ʈ
	public void getSuperlativesDelete(String name) throws Exception {
		// ������ ó���� ���� sql��
				StringBuffer sql = new StringBuffer();
				sql.append("delete from superlatives where p_name = ? ");
				Connection con = null;
				PreparedStatement pstmt = null;

				try {
					// DBUtil�̶�� Ŭ������ getconnection�޼ҵ�� �����ͺ��̽� ����
					con = DBUtil.getConnection();

					// sql���� ���� �� ó�� ����� ����
					pstmt = con.prepareStatement(sql.toString());
					pstmt.setString(1, name);

					// sql���� ���� �� ó�� ����� ����
					int i = pstmt.executeUpdate();

					if (i == 1) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("����� ���� ����");
						alert.setHeaderText("����� ���� ���� �Ϸ�.");
						alert.setContentText("����� ���� ���� ����.");
						alert.showAndWait();

					} else {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("����� ���� ����");
						alert.setHeaderText("����� ���� ���� ����.");
						alert.showAndWait();

					}
				} catch (SQLException e) {
					System.out.println("e=[" + e + "]");
				} catch (Exception e) {
					System.out.println("e=[" + e + "]");
				} finally {
					try {
						// �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
						if (pstmt != null)
							pstmt.close();
						if (con != null)
							con.close();
					} catch (SQLException e) {
					}

				}

			}
		
}
