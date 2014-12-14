package controller;

import interfaces.Soldier;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Player;
import model.Territory;

public class TurnPhrases {

	private List<Soldier> soldiersToDraft ;
	
	public void setSoldiersToDraft(Player player)
	{
		SoldierFactory soldierFactory = Board.getInstance().getSoldierFactory();
		
		soldiersToDraft = new ArrayList<Soldier>();
		soldiersToDraft = soldierFactory.createSoldier(player);
		soldiersToDraft.addAll(soldierFactory.createSoldierByContinent(player));
	}
	
	public void draft(Player player,Territory territory)
	{
		for(Territory aTerritory : player.getTerritories() )
		{
			if(aTerritory.getName() == territory.getName())
			{
				if(soldiersToDraft.size() != 0)
				{
					aTerritory.addSoldier(soldiersToDraft.get(0));
					soldiersToDraft.remove(0);
				}
				else
				{
					System.err.println("No more soldier to draft !");
				}
			}
		}
	}
	
	public void attack(Player player)
	{
		
	}
	
	public void fortify(Player player)
	{
		
	}
	
	public void pass(Player player)
	{
		Board board = Board.getInstance();
		int playerIndex = board.getPlayers().indexOf(player);
		int playersSize = board.getPlayers().size();
		board.setCurrentPlayer(board.getPlayers().get(playerIndex+1 % playersSize));
	}
}
