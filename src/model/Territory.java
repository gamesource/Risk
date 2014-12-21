package model;

import interfaces.Soldier;

import java.util.ArrayList;

import controller.TerritoryNames;

public class Territory {
	
	private TerritoryNames name;
	private ArrayList<Soldier> soldiers;
	private ArrayList<Territory> neighbours;
	private Player owner;
	
	public Territory(TerritoryNames name) {
		this.name = name;
		soldiers = new ArrayList<Soldier>();
		neighbours = new ArrayList<Territory>();
	}
	
	public TerritoryNames getName() {
		return name;
	}
	
	public boolean addSoldier(Soldier soldier) {
		return soldiers.add(soldier);
	}
	
	public boolean removeSoldier(Soldier soldier) {
		return soldiers.remove(soldier);
	}
	
	//it needs to remove soldier from territory
	public Soldier getSoldierWithStrength(int strength) {
		for(Soldier soldier : soldiers) {
			if(soldier.getSoldierStrength() == strength) {
				return soldier;
			}
		}
		return null;
	}
	
	public ArrayList<Soldier> getSoldierList() {
		return soldiers;
	}
	
	public ArrayList<Territory> getNeighbours() {
		return neighbours;
	}
	
	public boolean addNeighbour(Territory neighbour) {
		return neighbours.add(neighbour);
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
}
