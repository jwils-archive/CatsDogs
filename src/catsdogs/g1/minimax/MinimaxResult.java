package catsdogs.g1.minimax;

public class MinimaxResult {
	private int turnDepth;
	private int score;
	
	public MinimaxResult(int turnDepth, int score) {
		this.turnDepth = turnDepth;
		this.score = score;
	}

	public int getTurnDepth() {
		return turnDepth;
	}

	public void setTurnDepth(int turnDepth) {
		this.turnDepth = turnDepth;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
