package catsdogs.g1;

import catsdogs.g1.heuristic.TwoInARowHeuristic;
import catsdogs.sim.Move;

public class Group1Cat extends catsdogs.sim.CatPlayer {
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
//	        TwoInARowHeuristic h = new TwoInARowHeuristic();
//	        h.evaluate(board, 0);
	        return evaluator.evaluate(board, Evaluator.CAT);
	}

}
