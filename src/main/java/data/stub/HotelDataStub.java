package data.stub;

import java.util.ArrayList;

import data.dao.HotelDataService;
import po.HotelPO;
import po.MemberPO;
import po.OrderPO;
import po.RoomPO;

public class HotelDataStub implements HotelDataService {

	String memberID;
	String name;
	String level;
	String address;
	String district;
	int credit;
	int phone;
	String checkInTime;
	String checkOutTime;
	String roomType;
	int roomNumber;
	String promotion;
	double discount;
	double price;
	
	public HotelDataStub(String memberID,String name,String level,String address,
			String district,int credit,int phone,String checkInTime,String checkOutTime,
			String roomType,int roomNumber,String promotion,double discount,double price){
		this.memberID=memberID;
		this.name=name;
		this.level=level;
		this.address=address;
		this.district=district;
		this.credit=credit;
		this.phone=phone;
		this.checkInTime=checkInTime;
		this.checkOutTime=checkOutTime;
		this.roomType=roomType;
		this.roomNumber=roomNumber;
		this.promotion=promotion;
		this.district=district;
		this.price=price;
	}
	
	@Override
	public MemberPO getMInformation(String memberID) {
		// TODO Auto-generated method stub
		return new MemberPO(memberID,name,level,credit,phone);
	}

	@Override
	public ArrayList<OrderPO> getOrderList(String hotelID, String time) {
		// TODO Auto-generated method stub
		ArrayList<OrderPO> orderList=new ArrayList<OrderPO>();
		orderList.add(new OrderPO(checkInTime, checkOutTime, roomType, roomNumber,
				promotion, discount, price));
		return orderList;
	}

	@Override
	public OrderPO getOrder(String orderID) {
		// TODO Auto-generated method stub
		return new OrderPO(checkInTime, checkOutTime, roomType, roomNumber,
				promotion, discount, price);
	}

	@Override
	public HotelPO getHotelInformat(String hotelID) {
		// TODO Auto-generated method stub
		return new HotelPO(hotelID, name, address, level, district);
	}

	@Override
	public boolean updataOrder(String orderID, OrderPO OR) {
		// TODO Auto-generated method stub
		System.out.println("订单更新成功");
		return true;
	}

	@Override
	public boolean updataHotelInformat(String hotelID,HotelPO po) {
		// TODO Auto-generated method stub
		System.out.println("酒店信息更新成功");
		return true;
	}

	@Override
	public boolean check(String orderID, String memberID, String roomID,RoomPO RO,int mark) {
		// TODO Auto-generated method stub
		if(mark==1){
			System.out.println("入住成功");
			return true;
		}
		else if(mark==0){
			System.out.println("退房成功");
			return true;
		}
		else{
			System.out.println("操作失败");
			return false;
		}
	}
	
}