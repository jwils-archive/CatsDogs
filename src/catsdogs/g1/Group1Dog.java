package catsdogs.g1;

import java.util.ArrayList;
import java.util.Random;

import catsdogs.g1.minimax.AlphaBeta;
import catsdogs.g1.minimax.DogMove;
import catsdogs.g1.minimax.Mimimax;
import catsdogs.sim.Cat;
import catsdogs.sim.Move;
import catsdogs.sim.PossibleMove;

public class Group1Dog extends catsdogs.sim.DogPlayer {
	
	private DogMove move;

	@Override
	public Move doMove1(int[][] board) {
            Evaluator evaluator = new AlphaBeta(board, Evaluator.DOG1);
            Thread thread = new Thread(evaluator);
            thread.start();
            try {
            thread.join((long) (4.5 * 1000));
            thread.interrupt();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        move = (DogMove) evaluator.getBestMove();
        
        if (move == null) {
			return randomMove(board);
		}
        
        return move.getFirstMove();
	}

	@Override
	public Move doMove2(int[][] board) {
		// TODO Auto-generated method stub
		
		if (move == null) {
			return randomMove(board);
		}
		return move;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Group 1 Dog final";
	}

	@Override
	public void startNewGame() {
		// TODO Auto-generated method stub
		
	}

	public Move randomMove(int[][] board) {
		ArrayList<PossibleMove> moves = Cat.allLegalMoves(board);
		Random r = new Random();
		int size = moves.size();
		int which = r.nextInt(size);
		
		return moves.get(which);
	}

}
