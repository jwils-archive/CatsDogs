package catsdogs.g1;

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
}
