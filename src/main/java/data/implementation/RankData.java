package data.implementation;

import data.dataservice.RankDataService;
import jxl.NumberCell;
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
	private WritableWorkbook wBook;
	private WritableSheet wSheet;

	public RankData(){
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

	public ArrayList<Double> getCreditList() {
		int col = 0;
		int row = 0;
		int rows = wSheet.getRows();
		if(rows==0) return null;  //There is no rank.
		ArrayList<Double> result = new ArrayList<Double>();
		for (int i = 0; i < rows; i++) {
			double temp = ((NumberCell) wSheet.getCell(col, row+i)).getValue();
			result.add(new Double(temp));
		}
		return result;
	}

	public ArrayList<Double> getDiscountList() {
		int col = 1;
		int row = 0;
		int rows = wSheet.getRows();
		if(rows==0) return null;  //There is no rank.
		ArrayList<Double> result = new ArrayList<Double>();
		for (int i = 0; i < rows; i++) {
			double temp = ((NumberCell) wSheet.getCell(col, row+i)).getValue();
			result.add(new Double(temp));
		}
		return result;
	}

	public boolean updateCreditList(ArrayList<Double> creditList) {
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

		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateDiscountList(ArrayList<Double> discountList) {
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

		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
