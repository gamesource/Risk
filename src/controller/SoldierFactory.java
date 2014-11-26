package controller;

import java.util.ArrayList;

import model.Cannon;
import model.FootMan;
import model.HorseMan;
import interfaces.CardBehaviour;
import interfaces.Soldier;

public class SoldierFactory {
	
	public Soldier createSoldier(ArrayList<CardBehaviour> cards) {
		
		Soldier soldier = null;
		int value = 0x00;
		boolean has_wild_card = false;
		
		for(CardBehaviour card : cards) {
			if(card.getSoldiers().size() < 3) {
				value |= card.getSoldiers().get(0).getValue();
			}
			else {
				has_wild_card = true;
			}
		}
		
		if(has_wild_card) {
			if(value != SoldierTypes.footman.getValue() || 
					value != SoldierTypes.horseman.getValue() || 
					value != SoldierTypes.cannon.getValue()) {
				value = SoldierTypes.all.getValue();
			}
		}

		if(value == SoldierTypes.footman.getValue()) {
			soldier = new FootMan();
		}
		else if(value == SoldierTypes.horseman.getValue()) {
			soldier = new HorseMan();
		}
		else if(value == SoldierTypes.cannon.getValue()) {
			soldier = new Cannon();
		}
		else if(value == SoldierTypes.all.getValue()) {
			soldier = new FootMan();
		}
		return soldier;
	}
	
}
