package data.dataservice;

import po.HotelPO;

import java.util.ArrayList;

/**
 * Created by zhangxinyue on 2016/12/1.
 */
public interface HotelDataService {
	public boolean addHotel(HotelPO hotel);
	public boolean deleteHotel(String hotelID);
	public boolean updateHotel(HotelPO hotel);
	public HotelPO getHotelByID(String hotelID);
	public ArrayList<HotelPO> getHotelListByCityDistrict(String city,String district);
	public ArrayList<HotelPO> getHotelListSortedByScore(double lowScore, double highScore, String city,String district);   //通过评分过滤酒店
	public ArrayList<HotelPO> getHotelListFilteredByLevel(int level, String city,String district);   //通过星级过滤目标酒店
	public ArrayList<HotelPO> getHotelListFilteredByPrice(double lowPrice, double highPrice, String city,String district);   //通过价格过滤目标酒店
	public String getAvailableID();   //得到可用的新增ID
	public void close();   //关闭输入流
}
