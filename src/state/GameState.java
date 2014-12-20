package state;

import model.Board;

public abstract class GameState {
	public abstract void next(Board board);
	public abstract void pass(Board board);
	public abstract State getState();
	protected void changeState(Board board, GameState state) {
		board.changeState(state);
	}
}
