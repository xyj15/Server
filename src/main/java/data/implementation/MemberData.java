package data.implementation;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import data.dataservice.MemberDataService;
import helper.Encryption;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
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

	int dataSize = 8;
	int lengthOfID = 8;
	String sourceFile = "MemberData.xls";
	private DateFormat theDateFormat = new DateFormat ("dd-mm-yyyy");
	private WritableCellFormat dateFormat = new WritableCellFormat (theDateFormat);
	Workbook book;
	Sheet sheet;
	WritableWorkbook wBook;
	WritableSheet wSheet;
	public MemberData() {
		 //TODO Auto-generated constructor stub
//		try {
//			try {
//				book=Workbook.getWorkbook(new File(sourceFile));
//				wBook = Workbook.createWorkbook(new File(sourceFile),book);
//				wSheet = wBook.getSheet(0);
//			} catch (BiffException e) {
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}


	/**
	 *
	 * @param member
	 * @return
	 */
	//@Override
	public boolean addMember(MemberPO member) {
		// TODO Auto-generated method stub
		createWritableSheet();
		int col = 0;
		int row = hash(member.getMemberID());
		while(wSheet.getCell(col, row).getContents()!=""&&!wSheet.getCell(col, row).getContents().equals("-1")){
			if(wSheet.getCell(col, row).getContents().equals(Encryption.convertMD5(member.getMemberID()))){
				close();
				return false;   //The member with the same ID has already existed.
			}
			col+=dataSize;
		}
		Label label = new Label(col,row, Encryption.convertMD5(member.getMemberID()));
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(col,row, Encryption.convertMD5(member.getPassword()));
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(col,row,Encryption.convertMD5(member.getName()));
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(col,row,Encryption.convertMD5(member.getPhone()));
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
				DateTime birthDay = new DateTime(col,row,member.getBirthday(), dateFormat);
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

		close();
		return true;
	}

	/**
	 *
	 * @param memberID
	 * @return
	 */
	//@Override
	public boolean deleteMember(String memberID) {
		// TODO Auto-generated method stub
		createWritableSheet();
		int col = 0;
		int row = hash(memberID);
		while(!wSheet.getCell(col, row).getContents().equals(Encryption.convertMD5(memberID))){
			if(wSheet.getCell(col, row).getContents().equals("")){
				close();
				return false;     //The member with the ID of memberID does not exist.
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

	/**
	 *
	 * @param member
	 * @return
	 */
	//@Override
	public boolean updateMember(MemberPO member) {
		// TODO Auto-generated method stub
		createWritableSheet();
		int col = 0;
		int row = hash(member.getMemberID());
		while(!wSheet.getCell(col, row).getContents().equals(Encryption.convertMD5(member.getMemberID()))){
			if(wSheet.getCell(col, row).getContents().equals("")){
				close();
				return false;    //The member with the ID of memberID does not exist.
			}
			col+=dataSize;
		}
		col++;
		Label label = new Label(col,row,Encryption.convertMD5(member.getPassword()));
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(col,row,Encryption.convertMD5(member.getName()));
		try {
			wSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col++;
		label = new Label(col,row,Encryption.convertMD5(member.getPhone()));
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
		col++;
		switch(member.getMemberType().getValue()){
			case 0: {
				DateTime birthDay = new DateTime(col,row,member.getBirthday(), dateFormat);
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

		close();
		return true;
	}

	/**
	 *
	 * @param ID
	 * @return
	 */
	//@Override
	public MemberPO getMember(String ID) {
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
				String phone = Encryption.convertMD5(sheet.getCell(col, row).getContents());
				col++;
				NumberCell nCell = (NumberCell)sheet.getCell(col, row);
				double discount = nCell.getValue();
				col++;
				int level = (int)((NumberCell)sheet.getCell(col, row)).getValue();
				col++;
				int type = (int)((NumberCell)sheet.getCell(col, row)).getValue();
				col++;
				switch (type){
					case 0:{
						Date birthday = ((DateCell)sheet.getCell(col,row)).getDate();
						book.close();
						return new MemberPO(ID,name,password,phone,level,discount,MemberType.Orinary,birthday,"");
					}
					case 1:{
						String enterprise = sheet.getCell(col, row).getContents();
						book.close();
						return new MemberPO(ID,name,password,phone,level,discount,MemberType.Bussiness,null,enterprise);
					}
				}
			}
		}

		book.close();
		return null;
	}

	/**
	 *
	 * @return
	 */
	public String getAvailableMemberID() {
		createSheet();
		long rows = sheet.getRows();
		if(rows>99999999) return null;    //The space for saving the information of Members has been full.
		String ID = rows+"";
		while(ID.length()<lengthOfID){
			ID = '0'+ID;
		}
		return ID;
	}

	/**
	 *
	 * @param ID
	 * @return the result of hash
	 */
	private int hash(String ID){
		int hashResult = Integer.parseInt(ID);
		hashResult %= 100;
		return hashResult;
	}

	/**
	 *
	 */
	private void close(){
		write();
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
	 *
	 */
	private void write(){
		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
