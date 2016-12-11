package data.implementation;

import java.io.File;
import java.io.IOException;

import data.dataservice.SalerDataService;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import po.SalerPO;
/**
 * 
 * @author 张新悦
 *
 */
public class SalerData implements SalerDataService {

	String sourceFile = "SalerData.xls";
	Workbook book;
	Sheet sheet;
	WritableWorkbook wBook;
	WritableSheet wSheet;
	int dataSize = 4;
	int lengthOfID = 4;
	
	//@Override
	public boolean addSaler(SalerPO saler) {
		// TODO Auto-generated method stub
		createWritableSheet();
		int col = 0;
		int row = hash(saler.getUserID());
		while(wSheet.getCell(col, row).getContents()!=""&&(!wSheet.getCell(col, row).getContents().equals("-1"))){
			if(wSheet.getCell(col, row).getContents().equals(saler.getUserID())){
				close();
				return false;
			}
			col+=dataSize;
		}
		Label account = new Label(col,row,saler.getAccount());
		col++;
		Label password = new Label(col,row,saler.getPassword());
		col++;
		Label name = new Label(col, row, saler.getName());
		col++;
		Label tel = new Label(col, row, saler.getTel());
		try {
			wSheet.addCell(account);
			wSheet.addCell(password);
			wSheet.addCell(name);
			wSheet.addCell(tel);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WritableSheet wSheet2 = wBook.getSheet(1);
		int sum = (int)((NumberCell) wSheet2.getCell(0, 0)).getValue();
		Number total = new Number(0, 0,sum+1);
		try {
			wSheet2.addCell(total);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	//@Override
	public boolean deleteSaler(String salerID) {
		// TODO Auto-generated method stub
		createWritableSheet();
		int col = 0;
		int row = hash(salerID);
		while(!wSheet.getCell(col, row).getContents().equals(salerID)){
			if(wSheet.getCell(col, row).getContents().equals("")){
				close();
				return false;
			}
			col+=dataSize;
		}
		for(int i=0;i<dataSize;i++){
			Label label = new Label(col+i,row,"-1");
			try {
				wSheet.addCell(label);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		WritableSheet wSheet2 = wBook.getSheet(1);
		int sum = (int)((NumberCell) wSheet2.getCell(0, 0)).getValue();
		Number total = new Number(0, 0,sum-1);
		try {
			wSheet2.addCell(total);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	//@Override
	public boolean updateSaler(SalerPO saler) {
		// TODO Auto-generated method stub
		createWritableSheet();
		int col = 0;
		int row = hash(saler.getAccount());
		while(!wSheet.getCell(col, row).getContents().equals(saler.getUserID())){
			if(wSheet.getCell(col, row).getContents().equals("")){
				close();
				return false;
			}
			col+=dataSize;
		}
		col++;
		Label password = new Label(col,row,saler.getPassword());
		col++;
		Label name = new Label(col, row, saler.getName());
		col++;
		Label tel = new Label(col, row, saler.getTel());
		try {
			wSheet.addCell(password);
			wSheet.addCell(name);
			wSheet.addCell(tel);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return true;
	}

	//@Override
	public SalerPO getSaler(String ID) {
		// TODO Auto-generated method stub
		createSheet();
		int col = 0;
		int row = hash(ID);
		if(row>=sheet.getRows()||col>=sheet.getColumns()){
			book.close();
			return null;
		}
		for (int i = 0; i < sheet.getRow(row).length; i+=dataSize) {
			if(sheet.getCell(i, row).getContents().equals(ID)){
				col=i;
				col++;
				String password = sheet.getCell(col, row).getContents();
				col++;
				String name = sheet.getCell(col, row).getContents();
				col++;
				String tel = sheet.getCell(col, row).getContents();
				book.close();
				return new SalerPO(ID, password, name, tel);
			}
		}
		book.close();
		return null;    //Did not find the saler
	}

	public String getAvailableSalerID() {
		createSheet();
		Sheet sheet2 = book.getSheet(1);
		int sum = (int)((NumberCell) sheet2.getCell(0, 0)).getValue();
		if(sum>9999) return null;    //The space for saving the information of Members has been full.
		String ID = sum+1+"";
		while(ID.length()<lengthOfID){
			ID = '0'+ID;
		}
		return ID;
	}

	private int hash(String ID){
		int hashResult = Integer.parseInt(ID);
		hashResult%=10;
		return hashResult;
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
