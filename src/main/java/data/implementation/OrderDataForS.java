package data.implementation;

import data.dataservice.OrderDataService;
import helper.RoomType;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import po.OrderPO;
import helper.OrderStatus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by apple on 2016/12/1.
 */
public class OrderDataForS implements OrderDataService {

	private int dataSize = 21;
	private String sourceFile = "OrderForMember.xls";
	private Workbook book;
	private Sheet sheet;
	private WritableWorkbook wBook;
	private WritableSheet wSheet;
	private OrderDataForH sync;

	public OrderDataForS(){
		sync = new OrderDataForH();
//		try {
//			book = Workbook.getWorkbook(new File(sourceFile));
//			wBook = Workbook.createWorkbook(new File(sourceFile),book);
//			wSheet = wBook.getSheet(0);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (BiffException e) {
//			e.printStackTrace();
//		}
	}

	public boolean addOrder(OrderPO order) {
		return false;
	}

	/**
	 *
	 * @param order
	 * @return
	 */
	public boolean updateOrder(OrderPO order) {
		createWritablSheet();
		Cell orderStart = wSheet.findCell(order.getOrderID());
		int col = orderStart.getColumn()+1;
		int row = orderStart.getRow();
		Label memberID = new Label(col,row,order.getMemberID());
		col++;
		Label hotelID = new Label(col, row, order.getHotelID());
		col++;
		Label evaluation = new Label(col, row, order.getEvaluation());
		col++;
		Label promotion = new Label(col, row, order.getPromotionID());
		col++;
		Label roomName = new Label(col, row, order.getRoomName());
		col++;
		DateTime checkIn = new DateTime(col, row, order.getCheckinTime());
		col++;
		DateTime checkOut = new DateTime(col, row, order.getCheckoutTime());
		col++;
		DateTime latestCheckIn = new DateTime(col, row, order.getLatestCheckinTime());
		col++;
		DateTime creatTime = new DateTime(col, row, order.getCreateTime());
		col++;
		DateTime actualCheckIn, actualCheckOut, cancelTime;
		if(order.getOrderStatus()==OrderStatus.Executed){
			actualCheckIn = new DateTime(col, row, order.getActualCheckinTime());
			col++;
			actualCheckOut = new DateTime(col, row, order.getActualCheckoutTime());
			col++;
		}
		else{
			actualCheckIn = new DateTime(col, row, new Date());
			col++;
			actualCheckOut = new DateTime(col, row, new Date());
			col++;
		}
		if(order.getOrderStatus()==OrderStatus.Canceled){
			cancelTime = new DateTime(col, row, order.getCancelTime());
		}
		else{
			cancelTime = new DateTime(col, row, new Date());
		}
		col++;
		Number roomNUM = new Number(col, row, order.getNumberOfRoom());
		col++;
		Number numOfClient = new Number(col, row, order.getNumberOfClient());
		col++;
		Number price = new Number(col, row, order.getPrice());
		col++;
		Number score = new Number(col, row, order.getScore());
		col++;
		Number recover = new Number(col, row, order.getRecover());
		col++;
		double kid = 0.0;
		if(order.getHaveKids()){
			kid = 1;
		}
		Number hasKid = new Number(col, row, kid);
		col++;
		Number roomType = new Number(col, row, order.getRoomType().getValue());
		col++;
		Number orderStatus = new Number(col, row, order.getOrderStatus().getV());

		try {
			wSheet.addCell(memberID);
			wSheet.addCell(hotelID);
			wSheet.addCell(promotion);
			wSheet.addCell(roomName);
			wSheet.addCell(evaluation);
			wSheet.addCell(checkIn);
			wSheet.addCell(checkOut);
			wSheet.addCell(latestCheckIn);
			wSheet.addCell(creatTime);
			wSheet.addCell(actualCheckIn);
			wSheet.addCell(actualCheckOut);
			wSheet.addCell(cancelTime);
			wSheet.addCell(numOfClient);
			wSheet.addCell(roomNUM);
			wSheet.addCell(price);
			wSheet.addCell(score);
			wSheet.addCell(recover);
			wSheet.addCell(hasKid);
			wSheet.addCell(roomType);
			wSheet.addCell(orderStatus);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		sync.updateOrder(order);
		return true;
	}

	public boolean cancelOrder(String orderID) {
		return false;
	}

	public boolean makeOrderAbnormal(String orderID) {
		return false;
	}

	/**
	 *
	 * @param orderID
	 * @param recover
	 * @return
	 */
	public boolean recoverOrder(String orderID, double recover) {
		createWritablSheet();
		Cell orderStart = wSheet.findCell(orderID);
		int col = orderStart.getColumn();
		int row = orderStart.getRow();
		Number orderStatus = new Number(col+dataSize-1, row, OrderStatus.Canceled.getV());
		Number recoverLocation = new Number(col+dataSize-4, row, recover);

		try {
			wSheet.addCell(orderStatus);
			wSheet.addCell(recoverLocation);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		sync.recoverOrder(orderID,recover);
		return true;
	}

	public OrderPO getOrder(String orderID) {
		return null;
	}

	/**
	 *
	 * @param userID
	 * @return
	 */
	public ArrayList<OrderPO> getOrderList(String userID) {
		createSheet();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int day = now.get(Calendar.DAY_OF_MONTH);
		ArrayList<OrderPO> result = new ArrayList<OrderPO>();
		int rows = sheet.getRows();
		for (int i = 0; i < rows; i++) {
			ArrayList<OrderPO> temp = getOrderList(i);
			if(temp!=null){
				for (OrderPO order:temp) {
					Date createTime = order.getCreateTime();
					Calendar orderTime = Calendar.getInstance();
					orderTime.setTime(createTime);
					boolean yearEqual = (orderTime.get(Calendar.YEAR)==year);
					boolean monthEqual = (orderTime.get(Calendar.MONTH)==month);
					boolean dayEqual = (orderTime.get(Calendar.DAY_OF_MONTH)==day);
					if(yearEqual&&monthEqual&&dayEqual){
						result.add(order);
					}
				}
			}
		}
		book.close();
		if(result.size()==0) return null;   //does not have any abnormal order.
		return result;
	}

	public ArrayList<OrderPO> getFinishedOrders(String userID) {
		return null;
	}

	public ArrayList<OrderPO> getUnfinishedOrders(String userID) {
		return null;
	}

	public ArrayList<OrderPO> getAbnormalOrders(String userID) {
		return null;
	}

	public ArrayList<OrderPO> getCancledOrders(String userID) {
		return null;
	}

	/**
	 *
	 */
	private void close() {
		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			wBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		book.close();
	}

	/**
	 *
	 * @param row
	 * @return
	 */
	private ArrayList<OrderPO> getOrderList(int row){
		ArrayList<OrderPO> result = new ArrayList<OrderPO>();
		int col = 0;
		while(sheet.getCell(col, row).getContents()!=""){
			result.add(getOrder(col, row));
			col+=dataSize;
		}
		if(result.size()==0) return null;   //This hotel does not have any order.
		return result;
	}

	/**
	 *
	 * @param col
	 * @param row
	 * @return
	 */
	private OrderPO getOrder(int col, int row){
		int status = (int)((NumberCell) wSheet.getCell(col+dataSize-1, row)).getValue();
		OrderStatus orderStatus = null;
		switch (status){
			case 0: orderStatus = OrderStatus.Executed; break;
			case 1: orderStatus = OrderStatus.Unexecuted; break;
			case 2: orderStatus = OrderStatus.Abnormal; break;
			case 3: orderStatus = OrderStatus.Canceled; break;
		}
		String orderID = sheet.getCell(col, row).getContents();
		col++;
		String memberID = sheet.getCell(col, row).getContents();
		col++;
		String hotelID = sheet.getCell(col, row).getContents();
		col++;
		String evaluation = sheet.getCell(col, row).getContents();
		col++;
		String promotion = sheet.getCell(col, row).getContents();
		col++;
		String roomName = wSheet.getCell(col, row).getContents();
		col++;
		Date checkIn = ((DateCell) sheet.getCell(col, row)).getDate();
		col++;
		Date checkOut = ((DateCell) sheet.getCell(col, row)).getDate();
		col++;
		Date latestCheckIn = ((DateCell) sheet.getCell(col, row)).getDate();
		col++;
		Date creatTime = ((DateCell) sheet.getCell(col, row)).getDate();
		col++;
		Date actualCheckIn = null;
		Date actualCheckOut = null;
		Date cancelTime = null;
		if(orderStatus==OrderStatus.Executed){
			actualCheckIn = ((DateCell) sheet.getCell(col, row)).getDate();
			col++;
			actualCheckOut = ((DateCell) sheet.getCell(col, row)).getDate();
			col++;
		}
		else{
			col+=2;
		}
		if(orderStatus==OrderStatus.Canceled){
			cancelTime = ((DateCell) sheet.getCell(col, row)).getDate();
		}
		col++;
		int roomNUM = (int)((NumberCell) sheet.getCell(col, row)).getValue();
		col++;
		int numOfClient = (int)((NumberCell) sheet.getCell(col, row)).getValue();
		col++;
		double price = ((NumberCell) sheet.getCell(col, row)).getValue();
		col++;
		double score = ((NumberCell) sheet.getCell(col, row)).getValue();
		col++;
		double recover = ((NumberCell) sheet.getCell(col, row)).getValue();
		col++;
		boolean hasKid = true;
		int kid = (int)((NumberCell) sheet.getCell(col, row)).getValue();
		if(kid==0){
			hasKid=false;
		}
		col++;
		int type = (int)((NumberCell) wSheet.getCell(col, row)).getValue();
		RoomType roomType = null;
		switch (type){
			case 0: roomType = RoomType.Single; break;
			case 1: roomType = RoomType.TwinBed; break;
			case 2: roomType = RoomType.BigBed; break;
			case 3: roomType = RoomType.Suite; break;
		}

		return new OrderPO(memberID,hotelID,orderID,orderStatus,creatTime,checkIn,actualCheckIn,latestCheckIn,checkOut,actualCheckOut,
				roomNUM,roomName,numOfClient,hasKid,score,evaluation,recover,promotion,price,cancelTime, roomType);
	}

	/**
	 *
	 */
	private void createWritablSheet(){
		try {
			book = Workbook.getWorkbook(new File(sourceFile));
			wBook = Workbook.createWorkbook(new File(sourceFile),book);
			wSheet = wBook.getSheet(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 */
	private void createSheet(){
		try {
			book = Workbook.getWorkbook(new File(sourceFile));
			sheet = book.getSheet(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

}
