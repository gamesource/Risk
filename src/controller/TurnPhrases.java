package controller;

import interfaces.CardBehaviour;
import interfaces.Soldier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import view.DiceView;
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
	
	public int getSoldiersToDraftSize()
	{
		return soldiersToDraft.size();
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
			defender.addDice(new Dice());
		}
		else
		{
			defender.addDice(new Dice());
			defender.addDice(new Dice());
		}
		
		if(armySize >= 3)
		{
			for(int i = 0 ; i < 3 ; i++)//Maximum three
			{
				attacker.addDice(new Dice());
			}
		}
		else
		{
			for(int i = 0 ; i < armySize ; i++)
			{
				attacker.addDice(new Dice());
			}
		}
		
		attacker.roll();
		defender.roll();
		
		for(Dice dice : attacker.getDices()) {
			rolledNumbersOfAttacker.add(dice.getValue());
		}
		
		for(Dice dice : defender.getDices()) {
			rolledNumbersOfDefender.add(dice.getValue());
		}
		
		Collections.sort(rolledNumbersOfAttacker);
		Collections.sort(rolledNumbersOfDefender);
		
		Collections.reverse(rolledNumbersOfAttacker);
		Collections.reverse(rolledNumbersOfDefender);
		
		DiceView dice_view = new DiceView();
		dice_view.setVisible(true);
		
		for(int j = 0; j < rolledNumbersOfAttacker.size(); j++) {
			dice_view.setImage(j, getDiePath(rolledNumbersOfAttacker.get(j))); 
		}
		
		for(int k = 0; k < rolledNumbersOfDefender.size(); k++) {
			dice_view.setImage(k + 3, getDiePath(rolledNumbersOfDefender.get(k)));
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
		
		attackersArmySize = attackersArmySize - numberOfArmyLostByAttacker;
		defendersArmySize = defendersArmySize - numberOfArmyLostByDefender;
		
		for(int i = 0 ; i< numberOfArmyLostByDefender; i++)
		{
			defendersTerritory.getSoldierList().remove(0);
		}
		
		if(defendersArmySize == 0)
		{
			attacker.addTerritory(defendersTerritory);
			defender.removeTerritory(defendersTerritory);
			
			for(int i = 0 ; i< attackersArmySize ; i++)
			{
				defendersTerritory.addSoldier(new FootMan());
			}
			
			//Get all cards of player
			if(defender.getTerritories().size() == 0)
			{
				for(CardBehaviour card : defender.getCards())
				{
					attacker.addCard(card);
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
		
		attacker.resetDices();
		defender.resetDices();
	}
	
	public void fortify(Player player,Territory oldTerritory,Territory newTerritory,Soldier soldier)
	{
		if(oldTerritory.getSoldierList().size() > 1) {
			newTerritory.addSoldier(soldier);
			oldTerritory.removeSoldier(soldier);
		}
	}
	
	public void pass(Player player)
	{
		Board board = Board.getInstance();
		int playerIndex = board.getPlayers().indexOf(player);
		int playersSize = board.getPlayers().size();
		board.setCurrentPlayer(board.getPlayers().get((playerIndex+1) % playersSize));
	}
	
	private String getDiePath(int number) {
		String path = null;
		
		switch (number) {
			case 1:
				path = DieE.die1.getValue();
				break;
			case 2:
				path = DieE.die2.getValue();
				break;
			case 3:
				path = DieE.die3.getValue();
				break;
			case 4:
				path = DieE.die4.getValue();
				break;
			case 5:
				path = DieE.die5.getValue();
				break;
			case 6:
				path = DieE.die6.getValue();
				break;
			default:
				break;
			}
		return path;
	}
}
