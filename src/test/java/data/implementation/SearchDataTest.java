package data.implementation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by apple on 2016/12/15.
 */
public class SearchDataTest {

	private SearchData searchData;
	@Before
	public void setUp() throws Exception {
		searchData = new SearchData();
	}

	@Test
	public void getHotelListByCityDistrict() throws Exception {
		assertEquals(2, searchData.getHotelListByCityDistrict("南京市","新街口").size());
		assertEquals(null, searchData.getHotelListByCityDistrict("上海市", "徐家汇"));
	}

	@Test
	public void getHotelListSortedByScore() throws Exception {
		assertEquals(1, searchData.getHotelListSortedByScore(4.5, 5.0,"南京市","新街口").size());
		assertEquals(null, searchData.getHotelListSortedByScore(2.0, 3.5, "南京市","新街口"));
	}

	@Test
	public void getHotelListFilteredByLevel() throws Exception {
		assertEquals(1, searchData.getHotelListFilteredByLevel(5, "南京市","新街口").size());
		assertEquals(null, searchData.getHotelListFilteredByLevel(2, "南京市","新街口"));
	}

	@Test
	public void getHotelListFilteredByPrice() throws Exception {
		assertEquals(2, searchData.getHotelListFilteredByPrice(500, 2000,"南京市","新街口").size());
		assertEquals(null, searchData.getHotelListFilteredByPrice(1000, 2000, "南京市", "大行宫"));
	}

}