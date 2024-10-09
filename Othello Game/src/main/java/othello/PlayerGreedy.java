package othello;
import java.util.ArrayList;

/**
 * PlayerGreedy makes a move by considering all possible moves that the player
 * can make. Each move leaves the player with a total number of tokens.
 * getMove() returns the first move which maximizes the number of
 * tokens owned by this player. In case of a tie, between two moves,
 * (row1,column1) and (row2,column2) the one with the smallest row wins. In case
 * both moves have the same row, then the smaller column wins.
 * 
 * Example: Say moves (2,7) and (3,1) result in the maximum number of tokens for
 * this player. Then (2,7) is returned since 2 is the smaller row.
 * 
 * Example: Say moves (2,7) and (2,4) result in the maximum number of tokens for
 * this player. Then (2,4) is returned, since the rows are tied, but (2,4) has
 * the smaller column.
 * 
 * See the examples supplied in the assignment handout.
 * 
 */

public class PlayerGreedy {
	private Othello othello;
	private char player;


	/**
	 * Constructor method for PlayerGreedy.
	 *
	 * @param othello board on which game is being played
	 * @param player player who is going to be using the greedy strategy
	 */
	public PlayerGreedy(Othello othello, char player) {

		this.othello = othello;
		this.player = player;
	}

	/**
	 * @return the move as a Move object that would leave the player with most amount of tokens
	 */
    public Move getMove() {
		ArrayList<String> possibleMoves = greedyHasMove(this.othello.board, this.player);

		int maxFlipped = -1;
		int maxFlippedRow = -1;
		int maxFlippedCol = -1;


		for (int i = 0; i < possibleMoves.size(); i++) {

			OthelloBoard copyBoard = new OthelloBoard(8);
			copyBoard.othelloBoardCopy(this.othello.board);

			String coordinates = possibleMoves.get(i);

			// given the string representation of the coordinates breaks them
			String[] parts = coordinates.split(",");

			// assign the int version of the coordinates to variable to work with
			int row = Integer.parseInt(parts[0]);
			int col = Integer.parseInt(parts[1]);
			int drow = Integer.parseInt(parts[2]);
			int dcol = Integer.parseInt(parts[3]);

			copyBoard.flip(row, col, drow, dcol, player);

			if (copyBoard.getCount(player) > maxFlipped) {
				maxFlippedRow = row;
				maxFlippedCol = col;
				maxFlipped = copyBoard.getCount(player);
			} else if (copyBoard.getCount(player) == maxFlipped) {
				if(row < maxFlippedRow) {
					maxFlippedRow = row;
					maxFlippedCol = col;
					maxFlipped = copyBoard.getCount(player);
				}else if (row == maxFlippedRow) {
					if(col < maxFlippedCol) {
						maxFlippedRow = row;
						maxFlippedCol = col;
						maxFlipped = copyBoard.getCount(player);
					}
				}
			}

		}

        return new Move(maxFlippedRow,maxFlippedCol);
    }

	/**
	 * @param board, the board to be copied
	 * @param player, the player to consider the moves for
	 * @return an ArrayList of all the moves possible for the player for their turn. Empty if none
	 */
	private static ArrayList<String> greedyHasMove(OthelloBoard board, char player) {
		ArrayList<String> movesAvailable = new ArrayList<>();

		for (int row = 0; row < board.getDimension(); row++) {
			for (int col = 0; col < board.getDimension(); col++) {
				for (int drow = -1; drow <= 1; drow++) {
					for (int dcol = -1; dcol <= 1; dcol++) {

						char whosMove = board.hasMove(row,col,drow,dcol);

						if(whosMove == player){
							String stringRow = String.valueOf(row);
							String stringCol = String.valueOf(col);
							String stringDrow = String.valueOf(drow);
							String stringDcol = String.valueOf(dcol);

							movesAvailable.add(stringRow + "," + stringCol + "," + stringDrow + "," + stringDcol);
						} else {
							continue;
						}
					}
				}
			}
		}

		return movesAvailable;
	}
}

