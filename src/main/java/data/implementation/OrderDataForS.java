package data.implementation;

import data.service.OrderDataService;
import jxl.*;
import jxl.read.biff.BiffException;
import po.OrderPO;
import other.OrderStatus;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by apple on 2016/12/1.
 */
public class OrderDataForS implements OrderDataService ,Serializable{

	private int dataSize = 20;
	private String sourceFile = "OrderDataForMember.xls";
	private Workbook book;
	private Sheet sheet;
	private OrderDataForM memberOrder = new OrderDataForM();
	private static final long serialVersionUID = -6833877079313718314L;   //序列号

	public boolean addOrder(OrderPO order) {
		return false;
	}

	/**
	 *
	 * @param order
	 * @return
	 */
	public boolean updateOrder(OrderPO order) {
		return memberOrder.updateOrder(order);
	}

	public boolean cancelOrder(String orderID) {
		return false;
	}

	public boolean makeOrderAbnormal(String orderID) {
		return false;
	}

	public  boolean recoverOrder(String orderID, double recover) {
		return memberOrder.recoverOrder(orderID, recover);
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
	 * @param row
	 * @return
	 */
	private ArrayList<OrderPO> getOrderList(int row){
		ArrayList<OrderPO> result = new ArrayList<OrderPO>();
		int col = 0;
		for (int i = 0; i < sheet.getRow(row).length; i+=dataSize) {
			result.add(getOrder(col+i, row));
		}
		if(result.size()==0) return null;   //does not have any order.
		return result;
	}

	/**
	 *
	 * @param col
	 * @param row
	 * @return
	 */
	private OrderPO getOrder(int col, int row){
		int status = (int)((NumberCell) sheet.getCell(col+dataSize-1, row)).getValue();
		OrderStatus orderStatus = null;
		switch (status) {
			case 0:
				orderStatus = OrderStatus.Executed;
				break;
			case 1:
				orderStatus = OrderStatus.Unexecuted;
				break;
			case 2:
				orderStatus = OrderStatus.Abnormal;
				break;
			case 3:
				orderStatus = OrderStatus.Canceled;
				break;
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
		String roomName = sheet.getCell(col, row).getContents();
		col++;
		long dateHelper = (long)((NumberCell) sheet.getCell(col, row)).getValue();
		Date checkIn = new Date(dateHelper);
		col++;
		dateHelper = (long)((NumberCell) sheet.getCell(col, row)).getValue();
		Date checkOut = new Date(dateHelper);
		col++;
		dateHelper = (long)((NumberCell) sheet.getCell(col, row)).getValue();
		Date latestCheckIn = new Date(dateHelper);
		col++;
		dateHelper = (long)((NumberCell) sheet.getCell(col, row)).getValue();
		Date creatTime = new Date(dateHelper);
		col++;
		Date actualCheckIn = null;
		Date actualCheckOut = null;
		Date cancelTime = null;
		if(orderStatus==OrderStatus.Executed){
			dateHelper = (long)((NumberCell) sheet.getCell(col, row)).getValue();
			actualCheckIn = new Date(dateHelper);
			col++;
			dateHelper = (long)((NumberCell) sheet.getCell(col, row)).getValue();
			actualCheckOut = new Date(dateHelper);
			col++;
		}
		else{
			col+=2;
		}
		if(orderStatus==OrderStatus.Canceled){
			dateHelper = (long)((NumberCell) sheet.getCell(col, row)).getValue();
			cancelTime = new Date(dateHelper);
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
		return new OrderPO(memberID,hotelID,orderID,orderStatus,creatTime,checkIn,actualCheckIn,latestCheckIn,checkOut,actualCheckOut,
				roomNUM,roomName,numOfClient,hasKid,score,evaluation,recover,promotion,price,cancelTime);
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
