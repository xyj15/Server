package driver;

import data.implementation.CreditData;
import helper.OrderAction;
import po.CreditChangePO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apple on 2016/12/5.
 */
public class DriverForCreditData {

	private CreditData test = new CreditData();

	public static void main(String[] args){
		DriverForCreditData driver = new DriverForCreditData();
		Date day = new Date();
		day.setYear(2016);
		day.setMonth(3);
		day.setDate(12);
		driver.testAdd("00000000", new CreditChangePO(day,"2016120400000000", OrderAction.ExecuteOrder, 200, 400));
		day.setDate(6);
		day.setMonth(28);
		driver.testAdd("00000000", new CreditChangePO(day,"2016280700000000", OrderAction.CancelOrder, -50, 350));
		day.setDate(21);
		day.setMonth(9);
		driver.testAdd("00000000", new CreditChangePO(day,"2016211000000000", OrderAction.RechargeCredit, 500, 850));
		day.setMonth(4);
		day.setDate(15);
		driver.testAdd("00000001", new CreditChangePO(day,"2016150500000000", OrderAction.ExecuteOrder, 200, 300));
		day.setDate(8);
		day.setMonth(10);
		driver.testAdd("00000001", new CreditChangePO(day,"2016081100000000", OrderAction.AbnormalOrder, -200, 100));
		driver.testGetChanges("00000000");
		driver.testGetCredit("00000001");
	}

	public boolean testAdd(String memberID, CreditChangePO credit){
		System.out.println("addCreditChange");
		return test.addCreditChange(memberID,credit);
	}

	public void testGetCredit(String memberID){
		System.out.println("getCredit");
		System.out.println(test.getCredit(memberID));
	}

	public void testGetChanges(String memberID){
		System.out.println("CreditChangeRecords");
		ArrayList<CreditChangePO> result = test.getCreditChange(memberID);
		for (CreditChangePO temp: result) {
			System.out.println();
			System.out.println("orderID: "+temp.getOrderID());
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("MM-dd-yyyy");
			System.out.println("date: "+bartDateFormat.format(temp.getDate()));
			System.out.println("orderAction: "+temp.getOrderAction());
			System.out.println("change: "+temp.getChange());
			System.out.println("result: "+temp.getResult());
		}
	}
}
