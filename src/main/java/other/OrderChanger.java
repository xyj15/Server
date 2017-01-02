package other;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import po.OrderPO;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by apple on 2016/12/10.
 */
public class OrderChanger implements Serializable{
	private Workbook book;
	private WritableWorkbook wBook;
	private WritableSheet wSheet;
	private String sourceFile;
	private int dataSize = 20;
	private static final long serialVersionUID = -6833877079313718314L;   //序列号

	public OrderChanger(String sourceFile){
		this.sourceFile = sourceFile;
	}

	/**
	 *
	 * @param col
	 * @param row
	 * @param order
	 * @return
	 */
	public boolean addOrder(int col, int row, OrderPO order){
		createWritablSheet();
		while(wSheet.getCell(col, row).getContents()!=""){
			if(wSheet.getCell(col, row).getContents().equals(order.getOrderID())){
				close();
				return false;                               //An order of the same number has already existed.
			}
			col+=dataSize;
		}
		Label orderID = new Label(col,row,order.getOrderID());
		col++;
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
		Number checkIn = new Number(col, row, order.getCheckinTime().getTime());
		col++;
		Number checkOut = new Number(col, row, order.getCheckoutTime().getTime());
		col++;
		Number latestCheckIn = new Number(col, row, order.getLatestCheckinTime().getTime());
		col++;
		Number creatTime = new Number(col, row, order.getCreateTime().getTime());
		col++;
		Number actualCheckIn, actualCheckOut, cancelTime;
		if(order.getOrderStatus()==OrderStatus.Executed){
			actualCheckIn = new Number(col, row, order.getActualCheckinTime().getTime());
			col++;
			actualCheckOut = new Number(col, row, order.getActualCheckoutTime().getTime());
			col++;
		}
		else{
			actualCheckIn = new Number(col, row, new Date().getTime());
			col++;
			if(order.getActualCheckoutTime()!=null){
				actualCheckOut = new Number(col, row, order.getActualCheckoutTime().getTime());
			}
			else{
				actualCheckOut = new Number(col, row, -1);
			}
			col++;
		}
		if(order.getOrderStatus()==OrderStatus.Canceled){
			cancelTime = new Number(col, row, order.getCancelTime().getTime());
		}
		else{
			cancelTime = new Number(col, row, new Date().getTime());
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
		Number orderStatus = new Number(col, row, order.getOrderStatus().getV());

		try {
			wSheet.addCell(orderID);
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
			wSheet.addCell(orderStatus);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	/**
	 *
	 * @param order
	 * @return
	 */
	public boolean updateOrder(OrderPO order){
		createWritablSheet();
		WritableCell orderStart = (WritableCell) wSheet.findCell(order.getOrderID());
		if(orderStart==null){
			close();
			return false;    //Did not find the order.
		}
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
		Number checkIn = new Number(col, row, order.getCheckinTime().getTime());
		col++;
		Number checkOut = new Number(col, row, order.getCheckoutTime().getTime());
		col++;
		Number latestCheckIn = new Number(col, row, order.getLatestCheckinTime().getTime());
		col++;
		Number creatTime = new Number(col, row, order.getCreateTime().getTime());
		col++;
		Number actualCheckIn, actualCheckOut, cancelTime;
		if(order.getOrderStatus()==OrderStatus.Executed){
			actualCheckIn = new Number(col, row, order.getActualCheckinTime().getTime());
			col++;
			if(order.getActualCheckoutTime()!=null){
				actualCheckOut = new Number(col, row, order.getActualCheckoutTime().getTime());
			}
			else{
				actualCheckOut = new Number(col, row, -1);
			}
			col++;
		}
		else{
			actualCheckIn = new Number(col, row, new Date().getTime());
			col++;
			actualCheckOut = new Number(col, row, new Date().getTime());
			col++;
		}
		if(order.getOrderStatus()==OrderStatus.Canceled){
			if(order.getCancelTime()!=null){
				cancelTime = new Number(col, row, order.getCancelTime().getTime());
			}
			else{
				System.err.println("*");
				cancelTime = new Number(col, row, -1);
			}
		}
		else{
			cancelTime = new Number(col, row, new Date().getTime());
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
			wSheet.addCell(orderStatus);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();

		return true;
	}

	/**
	 *
	 * @param orderID
	 * @return
	 */
	public boolean cancelOrder(String orderID){
		createWritablSheet();
		Cell orderStart = wSheet.findCell(orderID);
		if(orderStart==null){
			close();
			return false;    //Did not find the order.
		}
		int col = orderStart.getColumn()+dataSize-1;
		int row = orderStart.getRow();
		Number orderStatus = new Number(col, row, OrderStatus.Canceled.getV());
		col -= 7;
		Calendar cal = Calendar.getInstance();
		Number cancelTime = new Number(col, row, cal.getTime().getTime());

		try {
			wSheet.addCell(orderStatus);
			wSheet.addCell(cancelTime);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	/**
	 *
	 * @param orderID
	 * @return
	 */
	public boolean makeOrderAbnormal(String orderID){
		createWritablSheet();
		Cell orderStart = wSheet.findCell(orderID);
		if(orderStart==null){
			close();
			return false;    //Did not find the order.
		}
		int col = orderStart.getColumn()+dataSize-1;
		int row = orderStart.getRow();
		Number orderStatus = new Number(col, row, OrderStatus.Abnormal.getV());

		try {
			wSheet.addCell(orderStatus);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	public boolean recoverOrder(String orderID, double recover){
		createWritablSheet();
		Cell orderStart = wSheet.findCell(orderID);
		if(orderStart==null){
			close();
			return false;    //Did not find the order.
		}
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
		return true;
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
		}
		book.close();
	}
}
