package data.driver;

import po.UserRole;
import data.dao.MemberDataDao;
import data.stub.UserDataStub;
import po.UserPO;

public class UserDataDriver {


	MemberDataDao service = new UserDataStub();
	public void getUser() {
		// TODO Auto-generated method stub
		service.getUser("541322049");
		System.out.println("---查找用户———");
	}

	
	public void updateUser(UserPO user) {
		// TODO Auto-generated method stub
		System.out.println("---更新用户———");
		System.out.println(service.update(user));
	}

	public void addUser(UserPO user) {
		// TODO Auto-generated method stub
		System.out.println("---新增用户———");
		System.out.println(service.insert(user));
	}

	
	public void deleteUser(UserPO user) {
		// TODO Auto-generated method stub
		System.out.println("---删除用户———");
		System.out.println(service.delete(user));
	}


	public void getList() {
		// TODO Auto-generated method stub
		System.out.println(service.getList("limit"));
	}
	
	public static void main(String[] args) {
		UserDataDriver driver = new UserDataDriver();
		UserPO user = new UserPO("1234", "1234", "xyj", UserRole.SALER);
		driver.addUser(user);
		driver.getUser();
		driver.getList();
		driver.updateUser(user);
		driver.deleteUser(user);
	}
	
}
