package po;

import helper.PromotionType;
import helper.SaleType;

import java.util.Date;

public class PromotionPO {
	
	private String promotionID;
	private String promotionName;
	private PromotionType promotionType;
	private SaleType saleType;
	
	private Date startDate;
	private Date endDate;
	
	private Date birthday;
	
	private int numberOfRoom;
	
	private String enterprise;
	
	private int level;
	
	private String district;
	
	private double discount;
	private double neededPrice;
	private double reducePrice;
	
	/**
	 * 一般营销策略的构造方法
	 * @param promotionID
	 * @param promotionName
	 * @param promotionType
	 */
	public PromotionPO(String promotionID, String promotionName, PromotionType promotionType) {
		this.promotionID = promotionID;
		this.promotionName = promotionName;
		this.promotionType = promotionType;
	}
	
	public void setRankPromotion() {
		saleType = SaleType.Rank;
	}
	
	public void setDatePromotion(Date startDate, Date endDate) {
		saleType = SaleType.Date;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public void setBirthdayPromotion(Date birthday) {
		saleType = SaleType.Birthday;
		this.birthday = birthday;
	}
	
	public void setRoomNumberPromotion() {
		saleType = SaleType.RoomNumber;
	}
	
	public void setEnterprisePromotion(String enterprise) {
		saleType = SaleType.Enterprise;
		this.enterprise = enterprise;
	}
	
	public void setDistrictPromotion(String district) {
		saleType = SaleType.District;
	}
	
	public boolean checkAvailable(MemberPO memberVO) {
		return false;
	}
	
	public double calculatePrice(double originalPrice) {
		return 0;
	}
	
	public String getPromotionID() {
		return promotionID;
	}
	
	public void setPromotionID(String promotionID) {
		this.promotionID = promotionID;
	}
	
	public String getPromotionName() {
		return promotionName;
	}
	
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	
	public PromotionType getPromotionType() {
		return promotionType;
	}
	
	public void setPromotionType(PromotionType promotionType) {
		this.promotionType = promotionType;
	}
	
	public SaleType getSaleType() {
		return saleType;
	}
	
	public void setSaleType(SaleType saleType) {
		this.saleType = saleType;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public int getNumberOfRoom() {
		return numberOfRoom;
	}
	
	public void setNumberOfRoom(int numberOfRoom) {
		this.numberOfRoom = numberOfRoom;
	}
	
	public String getEnterprise() {
		return enterprise;
	}
	
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public double getNeededPrice() {
		return neededPrice;
	}
	
	public void setNeededPrice(double neededPrice) {
		this.neededPrice = neededPrice;
	}
	
	public double getReducePrice() {
		return reducePrice;
	}
	
	public void setReducePrice(double reducePrice) {
		this.reducePrice = reducePrice;
	}
}
