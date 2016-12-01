package data.dataservice;

import po.MemberPO;


public interface MemberDataService {
	public boolean addMember(MemberPO member);
	public boolean deleteMember(String memberID);
	public boolean updateMember(MemberPO member);
	public MemberPO getMember(String ID);
}
