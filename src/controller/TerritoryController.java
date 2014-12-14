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
	
	public static ArrayList<TerritoryNames> getNeighbours(TerritoryNames name) {
		ArrayList<TerritoryNames> neighbours = new ArrayList<TerritoryNames>();
		
		Territory current_territory = null;
		for(Continent continent : board.getContinents()) {
			for(Territory territory : continent.getTerritoryList()) {
				if(territory.getName() == name) {
					current_territory = territory;
					break;
				}
			}
			if(current_territory != null) {
				break;
			}
		}
		for(Territory neighbour : current_territory.getNeighbours()) {
			neighbours.add(neighbour.getName());
		}
		
		return neighbours;
	}
}
