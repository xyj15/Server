package data.service;

import other.RoomType;
import po.RoomPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public interface RoomDataService extends Remote {
	
	public RoomPO getSingleRoom(Date theDay, String roomNUM, String hotelID) throws RemoteException;  //得到具体某天某间房的信息
	public boolean addSingleRoom(RoomPO room, String hotelID) throws RemoteException;  //添加具体房间
	public boolean updateSingleRoom(Date theDay, RoomPO room, String hotelID) throws RemoteException;  //更新具体房间信息
	public boolean deleteSingleRoom(String roomNUM, String hotelID) throws RemoteException;  //删除具体房间
	public ArrayList<RoomPO> getRoomsByTypeDate(Date day, RoomType roomType, String hotelID) throws RemoteException;  //通过日期房型查询某酒店可用房间信息
	public ArrayList<RoomPO> getRoomsByNameDate(Date day, String roomName, String hotelID) throws RemoteException;  //通过日期房间名称查询某酒店可用房间信息
	public ArrayList<RoomPO> getRoomsByDate(Date day, String hotelID) throws RemoteException;  //通过日期查询某酒店所有可用房间信息
	public boolean reserveSingleRoom(Date day, String roomNUM, String hotelID) throws RemoteException;  //通过日期房间号预定某酒店房间
	public boolean checkIn(Date day, String roomNUM, String hotelID) throws RemoteException;  //具体房间的入住执行
	public boolean checkOut(Date day, String roomNUM, String hotelID) throws RemoteException;  //具体房间的退房执行
}
