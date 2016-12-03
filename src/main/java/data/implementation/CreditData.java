package data.implementation;

import data.dataservice.CreditDataService;
import jxl.NumberCell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import po.CreditChangePO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by apple on 2016/12/3.
 */
public class CreditData implements CreditDataService {

	private int dateSize = 5;
	private String sourceFile = "CreditFile.xls";
	private Workbook book;
	private WritableWorkbook wBook;
	private WritableSheet wSheet;

	public CreditData(){
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

	public double getCredit(String memberID) {
		int col = 0;
		int row = hash(memberID);
		if(!wSheet.getCell(col, row).getContents().equals(memberID)) return -1.0;  //This member does not exist;
		double credit = ((NumberCell) wSheet.getCell(col+1, row)).getValue();
		return credit;
	}

	public ArrayList<CreditChangePO> getCreditChange(String memberID) {

		return null;
	}

	public boolean setCredit(String memberID, double credit) {
		return false;
	}

	public boolean addCreditChange(String memberID, CreditChangePO creditChange) {
		return false;
	}

	private int hash(String memberID){
		int hashResult = Integer.parseInt(memberID);
		return hashResult;
	}

	private CreditChangePO getCreditChange

}
