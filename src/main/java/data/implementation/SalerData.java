package data.implementation;

import java.io.File;
import java.io.IOException;

import data.dataservice.SalerDataService;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import po.SalerPO;
/**
 * 
 * @author 张新悦
 *
 */
public class SalerData implements SalerDataService {

	String sourceFile = "SalerData.xls";
	Workbook book;
	WritableWorkbook wBook;
	WritableSheet wSheet;
	int dataSize = 4;
	int lengthOfID = 4;
	
	public SalerData(){
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
	
	//@Override
	public boolean addSaler(SalerPO saler) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(saler.getAccount());
		while(wSheet.getCell(col, row).getContents()!=""){
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

		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	//@Override
	public boolean deleteSaler(String salerID) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(salerID);
		while(!wSheet.getCell(col, row).getContents().equals(salerID)){
			if(wSheet.getCell(col, row).getContents().equals("")){
				return false;
			}
			col+=dataSize;
		}
		for(int i=0;i<dataSize;i++){
			Label label = new Label(col+i,row,"");
			try {
				wSheet.addCell(label);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
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

	//@Override
	public boolean updateSaler(SalerPO saler) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(saler.getAccount());
		while(!wSheet.getCell(col, row).getContents().equals(saler.getAccount())){
			if(wSheet.getCell(col, row).getContents().equals("")){
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

		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	//@Override
	public SalerPO getSaler(String ID) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(ID);
		while(!wSheet.getCell(col, row).getContents().equals(ID)){
			if(wSheet.getCell(col, row).getContents().equals("")){
				return null;
			}
			col+=dataSize;
		}
		col++;
		String password = wSheet.getCell(col, row).getContents();
		col++;
		String name = wSheet.getCell(col, row).getContents();
		col++;
		String tel = wSheet.getCell(col, row).getContents();
		return new SalerPO(ID, password, name, tel);
	}

	public String getAvailableID() {
		long rows = wSheet.getRows();
		if(rows>99999999) return null;    //The space for saving the information of Members has been full.
		String ID = rows+1+"";
		while(ID.length()<lengthOfID){
			ID = '0'+ID;
		}
		return ID;
	}

	private int hash(String ID){
		int hashResult = ID.hashCode();
		hashResult%=10;
		return hashResult;
	}

	/**
	 *
	 */
	public void close() {
		try {
			wBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		book.close();
	}
}
