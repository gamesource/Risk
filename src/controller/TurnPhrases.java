package controller;

import interfaces.Soldier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Board;
import model.Dice;
import model.FootMan;
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
	
	public void attack(Territory attakersTerritory,Territory defendersTerritory,int armySize)//Adding or removing soldier can be done in booard also.
	{
		int attackersArmySize = armySize;
		int defendersArmySize = defendersTerritory.getSoldierList().size();
		List<Integer> rolledNumbersOfAttacker = new ArrayList<Integer>();
		List<Integer> rolledNumbersOfDefender = new ArrayList<Integer>();
		
		Player attacker = attakersTerritory.getOwner();
		Player defender = defendersTerritory.getOwner();
		
		int numberOfArmyLostByDefender = 0;
		int numberOfArmyLostByAttacker = 0;
		
		if(defendersArmySize == 1)
		{
			defender.addDice(new Dice());
		}
		else
		{
			defender.addDice(new Dice());
			defender.addDice(new Dice());
		}
		
		for(int i = 0 ; i < armySize ; i++)//Maximum three
		{
			attacker.addDice(new Dice());
		}
		
		attacker.roll();
		defender.roll();
		
		for(Dice dice : attacker.getDices()) {
			rolledNumbersOfAttacker.add(dice.getValue());
		}
		
		for(Dice dice : defender.getDices()) {
			rolledNumbersOfAttacker.add(dice.getValue());
		}
		
		Collections.sort(rolledNumbersOfAttacker);
		Collections.sort(rolledNumbersOfDefender);
		
		if(rolledNumbersOfDefender.size() == 1)
		{
			if(rolledNumbersOfDefender.get(0) < rolledNumbersOfAttacker.get(0))
			{
				numberOfArmyLostByDefender++;
			}
			else
			{
				numberOfArmyLostByAttacker++;
			}
		}
		else
		{
			for(int i = 0 ; i < rolledNumbersOfDefender.size(); i++)
			{
				if(rolledNumbersOfDefender.get(i) < rolledNumbersOfAttacker.get(i))
				{
					numberOfArmyLostByDefender++;
				}
				else
				{
					numberOfArmyLostByAttacker++;
				}
			}
		}
		
		attackersArmySize = attackersArmySize - numberOfArmyLostByAttacker;
		defendersArmySize = defendersArmySize - numberOfArmyLostByDefender;
		
		for(int i = 0 ; i< numberOfArmyLostByDefender; i++)
		{
			defendersTerritory.getSoldierList().remove(i);
		}
		
		if(defendersArmySize == 0)
		{
			defendersTerritory.setOwner(attakersTerritory.getOwner());
			attakersTerritory.getOwner().addTerritory(defendersTerritory);
			
			//bonus for occupy new territory.

			//If occupied all territories of continent,then bonus.
			Board board = Board.getInstance();
			List<Soldier> soldiers = board.getSoldierFactory().createSoldierByContinent(attacker);
			
			for(Soldier soldier: soldiers)
			{
				defendersTerritory.addSoldier(soldier);
			}
			
			for(int i = 0 ; i< attackersArmySize ; i++)
			{
				defendersTerritory.addSoldier(new FootMan());
			}
		}
		else
		{
			for(int i = 0 ; i< attackersArmySize ; i++)
			{
				attakersTerritory.addSoldier(new FootMan());
			}
		}
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
