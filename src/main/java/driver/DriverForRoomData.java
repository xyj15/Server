package driver;

import data.implementation.RoomData;
import other.RoomType;
import po.RoomPO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apple on 2016/12/9.
 */
public class DriverForRoomData {

	private RoomData test = new RoomData();

	public static void main(String[] args){
		DriverForRoomData driver = new DriverForRoomData();
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "314", "豪华代码房",  RoomType.BigBed, 650), "000002"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "313", "豪华代码房", RoomType.BigBed, 650), "000002"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "312", "豪华代码房", RoomType.BigBed, 650), "000002"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "503", "简约debug房", RoomType.Single, 480), "000002"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "504", "简约debug房", RoomType.Single, 480), "000002"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "505", "简约debug房", RoomType.Single, 480), "000002"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "1024", "奢华海景测试房", RoomType.Suite, 1024), "000002"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "1023", "奢华海景测试房", RoomType.Suite, 1024), "000002"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "1025", "奢华海景测试房", RoomType.Suite, 1024), "000002"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "609", "标准结对编程房", RoomType.TwinBed, 760), "000002"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "608", "标准结对编程房", RoomType.TwinBed, 760), "000002"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "610", "标准结对编程房", RoomType.TwinBed, 760), "000002"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "3406", "豪华湖景房", RoomType.BigBed, 1500), "000000"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "902", "商务单人间", RoomType.Single, 830), "000000"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "817", "高档温馨家庭套房", RoomType.Suite, 2050), "000000"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "2107", "景观园林双床房", RoomType.TwinBed, 998), "000000"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "1308", "浪漫沙滩海景大床房", RoomType.BigBed, 350), "000001"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "1516", "舒适阳光单人房", RoomType.Single, 210), "000001"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "2001", "豪华泰式温泉套房", RoomType.Suite, 520), "000001"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "906", "标准双床房", RoomType.TwinBed, 370), "000001"));
//		Date day = new Date(2016, 11, 1);
//		System.out.println(driver.testUpdateSingleRoom(day, new RoomPO(false, true, "903", "标准舒适商务双床房", 370, RoomType.TwinBed), "000001"));
//		System.out.println(driver.testDeleteSingleRoom("2010", "000001"));
//		System.out.println(driver.testUpdateSingleRoom(day, new RoomPO(false, true, "906", "标准舒适商务双床房", 370, RoomType.TwinBed), "000001"));
//		System.out.println(driver.testDeleteSingleRoom("2001", "000001"));
//		System.out.println(driver.testGetSingleRoom(day, "314", "000002"));
//		System.out.println(driver.testGetSingleRoom(day, "304", "000002"));
//		System.out.println(driver.testReserve(day, "3406", "000000"));
//		System.out.println(driver.testReserve(day, "3406", "000000"));
//		System.out.println(driver.testCheckIn(day, "3406", "000000"));
//		System.out.println(driver.testCheckIn(day, "3406", "000000"));
//		System.out.println(driver.testCheckOut(day, "3406", "000000"));
//		System.out.println(driver.testCheckOut(day, "3406", "000000"));
//		System.out.println(driver.testGetRoomByDate(day, "000002"));
//		System.out.println(driver.testGetRoomByNameDate(day, "豪华代码房", "000002"));
//		System.out.println(driver.testGetRoomByTypeDate(day, RoomType.TwinBed, "000002"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "3405", "豪华湖景房", RoomType.BigBed, 1500), "000000"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "903", "商务单人间", RoomType.Single, 830), "000000"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "816", "高档温馨家庭套房", RoomType.Suite, 2050), "000000"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "2105", "景观园林双床房", RoomType.TwinBed, 998), "000000"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "1309", "浪漫沙滩海景大床房", RoomType.BigBed, 350), "000001"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "1515", "舒适阳光单人房", RoomType.Single, 210), "000001"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "2002", "豪华泰式温泉套房", RoomType.Suite, 520), "000001"));
//		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "908", "标准双床房", RoomType.TwinBed, 370), "000001"));
		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "3406", "浪漫无bug观海房", RoomType.BigBed, 850), "000003"));
		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "902", "只有你与Cpp房", RoomType.Single, 660), "000003"));
		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "817", "带助教套房", RoomType.Suite, 2050), "000003"));
		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "2107", "编译器双床房", RoomType.TwinBed, 1000), "000003"));
		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "1308", "浪漫无bug观海房", RoomType.BigBed, 850), "000003"));
		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "1516", "只有你与Cpp房房", RoomType.Single, 660), "000003"));
		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "2001", "带助教套房", RoomType.Suite, 2050), "000003"));
		System.out.println(driver.testAddSingleRoom(new RoomPO(false, true, "906", "编译器双床房", RoomType.TwinBed, 1000), "000003"));
	}

	/**
	 *
	 * @param room
	 * @param hotelID
	 * @return
	 */
	public boolean testAddSingleRoom(RoomPO room, String hotelID){
		System.out.println("Add a single room whose ID is "+room.getRoomNumber());
		return test.addSingleRoom(room, hotelID);
	}

	/**
	 *
	 * @param day
	 * @param roomID
	 * @param hotelID
	 * @return
	 */
	public boolean testGetSingleRoom(Date day, String roomID, String hotelID){
		System.out.println("Look up for a single room whose ID is "+roomID);
		RoomPO result = test.getSingleRoom(day, roomID,hotelID);
		if(result==null) return false;
		output(result);
		return true;
	}

	/**
	 *
	 * @param roomID
	 * @param hotelID
	 * @return
	 */
	public boolean testDeleteSingleRoom(String roomID, String hotelID){
		System.out.println("Delete a single room whose ID is "+roomID);
		return test.deleteSingleRoom(roomID, hotelID);
	}

	/**
	 *
	 * @param day
	 * @param room
	 * @param hotelID
	 * @return
	 */
	public boolean testUpdateSingleRoom(Date day, RoomPO room, String hotelID){
		System.out.println("Update a single room whose ID is "+room.getRoomNumber());
		return test.updateSingleRoom(day, room, hotelID);
	}

	/**
	 *
	 * @param day
	 * @param hotelID
	 * @return
	 */
	public boolean testGetRoomByDate(Date day, String hotelID){
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("MM-dd-yyyy");
		System.out.println("Look up for rooms of hotel "+hotelID+" on "+bartDateFormat.format(day));
		ArrayList<RoomPO> result = test.getRoomsByDate(day, hotelID);
		if(result==null) return false;
		for (RoomPO thisRoom: result
		     ) {
			output(thisRoom);
		}
		return true;
	}

	/**
	 *
	 * @param day
	 * @param roomName
	 * @param hotelID
	 * @return
	 */
	public boolean testGetRoomByNameDate(Date day, String roomName, String hotelID){
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("MM-dd-yyyy");
		System.out.println("Look up for rooms of hotel "+hotelID+" whose name is "+roomName+" on "+bartDateFormat.format(day));
		ArrayList<RoomPO> result = test.getRoomsByNameDate(day, roomName, hotelID);
		if(result==null) return false;
		for (RoomPO thisRoom: result
				) {
			output(thisRoom);
		}
		return true;
	}

	/**
	 *
	 * @param day
	 * @param roomType
	 * @param hotelID
	 * @return
	 */
	public boolean testGetRoomByTypeDate(Date day, RoomType roomType, String hotelID){
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("MM-dd-yyyy");
		System.out.println("Look up for rooms of hotel "+hotelID+" whose type is "+roomType+" on "+bartDateFormat.format(day));
		ArrayList<RoomPO> result = test.getRoomsByTypeDate(day,roomType, hotelID);
		if(result==null) return false;
		for (RoomPO thisRoom: result
				) {
			output(thisRoom);
		}
		return true;
	}

	/**
	 *
	 * @param day
	 * @param roomNUM
	 * @param hotelID
	 * @return
	 */
	public boolean testReserve(Date day, String roomNUM, String hotelID){
		SimpleDateFormat bartDateFormate = new SimpleDateFormat("MM-dd-yyyy");
		System.out.println("Reserve "+roomNUM+" room on "+bartDateFormate.format(day)+" of hotel "+hotelID);
		return test.reserveSingleRoom(day, roomNUM, hotelID);
	}

	/**
	 *
	 * @param day
	 * @param roomNUM
	 * @param hotelID
	 * @return
	 */
	public boolean testCheckIn(Date day, String roomNUM, String hotelID){
		SimpleDateFormat bartDateFormate = new SimpleDateFormat("MM-dd-yyyy");
		System.out.println("Check in "+roomNUM+" room on "+bartDateFormate.format(day)+" of hotel "+hotelID);
		return test.checkIn(day, roomNUM, hotelID);
	}

	/**
	 *
	 * @param day
	 * @param roomNUM
	 * @param hotelID
	 * @return
	 */
	public boolean testCheckOut(Date day, String roomNUM, String hotelID){
		SimpleDateFormat bartDateFormate = new SimpleDateFormat("MM-dd-yyyy");
		System.out.println("Check out "+roomNUM+" room on "+bartDateFormate.format(day)+" of hotel "+hotelID);
		return test.checkOut(day, roomNUM, hotelID);
	}

	/**
	 *
	 * @param room
	 */
	private void output(RoomPO room){
		System.out.println("roomType: "+room.getRoomType());
		System.out.println("roomName: "+room.getRoomName());
		System.out.println("roomID: "+room.getRoomNumber());
		System.out.println("price: "+room.getPrice());
		System.out.println("isRserved: "+room.isReserved());
		System.out.println("isAvailable: "+room.isAvailable());
	}
}
