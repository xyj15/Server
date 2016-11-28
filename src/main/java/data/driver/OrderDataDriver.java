package data.driver;

import data.stub.OrderDataStub;
import po.OrderPO;

public class OrderDataDriver {
	OrderDataStub service = new OrderDataStub();
	public void getOrder() {
		// TODO Auto-generated method stub
		service.getOrder("541322049");
		System.out.println("�ɹ��õ�����");
	}

	
	public void updateOrder(OrderPO user) {
		// TODO Auto-generated method stub
		System.out.println("�ɹ�����");
		System.out.println(service.update(user));
	}

	public void addOrder(OrderPO user) {
		// TODO Auto-generated method stub
		System.out.println("�ɹ����");
		System.out.println(service.insert(user));
	}

	
	public void deleteOrder(OrderPO user) {
		// TODO Auto-generated method stub
		System.out.println("�ɹ�ɾ��");
		System.out.println(service.delete(user));
	}


	public void getList() {
		// TODO Auto-generated method stub
		System.out.println(service.getOrderList("limit"));
	}
	
	public static void main(String[] args) {
		OrderDataDriver driver = new OrderDataDriver();
		OrderPO Order = new OrderPO("1997-2-2", "1997-2-5", "�󴲷�", 2, "��", 0, 450);
		driver.addOrder(Order);
		driver.getOrder();
		driver.getList();
		driver.updateOrder(Order);
		driver.deleteOrder(Order);
	}



}
