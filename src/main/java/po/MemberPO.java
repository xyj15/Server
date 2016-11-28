package po;

public class MemberPO {
	
	private String memberID;
	private String name;
	private String password;
	private String birthday;
	private double credit;
	private String phone;
	
	public MemberPO(String memberID,String name,String password,String birth,double credit,String phone){
		this.setName(name);
		this.setMemberID(memberID);
		this.setPassword(password);
		this.setBirthday(birth);
		this.setCredit(credit);
		this.setPhone(phone);
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthday(){
		return birthday;
	}
	
	public void setBirthday(String birth){
		this.birthday=birth;
	}
	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}