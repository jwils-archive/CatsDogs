package catsdogs.g1.heuristic;

import org.apache.log4j.Logger;

import catsdogs.sim.Board;

public class TwoInARowHeuristic extends Heuristic {
    private Logger logger = Logger.getLogger(this.getClass()); // for logging
    @Override
    public double evaluate(int[][] board, int playerMove) {
        final int catCount = 5;
        int cats = 0;
        for (int x = 0; x < Board.X; x++) {
            int count = 0;
            for (int y = 0; y < Board.Y; y++) {
                if (board[x][y] == Board.CAT)
                    count++;
                if (count == 2) {
                    cats++;
                }
            }
        }
        for (int y = 0; y < Board.Y; y++) {
            int count = 0;
            for (int x = 0; x < Board.X; x++) {
                if (board[x][y] == Board.CAT)
                    count++;
                if (count == 2) {
                    cats++;
                }
            }
        }
        //not sure this is correct, should this heuristic return a negative value when cats are 2 in a row for a cat?
        if (playerMove == CAT) {
            double hValueCat = -(double) catCount / (cats + catCount);
            logger.info("Cat TwoInARowHeuristic value is " + hValueCat);
            return hValueCat;
        } else {
            double hValueDog = -(double) catCount / (cats + catCount);
            logger.info("Dog TwoInARowHeuristic value is " + hValueDog);
            return hValueDog;
        }
    }
}
