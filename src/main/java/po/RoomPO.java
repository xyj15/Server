package po;

import helper.RoomType;

import java.io.Serializable;

/**
 * 存储Room信息的PO类
 * @author CROFF
 * @version 2016-12-1
 */
public class RoomPO implements Serializable {

	private boolean reserved;   //客房是否被预定
	private boolean available;	//客房是否可使用
	private String roomNumber;	//客房号码
	private String roomName;	//客房名称
	private RoomType roomType;	//客房类型
	private double price;	//客房单价
	private String hotelID;	//所属酒店ID
	
	/**
	 * 空构造方法
	 */
	public RoomPO() {
		
	}
	
	/**
	 * 带参数的构造方法
	 * @param reserved  客房是否被预定
	 * @param available 客房是否可使用
	 * @param roomNumber 客房号码
	 * @param roomName 客房名称
	 * @param roomType 客房类型
	 * @param price 客房单价
	 * @param hotelID 所属酒店ID
	 */
	public RoomPO(boolean reserved, boolean available, String roomNumber, String roomName,
	              RoomType roomType, double price, String hotelID) {
		this.reserved = reserved;
		this.available = available;
		this.roomNumber = roomNumber;
		this.roomName = roomName;
		this.roomType = roomType;
		this.price = price;
		this.hotelID = hotelID;
	}

	/**
	 *
	 * @param reserved
	 * @param available
	 * @param roomNumber
	 * @param roomName
	 * @param roomType
	 * @param price
	 */
	public RoomPO(boolean reserved, boolean available, String roomNumber, String roomName,
	              RoomType roomType, double price) {
		this.reserved = reserved;
		this.available = available;
		this.roomNumber = roomNumber;
		this.roomName = roomName;
		this.roomType = roomType;
		this.price = price;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public String getRoomNumber() {
		return roomNumber;
	}
	
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public RoomType getRoomType() {
		return roomType;
	}
	
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getHotelID() {
		return hotelID;
	}
	
	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
}
