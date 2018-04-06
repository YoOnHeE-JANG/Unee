package Model;

import java.sql.Date;

public class CoronationVO {

	private int c_No; //대관번호
	private String p_Name; // 대관자명
	private String hall; // 공연장
	private Date start_Date; // 대관시작일
	private Date end_Date; // 대관종료일
	private int c_Total; //대관총액
	private String c_PayMent; //대관결제
	
	public CoronationVO() {
		super();
	}

	public CoronationVO(int c_No, String p_Name, String hall, Date start_Date, Date end_Date, int c_Total,
			String c_PayMent) {
		super();
		this.c_No = c_No;
		this.p_Name = p_Name;
		this.hall = hall;
		this.start_Date = start_Date;
		this.end_Date = end_Date;
		this.c_Total = c_Total;
		this.c_PayMent = c_PayMent;
	}

	public CoronationVO(String p_Name, String hall, Date start_Date, Date end_Date, int c_Total, String c_PayMent) {
		super();
		this.p_Name = p_Name;
		this.hall = hall;
		this.start_Date = start_Date;
		this.end_Date = end_Date;
		this.c_Total = c_Total;
		this.c_PayMent = c_PayMent;
	}

	public int getC_No() {
		return c_No;
	}

	public void setC_No(int c_No) {
		this.c_No = c_No;
	}

	public String getP_Name() {
		return p_Name;
	}

	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}

	public String getHall() {
		return hall;
	}

	public void setHall(String hall) {
		this.hall = hall;
	}

	public Date getStart_Date() {
		return start_Date;
	}

	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
	}

	public Date getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}

	public int getC_Total() {
		return c_Total;
	}

	public void setC_Total(int c_Total) {
		this.c_Total = c_Total;
	}

	public String getC_PayMent() {
		return c_PayMent;
	}

	public void setC_PayMent(String c_PayMent) {
		this.c_PayMent = c_PayMent;
	}
	

	
}
