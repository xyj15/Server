package data.service;

import po.SalerPO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SalerDataService extends Remote {
	
	public boolean addSaler(SalerPO saler) throws RemoteException;
	public boolean deleteSaler(String salerID) throws RemoteException;
	public boolean updateSaler(SalerPO saler) throws RemoteException;
	public SalerPO getSaler(String ID) throws RemoteException;
	public String getAvailableSalerID() throws RemoteException;   //得到可用的新增ID
}
