package state;

import model.Board;

public class PlaceArmy extends GameState{

	private static PlaceArmy instance = null;
	private PlaceArmy() {}
	
	public static PlaceArmy getInstance() {
		if(instance == null) {
			instance = new PlaceArmy();
		}
		
		return instance;
	}
	
	@Override
	public void next(Board board) {
		changeState(board, Attack.getInstance());	
	}

	@Override
	public void pass(Board board) {
		changeState(board, PlaceArmy.getInstance());			
	}

	@Override
	public State getState() {
		return State.place_army;
	}

}
