package data.implementation;

import data.dataservice.OrderDataService;
import helper.OrderChanger;
import helper.RoomType;
import jxl.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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

    private int dataSize = 21;
    private String sourceFile = "OrderForMember.xls";
    private OrderChanger changerForM = new OrderChanger("OrderForMember.xls");
    private OrderChanger changerForH = new OrderChanger("OrderForHotel.xls");
    private Workbook book;
    private Sheet sheet;

    /**
     *
     * @param order
     * @return
     */
    public boolean addOrder(OrderPO order) {
        int col = 0;
        int row = hash(order.getMemberID());
        boolean result = changerForM.addOrder(col, row, order);
        row = hash(order.getHotelID());
        boolean sync = changerForH.addOrder(col, row, order);
        return result&&sync;
    }

    /**
     *
     * @param order
     * @return
     */
    public boolean updateOrder(OrderPO order) {
        boolean result = changerForM.updateOrder(order);
        boolean sync = changerForH.updateOrder(order);
        return result&&sync;
    }

    /**
     *
     * @param orderID
     * @return
     */
    public boolean cancelOrder(String orderID) {
        boolean result = changerForM.cancelOrder(orderID);
        boolean sync = changerForH.cancelOrder(orderID);
        return result&&sync;
    }

    /**
     *
     * @param orderID
     * @return
     */
    public boolean makeOrderAbnormal(String orderID) {
        boolean result = changerForM.makeOrderAbnormal(orderID);
        boolean sync = changerForH.makeOrderAbnormal(orderID);
        return result&&sync;
    }

    /**
     *
     * @param orderID
     * @param recover
     * @return
     */
    public boolean recoverOrder(String orderID, double recover) {
        boolean result = changerForM.recoverOrder(orderID, recover);
        boolean sync = changerForH.recoverOrder(orderID, recover);
        return result&&sync;
    }

    /**
     *
     * @param orderID
     * @return
     */
    public OrderPO getOrder(String orderID) {
        createSheet();
        Cell orderStart = sheet.findCell(orderID);
        if(orderStart==null){
            book.close();
            return null;
        }
        int col = orderStart.getColumn();
        int row = orderStart.getRow();
        OrderPO result = getOrder(col, row);
        book.close();
        return result;
    }

    /**
     *
     * @param userID
     * @return
     */
    public ArrayList<OrderPO> getOrderList(String userID) {
        createSheet();
        ArrayList<OrderPO> result = new ArrayList<OrderPO>();
        int col = 0;
        int row = hash(userID);
        for (int i = 0; i < sheet.getColumn(row).length; i+=dataSize) {
            result.add(getOrder(col+i, row));
        }
        book.close();
        if(result.size()==0) return null;   //This hotel does not have any order.
        return result;
    }

    /**
     *
     * @param userID
     * @return
     */
    public ArrayList<OrderPO> getFinishedOrders(String userID) {
        ArrayList<OrderPO> temp = getOrderList(userID);
        if(temp==null) return null;
        ArrayList<OrderPO> result = new ArrayList<>();
        for (OrderPO thisOrder: temp
             ) {
            if(thisOrder.getOrderStatus()==OrderStatus.Executed){
                result.add(thisOrder);
            }
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
        ArrayList<OrderPO> temp = getOrderList(userID);
        if(temp==null) return null;
        ArrayList<OrderPO> result = new ArrayList<>();
        for (OrderPO thisOrder: temp
                ) {
            if(thisOrder.getOrderStatus()==OrderStatus.Unexecuted){
                result.add(thisOrder);
            }
        }
        if(result.size()==0) return null;   //This hotel does not have any finished order.
        return result;
    }

    /**
     *
     * @param userID
     * @return
     */
    public ArrayList<OrderPO> getAbnormalOrders(String userID) {
        ArrayList<OrderPO> temp = getOrderList(userID);
        if(temp==null) return null;
        ArrayList<OrderPO> result = new ArrayList<>();
        for (OrderPO thisOrder: temp
                ) {
            if(thisOrder.getOrderStatus()==OrderStatus.Abnormal){
                result.add(thisOrder);
            }
        }
        if(result.size()==0) return null;   //This hotel does not have any finished order.
        return result;
    }

    /**
     *
     * @param userID
     * @return
     */
    public ArrayList<OrderPO> getCancledOrders(String userID) {
        ArrayList<OrderPO> temp = getOrderList(userID);
        if(temp==null) return null;
        ArrayList<OrderPO> result = new ArrayList<>();
        for (OrderPO thisOrder: temp
                ) {
            if(thisOrder.getOrderStatus()==OrderStatus.Canceled){
                result.add(thisOrder);
            }
        }
        if(result.size()==0) return null;   //This hotel does not have any finished order.
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
     */
    private void createSheet(){
        try {
            book = Workbook.getWorkbook(new File(sourceFile));
            sheet = book.getSheet(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param col
     * @param row
     * @return
     */
    private OrderPO getOrder(int col, int row){
        int status = (int)((NumberCell) sheet.getCell(col+dataSize-1, row)).getValue();
        OrderStatus orderStatus = null;
        switch (status){
            case 0: orderStatus = OrderStatus.Executed; break;
            case 1: orderStatus = OrderStatus.Unexecuted; break;
            case 2: orderStatus = OrderStatus.Abnormal; break;
            case 3: orderStatus = OrderStatus.Canceled; break;
        }
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
        String roomName = sheet.getCell(col, row).getContents();
        col++;
        Date checkIn = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date checkOut = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date latestCheckIn = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date creatTime = ((DateCell) sheet.getCell(col, row)).getDate();
        col++;
        Date actualCheckIn = null;
        Date actualCheckOut = null;
        Date cancelTime = null;
        if(orderStatus==OrderStatus.Executed){
            actualCheckIn = ((DateCell) sheet.getCell(col, row)).getDate();
            col++;
            actualCheckOut = ((DateCell) sheet.getCell(col, row)).getDate();
            col++;
        }
        else{
            col+=2;
        }
        if(orderStatus==OrderStatus.Canceled){
            cancelTime = ((DateCell) sheet.getCell(col, row)).getDate();
        }
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
        int type = (int)((NumberCell) sheet.getCell(col, row)).getValue();
        RoomType roomType = null;
        switch (type){
            case 0: roomType = RoomType.Single; break;
            case 1: roomType = RoomType.TwinBed; break;
            case 2: roomType = RoomType.BigBed; break;
            case 3: roomType = RoomType.Suite; break;
        }
        return new OrderPO(memberID,hotelID,orderID,orderStatus,creatTime,checkIn,actualCheckIn,latestCheckIn,checkOut,actualCheckOut,
                roomNUM,roomName,numOfClient,hasKid,score,evaluation,recover,promotion,price,cancelTime, roomType);
    }

}

