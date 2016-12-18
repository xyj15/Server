package data.service;

import po.HotelPO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by zhangxinyue on 2016/12/1.
 */
public interface HotelDataService extends Remote {
	
	public boolean addHotel(HotelPO hotel) throws RemoteException;
	public boolean deleteHotel(String hotelID) throws RemoteException;
	public boolean updateHotel(HotelPO hotel) throws RemoteException;
	public HotelPO getHotelByID(String hotelID) throws RemoteException;
	public String getAvailableHotelID() throws RemoteException;   //得到可用的新增ID
}
