package othello;

/**
 * The goal here is to print out the probability that Random wins and Greedy
 * wins as a result of playing 10000 games against each other with P1=Random and
 * P2=Greedy. What is your conclusion, which is the better strategy?
 *
 */
public class OthelloControllerRandomVSGreedy {

	/**
	 * Run main to execute the simulation and print out the two line results.
	 * Output looks like: 
	 * Probability P1 wins=.75 
	 * Probability P2 wins=.20
	 * @param args
	 */

	protected Othello othello;
	PlayerRandom player1;
	PlayerGreedy player2;

	public OthelloControllerRandomVSGreedy() {

		this.othello = new Othello();
		this.player1 = new PlayerRandom(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerGreedy(this.othello, OthelloBoard.P2);
	}

	/**
	 * Starts a game of Othello for a Random algorithm Vs Greedy algorithm
	 */
	public void play() {

		while (!othello.isGameOver()) {

			Move move = null;
			char whosTurn = othello.getWhosTurn();

			if (whosTurn == OthelloBoard.P1)
				move = player1.getMove();
			if (whosTurn == OthelloBoard.P2)
				move = player2.getMove();

			if (move.getCol() == -1 || move.getRow() == -1){
				othello.switchTurn();

			} else{
				othello.move(move.getRow(), move.getCol());
			}

		}
	}

	/**
	 * Returns the winner of the game has ended if there is one
	 */
	private char getWinner() {
		return othello.getWinner();
	}

	/**
	 * Runs 1000 instances of an Othello game counting how much each player wins.
	 * Prints out win percentage of each player based on games played.
	 * Probability P1 wins=.75
	 * Probability P2 wins=.20
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		int p1wins = 0, p2wins = 0, numGames = 10000;
		int gamesPlayed = 0;

		while(gamesPlayed < numGames) {
			OthelloControllerRandomVSGreedy oc = new OthelloControllerRandomVSGreedy();
			oc.play();

			char winner = oc.getWinner();

			if (OthelloBoard.P1 == winner){
				p1wins += 1;
			} else if (OthelloBoard.P2 == winner) {
				p2wins += 1;
			}
			gamesPlayed += 1;
		}
		

		System.out.println("Probability P1 wins=" + (float) p1wins / numGames);
		System.out.println("Probability P2 wins=" + (float) p2wins / numGames);
	}
}
