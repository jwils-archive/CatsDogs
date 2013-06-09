package catsdogs.g1.heuristic;


public abstract class Heuristic {
	public static int CAT = 1;
	public static int DOG = 2;
	public abstract double evaluate(int[][] board, int playerMove);
}
