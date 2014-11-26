package model;

import controller.SoldierTypes;
import interfaces.Soldier;

public class Cannon implements Soldier {

	public Cannon() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getSoldierStrength() {
		return 10;
	}

	@Override
	public SoldierTypes getSoldierType() {
		return SoldierTypes.cannon;
	}

}
