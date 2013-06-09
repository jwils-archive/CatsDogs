package catsdogs.g1;

import catsdogs.g1.minimax.AlphaBeta;
import catsdogs.g1.minimax.Mimimax;

public class Settings {
	public static int MAX_MINIMAX_DEPTH = 5;
	public static Evaluator EVALUATOR = new AlphaBeta(null, null);

}
