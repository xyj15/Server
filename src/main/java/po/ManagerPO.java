package po;

import helper.User;
import helper.UserType;

import java.io.Serializable;

/**
 * Created by CROFF on 2016/12/1.
 * 存储Manager信息的PO类
 * @author CROFF
 * @version 2016-12-1
 */
public class ManagerPO extends User implements Serializable {
	
	private String name;	//姓名或名称
	private String tel;	//联系方式
	
	/**
	 * 空构造方法
	 */
	public ManagerPO() {
		super.setUserType(UserType.Manager);
	}
	
	/**
	 * 无用户名和密码的构造方法
	 * @param name 姓名或名称
	 * @param tel 联系方式
	 */
	public ManagerPO(String name, String tel) {
		this.name = name;
		this.tel = tel;
		super.setUserType(UserType.Manager);
	}
	
	/**
	 * 有用户名和密码的构造方法
	 * @param userID 用户名
	 * @param password 密码
	 * @param name 姓名或名称
	 * @param tel 联系方式
	 */
	public ManagerPO(String userID, String password, String name, String tel) {
		super(userID, password, UserType.Manager);
		this.name = name;
		this.tel = tel;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
}
