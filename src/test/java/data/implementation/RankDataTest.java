package data.implementation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by apple on 2016/12/15.
 */
public class RankDataTest {

	private RankData rankData;
	@Before
	public void setUp() throws Exception {
		rankData = new RankData();
	}

	@Test
	public void getCreditList() throws Exception {
		assertEquals(10, rankData.getCreditList().size());
	}

	@Test
	public void getDiscountList() throws Exception {
		assertEquals(10, rankData.getDiscountList().size());
	}

	@Test
	public void updateCreditList() throws Exception {
		ArrayList<Double> credit = new ArrayList<>();
		for (int i = 0; i < 1000; i+=100) {
			Double cre = new Double(i);
			credit.add(cre);
		}
		assertEquals(true, rankData.updateCreditList(credit));
	}

	@Test
	public void updateDiscountList() throws Exception {
		ArrayList<Double> discount = new ArrayList<>();
		for (int i = 0; i <10; i++) {
			Double dis = new Double(1-i/10.0);
			discount.add(dis);
		}
		assertEquals(true, rankData.updateDiscountList(discount));
	}

}