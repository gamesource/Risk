package controller;

import interfaces.Soldier;

public class SoldierFactory {

	private Soldier soldier;

	public Soldier getSoldier() {
		return soldier;
	}

	public void setSoldier(Soldier soldier) {
		this.soldier = soldier;
	} 
	
}
