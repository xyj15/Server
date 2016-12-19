package po;

import other.MemberType;
import other.User;
import other.UserType;

import java.io.Serializable;
import java.util.Date;


/**
 * 存储Member信息的PO类
 * @author CROFF
 * @version 2016-12-1
 */
public class MemberPO extends User implements Serializable {
	
	private String name;
	private String phone;
	private int level;
	private double discount;
	private MemberType memberType;
	private Date birthday;
	private String enterprise;
	private static final long serialVersionUID = -6833877079313718314L;   //序列号
	
	/**
	 * 空构造方法
	 */
	public MemberPO() {
		super.setUserType(UserType.Member);
	}
	
	/**
	 * 无用户名和密码的构造方法
	 * @param name 姓名或名称
	 * @param phone 联系方式
	 * @param level 会员等级
	 * @param discount 当前会员等级享受折扣
	 * @param memberType 客户类型
	 * @param birthday 生日
	 * @param enterprise 合作企业名称
	 */
	public MemberPO(String name, String phone, int level, double discount,
	                MemberType memberType, Date birthday, String enterprise) {
		this.name = name;
		this.phone = phone;
		this.level = level;
		this.discount = discount;
		this.memberType = memberType;
		this.birthday = birthday;
		this.enterprise = enterprise;
		super.setUserType(UserType.Member);
	}
	
	/**
	 * 有用户名和密码的构造方法
	 * @param userID 用户名
	 * @param password 密码
	 * @param name 姓名或名称
	 * @param phone 联系方式
	 * @param level 会员等级
	 * @param discount 当前会员等级享受折扣
	 * @param memberType 客户类型
	 * @param birthday 生日
	 * @param enterprise 合作企业名称
	 */
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