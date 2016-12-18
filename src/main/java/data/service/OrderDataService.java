package data.service;

import po.OrderPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface OrderDataService extends Remote {
	
	public boolean addOrder(OrderPO order) throws RemoteException;  //添加订单
	public boolean updateOrder(OrderPO order) throws RemoteException;  //更新订单
	public boolean cancelOrder(String orderID) throws RemoteException;  //取消订单
	public boolean makeOrderAbnormal(String orderID) throws RemoteException;  //将订单置为异常
	public boolean recoverOrder(String orderID,double recover) throws RemoteException;  //恢复异常订单
	public OrderPO getOrder(String orderID) throws RemoteException;  //根据订单编号查找订单
	public ArrayList<OrderPO> getOrderList(String userID) throws RemoteException;  //根据客户编号查找其所有订单列表
	public ArrayList<OrderPO> getFinishedOrders(String userID) throws RemoteException;  //根据客户编号查找其所有已执行订单列表
	public ArrayList<OrderPO> getUnfinishedOrders(String userID) throws RemoteException;  //根据客户编号查找其所有未执行订单列表
	public ArrayList<OrderPO> getAbnormalOrders(String userID) throws RemoteException;  //根据客户编号查找其所有异常订单列表
	public ArrayList<OrderPO> getCancledOrders(String userID) throws RemoteException;  //根据客户编号查找其所有取消订单列表
}
