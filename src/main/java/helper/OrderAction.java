package helper;

/**
 * Created by 97147 on 2016/11/19.
 * 导致信用变化的行为，分为订单正常执行、订单异常、取消订单、信用充值
 * ExecuteOrder：订单正常执行(0)
 * AbnormalOrder：订单异常(1)
 * CancelOrder：取消订单(2)
 * RechargeCredit：信用充值(3)
 * @author CROFF
 * @version 2016-11-30
 */
public enum OrderAction {
    ExecuteOrder(0), AbnormalOrder(1), CancelOrder(2), RechargeCredit(3);
    
    int value;
	private OrderAction(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
