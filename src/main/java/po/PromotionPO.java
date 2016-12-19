package po;

import helper.PromotionType;
import helper.SaleType;

import java.io.Serializable;
import java.util.Date;

/**
 * 存储Promotion信息的PO类
 * @author CROFF
 * @version 2016-12-7
 */
public class PromotionPO implements Serializable {
	
	private String promotionID;	//营销策略编号
	private String promotionName;	//营销策略名称
	private PromotionType promotionType;	//营销策略的价格优惠类型，分为满减和折扣
	private String relatedHotelID;  //酒店营销策略则为酒店ID，网站营销策略则为null
	private SaleType saleType;	//营销策略判断条件的类型，分为特定日期、生日、订房数量、合作企业、优惠商圈、会员等级
	
	private Date startDate;	//起始日期
	private Date endDate;	//结束日期
	
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
//		saleType = SaleType.Date;
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
//		saleType = SaleType.Date;
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
	 * @param discount 折扣
	 * @param neededPrice 需要满足的金额
	 * @param reducePrice 减少的金额
	 */
	public void setDatePromotion(Date startDate, Date endDate, double discount, double neededPrice, double reducePrice) {
		saleType = SaleType.Date;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discount = discount;
		this.neededPrice = neededPrice;
		this.reducePrice = reducePrice;
	}
	
	/**
	 * 将判断条件设置为生日特惠
	 * @param discount 折扣
	 * @param neededPrice 需要满足的金额
	 * @param reducePrice 减少的金额
	 */
	public void setBirthdayPromotion(double discount, double neededPrice, double reducePrice) {
		saleType = SaleType.Birthday;
		this.discount = discount;
		this.neededPrice = neededPrice;
		this.reducePrice = reducePrice;
		
	}
	
	/**
	 * 将判断条件设置为订房数量优惠
	 * @param numberOfRoom 最低订房数量
	 * @param discount 折扣
	 * @param neededPrice 需要满足的金额
	 * @param reducePrice 减少的金额
	 */
	public void setRoomNumberPromotion(int numberOfRoom, double discount, double neededPrice, double reducePrice) {
		saleType = SaleType.RoomNumber;
		this.numberOfRoom = numberOfRoom;
		this.discount = discount;
		this.neededPrice = neededPrice;
		this.reducePrice = reducePrice;
	}
	
	/**
	 * 将判断条件设置为合作企业优惠
	 * @param enterprise 合作企业名称
	 * @param discount 折扣
	 * @param neededPrice 需要满足的金额
	 * @param reducePrice 减少的金额
	 */
	public void setEnterprisePromotion(String enterprise, double discount, double neededPrice, double reducePrice) {
		saleType = SaleType.Enterprise;
		this.enterprise = enterprise;
		this.discount = discount;
		this.neededPrice = neededPrice;
		this.reducePrice = reducePrice;
	}
	
	/**
	 * 将判断条件设置为特定商圈优惠
	 * @param district 优惠商圈名称
	 * @param discount 折扣
	 * @param neededPrice 需要满足的金额
	 * @param reducePrice 减少的金额
	 */
	public void setDistrictPromotion(String district, double discount, double neededPrice, double reducePrice) {
		saleType = SaleType.District;
		this.district = district;
		this.discount = discount;
		this.neededPrice = neededPrice;
		this.reducePrice = reducePrice;
	}
	
	/**
	 * 计算使用优惠后的价格
	 * @param originalPrice 原价
	 * @return 优惠价
	 */
	public double calculatePrice(double originalPrice) {
		if(promotionType==PromotionType.Discount) {
			return originalPrice*discount;
		} else {
			if(originalPrice>=neededPrice) {
				return originalPrice-reducePrice;
			} else {
				return originalPrice;
			}
		}
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
