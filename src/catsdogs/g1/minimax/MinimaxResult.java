package catsdogs.g1.minimax;

public class MinimaxResult {
	private int turnDepth;
	private double score;
	private double score_max;
	private double score_min;
	
	public MinimaxResult(int turnDepth, double score) {
		this.turnDepth = turnDepth;
		this.score = score;
	}

	public int getTurnDepth() {
		return turnDepth;
	}

	public void setTurnDepth(int turnDepth) {
		this.turnDepth = turnDepth;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
}
