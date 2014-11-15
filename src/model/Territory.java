package model;

import interfaces.Soldier;

import java.util.ArrayList;

public class Territory {
	
	private String name;
	private ArrayList<Soldier> soldiers;
	
	public Territory(String name) {
		this.name = name;
		soldiers = new ArrayList<Soldier>();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean addSoldier(Soldier soldier) {
		return soldiers.add(soldier);
	}
	
	public boolean removeSoldier(Soldier soldier) {
		return soldiers.remove(soldier);
	}
	
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
}
