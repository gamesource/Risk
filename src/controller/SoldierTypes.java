package controller;

public enum SoldierTypes {
	footman(0x01),
	horseman(0x02),
	cannon(0x04),
	all(0x07);
	
	private final int id;
	SoldierTypes(int id) { this.id = id; }
    public int getValue() { return id; }
}
