package data.stub;
import data.dao.OrderDataService;
import po.OrderPO;

public class OrderDataStub implements OrderDataService {
	
	@Override
	public boolean insert(OrderPO Order) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean delete(OrderPO Order) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean update(OrderPO Order) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public OrderPO getOrder(String orderID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public OrderPO[] getOrderList(String OrderID) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
