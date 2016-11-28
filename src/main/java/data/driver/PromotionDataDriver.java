package data.driver;

import data.stub.PromotionDataStub;
import po.PromotionPO;

public class PromotionDataDriver{
	data.dao.PromotionDataService service = new PromotionDataStub();
	public void getUser() {
		// TODO Auto-generated method stub
		service.getPromotion("541322049");
		System.out.println("�ɹ��õ�����");
	}

	public PromotionPO getPromotion(String promotionID) {
		// TODO Auto-generated method stub
		return null;
	}

	public PromotionPO[] getPromotionList() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean changePromotion(PromotionPO promotion) {
		// TODO Auto-generated method stub
		System.out.println("�ɹ�����");
		System.out.println(service.update(promotion));
		return true;
	}

	public boolean addPromotion(PromotionPO promotion) {
		// TODO Auto-generated method stub
		System.out.println("�ɹ����");
		System.out.println(service.insert(promotion));
		return true;
	}
	
	public boolean delPromotion(PromotionPO promotion) {
		// TODO Auto-generated method stub
		System.out.println("�ɹ�ɾ��");
		System.out.println(service.delete(promotion));
		return true;
	}

	
	public static void main(String[] args) {
		PromotionDataDriver driver = new PromotionDataDriver();
		PromotionPO p = new PromotionPO("1997-3-4", 1, false, "2016-2-11", false,"12345678");
		driver.addPromotion(p);
		driver.getPromotion("1234");
		driver.getPromotionList();
		driver.changePromotion(p);
		driver.delPromotion(p);
	}



}
