package catsdogs.g1.minimax;

import catsdogs.sim.PossibleMove;

public class DogMove extends PossibleMove {
	public DogMove(PossibleMove move1) {
		super(move1.getX(), move1.getY(), move1.getDirection(), move1.getBoard());
		// TODO Auto-generated constructor stub
	}

	private PossibleMove firstMove;
	
	public void setFirstMove(PossibleMove dogmove) {
		firstMove = dogmove;
	}
	
	public PossibleMove getFirstMove() {
		return firstMove;
	}
}
