package data.service;

import po.MemberPO;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface MemberDataService extends Remote {
	
	public boolean addMember(MemberPO member) throws RemoteException;  //添加会员
	public boolean deleteMember(String memberID) throws RemoteException;  //删除会员
	public boolean updateMember(MemberPO member) throws RemoteException;  //更新会员
	public MemberPO getMember(String ID) throws RemoteException;  //查看会员
	public String getAvailableMemberID() throws RemoteException;  //得到可用的新增ID
}
