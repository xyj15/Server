package data.dataservice;

import po.CreditChangePO;

import java.util.ArrayList;

/**
 * Created by CROFF on 2016/12/1.
 * Credit模块bl层和data层之间的接口
 * @author CROFF
 * @version 2016-12-1
 */
public interface CreditDataService {
	
	public double getCredit(String memberID);	//根据客户ID获取最新客户信用
	public ArrayList<CreditChangePO> getCreditChange(String memberID);	//根据客户ID获取客户信用变化情况列表
	//public boolean setCredit(String memberID, double credit);	//设置客户的信用
	public boolean addCreditChange(String memberID, CreditChangePO creditChange);	//为客户添加新的信用变化情况
	public void close();   //关闭输入流
}
