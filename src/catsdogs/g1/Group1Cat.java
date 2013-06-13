package catsdogs.g1;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import catsdogs.g1.minimax.AlphaBeta;
import catsdogs.g1.minimax.DogMove;
import catsdogs.sim.Move;

public class Group1Cat extends catsdogs.sim.CatPlayer {
	private Logger logger = Logger.getLogger(this.getClass()); // for logging
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
		Evaluator evaluator = new AlphaBeta(board, Evaluator.CAT);
		evaluator.evaluate(board, Evaluator.CAT);
		logger.error(evaluator.getBestMove());
	    return evaluator.getBestMove();
//http://stackoverflow.com/questions/4252187/how-to-stop-execution-after-a-certain-time-in-java
	       
	}

}
