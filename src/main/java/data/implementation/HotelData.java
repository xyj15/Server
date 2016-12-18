package data.implementation;

import data.service.HotelDataService;
import helper.Encryption;
import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import po.HotelPO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by apple on 2016/12/1.
 * @author 张新悦
 * @version 2016-12-01
 */
public class HotelData implements HotelDataService {

	private int dataSize = 13;
	private int lengthOfID = 6;
	private String sourceFile = "HotelData.xls";
	private Workbook book;
	private Sheet sheet;
	private WritableWorkbook wBook;
	private WritableSheet wSheet;

	public HotelData(){
//		try {
//			book = Workbook.getWorkbook(new File(sourceFile));
//			wBook = Workbook.createWorkbook(new File(sourceFile),book);
//			wSheet = wBook.getSheet(0);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (BiffException e) {
//			e.printStackTrace();
//		}

	}


	/**
	 *
	 * @param hotel
	 * @return
	 */
	public boolean addHotel(HotelPO hotel) {
		createWritableSheet();
		int col = 0;
		int row = hash(hotel.getCity()+hotel.getDistrict());
		while(wSheet.getCell(col, row).getContents()!=""&&(!wSheet.getCell(col, row).getContents().equals("-1"))){
			if(wSheet.getCell(col, row).getContents().equals(Encryption.convertMD5(hotel.getUserID()))){
				close();
				return false;     // the hotel with the same ID has already existed
			}
			col+=dataSize;
		}
		Label ID = new Label(col, row, Encryption.convertMD5(hotel.getUserID()));
		col++;
		Label password = new Label(col, row, Encryption.convertMD5(hotel.getPassword()));
		col++;
		Label name = new Label(col, row, hotel.getName());
		col++;
		Label city = new Label(col, row, hotel.getCity());
		col++;
		Label district = new Label(col, row, hotel.getDistrict());
		col++;
		Label address = new Label(col, row, hotel.getAddress());
		col++;
		Label service = new Label(col, row, hotel.getService());
		col++;
		Label introduction = new Label(col, row, hotel.getIntroduction());
		col++;
		Label managerName = new Label(col, row, hotel.getManagerName());
		col++;
		Label managerTel = new Label(col, row, hotel.getManagerTel());
		col++;
		Number level = new Number(col, row, hotel.getLevel());
		col++;
		Number score = new Number(col, row, hotel.getScore());
		col++;
		String enterPrise = "";
		for (String temp:hotel.getEnterpriseList()) {
			enterPrise = enterPrise+";"+temp;
		}
		Label enterPriseLabel = new Label(col, row, enterPrise);
		try {
			wSheet.addCell(ID);
			wSheet.addCell(password);
			wSheet.addCell(name);
			wSheet.addCell(city);
			wSheet.addCell(district);
			wSheet.addCell(address);
			wSheet.addCell(service);
			wSheet.addCell(introduction);
			wSheet.addCell(managerName);
			wSheet.addCell(managerTel);
			wSheet.addCell(level);
			wSheet.addCell(score);
			wSheet.addCell(enterPriseLabel);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		WritableSheet sumSheet = wBook.getSheet(1);
		int sum = (int) ((NumberCell) sumSheet.getCell(0, 0)).getValue();
		sum++;
		Number sumOfHotel = new Number(0,0,sum);
		try {
			sumSheet.addCell(sumOfHotel);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		try {
			Workbook roomBook = Workbook.getWorkbook(new File("RoomData.xls"));
			WritableWorkbook wRoomBook = Workbook.createWorkbook(new File("RoomData.xls"),roomBook);
			wRoomBook.createSheet(hotel.getName(), Integer.parseInt(hotel.getUserID()));
			wRoomBook.write();
			try {
				wRoomBook.close();
			} catch (WriteException e) {
				e.printStackTrace();
			}
			roomBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	/**
	 *
	 * @param hotelID
	 * @return
	 */
	public boolean deleteHotel(String hotelID) {
		createWritableSheet();
		Cell hotelStart = wSheet.findCell(Encryption.convertMD5(hotelID));
		if(hotelStart==null){
			close();
			return false;
		}
		int col = hotelStart.getColumn();
		int row = hotelStart.getRow();
//		while(!wSheet.getCell(col, row).getContents().equals(hotelID)){
//			if(wSheet.getCell(col, row).getContents().equals("")){
//				close();
//				return false;                         //The hotel with ID of hotelID does not exist.
//			}
//			col+=dataSize;
//		}
		for(int i=0;i<dataSize;i++){
			Label label = new Label(col+i,row,"-1");
			try {
				wSheet.addCell(label);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			Workbook roomBook = Workbook.getWorkbook(new File("RoomData.xls"));
			WritableWorkbook wRoomBook = Workbook.createWorkbook(new File("RoomData.xls"),roomBook);
			wRoomBook.removeSheet(Integer.parseInt(hotelID));
			wRoomBook.write();
			try {
				wRoomBook.close();
			} catch (WriteException e) {
				e.printStackTrace();
			}
			roomBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	/**
	 *
	 * @param hotel
	 * @return
	 */
	public boolean updateHotel(HotelPO hotel) {
		createWritableSheet();
		int col = 0;
		int row = hash(hotel.getCity()+hotel.getDistrict());
		while(!wSheet.getCell(col, row).getContents().equals(Encryption.convertMD5(hotel.getUserID()))){
			if(wSheet.getCell(col, row).getContents().equals("")){
				close();                         //The hotel with ID of hotelID does not exist.
				return false;
			}
			col+=dataSize;
		}
		Label ID = new Label(col, row, Encryption.convertMD5(hotel.getUserID()));
		col++;
		Label password = new Label(col, row, Encryption.convertMD5(hotel.getPassword()));
		col++;
		Label name = new Label(col, row, hotel.getName());
		col++;
		Label city = new Label(col, row, hotel.getCity());
		col++;
		Label district = new Label(col, row, hotel.getDistrict());
		col++;
		Label address = new Label(col, row, hotel.getAddress());
		col++;
		Label service = new Label(col, row, hotel.getService());
		col++;
		Label introduction = new Label(col, row, hotel.getIntroduction());
		col++;
		Label managerName = new Label(col, row, hotel.getManagerName());
		col++;
		Label managerTel = new Label(col, row, hotel.getManagerTel());
		col++;
		Number level = new Number(col, row, hotel.getLevel());
		col++;
		Number score = new Number(col, row, hotel.getScore());
		col++;
		String enterPrise = "";
		for (String temp:hotel.getEnterpriseList()) {
			enterPrise = enterPrise+";"+temp;
		}
		Label enterPriseLabel = new Label(col, row, enterPrise);
		try {
			wSheet.addCell(ID);
			wSheet.addCell(password);
			wSheet.addCell(name);
			wSheet.addCell(city);
			wSheet.addCell(district);
			wSheet.addCell(address);
			wSheet.addCell(service);
			wSheet.addCell(introduction);
			wSheet.addCell(managerName);
			wSheet.addCell(managerTel);
			wSheet.addCell(level);
			wSheet.addCell(score);
			wSheet.addCell(enterPriseLabel);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	/**
	 *
	 * @param hotelID
	 * @return
	 */
	public HotelPO getHotelByID(String hotelID) {
		createSheet();
		Cell hotelStart = sheet.findCell(Encryption.convertMD5(hotelID));
		if(hotelStart==null) return null;
		int col = hotelStart.getColumn();
		int row = hotelStart.getRow();
		HotelPO result = getHotelByPosition(col, row);
		book.close();
		return result;
	}

	/**
	 *
	 * @param hotelName
	 * @return
	 */
	public HotelPO getHotelByName(String hotelName) {
		createSheet();
		Cell hotelStart = sheet.findCell(hotelName);
		if(hotelStart==null) return null;
		int col = hotelStart.getColumn()-2;
		int row = hotelStart.getRow();
		HotelPO result = getHotelByPosition(col, row);
		book.close();
		return result;
	}

	/**
	 *
	 * @return
	 */
	public String getAvailableHotelID() {
		createSheet();
		Sheet sumSheet = book.getSheet(1);
		int sum = (int) ((NumberCell) sumSheet.getCell(0, 0)).getValue();
		if(sum>999999) return null;   //The space for saving the information of Hotels has been full.
		String ID = sum+"";
		while(ID.length()<lengthOfID){
			ID = '0'+ID;
		}
		book.close();
		return ID;
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
	 *
	 * @param hotelScope
	 * @return
	 */
	private int hash(String hotelScope){
		int result = hotelScope.hashCode();
		if(result<0)result = 0-result;
		result%=10;
		return result;
	}

	/**
	 *
	 * @param col
	 * @param row
	 * @return
	 */
	private HotelPO getHotelByPosition(int col, int row){
		String ID = Encryption.convertMD5(sheet.getCell(col, row).getContents());
		col++;
		String password = Encryption.convertMD5(sheet.getCell(col, row).getContents());
		col++;
		String name = sheet.getCell(col, row).getContents();
		col++;
		String city = sheet.getCell(col, row).getContents();
		col++;
		String district = sheet.getCell(col, row).getContents();
		col++;
		String address = sheet.getCell(col, row).getContents();
		col++;
		String service = sheet.getCell(col, row).getContents();
		col++;
		String introduction = sheet.getCell(col, row).getContents();
		col++;
		String managerName = sheet.getCell(col, row).getContents();
		col++;
		String managerTel = sheet.getCell(col, row).getContents();
		col++;
		int level = (int)((NumberCell)sheet.getCell(col, row)).getValue();
		col++;
		double score = ((NumberCell)sheet.getCell(col, row)).getValue();
		col++;
		String totalEnterprise = sheet.getCell(col, row).getContents();
		String[] temp = totalEnterprise.split(";");
		ArrayList<String> enterprise = new ArrayList<String>();
		for (String anEnterprise: temp) {
			enterprise.add(anEnterprise);
		}
		return new HotelPO(ID,password,name,address,district,city,level,score,service,introduction,managerName,managerTel,enterprise);
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

	private void out(){
		try {
			wBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		book.close();
	}
//	private boolean add(int col, int row, HotelPO hotel){
//		return true;
//	}
//
//	private ArrayList<HotelPO> getHotelListByRow(int row){
//
//	}
//
//	private int findLocation(int row, double score, int level){
//		ArrayList<HotelPO> hotelTree = getHotelListByRow(row);
//		for(int i=1;i<hotelTree.size();){
//			double theSocre = hotelTree.get(i).getScore();
//			double nextLeftScore = hotelTree.get(2*i).getScore();
//			double nextRightScore = hotelTree.get(2*i+1).getScore();
//			if(score>theSocre) {
//				if(score<nextRightScore)i=2*i+1;
//			}
//			if(score<theSocre) i=2*i;
//			if(score==theSocre){
//				if(level>hotelTree.get(i).getLevel()){
//					i=2*i+1;
//				}
//				else{
//					i=2*i;
//				}
//			}
//		}
//	}

}
