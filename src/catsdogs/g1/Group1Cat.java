package catsdogs.g1;

import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;

import catsdogs.g1.minimax.AlphaBeta;
import catsdogs.sim.Cat;
import catsdogs.sim.Move;
import catsdogs.sim.PossibleMove;

public class Group1Cat extends catsdogs.sim.CatPlayer {
	private Logger logger = Logger.getLogger(this.getClass()); // for logging
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Group 1 Cat final";
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
    		
		if (evaluator.getBestMove() == null) {
			return randomMove(board);
		}
	    return evaluator.getBestMove();
	       
	}
	
	public Move randomMove(int[][] board) {
		ArrayList<PossibleMove> moves = Cat.allLegalMoves(board);
		Random r = new Random();
		int size = moves.size();
		int which = r.nextInt(size);
		
		return moves.get(which);
	}

}
