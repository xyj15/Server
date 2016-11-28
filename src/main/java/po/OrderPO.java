package po;

import java.io.Serializable;

public class OrderPO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String checkInTime;
	String checkOutTime;
	String roomType;
	int roomNumber;
	String promotion;
	double discount;
	double price;
	
	public OrderPO(String ci, String co, String rt, int rn, String pro, double d, double pri) {
		checkInTime=ci;
		checkOutTime=co;
		roomType=rt;
		roomNumber=rn;
		promotion=pro;
		discount=d;
		price=pri;
	}
	
	public String getCheckInTime(){
		return checkInTime;
	}
	
	public String getCheckOutTime(){
		return checkOutTime;
	}
	
	public String getRoomType(){
		return roomType;
	}
	
	public int getRoomNumber(){
		return roomNumber;
	}
	
	public String getPromotion(){
		return promotion;
	}
	
	public double getDiscount(){
		return discount;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setCheckInTime(String checkInTime){
		this.checkInTime=checkInTime;
	}
	
	public void setCheckOutTime(String checkOutTime){
		this.checkOutTime=checkOutTime;
	}
	
	public void setRoomType(String roomType){
		this.roomType=roomType;
	}
	
	public void setRoomNumber(int roomNumber){
		this.roomNumber=roomNumber;
	}
	
	public void setPromotion(String promotion){
		this.promotion=promotion;
	}
	
	public void setDiscount(double discount){
		this.discount=discount;
	}
	
	public void setPrice(double price){
		this.price=price;
	}
	
}
