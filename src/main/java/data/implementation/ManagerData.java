package data.implementation;

import data.service.ManagerDataService;
import other.Encryption;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import po.ManagerPO;

import java.io.File;
import java.io.IOException;

/**
 * Created by apple on 2016/12/1.
 * @author 张新悦
 * @version 2016-12-01
 */
public class ManagerData implements ManagerDataService {

	private String sourceFile = "ManagerData.xls";
	private Workbook book;
	private Sheet sheet;
	private WritableWorkbook wBook;
	private WritableSheet wSheet;
	private int dataSize = 4;

	public boolean updateManager(ManagerPO manager) {
		createWritableSheet();
		int col = 0;
		int row = 0;
		Label ID = new Label(col, row, Encryption.convertMD5(manager.getUserID()));
		col++;
		Label password = new Label(col,row, Encryption.convertMD5(manager.getPassword()));
		col++;
		Label name = new Label(col, row, Encryption.convertMD5(manager.getName()));
		col++;
		Label tel = new Label(col, row, Encryption.convertMD5(manager.getTel()));

		try {
			wSheet.addCell(ID);
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

	public ManagerPO getManager() {
		createSheet();
		int col = 0;
		int row = 0;
		String managerID = Encryption.convertMD5(sheet.getCell(col, row).getContents());
		col++;
		String password = Encryption.convertMD5(sheet.getCell(col, row).getContents());
		col++;
		String name = Encryption.convertMD5(sheet.getCell(col, row).getContents());
		col++;
		String tel = Encryption.convertMD5(sheet.getCell(col, row).getContents());
		book.close();
		return new ManagerPO(managerID, password, name, tel);
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
