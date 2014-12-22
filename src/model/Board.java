package model;

import interfaces.CardBehaviour;

import java.util.ArrayList;
import java.util.Arrays;

import state.GameState;
import state.Draft;
import state.State;
import controller.ContinentNames;
import controller.SoldierFactory;
import controller.SoldierTypes;
import controller.TerritoryNames;

public class Board {
	
	private GameState state;
	private static Board instance ;
	private ArrayList<Player> players ; 
	private ArrayList<CardBehaviour> cards ;
	private SoldierFactory soldierFactory ;
	private ArrayList<Continent> continents ;
	private int counterForSetTrade = 1 ;
	private int additionalSoldierForEachSetTrade = 2 ;
	private int initialSoldierSizeForSetTrade = 2 ;
	private Player currentPlayer;/*After adding all player, 
									currentPlayer should be set and setSoldiersToDraft should be called immediately*/
	
	private Board()
	{
		state = Draft.getInstance();  //Default state
		soldierFactory = new SoldierFactory();
		players = new ArrayList<Player>();
		cards = new ArrayList<CardBehaviour>();
		continents = new ArrayList<Continent>();
		initializeGame();
	}

	public static synchronized Board getInstance()
	{
		if (instance == null)
			instance = new Board();

		return instance;
	}
	
	public void next() {
		this.state.next(this);
	}

	public void pass() {
		this.state.pass(this);
	}

	public void changeState(GameState state) {
		this.state = state;
	}
	
