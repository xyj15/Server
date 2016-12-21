package data.implementation;

import data.service.SearchDataService;
import other.Encryption;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import po.HotelPO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by apple on 2016/12/5.
 */
public class SearchData implements SearchDataService {

	private int dataSize = 14;
	private String sourceFile = "HotelData.xls";
	private Workbook book;
	private Sheet sheet;

	/**
	 *
	 * @param city
	 * @param district
	 * @return
	 */
	public ArrayList<HotelPO> getHotelListByCityDistrict(String city, String district) {
		createSheet();
		sheet = book.getSheet(0);
		int col = 0;
		int row = hash(city+district);
		ArrayList<HotelPO> result = new ArrayList<HotelPO>();
		if(row>=sheet.getRows()||col>=sheet.getColumns()){
			close();
			return null;
		}
		for (int i = 0; i < sheet.getRow(row).length; i+=dataSize) {
			if(!sheet.getCell(col+i, row).getContents().equals("-1")&&sheet.getCell(col+i, row).getContents()!=""){
				result.add(getHotelByPosition(col+i, row));
			}
		}
		close();
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
		ArrayList<HotelPO> temp = getHotelListByCityDistrict(city, district);
		ArrayList<HotelPO> result = new ArrayList<>();
		if(temp==null) return null;       //There is no hotel sighed in the district of the city.
		for (HotelPO thisHotel: temp) {
			if(thisHotel.getScore()>=lowScore&&thisHotel.getScore()<=highScore){
				result.add(thisHotel);
			}
		}
		if(result.size()==0) return null;       //There is no hotel sighed in the district of the city whose score is between lowScore and highScore.
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
		ArrayList<HotelPO> temp = getHotelListByCityDistrict(city, district);
		ArrayList<HotelPO> result = new ArrayList<>();
		if(temp==null) return null;       //There is no hotel sighed in the district of the city.
		for (HotelPO thisHotel: temp) {
			if(thisHotel.getLevel()==level){
				result.add(thisHotel);
			}
		}
//		for (int i = 0; i < result.size(); i++) {
//			if(result.get(i).getLevel()!=level){
//				result.remove(i);
//			}
//		}
		if(result.size()==0) return null;       //There is no hotel sighed in the district of the city whose level equals to "level".
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
		ArrayList<HotelPO> temp = getHotelListByCityDistrict(city, district);
		ArrayList<HotelPO> result = new ArrayList<>();
		if(temp==null) return null;       //There is no hotel sighed in the district of the city.
		RoomData rooms = new RoomData();
		for (HotelPO thisHotel: temp) {
			if(rooms.hasSuitableRoom(lowPrice, highPrice, thisHotel.getUserID())){
				result.add(thisHotel);
			}
		}
		if(result.size()==0) return null;  //There is no hotel fits the condition.
		return result;
	}
	/**
	 *
	 */
	private void close(){
		book.close();
	}


	/**
	 *
	 * @param hotelScope
	 * @return
	 */
	private int hash(String hotelScope){
		int result = hotelScope.hashCode();
		if(result<0)result = 0-result;
		result %= 10;
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
		boolean isLogged = false;
		if(((NumberCell)sheet.getCell(col, row)).getValue()==1) isLogged = true;
		col++;
		String totalEnterprise = sheet.getCell(col, row).getContents();
		String[] temp = totalEnterprise.split(";");
		ArrayList<String> enterprise = new ArrayList<String>();
		for (String anEnterprise: temp) {
			enterprise.add(anEnterprise);
		}
		return new HotelPO(ID,password,name,address,district,city,level,score,service,introduction,managerName,managerTel,enterprise, isLogged);
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

}
