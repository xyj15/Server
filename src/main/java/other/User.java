package other;

/**
 * 所有用户的父类，存储用户名和密码，提供验证用户名和密码的功能
 * @author CROFF
 * @version 2016-11-28
 */
public class User {

	private String userID;	//用户ID
	private String password;	//密码
	private UserType userType;	//用户类型
	private boolean isLoged;   //登录状态
	
	/**
	 * 空构造方法
	 */
	public User() {

	}
	
	/**
	 * 不带用户类型的构造方法
	 * @param userID 用户ID
	 * @param password 密码
	 */
	public User(String userID, String password) {
		this.userID = userID;
		this.password = password;
	}
	
	/**
	 * 带用户类型的构造方法
	 * @param userID 用户ID
	 * @param password 密码
	 * @param userType 用户类型
	 * @param isLoged
	 */
	public User(String userID, String password, UserType userType, boolean isLoged) {
		this.userID = userID;
		this.password = password;
		this.userType = userType;
		this.isLoged = isLoged;
	}
	
	/**
	 * 检查用户名和密码
	 * @param userID 用户ID
	 * @param password 密码
	 * @return 正确则true，否则false
	 */
	public boolean checkIdentity(String userID, String password) {
		if(this.userID.equals(userID) && this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public boolean isLoged() {
		return isLoged;
	}

	public void setLoged(boolean loged) {
		isLoged = loged;
	}
}