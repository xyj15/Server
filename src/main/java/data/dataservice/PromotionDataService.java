package data.dataservice;

import po.PromotionPO;

import java.util.ArrayList;

/**
 * Promotion模块bl层和data层之间的接口
 * @author CROFF
 * @version 2016-11-30
 */
public interface PromotionDataService {
	
	public PromotionPO getPromotion(String promotionID);	//获得促销策略信息
	public boolean addPromotion(PromotionPO promotion);	//添加促销策略
	public boolean deletePromotion(String promotionID);	//删除促销策略
	public boolean updatePromotion(String promotionID, PromotionPO promotion);	//更新促销策略信息
	public ArrayList<PromotionPO> getPromotionList();	//获得促销策略列表
}
