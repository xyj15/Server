package data.implementation;

import data.dataservice.SearchDataService;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;
import po.HotelPO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by apple on 2016/12/5.
 */
public class SearchData implements SearchDataService {
	private String sourceFile = "HotelData.xls";
	private Workbook book;
	private Sheet wSheet;

	public SearchData(){
		try {
			book = Workbook.getWorkbook(new File(sourceFile));
			wSheet = book.getSheet(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
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
	public void close() {
		book.close();
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
}
