package data.implementation;

import other.MemberType;
import org.junit.Before;
import org.junit.Test;
import po.MemberPO;

import static driver.DriverForMemberData.getDate;
import static org.junit.Assert.*;

/**
 * Created by apple on 2016/12/15.
 */
public class MemberDataTest {

	private MemberData memberData;
	@Before
	public void setUp() throws Exception {
		memberData = new MemberData();
	}

	@Test
	public void addMember() throws Exception {
		MemberPO member1 = new MemberPO("00000004", "xinxin", "张新悦", "15205153967", 3, 5.0, MemberType.Orinary, getDate("02-11-1997"),"");
		MemberPO member2 = new MemberPO("00000001", "jingjing", "徐亚婧", "13151530838", 2, 6.0, MemberType.Orinary, getDate("03-03-1997"),"");
		MemberPO member3 = new MemberPO("00000002", "kunkun", "CroffCompany", "8888888", 4, 7.0, MemberType.Bussiness, null,"仙林大道163号");
		MemberPO member4 = new MemberPO("00000003", "zhuangzhuang", "刚轻厂", "6666666", 1, 8.0, MemberType.Bussiness, null,"新街口");
		assertEquals(true,memberData.addMember(member1));
		assertEquals(true,memberData.addMember(member2));
		assertEquals(true,memberData.addMember(member3));
		assertEquals(true,memberData.addMember(member4));
		assertEquals(true,memberData.addMember(member1));
	}

	@Test
	public void deleteMember() throws Exception {
		assertEquals(true, memberData.deleteMember("00000003"));
		assertEquals(false,memberData.deleteMember("00000008"));
	}

	@Test
	public void updateMember() throws Exception {
		MemberPO member3 = new MemberPO("00000002", "kunkun", "CroffCompany", "13909466189", 4, 7.0, MemberType.Bussiness, null,"仙林大道163号");
		assertEquals(true, memberData.updateMember(member3));
	}

	@Test
	public void getMember() throws Exception {
		assertEquals("00000001", memberData.getMember("00000001").getUserID());
	}

	@Test
	public void getAvailableMemberID() throws Exception {
		assertEquals("00000004",memberData.getAvailableMemberID());
	}

}