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
		Date createTime = new Date(2016-1900, 12, 9);
		Date checkInTime = new Date(2016-1900, 12, 11);
		Date checkOutTime = new Date(2016-1900, 12, 14);
		Date latestCheckInTime = new Date(2016-1900, 12, 9);
		Date actualCheckInTime = null;
		Date actualCheckOutTime = null;
		Date cancelTime = null;
//		try {
//			driverForMember.testAddOrder(new OrderPO("00000000", "000000", "161209000000", OrderStatus.Unexecuted,
//					createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,1,
//					"豪华湖景房", 2, false, 0, null, 0, "00000", 1500, cancelTime));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		createTime = new Date(2017-1900, 0, 2);
//		checkInTime = new Date(2017-1900, 0, 3);
//		checkOutTime = new Date(2017-1900, 0, 5);
//		latestCheckInTime = new Date(2017-1900, 0, 4);
//		try {
//			driverForMember.testAddOrder(new OrderPO("00000000", "000000", "170518000000", OrderStatus.Abnormal,
//					createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,1,
//					"景观园林双床房", 2, false, 0, null, 0, "00000", 998, cancelTime));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		createTime = new Date(2016-1900, 8, 25);
//		checkInTime = new Date(2016-1900, 9, 4);
//		checkOutTime = new Date(2016-1900, 9, 5);
//		latestCheckInTime = new Date(2016-1900, 9, 4);
//		actualCheckInTime = new Date(2016-1900, 9, 4);
//		actualCheckOutTime = new Date(2016-1900, 9 ,6);
//		try {
//			driverForMember.testAddOrder(new OrderPO("00000000", "000000", "160825000000", OrderStatus.Executed,
//					createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,2,
//					"商务单人间", 2, false, 4.7, "愉快的住宿体验", 0, "00000",1660, cancelTime));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		createTime = new Date(2016-1900, 10, 16);
//		checkInTime = new Date(2016-1900, 10, 20);
//		checkOutTime = new Date(2016-1900, 10, 25);
//		latestCheckInTime = new Date(2016-1900, 10, 21);
//		actualCheckInTime = new Date(2016-1900, 10, 22);
//		actualCheckOutTime = new Date(2016-1900, 10 ,26);
//		try {
//			driverForMember.testAddOrder(new OrderPO("00000000", "000000", "161016000000", OrderStatus.Executed,
//					createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,1,
//					"高档温馨家庭套房", 3, true, 4.8, "虽然除了一些差错，但酒店服务态度很好", 1, "00000", 2050, cancelTime));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		createTime = new Date(2016-1900, 11, 22);
//		checkInTime = new Date(2016-1900, 11, 23);
//		checkOutTime = new Date(2016-1900, 11, 25);
//		latestCheckInTime = new Date(2016-1900, 10, 24);
//		actualCheckInTime = null;
//		actualCheckOutTime = null;
//		cancelTime = new Date(2016, 11, 23);
//		try {
//			driverForMember.testAddOrder(new OrderPO("00000000", "000000", "161122000000", OrderStatus.Unexecuted,
//					createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,3,
//					"豪华湖景房", 6, false, 0, null, 0, "00000",4500, cancelTime));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
////		Date createTime = new Date(2016-1900, 12-1, 9);
////		Date checkInTime = new Date(2016-1900, 12-1, 11);
////		Date checkOutTime = new Date(2016-1900, 12-1, 14);
////		Date latestCheckInTime = new Date(2016-1900, 12-1, 9);
////		Date actualCheckInTime = null;
////		Date actualCheckOutTime = null;
////		Date cancelTime = null;
//		try {
//			driverForMember.testAddOrder(new OrderPO("00000000", "000002", "161209213558", OrderStatus.Unexecuted,
//					createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,1,
//					"豪华代码房", 2, false, 0, null, 0, "00000", 650, cancelTime));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		createTime = new Date(2017-1900, 0, 2);
//		checkInTime = new Date(2016-1900, 0, 13);
//		checkOutTime = new Date(2016-1900, 0, 14);
//		latestCheckInTime = new Date(2016-1900, 0, 13);
//		try {
//			driverForMember.testAddOrder(new OrderPO("00000000", "000002", "160618163756", OrderStatus.Abnormal,
//					createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,1,
//					"标准结对编程房", 2, false, 0, null, 0, "00000", 760, cancelTime));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		createTime = new Date(2016-1900, 9, 25);
//		checkInTime = new Date(2016-1900, 9, 4);
//		checkOutTime = new Date(2016-1900, 9, 5);
//		latestCheckInTime = new Date(2016-1900, 9, 4);
//		actualCheckInTime = new Date(2016-1900, 9, 4);
//		actualCheckOutTime = new Date(2016-1900, 9 ,6);
//		try {
//			driverForMember.testAddOrder(new OrderPO("00000000", "000002", "161025191853", OrderStatus.Executed,
//					createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,2,
//					"简约debug房", 2, false, 4.7, "愉快的住宿体验", 0, "00000",960, cancelTime));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		createTime = new Date(2016-1900, 10, 16);
//		checkInTime = new Date(2016-1900, 10, 20);
//		checkOutTime = new Date(2016-1900, 10, 25);
//		latestCheckInTime = new Date(2016-1900, 10, 21);
//		actualCheckInTime = new Date(2016-1900, 10, 22);
//		actualCheckOutTime = new Date(2016-1900, 10 ,26);
//		try {
//			driverForMember.testAddOrder(new OrderPO("00000000", "000002", "161116151908", OrderStatus.Executed,
//					createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,1,
//					"奢华海景测试房", 3, true, 4.8, "虽然除了一些差错，但酒店服务态度很好", 1, "00000", 1024, cancelTime));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		createTime = new Date(2016-1900, 11, 22);
//		checkInTime = new Date(2016-1900, 11, 23);
//		checkOutTime = new Date(2016-1900, 11, 25);
//		latestCheckInTime = new Date(2016-1900, 10, 24);
//		actualCheckInTime = null;
//		actualCheckOutTime = null;
//		cancelTime = new Date(2016, 11, 23);
//		try {
//			driverForMember.testAddOrder(new OrderPO("00000001", "000001", "1611220152436", OrderStatus.Unexecuted,
//					createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,3,
//					"浪漫沙滩海景大床房", 6, false, 0, null, 0, "00000",2100, cancelTime));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		createTime = new Date(2017-1900, 0, 1);
//		checkInTime = new Date(2016-1900, 0, 6);
//		checkOutTime = new Date(2016-1900, 0, 9);
//		latestCheckInTime = new Date(2016-1900, 0, 7);
//		try {
//			driverForMember.testAddOrder(new OrderPO("00000000", "000002", "160618163814", OrderStatus.Abnormal,
//					createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,1,
//					"标准结对编程房", 2, false, 0, null, 0, "00000", 760, cancelTime));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
		createTime = new Date(2017-1900, 0, 1);
		checkInTime = new Date(2016-1900, 0, 3);
		checkOutTime = new Date(2016-1900, 0, 4);
		latestCheckInTime = new Date(2016-1900, 0, 3);
		try {
			driverForMember.testAddOrder(new OrderPO("00000000", "000002", "160618168654", OrderStatus.Abnormal,
					createTime,checkInTime,actualCheckInTime, latestCheckInTime,checkOutTime, actualCheckOutTime,1,
					"标准结对编程房", 2, false, 0, null, 0, "00000", 760, cancelTime));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		try {
//			driverForMember.testGetOrder("160618163756");
			driverForHotel.testGetOrderList("000000");
//			driverForSaler.testGetOrderList("0000");
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	/**
	 *
	 * @param userID
	 */
	public DriverForOrderData(String userID){
		OrderDataAbstractFactory factory = new OrderDataConFactory();
		try {
			test = factory.getOrderData(userID);
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
