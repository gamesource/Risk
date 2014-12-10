package controller;

public enum ContinentNames {
	north_america("North America"),
	south_america("South America"),
	europe("Europe"),
	africa("Africa"),
	asia("Asia"),
	australia("Australia");
	
	private final String name;
	ContinentNames(String name) { this.name = name; }
    public String getName() { return name; }
}
