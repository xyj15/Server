package driver;

import data.implementation.MemberData;
import other.MemberType;
import po.MemberPO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by apple on 2016/12/5.
 */
public class DriverForMemberData {

	private MemberData test = new MemberData();

	public static void main(String[] args){
		DriverForMemberData driver = new DriverForMemberData();
//		System.out.println(driver.testGetID());
//		MemberPO member1 = new MemberPO(driver.testGetID(), "张新悦", "xinxinpassword", "15205153967", 3, 5.0, MemberType.Orinary, getDate("02-11-1997"),"", false);
//		System.out.println(driver.testAdd(member1));
//		System.out.println(driver.testGetID());
//		MemberPO member2 = new MemberPO("000000001", "徐亚婧", "jingjinghh", "13151530838", 2, 6.0, MemberType.Orinary, getDate("03-03-1997"),"", false);
//		System.out.println(driver.testAdd(member2));
//		System.out.println(driver.testGetID());
//		MemberPO member3 = new MemberPO("00000002", "CroffCompany", "kunkundabeitou", "8888888", 4, 7.0, MemberType.Bussiness, null,"仙林大道163号", false);
//		System.out.println(driver.testAdd(member3));
//		System.out.println(driver.testGetID());
//		MemberPO member4 = new MemberPO("00000003", "刚轻厂", "badtemperzz", "6666666", 1, 8.0, MemberType.Bussiness, null,"新街口", false);
//		System.out.println(driver.testAdd(member4));
//		System.out.println(driver.testDelete("00000002"));
//		System.out.println(driver.testUpdate(new MemberPO("00000000", "xinxin", "张新悦", "15205153967", 7, 5.0, MemberType.Orinary, getDate("02-11-1997"),"", false)));
		driver.testGet("00000000");
	}

	public boolean testAdd(MemberPO member){
		System.out.println("Add a member");
		return test.addMember(member);
	}

	public boolean testDelete(String memberID){
		System.out.println("Delete a member");
		return test.deleteMember(memberID);
	}

	public boolean testGet(String memberID){
		System.out.println("Look up for a member");
		MemberPO goal = test.getMember(memberID);
		if(goal==null) return false;
		System.out.println("memberID: "+goal.getMemberID());
		System.out.println("password: "+goal.getPassword());
		System.out.println("memberName: "+goal.getName());
		System.out.println("telephone: "+goal.getPhone());
		System.out.println("level: "+goal.getLevel());
		System.out.println("isLogged: "+goal.isLoged());
		System.out.println("memberType: "+goal.getMemberType());
		if(goal.getMemberType()==MemberType.Orinary){
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("MM-dd-yyyy");
			System.out.println("birthday: "+bartDateFormat.format(goal.getBirthday()));
		}
		else{
			System.out.println("enterprise: "+goal.getEnterprise());
		}
		System.out.println("discount: "+goal.getDiscount());
		return true;
	}

	public boolean testUpdate(MemberPO member){
		System.out.println("Update the information of a member");
		return test.updateMember(member);
	}

	public String testGetID(){
		return test.getAvailableMemberID();
	}

	public static Date getDate(String day){
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("MM-dd-yyyy");
		try {
			return bartDateFormat.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


}
