package Model;

import java.sql.Date;

public class RentalVO {
	
	private int r_No; //대여번호
	private int c_No1; //대관번호
	private String p_Name; //대관자명
	private Date rental_StartDate; //대여일
	private Date rental_EndDate; //반납일
	private int device_P; //대여장치 수
	private int light_P; //대여조명기 수
	private int sound_P; //대여음향기 수
	private int rental_Total; //대여 총 수량
	private int rental_Pay; //총 대여 액
	private String rental_PayMent; //대여결제
	private String rental_Ok; //반납확인
	
	public RentalVO() {
		super();
	}
	
	

	public RentalVO(int c_No1, String p_Name, Date rental_StartDate, Date rental_EndDate, int device_P, int light_P,
			int sound_P, int rental_Total, int rental_Pay, String rental_PayMent, String rental_Ok) {
		super();
		this.c_No1 = c_No1;
		this.p_Name = p_Name;
		this.rental_StartDate = rental_StartDate;
		this.rental_EndDate = rental_EndDate;
		this.device_P = device_P;
		this.light_P = light_P;
		this.sound_P = sound_P;
		this.rental_Total = rental_Total;
		this.rental_Pay = rental_Pay;
		this.rental_PayMent = rental_PayMent;
		this.rental_Ok = rental_Ok;
	}




	public int getR_No() {
		return r_No;
	}


	public void setR_No(int r_No) {
		this.r_No = r_No;
	}


	public int getC_No1() {
		return c_No1;
	}


	public void setC_No1(int c_No1) {
		this.c_No1 = c_No1;
	}


	public String getP_Name() {
		return p_Name;
	}


	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}


	public Date getRental_StartDate() {
		return rental_StartDate;
	}


	public void setRental_StartDate(Date rental_StartDate) {
		this.rental_StartDate = rental_StartDate;
	}


	public Date getRental_EndDate() {
		return rental_EndDate;
	}


	public void setRental_EndDate(Date rental_EndDate) {
		this.rental_EndDate = rental_EndDate;
	}


	public int getDevice_P() {
		return device_P;
	}


	public void setDevice_P(int device_P) {
		this.device_P = device_P;
	}


	public int getLight_P() {
		return light_P;
	}


	public void setLight_P(int light_P) {
		this.light_P = light_P;
	}


	public int getSound_P() {
		return sound_P;
	}


	public void setSound_P(int sound_P) {
		this.sound_P = sound_P;
	}


	public int getRental_Total() {
		return rental_Total;
	}


	public void setRental_Total(int rental_Total) {
		this.rental_Total = rental_Total;
	}


	public int getRental_Pay() {
		return rental_Pay;
	}


	public void setRental_Pay(int rental_Pay) {
		this.rental_Pay = rental_Pay;
	}


	public String getRental_PayMent() {
		return rental_PayMent;
	}


	public void setRental_PayMent(String rental_PayMent) {
		this.rental_PayMent = rental_PayMent;
	}


	public String getRental_Ok() {
		return rental_Ok;
	}


	public void setRental_Ok(String rental_Ok) {
		this.rental_Ok = rental_Ok;
	}


	public RentalVO(Date rental_StartDate, Date rental_EndDate, int device_P, int light_P, int sound_P,
			int rental_Total, int rental_Pay) {
		super();
		this.rental_StartDate = rental_StartDate;
		this.rental_EndDate = rental_EndDate;
		this.device_P = device_P;
		this.light_P = light_P;
		this.sound_P = sound_P;
		this.rental_Total = rental_Total;
		this.rental_Pay = rental_Pay;
	}


	public RentalVO(int r_No, int c_No1, String p_Name, Date rental_StartDate, Date rental_EndDate, int device_P,
			int light_P, int sound_P, int rental_Total, int rental_Pay, String rental_PayMent, String rental_Ok) {
		super();
		this.r_No = r_No;
		this.c_No1 = c_No1;
		this.p_Name = p_Name;
		this.rental_StartDate = rental_StartDate;
		this.rental_EndDate = rental_EndDate;
		this.device_P = device_P;
		this.light_P = light_P;
		this.sound_P = sound_P;
		this.rental_Total = rental_Total;
		this.rental_Pay = rental_Pay;
		this.rental_PayMent = rental_PayMent;
		this.rental_Ok = rental_Ok;
	}
	
	
	

	


}
