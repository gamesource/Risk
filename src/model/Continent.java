package model;

import java.util.ArrayList;

public class Continent {

	private String name;
	private ArrayList<Territory> territories;
	
	public Continent(String name) {
		this.name = name;
		territories = new ArrayList<Territory>();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean addTerritory(Territory territory) {
		return territories.add(territory);
	}
	
	public ArrayList<Territory> getTerritoryList() {
		return territories;
	}
}
