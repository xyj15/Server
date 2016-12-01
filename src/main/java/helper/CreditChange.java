package helper;

import java.util.Date;

/**
 * Created by 97147 on 2016/11/19.
 */
public class CreditChange {

    private Date date;  //信用变化日期和时间
    private String orderID; //相关订单号
    private OrderAction action; //导致信用变化的行为
    private double change;  //信用度变化
    private double result;  //信用度变化后的结果
	
	public CreditChange() {
		
	}
}
