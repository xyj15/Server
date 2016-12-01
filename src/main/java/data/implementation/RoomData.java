package data.implementation;

import java.io.File;
import java.io.IOException;

import data.dataservice.RoomDataService;
import jxl.NumberCell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import po.RoomPO;

public class RoomData implements RoomDataService {

	private int dataSize = 4;
	private String sourceFile = "RoomData.xls";
	private Workbook book;
	private WritableWorkbook wBook;
	private WritableSheet wSheet;
	
	public RoomData() {
		// TODO Auto-generated constructor stub
		try {
			try {
				book = Workbook.getWorkbook(new File(sourceFile));
			} catch (BiffException e) {
				e.printStackTrace();
			}
			wBook = Workbook.createWorkbook(new File(sourceFile),book);
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
		while(!wSheet.getCell(col, row).getContents().equals(roomNUM)){
			if(wSheet.getCell(col, row).getContents().equals("")){
				return null;
			}
			col+=dataSize;
		}
		col++;
		String roomName = wSheet.getCell(col, row).getContents();
		col++;
		NumberCell validNC = (NumberCell)wSheet.getCell(col,row);
		int valid = (int)validNC.getValue();
		boolean isValid = true;
		if(valid==0){
			isValid=false;
		}
		col++;
		NumberCell priceNC = (NumberCell)wSheet.getCell(col,row);
		double price = priceNC.getValue();
		return new RoomPO(isValid,roomNUM,roomName,price);

	}

	//@Override
	public boolean updateRoom(RoomPO room) {
		// TODO Auto-generated method stub
		int col=0;
		int row=hash(room.getRoomID());
		while(!wSheet.getCell(col, row).getContents().equals(room.getRoomID())){
			if(wSheet.getCell(col, row).getContents().equals("")){
				return false;
			}
			col+=dataSize;
		}
		Label roomNUM = new Label(col,row,room.getRoomID());
		col++;
		Label roomName = new Label(col, row, room.getRoomName());
		col++;
		double valid = 0.0;
		if(room.isAvailable()){
			valid = 1;
		}
		Number isValid = new Number(col,row,valid);
		col++;
		Number price = new Number(col,row,room.getPrice());
		try {
			wSheet.addCell(roomNUM);
			wSheet.addCell(roomName);
			wSheet.addCell(isValid);
			wSheet.addCell(price);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	//@Override
	public boolean addRoom(RoomPO room) {
		// TODO Auto-generated method stub
		int col=0;
		int row=hash(room.getRoomID());
		while(wSheet.getCell(col, row).getContents()!=""){
			if(wSheet.getCell(col, row).getContents().equals(room.getRoomID())){
				return true;
			}
			col+=dataSize;
		}
		Label roomNUM = new Label(col,row,room.getRoomID());
		col++;
		Label roomName = new Label(col, row, room.getRoomName());
		col++;
		double valid = 0.0;
		if(room.isAvailable()){
			valid = 1;
		}
		Number isValid = new Number(col,row,valid);
		col++;
		Number price = new Number(col,row,room.getPrice());
		try {
			wSheet.addCell(roomNUM);
			wSheet.addCell(roomName);
			wSheet.addCell(isValid);
			wSheet.addCell(price);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	//@Override
	public boolean deleteRoom(String roomNUM) {
		// TODO Auto-generated method stub
		int col=0;
		int row=hash(roomNUM);
		while(!wSheet.getCell(col, row).getContents().equals(roomNUM)){
			if(wSheet.getCell(col, row).getContents().equals("")){
				return false;
			}
			col+=dataSize;
		}
		for(int i=0;i<dataSize;i++){
			Label label = new Label(col+i,row,"");
			try {
				wSheet.addCell(label);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	private int hash(String roomNUM){
		int hashResult = roomNUM.hashCode();
		hashResult %= 100;
		return hashResult;
	}
}
