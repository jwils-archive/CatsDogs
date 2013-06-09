package catsdogs.g1.minimax;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import catsdogs.g1.Board;
import catsdogs.g1.Evaluator;
import catsdogs.g1.heuristic.*;
import catsdogs.sim.Cat;
import catsdogs.sim.CatPlayer;
import catsdogs.sim.Dog;
import catsdogs.sim.DogPlayer;
import catsdogs.sim.Move;
import catsdogs.sim.PossibleMove;

public class AlphaBeta extends Evaluator {
    CachedBoards cache = new CachedBoards();
    private Logger logger = Logger.getLogger(this.getClass()); // for logging
    Heuristic heuristic = new OpenSquaresAroundCatHeuristic();

    
	public AlphaBeta(CatPlayer cat, DogPlayer dog) {
		super(cat, dog);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Move evaluate(int[][] board, int playerMove) {
		 cache = new CachedBoards();
		alpha_beta(board,6, Double.MIN_VALUE, Double.MAX_VALUE, playerMove);
		ArrayList<PossibleMove> moves = getMoves(board, playerMove);
		double score = Double.MIN_VALUE;
		Move toMove = null;
		for (PossibleMove move : moves) {
			MinimaxResult result = cache.getResult(move.getBoard(), 5);
			logger.error(result.getScore());
			if( toMove == null || result.getScore() > score) {
				score = result.getScore();
				toMove = move;
				
			}
		}
		return toMove;
	}

	public double alpha_beta(int[][] board, int depth, double alpha, double beta, int player) {
		//terminal check
		
		if (Cat.wins(board) || Dog.wins(board)) {
			if (Cat.wins(board) && player > CAT || Dog.wins(board) && player == CAT) {
				//We make a possible move as a place holder. This move should not be used.
				return  Double.MIN_VALUE; //This only works for cats.
			} else {
				return Double.MAX_VALUE; //This can be changed to favor winning sooner.
			}
		}
		
		if (depth == 0) {
			return  0; //heuristic.evaluate(board, player); 		
		}
		
		
		ArrayList<PossibleMove> moves = getMoves(board, player);
		
		for (PossibleMove move : moves) {
			MinimaxResult result = cache.getResult(move.getBoard(), depth -1);
			double tmp_val;
			if (result != null) {
				tmp_val = result.getScore();
			} else{
				if (player == DOG1) {
					tmp_val = alpha_beta(move.getBoard(), depth -1, alpha, beta, nextPlayer(player));
				} else {
					tmp_val = -alpha_beta(move.getBoard(), depth -1, -beta, -alpha, nextPlayer(player));

				}
			}
			
			cache.setResult(new Board(move.getBoard()), new MinimaxResult(depth - 1, tmp_val));
			
			if (tmp_val >= beta)
				return tmp_val;
			if (tmp_val > alpha)
				alpha = tmp_val;
		}
		
		return alpha;
	}
	
	
	
	//TODO both dog players
	private ArrayList<PossibleMove> getMoves(int[][] board, int player) {
		if (player == CAT) {
			return Cat.allLegalMoves(board);
		} else if (player == DOG1 || player == DOG2) {
			return Dog.allLegalMoves(board);
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
			return DOG2;
		} else if (player == DOG2) {
			return CAT;
		} else {
			return -1;
		}
	}
}
