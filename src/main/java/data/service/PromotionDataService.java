package data.service;

import po.PromotionPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Promotion模块bl层和data层之间的接口
 * @author CROFF
 * @version 2016-11-30
 */
public interface PromotionDataService extends Remote {
	
	public PromotionPO getPromotion(String promotionID) throws RemoteException;	//获得促销策略信息
	public boolean addPromotion(PromotionPO promotion) throws RemoteException;	//添加促销策略
	public boolean deletePromotion(String promotionID) throws RemoteException;	//删除促销策略
	public boolean updatePromotion(PromotionPO promotion) throws RemoteException;	//更新促销策略信息
	public ArrayList<PromotionPO> getPromotionList() throws RemoteException;	//获得促销策略列表
	public String getAvailablePromotionID() throws RemoteException;   //获得可用的PromotionID
}
