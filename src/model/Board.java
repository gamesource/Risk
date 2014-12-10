package model;

import interfaces.CardBehaviour;

import java.util.ArrayList;

import controller.ContinentNames;
import controller.SoldierFactory;
import controller.SoldierTypes;
import controller.TerritoryNames;

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
	
	public void initializeGame()
	{
		initializeCards();
		initializeContinents();
	}
	
	private void initializeCards()
	{
		int numberOfTotalCards = 42; 
		int numberForEachSoldier = 14;
		
		int count = 0;
		for(int i = 0 ; i < numberOfTotalCards ; i ++)
		{
			CardBehaviour newCard = new TwoSlotsCard(TerritoryNames.values()[i]);
			
			if(count < numberForEachSoldier)
			{
				newCard.addSoldier(SoldierTypes.footman);
			}
			else if(count < 2*numberForEachSoldier)
			{
				newCard.addSoldier(SoldierTypes.horseman);
			}
			else
			{
				newCard.addSoldier(SoldierTypes.cannon);
			}
			cards.add(newCard);
			count++;
		}
	}
	
	private void initializeContinents()
	{
		Continent northAmerica = new Continent(ContinentNames.north_america);
		Continent africa = new Continent(ContinentNames.africa);
		Continent asia = new Continent(ContinentNames.asia);
		Continent australia = new Continent(ContinentNames.australia);
		Continent southAmerica = new Continent(ContinentNames.south_america);
		Continent europe = new Continent(ContinentNames.europe);
		
		ArrayList<Territory> territories = new ArrayList<Territory>();
		
		northAmerica.addTerritories(territories);
		africa.addTerritories(territories);
		asia.addTerritories(territories);
		australia.addTerritories(territories);
		southAmerica.addTerritories(territories);
		europe.addTerritories(territories);
	}
}
