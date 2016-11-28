package data.stub;

import java.util.ArrayList;

import data.dao.MemberDataDao;
import po.UserRole;
import po.UserPO;


public class UserDataStub implements MemberDataDao {

	private String ID="541322049";
	private String password="DREAMING";
	private String name="zhangxinyue";
	private UserRole role;
	private ArrayList<UserPO> list = new ArrayList<UserPO>();
	
	public UserDataStub () {
		list.add(new UserPO(ID, password, name, role));
	}
	
	@Override
	public boolean insert(UserPO user) {
		// TODO Auto-generated method stub
		ID=user.getID();
		password=user.getPassword();
		name=user.getName();
		role=user.getUserRole();
		list.add(new UserPO(ID, password, name, role));
		return true;
	}

	@Override
	public boolean delete(UserPO user) {
		// TODO Auto-generated method stub
		for(int i=0;i<list.size();i++){
			if(list.get(i).getID().equals(user.getID())){
				list.remove(i);
			}
		}
		return true;
	}

	@Override
	public boolean update(UserPO user) {
		// TODO Auto-generated method stub
		for(int i=0;i<list.size();i++){
			if(list.get(i).getID().equals(user.getID())){
				list.remove(i);
				list.add(user);
			}
		}
		return true;
	}

	@Override
	public UserPO getUser(String ID) {
		// TODO Auto-generated method stub
		return new UserPO(ID,password,name,role);
	}

	@Override
	public ArrayList<UserPO> getList(String limit) {
		// TODO Auto-generated method stub
		return list;
	}

}
