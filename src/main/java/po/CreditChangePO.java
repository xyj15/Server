package po;

import helper.OrderAction;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 97147 on 2016/11/19.
 * 存储CreditChange信息的PO类
 * @author CROFF
 * @version 2016-12-1
 */
public class CreditChangePO implements Serializable {

    private Date date;  //信用变化日期和时间
    private String orderID; //相关订单号
    private OrderAction orderAction; //导致信用变化的行为
    private double change;  //信用度变化数值
    private double result;  //信用度变化后的结果数值
	
	/**
	 * CreditChange构造方法
	 * @param date 信用变化日期和时间
	 * @param orderID 相关订单号
	 * @param orderAction 导致信用变化的行为
	 * @param change 信用度变化数值
	 * @param result 信用度变化后的结果数值
	 */
	public CreditChangePO(Date date, String orderID, OrderAction orderAction,
	                      double change, double result) {
		this.date = date;
		this.orderID = orderID;
		this.orderAction = orderAction;
		this.change = change;
		this.result = result;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getOrderID() {
		return orderID;
	}
	
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
	public OrderAction getOrderAction() {
		return orderAction;
	}
	
	public void setOrderAction(OrderAction orderAction) {
		this.orderAction = orderAction;
	}
	
	public double getChange() {
		return change;
	}
	
	public void setChange(double change) {
		this.change = change;
	}
	
	public double getResult() {
		return result;
	}
	
	public void setResult(double result) {
		this.result = result;
	}
}
