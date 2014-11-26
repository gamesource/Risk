package model;

import controller.SoldierTypes;
import interfaces.Soldier;

public class HorseMan implements Soldier {

	public HorseMan() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getSoldierStrength() {
		return 5;
	}

	@Override
	public SoldierTypes getSoldierType() {
		return SoldierTypes.horseman;
	}

}
