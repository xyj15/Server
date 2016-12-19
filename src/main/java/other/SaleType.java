package other;

/**
 * 优惠策略减价类型枚举类，分为会员等级折扣、特定日期优惠、生日优惠、预定房间数优惠、合作企业优惠、特定商圈优惠
 * Rank：会员等级折扣(0)
 * Date：特定日期优惠(1)
 * Birthday：生日优惠(2)
 * RoomNumber：预定房间数优惠(3)
 * Enterprise：合作企业优惠(4)
 * District：特定商圈优惠(5)
 * @author CROFF
 * @version 2016-11-30
 */
public enum SaleType {
	
	Rank(0), Date(1), Birthday(2), RoomNumber(3), Enterprise(4), District(5);
	
	int value;
	private SaleType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}