package catsdogs.g1.database;

import java.io.File;
import java.util.ArrayList;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import catsdogs.g1.Board;
import catsdogs.g1.minimax.DogMove;
import catsdogs.g1.minimax.MinimaxResult;
import catsdogs.sim.Cat;
import catsdogs.sim.Dog;
import catsdogs.sim.PossibleMove;

public class TreeExplorer {
	BoardStore boardStore = new BoardStore();

	public static int CAT = 1;
	public static int DOG = 2;
	
	private static double WIN_VALUE = Double.MAX_VALUE/2;
    private static double LOSS_VALUE = -1*Double.MAX_VALUE/2;

	public static void main(String[] args) {

		TreeExplorer explorer = new TreeExplorer();
		explorer.explore();

	}

	public TreeExplorer() {
		boardStore = new BoardStore();
	}

	public void explore() {
		int[][] board = new int[8][8];
		int X = 7;
		int Y = 7;
		// place the dogs
		for (int x = 0; x < X; x++) {
			for (int y = 0; y < Y; y++) {
				board[x][y] = 2;
			}
		}

		// place the cats
		board[0][0] = 1;
		board[0][Y - 1] = 1;
		board[X - 1][0] = 1;
		board[X - 1][Y - 1] = 1;
		board[X / 2][Y / 2] = 1;

		alpha_beta(board, LOSS_VALUE, WIN_VALUE, 1, 0);
		boardStore.close();
	}

	public double alpha_beta(int[][] board, double alpha, double beta,
			int player, int depth) {
		// terminal check
		if ((Cat.wins(board) || Dog.wins(board))) {
			if ((Cat.wins(board) && player > CAT)
					|| (Dog.wins(board) && player == CAT)) {
				if (player == 1) {
					//boardStore.store(board, LOSS_VALUE);
				}
				return LOSS_VALUE; // This only works for cats.
			} else {
				if (player == 1) {
					//boardStore.store(board, WIN_VALUE);
				}
				return WIN_VALUE; // This can be changed to favor
												// winning sooner.
			}
		}

		
		if (player == 1) {
			
			double tmp_val = -boardStore.fetch(board);
			
			if (tmp_val != 0) {
				return tmp_val;
			}
				
			
		}
		
		ArrayList<PossibleMove> moves = getMoves(board, player);

		for (PossibleMove move : moves) {
			double tmp_val = -alpha_beta(move.getBoard(), -beta, -alpha, nextPlayer(player), depth + 1);
					

			if (tmp_val >= beta) {
				if (player == 1) {
					boardStore.store(board, tmp_val);
				}
				return tmp_val;
			}

			if (tmp_val > alpha) {
				alpha = tmp_val;
			}

		}
		if (player == 1) {
			boardStore.store(board, alpha);
		}
		if (depth < 20) {
			System.out.println("Finished Depth " + depth);
		}
		return alpha;
	}

	// TODO both dog players
	private ArrayList<PossibleMove> getMoves(int[][] board, int player) {
		if (player == CAT) {
			return Cat.allLegalMoves(board);
		} else if (player == DOG) {
			ArrayList<PossibleMove> moves = new ArrayList<PossibleMove>();
			for (PossibleMove move : Dog.allLegalMoves(board)) {
				for (PossibleMove secondMove : Dog.allLegalMoves(move
						.getBoard())) {
					DogMove dogMove = new DogMove(secondMove);
					dogMove.setFirstMove(move);
					moves.add(dogMove);
				}
			}
			return moves;
		} else {
			return null;
			// Error
		}
	}

	private int nextPlayer(int player) {
		// DOg1 -> dog2 -> cat -> dog1.
		if (player == CAT) {
			return DOG;
		} else if (player == DOG) {
			return CAT;
		} else {
			return -1;
		}
	}
}
