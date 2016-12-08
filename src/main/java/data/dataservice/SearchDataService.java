package data.dataservice;

import po.HotelPO;

import java.util.ArrayList;

/**
 * Created by apple on 2016/12/5.
 */
public interface SearchDataService {
	public ArrayList<HotelPO> getHotelListByCityDistrict(String city, String district);   //通过城市和商圈搜索酒店
	public void close();   //关闭输入流
}
