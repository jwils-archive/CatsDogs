package catsdogs.g1.database;

import java.io.Serializable;

import catsdogs.g1.Board;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

@Entity
public class SolvedBoard implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	int hash;
	
	double score;
	
	
	int[][] board;
	
	public SolvedBoard() {}
	
	public SolvedBoard(Board b, double s) {
		board = b.getBoard();
		hash = b.hashCode();
		score = s;
	}
	
	public SolvedBoard(int[][] board, double s) {
		this(new Board(board), s);
	}

	public Board getBoard() {
		return new Board(board);
	}
}
