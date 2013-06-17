package catsdogs.g1.database;

import java.io.File;

import org.apache.log4j.Logger;

import catsdogs.g1.Board;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

public class BoardStore {
	BerkeleyDatabase<SolvedBoard> database;
	
	public BoardStore() {
		File envDir = new File("database");
		if (!envDir.exists()) {
			envDir.mkdir();
		}

		EnvironmentConfig envConf = new EnvironmentConfig();
		envConf.setAllowCreate(true);
		envConf.setReadOnly(false);
		Environment env = new Environment(envDir, envConf);
		
		database = new BerkeleyDatabase<SolvedBoard>(env,
				"saved_boards", SolvedBoard.class);
		
	}
	
	
	public void store(int[][] b, double score) {
		SolvedBoard board = new SolvedBoard(b, score);
		
		if (database.containsPrimaryKey(board.hashCode())) {
			SolvedBoard otherBoard = database.getByPrimaryKey(board.hashCode());
			
			if (otherBoard.score - board.score > 1000000) {
				board.score = 0;
				database.put(board);
				System.out.println("2 Different winners for this hash");
			}
		} else {
			database.put(board);
		}
		
	}
	
	public double fetch(int[][] board) {
		int hashCode = new SolvedBoard(board, 0).hashCode();
		if (database.containsPrimaryKey(hashCode)) {
			SolvedBoard b = database.getByPrimaryKey(hashCode);
			if (new Board(b.board).equals(new Board(board))) {
				return b.score;
			}
		}
		return 0;
	}
	
	public void close() {
		database.close();
	}
}
