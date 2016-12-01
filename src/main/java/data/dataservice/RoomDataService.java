package data.dataservice;

import po.RoomPO;

public interface RoomDataService {
	public RoomPO getRoom(String roomNUM) ;
	public boolean addRoom(RoomPO room);
	public boolean updateRoom(RoomPO room) ;
	public boolean deleteRoom(String roomNUM);
}
