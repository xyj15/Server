package other;

/**
 * 客房类型枚举类，分为单人房、双床房、大床房、套房
 * Single：单人房(0)
 * TwinBed：双床房(1)
 * BigBed：大床房(2)
 * Suite：套房(3)
 * @author CROFF
 * @version 2016-11-30
 */
public enum RoomType {

	Single(0), TwinBed(1), BigBed(2), Suite(3);
	
	int value;
	private RoomType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
