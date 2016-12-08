package data.dataservice;

import po.OrderPO;

import java.util.ArrayList;

public interface OrderDataService {
	public boolean addOrder(OrderPO order);  //添加订单
	public boolean updateOrder(OrderPO order);  //更新订单
	public boolean cancelOrder(String orderID);  //取消订单
	public boolean makeOrderAbnormal(String orderID);  //将订单置为异常
	public boolean recoverOrder(String orderID,double recover);  //恢复异常订单
	public OrderPO getOrder(String orderID);  //根据订单编号查找订单
	public ArrayList<OrderPO> getOrderList(String userID);  //根据客户编号查找其所有订单列表
	public ArrayList<OrderPO> getFinishedOrders(String userID);  //根据客户编号查找其所有已执行订单列表
	public ArrayList<OrderPO> getUnfinishedOrders(String userID);  //根据客户编号查找其所有未执行订单列表
	public ArrayList<OrderPO> getAbnormalOrders(String userID);  //根据客户编号查找其所有异常订单列表
	public ArrayList<OrderPO> getCancledOrders(String userID);  //根据客户编号查找其所有取消订单列表
	public void close();   //关闭输入流
}
