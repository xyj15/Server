package driver;

import data.implementation.ManagerData;
import po.ManagerPO;

/**
 * Created by apple on 2016/12/10.
 */
public class DriverForManagerData {

	private ManagerData test = new ManagerData();

	public static void main(String[] args) {
		DriverForManagerData driver = new DriverForManagerData();
		System.out.println(driver.testUpdateManager(new ManagerPO("00", "zhuangzhuang", "zhuangzhuang","13151522158", false)));
		System.out.println(driver.testGetManager());
	}

	/**
	 *
	 * @param manager
	 * @return
	 */
	public boolean testUpdateManager(ManagerPO manager){
		System.out.println("Update the information of the manager");
		return test.updateManager(manager);
	}

	public boolean testGetManager(){
		System.out.println("Look up for the information of the manager");
		ManagerPO result = test.getManager();
		if(result==null) return false;
		output(result);
		return true;
	}

	private void output(ManagerPO manager){
		System.out.println("managerID: "+manager.getUserID());
		System.out.println("password: "+manager.getPassword());
		System.out.println("name: "+manager.getName());
		System.out.println("Tel: "+manager.getTel());
	}
}
