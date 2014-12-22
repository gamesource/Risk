package state;

import model.Board;

public class Fortify extends GameState{

	private static Fortify instance = null;
	private Fortify() {}
	
	public static Fortify getInstance() {
		if(instance == null) {
			instance = new Fortify();
		}
		
		return instance;
	}
	
	@Override
	public void next(Board board) {
		changeState(board, Draft.getInstance());			
	}

	@Override
	public void pass(Board board) {
		changeState(board, Draft.getInstance());			
	}

	@Override
	public State getState() {
		return State.fortify;
	}

}
