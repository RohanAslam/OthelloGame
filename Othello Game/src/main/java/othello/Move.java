package othello;
/**
 * Class to capture a move made by a player.
 *
 */
public class Move {
	private int row, col;

	/**
	 * Constructor for Move. Initializes row and col to the ones provided
	 *
	 * @param row the row to be initialized
	 * @param col  the col to be initialized
	 */
	public Move(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 *
	 * @return the row where the move is made
	 */
	public int getRow() {
		return row;
	}

	/**
	 *
	 * @return the col where the move is made
	 */
	public int getCol() {
		return col;
	}


	/**
	 *
	 * @return a string representation of the move including row and col
	 */
	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}
}
