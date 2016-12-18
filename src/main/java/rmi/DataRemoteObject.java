package rmi;

import data.dataservice.*;
import data.factory.OrderDataObstractFactory;
import data.factoryImpl.OrderDataConFactory;
import data.implementation.*;
import helper.RoomType;
import po.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by CROFF on 2016/12/10.
 */
public class DataRemoteObject extends UnicastRemoteObject implements CreditDataService,
		HotelDataService, ManagerDataService, MemberDataService, OrderDataObstractFactory,
		PromotionDataService, RankDataService, RoomDataService, SalerDataService, SearchDataService {
	
	private static final long serialVersionUID = 4666870661827494597L;
	private CreditDataService creditDataService;
	private HotelDataService hotelDataService;
	private ManagerDataService managerDataService;
	private MemberDataService memberDataService;
//	private OrderDataService orderDataService;
	private PromotionDataService promotionDataService;
	private RankDataService rankDataService;
	private RoomDataService roomDataService;
	private SalerDataService salerDataService;
	private SearchDataService searchDataService;
	private OrderDataObstractFactory orderDataFactory;
	
	protected DataRemoteObject() throws RemoteException {
		creditDataService = new CreditData();
		hotelDataService = new HotelData();
		managerDataService = new ManagerData();
		memberDataService = new MemberData();
//		orderDataService = ;
		promotionDataService = new PromotionData();
		rankDataService = new RankData();
		roomDataService = new RoomData();
		salerDataService = new SalerData();
		searchDataService = new SearchData();
		orderDataFactory = new OrderDataConFactory();
	}
	
	@Override
	public boolean addSaler(SalerPO saler) {
		return salerDataService.addSaler(saler);
	}
	
	@Override
	public boolean deleteSaler(String salerID) {
		return salerDataService.deleteSaler(salerID);
	}
	
	@Override
	public boolean updateSaler(SalerPO saler) {
		return salerDataService.updateSaler(saler);
	}
	
	@Override
	public SalerPO getSaler(String ID) {
		return salerDataService.getSaler(ID);
	}
	
	@Override
	public String getAvailableSalerID() {
		return salerDataService.getAvailableSalerID();
	}
	
	@Override
	public boolean addMember(MemberPO member) {
		return memberDataService.addMember(member);
	}
	
	@Override
	public boolean deleteMember(String memberID) {
		return memberDataService.deleteMember(memberID);
	}
	
	@Override
	public boolean updateMember(MemberPO member) {
		return memberDataService.updateMember(member);
	}
	
	@Override
	public MemberPO getMember(String ID) {
		return memberDataService.getMember(ID);
	}
	
	@Override
	public String getAvailableMemberID() {
		return memberDataService.getAvailableMemberID();
	}
	
//	@Override
//	public boolean addOrder(OrderPO order) {
//		return orderDataService.addOrder(order);
//	}
//
//	@Override
//	public boolean updateOrder(OrderPO order) {
//		return orderDataService.updateOrder(order);
//	}
//
//	@Override
//	public boolean cancelOrder(String orderID) {
//		return orderDataService.cancelOrder(orderID);
//	}
//
//	@Override
//	public boolean makeOrderAbnormal(String orderID) {
//		return orderDataService.makeOrderAbnormal(orderID);
//	}
//
//	@Override
//	public boolean recoverOrder(String orderID, double recover) {
//		return orderDataService.recoverOrder(orderID, recover);
//	}
//
//	@Override
//	public OrderPO getOrder(String orderID) {
//		return orderDataService.getOrder(orderID);
//	}
//
//	@Override
//	public ArrayList<OrderPO> getOrderList(String userID) {
//		return orderDataService.getOrderList(userID);
//	}
//
//	@Override
//	public ArrayList<OrderPO> getFinishedOrders(String userID) {
//		return orderDataService.getFinishedOrders(userID);
//	}
//
//	@Override
//	public ArrayList<OrderPO> getUnfinishedOrders(String userID) {
//		return orderDataService.getUnfinishedOrders(userID);
//	}
//
//	@Override
//	public ArrayList<OrderPO> getAbnormalOrders(String userID) {
//		return orderDataService.getAbnormalOrders(userID);
//	}
//
//	@Override
//	public ArrayList<OrderPO> getCancledOrders(String userID) {
//		return orderDataService.getCancledOrders(userID);
//	}
//
	@Override
	public RoomPO getSingleRoom(Date theDay, String roomNUM, String hotelID) {
		return roomDataService.getSingleRoom(theDay, roomNUM, hotelID);
	}
	
	@Override
	public boolean addSingleRoom(RoomPO room, String hotelID) {
		return roomDataService.addSingleRoom(room, hotelID);
	}
	
	@Override
	public boolean updateSingleRoom(Date theDay, RoomPO room, String hotelID) {
		return roomDataService.updateSingleRoom(theDay, room, hotelID);
	}
	
	@Override
	public boolean deleteSingleRoom(String roomNUM, String hotelID) {
		return roomDataService.deleteSingleRoom(roomNUM, hotelID);
	}
	
	@Override
	public ArrayList<RoomPO> getRoomsByTypeDate(Date day, RoomType roomType, String hotelID) {
		return roomDataService.getRoomsByTypeDate(day, roomType, hotelID);
	}
	
	@Override
	public ArrayList<RoomPO> getRoomsByNameDate(Date day, String roomName, String hotelID) {
		return roomDataService.getRoomsByNameDate(day, roomName, hotelID);
	}
	
	@Override
	public ArrayList<RoomPO> getRoomsByDate(Date day, String hotelID) {
		return roomDataService.getRoomsByDate(day, hotelID);
	}
	
	@Override
	public boolean reserveSingleRoom(Date day, String roomNUM, String hotelID) {
		return roomDataService.reserveSingleRoom(day, roomNUM, hotelID);
	}
	
	@Override
	public boolean checkIn(Date day, String roomNUM, String hotelID) {
		return roomDataService.checkIn(day, roomNUM, hotelID);
	}
	
	@Override
	public boolean checkOut(Date day, String roomNUM, String hotelID) {
		return roomDataService.checkOut(day, roomNUM, hotelID);
	}
	
	@Override
	public boolean addHotel(HotelPO hotel) {
		return hotelDataService.addHotel(hotel);
	}
	
	@Override
	public boolean deleteHotel(String hotelID) {
		return hotelDataService.deleteHotel(hotelID);
	}
	
	@Override
	public boolean updateHotel(HotelPO hotel) {
		return hotelDataService.updateHotel(hotel);
	}
	
	@Override
	public HotelPO getHotelByID(String hotelID) {
		return hotelDataService.getHotelByID(hotelID);
	}
	
	@Override
	public String getAvailableHotelID() {
		return hotelDataService.getAvailableHotelID();
	}
	
	@Override
	public ArrayList<HotelPO> getHotelListByCityDistrict(String city, String district) {
		return searchDataService.getHotelListByCityDistrict(city, district);
	}
	
	@Override
	public ArrayList<HotelPO> getHotelListSortedByScore(double lowScore, double highScore, String city, String district) {
		return searchDataService.getHotelListSortedByScore(lowScore, highScore, city, district);
	}
	
	@Override
	public ArrayList<HotelPO> getHotelListFilteredByLevel(int level, String city, String district) {
		return searchDataService.getHotelListFilteredByLevel(level, city, district);
	}
	
	@Override
	public ArrayList<HotelPO> getHotelListFilteredByPrice(double lowPrice, double highPrice, String city, String district) {
		return searchDataService.getHotelListFilteredByPrice(lowPrice, highPrice, city, district);
	}
	
	@Override
	public boolean updateManager(ManagerPO manager) {
		return managerDataService.updateManager(manager);
	}
	
	@Override
	public ManagerPO getManager() {
		return managerDataService.getManager();
	}
	
	@Override
	public PromotionPO getPromotion(String promotionID) {
		return promotionDataService.getPromotion(promotionID);
	}
	
	@Override
	public boolean addPromotion(PromotionPO promotion) {
		return promotionDataService.addPromotion(promotion);
	}
	
	@Override
	public boolean deletePromotion(String promotionID) {
		return promotionDataService.deletePromotion(promotionID);
	}
	
	@Override
	public boolean updatePromotion(PromotionPO promotion) {
		return promotionDataService.updatePromotion(promotion);
	}
	
	@Override
	public ArrayList<PromotionPO> getPromotionList() {
		return promotionDataService.getPromotionList();
	}
	
	@Override
	public String getAvailablePromotionID() {
		return promotionDataService.getAvailablePromotionID();
	}
	
	@Override
	public ArrayList<Double> getCreditList() {
		return rankDataService.getCreditList();
	}
	
	@Override
	public ArrayList<Double> getDiscountList() {
		return rankDataService.getDiscountList();
	}
	
	@Override
	public boolean updateCreditList(ArrayList<Double> creditList) {
		return rankDataService.updateCreditList(creditList);
	}
	
	@Override
	public boolean updateDiscountList(ArrayList<Double> discountList) {
		return rankDataService.updateDiscountList(discountList);
	}
	
	@Override
	public double getCredit(String memberID) {
		return creditDataService.getCredit(memberID);
	}
	
	@Override
	public ArrayList<CreditChangePO> getCreditChange(String memberID) {
		return creditDataService.getCreditChange(memberID);
	}
	
	@Override
	public boolean addCreditChange(String memberID, CreditChangePO creditChange) {
		return creditDataService.addCreditChange(memberID, creditChange);
	}

	@Override
	public OrderDataService getOrdaerData(String userID) {
		return orderDataFactory.getOrdaerData(userID);
	}
}
