package data.implementation;

import helper.OrderDataServiceMini;
import jxl.Cell;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import po.OrderPO;
import helper.OrderStatus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apple on 2016/12/1.
 */
public class OrderDataForS implements OrderDataServiceMini {

	private int dataSize = 19;
	private String sourceFile = "OrderForMember.xls";
	private Workbook book;
	private WritableWorkbook wBook;
	private WritableSheet sheet;
	private OrderDataForH sync;

	public OrderDataForS(){
		sync = new OrderDataForH();
		try {
			book = Workbook.getWorkbook(new File(sourceFile));
			wBook = Workbook.createWorkbook(new File(sourceFile),book);
			sheet = wBook.getSheet(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	public boolean addOrder(OrderPO order) {
		return false;
	}

	public boolean updateOrder(OrderPO order) {
		return false;
	}

	public boolean cancleOrder(String orderID) {
		return false;
	}

	public boolean makeOrderAbnormal(String orderID) {
		return false;
	}

	public boolean recoverOrder(String orderID, double recover) {
		Cell orderStart = sheet.findCell(orderID);
		int col = orderStart.getColumn();
		int row = orderStart.getRow();
		Number orderStatus = new Number(col+dataSize-1, row, OrderStatus.Canceled.getV());
		Number recoverLocation = new Number(col+dataSize-4, row, recover);

		try {
			sheet.addCell(orderStatus);
			sheet.addCell(recoverLocation);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sync.recoverOrder(orderID,recover);
		return true;
	}

	public OrderPO getOrder(String orderID) {
		return null;
	}

	public ArrayList<OrderPO> getOrderList(String userID) {
		return null;
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

	public ArrayList<OrderPO> getAbnormalOrders(Date theDay) {
		ArrayList<OrderPO> result = new ArrayList<OrderPO>();
		int rows = sheet.getRows();
		for (int i = 0; i < rows; i++) {
			ArrayList<OrderPO> temp = getOrderList(i);
			if(temp!=null){
				for (OrderPO order:temp) {
					if(order.getCreateTime()==theDay){
						result.add(order);
					}
				}
			}
		}
		if(result.size()==0) return null;   //does not have any abnormal order.
		return result;
	}

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

	private OrderPO getOrder(int col, int row){
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
		Date checkIn = ((DateCell) sheet.getCell(col, row)).getDate();
		col++;
		Date checkOut = ((DateCell) sheet.getCell(col, row)).getDate();
		col++;
		Date latestCheckIn = ((DateCell) sheet.getCell(col, row)).getDate();
		col++;
		Date creatTime = ((DateCell) sheet.getCell(col, row)).getDate();
		col++;
		Date actualCheckIn = ((DateCell) sheet.getCell(col, row)).getDate();
		col++;
		Date actualCheckOut = ((DateCell) sheet.getCell(col, row)).getDate();
		col++;
		Date cancelTime = ((DateCell) sheet.getCell(col, row)).getDate();
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
		String roomName = sheet.getCell(col, row).getContents();
		col++;
		int status = (int)((NumberCell) sheet.getCell(col, row)).getValue();
		OrderStatus orderStatus = null;
		switch (status){
			case 0: orderStatus = OrderStatus.Executed; break;
			case 1: orderStatus = OrderStatus.Unexecuted; break;
			case 2: orderStatus = OrderStatus.Abnormal; break;
			case 3: orderStatus = OrderStatus.Canceled; break;
		}
		return new OrderPO(memberID,hotelID,orderID,orderStatus,creatTime,checkIn,actualCheckIn,latestCheckIn,checkOut,actualCheckOut,
				roomNUM,roomName,numOfClient,hasKid,score,evaluation,recover,promotion,price,cancelTime);
	}
}
