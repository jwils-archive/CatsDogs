package catsdogs.g1.minimax;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MinimaxResult implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public String toString() {
		return "Result: \n\tDepth:" + turnDepth + " \n\tScore: " + score + "\n\n";
	}
}
