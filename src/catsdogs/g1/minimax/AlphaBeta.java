package catsdogs.g1.minimax;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import catsdogs.g1.Evaluator;
import catsdogs.g1.heuristic.Heuristic;
import catsdogs.sim.Cat;
import catsdogs.sim.Dog;
import catsdogs.sim.Move;
import catsdogs.sim.PossibleMove;

public class AlphaBeta extends Evaluator {
    //CachedBoards cache = new CachedBoards();
    private Logger logger = Logger.getLogger(this.getClass()); // for logging
    Heuristic heuristic = null; //new CompositeHeuristic();
    
	private int max_depth = 4;
	
	private boolean testing = false;
	
	int[][] board;
	int playerToMove;
    
    private static double WIN_VALUE = Double.MAX_VALUE/2;
    private static double LOSS_VALUE = Double.MIN_VALUE/2;

    
	public AlphaBeta(int[][] board, int playerMove) {
		this.board = board;
		this.playerToMove = playerMove;
	}

	@Override
	public Move evaluate(int[][] board, int playerMove) {
		alpha_beta(board, max_depth, LOSS_VALUE - 10000, WIN_VALUE + 10000, playerMove);
		return bestMove;
	}

	public double alpha_beta(int[][] board, int depth, double alpha, double beta, int player) {
		//terminal check
		if ((Cat.wins(board) || Dog.wins(board))) {
			if ((Cat.wins(board) && player > CAT) || (Dog.wins(board) && player == CAT)) {
				//We make a possible move as a place holder. This move should not be used.
				return  Double.MIN_VALUE/2; //This only works for cats.
			} else {
				return Double.MAX_VALUE/2; //This can be changed to favor winning sooner.
			}
		}

		if (depth == 0) {
			if (heuristic != null) {
				return heuristic.evaluate(board, player); 
			} else {
				return 0;
			}
		} 
		
		ArrayList<PossibleMove> moves = getMoves(board, player);
		if (bestMove != null && depth == max_depth) {
			moves.add(0, (PossibleMove) bestMove);
		}
		for (PossibleMove move : moves) {
			MinimaxResult result = null; //cache.getResult(move.getBoard(), depth -1);
			double tmp_val;

			tmp_val = -alpha_beta(move.getBoard(), depth -1, -beta, -alpha, nextPlayer(player));
			
			if (tmp_val >= beta) {
				if (depth == max_depth) {
					bestMove = move;
				}
				return tmp_val;
			}
			
			if (tmp_val > alpha) {
				if (depth == max_depth) {
					bestMove = move;
				}
				alpha = tmp_val;
			}
		}
		
		return alpha;
	}
	
	
	
	//TODO both dog players
	private ArrayList<PossibleMove> getMoves(int[][] board, int player) {
		if (player == CAT) {
			return Cat.allLegalMoves(board);
		} else if (player == DOG1) {
			ArrayList<PossibleMove> moves = new ArrayList<PossibleMove>();
			for (PossibleMove move : Dog.allLegalMoves(board)) {
				for(PossibleMove secondMove : Dog.allLegalMoves(move.getBoard())) {
					DogMove dogMove = new DogMove(secondMove);
					dogMove.setFirstMove(move);
					moves.add(dogMove);
				}
			}
			return moves;
		} else {
			return null;
			//Error
		}
	}
	
	private int nextPlayer(int player) {
		//DOg1 -> dog2 -> cat -> dog1.
		if (player == CAT) {
			return DOG1;
		}else if (player == DOG1) {
			return CAT;
		} else {
			return -1;
		}
	}

	@Override
	public void run() {
		while(!testing) {
			evaluate(board, playerToMove);
			max_depth++;
		}
	}
}
