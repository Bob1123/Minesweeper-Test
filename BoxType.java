
public enum BoxType {
	ZERO,
	ONE,
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN,
	EIGHT,
	MINE;
	
	
	public static BoxType numToType(int num) {
		switch(num) {
		case 0: return ZERO;
		case 1: return ONE;
		case 2: return TWO;
		case 3: return THREE;
		case 4: return FOUR;
		case 5: return FIVE;
		case 6: return SIX;
		case 7: return SEVEN;
		case 8: return EIGHT;
		default: return MINE;
		}
	}
	
}
