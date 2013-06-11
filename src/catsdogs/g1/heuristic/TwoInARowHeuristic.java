package catsdogs.g1.heuristic;

import org.apache.log4j.Logger;

import catsdogs.sim.Board;

public class TwoInARowHeuristic extends Heuristic {
    private Logger logger = Logger.getLogger(this.getClass()); // for logging
    @Override
    public double evaluate(int[][] board, int playerMove) {
        final int catCount = 5;
        double cats = 0;
        for (int x = 0; x < Board.X; x++) {
            int count = 0;
            for (int y = 0; y < Board.Y; y++) {
                if (board[x][y] == Board.CAT)
                    count++;
                if (count == 2) {
                    cats++;
                    break;
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
                    break;
                }
            }
        }
        //not sure this is correct, should this heuristic return a negative value when cats are 2 in a row for a cat?
        if (playerMove == CAT) {
            double hValueCat = (double)cats;
            logger.info("There are " + hValueCat + " rows and columns that contain 2 or more cats");
            return hValueCat;
        } else {
            double hValueDog = -(double)cats;
            logger.info("There are " + Math.abs(hValueDog) + " rows and columns that contain 2 or more cats");
            return hValueDog;
        }
    }
}
