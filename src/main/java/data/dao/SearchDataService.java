package data.dao;

import java.util.ArrayList;

import po.*;

public interface SearchDataService {

	public ArrayList<HotelPO> getHotelList(String str, searchHotelInfo info);
	public ArrayList<RoomPO> getRoomList(String hotelID);
	
	enum searchHotelInfo {
		DISTRICT, ADDRESS
	}
}

