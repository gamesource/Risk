package model;

import interfaces.CardBehaviour;

import java.awt.Color;
import java.util.ArrayList;

public class Player {

	private ArrayList<Dice> dices ;
	private ArrayList<Territory> territories ;
	private ArrayList<CardBehaviour> cards;
	private Color color;
	
	public Player(String name){
		this.dices = new ArrayList<Dice>() ;
		this.territories = new ArrayList<Territory>() ;
		this.cards = new ArrayList<CardBehaviour>();
	}
	
	public boolean addCard(CardBehaviour card) {
		if(cards.size() > 5) {
			return false;
		}
		return cards.add(card);
	}
	
	public boolean removeCard(CardBehaviour card) {
		return cards.remove(card);
	}
	
	public ArrayList<CardBehaviour> getCards() {
		return cards;
	}
	
	public void roll()
	{
		for(Dice dice : dices) {
			dice.roll();
		}
	}
	
	public boolean addDice(Dice dice) {
		return dices.add(dice);
	}
	
	public ArrayList<Dice> getDices() {
		return dices;
	}
	
	public boolean addTerritory(Territory territory) {
		territory.setOwner(this);
		return territories.add(territory);
	}
	
	public boolean removeTerritory(Territory territory) {
		return territories.remove(territory);
	}
	
	public ArrayList<Territory> getTerritories() {
		return territories;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
}
