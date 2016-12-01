package po;

public class RoomPO{

	/**
	 * 
	 */
	private boolean available;	//客房是否可使用
	private String roomID;	//客房号
	private String roomName;	//客房名称
	private double price;	//客房单价
	
	public RoomPO(boolean available, String roomID, String roomName, double price){
		this.available = available;
		this.roomID = roomID;
		this.roomName = roomName;
		this.price =price;
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

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public void setRoomType(String roomName) {
		this.roomName = roomName;
	}
}
