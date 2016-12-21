package data.service;

import po.HotelPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by apple on 2016/12/5.
 */
public interface SearchDataService extends Remote {

	public ArrayList<String> getCityList() throws RemoteException;	//获取所有城市的列表
	public ArrayList<String> getDistrictList(String city) throws RemoteException;	//获取所有商圈的列表

	public ArrayList<HotelPO> getHotelListByCityDistrict(String city, String district) throws RemoteException;   //通过城市和商圈搜索酒店
	public ArrayList<HotelPO> getHotelListSortedByScore(double lowScore, double highScore, String city,String district) throws RemoteException;   //通过评分过滤酒店
	public ArrayList<HotelPO> getHotelListFilteredByLevel(int level, String city,String district) throws RemoteException;   //通过星级过滤目标酒店
	public ArrayList<HotelPO> getHotelListFilteredByPrice(double lowPrice, double highPrice, String city,String district) throws RemoteException;   //通过价格过滤目标酒店
}
