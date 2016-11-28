package data.implementation;

import java.io.File;
import java.io.IOException;

import jxl.NumberCell;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import po.MemberPO;
/**
 * 
 * @author 张新悦
 *
 */
public class MemberData implements data.dao.MemberDataDao {

	int dataSize = 6;
	String sourceFile = "MemberData.xls";
	WritableWorkbook wBook;
	WritableSheet wSheet;
	public MemberData() {
		// TODO Auto-generated constructor stub
		try {
			wBook = Workbook.createWorkbook(new File(sourceFile));
			wSheet = wBook.getSheet(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	private int hash(String ID){
		int hashResult = ID.hashCode();
		hashResult %= 1024;
		return hashResult;
	}
	//@Override
	public boolean add(MemberPO member) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(member.getMemberID());
		while(wSheet.getCell(row, col).getContents()!=""){
			col+=dataSize;
		}
		Label label = new Label(row,col,member.getMemberID());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(row,col,member.getPassword());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(row,col,member.getName());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(row,col,member.getBirthday());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(row,col,member.getPhone());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		Number number = new Number(row,col,member.getCredit());
		try {
			wSheet.addCell(number);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	//@Override
	public boolean delete(MemberPO member) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(member.getMemberID());
		while(!wSheet.getCell(row, col).getContents().equals(member.getMemberID())){
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
	public boolean update(MemberPO member) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(member.getMemberID());
		while(!wSheet.getCell(row, col).getContents().equals(member.getMemberID())){
			if(wSheet.getCell(row, col).getContents().equals("")){
				return false;
			}
			col+=dataSize;
		}
		Label label = new Label(row,col,member.getMemberID());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(row,col,member.getPassword());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(row,col,member.getName());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(row,col,member.getBirthday());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(row,col,member.getPhone());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		Number number = new Number(row,col,member.getCredit());
		try {
			wSheet.addCell(number);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//@Override
	public MemberPO getMember(String ID) {
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
		col++;
		String name = wSheet.getCell(row, col).getContents();
		col++;
		String birthday = wSheet.getCell(row,col).getContents();
		col++;
		String phone = wSheet.getCell(row,col).getContents();
		col++;
		NumberCell nCell = (NumberCell)wSheet.getCell(row, col);
		double credit = nCell.getValue();
		return new MemberPO(ID, name, password, birthday, credit, phone);
	}
}
