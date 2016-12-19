package other;

/**
 * 所有用户的父类，存储用户名和密码，提供验证用户名和密码的功能
 * @author CROFF
 * @version 2016-11-28
 */
public class User {

	private String userID;
	private String password;
	private UserType userType;

	public User() {

	}

	public User(String userID, String password) {
		this.userID = userID;
		this.password = password;
	}

	public User(String userID, String password, UserType userType) {
		this.userID = userID;
		this.password = password;
		this.userID = userID;
	}

	public boolean checkIdentity(String username, String password) {
		if (this.userID.equals(username) && this.password.equals(password)) {
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

}