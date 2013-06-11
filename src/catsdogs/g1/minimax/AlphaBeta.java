package catsdogs.g1.minimax;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import catsdogs.g1.Board;
import catsdogs.g1.Evaluator;
import catsdogs.g1.Settings;
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
    
    private static double WIN_VALUE = Double.MAX_VALUE/2;
    private static double LOSS_VALUE = Double.MIN_VALUE/2;

    
	public AlphaBeta(CatPlayer cat, DogPlayer dog) {
		super(cat, dog);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Move evaluate(int[][] board, int playerMove) {
		alpha_beta(board,Settings.MAX_MINIMAX_DEPTH, LOSS_VALUE, WIN_VALUE, playerMove);
		ArrayList<PossibleMove> moves = getMoves(board, playerMove);
		double score = Double.MIN_VALUE;
		Move toMove = null;
		for (PossibleMove move : moves) {
			MinimaxResult result = cache.getResult(move.getBoard(), 4);
			if( toMove == null || (result != null && result.getScore() > score)) {
				logger.error(result.getScore());
				score = result.getScore();
				toMove = move;
				
			}
		}
		
		//cache.writeToFile();
		return toMove;
	}

	public double alpha_beta(int[][] board, int depth, double alpha, double beta, int player) {
		//terminal check
		
		if (Cat.wins(board) || Dog.wins(board)) {
			if (Cat.wins(board) && player > CAT || Dog.wins(board) && player == CAT) {
				//We make a possible move as a place holder. This move should not be used.
				return  Double.MIN_VALUE/2; //This only works for cats.
			} else {
				return Double.MAX_VALUE/2; //This can be changed to favor winning sooner.
			}
		}
		
		if (depth == 0) {
			return heuristic.evaluate(board, player); 		
		}
		
		
		ArrayList<PossibleMove> moves = getMoves(board, player);
		//cache.orderMoves(moves);
		
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
		} else if (player == DOG1) {
//			ArrayList<PossibleMove> moves = new ArrayList<PossibleMove>();
//			for (PossibleMove move : Dog.allLegalMoves(board)) {
//				moves.addAll(Dog.allLegalMoves(move.getBoard()));
//			}
//			return moves;
			return Dog.allLegalMoves(board);
		} else if(player == DOG2) {
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
			//return CAT;
		} else if (player == DOG2) {
			return CAT;
		} else {
			return -1;
		}
	}
}
