package controller;

import interfaces.Soldier;

import java.awt.Color;
import java.util.ArrayList;

import state.State;
import model.Board;
import model.Continent;
import model.FootMan;
import model.Player;
import model.Territory;

public class TerritoryController {
	private static Board board = Board.getInstance();
	private static TurnPhrases turn_phrases = new TurnPhrases();
	
	public static Color getTerritoryColor(TerritoryNames name) {

		for(Player player : board.getPlayers()) {
			for(Territory territory : player.getTerritories()) {
				if(territory.getName() == name) {
					return player.getColor();
				}
			}
		}
		return null;
	}
	
	public static ArrayList<TerritoryNames> getNeighbours(TerritoryNames name, boolean enemy) {
		ArrayList<TerritoryNames> neighbours = new ArrayList<TerritoryNames>();
		
		Territory current_territory = queryTerritory(name);
		
		for(Territory neighbour : current_territory.getNeighbours()) {
			if(enemy) {
				if(isEnemyTerritory(neighbour)) {
					neighbours.add(neighbour.getName());
				}
			}
			else {
				if(!isEnemyTerritory(neighbour)) {
					neighbours.add(neighbour.getName());
				}
			}
		}
		
		return neighbours;
	}
	
	public static boolean isCurrentPlayersTerritory(TerritoryNames name) {
		Territory territory = queryTerritory(name);
		return !isEnemyTerritory(territory);
	}
	
	private static boolean isEnemyTerritory(Territory territory) {
		Player current_player = board.getCurrentPlayer();
		return !territory.getOwner().equals(current_player);
	}
	
	public static int getNumberOfArmy(TerritoryNames name) {
		int numberOfArmy = 0;
		
		Territory territory = queryTerritory(name);
		for(Soldier soldier : territory.getSoldierList()) {
			numberOfArmy += soldier.getSoldierStrength();
		}
		
		return numberOfArmy;
	}
	
	private static Territory queryTerritory(TerritoryNames name) {

		for(Continent continent : board.getContinents()) {
			for(Territory territory : continent.getTerritoryList()) {
				if(territory.getName() == name) {
					return territory;
				}
			}
		}
		
		return null;
	}
	
	public static void addSoldier(TerritoryNames name) {
		Player current_player = board.getCurrentPlayer();
		Territory territory = queryTerritory(name);

		turn_phrases.draft(current_player, territory);
	}
	
	public static void newTurn() {
		Player current_player = board.getCurrentPlayer();
		turn_phrases.setSoldiersToDraft(current_player);
	}
	
	public static void changeState(boolean pass) {
		if(pass) {
			board.pass();
		}
		else {
			board.next();
		}
		
		System.out.println(board.getCurrentState());
	}
	
	public static State getCurrentState() {
		return board.getCurrentState();
	}
	
	public static void changeCurrentPlayer() {
		Player current_player = board.getCurrentPlayer();
		turn_phrases.pass(current_player);
	}
	
}
