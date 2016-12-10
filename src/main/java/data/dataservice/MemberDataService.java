package data.dataservice;

import po.MemberPO;


public interface MemberDataService {
	public boolean addMember(MemberPO member);  //添加会员
	public boolean deleteMember(String memberID);  //删除会员
	public boolean updateMember(MemberPO member);  //更新会员
	public MemberPO getMember(String ID);  //查看会员
	public String getAvailableID();  //得到可用的新增ID
}
