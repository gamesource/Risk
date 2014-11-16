package model;

import java.util.ArrayList;

public class Player {

	private String name ;
	private ArrayList<Dice> dices ;
	private ArrayList<Territory> territories ;
	
	public Player(String name){
		this.name = name ;
		this.dices = new ArrayList<Dice>() ;
		this.territories = new ArrayList<Territory>() ;
	}
	
	public void roll()
	{
		int randomNumber = (int) (1 + Math.random() * 5) ; 
		dices.add(new Dice(randomNumber));
	}
}
