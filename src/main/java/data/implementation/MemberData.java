package data.implementation;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import data.dataservice.MemberDataService;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import po.MemberPO;
import helper.MemberType;

/**
 * 
 * @author 张新悦
 *
 */
public class MemberData implements MemberDataService {

	int dataSize = 9;
	String sourceFile = "MemberData.xls";
	Workbook book;
	WritableWorkbook wBook;
	WritableSheet wSheet;
	public MemberData() {
		// TODO Auto-generated constructor stub
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

	private int hash(String ID){
		int hashResult = ID.hashCode();
		hashResult %= 1024;
		return hashResult;
	}
	//@Override
	public boolean addMember(MemberPO member) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(member.getMemberID());
		while(wSheet.getCell(col, row).getContents()!=""){
			if(wSheet.getCell(col, row).getContents().equals(member.getMemberID())){
				return true;
			}
			col+=dataSize;
		}
		Label label = new Label(col,row,member.getMemberID());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(col,row,member.getPassword());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(col,row,member.getName());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(col,row,member.getPhone());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		Number number = new Number(col,row,member.getDiscount());
		try {
			wSheet.addCell(number);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		number = new Number(col, row, member.getLevel());
		try {
			wSheet.addCell(number);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		Number memberType = new Number(col, row, member.getMemberType().getValue());
		try {
			wSheet.addCell(memberType);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		switch(member.getMemberType().getValue()){
			case 0: {
				DateTime birthDay = new DateTime(col,row,member.getBirthday());
				try {
					wSheet.addCell(birthDay);
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case 1: {
				Label enterprise = new Label(col, row, member.getEnterprise());
				try {
					wSheet.addCell(enterprise);
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	public boolean delete(String memberID) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(memberID);
		while(!wSheet.getCell(col, row).getContents().equals(memberID)){
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
	public boolean updateMember(MemberPO member) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(member.getMemberID());
		while(!wSheet.getCell(col, row).getContents().equals(member.getMemberID())){
			if(wSheet.getCell(col, row).getContents().equals("")){
				return false;
			}
			col+=dataSize;
		}
		Label label = new Label(col,row,member.getMemberID());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(col,row,member.getPassword());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(col,row,member.getName());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(col,row,member.getPhone());
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		Number number = new Number(col,row,member.getDiscount());
		try {
			wSheet.addCell(number);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		number = new Number(col, row, member.getLevel());
		try {
			wSheet.addCell(number);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		switch(member.getMemberType().getValue()){
			case 0: {
				DateTime birthDay = new DateTime(col,row,member.getBirthday());
				try {
					wSheet.addCell(birthDay);
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case 1: {
				Label enterprise = new Label(col, row, member.getEnterprise());
				try {
					wSheet.addCell(enterprise);
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	public MemberPO getMember(String ID) {
		// TODO Auto-generated method stub
		int col = 0;
		int row = hash(ID);
		while(!wSheet.getCell(col, row).getContents().equals(ID)){
			if(wSheet.getCell(col, row).getContents().equals("")){
				return null;   //cannot find the member with the given ID
			}
			col+=dataSize;
		}
		col++;
		String password = wSheet.getCell(col, row).getContents();
		col++;
		String name = wSheet.getCell(col, row).getContents();
		col++;
		String phone = wSheet.getCell(col,row).getContents();
		col++;
		NumberCell nCell = (NumberCell)wSheet.getCell(col, row);
		double discount = nCell.getValue();
		col++;
		int level = (int)((NumberCell)wSheet.getCell(col, row)).getValue();
		col++;
		int type = (int)((NumberCell)wSheet.getCell(col, row)).getValue();
		col++;
		switch (type){
			case 0:{
				Date birthday = ((DateCell)wSheet.getCell(col,row)).getDate();
				return new MemberPO(ID,name,password,phone,level,discount,MemberType.Orinary,birthday,"");
			}
			case 1:{
				String enterprise = wSheet.getCell(col, row).getContents();
				return new MemberPO(ID,name,password,phone,level,discount,MemberType.Bussiness,null,enterprise);
			}
		}

		return null;
	}
}
