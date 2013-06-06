package catsdogs.g1;

import javax.swing.border.Border;

public class Board {
    private int[][] board;
    public Board(int[][] board){
        setBoard(board);
    }
    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int hashCode() {
    	return 0;
    }
    
    public boolean equals(Object obj) {
		return false;
    }
    
    private int[][] rotateCW(int[][] board) {
        int[][] rotatedArray = new int[board.length][board[0].length];
        for (int i = 0; i < board[0].length; i++) {
            for (int j = board.length - 1; j >= 0; j--) {
                rotatedArray[i][j] = board[j][i];
            }
        }
        return rotatedArray;
    }
    
    private int[][] reflect(int[][] board, int middle) {
        int[][] reflectedArray = new int[board.length][board[0].length];
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i < middle) {
                    reflectedArray[i][j] = board[board.length - i][j];
                } else {
                    reflectedArray[i][j] = board[board.length + i][j];
                }
                reflectedArray[i][j] = board[j][i];
            }
        }
        return reflectedArray;
    }
}
