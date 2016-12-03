package data.implementation;

import data.dataservice.OrderDataService;
import jxl.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import po.OrderPO;
import helper.OrderStatus;

/**
 * Created by apple on 2016/11/30.
 */
public class OrderDataForM implements OrderDataService {

    private int dataSize = 20;
    private String sourceFile = "OrderForMember.xls";
    private Workbook book;
    private WritableWorkbook wBook;
    private WritableSheet sheet;
    private OrderDataForH sync;

    public OrderDataForM(){
        sync = new OrderDataForH();
        try {
            book = Workbook.getWorkbook(new File(sourceFile));
            wBook = Workbook.createWorkbook(new File(sourceFile),book);
            sheet = wBook.getSheet(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param order
     * @return
     */
    public boolean addOrder(OrderPO order) {
        int col = 0;
        int row = hash(order.getMemberID());
        while(sheet.getCell(col, row).getContents()!=""){
            if(sheet.getCell(col, row).getContents().equals(order.getOrderID())){
                return false;                               //An order of the same number has already existed.
            }
            col+=dataSize;
        }
        Label orderID = new Label(col,row,order.getOrderID());
        col++;
        Label memberID = new Label(col,row,order.getMemberID());
        col++;
        Label hotelID = new Label(col, row, order.getHotelID());
        col++;
        Label evaluation = new Label(col, row, order.getEvaluation());
        col++;
        Label promotion = new Label(col, row, order.getPromotionID());
        col++;
        DateTime checkIn = new DateTime(col, row, order.getCheckinTime());
        col++;
        DateTime checkOut = new DateTime(col, row, order.getCheckoutTime());
        col++;
        DateTime latestCheckIn = new DateTime(col, row, order.getLatestCheckinTime());
        col++;
        DateTime creatTime = new DateTime(col, row, order.getCreateTime());
        col++;
        DateTime actualCheckIn = new DateTime(col, row, order.getActualCheckinTime());
        col++;
        DateTime actualCheckOut = new DateTime(col, row, order.getActualCheckoutTime());
        col++;
        DateTime cancelTime = new DateTime(col, row, order.getCancelTime());
        col++;
        Number roomNUM = new Number(col, row, order.getNumberOfRoom());
        col++;
        Number numOfClient = new Number(col, row, order.getNumberOfClient());
        col++;
        Number price = new Number(col, row, order.getPrice());
        col++;
        Number score = new Number(col, row, order.getScore());
        col++;
        Number recover = new Number(col, row, order.getRecover());
        col++;
        double kid = 0.0;
        if(order.getHaveKids()){
            kid = 1;
        }
        Number hasKid = new Number(col, row, kid);
        col++;
        Label roomName = new Label(col, row, order.getRoomName());
        col++;
        Number orderStatus = new Number(col, row, order.getOrderStatus().getV());

        try {
            sheet.addCell(orderID);
            sheet.addCell(memberID);
            sheet.addCell(hotelID);
            sheet.addCell(promotion);
            sheet.addCell(evaluation);
            sheet.addCell(checkIn);
            sheet.addCell(checkOut);
            sheet.addCell(latestCheckIn);
            sheet.addCell(creatTime);
            sheet.addCell(actualCheckIn);
            sheet.addCell(actualCheckOut);
            sheet.addCell(cancelTime);
            sheet.addCell(numOfClient);
            sheet.addCell(roomNUM);
            sheet.addCell(price);
            sheet.addCell(score);
            sheet.addCell(recover);
            sheet.addCell(hasKid);
            sheet.addCell(roomName);
            sheet.addCell(orderStatus);
        } catch (WriteException e) {
            e.printStackTrace();
        }

        try {
            wBook.write();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sync.addOrder(order);
        return true;
    }

    /**
     *
     * @param order
     * @return
     */
    public boolean updateOrder(OrderPO order) {
        WritableCell orderStart = (WritableCell) sheet.findCell(order.getOrderID());
        int col = orderStart.getColumn()+1;
        int row = orderStart.getRow();
        Label memberID = new Label(col,row,order.getMemberID());
        col++;
        Label hotelID = new Label(col, row, order.getHotelID());
        col++;
        Label evaluation = new Label(col, row, order.getEvaluation());
        col++;
        Label promotion = new Label(col, row, order.getPromotionID());
        col++;
        DateTime checkIn = new DateTime(col, row, order.getCheckinTime());
        col++;
        DateTime checkOut = new DateTime(col, row, order.getCheckoutTime());
        col++;
        DateTime latestCheckIn = new DateTime(col, row, order.getLatestCheckinTime());
        col++;
        DateTime creatTime = new DateTime(col, row, order.getCreateTime());
        col++;
        DateTime actualCheckIn = new DateTime(col, row, order.getActualCheckinTime());
        col++;
        DateTime actualCheckOut = new DateTime(col, row, order.getActualCheckoutTime());
        col++;
        DateTime cancelTime = new DateTime(col, row, order.getCancelTime());
        col++;
        Number roomNUM = new Number(col, row, order.getNumberOfRoom());
        col++;
        Number numOfClient = new Number(col, row, order.getNumberOfClient());
        col++;
        Number price = new Number(col, row, order.getPrice());
        col++;
        Number score = new Number(col, row, order.getScore());
        col++;
        Number recover = new Number(col, row, order.getRecover());
        col++;
        double kid = 0.0;
        if(order.getHaveKids()){
            kid = 1;
        }
        Number hasKid = new Number(col, row, kid);
        col++;
        Label roomName = new Label(col, row, order.getRoomName());
        col++;
        Number orderStatus = new Number(col, row, order.getOrderStatus().getV());

        try {
            sheet.addCell(memberID);
            sheet.addCell(hotelID);
            sheet.addCell(promotion);
            sheet.addCell(evaluation);
            sheet.addCell(checkIn);
            sheet.addCell(checkOut);
            sheet.addCell(latestCheckIn);
            sheet.addCell(creatTime);
            sheet.addCell(actualCheckIn);
            sheet.addCell(actualCheckOut);
            sheet.addCell(cancelTime);
            sheet.addCell(numOfClient);
            sheet.addCell(roomNUM);
            sheet.addCell(price);
            sheet.addCell(score);
            sheet.addCell(recover);
            sheet.addCell(hasKid);
            sheet.addCell(roomName);
            sheet.addCell(orderStatus);
        } catch (WriteException e) {
            e.printStackTrace();
        }

        try {
            wBook.write();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sync.updateOrder(order);
        return true;
    }

    /**
     *
     * @param orderID
     * @return
     */
    public boolean cancleOrder(String orderID) {
        Cell orderStart = sheet.findCell(orderID);
        int col = orderStart.getColumn()+dataSize-1;
        int row = orderStart.getRow();
        Number orderStatus = new Number(col, row, OrderStatus.Canceled.getV());

        try {
            sheet.addCell(orderStatus);
        } catch (WriteException e) {
            e.printStackTrace();
        }

        try {
            wBook.write();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sync.cancleOrder(orderID);
        return true;
    }

    /**
     *
     * @param orderID
     * @return
     */
    public boolean makeOrderAbnormal(String orderID) {
        Cell orderStart = sheet.findCell(orderID);
        int col = orderStart.getColumn()+dataSize-1;
        int row = orderStart.getRow();
        Number orderStatus = new Number(col, row, OrderStatus.Abnormal.getV());

        try {
            sheet.addCell(orderStatus);
        } catch (WriteException e) {
            e.printStackTrace();
        }

        try {
            wBook.write();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sync.makeOrderAbnormal(orderID);
        return true;
    }

    /**
     *
     * @param orderID
     * @param recover
     * @return
     */
    public boolean recoverOrder(String orderID, double recover) {
        Cell orderStart = sheet.findCell(orderID);
        int col = orderStart.getColumn();
        int row = orderStart.getRow();
        Number orderStatus = new Number(col+dataSize-1, row, OrderStatus.Canceled.getV());
        Number recoverLocation = new Number(col+dataSize-4, row, recover);

        try {
            sheet.addCell(orderStatus);
            sheet.addCell(recoverLocation);
        } catch (WriteException e) {
            e.printStackTrace();
        }

        try {
            wBook.write();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sync.recoverOrder(orderID,recover);
        return true;
    }

    /**
     *
     * @param orderID
     * @return
     */
    public OrderPO getOrder(String orderID) {
        Cell orderStart = sheet.findCell(orderID);
        int col = orderStart.getColumn();
        int row = orderStart.getRow();
        col++;
        String memberID = sheet.getCell(col, row).getContents();
        col++;
        String hotelID = sheet.getCell(col, row).getContents();
        col++;
        String evaluation = sheet.getCell(col, row).getContents();
        col++;
        String promotion = sheet.getCell(col, row).getContents();
        col++;
        Date checkIn = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date checkOut = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date latestCheckIn = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date creatTime = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date actualCheckIn = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date actualCheckOut = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date cancelTime = ((DateCell)sheet.getCell(col, row)).getDate();
        col++;
        int roomNUM = (int)((NumberCell) sheet.getCell(col, row)).getValue();
        col++;
        int numOfClient = (int)((NumberCell) sheet.getCell(col, row)).getValue();
        col++;
        double price = ((NumberCell) sheet.getCell(col, row)).getValue();
        col++;
        double score = ((NumberCell) sheet.getCell(col, row)).getValue();
        col++;
        double recover = ((NumberCell) sheet.getCell(col, row)).getValue();
        col++;
        boolean hasKid = true;
        int kid = (int)((NumberCell) sheet.getCell(col, row)).getValue();
        if(kid==0){
            hasKid=false;
        }
        col++;
        String roomName = sheet.getCell(col, row).getContents();
        col++;
        int status = (int)((NumberCell) sheet.getCell(col, row)).getValue();
        OrderStatus orderStatus = null;
        switch (status){
            case 0: orderStatus = OrderStatus.Executed; break;
            case 1: orderStatus = OrderStatus.Unexecuted; break;
            case 2: orderStatus = OrderStatus.Abnormal; break;
            case 3: orderStatus = OrderStatus.Canceled; break;
        }
        return new OrderPO(memberID,hotelID,orderID,orderStatus,creatTime,checkIn,actualCheckIn,latestCheckIn,checkOut,actualCheckOut,
                roomNUM,roomName,numOfClient,hasKid,score,evaluation,recover,promotion,price,cancelTime);
    }

    /**
     *
     * @param userID
     * @return
     */
    public ArrayList<OrderPO> getOrderList(String userID) {
        ArrayList<OrderPO> result = new ArrayList<OrderPO>();
        int col = 0;
        int row = hash(userID);
        while(sheet.getCell(col, row).getContents()!=""){
            result.add(getOrder(col, row));
            col+=dataSize;
        }
        if(result.size()==0) return null;   //This hotel does not have any order.
        return result;
    }

    /**
     *
     * @param userID
     * @return
     */
    public ArrayList<OrderPO> getFinishedOrders(String userID) {
        ArrayList<OrderPO> result = new ArrayList<OrderPO>();
        int col = 0;
        int row = hash(userID);
        while(sheet.getCell(col, row).getContents()!=""){
            OrderPO temp = getOrder(col, row);
            if(temp.getOrderStatus()==OrderStatus.Executed){
                result.add(temp);
            }
            col+=dataSize;
        }
        if(result.size()==0) return null;   //This hotel does not have any finished order.
        return result;
    }

    /**
     *
     * @param userID
     * @return
     */
    public ArrayList<OrderPO> getUnfinishedOrders(String userID) {
        ArrayList<OrderPO> result = new ArrayList<OrderPO>();
        int col = 0;
        int row = hash(userID);
        while(sheet.getCell(col, row).getContents()!=""){
            OrderPO temp = getOrder(col, row);
            if(temp.getOrderStatus()==OrderStatus.Unexecuted){
                result.add(temp);
            }
            col+=dataSize;
        }
        if(result.size()==0) return null;   //This hotel does not have any unfinished order.
        return result;
    }

    /**
     *
     * @param userID
     * @return
     */
    public ArrayList<OrderPO> getAbnormalOrders(String userID) {
        ArrayList<OrderPO> result = new ArrayList<OrderPO>();
        int col = 0;
        int row = hash(userID);
        while(sheet.getCell(col, row).getContents()!=""){
            OrderPO temp = getOrder(col, row);
            if(temp.getOrderStatus()==OrderStatus.Abnormal){
                result.add(temp);
            }
            col+=dataSize;
        }
        if(result.size()==0) return null;   //This hotel does not have any abnormal order.
        return result;
    }

    /**
     *
     * @param userID
     * @return
     */
    public ArrayList<OrderPO> getCancledOrders(String userID) {
        ArrayList<OrderPO> result = new ArrayList<OrderPO>();
        int col = 0;
        int row = hash(userID);
        while(sheet.getCell(col, row).getContents()!=""){
            OrderPO temp = getOrder(col, row);
            if(temp.getOrderStatus()==OrderStatus.Canceled){
                result.add(temp);
            }
            col+=dataSize;
        }
        if(result.size()==0) return null;   //This hotel does not have any canceled order.
        return result;
    }

    /**
     *
     * @param ID
     * @return
     */
    private int hash(String ID){
        int hashResult = Integer.parseInt(ID);
        hashResult%=27;
        return hashResult;
    }

    /**
     *
     * @param col
     * @param row
     * @return
     */
    private OrderPO getOrder(int col, int row){
        String orderID = sheet.getCell(col, row).getContents();
        col++;
        String memberID = sheet.getCell(col, row).getContents();
        col++;
        String hotelID = sheet.getCell(col, row).getContents();
        col++;
        String evaluation = sheet.getCell(col, row).getContents();
        col++;
        String promotion = sheet.getCell(col, row).getContents();
        col++;
        Date checkIn = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date checkOut = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date latestCheckIn = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date creatTime = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date actualCheckIn = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date actualCheckOut = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date cancelTime = ((DateCell)sheet.getCell(col, row)).getDate();
        col++;
        int roomNUM = (int)((NumberCell) sheet.getCell(col, row)).getValue();
        col++;
        int numOfClient = (int)((NumberCell) sheet.getCell(col, row)).getValue();
        col++;
        double price = ((NumberCell) sheet.getCell(col, row)).getValue();
        col++;
        double score = ((NumberCell) sheet.getCell(col, row)).getValue();
        col++;
        double recover = ((NumberCell) sheet.getCell(col, row)).getValue();
        col++;
        boolean hasKid = true;
        int kid = (int)((NumberCell) sheet.getCell(col, row)).getValue();
        if(kid==0){
            hasKid=false;
        }
        col++;
        String roomName = sheet.getCell(col, row).getContents();
        col++;
        int status = (int)((NumberCell) sheet.getCell(col, row)).getValue();
        OrderStatus orderStatus = null;
        switch (status){
            case 0: orderStatus = OrderStatus.Executed; break;
            case 1: orderStatus = OrderStatus.Unexecuted; break;
            case 2: orderStatus = OrderStatus.Abnormal; break;
            case 3: orderStatus = OrderStatus.Canceled; break;
        }
        return new OrderPO(memberID,hotelID,orderID,orderStatus,creatTime,checkIn,actualCheckIn,latestCheckIn,checkOut,actualCheckOut,
                roomNUM,roomName,numOfClient,hasKid,score,evaluation,recover,promotion,price,cancelTime);
    }

}

