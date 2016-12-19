package data.service;

import data.service.OrderDataService;

import java.rmi.RemoteException;

/**
 * Created by apple on 2016/11/29.
 */
public interface OrderDataAbstractFactory {
   public OrderDataService getOrdaerData(String userID) throws RemoteException;
}
