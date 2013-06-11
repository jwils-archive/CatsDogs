package catsdogs.g1.heuristic;

import org.apache.log4j.Logger;

import catsdogs.sim.Board;
import catsdogs.sim.Dog;

public class CatCanMakeLosingMoveHeuristic extends Heuristic {
    private Logger logger = Logger.getLogger(this.getClass()); // for logging
    @Override
    public double evaluate(int[][] board, int playerMove) {
        double possibleLosingMoves = 0;
        for (int x = 0; x < Board.X; x++) {
            for (int y = 0; y < Board.Y; y++) {
                if (board[x][y] == Board.CAT && y > 0 && board[x][y-1] == Board.EMPTY && board[x][y-1] == Board.CAT) {
                    int [][] moveLeftBoard = board;
                    moveLeftBoard[x][y] = Board.EMPTY;
                    moveLeftBoard[x][y-1] = Board.CAT;
                    if (Dog.wins(moveLeftBoard)){
                        possibleLosingMoves++;
                    }
                }
                if (board[x][y] == Board.CAT && y < 6 && board[x][y+1] == Board.EMPTY && board[x][y+1] == Board.CAT) {
                    int [][] moveUpBoard = board;
                    moveUpBoard[x][y] = Board.EMPTY;
                    moveUpBoard[x][y+1] = Board.CAT;
                    if (Dog.wins(moveUpBoard)){
                        possibleLosingMoves++;
                    }
                }
                if (board[x][y] == Board.CAT && x < 6 && board[x+1][y] == Board.EMPTY && board[x+1][y] == Board.CAT) {
                    int [][] moveRightBoard = board;
                    moveRightBoard[x][y] = Board.EMPTY;
                    moveRightBoard[x+1][y] = Board.CAT;
                    if (Dog.wins(moveRightBoard)){
                        possibleLosingMoves++;
                    }
                }
                if (board[x][y] == Board.CAT && x > 0 && board[x-1][y] ==  Board.EMPTY && board[x-1][y] == Board.CAT) {
                    int [][] moveLeftBoard = board;
                    moveLeftBoard[x][y] = Board.EMPTY;
                    moveLeftBoard[x-1][y] = Board.CAT;
                    if (Dog.wins(moveLeftBoard)){
                        possibleLosingMoves++;
                    }
                }
            }
        }
        if (playerMove == CAT) {
            double hValueCat = (double)possibleLosingMoves;
            logger.info("There are " + hValueCat + " moves that result in the dog winning");
            return hValueCat;
        } else {
            double hValueDog = -(double)possibleLosingMoves;
            logger.info("There are " + Math.abs(hValueDog) + " moves that result in the dog winning");
            return hValueDog;
        }
    }

}
