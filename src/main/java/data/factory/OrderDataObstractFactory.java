package data.factory;

import data.service.OrderDataService;

/**
 * Created by apple on 2016/11/29.
 */
public interface OrderDataObstractFactory {
   public OrderDataService getOrdaerData(String userID);
}
