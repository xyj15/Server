package driver;

import data.service.OrderDataService;
import data.service.OrderDataAbstractFactory;
import data.factoryImpl.OrderDataConFactory;
import other.OrderStatus;
import po.OrderPO;

import java.rmi.RemoteException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by apple on 2016/12/9.
 */
public class DriverForOrderData {

	private OrderDataService test;

	public static void main(String[] args) {
		DriverForOrderData driverForMember = new DriverForOrderData("00000000");
		DriverForOrderData driverForHotel = new DriverForOrderData("000000");
		DriverForOrderData driverForSaler = new DriverForOrderData("0000");
		SimpleDateFormat bartDateFormate = new SimpleDateFormat("MM-dd-yyyy");
		Date createTime = new Date(2016, 12, 9);
		System.err.println(bartDateFormate.format(createTime));
//		Date checkInTime = new Date(2016, 12, 11);
//		Date checkOutTime = new Date(2016, 12, 14);
//		Date latestCheckInTime = new Date(2016, 12, 9);
//		Date actualCheckInTime = null;
//		Date actualCheckOutTime = null;
//		Date cancelTime = null;
//		driverForMember.testAddOrder(new OrderPO("00000000", "000000", "2016120900000000", OrderStatus.Unexecuted,
//				createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,1,
//				"豪华湖景房", 2, false, 0, null, 0, "00000", 1500, cancelTime, RoomType.BigBed));
//		createTime = new Date(2016, 5, 18);
//		checkInTime = new Date(2016, 5, 20);
//		checkOutTime = new Date(2016, 5, 21);
//		latestCheckInTime = new Date(2016, 5, 20);
//		driverForMember.testAddOrder(new OrderPO("00000000", "000000", "2016051800000000", OrderStatus.Abnormal,
//				createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,1,
//				"景观园林双床房", 2, false, 0, null, 0, "00000", 998, cancelTime, RoomType.TwinBed));
//		createTime = new Date(2016, 8, 25);
//		checkInTime = new Date(2016, 9, 4);
//		checkOutTime = new Date(2016, 9, 5);
//		latestCheckInTime = new Date(2016, 9, 4);
//		actualCheckInTime = new Date(2016, 9, 4);
//		actualCheckOutTime = new Date(2016, 9 ,6);
//		driverForMember.testAddOrder(new OrderPO("00000000", "000000", "2016082500000000", OrderStatus.Executed,
//				createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,2,
//				"商务单人间", 2, false, 4.7, "愉快的住宿体验", 0, "00000",1660, cancelTime, RoomType.Single));
//		createTime = new Date(2016, 10, 16);
//		checkInTime = new Date(2016, 10, 20);
//		checkOutTime = new Date(2016, 10, 25);
//		latestCheckInTime = new Date(2016, 10, 21);
//		actualCheckInTime = new Date(2016, 10, 22);
//		actualCheckOutTime = new Date(2016, 10 ,26);
//		driverForMember.testAddOrder(new OrderPO("00000000", "000000", "2016101600000000", OrderStatus.Executed,
//				createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,1,
//				"高档温馨家庭套房", 3, true, 4.8, "虽然除了一些差错，但酒店服务态度很好", 1, "00000", 2050, cancelTime, RoomType.Suite));
//		createTime = new Date(2016, 11, 22);
//		checkInTime = new Date(2016, 11, 23);
//		checkOutTime = new Date(2016, 11, 25);
//		latestCheckInTime = new Date(2016, 10, 24);
//		actualCheckInTime = null;
//		actualCheckOutTime = null;
//		cancelTime = new Date(2016, 11, 23);
//		driverForMember.testAddOrder(new OrderPO("00000000", "000000", "2016112200000000", OrderStatus.Unexecuted,
//				createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,3,
//				"豪华湖景房", 6, false, 0, null, 0, "00000",4500, cancelTime, RoomType.BigBed));
//		driverForMember.testGetOrder("2016120900000000");
//		driverForHotel.testGetOrderList("000001");
//		driverForSaler.testGetOrderList("0000");
	}

	/**
	 *
	 * @param userID
	 */
	public DriverForOrderData(String userID){
		OrderDataAbstractFactory factory = new OrderDataConFactory();
		try {
			test = factory.getOrdaerData(userID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param order
	 * @return
	 */
	public boolean testAddOrder(OrderPO order) throws RemoteException {
		System.out.println("Add an order whose ID is "+order.getOrderID());
		return test.addOrder(order);
	}

	/**
	 *
	 * @param order
	 * @return
	 */
	public boolean testUpdateOrder(OrderPO order) throws RemoteException {
		System.out.println("Update an order whose ID is "+order.getOrderID());
		return test.updateOrder(order);
	}

	/**
	 *
	 * @param orderID
	 * @return
	 */
	public boolean testGetOrder(String orderID) throws RemoteException {
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
	public boolean testGetOrderList(String userID) throws RemoteException {
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
	public boolean testCancelOrder(String orderID) throws RemoteException {
		System.out.println("Cancel an order whose ID is "+orderID);
		return test.cancelOrder(orderID);
	}

	/**
	 *
	 * @param orderID
	 * @param recover
	 * @return
	 */
	public boolean testRecoverOrder(String orderID, double recover) throws RemoteException {
		System.out.println("Recover an order whose ID is "+orderID);
		return test.recoverOrder(orderID, recover);
	}

	/**
	 *
	 * @param orderID
	 * @return
	 */
	public boolean testMakeOrderAbnormal(String orderID) throws RemoteException {
		System.out.println("Make an order whose ID is "+orderID+" abnormal");
		return test.makeOrderAbnormal(orderID);
	}

	/**
	 *
	 * @param userID
	 * @return
	 */
	public boolean testGetUnfinishedOrders(String userID) throws RemoteException {
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
	public boolean testGetFinishedOrders(String userID) throws RemoteException {
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
	public boolean testGetCanceledOrders(String userID) throws RemoteException {
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
	public boolean testGetAbnormalOrders(String userID) throws RemoteException {
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
