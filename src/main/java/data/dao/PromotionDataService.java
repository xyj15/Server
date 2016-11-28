package data.dao;

import po.PromotionPO;

public interface PromotionDataService {
	public boolean insert(PromotionPO user);
	public boolean delete(PromotionPO user);
	public boolean update(PromotionPO user);
	public PromotionPO getPromotion(String promotionID);
	public PromotionPO[] getPromotionList(String userID);
}
