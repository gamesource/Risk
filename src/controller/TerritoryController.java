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
	static Board board = Board.getInstance();
	
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
					continue;
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
		Player currentPlayer = board.getCurrentPlayer();
		
		for(Territory territory : currentPlayer.getTerritories()) {
			if(territory.getName() == name) {
				return true;
			}
		}
		return false;
	}
	
	public static int getNumberOfArmy(TerritoryNames name) {
		int numberOfArmy = 0;
		
		Territory territory = queryTerritory(name);
		for(Soldier soldier : territory.getSoldierList()) {
			numberOfArmy += soldier.getSoldierStrength();
		}
		
		return numberOfArmy;
	}
	
	private static boolean isEnemyTerritory(Territory territory) {
		Player current_player = board.getCurrentPlayer();
		for(Territory enemy_territory : current_player.getTerritories()) {
			if(enemy_territory.equals(territory)) {
				return false;
			}
		}
		return true;
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
		Territory territory = queryTerritory(name);
		
		territory.addSoldier(new FootMan());
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
	
}
