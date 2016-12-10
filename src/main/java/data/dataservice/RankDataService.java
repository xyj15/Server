package data.dataservice;

import java.util.ArrayList;

/**
 * Created by CROFF on 2016/11/29.
 * Rank模块bl层和data层之间的接口
 * @author CROFF
 * @version 2016-12-1
 */
public interface RankDataService {
	
	public ArrayList<Double> getCreditList();	//获得升级所需信用表
	public ArrayList<Double> getDiscountList();	//获得每级享受折扣表
	public boolean updateCreditList(ArrayList<Double> creditList);	//更新升级所需信用表
	public boolean updateDiscountList(ArrayList<Double> discountList);	//更新每级享受折扣表
}
