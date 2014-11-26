package interfaces;

import java.util.ArrayList;

import controller.SoldierTypes;
import controller.TerritoryNames;

public interface CardBehaviour {
	public boolean addSoldier(SoldierTypes soldier);
	public TerritoryNames getTerritoryName();
	public ArrayList<SoldierTypes> getSoldiers();
}
