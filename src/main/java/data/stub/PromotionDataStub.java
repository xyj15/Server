package data.stub;
import data.dao.PromotionDataService;
import po.PromotionPO;

public class PromotionDataStub implements PromotionDataService{
	
	@Override
	public boolean insert(PromotionPO user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean delete(PromotionPO user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean update(PromotionPO user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public PromotionPO[] getPromotionList(String userID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public PromotionPO getPromotion(String promotionID) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
