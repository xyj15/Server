package driver;

import data.implementation.PromotionData;
import helper.PromotionType;
import helper.SaleType;
import jxl.write.DateTime;
import jxl.write.WriteException;
import po.PromotionPO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by apple on 2016/12/10.
 */
public class DriverForPromotionData {

	private PromotionData test = new PromotionData();

	public static void main(String[] args) {
		DriverForPromotionData driver = new DriverForPromotionData();
		PromotionPO model1 = new PromotionPO(driver.testGetID(),"关爱程序员营销活动", PromotionType.Discount);
		model1.setSaleType(SaleType.RoomNumber);
		model1.setDiscount(0.85);
		model1.setNumberOfRoom(3);
		System.out.println(driver.testAddPromotion(model1));
		PromotionPO model2 = new PromotionPO(driver.testGetID(),"代码节促销活动", PromotionType.Discount);
		model2.setSaleType(SaleType.Date);
		model2.setDiscount(0.7);
		model2.setStartDate(new Date(2016-1900,9,24));
		model2.setEndDate(new Date(2016-1900,9,26));
		System.out.println(driver.testAddPromotion(model2));
		PromotionPO model3 = new PromotionPO(driver.testGetID(),"会员等级折扣", PromotionType.Discount);
		model3.setSaleType(SaleType.Rank);
		model3.setDiscount(0.85);
		System.out.println(driver.testAddPromotion(model3));
		PromotionPO model4 = new PromotionPO(driver.testGetID(),"来自java的生日问候", PromotionType.Discount);
		model4.setSaleType(SaleType.Birthday);
		model4.setDiscount(0.75);
		System.out.println(driver.testAddPromotion(model4));
		PromotionPO model5 = new PromotionPO(driver.testGetID(),"与Intellij IDEA的合作折扣", PromotionType.Discount);
		model5.setSaleType(SaleType.Enterprise);
		model5.setDiscount(0.75);
		model5.setEnterprise("Intellij IDEA");
		System.out.println(driver.testAddPromotion(model5));
//		System.out.println(driver.testGetID());
//		System.out.println(driver.testUpdatePromotion(model1));
//		System.out.println(driver.testGetPromotion("00001"));
//		System.out.println(driver.testGetPromotionList());
	}

	/**
	 *
	 * @return
	 */
	public String testGetID(){
		return test.getAvailablePromotionID();
	}

	/**
	 *
	 * @param promotion
	 * @return
	 */
	public boolean testAddPromotion(PromotionPO promotion){
		System.out.println("Add a promotion whose ID is "+promotion.getPromotionID());
		return test.addPromotion(promotion);
	}

	/**
	 *
	 * @param promotionID
	 * @return
	 */
	public boolean testDeletePromotion(String promotionID){
		System.out.println("Delete a promotion whose ID is "+promotionID);
		return test.deletePromotion(promotionID);
	}

	/**
	 *
	 * @param promotion
	 * @return
	 */
	public boolean testUpdatePromotion(PromotionPO promotion){
		System.out.println("Update a promotion whose ID is "+promotion.getPromotionID());
		return test.updatePromotion(promotion);
	}

	/**
	 *
	 * @param promotionID
	 * @return
	 */
	public boolean testGetPromotion(String promotionID){
		System.out.println("Look up for a promotion whose ID is "+promotionID);
		PromotionPO result = test.getPromotion(promotionID);
		if(result==null) return false;
		output(result);
		return true;
	}

	public boolean testGetPromotionList(){
		System.out.println("Look up for promotions");
		ArrayList<PromotionPO> result = test.getPromotionList();
		if(result==null) return false;
		for (PromotionPO thisPromotion: result
		     ) {
			output(thisPromotion);
		}
		return true;
	}

	private void output(PromotionPO promotion){
		SimpleDateFormat bartDateFormate = new SimpleDateFormat("MM-dd-yyyy");
		System.out.println("promotionID: "+promotion.getPromotionID());
		System.out.println("name: "+promotion.getPromotionName());
		if(promotion.getRelatedHotelID()!=null)System.out.println("relatedHotelID: "+promotion.getRelatedHotelID());
		System.out.println("saleType: "+promotion.getSaleType());
		switch (promotion.getSaleType().getValue()){
			case 1: {
				System.out.println("startDate: "+bartDateFormate.format(promotion.getStartDate()));
				System.out.println("endDate: "+bartDateFormate.format(promotion.getEndDate()));
				break;
			}
			case 3: {
				System.out.println("numberOfRooms: "+promotion.getNumberOfRoom());
				break;
			}
			case 4: {
				System.out.println("enterprise: "+promotion.getEnterprise());
				break;
			}
			case 5: {
				System.out.println("district: "+promotion.getDistrict());
				break;
			}
			default:
				break;
		}
		System.out.println("promotionType: "+promotion.getPromotionType());
		switch (promotion.getPromotionType().getValue()){
			case 0:{
				System.out.println("discount: "+promotion.getDiscount());
				break;
			}
			case 1: {
				System.out.println("neededPrice: "+promotion.getNeededPrice());
				System.out.println("reducePrice: "+promotion.getReducePrice());
				break;
			}
		}
		System.out.println();
	}
}
