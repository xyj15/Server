package data.implementation;

import data.dataservice.PromotionDataService;
import helper.PromotionType;
import helper.SaleType;
import jxl.DateCell;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import po.PromotionPO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apple on 2016/12/2.
 */
public class PromotionData implements PromotionDataService {

	private int dataSize = 14;
	int lengthOfID = 5;
	private String sourceFile = "PromotionData.xls";
	private Workbook book;
	private WritableWorkbook wBook;
	private WritableSheet wSheet;

	public PromotionPO getPromotion(String promotionID) {
		int col = 0;
		int row = hash(promotionID);
		while(!wSheet.getCell(col, row).getContents().equals(promotionID)){
			if(wSheet.getCell(col, row).getContents()==""){
				return null;  //The promotion with the ID of promotionID does not exist.
			}
			col+=dataSize;
		}
		col++;
		String promotionName = wSheet.getCell(col, row).getContents();
		col++;
		String relatedHotelID = wSheet.getCell(col, row).getContents();
		col++;
		String enterprise = wSheet.getCell(col, row).getContents();
		col++;
		String district = wSheet.getCell(col, row).getContents();
		col++;
		Date startDate = ((DateCell) wSheet.getCell(col, row)).getDate();
		col++;
		Date endDate = ((DateCell) wSheet.getCell(col, row)).getDate();
		col++;
		Date birthday = ((DateCell) wSheet.getCell(col, row)).getDate();
		col++;
		int numberOfRoom = (int)((Number) wSheet.getCell(col, row)).getValue();
		col++;
		int level = (int)((Number) wSheet.getCell(col, row)).getValue();
		col++;
		double discount = ((Number) wSheet.getCell(col, row)).getValue();
		col++;
		double neededPrice = ((Number) wSheet.getCell(col, row)).getValue();
		col++;
		double reducePrice = ((Number) wSheet.getCell(col, row)).getValue();
		col++;
		int pType = (int)((Number) wSheet.getCell(col, row)).getValue();
		PromotionType promotionType = null;
		switch (pType){
			case 0: promotionType = PromotionType.Discount; break;
			case 1: promotionType = PromotionType.Reduce; break;
		}
		col++;
		int sType = (int)((Number) wSheet.getCell(col, row)).getValue();
		SaleType saleType = null;
		switch (sType){
			case 0: saleType = SaleType.Rank; break;
			case 1: saleType = SaleType.Date; break;
			case 2: saleType = SaleType.Birthday; break;
			case 3: saleType = SaleType.RoomNumber; break;
			case 4: saleType = SaleType.Enterprise; break;
			case 5: saleType = SaleType.District; break;
		}
		PromotionPO result = new PromotionPO(promotionID,promotionName,promotionType,relatedHotelID);
		result.setEnterprise(enterprise);
		result.setDistrict(district);
		result.setStartDate(startDate);
		result.setEndDate(endDate);
		result.setBirthday(birthday);
		result.setNumberOfRoom(numberOfRoom);
		//result.setLevel(level);
		result.setDiscount(discount);
		result.setNeededPrice(neededPrice);
		result.setReducePrice(reducePrice);
		result.setSaleType(saleType);
		return result;
	}

