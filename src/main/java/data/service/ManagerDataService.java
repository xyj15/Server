package data.service;

import po.ManagerPO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by apple on 2016/12/1.
 * @author  张新悦
 * @version 2016-12-01
 */
public interface ManagerDataService extends Remote {
	
	public boolean updateManager(ManagerPO manager) throws RemoteException;
	public ManagerPO getManager() throws RemoteException;
}
