package driver;

import data.dataservice.OrderDataService;
import data.factory.OrderDataObstractFactory;
import data.factoryImpl.OrderDataConFactory;
import helper.OrderStatus;
import helper.RoomType;
import po.OrderPO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by apple on 2016/12/9.
 */
public class DriverForOrder {

	private OrderDataService test;

	public static void main(String[] args){
		DriverForOrder driverForMember = new DriverForOrder("00000000");
		DriverForOrder driverForHotel = new DriverForOrder("000000");
		DriverForOrder driverForSaler = new DriverForOrder("0000");
		Date createTime = new Date(2016, 12, 9);
		Date checkInTime = new Date(2016, 12, 11);
		Date checkOutTime = new Date(2016, 12, 14);
		Date latestCheckInTime = new Date(2016, 12, 9);
		Date actualCheckInTime = null;
		Date actualCheckOutTime = null;
		Date cancelTime = null;
		driverForMember.testAddOrder(new OrderPO("00000000", "000000", "2016120900000000", OrderStatus.Unexecuted,
				createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,1,
				"豪华湖景房", 2, false, 0, null, 0, "00000", 1500, cancelTime, RoomType.BigBed));
		createTime = new Date(2016, 8, 25);
		checkInTime = new Date(2016, 9, 4);
		checkOutTime = new Date(2016, 9, 5);
		latestCheckInTime = new Date(2016, 9, 4);
		actualCheckInTime = new Date(2016, 9, 4);
		actualCheckOutTime = new Date(2016, 9 ,6);
		driverForMember.testAddOrder(new OrderPO("00000000", "000000", "2016082500000000", OrderStatus.Executed,
				createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,2,
				"商务单人间", 2, false, 0, null, 0, "00000",1660, cancelTime, RoomType.Single));
		createTime = new Date(2016, 10, 16);
		checkInTime = new Date(2016, 10, 20);
		checkOutTime = new Date(2016, 10, 25);
		latestCheckInTime = new Date(2016, 10, 4);
		actualCheckInTime = new Date(2016, 9, 4);
		actualCheckOutTime = new Date(2016, 9 ,6);

	}

	/**
	 *
	 * @param userID
	 */
	public DriverForOrder(String userID){
		OrderDataObstractFactory factory = new OrderDataConFactory();
		test = factory.getOrdaerData(userID);
	}

	/**
	 *
	 * @param order
	 * @return
	 */
	public boolean testAddOrder(OrderPO order){
		System.out.println("Add an order whose ID is "+order.getOrderID());
		return test.addOrder(order);
	}

	/**
	 *
	 * @param order
	 * @return
	 */
	public boolean testUpdateOrder(OrderPO order){
		System.out.println("Update an order whose ID is "+order.getOrderID());
		return test.updateOrder(order);
	}

	/**
	 *
	 * @param orderID
	 * @return
	 */
	public boolean testGetOrder(String orderID){
		System.out.println("Look up for an order whose ID is "+orderID);
		OrderPO result = test.getOrder(orderID);
		if(result==null) return false;
		output(result);
		return true;
	}

	/**
	 *
	 * @param userID
	 * @return
	 */
	public boolean testGetOrderList(String userID){
		System.out.println("Look up for orders belong to "+userID);
		ArrayList<OrderPO> result = test.getOrderList(userID);
		if(result==null) return false;
		for (OrderPO thisOrder: result
		     ) {
			output(thisOrder);
		}
		return true;
	}

	/**
	 *
	 * @param orderID
	 * @return
	 */
	public boolean testCancelOrder(String orderID){
		System.out.println("Cancel an order whose ID is "+orderID);
		return test.cancelOrder(orderID);
	}

	/**
	 *
	 * @param orderID
	 * @param recover
	 * @return
	 */
	public boolean testRecoverOrder(String orderID, double recover){
		System.out.println("Recover an order whose ID is "+orderID);
		return test.recoverOrder(orderID, recover);
	}

	/**
	 *
	 * @param orderID
	 * @return
	 */
	public boolean testMakeOrderAbnormal(String orderID){
		System.out.println("Make an order whose ID is "+orderID+" abnormal");
		return test.makeOrderAbnormal(orderID);
	}

	/**
	 *
	 * @param userID
	 * @return
	 */
	public boolean testGetUnfinishedOrders(String userID){
		ArrayList<OrderPO> result = test.getUnfinishedOrders(userID);
		if(result==null) return false;
		for (OrderPO thisOrder: result
		     ) {
			output(thisOrder);
		}
		return true;
	}

	/**
	 *
	 * @param userID
	 * @return
	 */
	public boolean testGetFinishedOrders(String userID){
		ArrayList<OrderPO> result = test.getFinishedOrders(userID);
		if(result==null) return false;
		for (OrderPO thisOrder: result
				) {
			output(thisOrder);
		}
		return true;
	}

	/**
	 *
	 * @param userID
	 * @return
	 */
	public boolean testGetCanceledOrders(String userID){
		ArrayList<OrderPO> result = test.getCancledOrders(userID);
		if(result==null) return false;
		for (OrderPO thisOrder: result
				) {
			output(thisOrder);
		}
		return true;
	}

	/**
	 *
	 * @param userID
	 * @return
	 */
	public boolean testGetAbnormalOrders(String userID){
		ArrayList<OrderPO> result = test.getAbnormalOrders(userID);
		if(result==null) return false;
		for (OrderPO thisOrder: result
				) {
			output(thisOrder);
		}
		return true;
	}

	/**
	 *
	 * @param order
	 */
	private void output(OrderPO order){
		SimpleDateFormat bartDateFormate = new SimpleDateFormat("MM-dd-yyyy");
		System.out.println("orderID: "+order.getOrderID());
		System.out.println("mamberID: "+order.getMemberID());
		System.out.println("hotelID: "+order.getHotelID());
		System.out.println("orderStatus: "+order.getOrderStatus());
		if(order.getOrderStatus()== OrderStatus.Canceled){
			System.out.println("cancel: "+bartDateFormate.format(order.getCancelTime()));
		}
		System.out.println("roomName: "+order.getRoomName());
		System.out.println("roomType: "+order.getRoomType());
		System.out.println("numberOfRoom: "+order.getNumberOfRoom());
		System.out.println("numberOfClient: "+order.getNumberOfClient());
		System.out.println("hasKid: "+order.getHaveKids());
		System.out.println("checkInTime: "+bartDateFormate.format(order.getCheckinTime()));
		System.out.println("checkOutTime: "+bartDateFormate.format(order.getCheckoutTime()));
		System.out.println("latesCheckInTime: "+bartDateFormate.format(order.getLatestCheckinTime()));
		System.out.println("promotionID: "+order.getPromotionID());
		System.out.println("price: "+order.getPrice());
		if(order.getOrderStatus()==OrderStatus.Executed){
			System.out.println("actualCheckInTime: "+bartDateFormate.format(order.getActualCheckinTime()));
			System.out.println("actualCheckOutTime: "+bartDateFormate.format(order.getActualCheckoutTime()));
			System.out.println("score: "+order.getScore());
			System.out.println("evaluation: "+order.getEvaluation());
			System.out.println("recover: "+order.getRecover());
		}
		System.out.println("createTime: "+bartDateFormate.format(order.getCreateTime()));
	}
}
