package data.implementation;

import java.io.File;
import java.io.IOException;

import data.dao.SalerDataDao;
import jxl.Workbook;
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
public class SalerData implements SalerDataDao {

	String sourceFile = "SalerData.xls";
	WritableWorkbook wBook;
	WritableSheet wSheet;
	int dataSize = 2;
	
	public SalerData(){
		try {
			wBook = Workbook.createWorkbook(new File(sourceFile));
			wSheet = wBook.getSheet(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//@Override
	public boolean add(SalerPO saler) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(saler.getAccount());
		while(wSheet.getCell(row, col).getContents()!=""){
			col+=dataSize;
		}
		Label account = new Label(row,col,saler.getAccount());
		col++;
		Label password = new Label(row,col,saler.getPassword());
		try {
			wSheet.addCell(account);
			wSheet.addCell(password);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return true;
	}

	//@Override
	public boolean delete(SalerPO saler) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(saler.getAccount());
		while(!wSheet.getCell(row, col).getContents().equals(saler.getAccount())){
			if(wSheet.getCell(row, col).getContents().equals("")){
				return false;
			}
			col+=dataSize;
		}
		for(int i=0;i<dataSize;i++){
			Label label = new Label(row,col+i,"");
			try {
				wSheet.addCell(label);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	//@Override
	public boolean update(SalerPO saler) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(saler.getAccount());
		while(!wSheet.getCell(row, col).getContents().equals(saler.getAccount())){
			if(wSheet.getCell(row, col).getContents().equals("")){
				return false;
			}
			col+=dataSize;
		}
		Label password = new Label(row,col,saler.getPassword());
		try {
			wSheet.addCell(password);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return true;
	}

	//@Override
	public SalerPO getSaler(String ID) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(ID);
		while(!wSheet.getCell(row, col).getContents().equals(ID)){
			if(wSheet.getCell(row, col).getContents().equals("")){
				return null;
			}
			col+=dataSize;
		}
		col++;
		String password = wSheet.getCell(row, col).getContents();
		return new SalerPO(ID, password);
	}
	
	private int hash(String ID){
		int hashResult = ID.hashCode();
		hashResult%=10;
		return hashResult;
	}

}
