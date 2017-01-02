package data.implementation;

import java.io.File;
import java.io.IOException;

import data.service.SalerDataService;
import jxl.NumberCell;
import jxl.write.Number;
import other.Encryption;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
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
	int dataSize = 5;
	int lengthOfID = 4;
	
	//@Override
	public boolean addSaler(SalerPO saler) {
		// TODO Auto-generated method stub
		createWritableSheet();
		int col = 0;
		int row = hash(saler.getUserID());
		while(wSheet.getCell(col, row).getContents()!=""&&(!wSheet.getCell(col, row).getContents().equals("-1"))){
			if(wSheet.getCell(col, row).getContents().equals(Encryption.convertMD5(saler.getUserID()))){
				close();
				return false;
			}
			col+=dataSize;
		}
		Label account = new Label(col, row, Encryption.convertMD5(saler.getUserID()));
		col++;
		Label password = new Label(col, row, Encryption.convertMD5(saler.getPassword()));
		col++;
		Label name = new Label(col, row, Encryption.convertMD5(saler.getName()));
		col++;
		Label tel = new Label(col, row, Encryption.convertMD5(saler.getTel()));
		col++;
		int log = 0;
		if(saler.isLoged()) log = 1;
		jxl.write.Number isLogged = new Number(col, row, log);
		try {
			wSheet.addCell(account);
			wSheet.addCell(password);
			wSheet.addCell(name);
			wSheet.addCell(tel);
			wSheet.addCell(isLogged);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
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
		while(!wSheet.getCell(col, row).getContents().equals(Encryption.convertMD5(salerID))){
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

		close();
		return true;
	}

	//@Override
	public boolean updateSaler(SalerPO saler) {
		// TODO Auto-generated method stub
		createWritableSheet();
		int col = 0;
		int row = hash(saler.getAccount());
		while(!wSheet.getCell(col, row).getContents().equals(Encryption.convertMD5(saler.getUserID()))){
			if(wSheet.getCell(col, row).getContents().equals("")){
				close();
				return false;
			}
			col+=dataSize;
		}
		col++;
		Label password = new Label(col, row, Encryption.convertMD5(saler.getPassword()));
		col++;
		Label name = new Label(col, row, Encryption.convertMD5(saler.getName()));
		col++;
		Label tel = new Label(col, row, Encryption.convertMD5(saler.getTel()));
		col++;
		int log = 0;
		if(saler.isLoged()) log = 1;
		jxl.write.Number isLogged = new Number(col, row, log);
		try {
			wSheet.addCell(password);
			wSheet.addCell(name);
			wSheet.addCell(tel);
			wSheet.addCell(isLogged);
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
			if(sheet.getCell(i, row).getContents().equals(Encryption.convertMD5(ID))){
				col=i;
				col++;
				String password = Encryption.convertMD5(sheet.getCell(col, row).getContents());
				col++;
				String name = Encryption.convertMD5(sheet.getCell(col, row).getContents());
				col++;
				String tel = Encryption.convertMD5(sheet.getCell(col, row).getContents());
				col++;
				boolean isLogged = false;
				int log = (int)((NumberCell) sheet.getCell(col, row)).getValue();
				if(log==1) isLogged =true;
				book.close();
				return new SalerPO(ID, password, name, tel, isLogged);
			}
		}
		book.close();
		return null;    //Did not find the saler
	}

	public String getAvailableSalerID() {
		createSheet();
		int sum = sheet.getRows();
		if(sum>9999) return null;    //The space for saving the information of Members has been full.
		String ID = sum+"";
		while(ID.length()<lengthOfID){
			ID = '0'+ID;
		}
		return ID;
	}

	private int hash(String ID){
		int hashResult = Integer.parseInt(ID);
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
