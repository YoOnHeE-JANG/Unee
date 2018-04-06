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
	// ������� �뿩���
	public RentalVO getRentalInfo(RentalVO rvo) throws Exception {
		// ������ ó���� ���� sql��
		StringBuffer sql = new StringBuffer();
		sql.append("insert into Rental ");
		sql.append(
				"(R_NO , C_NO1, P_NAME, RENTAL_STARTDATE, RENTAL_ENDDATE, DEVICE_P, LIGHT_P, SOUND_P, RENTAL_TOTAL, RENTAL_PAY, RENTAL_PAYMENT, RENTAL_OK )");
		sql.append(" values (rental_seq.nextval, ?, ?,sysdate,sysdate,?,?,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		RentalVO rVo = rvo;

		try {
			// dbutil�̶�� Ŭ������ getconnection�޼ҵ�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();
			// �Է¹��� �뿩������ ó���ϱ� ���Ͽ� sql������ ����
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
		return rVo;
	}

	// ������� ��ü����Ʈ
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

	// �����ͺ��̽����� �뿩���̺��� �÷��� ����
	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from Rental");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSetMetaData ��ü ���� ����
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

	// �뿩�ǿ��� ������ ����Ʈ ����
	public void getRentalDelete(int rno) throws Exception {
		// ������ ó���� ���� sql��
		StringBuffer sql = new StringBuffer();
		sql.append("delete from RENTAL where r_no = ? ");
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("������1");

		try {
			// DBUtil�̶�� Ŭ������ getconnection�޼ҵ�� �����ͺ��̽� ����
			con = DBUtil.getConnection();

			// sql���� ���� �� ó�� ����� ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, rno);
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

	// �뿩�Ѿ� ���� (�̳� ->�ϳ�)����
	public void getRentalPay(int r_Num) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update rental set ");
		sql.append("rental_payment = '�� ��' ");
		sql.append("where r_no = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		// CoronationVO retval = null;

		try {
			// dbutil�̶�� Ŭ������ getconnection�޼ҵ�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// �̳� -> �ϳ� , ����Ѿװ������� �����ϱ� ���Ͽ� sql������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, r_Num);

			int i = pstmt.executeUpdate();

			// sql���� ���� �� ó�� ����� ����
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���뿩 ���� �ȳ�");
				alert.setHeaderText("���뿩���� �ϳ��Ǿ����ϴ�..");

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
			System.out.println("��1");

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

	// ���뿩->�ݳ� ����
	public void getRentalReturn(int r_no) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update rental set ");
		sql.append("rental_ok = '�� ��' ");
		sql.append("where r_no = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		// CoronationVO retval = null;

		try {
			// dbutil�̶�� Ŭ������ getconnection�޼ҵ�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// �̳� -> �ϳ� , ����Ѿװ������� �����ϱ� ���Ͽ� sql������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, r_no);

			int i = pstmt.executeUpdate();

			// sql���� ���� �� ó�� ����� ����
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���뿩 �ȳ�");
				alert.setHeaderText("�뿩�� ������ �ݳ��Ǿ����ϴ�..");

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

	// �����ȣ�� ���� �뿩,�ݳ� �˻�
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
			System.out.println("��1");

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

	// ���뿩��û�� ������� ���뿩���
	public RentalVO getRentalUpdate(RentalVO rvo) throws Exception {
		// ������ ó���� ���� sql��
		StringBuffer sql = new StringBuffer();
		sql.append("update rental set ");
		sql.append(" rental_startdate = ?, rental_enddate = ?, device_p=?, light_p=?, sound_p=?, rental_Total=?, rental_Pay=? ");
		sql.append(" where p_name = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		RentalVO retval = null;

		try {
			// dbutill�̶�� Ŭ������ getconnection�޼ҵ�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// ������ �л� ������ �����ϱ� ���Ͽ� sql������ ����.
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setDate(1,  rvo.getRental_StartDate());
			pstmt.setDate(2,  rvo.getRental_EndDate());
			pstmt.setInt(3, rvo.getDevice_P());
			pstmt.setInt(4, rvo.getLight_P());
			pstmt.setInt(5, rvo.getSound_P());
			pstmt.setInt(6, rvo.getRental_Total());
			pstmt.setInt(7, rvo.getRental_Pay());
			pstmt.setString(8, rvo.getP_Name());


			// sql���� ���� �� ó�� ����� ����
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����� ���� ����!");
				alert.setHeaderText("����� ���� ���� �Ϸ�.");
				alert.showAndWait();
				retval = new RentalVO();

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����� ���� ����!");
				alert.setHeaderText("����� ���� ���� ����.");
				alert.showAndWait();
				retval = new RentalVO();

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
		return retval;

	}

}
