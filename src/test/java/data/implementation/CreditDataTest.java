package data.implementation;

import helper.OrderAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import po.CreditChangePO;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by apple on 2016/12/15.
 */
public class CreditDataTest {

	CreditData creditData;

	@Before
	public void setUp() throws Exception {
		creditData = new CreditData();

	}

	@Test
	public void addCreditChange() throws Exception {
		Date day = new Date();
		day.setYear(2016);
		day.setMonth(3);
		day.setDate(12);
		creditData.addCreditChange("00000000", new CreditChangePO(day,"2016120400000000", OrderAction.ExecuteOrder, 200, 400));
		assertEquals(true, creditData.addCreditChange("00000000", new CreditChangePO(day,"2016120400000000", OrderAction.ExecuteOrder, 200, 400)));
		day.setDate(6);
		day.setMonth(28);
		assertEquals(true,creditData.addCreditChange("00000000", new CreditChangePO(day,"2016280700000000", OrderAction.CancelOrder, -50, 350)));
		day.setDate(21);
		day.setMonth(9);
		assertEquals(true, creditData.addCreditChange("00000000", new CreditChangePO(day,"2016211000000000", OrderAction.RechargeCredit, 500, 850)));
		day.setMonth(4);
		day.setDate(15);
		assertEquals(true, creditData.addCreditChange("00000001", new CreditChangePO(day,"2016150500000000", OrderAction.ExecuteOrder, 200, 300)));
		day.setDate(8);
		day.setMonth(10);
		assertEquals(true, creditData.addCreditChange("00000001", new CreditChangePO(day,"2016081100000000", OrderAction.AbnormalOrder, -200, 100)));
	}

	@Test
	public void getCredit() throws Exception {
		assertEquals(850,  creditData.getCredit("00000000"), 0.1);
	}

	@Test
	public void getCreditChange() throws Exception {
		Date day = new Date();
		day.setYear(2016);
		day.setMonth(4);
		day.setDate(15);
		ArrayList<CreditChangePO> result = creditData.getCreditChange("00000001");
		ArrayList<CreditChangePO> expected = new ArrayList<>();
		expected.add(new CreditChangePO(day,"2016150500000000", OrderAction.ExecuteOrder, 200, 300));
		day.setDate(8);
		day.setMonth(10);
		expected.add(new CreditChangePO(day,"2016081100000000", OrderAction.AbnormalOrder, -200, 100));
		assertEquals(expected.get(0).getOrderID(), result.get(0).getOrderID());
		assertEquals(expected.get(1).getOrderID(),result.get(1).getOrderID());
	}

}