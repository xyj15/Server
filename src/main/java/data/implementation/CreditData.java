package data.implementation;

import data.service.CreditDataService;
import other.OrderAction;
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
 * 控制creditChange数据
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
	}

	/**
	 *为ID为memberID的客户增加信用值改变记录
	 * @param memberID
	 * @param creditChange
	 * @return
	 */
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
		Number changeDate = new Number(col, row, creditChange.getDate().getTime());
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

	/**
	 *
	 * @param memberID
	 * @return
	 */
	public double getCredit(String memberID) {
		createSheet();
		int col = 0;
		int row = hash(memberID);
		if(row>=sheet.getRows()) return -1.0;
		if(!sheet.getCell(col, row).getContents().equals(memberID)) return -1.0;  //This member does not exist;
		double credit = ((NumberCell) sheet.getCell(col+1, row)).getValue();
		book.close();
		return credit;
	}

	/**
	 *
	 * @param memberID
	 * @return
	 */
	public ArrayList<CreditChangePO> getCreditChange(String memberID) {
		createSheet();
		int col = 0;
		int row = hash(memberID);
		if(row>=sheet.getRows()) return null;
		if(!sheet.getCell(col, row).getContents().equals(memberID)) return null;  //This member does not exist;
		ArrayList<CreditChangePO> result = new ArrayList<CreditChangePO>();
		col += 2;
		for (int i = 0; i < sheet.getRow(row).length-2; i+=dateSize) {
			result.add(getCreditChange(col+i, row));
		}
		book.close();
		return result;
	}

	/**
	 *
	 * @param memberID
	 * @param credit
	 * @return
	 */
	@Override
	public boolean setCredit(String memberID, double credit) {
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
		Number thisCredit = new Number(col, row, credit);
		try {
			wSheet.addCell(thisCredit);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	/**
	 *
	 */
	private void close() {
		try {
			wBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		book.close();
	}

	/**
	 *
	 * @param memberID
	 * @return
	 */
	private int hash(String memberID){
		int hashResult = Integer.parseInt(memberID);
		return hashResult;
	}

	/**
	 *
	 * @param col
	 * @param row
	 * @return
	 */
	private CreditChangePO getCreditChange(int col, int row){
		String orderID = sheet.getCell(col, row).getContents();
		col++;
		double change = ((NumberCell) sheet.getCell(col, row)).getValue();
		col++;
		double result = ((NumberCell) sheet.getCell(col, row)).getValue();
		col++;
		long dateHelper = (long)((NumberCell) sheet.getCell(col, row)).getValue();
		Date changeDate = new Date(dateHelper);
		col++;
		int action = (int) ((NumberCell) sheet.getCell(col, row)).getValue();
		switch (action){
			case 0: return new CreditChangePO(changeDate, orderID, OrderAction.ExecuteOrder, change, result);
			case 1: return new CreditChangePO(changeDate, orderID, OrderAction.AbnormalOrder, change, result);
			case 2: return new CreditChangePO(changeDate, orderID, OrderAction.CancelOrder, change, result);
			case 3: return new CreditChangePO(changeDate, orderID, OrderAction.RechargeCredit, change, result);
		}
		return null;
	}

	/**
	 *
	 */
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
