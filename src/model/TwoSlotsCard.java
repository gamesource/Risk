package model;

import java.util.ArrayList;

import interfaces.CardBehaviour;
import controller.SoldierTypes;
import controller.TerritoryNames;

public class TwoSlotsCard implements CardBehaviour{
	private TerritoryNames name;
	private ArrayList<SoldierTypes> soldiers;
	
	public TwoSlotsCard(TerritoryNames name) {
		this.name = name;
		soldiers = new ArrayList<SoldierTypes>();
	}
	
	@Override
	public boolean addSoldier(SoldierTypes soldier) {
		return soldiers.add(soldier);
	}

	@Override
	public TerritoryNames getTerritoryName() {
		return name;
	}

	@Override
	public ArrayList<SoldierTypes> getSoldiers() {
		return soldiers;
	}
}
