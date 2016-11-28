package data.stub;

import java.util.ArrayList;

import data.dao.SearchDataService;
import po.*;

public class SearchDataStub implements SearchDataService {

	private ArrayList<HotelPO> hotelList;
	
	@Override
	public ArrayList<HotelPO> getHotelList(String str, searchHotelInfo info) {
		// TODO Auto-generated method stub
		ArrayList<HotelPO> resultList = new ArrayList<HotelPO>();
		
		if(info.equals(searchHotelInfo.ADDRESS)) {
			for(int i=0; i<hotelList.size(); i++) {
				if(str.equals(hotelList.get(i).getAddress())) {
					resultList.add(hotelList.get(i));
				}
			}
		} else {
			for(int i=0; i<hotelList.size(); i++) {
				if(str.equals(hotelList.get(i).getDistrict())) {
					resultList.add(hotelList.get(i));
				}
			}
		}
		return resultList;
	}

	@Override
	public ArrayList<RoomPO> getRoomList(String hotelID) {
		// TODO Auto-generated method stub
		for(int i=0; i<hotelList.size(); i++) {
			if(hotelList.get(i).getHotelID().equals(hotelID)) {
				return hotelList.get(i).getRoomList();
			}
		}
		return null;
	}

	public ArrayList<HotelPO> getHotelList() {
		return hotelList;
	}

	public void setHotelList(ArrayList<HotelPO> hotelList) {
		this.hotelList = hotelList;
	}

}
