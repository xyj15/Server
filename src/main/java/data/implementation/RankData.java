package data.implementation;

import data.service.RankDataService;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Number;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by apple on 2016/12/3.
 */
public class RankData implements RankDataService {

	private String sourceFile = "RankData.xls";
	private Workbook book;
	private Sheet sheet;
	private WritableWorkbook wBook;
	private WritableSheet wSheet;

	public ArrayList<Double> getCreditList() {
		createSheet();
		int col = 0;
		int row = 0;
		int rows = sheet.getRows();
		if(rows==0) return null;  //There is no rank.
		ArrayList<Double> result = new ArrayList<Double>();
		for (int i = 0; i < rows; i++) {
			double temp = ((NumberCell) sheet.getCell(col, row+i)).getValue();
			result.add(new Double(temp));
		}
		book.close();
		return result;
	}

	public ArrayList<Double> getDiscountList() {
		createSheet();
		int col = 1;
		int row = 0;
		int rows = sheet.getRows();
		if(rows==0) return null;  //There is no rank.
		ArrayList<Double> result = new ArrayList<Double>();
		for (int i = 0; i < rows; i++) {
			double temp = ((NumberCell) sheet.getCell(col, row+i)).getValue();
			result.add(new Double(temp));
		}
		book.close();
		return result;
	}

	public boolean updateCreditList(ArrayList<Double> creditList) {
		createWritableSheet();
		int col = 0;
		int row = 0;
		for (int i = 0; i < creditList.size(); i++) {
			Number temp = new Number(col, row+i, creditList.get(i).doubleValue());
			try {
				wSheet.addCell(temp);
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}

		close();
		return true;
	}

	public boolean updateDiscountList(ArrayList<Double> discountList) {
		createWritableSheet();
		int col = 1;
		int row = 0;
		for (int i = 0; i < discountList.size(); i++) {
			Number temp = new Number(col, row+i, discountList.get(i).doubleValue());
			try {
				wSheet.addCell(temp);
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}

		close();
		return true;
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
}
