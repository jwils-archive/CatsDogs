package catsdogs.g1;

import catsdogs.sim.Move;

public class Group1Cat extends CatPlayer {
	Evaluator evaluator = Settings.EVALUATOR;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Group 1 Cat";
	}

	@Override
	public void startNewGame() {
		//Evaluator.innitialize?
		
	}

	@Override
	public Move doMove(int[][] board) {
		return evaluator.evaluate(board, Evaluator.CAT);
	}

}
