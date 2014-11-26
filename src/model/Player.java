package model;

import interfaces.CardBehaviour;

import java.util.ArrayList;

public class Player {

	private String name ;
	private ArrayList<Dice> dices ;
	private ArrayList<Territory> territories ;
	private ArrayList<CardBehaviour> cards;
	
	public Player(String name){
		this.name = name ;
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
		int randomNumber = (int) (1 + Math.random() * 5) ; 
		dices.add(new Dice(randomNumber));
	}
	
	public ArrayList<Territory> getTerritories() {
		return territories;
	}
	
}
