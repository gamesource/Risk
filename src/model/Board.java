package model;

import interfaces.CardBehaviour;

import java.util.ArrayList;

import controller.SoldierFactory;

public class Board {

	private static Board instance ;
	private ArrayList<Player> players ; 
	private ArrayList<CardBehaviour> cards ;
	private SoldierFactory soldierFactory ;
	private ArrayList<Continent> continents ;
	
	private Board()
	{
		soldierFactory = new SoldierFactory();
	}

	public static synchronized Board getInstance()
	{
		if (instance == null)
			instance = new Board();

		return instance;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public boolean addPlayer(Player player) {
		return this.players.add(player);
	}

	public ArrayList<CardBehaviour> getCards() {
		return cards;
	}

	public void setCards(ArrayList<CardBehaviour> cards) {
		this.cards = cards;
	}

	public SoldierFactory getSoldierFactory() {
		return soldierFactory;
	}

	public ArrayList<Continent> getContinents() {
		return continents;
	}

	public boolean addContinent(Continent continent) {
		return this.continents.add(continent);
	}
}
