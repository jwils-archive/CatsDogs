package catsdogs.g1;

import catsdogs.sim.Move;
import catsdogs.sim.Player;

public abstract class Evaluator {
	public static final int CAT = 1;
	public static final int DOG1 = 2;
	public static final int DOG2 = 3;
	
	private CatPlayer cat;
	private DogPlayer dog;
	
	public Evaluator(CatPlayer cat, DogPlayer dog) {
		this.cat = cat;
		this.dog = dog;
	}

	public abstract Move evaluate(int[][] board, int playerMove);
	
	public DogPlayer getDog() {
		return dog;
	}

	public void setDog(DogPlayer dog) {
		this.dog = dog;
	}
	
	public CatPlayer getCat() {
		return cat;
	}

	public void setCat(CatPlayer cat) {
		this.cat = cat;
	}
}
