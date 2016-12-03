package data.implementation;

import data.dataservice.HotelDataService;
import jxl.Cell;
import jxl.NumberCell;
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
	private WritableWorkbook wBook;
	private WritableSheet wSheet;
	private static long sumOfHotel = 0;

	public HotelData(){
		try {
			book = Workbook.getWorkbook(new File(sourceFile));
			wBook = Workbook.createWorkbook(new File(sourceFile),book);
			wSheet = wBook.getSheet(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}

	}


	/**
	 *
	 * @param hotel
	 * @return
	 */
	public boolean addHotel(HotelPO hotel) {
		int col = 0;
		int row = hash(hotel.getCity()+hotel.getDistrict());
		while(wSheet.getCell(col, row).getContents()!=""){
			if(wSheet.getCell(col, row).getContents().equals(hotel.getUserID())){
				return false;     // the hotel with the same ID has already existed
			}
			col+=dataSize;
		}
		Label ID = new Label(col, row, hotel.getUserID());
		col++;
		Label password = new Label(col, row, hotel.getPassword());
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

		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sumOfHotel++;
		return true;
	}

	/**
	 *
	 * @param hotelID
	 * @return
	 */
	public boolean deleteHotel(String hotelID) {
		int col = 0;
		int row = hash(hotelID);
		while(!wSheet.getCell(col, row).getContents().equals(hotelID)){
			if(wSheet.getCell(col, row).getContents().equals("")){
				return false;                         //The hotel with ID of hotelID does not exist.
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

	/**
	 *
	 * @param hotel
	 * @return
	 */
	public boolean updateHotel(HotelPO hotel) {
		int col = 0;
		int row = hash(hotel.getUserID());
		while(!wSheet.getCell(col, row).getContents().equals(hotel.getUserID())){
			if(wSheet.getCell(col, row).getContents().equals("")){
				return false;                         //The hotel with ID of hotelID does not exist.
			}
			col+=dataSize;
		}
		Label ID = new Label(col, row, hotel.getUserID());
		col++;
		Label password = new Label(col, row, hotel.getPassword());
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

		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 *
	 * @param hotelID
	 * @return
	 */
	public HotelPO getHotelByID(String hotelID) {
		Cell hotelStart = wSheet.findCell(hotelID);
		int col = hotelStart.getColumn();
		int row = hotelStart.getRow();
		return getHotelByPosition(col, row);
	}

	/**
	 *
	 * @param hotelName
	 * @return
	 */
	public HotelPO getHotelByName(String hotelName) {
		Cell hotelStart = wSheet.findCell(hotelName);
		int col = hotelStart.getColumn()-2;
		int row = hotelStart.getRow();
		return getHotelByPosition(col, row);
	}

	/**
	 *
	 * @param city
	 * @param district
	 * @return
	 */
	public ArrayList<HotelPO> getHotelListByCityDistrict(String city, String district) {
		int col = 0;
		int row = hash(city+district);
		ArrayList<HotelPO> result = new ArrayList<HotelPO>();
		while(wSheet.getCell(col, row).getContents()!=""){
			result.add(getHotelByPosition(col, row));
		}
		if(result.size()==0) return null;       //There is no hotel sighed in the district of the city.
		return result;
	}

	/**
	 *
	 * @param lowScore
	 * @param highScore
	 * @param city
	 * @param district
	 * @return
	 */
	public ArrayList<HotelPO> getHotelListSortedByScore(double lowScore, double highScore, String city, String district) {
		ArrayList<HotelPO> result = getHotelListByCityDistrict(city, district);
		for (HotelPO thisHotel: result) {
			if(thisHotel.getScore()<lowScore||thisHotel.getScore()>highScore){
				result.remove(thisHotel);
			}
		}
		return result;
	}

	/**
	 *
	 * @param level
	 * @param city
	 * @param district
	 * @return
	 */
	public ArrayList<HotelPO> getHotelListFilteredByLevel(int level, String city, String district) {
		ArrayList<HotelPO> result = getHotelListByCityDistrict(city, district);
		for (HotelPO thisHotel: result) {
			if(thisHotel.getLevel()!=level){
				result.remove(thisHotel);
			}
		}
		return result;
	}

	/**
	 *
	 * @param lowPrice
	 * @param highPrice
	 * @param city
	 * @param district
	 * @return
	 */
	public ArrayList<HotelPO> getHotelListFilteredByPrice(double lowPrice, double highPrice, String city, String district) {
		return null;
	}

	/**
	 *
	 * @return
	 */
	public String getAvailableID() {
		if(sumOfHotel>999999) return null;   //The space for saving the information of Hotels has been full.
		String ID = sumOfHotel+1+"";
		while(ID.length()<lengthOfID){
			ID = '0'+ID;
		}
		return ID;
	}

	/**
	 *
	 * @param hotelScope
	 * @return
	 */
	private int hash(String hotelScope){
		int result = hotelScope.hashCode();
		result%=11;
		return result;
	}

	/**
	 *
	 * @param col
	 * @param row
	 * @return
	 */
	private HotelPO getHotelByPosition(int col, int row){
		String ID = wSheet.getCell(col, row).getContents();
		col++;
		String password = wSheet.getCell(col, row).getContents();
		col++;
		String name = wSheet.getCell(col, row).getContents();
		col++;
		String city = wSheet.getCell(col, row).getContents();
		col++;
		String district = wSheet.getCell(col, row).getContents();
		col++;
		String address = wSheet.getCell(col, row).getContents();
		col++;
		String service = wSheet.getCell(col, row).getContents();
		col++;
		String introduction = wSheet.getCell(col, row).getContents();
		col++;
		String managerName = wSheet.getCell(col, row).getContents();
		col++;
		String managerTel = wSheet.getCell(col, row).getContents();
		col++;
		int level = (int)((NumberCell)wSheet.getCell(col, row)).getValue();
		col++;
		double score = ((NumberCell)wSheet.getCell(col, row)).getValue();
		col++;
		String totalEnterprise = wSheet.getCell(col, row).getContents();
		String[] temp = totalEnterprise.split(";");
		ArrayList<String> enterprise = new ArrayList<String>();
		for (String anEnterprise: temp) {
			enterprise.add(anEnterprise);
		}
		return new HotelPO(ID,password,name,address,district,city,level,score,service,introduction,managerName,managerTel,enterprise);
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
