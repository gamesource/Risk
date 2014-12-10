package model;

import java.util.ArrayList;

import controller.ContinentNames;

public class Continent {

	private ContinentNames name;
	private ArrayList<Territory> territories;
	
	public Continent(ContinentNames name) {
		this.name = name;
		territories = new ArrayList<Territory>();
	}
	
	public ContinentNames getName() {
		return name;
	}
	
	public void addTerritories(ArrayList<Territory> territories) {
		this.territories = territories ;
	}
	
	public ArrayList<Territory> getTerritoryList() {
		return territories;
	}
}
