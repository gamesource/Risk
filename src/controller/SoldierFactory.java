package controller;

import java.util.ArrayList;

import model.Board;
import model.Cannon;
import model.Continent;
import model.FootMan;
import model.HorseMan;
import model.Player;
import model.Territory;
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
	
	public ArrayList<Soldier> createSoldier(Player player) {
		ArrayList<Soldier> soldiers = new ArrayList<Soldier>();
		for(int i = 0; i < player.getTerritories().size()/3; i++) {
			soldiers.add(new FootMan());
		}
		return soldiers;
	}
	
	public ArrayList<Soldier> createPlayerByContinent(Player player) {
		ArrayList<Soldier> soldiers = new ArrayList<Soldier>();
		ArrayList<Continent> own_continents = new ArrayList<Continent>();
		
		//Find player has which continents
		Board board = Board.getInstance();	
		for(Continent continent : board.getContinents()) {
			boolean has_continent = true;
			for(Territory territory : continent.getTerritoryList()) {
				if(!player.getTerritories().contains(territory)) {
					has_continent = false;
					break;
				}
			}
			if(has_continent) {
				own_continents.add(continent);
			}
		}
		
		//Calculate number of army
		int army_number = 0;
		for(Continent continent : own_continents) {
			if(continent.getName() == ContinentNames.north_america) {
				army_number += 5;
			}
			else if(continent.getName() == ContinentNames.south_america) {
				army_number += 2;
			}
			else if(continent.getName() == ContinentNames.europe) {
				army_number += 5;
			}
			else if(continent.getName() == ContinentNames.asia) {
				army_number += 7;
			}
			else if(continent.getName() == ContinentNames.africa) {
				army_number += 3;
			}
			else if(continent.getName() == ContinentNames.australia) {
				army_number += 2;
			}
		}
		
		//Create footman and add to list
		for(int i = 0; i < army_number; i++) {
			soldiers.add(new FootMan());
		}
		
		return soldiers;
	}
	
}
