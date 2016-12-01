package data.factoryImpl;

import data.dataservice.OrderDataService;
import data.factory.OrderDataObstractFactory;
import data.implementation.OrderDataForH;

/**
 * Created by apple on 2016/11/29.
 */
public class OrderDataConFactoryForH implements OrderDataObstractFactory {
    public OrderDataService getOrdaerData() {
        return new OrderDataForH();
    }
}
