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

	// ������� ����������
	public CoronationVO getCoronationInfo(CoronationVO cvo) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into CORONATION ");
		sql.append("(C_NO, P_NAME, HALL, START_DATE, END_DATE, C_TOTAL, C_PAYMENT)");
		sql.append(" VALUES(CORONATION_SEQ.NEXTVAL,?,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		CoronationVO cVo = cvo;

		try {
			// dbutill�̶�� Ŭ������ getconnection�޼ҵ�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// �Է¹��� ������������� ó���ϱ� ���Ͽ� sql������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, cVo.getP_Name());
			pstmt.setString(2, cVo.getHall());
			pstmt.setDate(3, cVo.getStart_Date());
			pstmt.setDate(4, cVo.getEnd_Date());
			pstmt.setInt(5, cVo.getC_Total());
			pstmt.setString(6, cVo.getC_PayMent());

			// sql���� ���� �� ó�� ����� ����
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
		return cVo;
	}

	// '������ ���̽�'���� '������� ��� ���̺� �÷��� ����'
	public ArrayList<String> getColumnName() {
		ArrayList<String> columName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from CORONATION");
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

	// �����Ȳ ��ü ����Ʈ
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
	
	

	// �����Ȳ���� p_Name�� �Է¹޾� ������� ������λ��� �˻�
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

	// �������->��ü��������
	public void getCoronationDelete(int no) throws Exception {
		// ������ ó���� ���� sql��
		StringBuffer sql = new StringBuffer();
		sql.append("delete from Coronation where c_no = ? ");
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("������1");

		try {
			// DBUtil�̶�� Ŭ������ getconnection�޼ҵ�� �����ͺ��̽� ����
			con = DBUtil.getConnection();

			// sql���� ���� �� ó�� ����� ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			System.out.println("������2");

			// sql���� ���� �� ó�� ����� ����
			int i = pstmt.executeUpdate();
			System.out.println("������3");

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
				alert.setContentText("����� ���� ���� ����.");
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

	// ����Ѿ� ���� (�̳� ->�ϳ�)����
	public void getCoronationPay(int c_Num) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update coronation set ");
		sql.append("c_payment = '�� ��' ");
		sql.append("where c_no = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		// CoronationVO retval = null;

		try {
			// dbutil�̶�� Ŭ������ getconnection�޼ҵ�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// �̳� -> �ϳ� , ����Ѿװ������� �����ϱ� ���Ͽ� sql������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, c_Num);

			int i = pstmt.executeUpdate();

			// sql���� ���� �� ó�� ����� ����
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������� �ȳ�");
				alert.setHeaderText("������� �ϳ��Ǿ����ϴ�..");

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

	// �����ȣ�� ���� �̳�,�ϳ� �˻� 
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
			System.out.println("��1");

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
	// �����ȣ�� ���� ��¥�˻� 
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
				System.out.println("��1");

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


