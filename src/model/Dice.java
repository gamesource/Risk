package model;

public class Dice {

	private int value ;
	
	public Dice() {
	}

	public int getValue() {
		return value;
	}
	
	public void roll() {
		int randomNumber = (int) (1 + Math.random() * 6); 
		value = randomNumber;
	}
}
