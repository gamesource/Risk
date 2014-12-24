package controller;

public enum DieE {
	die1("die1.png"),
	die2("die2.png"),
	die3("die3.png"),
	die4("die4.png"),
	die5("die5.png"),
	die6("die6.png");
	
	private final String name;
	DieE(String name) { this.name = name; }
    public String getValue() { return name; }
}
