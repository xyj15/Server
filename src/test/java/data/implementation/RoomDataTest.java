package data.implementation;

import other.RoomType;
import org.junit.Before;
import org.junit.Test;
import po.RoomPO;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by apple on 2016/12/15.
 */
public class RoomDataTest {

	private RoomData roomData;
	@Before
	public void setUp() throws Exception {
		roomData = new RoomData();
	}

	@Test
	public void getSingleRoom() throws Exception {
		Date day = new Date();
		assertEquals("314", roomData.getSingleRoom(day, "314", "000002").getRoomNumber());
	}

	@Test
	public void addSingleRoom() throws Exception {
		assertEquals(true, roomData.addSingleRoom(new RoomPO(false, true, "1308", "浪漫沙滩海景大床房", RoomType.BigBed, 350), "000001"));
		assertEquals(true, roomData.addSingleRoom(new RoomPO(false, true, "1516", "舒适阳光单人房", RoomType.Single, 210), "000001"));
		assertEquals(true, roomData.addSingleRoom(new RoomPO(false, true, "2001", "豪华泰式温泉套房", RoomType.Suite, 520), "000001"));
		assertEquals(true, roomData.addSingleRoom(new RoomPO(false, true, "906", "标准双床房", RoomType.TwinBed, 370), "000001"));
	}

	@Test
	public void updateSingleRoom() throws Exception {
		Date day = new Date(2016, 11, 1);
		assertEquals(true, roomData.updateSingleRoom(day, new RoomPO(false, true, "906", "标准舒适商务双床房", RoomType.TwinBed, 370), "000001"));
		assertEquals(false, roomData.updateSingleRoom(day, new RoomPO(false, true, "903", "标准舒适商务双床房", RoomType.TwinBed, 370), "000001"));
	}

	@Test
	public void deleteSingleRoom() throws Exception {
		assertEquals(true, roomData.deleteSingleRoom("2001", "000001"));
		assertEquals(false, roomData.deleteSingleRoom("2010", "000001"));
	}

	@Test
	public void getRoomsByTypeDate() throws Exception {
		assertEquals(3, roomData.getRoomsByTypeDate(new Date(), RoomType.TwinBed, "000002").size());
	}

	@Test
	public void getRoomsByNameDate() throws Exception {
		assertEquals(3, roomData.getRoomsByNameDate(new Date(), "豪华代码房", "000002").size());
	}

	@Test
	public void getRoomsByDate() throws Exception {
		assertEquals(12, roomData.getRoomsByDate(new Date(), "000002").size());
	}

	@Test
	public void reserveSingleRoom() throws Exception {
		Date day = new Date(2016, 11, 1);
		assertEquals(true, roomData.reserveSingleRoom(day, "3406", "000000"));
		assertEquals(false, roomData.reserveSingleRoom(day, "3406", "000000"));
	}

	@Test
	public void checkIn() throws Exception {
		Date day = new Date(2016, 11, 1);
		assertEquals(true, roomData.checkIn(day, "3406", "000000"));
		assertEquals(false, roomData.checkIn(day, "3406", "000000"));
	}

	@Test
	public void checkOut() throws Exception {
		Date day = new Date(2016, 11, 1);
		assertEquals(true, roomData.checkOut(day, "3406", "000000"));
		assertEquals(false, roomData.checkOut(day, "3406", "000000"));
	}

	@Test
	public void hasSuitableRoom() throws Exception {
		Date day = new Date(2016, 11, 1);
		assertEquals(true, roomData.hasSuitableRoom(500, 2000, "000000"));
	}

}