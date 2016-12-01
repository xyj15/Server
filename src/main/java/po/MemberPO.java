package po;

import helper.MemberType;
import helper.User;
import helper.UserType;

import java.util.Date;

public class MemberPO extends User {
	
	private String name;
	private String phone;
	private int level;
	private double discount;
	private MemberType memberType;
	private Date birthday;
	private String enterprise;
	
	public MemberPO() {
		memberType = null;
		birthday = null;
	}
	
	public MemberPO(String name, String phone, int level, double discount,
	                MemberType memberType, Date birthday, String enterprise) {
		this.name = name;
		this.phone = phone;
		this.level = level;
		this.discount = discount;
		this.memberType = memberType;
		this.birthday = birthday;
		this.enterprise = enterprise;
	}
	
	public MemberPO(String userID, String password, String name, String phone,
	                int level, double discount, MemberType memberType, Date birthday, String enterprise) {
		super(userID, password, UserType.Member);
		this.name = name;
		this.phone = phone;
		this.level = level;
		this.discount = discount;
		this.memberType = memberType;
		this.birthday = birthday;
		this.enterprise = enterprise;
	}

	public String getMemberID() {
		return super.getUserID();
	}

	public void setMemberID(String memberID) {
		super.setUserID(memberID);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public MemberType getMemberType() {
		return memberType;
	}
	
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getEnterprise() {
		return enterprise;
	}
	
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}
}