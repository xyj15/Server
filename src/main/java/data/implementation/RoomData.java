package data.implementation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import data.dataservice.RoomDataService;
import helper.RoomType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import po.RoomPO;

public class RoomData implements RoomDataService {

	private int dataSize = 6;
	private int daysOfMonth = 31;
	private String sourceFile = "RoomData.xls";
	private Workbook book;
	private Sheet sheet;
	private WritableWorkbook wBook;
	private WritableSheet wSheet;
	
	public RoomData() {
		// TODO Auto-generated constructor stub
//		try {
//			try {
//				book = Workbook.getWorkbook(new File(sourceFile));
//			} catch (BiffException e) {
//				e.printStackTrace();
//			}
//			wBook = Workbook.createWorkbook(new File(sourceFile),book);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 *
	 */
	private void write(){
		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *用来初始化wSheet
	 *
	 */
	private void createWritableSheet(){
		try {
			try {
				book=Workbook.getWorkbook(new File(sourceFile));
				wBook = Workbook.createWorkbook(new File(sourceFile),book);
				wSheet = wBook.getSheet(0);
			} catch (BiffException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 用来初始化sheet
	 *
	 */
	private void createSheet(){
		try {
			try {
				book=Workbook.getWorkbook(new File(sourceFile));
				sheet = book.getSheet(0);
			} catch (BiffException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param row
	 * @param roomNUM
	 * @return
	 */
	private RoomPO getRoomByNumber(int row, String roomNUM) {
		// TODO Auto-generated method stub
		int col=0;
		int i = 0;
		int range = sheet.getRow(row).length;
		while(i<range){
			if(sheet.getCell(col+i, row).getContents().equals(roomNUM)){
				return getRoomByCol(col+i, row);
			}
			i+=dataSize;
		}
		return null;
	}

	private RoomPO getRoomByCol(int col, int row){
		String roomNUM = sheet.getCell(col, row).getContents();
		col++;
		String roomName = sheet.getCell(col, row).getContents();
		col++;
		int valid = (int)((NumberCell)sheet.getCell(col,row)).getValue();
		boolean isValid = true;
		if(valid==0){
			isValid=false;
		}
		col++;
		int reserved = (int)((NumberCell) sheet.getCell(col, row)).getValue();
		boolean isReserved = true;
		if(reserved==0){
			isReserved = false;
		}
		col++;
		double price = ((NumberCell) sheet.getCell(col,row)).getValue();
		col++;
		int type = (int)((NumberCell) sheet.getCell(col, row)).getValue();
		switch (type) {
			case 0: return new RoomPO(isReserved, isValid,roomNUM,roomName,price, RoomType.Single);
			case 1: return new RoomPO(isReserved, isValid, roomNUM, roomName, price, RoomType.TwinBed);
			case 2: return new RoomPO(isReserved, isValid, roomNUM, roomName, price, RoomType.BigBed);
			case 3: return new RoomPO(isReserved, isValid, roomNUM, roomName, price, RoomType.Suite);
		}
		return null;
	}

	private boolean updateRoom(int row, RoomPO room) {
		// TODO Auto-generated method stub
		int col=0;
		while(!wSheet.getCell(col, row).getContents().equals(room.getRoomID())){
			if(wSheet.getCell(col, row).getContents().equals("")){
				return false;       //Cannot find the room of the ID
			}
			col+=dataSize;
		}
		Label roomID = new Label(col,row,room.getRoomID());
		col++;
		Label roomName = new Label(col, row, room.getRoomName());
		col++;
		double valid = 0.0;
		if(room.isAvailable()){
			valid = 1.0;
		}
		Number isValid = new Number(col,row,valid);
		col++;
		double reserved = 0.0;
		if(room.isReserved()){
			reserved=1.0;
		}
		Number isReserveed = new Number(col, row, reserved);
		col++;
		Number price = new Number(col,row,room.getPrice());
		col++;
		Number roomType = new Number(col, row, room.getRoomType().getValue());
		try {
			wSheet.addCell(roomID);
			wSheet.addCell(roomName);
			wSheet.addCell(isValid);
			wSheet.addCell(isReserveed);
			wSheet.addCell(price);
			wSheet.addCell(roomType);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	//@Override
	private boolean addRoom(int row, RoomPO room) {
		// TODO Auto-generated method stub
		int col=0;
		while(wSheet.getCell(col, row).getContents()!=""&&(!wSheet.getCell(col, row).getContents().equals("-1"))){
			if(wSheet.getCell(col, row).getContents().equals(room.getRoomID())){
				return false;
			}
			col+=dataSize;
		}
		Label roomID = new Label(col,row,room.getRoomID());
		col++;
		Label roomName = new Label(col, row, room.getRoomName());
		col++;
		double valid = 0.0;
		if(room.isAvailable()){
			valid = 1.0;
		}
		Number isValid = new Number(col,row,valid);
		col++;
		double reserved = 0.0;
		if(room.isReserved()){
			reserved=1.0;
		}
		Number isReserveed = new Number(col, row, reserved);
		col++;
		Number price = new Number(col,row,room.getPrice());
		col++;
		Number roomType = new Number(col, row, room.getRoomType().getValue());
		try {
			wSheet.addCell(roomID);
			wSheet.addCell(roomName);
			wSheet.addCell(isValid);
			wSheet.addCell(isReserveed);
			wSheet.addCell(price);
			wSheet.addCell(roomType);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	//@Override
	private boolean deleteRoom(int row, String roomNUM) {
		// TODO Auto-generated method stub
		int col=0;
		while(!wSheet.getCell(col, row).getContents().equals(roomNUM)){
			if(wSheet.getCell(col, row).getContents().equals("")){
				return false;
			}
			col+=dataSize;
		}
		Label deleteNC = new Label(col, row, "-1");
		try {
			wSheet.addCell(deleteNC);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 *
	 * @param hotelID
	 */
	private void setwSheet(String hotelID){
		int location = Integer.parseInt(hotelID);
		wSheet = wBook.getSheet(location);
	}

	private void setSheet(String hotelID){
		int location = Integer.parseInt(hotelID);
		sheet = book.getSheet(location);
	}

	/**
	 *
	 */
	private void close(){
		write();
		try {
			wBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		book.close();
	}

	public RoomPO getSingleRoom(Date theDay, String roomNUM, String hotelID) {
		createSheet();
		setSheet(hotelID);
		Calendar cal = Calendar.getInstance();
		cal.setTime(theDay);
		int row = cal.get(Calendar.DAY_OF_MONTH);
		RoomPO result = getRoomByNumber(row, roomNUM);
		book.close();
		return result;
	}

	public boolean addSingleRoom(RoomPO room, String hotelID) {
		createWritableSheet();
		setwSheet(hotelID);
		for(int i=1; i<=daysOfMonth; i++){
			if(!addRoom(i, room)){
				close();
				return false;
			}
		}
		close();
		return true;
	}

	public boolean updateSingleRoom(Date theDay, RoomPO room, String hotelID) {
		createWritableSheet();
		setwSheet(hotelID);
		Calendar cal = Calendar.getInstance();
		cal.setTime(theDay);
		int row = cal.get(Calendar.DAY_OF_MONTH);
		boolean result = updateRoom(row, room);
		close();
		return result;
	}

	public boolean deleteSingleRoom(String roomNUM, String hotelID) {
		createWritableSheet();
		setwSheet(hotelID);
		for (int i = 1; i <= daysOfMonth; i++) {
			if(!deleteRoom(i, roomNUM)){
				close();
				return false;
			}
		}
		close();
		return true;
	}

	public ArrayList<RoomPO> getRoomsByTypeDate(Date day, RoomType roomType, String hotelID) {
		ArrayList<RoomPO> temp = getRoomsByDate(day, hotelID);
		ArrayList<RoomPO> result = new ArrayList<>();
		if(temp==null) return null;        //there is no room of the hotel on the day
		for (RoomPO thisRoom: temp) {
			if(thisRoom.getRoomType()==roomType){
				result.add(thisRoom);
			}
		}
		if(result.size()==0) return null;  //there is no room whose type is roomType belongs to hotel on the day.
		return result;
	}

	public ArrayList<RoomPO> getRoomsByNameDate(Date day, String roomName, String hotelID) {
		ArrayList<RoomPO> temp = getRoomsByDate(day, hotelID);
		ArrayList<RoomPO> result = new ArrayList<>();
		if(temp==null) return null;        //there is no room of the hotel on the day
		for (RoomPO thisRoom: temp) {
			if(thisRoom.getRoomName().equals(roomName)){
				result.add(thisRoom);
			}
		}
		if(result.size()==0) return null;  //there is no room whose name is roomName belongs to hotel on the day.
		return result;
	}

	public ArrayList<RoomPO> getRoomsByDate(Date day, String hotelID) {
		createSheet();
		setSheet(hotelID);
		int col = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		int row = cal.get(Calendar.DAY_OF_MONTH);
		ArrayList<RoomPO> result = new ArrayList<RoomPO>();
		for (int i = 0; i < sheet.getRow(row).length; i+=dataSize) {
			if(!sheet.getCell(col+i, row).getContents().equals("-1"))result.add(getRoomByCol(col, row));
		}
		close();
		if(result.size()==0) return null;  //there is no room of the hotel on the day
		return result;
	}

	public boolean reserveSingleRoom(Date day, String roomNUM, String hotelID) {
		createWritableSheet();
		setwSheet(hotelID);
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		int row = cal.get(Calendar.DAY_OF_MONTH);
		int col=0;
		while(!wSheet.getCell(col, row).getContents().equals(roomNUM)){
			if(wSheet.getCell(col, row).getContents().equals("")){
				close();
				return false;      //Cannot find the room of the ID
			}
			col+=dataSize;
		}
		col+=3;
		int reserved = (int)((NumberCell) wSheet.getCell(col, row)).getValue();
		if(reserved==1) {
			close();
			return false;                 //This room on the day has already been reserved.
		}
		Number isReserved = new Number(col, row, 1.0);
		try {
			wSheet.addCell(isReserved);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	public boolean checkIn(Date day, String roomNUM, String hotelID) {
		createWritableSheet();
		setwSheet(hotelID);
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		int row = cal.get(Calendar.DAY_OF_MONTH);
		int col=0;
		while(!wSheet.getCell(col, row).getContents().equals(roomNUM)){
			if(wSheet.getCell(col, row).getContents().equals("")){
				close();
				return false;     //Cannot find the room of the ID
			}
			col+=dataSize;
		}
		col+=2;
		int available = (int)((NumberCell) wSheet.getCell(col, row)).getValue();
		if(available==0){
			close();
			return false;                 //This room on the day has already been check in.
		}
		Number isAvailable = new Number(col, row, 0.0);
		try {
			wSheet.addCell(isAvailable);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	public boolean checkOut(Date day, String roomNUM, String hotelID) {
		createWritableSheet();
		setwSheet(hotelID);
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		int row = cal.get(Calendar.DAY_OF_MONTH);
		int col=0;
		while(!wSheet.getCell(col, row).getContents().equals(roomNUM)){
			if(wSheet.getCell(col, row).getContents().equals("")){
				close();
				return false;     //Cannot find the room of the ID
			}
			col+=dataSize;
		}
		col+=2;
		int available = (int)((NumberCell) wSheet.getCell(col, row)).getValue();
		if(available==1){
			close();
			return false;                 //This room on the day has already been check out.
		}
		Number isAvailable = new Number(col, row, 1.0);
		try {
			wSheet.addCell(isAvailable);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	/**
	 *
	 * @param low
	 * @param high
	 * @param hotelID
	 * @return
	 */
	public boolean hasSuitableRoom(double low, double high, String hotelID){
		createSheet();
		setSheet(hotelID);
		int col = 0;
		int row = 1;
		ArrayList<RoomPO> result = new ArrayList<RoomPO>();
		for (int i = 0; i < sheet.getRow(row).length; i++) {
			result.add(getRoomByCol(col+i, row));
		}
		close();
		for (RoomPO temp: result) {
			if(temp.getPrice()>=low&&temp.getPrice()<=high) return true;
		}
		return false;
	}
}
