package driver;

import data.implementation.SearchData;
import po.HotelPO;

import java.util.ArrayList;

/**
 * Created by apple on 2016/12/10.
 */
public class DriverForSearchData {

	private SearchData test = new SearchData();

	public static void main(String[] args){
//		driver.testGetHotelByCityDistrict("南京市", "新街口");
//		driver.testGetHotelByName("JavaHotel");
//		System.out.println(driver.testGetHotelByID("000101"));
//		System.out.println(driver.testGetHotelByName("CppHotel"));
//		System.out.println(driver.testGetHotelByCityDistrict("上海市", "徐家汇"));
//		driver.testGetHotelByLevel(5,"南京市", "新街口");
//		System.out.println(driver.testGetHotelByLevel(2, "南京市", "新街口"));
//		driver.testGetHotelByScore(4.5, 5.0, "南京市", "新街口");
//		System.out.println(driver.testGetHotelByScore(2.0, 3.5, "南京市", "新街口"));
//		driver.testGetHotelFilteredByPrice(450, 600, "南京市", "新街口");
//		System.out.println(driver.testGetHotelFilteredByPrice(500, 2000, "南京市", "新街口"));
//		System.out.println(driver.testGetHotelFilteredByPrice(1000, 2000, "南京市", "大行宫"));
	}

	/**
	 *
	 * @param city
	 * @param district
	 */
	public boolean testGetHotelByCityDistrict(String city, String district){
		System.out.println("Find hotels that locate in "+city+" "+district);
		ArrayList<HotelPO> result = test.getHotelListByCityDistrict(city, district);
		if(result==null) return false;
		for (HotelPO temp: result) {
			outputHotel(temp);
		}
		return true;
	}

	/**
	 *
	 * @param low
	 * @param high
	 * @param city
	 * @param district
	 */
	public boolean testGetHotelByScore(double low, double high, String city, String district){
		System.out.println("Find hotels that locate in "+city+" "+district+" and whose score is between "+low+" and "+high);
		ArrayList<HotelPO> result = test.getHotelListSortedByScore(low, high, city, district);
		if(result==null) return false;
		for (HotelPO temp: result) {
			outputHotel(temp);
		}
		return true;
	}

	/**
	 *
	 * @param level
	 * @param city
	 * @param district
	 */
	public boolean testGetHotelByLevel(int level, String city, String district){
		System.out.println("Find hotels that locate in "+city+" "+district+" and whose level is "+level);
		ArrayList<HotelPO> result = test.getHotelListFilteredByLevel(level, city, district);
		if(result==null) return false;
		for (HotelPO temp: result) {
			outputHotel(temp);
		}
		return true;
	}

	/**
	 *
	 * @param low
	 * @param high
	 * @param city
	 * @param district
	 */
	public boolean testGetHotelFilteredByPrice(double low, double high, String city, String district){
		System.out.println("Find hotels that locate in "+city+" "+district+" and whose price is between "+low+" and "+high);
		ArrayList<HotelPO> result = test.getHotelListFilteredByPrice(low, high, city, district);
		if(result==null) return false;
		for (HotelPO temp: result) {
			outputHotel(temp);
		}
		return true;
	}

	/**
	 *
	 * @param hotel
	 */
	private void outputHotel(HotelPO hotel){
		System.out.println("hotelID: "+hotel.getUserID());
		System.out.println("password: "+hotel.getPassword());
		System.out.println("name: "+hotel.getName());
		System.out.println("city: "+hotel.getCity());
		System.out.println("district: "+hotel.getDistrict());
		System.out.println("address: "+hotel.getAddress());
		System.out.println("introduction: "+hotel.getIntroduction());
		System.out.println("service: "+hotel.getService());
		System.out.println("level: "+hotel.getLevel());
		System.out.println("score: "+hotel.getScore());
		System.out.println("managerName: "+hotel.getManagerName());
		System.out.println("managerTel: "+hotel.getManagerTel());
		System.out.print("enterprise: ");
		ArrayList<String> enterprise = hotel.getEnterpriseList();
		for (String temp: enterprise) {
			System.out.print(temp+" ");
		}
		System.out.println();
	}
}
