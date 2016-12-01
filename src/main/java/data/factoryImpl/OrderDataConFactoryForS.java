package data.factoryImpl;

import data.dataservice.OrderDataService;
import data.factory.OrderDataObstractFactory;
import data.implementation.OrderDataForS;

/**
 * Created by apple on 2016/12/1.
 */
public class OrderDataConFactoryForS implements OrderDataObstractFactory {
	public OrderDataService getOrdaerData() {
		return new OrderDataForS();
	}
}
