package catsdogs.g1.heuristic;

public class OpenSquaresAroundCatHeuristic extends Heuristic {

	@Override
	public double evaluate(int[][] board, int playerMove) {
		int dogs = 0;
		int empty = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == CAT) {
					if (i > 0) {
						if (board[i-1][j] == DOG) {
							dogs++;
						} else {
							empty++;
						}
					}
					
					if (i < board.length - 1) {
						if (board[i+1][j] == DOG) {
							dogs++;
						} else {
							empty++;
						}
					}
					
					if (j < board.length - 1) {
						if (board[i][j+1] == DOG) {
							dogs++;
						} else {
							empty++;
						}
					}
					
					if (j > 0) {
						if (board[i][j-1] == DOG) {
							dogs++;
						} else {
							empty++;
						}
					}
				}
			}
		}
		if (playerMove == CAT) {
			return (double)empty/(dogs + empty);
		} else {
			return -(double)empty/(dogs + empty); 
		}
	}

}
