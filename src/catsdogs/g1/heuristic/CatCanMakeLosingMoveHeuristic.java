package catsdogs.g1.heuristic;

import java.util.Arrays;

import org.apache.log4j.Logger;

import catsdogs.sim.Board;
import catsdogs.sim.Cat;
import catsdogs.sim.Dog;

public class CatCanMakeLosingMoveHeuristic extends Heuristic {
    private Logger logger = Logger.getLogger(this.getClass()); // for logging
    @Override
    public double evaluate(int[][] board, int playerMove) {
        double possibleLosingMoves = 0;
        for (int x = 0; x < Board.X; x++) {
            for (int y = 0; y < Board.Y; y++) {
                if (board[x][y] == Board.CAT && y > 0 && board[x][y-1] != Board.CAT) {
                    int [][] moveUpBoard = copy(board);
                    moveUpBoard[x][y] = Board.EMPTY;
                    moveUpBoard[x][y-1] = Board.CAT;
                    if (Dog.wins(moveUpBoard)){
                        possibleLosingMoves++;
                    }
                }
                if (board[x][y] == Board.CAT && y < 6 && board[x][y+1] != Board.CAT) {
                    int [][] moveDownBoard = copy(board);
                    moveDownBoard[x][y] = Board.EMPTY;
                    moveDownBoard[x][y+1] = Board.CAT;
                    if (Dog.wins(moveDownBoard)){
                        possibleLosingMoves++;
                    }
                }
                if (board[x][y] == Board.CAT && x < 6 && board[x+1][y] != Board.CAT) {
                    int [][] moveRightBoard = copy(board);
                    moveRightBoard[x][y] = Board.EMPTY;
                    moveRightBoard[x+1][y] = Board.CAT;
                    if (Dog.wins(moveRightBoard)){
                        possibleLosingMoves++;
                    }
                }
                if (board[x][y] == Board.CAT && x > 0 && board[x-1][y] !=  Board.CAT) {
                    int [][] moveLeftBoard = copy(board);
                    moveLeftBoard[x][y] = Board.EMPTY;
                    moveLeftBoard[x-1][y] = Board.CAT;
                    if (Dog.wins(moveLeftBoard)){
                        possibleLosingMoves++;
                    }
                }
            }
        }
        if (playerMove == CAT) {
            double hValueCat = (double)possibleLosingMoves/Cat.allLegalMoves(board).size();
            return hValueCat;
        } else {
            double hValueDog = -(double)possibleLosingMoves/Cat.allLegalMoves(board).size();
            return hValueDog;
        }
    }

    public int[][] copy(int[][] input) {
        int[][] target = new int[input.length][];
        for (int i=0; i <input.length; i++) {
          target[i] = Arrays.copyOf(input[i], input[i].length);
        }
        return target;
  }
}
