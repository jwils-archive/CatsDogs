package catsdogs.g1.minimax;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import catsdogs.g1.Evaluator;
import catsdogs.g1.Settings;
import catsdogs.sim.Cat;
import catsdogs.sim.CatPlayer;
import catsdogs.sim.Dog;
import catsdogs.sim.DogPlayer;
import catsdogs.sim.Move;
import catsdogs.sim.PossibleMove;

public class Mimimax extends Evaluator {
        private Logger logger = Logger.getLogger(this.getClass()); // for logging
        private static PossibleMove USELESS_POSSIBLE_MOVE = new PossibleMove(0,0,Integer.MIN_VALUE,null);
	//Make 3 players Cat DOg1 dog2.

	@Override
	public Move evaluate(int[][] board, int player) {
		//If we are given a board with a game that is already over this will not return a valid move.
		return evaluate(board, player, Settings.MAX_MINIMAX_DEPTH);
	}
	
	private PossibleMoveWithScore evaluate(int[][] board , int player, int depth) {
		if (Cat.wins(board) || Dog.wins(board)) {
			if (Dog.wins(board)) {
				//We make a possible move as a place holder. This move should not be used.
				return  new PossibleMoveWithScore(USELESS_POSSIBLE_MOVE, Integer.MIN_VALUE); //This only works for cats.
			} else {
				return  new PossibleMoveWithScore(USELESS_POSSIBLE_MOVE, Integer.MAX_VALUE); //This can be changed to favor winning sooner.
			}
		}
		
		if (depth == 0) {
			return new PossibleMoveWithScore(USELESS_POSSIBLE_MOVE, 0); 		
		}
		
		
		if (player == CAT) {
			return getBestMove(board, player, depth -1);
		} else {
			return getWorstMove(board, player, depth -1);
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
	
	private PossibleMoveWithScore getBestMove(int[][] board, int player, int depth) {
		PossibleMoveWithScore bestMove = null;
		
		for (PossibleMove possibleMove : getMoves(board, player)) {
			PossibleMoveWithScore tmpMove = evaluate(possibleMove.getBoard(), nextPlayer(player), depth);
			if (bestMove == null || tmpMove.getScore() > bestMove.getScore()) {			
			        bestMove = new PossibleMoveWithScore(possibleMove, tmpMove.getScore());
			        logger.info("current best move is " + bestMove);
			}
		}
		
		return bestMove;
	}
	
	private PossibleMoveWithScore getWorstMove(int[][] board, int player, int depth) {
		PossibleMoveWithScore worstMove = null;
		
		for (PossibleMove possibleMove : getMoves(board, player)) {
			PossibleMoveWithScore tmpMove = evaluate(possibleMove.getBoard(), player, depth);
			if (worstMove == null || tmpMove.getScore() < worstMove.getScore()) {
				worstMove = new PossibleMoveWithScore(possibleMove, tmpMove.getScore());
			}
		}
		
		return worstMove;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
