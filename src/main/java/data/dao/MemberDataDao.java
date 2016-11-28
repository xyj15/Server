package data.dao;

import po.MemberPO;


public interface MemberDataDao {
	public boolean add(MemberPO member);
	public boolean delete(MemberPO member);
	public boolean update(MemberPO member);
	public MemberPO getMember(String ID);
}
