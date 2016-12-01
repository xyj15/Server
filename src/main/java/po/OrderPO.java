package po;

import helper.OrderStatus;

import java.util.Date;

public class OrderPO {
	
	private String memberID;	//订单相关客户
	private String hotelID;	//订单相关酒店
	
	private String orderID;	//订单编号
	private OrderStatus orderStatus;	//订单状态
	private Date createTime;	//订单创建时间
	private Date checkinTime; 	//预计入住时间
	private Date actualCheckinTime;	//实际入住时间(构造函数中没有)
	private Date latestCheckinTime;	//最晚入住时间
	private Date checkoutTime;	//预计退房时间
	private Date actualCheckoutTime;	//实际退房时间(构造函数中没有)
	private int roomNUM;  // 订单中房间数量
	private String roomName;  // 订单中房间类型
	
	private int numberOfClient;	//预计入住人数
	private boolean hasKids;	//有无儿童
	private double score;	//订单评分(构造函数中没有)
	private String evaluation;	//订单评价(构造函数中没有)
	
	private double recover;	//撤销异常时返回的信用比例，若无则为0
	
	//private PromotionPO promotion;
	private String promotionID;// 使用的促销策略
	private double price;	//订单价值
	
	public OrderPO(String memberID, String hotelID, String orderID, OrderStatus orderStatus, Date createTime, Date checkinTime, Date latestCheckinTime, Date checkoutTime, int roomNUM, String roomName, int numberOfClient, boolean hasKids, double recover, String promotionID, double price) {

		this.memberID = memberID;
		this.hotelID = hotelID;
		this.orderID = orderID;
		this.orderStatus = orderStatus;
		this.createTime = createTime;
		this.checkinTime = checkinTime;
		this.latestCheckinTime = latestCheckinTime;
		this.checkoutTime = checkoutTime;
		this.roomNUM = roomNUM;
		this.roomName = roomName;
		this.numberOfClient = numberOfClient;
		this.hasKids = hasKids;
		this.recover = recover;
		this.promotionID = promotionID;
		this.price = price;
	}


	public String getMemberID(){
		return memberID;
	}

	public String getHotelID(){
		return hotelID;
	}

	public String getOrderID(){
		return orderID;
	}

	public String getPromotionID() {
		return promotionID;
	}

	public OrderStatus getOrderStatus(){
		return orderStatus;
	}

	public Date getCheckinTime(){
		return checkinTime;
	}

	public Date getCheckoutTime(){
		return checkoutTime;
	}

	public Date getActualCheckinTime() {
		return actualCheckinTime;
	}

	public Date getActualCheckoutTime() {
		return actualCheckoutTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getLatestCheckinTime() {
		return latestCheckinTime;
	}

	public double getPrice() {
		return price;
	}

	public double getScore() {
		return score;
	}

	public double getRecover() {
		return recover;
	}

	public String getRoomName() {
		return roomName;
	}

	public int getRoomNUM() {
		return roomNUM;
	}

	public int getNumberOfClient() {
		return numberOfClient;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public boolean isHasKids() {
		return hasKids;
	}

	public void setActualCheckinTime(Date actualCheckinTime) {
		this.actualCheckinTime = actualCheckinTime;
	}

	public void setActualCheckoutTime(Date actualCheckoutTime) {
		this.actualCheckoutTime = actualCheckoutTime;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public void setRecover(double recover) {
		this.recover = recover;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}
