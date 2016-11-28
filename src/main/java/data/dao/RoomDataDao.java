package data.dao;

import po.RoomPO;

public interface RoomDataDao {
	public RoomPO getRoom(String roomNUM) ;
	public boolean addRoom(RoomPO room);
	public boolean updateRoom(RoomPO room) ;
	public boolean deleteRoom(RoomPO room);
}
