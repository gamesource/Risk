package controller;

import interfaces.CardBehaviour;
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
	
	public void draft(Territory territory)
	{
		if(soldiersToDraft.size() != 0)
		{
			territory.addSoldier(soldiersToDraft.get(0));
			soldiersToDraft.remove(0);
		}
		else
		{
			System.err.println("No more soldier to draft !");
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
			System.out.println("defenders army : 1");
			defender.addDice(new Dice());
		}
		else
		{
			System.out.println("defenders army : 2");
			defender.addDice(new Dice());
			defender.addDice(new Dice());
		}
		
		for(int i = 0 ; i < armySize ; i++)//Maximum three
		{
			attacker.addDice(new Dice());
		}
		
		System.out.println("attackers army : "+ armySize);
		
		attacker.roll();
		defender.roll();
		
		for(Dice dice : attacker.getDices()) {
			rolledNumbersOfAttacker.add(dice.getValue());
		}
		
		for(Dice dice : defender.getDices()) {
			rolledNumbersOfDefender.add(dice.getValue());
		}
		
		Collections.reverse(rolledNumbersOfAttacker);
		Collections.reverse(rolledNumbersOfDefender);
		
		for(int a : rolledNumbersOfAttacker)
		{
			System.out.println("attackers dice : " +a);
		}
		
		for(int a : rolledNumbersOfDefender)
		{
			System.out.println("defender dice : " + a);
		}
		
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
		
		System.out.println("losed army of attacker " + numberOfArmyLostByAttacker);
		System.out.println("losed army of defender " + numberOfArmyLostByDefender);
		
		attackersArmySize = attackersArmySize - numberOfArmyLostByAttacker;
		defendersArmySize = defendersArmySize - numberOfArmyLostByDefender;
		
		for(int i = 0 ; i< numberOfArmyLostByDefender; i++)
		{
			defendersTerritory.getSoldierList().remove(0);
		}
		
		if(defendersArmySize == 0)
		{
			defendersTerritory.setOwner(attakersTerritory.getOwner());
			attakersTerritory.getOwner().addTerritory(defendersTerritory);
			defendersTerritory.getOwner().removeTerritory(defendersTerritory);
			
			//If occupied all territories of continent,then bonus.
			Board board = Board.getInstance();
			List<Soldier> soldiers = board.getSoldierFactory().createSoldierByContinent(attakersTerritory.getOwner());
			
			for(Soldier soldier: soldiers)
			{
				defendersTerritory.addSoldier(soldier);
			}
			
			for(int i = 0 ; i< attackersArmySize ; i++)
			{
				defendersTerritory.addSoldier(new FootMan());
			}
			
			//Get all cards of player
			if(defendersTerritory.getOwner().getTerritories().size() == 0)
			{
				for(CardBehaviour card : defendersTerritory.getOwner().getCards())
				{
					attakersTerritory.getOwner().addCard(card);
				}
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
	
	public void fortify(Player player,Territory oldTerritory,Territory newTerritory,Soldier soldier)
	{
		newTerritory.addSoldier(soldier);
		oldTerritory.removeSoldier(soldier);
	}
	
	public void pass(Player player)
	{
		Board board = Board.getInstance();
		int playerIndex = board.getPlayers().indexOf(player);
		int playersSize = board.getPlayers().size();
		board.setCurrentPlayer(board.getPlayers().get((playerIndex+1) % playersSize));
	}
}
