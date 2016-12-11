package driver;

import data.implementation.RankData;

import java.util.ArrayList;

/**
 * Created by apple on 2016/12/10.
 */
public class DriverForRankData {

	private RankData test = new RankData();

	public static void main(String[] args) {
		DriverForRankData driver = new DriverForRankData();
		ArrayList<Double> credit = new ArrayList<>();
		ArrayList<Double> discount = new ArrayList<>();
		for (int i = 0; i < 1000; i+=100) {
			Double cre = new Double(i);
			credit.add(cre);
		}
		for (int i = 0; i <10; i++) {
			Double dis = new Double(1-i/10.0);
			discount.add(dis);
		}
		System.out.println(driver.testUpdateCreditList(credit));
		System.out.println(driver.testUpdateDiscountList(discount));
		System.out.println(driver.testGetCreditList());
		System.out.println(driver.testGetDiscountList());
	}

	/**
	 *
	 * @param credits
	 * @return
	 */
	public boolean testUpdateCreditList(ArrayList<Double> credits){
		System.out.println("Update the list of credit");
		return test.updateCreditList(credits);
	}

	/**
	 *
	 * @param discounts
	 * @return
	 */
	public boolean testUpdateDiscountList(ArrayList<Double> discounts){
		System.out.println("Update the list of discount");
		return test.updateDiscountList(discounts);
	}

	/**
	 *
	 * @return
	 */
	public boolean testGetCreditList(){
		System.out.println("Look up for the list of credit");
		ArrayList<Double> result = test.getCreditList();
		if(result==null) return false;
		output(result);
		return true;
	}

	/**
	 *
	 * @return
	 */
	public boolean testGetDiscountList(){
		System.out.println("Look up for the list of discount");
		ArrayList<Double> result = test.getDiscountList();
		if(result==null) return false;
		output(result);
		return true;
	}

	private void output(ArrayList<Double> list){
		for (Double thisDouble: list
		     ) {
			System.out.println(thisDouble.doubleValue());
		}
	}

}
