package catsdogs.g1.minimax;

import catsdogs.g1.Evaluator;
import catsdogs.sim.Cat;
import catsdogs.sim.CatPlayer;
import catsdogs.sim.Dog;
import catsdogs.sim.DogPlayer;
import catsdogs.sim.Move;
import catsdogs.sim.PossibleMove;

public class NegaScout extends Evaluator {
	 private static PossibleMove USELESS_POSSIBLE_MOVE = new PossibleMove(0,0,Integer.MIN_VALUE,null);
	 


	@Override
	public Move evaluate(int[][] board, int playerMove) {
		// TODO Auto-generated method stub
		return null;
	}

//	function pvs(node, depth, a, b, color)
//    if node is a terminal node or depth = 0
//        return color x the heuristic value of node
//    for each child of node
//        score := -pvs(child, depth-1, -a-1, -b, -color)       (* search with a null window *)
//        if a < score < b and child is not first child        (* if it failed high,
//            score := -pvs(child, depth-1, -b, -a, -color)        do a full re-search *)
//        a := max(a, score)
//        if a ³ b
//            break                                            (* beta cut-off *)
//    return a
	private PossibleMoveWithScore negaScout(int[][] board, int depth, double alpha, double beta, int player, boolean isScouting) {
		if (Cat.wins(board) || Dog.wins(board)) {
			if (Dog.wins(board)) {
				//We make a possible move as a place holder. This move should not be used.
				return  new PossibleMoveWithScore(USELESS_POSSIBLE_MOVE, Integer.MIN_VALUE); //This only works for cats.
			} else {
				return new PossibleMoveWithScore(USELESS_POSSIBLE_MOVE, Integer.MAX_VALUE); //This can be changed to favor winning sooner.
			}
		} 
		
		if (depth == 0) {
			return new PossibleMoveWithScore(USELESS_POSSIBLE_MOVE, (int)getScore(board, player));
		}
		PossibleMoveWithScore tmpMove = null;
		//for each child
			//tmpMove = -negaScout(board, depth-1,-a-1,-b,-player, true)
			if (tmpMove != null && alpha < tmpMove.getScore() && tmpMove.getScore() < beta) {
				//tmpMove = -negaScout(board, depth-1,-a-1,-b,-player, isScouting)
				tmpMove = null;
			}
			
			alpha = Math.max(alpha, tmpMove.getScore());
			if (alpha >= beta) {
		//		break;
			}
		//End For loop
		return null;
		
	}
	
	private PossibleMoveWithScore checkIfKnownValue(int[][] board, int depth) {
		return null;
	}
	
	private double getScore(int[][] board, int player) {
		return 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
