package model;

import java.util.ArrayList;

import controller.SoldierFactory;

public class Board {

	private static Board instance ;
	private ArrayList<Player> players ; 
	private ArrayList<Card> cards ;
	private SoldierFactory soldierFactory ;
	private ArrayList<Continent> continents ;
	
	private Board()
	{
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

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public SoldierFactory getSoldierFactory() {
		return soldierFactory;
	}

	public void setSoldierFactory(SoldierFactory soldierFactory) {
		this.soldierFactory = soldierFactory;
	}

	public ArrayList<Continent> getContinents() {
		return continents;
	}

	public void setContinents(ArrayList<Continent> continents) {
		this.continents = continents;
	}
}
