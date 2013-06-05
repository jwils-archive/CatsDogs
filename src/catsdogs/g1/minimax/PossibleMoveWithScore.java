package catsdogs.g1.minimax;

import catsdogs.sim.PossibleMove;

public class PossibleMoveWithScore extends PossibleMove {
	private int score;

	public PossibleMoveWithScore(PossibleMove move, int score) {
		super(move.getX(), move.getY(), move.getDirection(), move.getBoard());
		this.score = score;
	}

	public int getScore() {
		return score;
	}
	
	@Override 
	public String toString() {
	    return "(" + this.getX() + "," + this.getY() +"). Heading  " + this.getDirection() + " with a score of " + this.score + ".";
	}
}
