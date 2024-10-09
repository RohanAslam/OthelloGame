package othello;

import java.util.ArrayList;
import java.util.Random;

/**
 * PlayerRandom makes a move by first determining all possible moves that this
 * player can make, putting them in an ArrayList, and then randomly choosing one
 * of them.
 *
 */
public class PlayerRandom {
	private Othello othello;
	private char player;
	private Random rand = new Random();

	/**
	 * Constructor method for PlayerRandom.
	 *
	 * @param othello board on which game is being played
	 * @param player player who is going to be using the random strategy
	 */
	public PlayerRandom(Othello othello, char player) {
		this.othello = othello;
		this.player = player;
	}

	/**
	 * @return a random move as a Move object from all possible moves. (-1,-1) is no moves are possible.
	 */
	public Move getMove() {
		ArrayList<String> possibleMoves = randomHasMove(this.othello.board, this.player);

		if (possibleMoves.isEmpty()) {
			return new Move(-1,-1);
		}

		// choose a random number from 0 - possibleMoves.size() - 1
		int randomMove = rand.nextInt(possibleMoves.size());

		// get a random move
		String coordinates = possibleMoves.get(randomMove);

		// break string up to be stored as int
		String[] parts = coordinates.split(",");
		int row = Integer.parseInt(parts[0]);
		int col = Integer.parseInt(parts[1]);

		return new Move(row,col);
	}

	/**
	 * @param board, the board to be copied
	 * @param player, the player to consider the moves for
	 * @return an ArrayList of all the moves possible for the player for their turn. Empty if none.
	 */
	private static ArrayList<String> randomHasMove(OthelloBoard board, char player) {
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

							// add move to array as a string
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
