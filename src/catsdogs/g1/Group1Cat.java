package catsdogs.g1;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import catsdogs.sim.Move;

public class Group1Cat extends catsdogs.sim.CatPlayer {
	Evaluator evaluator = Settings.EVALUATOR;
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
	        return evaluator.evaluate(board, Evaluator.CAT);
//http://stackoverflow.com/questions/4252187/how-to-stop-execution-after-a-certain-time-in-java
	       
	}

}
