package data.dataservice;

import po.HotelPO;

import java.util.ArrayList;

/**
 * Created by apple on 2016/12/5.
 */
public interface SearchDataService {
	public ArrayList<HotelPO> getHotelListByCityDistrict(String city, String district);   //通过城市和商圈搜索酒店
	public ArrayList<HotelPO> getHotelListSortedByScore(double lowScore, double highScore, String city,String district);   //通过评分过滤酒店
	public ArrayList<HotelPO> getHotelListFilteredByLevel(int level, String city,String district);   //通过星级过滤目标酒店
	public ArrayList<HotelPO> getHotelListFilteredByPrice(double lowPrice, double highPrice, String city,String district);   //通过价格过滤目标酒店
}
