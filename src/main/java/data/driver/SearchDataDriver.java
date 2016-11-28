package data.driver;

import data.dao.SearchDataService.searchHotelInfo;
import data.stub.SearchDataStub;

/**
 * searchData的driver
 * @author CROFF
 * @version 2016-11-05 19:36
 */
public class SearchDataDriver {

	SearchDataStub search;
	
	public SearchDataDriver(SearchDataStub search) {
		this.search = search;
	}
	
	public void drive() {
		search.getHotelList("XianLinBigRoad", searchHotelInfo.ADDRESS);
		search.getRoomList("88888888");
	}
	
	public static void main(String[] args) {
		new SearchDataDriver(new SearchDataStub());
	}

}