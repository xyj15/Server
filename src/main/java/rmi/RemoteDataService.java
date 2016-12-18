package rmi;


import data.service.*;
import data.implementation.*;
import helper.RoomType;
import po.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by CROFF on 2016/12/18.
 */
public class RemoteDataService extends UnicastRemoteObject implements CreditDataService,
		HotelDataService, ManagerDataService, MemberDataService, OrderDataService,
		PromotionDataService, RankDataService, RoomDataService, SalerDataService, SearchDataService {
	
	private static final long serialVersionUID = 4029039744279087136L;
	private CreditDataService creditDataService;
	private HotelDataService hotelDataService;
	private ManagerDataService managerDataService;
	private MemberDataService memberDataService;
	private OrderDataService orderDataService;
	private PromotionDataService promotionDataService;
	private RankDataService rankDataService;
	private RoomDataService roomDataService;
	private SalerDataService salerDataService;
	private SearchDataService searchDataService;
	protected RemoteDataService() throws RemoteException {
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
//		orderDataFactory = new OrderDataConFactory();
	}
	
	@Override
	public boolean addSaler(SalerPO saler) throws RemoteException {
		return salerDataService.addSaler(saler);
	}
	
	@Override
	public boolean deleteSaler(String salerID) throws RemoteException {
		return salerDataService.deleteSaler(salerID);
	}
	
	@Override
	public boolean updateSaler(SalerPO saler) throws RemoteException {
		return salerDataService.updateSaler(saler);
	}
	
	@Override
	public SalerPO getSaler(String ID) throws RemoteException {
		return salerDataService.getSaler(ID);
	}
	
	@Override
	public String getAvailableSalerID() throws RemoteException {
		return salerDataService.getAvailableSalerID();
	}
	
	@Override
	public boolean addMember(MemberPO member) throws RemoteException {
		return memberDataService.addMember(member);
	}
	
	@Override
	public boolean deleteMember(String memberID) throws RemoteException {
		return memberDataService.deleteMember(memberID);
	}
	
	@Override
	public boolean updateMember(MemberPO member) throws RemoteException {
		return memberDataService.updateMember(member);
	}
	
	@Override
	public MemberPO getMember(String ID) throws RemoteException {
		return memberDataService.getMember(ID);
	}
	
	@Override
	public String getAvailableMemberID() throws RemoteException {
		return memberDataService.getAvailableMemberID();
	}
	
	@Override
	public boolean addOrder(OrderPO order) throws RemoteException {
		return false;
	}
	
	@Override
	public boolean updateOrder(OrderPO order) throws RemoteException {
		return false;
	}
	
	@Override
	public boolean cancelOrder(String orderID) throws RemoteException {
		return false;
	}
	
	@Override
	public boolean makeOrderAbnormal(String orderID) throws RemoteException {
		return false;
	}
	
	@Override
	public boolean recoverOrder(String orderID, double recover) throws RemoteException {
		return false;
	}
	
	@Override
	public OrderPO getOrder(String orderID) throws RemoteException {
		return null;
	}
	
	@Override
	public ArrayList<OrderPO> getOrderList(String userID) throws RemoteException {
		return null;
	}
	
	@Override
	public ArrayList<OrderPO> getFinishedOrders(String userID) throws RemoteException {
		return null;
	}
	
	@Override
	public ArrayList<OrderPO> getUnfinishedOrders(String userID) throws RemoteException {
		return null;
	}
	
	@Override
	public ArrayList<OrderPO> getAbnormalOrders(String userID) throws RemoteException {
		return null;
	}
	
	@Override
	public ArrayList<OrderPO> getCancledOrders(String userID) throws RemoteException {
		return null;
	}
	
	@Override
	public RoomPO getSingleRoom(Date theDay, String roomNUM, String hotelID) throws RemoteException {
		return roomDataService.getSingleRoom(theDay, roomNUM, hotelID);
	}
	
	@Override
	public boolean addSingleRoom(RoomPO room, String hotelID) throws RemoteException {
		return roomDataService.addSingleRoom(room, hotelID);
	}
	
	@Override
	public boolean updateSingleRoom(Date theDay, RoomPO room, String hotelID) throws RemoteException {
		return roomDataService.updateSingleRoom(theDay, room, hotelID);
	}
	
	@Override
	public boolean deleteSingleRoom(String roomNUM, String hotelID) throws RemoteException {
		return roomDataService.deleteSingleRoom(roomNUM, hotelID);
	}
	
	@Override
	public ArrayList<RoomPO> getRoomsByTypeDate(Date day, RoomType roomType, String hotelID) throws RemoteException {
		return roomDataService.getRoomsByTypeDate(day, roomType, hotelID);
	}
	
	@Override
	public ArrayList<RoomPO> getRoomsByNameDate(Date day, String roomName, String hotelID) throws RemoteException {
		return roomDataService.getRoomsByNameDate(day, roomName, hotelID);
	}
	
	@Override
	public ArrayList<RoomPO> getRoomsByDate(Date day, String hotelID) throws RemoteException {
		return roomDataService.getRoomsByDate(day, hotelID);
	}
	
	@Override
	public boolean reserveSingleRoom(Date day, String roomNUM, String hotelID) throws RemoteException {
		return roomDataService.reserveSingleRoom(day, roomNUM, hotelID);
	}
	
	@Override
	public boolean checkIn(Date day, String roomNUM, String hotelID) throws RemoteException {
		return roomDataService.checkIn(day, roomNUM, hotelID);
	}
	
	@Override
	public boolean checkOut(Date day, String roomNUM, String hotelID) throws RemoteException {
		return roomDataService.checkOut(day, roomNUM, hotelID);
	}
	
	@Override
	public boolean addHotel(HotelPO hotel) throws RemoteException {
		return hotelDataService.addHotel(hotel);
	}
	
	@Override
	public boolean deleteHotel(String hotelID) throws RemoteException {
		return hotelDataService.deleteHotel(hotelID);
	}
	
	@Override
	public boolean updateHotel(HotelPO hotel) throws RemoteException {
		return hotelDataService.updateHotel(hotel);
	}
	
	@Override
	public HotelPO getHotelByID(String hotelID) throws RemoteException {
		return hotelDataService.getHotelByID(hotelID);
	}
	
	@Override
	public String getAvailableHotelID() throws RemoteException {
		return hotelDataService.getAvailableHotelID();
	}
	
	@Override
	public ArrayList<HotelPO> getHotelListByCityDistrict(String city, String district) throws RemoteException {
		return searchDataService.getHotelListByCityDistrict(city, district);
	}
	
	@Override
	public ArrayList<HotelPO> getHotelListSortedByScore(double lowScore, double highScore, String city, String district) throws RemoteException {
		return searchDataService.getHotelListSortedByScore(lowScore, highScore, city, district);
	}
	
	@Override
	public ArrayList<HotelPO> getHotelListFilteredByLevel(int level, String city, String district) throws RemoteException {
		return searchDataService.getHotelListFilteredByLevel(level, city, district);
	}
	
	@Override
	public ArrayList<HotelPO> getHotelListFilteredByPrice(double lowPrice, double highPrice, String city, String district) throws RemoteException {
		return searchDataService.getHotelListFilteredByPrice(lowPrice, highPrice, city, district);
	}
	
	@Override
	public boolean updateManager(ManagerPO manager) throws RemoteException {
		return managerDataService.updateManager(manager);
	}
	
	@Override
	public ManagerPO getManager() throws RemoteException {
		return managerDataService.getManager();
	}
	
	@Override
	public PromotionPO getPromotion(String promotionID) throws RemoteException {
		return promotionDataService.getPromotion(promotionID);
	}
	
	@Override
	public boolean addPromotion(PromotionPO promotion) throws RemoteException {
		return promotionDataService.addPromotion(promotion);
	}
	
	@Override
	public boolean deletePromotion(String promotionID) throws RemoteException {
		return promotionDataService.deletePromotion(promotionID);
	}
	
	@Override
	public boolean updatePromotion(PromotionPO promotion) throws RemoteException {
		return promotionDataService.updatePromotion(promotion);
	}
	
	@Override
	public ArrayList<PromotionPO> getPromotionList() throws RemoteException {
		return promotionDataService.getPromotionList();
	}
	
	@Override
	public String getAvailablePromotionID() throws RemoteException {
		return promotionDataService.getAvailablePromotionID();
	}
	
	@Override
	public ArrayList<Double> getCreditList() throws RemoteException {
		return rankDataService.getCreditList();
	}
	
	@Override
	public ArrayList<Double> getDiscountList() throws RemoteException {
		return rankDataService.getDiscountList();
	}
	
	@Override
	public boolean updateCreditList(ArrayList<Double> creditList) throws RemoteException {
		return rankDataService.updateCreditList(creditList);
	}
	
	@Override
	public boolean updateDiscountList(ArrayList<Double> discountList) throws RemoteException {
		return rankDataService.updateDiscountList(discountList);
	}
	
	@Override
	public double getCredit(String memberID) throws RemoteException {
		return creditDataService.getCredit(memberID);
	}
	
	@Override
	public ArrayList<CreditChangePO> getCreditChange(String memberID) throws RemoteException {
		return creditDataService.getCreditChange(memberID);
	}
	
	@Override
	public boolean setCredit(String memberID, double credit) throws RemoteException {
		return creditDataService.setCredit(memberID, credit);
	}
	
	@Override
	public boolean addCreditChange(String memberID, CreditChangePO creditChange) throws RemoteException {
		return creditDataService.addCreditChange(memberID, creditChange);
	}
}
