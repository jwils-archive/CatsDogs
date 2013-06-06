package catsdogs.g1;

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
}
