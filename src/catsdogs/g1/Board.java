package catsdogs.g1;

import javax.swing.border.Border;

public class Board {
    private int[][] board;
    private int hashCode;
    
    public Board(int[][] board){
        setBoard(board);
    }
    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
        setHashCode();
    }

    public int hashCode() {
    	return hashCode;
    }
    
    public boolean equals(Object obj) {
		return false;
    }
    
    private void setHashCode() {
    	//Center square
    	int hash = board[3][3];
    	int power = 3;
    	
    	for (int i = 0; i < 3;i++) {
    		//Row-Column
    		hash += power*(i + 1)*board[3][i];
    		hash += power*(i + 1)*board[i][3];
    		hash += power*(i + 1)*board[6 - i][3];
    		hash += power*(i + 1)*board[3][6 - i];
    	}
    	
    	power *= 9;
    	
    	for (int i = 0; i < 3; i++) {
    		//DIAG
    		hash += power*(i + 1)*board[i][i];
    		hash += power*(i + 1)*board[i][6 - i];
    		hash += power*(i + 1)*board[6 - i][i];
    		hash += power*(i + 1)*board[6 - i][6 - i];
    	}
    	
    	power *= 9;
    	
    	for (int i = 0; i < 3; i ++) {
    		power *= 18;
    		for (int j=i+1;j<3;j++) {
    			if(i != j) {
    				hash += power*(i + 1)*board[i][j];
    	    		hash += power*(i + 1)*board[i][6 - j];
    	    		hash += power*(i + 1)*board[6 - i][j];
    	    		hash += power*(i + 1)*board[6 - i][6 - j];
    	    		hash += power*(i + 1)*board[j][i];
    	    		hash += power*(i + 1)*board[j][6 - i];
    	    		hash += power*(i + 1)*board[6 - j][i];
    	    		hash += power*(i + 1)*board[6 - j][6 - i];
    			}
    		}
    	}
    	hashCode = hash;
	}

    private static int[][] rotateCW(int[][] board) {
        int[][] rotatedArray = new int[board.length][board[0].length];
        for (int i = 0; i < board[0].length; i++) {
            for (int j = board.length - 1; j >= 0; j--) {
                rotatedArray[i][j] = board[j][i];
            }
        }
        return rotatedArray;
    }
    
    private static int[][] reflect(int[][] board, int middle) {
        int[][] reflectedArray = new int[board.length][board[0].length];
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
            	reflectedArray[i][j] = board[board.length - i - 1][j];	
            }
        }
        return reflectedArray;
    }
    
    public static void main(String[] args) {
    	int[][] testArray = new int[][]{
    			{0,1,0,0,1,0,0},
    			{0,0,2,2,0,1,0},
    			{0,2,0,0,2,0,0},
    			{0,1,0,2,0,0,0},
    			{0,0,0,1,0,1,0},
    			{1,0,0,0,1,0,2},
    			{2,1,0,0,0,0,1}};
    	printBoard(testArray);
    			
    	printBoard(testArray);
    	
    	System.out.println(new Board(testArray).hashCode());
    	testArray = rotateCW(testArray);
    	System.out.println(new Board(testArray).hashCode());
    	testArray = rotateCW(testArray);
    	System.out.println(new Board(testArray).hashCode());
    	testArray = rotateCW(testArray);
    	System.out.println(new Board(testArray).hashCode());
    	testArray = reflect(testArray,3);

    	System.out.println(new Board(testArray).hashCode());
    	testArray = rotateCW(testArray);
    	System.out.println(new Board(testArray).hashCode());
    	testArray = rotateCW(testArray);
    	System.out.println(new Board(testArray).hashCode());
    	testArray = rotateCW(testArray);
    	System.out.println(new Board(testArray).hashCode());
    }
    
    public static void printBoard(int[][] board) {
    	for (int i =0; i < 7;i++) {
    		for(int j=0; j< 7; j++) {
    			System.out.print(board[i][j] + " ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    }

    private static boolean boardsEqual(int [][] thisBoard, int [][] thatBoard) {
        for (int i = 0; i < thisBoard.length; i++) {
            for (int j = 0; j < thisBoard[0].length; j++) {
                if (thisBoard[i][j] != thatBoard[i][j]){
                    return false;
                }
            }
        }
        return true;

    }
}
