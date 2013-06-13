package catsdogs.g1;

import catsdogs.g1.minimax.AlphaBeta;
import catsdogs.g1.minimax.DogMove;
import catsdogs.g1.minimax.Mimimax;
import catsdogs.sim.Move;

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
        return move.getFirstMove();
	}

	@Override
	public Move doMove2(int[][] board) {
		// TODO Auto-generated method stub
		return move;
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
