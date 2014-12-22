package state;

import model.Board;

public class Draft extends GameState{

	private static Draft instance = null;
	private Draft() {}
	
	public static Draft getInstance() {
		if(instance == null) {
			instance = new Draft();
		}
		
		return instance;
	}
	
	@Override
	public void next(Board board) {
		changeState(board, Attack.getInstance());	
	}

	@Override
	public void pass(Board board) {
		changeState(board, Draft.getInstance());			
	}

	@Override
	public State getState() {
		return State.draft;
	}

}
