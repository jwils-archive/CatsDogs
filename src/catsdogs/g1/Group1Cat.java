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
	        Thread thread = new Thread(evaluator);
		thread.start();
		try {
                thread.join((long) (4.5 * 1000));
                thread.interrupt();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    		
	    return evaluator.getBestMove();
	       
	}

}
