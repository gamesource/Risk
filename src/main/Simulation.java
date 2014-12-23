package main;

import java.awt.Color;
import java.util.ArrayList;

import view.BoardView;
import model.Board;
import model.Continent;
import model.FootMan;
import model.Player;
import model.Territory;

public class Simulation {

	public static void main(String[] args) {
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		Player player = new Player("Player1");
		player.setColor(Color.cyan);
		
		Player player2 = new Player("Player2");
		player2.setColor(Color.green);
		
		players.add(player);
		players.add(player2);
		
		
		Board board = Board.getInstance();
		
		ArrayList<Territory> territories = new ArrayList<Territory>();
		for(Continent continent : board.getContinents()) {
			for(Territory territory : continent.getTerritoryList()) {
				
				territories.add(territory);
			}
		}
		
		
		for(Territory territory : territories) {
			players.get((int) (Math.random() * 2)).addTerritory(territory);
		}
		
		for(Territory territory : territories) {
			territory.addSoldier(new FootMan());
		}
		
		
		board.addPlayer(players.get(0));
		board.addPlayer(players.get(1));
		
		board.setCurrentPlayer(players.get(0));
		
		BoardView window = new BoardView();
		window.updatePanel();
		
	}

}
