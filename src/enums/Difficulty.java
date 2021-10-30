package enums;

public enum Difficulty {
	ONE(1),
	TWO(2), 
	THREE(3),
	FOUR(4);

	private int level;
	Difficulty(int level) {
		// TODO Auto-generated constructor stub
		this.level=level;
	}
	
	public int getLevel() {
		return level;
	}

}
