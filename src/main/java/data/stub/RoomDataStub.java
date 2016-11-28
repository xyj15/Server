package data.stub;

import po.RoomType;
import data.dao.RoomDataDao;
import po.RoomPO;

public class RoomDataStub implements RoomDataDao {

	private boolean valid;
	private int number;
	private RoomType type;
	private int price;
	
	public RoomDataStub(){
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public RoomPO getRoom(String ID) {
		// TODO Auto-generated method stub
		return new RoomPO(valid, number, type, price);
	}

	@Override
	public boolean updateRoom(RoomPO room) {
		// TODO Auto-generated method stub
		return true;
	}

}