	public State getCurrentState() {
		return this.state.getState();
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
		
		//North America
		Territory alaska = new Territory(TerritoryNames.alaska);
		Territory alberta = new Territory(TerritoryNames.alberta);
		Territory central_america = new Territory(TerritoryNames.central_america);
		Territory eastern_united_states = new Territory(TerritoryNames.eastern_united_states);
		Territory greenland = new Territory(TerritoryNames.greenland);
		Territory northwest_territory = new Territory(TerritoryNames.northwest_territory);
		Territory ontario = new Territory(TerritoryNames.ontario);
		Territory quebec = new Territory(TerritoryNames.quebec);
		Territory western_united_states = new Territory(TerritoryNames.western_united_states);
		
		//Africa
		Territory congo = new Territory(TerritoryNames.congo);
		Territory east_africa = new Territory(TerritoryNames.east_africa);
		Territory egypt = new Territory(TerritoryNames.egypt);
		Territory madagascar = new Territory(TerritoryNames.madagascar);
		Territory north_africa = new Territory(TerritoryNames.north_africa);
		Territory south_africa = new Territory(TerritoryNames.south_africa);
		
		//Asia
		Territory afghanistan = new Territory(TerritoryNames.afghanistan);
		Territory china = new Territory(TerritoryNames.china);
		Territory india = new Territory(TerritoryNames.india);
		Territory irkutsk = new Territory(TerritoryNames.irkutsk);
		Territory japan = new Territory(TerritoryNames.japan);
		Territory kamchatka = new Territory(TerritoryNames.kamchatka);
		Territory middle_east = new Territory(TerritoryNames.middle_East);
		Territory mongolia = new Territory(TerritoryNames.mongolia);
		Territory siam = new Territory(TerritoryNames.siam);
		Territory siberia = new Territory(TerritoryNames.siberia);
		Territory ural = new Territory(TerritoryNames.ural);
		Territory yakutsk = new Territory(TerritoryNames.yakutsk);
		
		//Australia
		Territory eastern_australia = new Territory(TerritoryNames.eastern_australia);
		Territory indonesia = new Territory(TerritoryNames.indonesia);
		Territory new_guinea = new Territory(TerritoryNames.new_guinea);
		Territory western_australia = new Territory(TerritoryNames.western_australia);
		
		//South America
		Territory argentina = new Territory(TerritoryNames.argentina);
		Territory brazil = new Territory(TerritoryNames.brazil);
		Territory peru = new Territory(TerritoryNames.peru);
		Territory venezuela = new Territory(TerritoryNames.venezuela);
		
		//Europe
		Territory great_britain = new Territory(TerritoryNames.great_britain);
		Territory iceland = new Territory(TerritoryNames.iceland);
		Territory northern_europe = new Territory(TerritoryNames.northern_europe);
		Territory scandinavia = new Territory(TerritoryNames.scandinavia);
		Territory southern_europe = new Territory(TerritoryNames.southern_europe);
		Territory ukraine = new Territory(TerritoryNames.ukraine);
		Territory western_europe = new Territory(TerritoryNames.western_europe);
		
		//Neighbours initialization
		alaska.addNeighbour(northwest_territory);
		alaska.addNeighbour(alberta);
		alaska.addNeighbour(kamchatka);
		
		ontario.addNeighbour(northwest_territory);
		ontario.addNeighbour(alberta);
		ontario.addNeighbour(western_united_states);
		ontario.addNeighbour(eastern_united_states);
		ontario.addNeighbour(quebec);
		
		quebec.addNeighbour(northwest_territory);
		quebec.addNeighbour(ontario);
		quebec.addNeighbour(eastern_united_states);
		quebec.addNeighbour(greenland);
		
		alberta.addNeighbour(alaska);
		alberta.addNeighbour(northwest_territory);
		alberta.addNeighbour(ontario);
		alberta.addNeighbour(western_united_states);
		
		central_america.addNeighbour(western_united_states);
		central_america.addNeighbour(eastern_united_states);
		central_america.addNeighbour(venezuela);
		
		eastern_united_states.addNeighbour(western_united_states);
		eastern_united_states.addNeighbour(ontario);
		eastern_united_states.addNeighbour(quebec);
		eastern_united_states.addNeighbour(central_america);
		
		greenland.addNeighbour(northwest_territory);
		greenland.addNeighbour(quebec);
		greenland.addNeighbour(iceland);
		
		northwest_territory.addNeighbour(alaska);
		northwest_territory.addNeighbour(alberta);
		northwest_territory.addNeighbour(ontario);
		northwest_territory.addNeighbour(greenland);
		northwest_territory.addNeighbour(quebec);
		
		western_united_states.addNeighbour(alberta);
		western_united_states.addNeighbour(ontario);
		western_united_states.addNeighbour(eastern_united_states);
		western_united_states.addNeighbour(central_america);
		
		congo.addNeighbour(north_africa);
		congo.addNeighbour(east_africa);
		congo.addNeighbour(south_africa);
		
		east_africa.addNeighbour(congo);
		east_africa.addNeighbour(middle_east);
		east_africa.addNeighbour(madagascar);
		east_africa.addNeighbour(south_africa);
		east_africa.addNeighbour(north_africa);
		east_africa.addNeighbour(egypt);
		
		egypt.addNeighbour(north_africa);
		egypt.addNeighbour(east_africa);
		egypt.addNeighbour(middle_east);
		egypt.addNeighbour(southern_europe);
		
		madagascar.addNeighbour(east_africa);
		madagascar.addNeighbour(south_africa);
		
		north_africa.addNeighbour(western_europe);
		north_africa.addNeighbour(egypt);
		north_africa.addNeighbour(congo);
		north_africa.addNeighbour(brazil);
		north_africa.addNeighbour(east_africa);
		
		south_africa.addNeighbour(congo);
		south_africa.addNeighbour(east_africa);
		south_africa.addNeighbour(madagascar);
		
		afghanistan.addNeighbour(middle_east);
		afghanistan.addNeighbour(china);
		afghanistan.addNeighbour(ukraine);
		afghanistan.addNeighbour(ural);
		afghanistan.addNeighbour(india);
		
		china.addNeighbour(afghanistan);
		china.addNeighbour(siberia);
		china.addNeighbour(siam);
		china.addNeighbour(mongolia);
		china.addNeighbour(india);
		china.addNeighbour(ural);
		
		india.addNeighbour(middle_east);
		india.addNeighbour(afghanistan);
		india.addNeighbour(china);
		india.addNeighbour(siam);
		
		irkutsk.addNeighbour(siberia);
		irkutsk.addNeighbour(mongolia);
		irkutsk.addNeighbour(yakutsk);
		irkutsk.addNeighbour(kamchatka);
		
		japan.addNeighbour(mongolia);
		japan.addNeighbour(kamchatka);
		
		kamchatka.addNeighbour(yakutsk);
		kamchatka.addNeighbour(irkutsk);
		kamchatka.addNeighbour(mongolia);
		kamchatka.addNeighbour(japan);
		kamchatka.addNeighbour(alaska);
		
		middle_east.addNeighbour(southern_europe);
		middle_east.addNeighbour(ukraine);
		middle_east.addNeighbour(afghanistan);
		middle_east.addNeighbour(india);
		middle_east.addNeighbour(egypt);
		middle_east.addNeighbour(east_africa);
		
		mongolia.addNeighbour(siberia);
		mongolia.addNeighbour(irkutsk);
		mongolia.addNeighbour(kamchatka);
		mongolia.addNeighbour(japan);
		mongolia.addNeighbour(china);
		
		siam.addNeighbour(indonesia);
		siam.addNeighbour(china);
		siam.addNeighbour(india);
		
		siberia.addNeighbour(ural);
		siberia.addNeighbour(china);
		siberia.addNeighbour(irkutsk);
		siberia.addNeighbour(yakutsk);
		siberia.addNeighbour(mongolia);
		
		ural.addNeighbour(ukraine);
		ural.addNeighbour(afghanistan);
		ural.addNeighbour(china);
		ural.addNeighbour(siberia);
		
		yakutsk.addNeighbour(siberia);
		yakutsk.addNeighbour(irkutsk);
		yakutsk.addNeighbour(kamchatka);
		
		eastern_australia.addNeighbour(western_australia);
		eastern_australia.addNeighbour(new_guinea);
		
		indonesia.addNeighbour(siam);
		indonesia.addNeighbour(new_guinea);
		indonesia.addNeighbour(western_australia);
		
		new_guinea.addNeighbour(indonesia);
		new_guinea.addNeighbour(western_australia);
		new_guinea.addNeighbour(eastern_australia);
		
		western_australia.addNeighbour(indonesia);
		western_australia.addNeighbour(new_guinea);
		western_australia.addNeighbour(eastern_australia);
		
		argentina.addNeighbour(peru);
		argentina.addNeighbour(brazil);
		
		brazil.addNeighbour(venezuela);
		brazil.addNeighbour(peru);
		brazil.addNeighbour(argentina);
		brazil.addNeighbour(north_africa);
		
		peru.addNeighbour(venezuela);
		peru.addNeighbour(brazil);
		peru.addNeighbour(argentina);
		
		venezuela.addNeighbour(central_america);
		venezuela.addNeighbour(peru);
		venezuela.addNeighbour(brazil);
		
		great_britain.addNeighbour(iceland);
		great_britain.addNeighbour(scandinavia);
		great_britain.addNeighbour(northern_europe);
		great_britain.addNeighbour(western_europe);
		
		iceland.addNeighbour(scandinavia);
		iceland.addNeighbour(great_britain);
		iceland.addNeighbour(greenland);
		
		northern_europe.addNeighbour(great_britain);
		northern_europe.addNeighbour(ukraine);
		northern_europe.addNeighbour(western_europe);
		northern_europe.addNeighbour(southern_europe);
		northern_europe.addNeighbour(scandinavia);
		
		scandinavia.addNeighbour(iceland);
		scandinavia.addNeighbour(great_britain);
		scandinavia.addNeighbour(ukraine);
		scandinavia.addNeighbour(northern_europe);
		
		southern_europe.addNeighbour(western_europe);
		southern_europe.addNeighbour(northern_europe);
		southern_europe.addNeighbour(ukraine);
		southern_europe.addNeighbour(middle_east);
		southern_europe.addNeighbour(egypt);
		
		ukraine.addNeighbour(ural);
		ukraine.addNeighbour(afghanistan);
		ukraine.addNeighbour(middle_east);
		ukraine.addNeighbour(scandinavia);
		ukraine.addNeighbour(northern_europe);
		ukraine.addNeighbour(southern_europe);
		
		western_europe.addNeighbour(great_britain);
		western_europe.addNeighbour(northern_europe);
		western_europe.addNeighbour(southern_europe);
		western_europe.addNeighbour(north_africa);
		
		ArrayList<Territory> territories = new ArrayList<>(Arrays.asList(
					alaska,
					alberta,
					central_america,
					eastern_united_states,
					greenland,
					northwest_territory,
					ontario,
					quebec,
					western_united_states
		));
		northAmerica.addTerritories(territories);
		
		territories = new ArrayList<>(Arrays.asList(
				congo,
				east_africa,
				egypt,
				madagascar,
				north_africa,
				south_africa
		));
		africa.addTerritories(territories);
		
		territories = new ArrayList<>(Arrays.asList(
				afghanistan,
				china,
				india,
				irkutsk,
				japan,
				kamchatka,
				middle_east,
				mongolia,
				siam,
				siberia,
				ural,
				yakutsk
		));
		asia.addTerritories(territories);
		
		territories = new ArrayList<>(Arrays.asList(
				eastern_australia,
				indonesia,
				new_guinea,
				western_australia
		));
		australia.addTerritories(territories);
		
		territories = new ArrayList<>(Arrays.asList(
				argentina,
				brazil,
				peru,
				venezuela
		));
		southAmerica.addTerritories(territories);
		
		territories = new ArrayList<>(Arrays.asList(
				great_britain,
				iceland,
				northern_europe,
				scandinavia,
				southern_europe,
				ukraine,
				western_europe
		));
		europe.addTerritories(territories);
		
		this.continents.add(northAmerica);
		this.continents.add(africa);
		this.continents.add(asia);
		this.continents.add(australia);
		this.continents.add(southAmerica);
		this.continents.add(europe);
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getCounterForSetTrade() {
		return counterForSetTrade;
	}

	public void setCounterForSetTrade(int counterForSetTrade) {
		this.counterForSetTrade = counterForSetTrade;
	}

	public int getAdditionalSoldierForEachSetTrade() {
		return additionalSoldierForEachSetTrade;
	}

	public void setAdditionalSoldierForEachSetTrade(
			int additionalSoldierForEachSetTrade) {
		this.additionalSoldierForEachSetTrade = additionalSoldierForEachSetTrade;
	}

	public int getInitialSoldierSizeForSetTrade() {
		return initialSoldierSizeForSetTrade;
	}

	public void setInitialSoldierSizeForSetTrade(
			int initialSoldierSizeForSetTrade) {
		this.initialSoldierSizeForSetTrade = initialSoldierSizeForSetTrade;
	}
}
