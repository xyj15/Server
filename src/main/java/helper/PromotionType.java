package helper;

/**
 * Created by CROFF on 2016/11/29.
 * 促销策略的类型，分为折扣和满减
 * Discount：折扣(0)
 * Reduce：满减(1)
 * @author CROFF
 * @version 2016-11-30
 */
public enum PromotionType {
	Discount(0), Reduce(1);
	
	int value;
	private PromotionType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
