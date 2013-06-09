package catsdogs.g1;

import catsdogs.sim.Move;

public class Group1Dog extends catsdogs.sim.DogPlayer {
	Evaluator evaluator = Settings.EVALUATOR;

	@Override
	public Move doMove1(int[][] board) {
		return evaluator.evaluate(board, 2);
	}

	@Override
	public Move doMove2(int[][] board) {
		// TODO Auto-generated method stub
		return evaluator.evaluate(board, 3);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Group 1 Dog";
	}

	@Override
	public void startNewGame() {
		// TODO Auto-generated method stub
		
	}



}
