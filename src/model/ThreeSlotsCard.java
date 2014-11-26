package model;

import java.util.ArrayList;

import controller.SoldierTypes;
import controller.TerritoryNames;
import interfaces.CardBehaviour;

public class ThreeSlotsCard implements CardBehaviour{

	private ArrayList<SoldierTypes> soldiers;
	
	public ThreeSlotsCard() {
		soldiers = new ArrayList<SoldierTypes>();
	}
	
	@Override
	public boolean addSoldier(SoldierTypes soldier) {
		return soldiers.add(soldier);
	}
	
	@Override
	public TerritoryNames getTerritoryName() {
		return null;
	}

	@Override
	public ArrayList<SoldierTypes> getSoldiers() {
		return soldiers;
	}

}
