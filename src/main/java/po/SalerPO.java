package po;

import other.User;
import other.UserType;

import java.io.Serializable;

/**
 * Created by apple on 2016/11/22.
 * 存储Saler信息的PO类
 * @author CROFF
 * @version 2016-12-1
 */
public class SalerPO extends User implements Serializable {
	
	private String name;	//姓名或名称
	private String tel;	//联系方式
	private static final long serialVersionUID = -6833877079313718314L;   //序列号
	
	/**
	 * 空构造方法
	 */
	public SalerPO() {
		super.setUserType(UserType.Saler);
	}
	
	/**
	 * 无用户名和密码的构造方法
	 * @param name 姓名或名称
	 * @param tel 联系方式
	 */
    public SalerPO(String name, String tel) {
        this.name = name;
        this.tel = tel;
		super.setUserType(UserType.Saler);
    }
	
	/**
	 * 有用户名和密码的构造方法
	 * @param userID 用户名
	 * @param password 密码
	 * @param name 姓名或名称
	 * @param tel 联系方式
	 */
    public SalerPO(String userID, String password, String name, String tel) {
		super(userID, password, UserType.Saler);
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

	public String getAccount(){
		return super.getUserID();
	}

	public String getPassword(){
		return super.getPassword();
	}
}
