package data.implementation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import po.ManagerPO;

import static org.junit.Assert.*;

/**
 * Created by apple on 2016/12/15.
 */
public class ManagerDataTest {

	private ManagerData managerData;
	@Before
	public void setUp() throws Exception {
		managerData = new ManagerData();
	}

	@Test
	public void updateManager() throws Exception {
		ManagerPO model = new ManagerPO("00", "zhuangzhuang", "zhuangzhuang","13151522158");
		assertEquals(true, managerData.updateManager(model));
	}

	@Test
	public void getManager() throws Exception {
		assertEquals("00", managerData.getManager().getUserID());
	}

}