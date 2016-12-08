package po;

import helper.RoomType;

public class RoomPO {

	/**
	 * 
	 */
	private boolean reserved;   //客房是否被预定
	private boolean available;	//客房是否可使用
	private String roomID;	//客房号
	private String roomName;	//客房名称
	private double price;	//客房单价
	private RoomType roomType;   //客房类型
	
	public RoomPO(boolean reserved, boolean available, String roomID, String roomName, double price, RoomType roomType){
		this.reserved = reserved;
		this.available = available;
		this.roomID = roomID;
		this.roomName = roomName;
		this.price =price;
		this.roomType = roomType;
	}

	public String getRoomID() {
		return roomID;
	}

	public String getRoomName() {
		return roomName;
	}

	public double getPrice(){
		return price;
	}

	public boolean isAvailable() {
		return available;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
}
