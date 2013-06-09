package catsdogs.g1.heuristic;

import java.util.HashMap;

import catsdogs.g1.Evaluator;
import catsdogs.sim.CatPlayer;
import catsdogs.sim.DogPlayer;
import catsdogs.sim.Move;

public class CompositeHeuristic extends Heuristic {
	private HashMap<Heuristic,Double> heuristics;
	
	public CompositeHeuristic(){
		createHeuristics();
	}
	
	public CompositeHeuristic(HashMap<Heuristic,Double> heuristics) {
		this.heuristics = heuristics;
	}

	@Override
	public double evaluate(int[][] board, int playerMove) {
		double score = 0;
		for (Heuristic heuristic : heuristics.keySet()) {
			score += heuristics.get(heuristic) * heuristic.evaluate(board, playerMove);
		}
		return score;
	}

	private void createHeuristics() {
		heuristics = new HashMap<Heuristic,Double>();
		
		//add all heuristics and scores here
	}
}
