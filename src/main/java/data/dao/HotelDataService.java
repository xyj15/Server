package data.dao;

import java.util.ArrayList;

import po.HotelPO;
import po.MemberPO;
import po.OrderPO;
import po.RoomPO;

public interface HotelDataService {

	public MemberPO getMInformation(String memberID);
	public ArrayList<OrderPO> getOrderList (String hotelID,String time);
	public OrderPO getOrder(String orderID);
	public HotelPO getHotelInformat (String hotelID);
	public boolean updataOrder  (String orderID,OrderPO OR);
	public boolean updataHotelInformat (String hotelID,HotelPO po);
	public boolean check (String orderID,String memberID,String roomID,RoomPO RO,int mark);
}