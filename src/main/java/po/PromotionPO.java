package po;

import java.io.Serializable;

public class PromotionPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String birthday;
	int roomNumber;
	boolean coordinate;
	String date;
	boolean VIP;
	String district;
	
	public PromotionPO(String b, int rn, boolean c, String da, boolean v, String di) {
		birthday=b;
		roomNumber=rn;
		coordinate=c;
		date=da;
		VIP=v;
		district=di;
	}
	
	public String getBirday(){
		return birthday;
	}
	
	public int getRoomNumber(){
		return roomNumber;
	}
	
	public boolean getCoordinate(){
		return coordinate;
	}
	
	public String getDate(){
		return date;
	}
	
	public boolean getVIP(){
		return VIP;
	}
	
	public String getDistrict(){
		return district;
	}
	
	public void setBirthday(String birthday){
		this.birthday=birthday;
	}
	public void setRoomNumber(int roomNumber){
		this.roomNumber=roomNumber;
	}
	public void setCoodinate(boolean coordinate){
		this.coordinate=coordinate;
	}
	public void setDate(String date){
		this.date=date;
	}
	public void setVIP(boolean VIP){
		this.VIP=VIP;
	}
	public void setDistrict(String district){
		this.district=district;
	}
	
}