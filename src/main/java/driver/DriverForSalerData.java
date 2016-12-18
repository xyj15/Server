package driver;

import data.implementation.SalerData;
import po.SalerPO;

/**
 * Created by apple on 2016/12/10.
 */
public class DriverForSalerData {

	private SalerData test = new SalerData();

	public static void main(String[] args) {
		DriverForSalerData driver = new DriverForSalerData();
//		System.out.println(driver.testAddSaler(new SalerPO("0000", "jingjing", "徐亚婧", "123456789")));
		System.out.println(driver.testAddSaler(new SalerPO("0003", "qianqian", "薛之谦", "123456789")));
//		System.out.println(driver.testAddSaler(new SalerPO(driver.testGetAvailableID(), "qianqian", "薛之谦", "123456789")));
//		System.out.println(driver.testDeleteSaler("0010"));
//		System.out.println(driver.testUpdateSaler(new SalerPO("0000", "jingjing", "徐亚婧", "13151530838")));
//		System.out.println(driver.testGetSaler("0010"));
		System.out.println(driver.testGetSaler("0003"));

	}

	/**
	 *
	 * @param saler
	 * @return
	 */
	public boolean testAddSaler(SalerPO saler){
		System.out.println("Add a saler with the ID "+saler.getUserID());
		return test.addSaler(saler);
	}

	/**
	 *
	 * @param salerID
	 * @return
	 */
	public boolean testDeleteSaler(String salerID){
		System.out.println("Delete a saler with the ID "+salerID);
		return test.deleteSaler(salerID);
	}

	/**
	 *
	 * @param saler
	 * @return
	 */
	public boolean testUpdateSaler(SalerPO saler){
		System.out.println("Update a saler with the ID "+saler.getUserID());
		return test.updateSaler(saler);
	}

	/**
	 *
	 * @param salerID
	 * @return
	 */
	public boolean testGetSaler(String salerID){
		System.out.println("Look up for a saler with the ID "+salerID);
		SalerPO result = test.getSaler(salerID);
		if(result == null) return false;
		output(result);
		return true;
	}

	public String testGetAvailableID(){
		return test.getAvailableSalerID();
	}

	private void output(SalerPO saler){
		System.out.println("salerID: "+saler.getUserID());
		System.out.println("password: "+saler.getPassword());
		System.out.println("name: "+saler.getName());
		System.out.println("Tel: "+saler.getTel());
	}
}
