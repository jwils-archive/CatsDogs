package catsdogs.g1.minimax;

import java.util.HashMap;

import catsdogs.g1.Board;

public class CachedBoards {
	private HashMap<Board,MinimaxResult> cache = new HashMap<Board,MinimaxResult>();
	
	public MinimaxResult getResult(Board gameBoard) {
		return cache.get(gameBoard);
	}
	
	public MinimaxResult getResult(int[][] board) {
		return getResult(new Board(board));
	}
	
	
	public MinimaxResult getResult(int[][] board, int depth) {
		MinimaxResult result = getResult(new Board(board));
		if (result != null && (result.getTurnDepth() == depth || result.getScore() > Double.MAX_VALUE/2 || result.getScore() / 2 < Double.MIN_VALUE/2)) {
		
			return result;
		}
		return null;
	}
	
	public boolean setResult(Board gameBoard, MinimaxResult result) {
		MinimaxResult oldResult = cache.get(gameBoard);
		if (oldResult == null || oldResult.getTurnDepth() < result.getTurnDepth()) {
			cache.put(gameBoard, result);
			return true;
		}
		return false;
	}
}
