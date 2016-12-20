package data.implementation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import po.HotelPO;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by apple on 2016/12/15.
 */
public class HotelDataTest {

	HotelData hotelData;

	@Before
	public void setUp() throws Exception {
		hotelData = new HotelData();
	}

	@Test
	public void addHotel() throws Exception {
		ArrayList<String> enterprise = new ArrayList<String>();
		enterprise.add("刚轻厂");
		enterprise.add("新悦集团");
		assertEquals(true , hotelData.addHotel(new HotelPO("000000","kunkun","CroffHotel", "珠江路", "新街口", "南京市",5, 4.8,"健身房、游泳池、spa","是一家专门针对高端人群的酒店。"
				,"张新悦", "15205153967",enterprise, false)));
		assertEquals(true, hotelData.addHotel(new HotelPO("000001","jingjing","亚婧大酒店", "石鼓路", "新街口", "南京市",5, 4.8,"健身房、自助餐厅、温泉","是亲子度假首选。"
				,"徐亚婧", "15205153967",enterprise, false)));
		assertEquals(true, hotelData.addHotel(new HotelPO("000002","helloworld","JavaHotel", "仙林大道", "仙林中心", "南京市",5, 4.8,"健身房、游泳池、spa","是一家专门针对高端人群的酒店"
				,"eclipse", "15205153967",enterprise, false)));
		assertEquals(false, hotelData.addHotel(new HotelPO("000000","kunkun","CroffHotel", "珠江路", "新街口", "南京市",5, 4.8,"健身房、游泳池、spa","是一家专门针对高端人群的酒店。"
				,"张新悦", "15205153967",enterprise, false)));
	}

	@Test
	public void deleteHotel() throws Exception {
		assertEquals(true, hotelData.deleteHotel("000000"));
		assertEquals(false, hotelData.deleteHotel("000007"));
	}

	@Test
	public void updateHotel() throws Exception {
		ArrayList<String> enterprise = new ArrayList<String>();
		enterprise.add("刚轻厂");
		enterprise.add("新悦集团");
		assertEquals(true, hotelData.updateHotel((new HotelPO("000001","jingjing","亚婧大酒店", "石鼓路", "新街口", "南京市", 3, 4.0,"健身房、自助餐厅、温泉","是亲子度假首选。"
				,"徐亚婧", "15205153967",enterprise, false))));
		assertEquals(false, hotelData.updateHotel(new HotelPO("000006","helloworld","JavaHotel", "仙林大道", "仙林中心", "南京市",5, 4.8,"健身房、游泳池、spa","是一家专门针对高端人群的酒店"
				,"eclipse", "15205153967",enterprise, false)));
	}

	@Test
	public void getHotelByID() throws Exception {
		assertEquals("000002", hotelData.getHotelByID("000002").getUserID());
	}

	@Test
	public void getHotelByName() throws Exception {
		assertEquals("JavaHotel", hotelData.getHotelByName("JavaHotel").getName());
	}

	@Test
	public void getAvailableHotelID() throws Exception {
		assertEquals("000003", hotelData.getAvailableHotelID());
	}

}