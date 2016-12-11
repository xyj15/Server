package rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by CROFF on 2016/12/10.
 */
public class RemoteHelper {
	
	public RemoteHelper() {
		initServer();
	}
	
	public void initServer() {
		DataRemoteObject dataRemoteObject;
		
		try {
			dataRemoteObject = new DataRemoteObject();
			LocateRegistry.createRegistry(8888);
			Naming.bind("rmi://localhost:8888/DataRemoteObject", dataRemoteObject);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		RemoteHelper remoteHelper = new RemoteHelper();
		remoteHelper.initServer();
	}
}