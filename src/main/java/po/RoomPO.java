package po;

import java.io.Serializable;

public class RoomPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean valid;
	private String number;
	private int type;
	private double price;
	
	public RoomPO(boolean valid, String number, int type, double price){
		this.valid =valid;
		this.number=number;
		this.type=type;
		this.price =price;
	}
	
	public boolean getValid(){
		return valid;
	}
	
	public String getNumber(){
		return number;
	}
	
	public int getRoomType(){
		return type;
	}
	
	public double getPrice(){
		return price;
	}
	
	

	
}
