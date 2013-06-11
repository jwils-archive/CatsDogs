package catsdogs.g1.minimax;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.apache.log4j.Logger;

import catsdogs.g1.Board;
import catsdogs.sim.PossibleMove;

public class CachedBoards {
	private HashMap<Board, MinimaxResult> cache = new HashMap<Board, MinimaxResult>();
	private Logger logger = Logger.getLogger(this.getClass());

	public MinimaxResult getResult(Board gameBoard) {
		return cache.get(gameBoard);
	}

	public MinimaxResult getResult(int[][] board) {
		return getResult(new Board(board));
	}

	public MinimaxResult getResult(int[][] board, int depth) {
		MinimaxResult result = getResult(new Board(board));
		if (result != null
				&& (result.getTurnDepth() == depth
						|| result.getScore() > Double.MAX_VALUE / 2 || result
						.getScore() / 2 < Double.MIN_VALUE / 2)) {

			return result;
		}
		return null;
	}

	public boolean setResult(Board gameBoard, MinimaxResult result) {
		MinimaxResult oldResult = cache.get(gameBoard);
		if (oldResult == null
				|| oldResult.getTurnDepth() < result.getTurnDepth()) {
			cache.put(gameBoard, result);
			return true;
		}
		return false;
	}

	public ArrayList<PossibleMove> orderMoves(ArrayList<PossibleMove> moves) {
		Collections.sort(moves, new Comparator<PossibleMove>() {

			@Override
			public int compare(PossibleMove pm, PossibleMove pm2) {
				MinimaxResult result1 = getResult(new Board(pm.getBoard()));
				MinimaxResult result2 = getResult(new Board(pm2.getBoard()));
				
				if (result1 == null && result2 == null) {
					return 0;
				} else if (result2 == null) {
					return -1;
				} else if (result1 == null) {
					return 1;
				}
				return Double.compare(result2.getScore(), result1.getScore());
			}
			
		});
		return moves;
	}
	
	public void writeToFile() {
		FileOutputStream fos;
	    try {
	        fos = new FileOutputStream("test.cache");

	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	     oos.writeObject(cache);
	     oos.close();
	    } catch (FileNotFoundException e) {e.printStackTrace();
	    } catch (IOException e) {e.printStackTrace();
	    //} catch (ClassNotFoundException e) {e.printStackTrace();
	    }
	}
}
