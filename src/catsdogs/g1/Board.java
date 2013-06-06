package catsdogs.g1;

public class Board {
    private int[][] board;
    Board(int[][] board){
        setBoard(board);
    }
    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

}
