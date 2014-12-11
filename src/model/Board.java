package model;

import interfaces.CardBehaviour;

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.org.apache.bcel.internal.generic.DDIV;

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
		
		ArrayList<Territory> territories = new ArrayList<>(Arrays.asList(
					new Territory(TerritoryNames.alaska),
					new Territory(TerritoryNames.alberta),
					new Territory(TerritoryNames.central_america),
					new Territory(TerritoryNames.eastern_united_states),
					new Territory(TerritoryNames.greenland),
					new Territory(TerritoryNames.northwest_territory),
					new Territory(TerritoryNames.ontario),
					new Territory(TerritoryNames.quebec),
					new Territory(TerritoryNames.western_united_states)
		));
		northAmerica.addTerritories(territories);
		
		territories = new ArrayList<>(Arrays.asList(
				new Territory(TerritoryNames.congo),
				new Territory(TerritoryNames.east_africa),
				new Territory(TerritoryNames.egypt),
				new Territory(TerritoryNames.madagascar),
				new Territory(TerritoryNames.north_africa),
				new Territory(TerritoryNames.south_africa)
		));
		africa.addTerritories(territories);
		
		territories = new ArrayList<>(Arrays.asList(
				new Territory(TerritoryNames.afghanistan),
				new Territory(TerritoryNames.china),
				new Territory(TerritoryNames.india),
				new Territory(TerritoryNames.irkutsk),
				new Territory(TerritoryNames.japan),
				new Territory(TerritoryNames.kamchatka),
				new Territory(TerritoryNames.middle_East),
				new Territory(TerritoryNames.mongolia),
				new Territory(TerritoryNames.siam),
				new Territory(TerritoryNames.siberia),
				new Territory(TerritoryNames.ural),
				new Territory(TerritoryNames.yakutsk)
		));
		asia.addTerritories(territories);
		
		territories = new ArrayList<>(Arrays.asList(
				new Territory(TerritoryNames.eastern_australia),
				new Territory(TerritoryNames.indonesia),
				new Territory(TerritoryNames.new_guinea),
				new Territory(TerritoryNames.western_australia)
		));
		australia.addTerritories(territories);
		
		territories = new ArrayList<>(Arrays.asList(
				new Territory(TerritoryNames.argentina),
				new Territory(TerritoryNames.brazil),
				new Territory(TerritoryNames.peru),
				new Territory(TerritoryNames.venezuela)
		));
		southAmerica.addTerritories(territories);
		
		territories = new ArrayList<>(Arrays.asList(
				new Territory(TerritoryNames.great_britain),
				new Territory(TerritoryNames.iceland),
				new Territory(TerritoryNames.northern_europe),
				new Territory(TerritoryNames.scandinavia),
				new Territory(TerritoryNames.southern_europe),
				new Territory(TerritoryNames.ukraine),
				new Territory(TerritoryNames.western_europe)
		));
		europe.addTerritories(territories);
		
		this.continents.add(northAmerica);
		this.continents.add(africa);
		this.continents.add(asia);
		this.continents.add(australia);
		this.continents.add(southAmerica);
		this.continents.add(europe);
	}
}
