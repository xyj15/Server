package data.implementation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import po.SalerPO;

import static org.junit.Assert.*;

/**
 * Created by apple on 2016/12/15.
 */
public class SalerDataTest {

	private SalerData salerData;
	@Before
	public void setUp() throws Exception {
		salerData = new SalerData();
	}

	@Test
	public void addSaler() throws Exception {
		assertEquals(true, salerData.addSaler(new SalerPO("0010", "qianqian", "薛之谦", "123456789", false)));
		assertEquals(false, salerData.addSaler(new SalerPO("0000", "jingjing", "徐亚婧", "123456789", false)));
	}

	@Test
	public void deleteSaler() throws Exception {
		assertEquals(true, salerData.deleteSaler("0003"));
		assertEquals(false, salerData.deleteSaler("0003"));
	}

	@Test
	public void updateSaler() throws Exception {
		assertEquals(true, salerData.updateSaler(new SalerPO("0000", "jingjing", "徐亚婧", "13151530838", false)));
		assertEquals(false, salerData.updateSaler(new SalerPO("0008", "jingjing", "徐亚婧", "13151530838", false)));
	}

	@Test
	public void getSaler() throws Exception {
		assertEquals("0000", salerData.getSaler("0000").getUserID());
	}

	@Test
	public void getAvailableSalerID() throws Exception {
		assertEquals("0011", salerData.getAvailableSalerID());
	}

}