package state;

public enum State {
	place_army("Place"),
	attack("Attack"),
	fortify("Fortify");
	
	private final String name;
	State(String name) { this.name = name; }
    public String getValue() { return name; }
}
