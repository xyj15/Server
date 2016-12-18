package data.implementation;

import data.service.PromotionDataService;
import helper.PromotionType;
import helper.SaleType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import po.PromotionPO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apple on 2016/12/2.
 */
public class PromotionData implements PromotionDataService {

	private int lengthOfID = 5;
	private String sourceFile = "PromotionData.xls";
	private Workbook book;
	private Sheet sheet;
	private WritableWorkbook wBook;
	private WritableSheet wSheet;

	public PromotionPO getPromotion(String promotionID) {
		createSheet();
		int col = 0;
		int row = hash(promotionID);
		if(row>=sheet.getRows()||col>=sheet.getColumns()){
			book.close();
			return null;
		}
		if(!sheet.getCell(col, row).getContents().equals(promotionID)){
			book.close();
			return null;
		}
		return getPromotionByRow(row);
	}

	public boolean addPromotion(PromotionPO promotion) {
		createWritableSheet();
		int col = 0;
		int row = hash(promotion.getPromotionID());
		if(wSheet.getCell(col, row).getContents()!=""&&!wSheet.getCell(col, row).getContents().equals("-1")){
			close();
			return false;  //The promotion with the same ID has already existed.
		}
		Label promotionID = new Label(col, row, promotion.getPromotionID());
		col++;
		Label name = new Label(col, row, promotion.getPromotionName());
		col++;
		Label relatedHotelID = new Label(col, row, promotion.getRelatedHotelID());
		col++;
		Number saleType = new Number(col, row, promotion.getSaleType().getValue());
		col++;
		switch (promotion.getSaleType().getValue()){
			case 1: {
				Number startDate = new Number(col, row, promotion.getStartDate().getTime());
				col++;
				Number endDate = new Number(col, row, promotion.getEndDate().getTime());
				col++;
				try {
					wSheet.addCell(startDate);
					wSheet.addCell(endDate);
				} catch (WriteException e) {
					e.printStackTrace();
				}
				break;
			}
			case 3: {
				Number numberOfRoom = new Number(col, row,promotion.getNumberOfRoom());
				col++;
				try {
					wSheet.addCell(numberOfRoom);
				} catch (WriteException e) {
					e.printStackTrace();
				}
				break;
			}
			case 4: {
				Label enterprise = new Label(col, row, promotion.getEnterprise());
				col++;
				try {
					wSheet.addCell(enterprise);
				} catch (WriteException e) {
					e.printStackTrace();
				}
				break;
			}
			case 5: {
				Label district = new Label(col, row, promotion.getDistrict());
				col++;
				try {
					wSheet.addCell(district);
				} catch (WriteException e) {
					e.printStackTrace();
				}
				break;
			}
			default:
				break;
		}
		Number promotionType = new Number(col, row, promotion.getPromotionType().getValue());
		col++;
		switch (promotion.getPromotionType().getValue()){
			case 0:{
				Number discount = new Number(col, row, promotion.getDiscount());
				col++;
				try {
					wSheet.addCell(discount);
				} catch (WriteException e) {
					e.printStackTrace();
				}
				break;
			}
			case 1: {
				Number neededPrice = new Number(col, row, promotion.getNeededPrice());
				col++;
				Number reducePrice = new Number(col, row, promotion.getReducePrice());
				col++;
				try {
					wSheet.addCell(neededPrice);
					wSheet.addCell(reducePrice);
				} catch (WriteException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		try {
			wSheet.addCell(saleType);
			wSheet.addCell(promotionType);
			wSheet.addCell(promotionID);
			wSheet.addCell(name);
			wSheet.addCell(relatedHotelID);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	public boolean deletePromotion(String promotionID) {
		createWritableSheet();
		int col = 0;
		int row = hash(promotionID);
		if(!wSheet.getCell(col, row).getContents().equals(promotionID)){
			close();
			return false;  //The promotion with the ID of promotionID does not exist.
		}
		Label delete = new Label(col, row, "-1");
		try {
			wSheet.addCell(delete);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	public boolean updatePromotion(PromotionPO promotion) {
		createWritableSheet();
		int col = 0;
		int row = hash(promotion.getPromotionID());
		if(!wSheet.getCell(col, row).getContents().equals(promotion.getPromotionID())){
			close();
			return false;  //The promotion with the ID of promotionID does not exist.
		}
		col++;
		Label name = new Label(col, row, promotion.getPromotionName());
		col++;
		Label relatedHotelID = new Label(col, row, promotion.getRelatedHotelID());
		col++;
		Number saleType = new Number(col, row, promotion.getSaleType().getValue());
		col++;
		switch (promotion.getSaleType().getValue()){
			case 1: {
				Number startDate = new Number(col, row, promotion.getStartDate().getTime());
				col++;
				Number endDate = new Number(col, row, promotion.getEndDate().getTime());
				col++;
				try {
					wSheet.addCell(startDate);
					wSheet.addCell(endDate);
				} catch (WriteException e) {
					e.printStackTrace();
				}
				break;
			}
			case 3: {
				Number numberOfRoom = new Number(col, row,promotion.getNumberOfRoom());
				col++;
				try {
					wSheet.addCell(numberOfRoom);
				} catch (WriteException e) {
					e.printStackTrace();
				}
				break;
			}
			case 4: {
				Label enterprise = new Label(col, row, promotion.getEnterprise());
				col++;
				try {
					wSheet.addCell(enterprise);
				} catch (WriteException e) {
					e.printStackTrace();
				}
				break;
			}
			case 5: {
				Label district = new Label(col, row, promotion.getDistrict());
				col++;
				try {
					wSheet.addCell(district);
				} catch (WriteException e) {
					e.printStackTrace();
				}
				break;
			}
			default:
				break;
		}
		Number promotionType = new Number(col, row, promotion.getPromotionType().getValue());
		col++;
		switch (promotion.getPromotionType().getValue()){
			case 0:{
				Number discount = new Number(col, row, promotion.getDiscount());
				col++;
				try {
					wSheet.addCell(discount);
				} catch (WriteException e) {
					e.printStackTrace();
				}
				break;
			}
			case 1: {
				Number neededPrice = new Number(col, row, promotion.getNeededPrice());
				col++;
				Number reducePrice = new Number(col, row, promotion.getReducePrice());
				col++;
				try {
					wSheet.addCell(neededPrice);
					wSheet.addCell(reducePrice);
				} catch (WriteException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		try {
			wSheet.addCell(saleType);
			wSheet.addCell(promotionType);
			wSheet.addCell(name);
			wSheet.addCell(relatedHotelID);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		close();
		return true;
	}

	public ArrayList<PromotionPO> getPromotionList() {
		createSheet();
		int rows = sheet.getRows();
		System.err.println(rows);
		ArrayList<PromotionPO> result = new ArrayList<PromotionPO>();
		for (int i = 0; i < rows; i++) {
			PromotionPO temp = getPromotionByRow(i);
			if(temp!=null){
				result.add(temp);
			}
		}
		book.close();
		if(result.size()==0){
			return null;  //There is no promotion.
		}
		return result;
	}

	/**
	 *
	 * @return
	 */
	public String getAvailablePromotionID() {
		createSheet();
		long rows = sheet.getRows();
		if(rows>99999) return null;    //The space for saving the information of Members has been full.
		String ID = rows+"";
		while(ID.length()<lengthOfID){
			ID = '0'+ID;
		}
		book.close();
		return ID;
	}

	/**
	 *
	 */
	private void close() {
		try {
			wBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	private int hash(String ID){
		int hashResult = Integer.parseInt(ID);
		return hashResult;
	}

	private PromotionPO getPromotionByRow(int row){
		int col = 0;
		Date startDate = null;
		Date endDate = null;
		int numberOfRoom = 0;
		String enterprise = null;
		String district = null;
		double discount = 0;
		double neededPrice = 0;
		double reducePrice = 0;
		String promotionID = sheet.getCell(col, row).getContents();
		col++;
		String promotionName = sheet.getCell(col, row).getContents();
		col++;
		String relatedHotelID = sheet.getCell(col, row).getContents();
		if(relatedHotelID=="") relatedHotelID=null;
		col++;
		int sType = (int)((NumberCell) sheet.getCell(col, row)).getValue();
		col++;
		SaleType saleType = null;
		switch (sType){
			case 0: {
				saleType = SaleType.Rank;
			} break;
			case 1: {
				saleType = SaleType.Date;
				long dateHelper = (long)((NumberCell)sheet.getCell(col, row)).getValue();
				startDate = new Date(dateHelper);
				col++;
				dateHelper = (long)((NumberCell)sheet.getCell(col, row)).getValue();
				endDate = new Date(dateHelper);
				col++;
			} break;
			case 2: saleType = SaleType.Birthday; break;
			case 3: {
				saleType = SaleType.RoomNumber;
				numberOfRoom = (int)((NumberCell) sheet.getCell(col, row)).getValue();
				col++;
			} break;
			case 4: {
				saleType = SaleType.Enterprise;
				enterprise = sheet.getCell(col, row).getContents();
				col++;
			} break;
			case 5: {
				saleType = SaleType.District;
				district = sheet.getCell(col, row).getContents();
				col++;
			} break;
			default: break;
		}
		int pType = (int)((NumberCell) sheet.getCell(col, row)).getValue();
		col++;
		PromotionType promotionType = null;
		switch (pType){
			case 0: {
				promotionType = PromotionType.Discount;
				discount = ((NumberCell) sheet.getCell(col, row)).getValue();
				col++;
			} break;
			case 1: {
				promotionType = PromotionType.Reduce;
				neededPrice = ((NumberCell) sheet.getCell(col, row)).getValue();
				col++;
				reducePrice = ((NumberCell) sheet.getCell(col, row)).getValue();
				col++;
			} break;
		}

		PromotionPO result = new PromotionPO(promotionID,promotionName,promotionType,relatedHotelID);
		result.setEnterprise(enterprise);
		result.setDistrict(district);
		result.setStartDate(startDate);
		result.setEndDate(endDate);
		result.setNumberOfRoom(numberOfRoom);
		result.setDiscount(discount);
		result.setNeededPrice(neededPrice);
		result.setReducePrice(reducePrice);
		result.setSaleType(saleType);
		return result;
	}

}
