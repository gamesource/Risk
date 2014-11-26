package model;

import controller.SoldierTypes;
import interfaces.Soldier;

public class FootMan implements Soldier{

	public FootMan() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getSoldierStrength() {
		return 1;
	}

	@Override
	public SoldierTypes getSoldierType() {
		return SoldierTypes.footman;
	}

}
