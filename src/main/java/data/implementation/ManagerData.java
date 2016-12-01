package data.implementation;

import data.dataservice.ManagerDataService;
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

	String sourceFile = "ManagerData.xls";
	Workbook book;
	WritableWorkbook wBook;
	WritableSheet wSheet;
	int dataSize = 4;

	public ManagerData(){
		try {
			try {
				book = Workbook.getWorkbook(new File(sourceFile));
			} catch (BiffException e) {
				e.printStackTrace();
			}
			wBook = Workbook.createWorkbook(new File(sourceFile));
			wSheet = wBook.getSheet(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean updateManager(ManagerPO manager) {
		int col = 0;
		int row = 0;
		col++;
		Label password = new Label(col,row,manager.getPassword());
		col++;
		Label name = new Label(col, row, manager.getName());
		col++;
		Label tel = new Label(col, row, manager.getTel());
		try {
			wSheet.addCell(password);
			wSheet.addCell(name);
			wSheet.addCell(tel);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public ManagerPO getManager() {
		int col = 0;
		int row = 0;
		String managerID = wSheet.getCell(col, row).getContents();
		col++;
		String password = wSheet.getCell(col, row).getContents();
		col++;
		String name = wSheet.getCell(col, row).getContents();
		col++;
		String tel = wSheet.getCell(col, row).getContents();

		return new ManagerPO(managerID, password, name, tel);
	}
}
