package helper;

/**
 * 存储用户的类型，分为客户、酒店管理人员、网站营销人员、网站管理人员
 * Member：客户(0)
 * Hotel：酒店管理人员(1)
 * Saler：网站营销人员(2)
 * Manager：网站管理人员(3)
 * @author CROFF
 * @version 2016-11-28
 */
public enum UserType {

	Member(0), Hotel(1), Saler(2), Manager(3);
	
	int value;
	private UserType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
