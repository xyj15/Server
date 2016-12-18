package rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by CROFF on 2016/12/18.
 */
public class RemoteHelper {
	
	private RemoteDataService remoteDataService;
	
	public RemoteHelper() {
		initServer();
	}
	
	public void initServer() {
		try {
			remoteDataService = new RemoteDataService();
			LocateRegistry.createRegistry(3304);
			Naming.bind("rmi://localhost:3304/RemoteDataService", remoteDataService);
			System.out.println("创建服务器成功");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new RemoteHelper();
	}
}
