package data.implementation;

import java.io.File;
import java.io.IOException;

import jxl.NumberCell;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import po.RoomPO;

public class RoomData implements data.dao.RoomDataDao {

	int dataSize = 4;
	String sourceFile = "RoomData.xls";
	WritableWorkbook wBook;
	WritableSheet wSheet;
	
	public RoomData() {
		// TODO Auto-generated constructor stub
		try {
			wBook = Workbook.createWorkbook(new File(sourceFile));
			wSheet = wBook.getSheet(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Override
	public RoomPO getRoom(String roomNUM) {
		// TODO Auto-generated method stub
		int col=0;
		int row=hash(roomNUM);
		while(!wSheet.getCell(row, col).getContents().equals(roomNUM)){
			if(wSheet.getCell(row, col).getContents().equals("")){
				return null;
			}
			col+=dataSize;
		}
		col++;
		NumberCell typeNC = (NumberCell)wSheet.getCell(row, col); 
		int type = (int)typeNC.getValue();
		col++;
		NumberCell validNC = (NumberCell)wSheet.getCell(row,col);
		int valid = (int)validNC.getValue();
		boolean isValid = true;
		if(valid==0){
			isValid=false;
		}
		col++;
		NumberCell priceNC = (NumberCell)wSheet.getCell(row,col);
		double price = priceNC.getValue();
		return new RoomPO(isValid, roomNUM, type, price);
	}

	//@Override
	public boolean updateRoom(RoomPO room) {
		// TODO Auto-generated method stub
		int col=0;
		int row=hash(room.getNumber());
		while(!wSheet.getCell(row, col).getContents().equals(room.getNumber())){
			if(wSheet.getCell(row, col).getContents().equals("")){
				return false;
			}
			col+=dataSize;
		}
		Label roomNUM = new Label(row,col,room.getNumber());
		col++;
		Number type = new Number(row,col,room.getRoomType());
		col++;
		double valid = 0.0;
		if(room.getValid()){
			valid = 1;
		}
		Number isValid = new Number(row,col,valid);
		col++;
		Number price = new Number(row,col,room.getPrice());
		try {
			wSheet.addCell(roomNUM);
			wSheet.addCell(type);
			wSheet.addCell(isValid);
			wSheet.addCell(price);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	//@Override
	public boolean addRoom(RoomPO room) {
		// TODO Auto-generated method stub
		int col=0;
		int row=hash(room.getNumber());
		while(wSheet.getCell(row, col).getContents()!=""){
			col+=dataSize;
		}
		Label roomNUM = new Label(row,col,room.getNumber());
		col++;
		Number type = new Number(row,col,room.getRoomType());
		col++;
		double valid = 0.0;
		if(room.getValid()){
			valid = 1;
		}
		Number isValid = new Number(row,col,valid);
		col++;
		Number price = new Number(row,col,room.getPrice());
		try {
			wSheet.addCell(roomNUM);
			wSheet.addCell(type);
			wSheet.addCell(isValid);
			wSheet.addCell(price);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	//@Override
	public boolean deleteRoom(RoomPO room) {
		// TODO Auto-generated method stub
		int col=0;
		int row=hash(room.getNumber());
		while(!wSheet.getCell(row, col).getContents().equals(room.getNumber())){
			if(wSheet.getCell(row, col).getContents().equals("")){
				return false;
			}
			col+=dataSize;
		}
		for(int i=0;i<dataSize;i++){
			Label label = new Label(row,col+i,"");
			try {
				wSheet.addCell(label);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	private int hash(String roomNUM){
		int hashResult = roomNUM.hashCode();
		hashResult %= 100;
		return hashResult;
	}
}
