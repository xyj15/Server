package data.implementation;

import data.dataservice.CreditDataService;
import helper.OrderAction;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import po.CreditChangePO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apple on 2016/12/3.
 */
public class CreditData implements CreditDataService {

	private int dateSize = 5;
	private String sourceFile = "CreditData.xls";
	private Workbook book;
	private Sheet sheet;
	private WritableWorkbook wBook;
	private WritableSheet wSheet;

	public CreditData(){
//		try {
//			book = Workbook.getWorkbook(new File(sourceFile));
//			wBook = Workbook.createWorkbook(new File(sourceFile), book);
//			wSheet = wBook.getSheet(0);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (BiffException e) {
//			e.printStackTrace();
//		}
	}

	public double getCredit(String memberID) {
		try {
			book = Workbook.getWorkbook(new File(sourceFile));
			sheet = book.getSheet(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		int col = 0;
		int row = hash(memberID);
		if(!sheet.getCell(col, row).getContents().equals(memberID)) return -1.0;  //This member does not exist;
		double credit = ((NumberCell) sheet.getCell(col+1, row)).getValue();
		return credit;
	}

	public ArrayList<CreditChangePO> getCreditChange(String memberID) {
		createWritableSheet();
		int col = 0;
		int row = hash(memberID);
		if(!wSheet.getCell(col, row).getContents().equals(memberID)) return null;  //This member does not exist;
		ArrayList<CreditChangePO> result = new ArrayList<CreditChangePO>();
		col += 2;
		while(wSheet.getCell(col, row).getContents()!=""){
			result.add(getCreditChange(col, row));
			col+=dateSize;
		}
		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		close();
		return result;
	}

	public boolean addCreditChange(String memberID, CreditChangePO creditChange) {
		createWritableSheet();
		int col = 0;
		int row = hash(memberID);
		if(!wSheet.getCell(col, row).getContents().equals(memberID)){
			Label member = new Label(col, row, memberID);
			try {
				wSheet.addCell(member);
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
		col++;
		Number latestCredit = new Number(col, row, creditChange.getResult());
		col++;
		while(wSheet.getCell(col, row).getContents()!=""){
			col+=dateSize;
		}
		Label orderID = new Label(col, row, creditChange.getOrderID());
		col++;
		Number change = new Number(col, row, creditChange.getChange());
		col++;
		Number result = new Number(col, row, creditChange.getResult());
		col++;
		DateTime changeDate = new DateTime(col, row, creditChange.getDate());
		col++;
		Number orderAction = new Number(col, row, creditChange.getOrderAction().getValue());
		try {
			wSheet.addCell(latestCredit);
			wSheet.addCell(orderID);
			wSheet.addCell(change);
			wSheet.addCell(result);
			wSheet.addCell(changeDate);
			wSheet.addCell(orderAction);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		close();
		return true;
	}

	private void close() {
		try {
			wBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		book.close();
	}

	private int hash(String memberID){
		int hashResult = Integer.parseInt(memberID);
		return hashResult;
	}

	private CreditChangePO getCreditChange(int col, int row){
		String orderID = wSheet.getCell(col, row).getContents();
		col++;
		double change = ((NumberCell) wSheet.getCell(col, row)).getValue();
		col++;
		double result = ((NumberCell) wSheet.getCell(col, row)).getValue();
		col++;
		Date changeDate = ((DateCell) wSheet.getCell(col, row)).getDate();
		col++;
		int action = (int) ((NumberCell) wSheet.getCell(col, row)).getValue();
		switch (action){
			case 0: return new CreditChangePO(changeDate, orderID, OrderAction.ExecuteOrder, change, result);
			case 1: return new CreditChangePO(changeDate, orderID, OrderAction.AbnormalOrder, change, result);
			case 2: return new CreditChangePO(changeDate, orderID, OrderAction.CancelOrder, change, result);
			case 3: return new CreditChangePO(changeDate, orderID, OrderAction.RechargeCredit, change, result);
		}
		return null;
	}

	private void createWritableSheet(){
		try {
			book = Workbook.getWorkbook(new File(sourceFile));
			wBook = Workbook.createWorkbook(new File(sourceFile), book);
			wSheet = wBook.getSheet(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}
}
