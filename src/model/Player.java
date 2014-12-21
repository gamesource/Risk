package model;

import interfaces.CardBehaviour;

import java.awt.Color;
import java.util.ArrayList;

import controller.TurnPhrases;

public class Player {

	private ArrayList<Dice> dices ;
	private ArrayList<Territory> territories ;
	private ArrayList<CardBehaviour> cards;
	private Color color;
	private TurnPhrases turnPhrases ;
	
	public Player(String name){
		this.dices = new ArrayList<Dice>() ;
		this.territories = new ArrayList<Territory>() ;
		this.cards = new ArrayList<CardBehaviour>();
		this.turnPhrases = new TurnPhrases();
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
	
	public ArrayList<Territory> getTerritories() {
		return territories;
	}
	
	public void draft(Territory territory)
	{
		turnPhrases.draft(this,territory);
	}
	
	public void attack(Territory attackersTerritory,Territory defendersTerritory,int armySize)
	{
		turnPhrases.attack(attackersTerritory,defendersTerritory,armySize);
	}
	
	public void fortify()
	{
		turnPhrases.fortify(this);
	}
	
	public void pass()
	{
		turnPhrases.pass(this);
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
}
