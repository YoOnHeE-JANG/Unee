package Model;



public class SuperlativesVO {
	
	private String p_Name; //����ڸ�
	private String groupName; //��ü��
	private String title; //������
	private String address; //�ּ�
	private String contactAll; //����ó
	private String prize; //����� �����

	

	
		public SuperlativesVO(String p_Name, String groupName, String title, String address, String contactAll, String prize) {
		super();
		this.p_Name = p_Name;
		this.groupName = groupName;
		this.title = title;
		this.address = address;
		this.contactAll = contactAll;
		this.prize = prize;
	}
		
		
		
	
	
	




	public SuperlativesVO(String groupName, String title, String address, String contactAll, String prize) {
			super();
			this.groupName = groupName;
			this.title = title;
			this.address = address;
			this.contactAll = contactAll;
			this.prize = prize;
		}










	public SuperlativesVO() {
		super();
	}


	public String getP_Name() {
		return p_Name;
	}


	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getContactAll() {
		return contactAll;
	}


	public void setContactAll(String contactAll) {
		this.contactAll = contactAll;
	}


	public String getPrize() {
		return prize;
	}


	public void setPrize(String prize) {
		this.prize = prize;
	}
	

	
	
	
	
	
	
	

	
	
	


	
	

	
}
