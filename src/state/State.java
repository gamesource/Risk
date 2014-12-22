package state;

public enum State {
	draft("Draft"),
	attack("Attack"),
	fortify("Fortify");
	
	private final String name;
	State(String name) { this.name = name; }
    public String getValue() { return name; }
}
