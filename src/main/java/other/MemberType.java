package other;

/**
 * 存储客户的类型，分为普通客户和企业客户
 * Ordinary：普通客户(0)
 * Business：企业客户(1)
 * @author CROFF
 * @version 2016-11-30
 */
public enum MemberType {
	
	Orinary(0), Bussiness(1);
	
	int value;
	private MemberType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
