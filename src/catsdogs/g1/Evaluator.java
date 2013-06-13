package catsdogs.g1;

import catsdogs.sim.Move;

public abstract class Evaluator implements Runnable {
	public static final int CAT = 1;
	public static final int DOG1 = 2;
	public static final int DOG2 = 3;
	
	protected Move bestMove;

	
	public abstract Move evaluate(int[][] board, int playerMove);
	
	public Move getBestMove() {
		return bestMove;
	}
}
