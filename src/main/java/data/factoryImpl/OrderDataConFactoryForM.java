package data.factoryImpl;

import data.dataservice.OrderDataService;
import data.factory.OrderDataObstractFactory;
import data.implementation.OrderDataForM;

/**
 * Created by apple on 2016/11/29.
 */
public class OrderDataConFactoryForM implements OrderDataObstractFactory {
    public OrderDataService getOrdaerData() {
        return new OrderDataForM();
    }
}
