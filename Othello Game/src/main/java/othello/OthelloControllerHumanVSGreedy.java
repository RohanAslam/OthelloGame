package othello;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a greedy strategy. 
 *
 */
public class OthelloControllerHumanVSGreedy {

	protected Othello othello;
	PlayerHuman player1;
	PlayerGreedy player2;

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with a user vs a Computer using a greedy algorithm at the console.
	 */
	public OthelloControllerHumanVSGreedy() {

		this.othello = new Othello();
		this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerGreedy(this.othello, OthelloBoard.P2);
	}

	/**
	 * Starts a game of Othello for a Human Vs Greedy Algorithm
	 */
	public void play() {

		while (!othello.isGameOver()) {
			this.report();

			Move move = null;
			char whosTurn = othello.getWhosTurn();

			if (whosTurn == OthelloBoard.P1)
				move = player1.getMove();
			if (whosTurn == OthelloBoard.P2)
				move = player2.getMove();

			this.reportMove(whosTurn, move);
			othello.move(move.getRow(), move.getCol());
		}
		this.reportFinal();
	}

	/**
	 * Prints a string informing about who made the move and where
	 *
	 * @param whosTurn the player whose turn it is
	 * @param move the move that the player is making. ex. (2,2)
	 */
	private void reportMove(char whosTurn, Move move) {
		System.out.println(whosTurn + " makes move " + move + "\n");
	}

	/**
	 * Prints a string reporting on the current status of the board. What the board looks like, number of
	 * pieces on the board per player and which players turn is next
	 */
	private void report() {

		String s = othello.getBoardString() + OthelloBoard.P1 + ":"
				+ othello.getCount(OthelloBoard.P1) + " "
				+ OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2) + "  "
				+ othello.getWhosTurn() + " moves next";
		System.out.println(s);
	}

	/**
	 * Prints a string reporting on the final status of the board after the game is over. Reports about
	 * what the board looks like, number of pieces on the board per player and which players won the game if any
	 */
	private void reportFinal() {

		String s = othello.getBoardString() + OthelloBoard.P1 + ":"
				+ othello.getCount(OthelloBoard.P1) + " "
				+ OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2)
				+ "  " + othello.getWinner() + " won\n";
		System.out.println(s);
	}

	/**
	 * Run main to play a Human (P1) against the computer P2. 
	 * The computer uses a greedy strategy, that is, it picks the first
	 * move which maximizes its number of token on the board.
	 * The output should be almost identical to that of OthelloControllerHumanVSHuman.
	 * @param args
	 */
	public static void main(String[] args) {
		OthelloControllerHumanVSGreedy oc = new OthelloControllerHumanVSGreedy();
		oc.play();
	}
}