	public boolean addPromotion(PromotionPO promotion) {
		int col = 0;
		int row = hash(promotion.getPromotionID());
		while(wSheet.getCell(col, row).getContents()!=""){
			if(wSheet.getCell(col, row).getContents().equals(promotion.getPromotionID())){
				return false;  //The promotion with the same ID has already existed.
			}
			col+=dataSize;
		}
		Label promotionID = new Label(col, row, promotion.getPromotionID());
		col++;
		Label name = new Label(col, row, promotion.getPromotionName());
		col++;
		Label relatedHotelID = new Label(col, row, promotion.getRelatedHotelID());
		col++;
		Label district = new Label(col, row, promotion.getDistrict());
		col++;
		Label enterprise = new Label(col, row, promotion.getEnterprise());
		col++;
		DateTime startDate = new DateTime(col, row, promotion.getStartDate());
		col++;
		DateTime endDate = new DateTime(col, row, promotion.getEndDate());
		col++;
		DateTime birthday = new DateTime(col, row, promotion.getBirthday());
		col++;
		Number numberOfRoom = new Number(col, row,promotion.getNumberOfRoom());
		col++;
		Number discount = new Number(col, row, promotion.getDiscount());
		col++;
		Number neededPrice = new Number(col, row, promotion.getNeededPrice());
		col++;
		Number reducePrice = new Number(col, row, promotion.getReducePrice());
		col++;
		Number promotionType = new Number(col, row, promotion.getPromotionType().getValue());
		col++;
		Number saleType = new Number(col, row, promotion.getSaleType().getValue());

		try {
			wSheet.addCell(promotionID);
			wSheet.addCell(name);
			wSheet.addCell(relatedHotelID);
			wSheet.addCell(district);
			wSheet.addCell(enterprise);
			wSheet.addCell(startDate);
			wSheet.addCell(endDate);
			wSheet.addCell(birthday);
			wSheet.addCell(numberOfRoom);
			wSheet.addCell(discount);
			wSheet.addCell(neededPrice);
			wSheet.addCell(reducePrice);
			wSheet.addCell(promotionType);
			wSheet.addCell(saleType);
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

	public boolean deletePromotion(String promotionID) {
		int col = 0;
		int row = hash(promotionID);
		while(!wSheet.getCell(col, row).getContents().equals(promotionID)){
			if(wSheet.getCell(col, row).getContents()==""){
				return false;  //The promotion with the ID of promotionID does not exist.
			}
			col+=dataSize;
		}
		for (int i=0;i<dataSize;i++){
			Label delete = new Label(col+i, row, "");
			try {
				wSheet.addCell(delete);
			} catch (WriteException e) {
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

	public boolean updatePromotion(PromotionPO promotion) {
		int col = 0;
		int row = hash(promotion.getPromotionID());
		while(!wSheet.getCell(col, row).getContents().equals(promotion.getPromotionID())){
			if(wSheet.getCell(col, row).getContents()==""){
				return false;  //The promotion with the ID of promotionID does not exist.
			}
			col+=dataSize;
		}
		col++;
		Label name = new Label(col, row, promotion.getPromotionName());
		col++;
		Label relatedHotelID = new Label(col, row, promotion.getRelatedHotelID());
		col++;
		Label district = new Label(col, row, promotion.getDistrict());
		col++;
		Label enterprise = new Label(col, row, promotion.getEnterprise());
		col++;
		DateTime startDate = new DateTime(col, row, promotion.getStartDate());
		col++;
		DateTime endDate = new DateTime(col, row, promotion.getEndDate());
		col++;
		DateTime birthday = new DateTime(col, row, promotion.getBirthday());
		col++;
		Number numberOfRoom = new Number(col, row,promotion.getNumberOfRoom());
		col++;
		Number discount = new Number(col, row, promotion.getDiscount());
		col++;
		Number neededPrice = new Number(col, row, promotion.getNeededPrice());
		col++;
		Number reducePrice = new Number(col, row, promotion.getReducePrice());
		col++;
		Number promotionType = new Number(col, row, promotion.getPromotionType().getValue());
		col++;
		Number saleType = new Number(col, row, promotion.getSaleType().getValue());

		try {
			wSheet.addCell(name);
			wSheet.addCell(relatedHotelID);
			wSheet.addCell(district);
			wSheet.addCell(enterprise);
			wSheet.addCell(startDate);
			wSheet.addCell(endDate);
			wSheet.addCell(birthday);
			wSheet.addCell(numberOfRoom);
			wSheet.addCell(discount);
			wSheet.addCell(neededPrice);
			wSheet.addCell(reducePrice);
			wSheet.addCell(promotionType);
			wSheet.addCell(saleType);
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

	public ArrayList<PromotionPO> getPromotionList() {
		int rows = wSheet.getRows();
		ArrayList<PromotionPO> result = new ArrayList<PromotionPO>();
		for (int i = 0; i < rows; i++) {
			PromotionPO temp = getPromotionByRow(i);
			if(temp!=null){
				result.add(temp);
			}
		}
		if(result.size()==0) return null;  //There is no promotion.
		return result;
	}

	/**
	 *
	 * @return
	 */
	public String getAvailableID() {
		long rows = wSheet.getRows();
		if(rows>99999) return null;    //The space for saving the information of Members has been full.
		String ID = rows+1+"";
		while(ID.length()<lengthOfID){
			ID = '0'+ID;
		}
		return ID;
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

	private int hash(String ID){
		int hashResult = Integer.parseInt(ID);
		hashResult %= 27;
		return hashResult;
	}

	private PromotionPO getPromotionByRow(int row){
		int col = 0;
		if(wSheet.getCell(col, row).getContents()=="") return null;
		String promotionID = wSheet.getCell(col, row).getContents();
		col++;
		String promotionName = wSheet.getCell(col, row).getContents();
		col++;
		String relatedHotelID = wSheet.getCell(col, row).getContents();
		col++;
		String enterprise = wSheet.getCell(col, row).getContents();
		col++;
		String district = wSheet.getCell(col, row).getContents();
		col++;
		Date startDate = ((DateCell) wSheet.getCell(col, row)).getDate();
		col++;
		Date endDate = ((DateCell) wSheet.getCell(col, row)).getDate();
		col++;
		Date birthday = ((DateCell) wSheet.getCell(col, row)).getDate();
		col++;
		int numberOfRoom = (int)((Number) wSheet.getCell(col, row)).getValue();
		col++;
		int level = (int)((Number) wSheet.getCell(col, row)).getValue();
		col++;
		double discount = ((Number) wSheet.getCell(col, row)).getValue();
		col++;
		double neededPrice = ((Number) wSheet.getCell(col, row)).getValue();
		col++;
		double reducePrice = ((Number) wSheet.getCell(col, row)).getValue();
		col++;
		int pType = (int)((Number) wSheet.getCell(col, row)).getValue();
		PromotionType promotionType = null;
		switch (pType){
			case 0: promotionType = PromotionType.Discount; break;
			case 1: promotionType = PromotionType.Reduce; break;
		}
		col++;
		int sType = (int)((Number) wSheet.getCell(col, row)).getValue();
		SaleType saleType = null;
		switch (sType){
			case 0: saleType = SaleType.Rank; break;
			case 1: saleType = SaleType.Date; break;
			case 2: saleType = SaleType.Birthday; break;
			case 3: saleType = SaleType.RoomNumber; break;
			case 4: saleType = SaleType.Enterprise; break;
			case 5: saleType = SaleType.District; break;
		}
		PromotionPO result = new PromotionPO(promotionID,promotionName,promotionType,relatedHotelID);
		result.setEnterprise(enterprise);
		result.setDistrict(district);
		result.setStartDate(startDate);
		result.setEndDate(endDate);
		result.setBirthday(birthday);
		result.setNumberOfRoom(numberOfRoom);
		result.setDiscount(discount);
		result.setNeededPrice(neededPrice);
		result.setReducePrice(reducePrice);
		result.setSaleType(saleType);
		return result;
	}

}
