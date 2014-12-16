package controller;

import java.awt.Color;
import java.util.ArrayList;

import model.Board;
import model.Continent;
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

		for(Player player : board.getPlayers()) {
			for(Territory territory : player.getTerritories()) {
				if(territory.getName() == name) {
					return territory;
				}
			}
		}
		
		return null;
	}
}
