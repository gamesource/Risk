package state;

import model.Board;

public class Attack extends GameState{
	
	private static Attack instance = null;
	private Attack() {}
	
	public static Attack getInstance() {
		if(instance == null) {
			instance = new Attack();
		}
		
		return instance;
	}

	@Override
	public void next(Board board) {
		changeState(board, Fortify.getInstance());		
	}

	@Override
	public void pass(Board board) {
		changeState(board, Draft.getInstance());
	}

	@Override
	public State getState() {
		return State.attack;
	}

}
