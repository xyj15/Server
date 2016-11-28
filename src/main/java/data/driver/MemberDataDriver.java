package data.driver;


import data.stub.MemberDataStub;
import po.MemberPO;


public class MemberDataDriver {

	static String memberID="0001";
	static String name="sadasd";
	static String level="max";
	static int credit=5000;
	static int phone=110;
	
	public void drive(MemberPO M){
		MemberDataStub memberDate = new MemberDataStub(memberID, name,  level, credit, phone);
		System.out.println(memberDate.saveMInformation(memberID, M));
		System.out.println(memberDate.getMInformation(memberID));
		System.out.println(memberDate.getCreditList(memberID));
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberDataDriver drive =new MemberDataDriver();
		drive.drive(new MemberPO(memberID, name, level, credit, phone));
	}

}
