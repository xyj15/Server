package data.dao;

import po.OrderPO;

public interface OrderDataService {
	public boolean insert(OrderPO user);
	public boolean delete(OrderPO user);
	public boolean update(OrderPO user);
	public OrderPO getOrder(String orderID);
	public OrderPO[] getOrderList(String userID);
}
