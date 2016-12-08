package po;

import  helper.PromotionType;
import helper.SaleType;

import java.util.Date;

/**
 * 存储Promotion信息的PO类
 * @author CROFF
 * @version 2016-12-1
 */
public class PromotionPO {
	
	private String promotionID;	//营销策略编号
	private String promotionName;	//营销策略名称
	private PromotionType promotionType;	//营销策略的价格优惠类型，分为满减和折扣
	private String relatedHotelID;  //酒店营销策略则为酒店ID，网站营销策略则为null
	private SaleType saleType;	//营销策略判断条件的类型，分为特定日期、生日、订房数量、合作企业、优惠商圈、会员等级
	
	private Date startDate;	//起始日期
	private Date endDate;	//结束日期
	
	private Date birthday;	//生日
	
	private int numberOfRoom;	//订房数量
	
	private String enterprise;	//合作企业名称
	
	private String district;	//优惠商圈名称
	
	private double discount;	//享受的折扣
	private double neededPrice;	//减价需要满足的价格
	private double reducePrice;	//减少的价格
	
	/**
	 * 网站营销策略的构造方法
	 * @param promotionID 营销策略编号
	 * @param promotionName 营销策略名称
	 * @param promotionType 营销策略的价格优惠类型，分为满减和折扣
	 */
	public PromotionPO(String promotionID, String promotionName, PromotionType promotionType) {
		this.promotionID = promotionID;
		this.promotionName = promotionName;
		this.promotionType = promotionType;
	}
	
	/**
	 * 酒店营销策略的构造方法
	 * @param promotionID 营销策略编号
	 * @param promotionName 营销策略名称
	 * @param promotionType 营销策略的价格优惠类型，分为满减和折扣
	 * @param relatedHotelID 对应酒店ID
	 */
	public PromotionPO(String promotionID, String promotionName, PromotionType promotionType, String relatedHotelID) {
		this.promotionID = promotionID;
		this.promotionName = promotionName;
		this.promotionType = promotionType;
		this.relatedHotelID = relatedHotelID;
	}
	
	/**
	 * 将判断条件设置为会员等级优惠
	 */
	public void setRankPromotion() {
		saleType = SaleType.Rank;
	}
	
	/**
	 * 将判断条件设置为特定日期优惠
	 * @param startDate 起始日期
	 * @param endDate 结束日期
	 */
	public void setDatePromotion(Date startDate, Date endDate) {
		saleType = SaleType.Date;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * 将判断条件设置为生日特惠
	 * @param birthday 客户的生日
	 */
	public void setBirthdayPromotion(Date birthday) {
		saleType = SaleType.Birthday;
		this.birthday = birthday;
	}
	
	/**
	 * 将判断条件设置为订房数量优惠
	 * @param numberOfRoom 最低订房数量
	 */
	public void setRoomNumberPromotion(int numberOfRoom) {
		saleType = SaleType.RoomNumber;
		this.numberOfRoom = numberOfRoom;
	}
	
	/**
	 * 将判断条件设置为合作企业优惠
	 * @param enterprise 合作企业名称
	 */
	public void setEnterprisePromotion(String enterprise) {
		saleType = SaleType.Enterprise;
		this.enterprise = enterprise;
	}
	
	/**
	 * 将判断条件设置为特定商圈优惠
	 * @param district 优惠商圈名称
	 */
	public void setDistrictPromotion(String district) {
		saleType = SaleType.District;
	}
	
	/**
	 * 检查某客户是否符合优惠条件
	 * @param memberPO 客户信息
	 * @return 符合则为true，否则为false
	 */
	public boolean checkAvailable(MemberPO memberPO) {
		return false;
	}
	
	/**
	 * 计算使用优惠后的价格
	 * @param originalPrice 原价
	 * @return 优惠价
	 */
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
	
	public String getRelatedHotelID() {
		return relatedHotelID;
	}
	
	public void setRelatedHotelID(String relatedHotelID) {
		this.relatedHotelID = relatedHotelID;
	}
}
