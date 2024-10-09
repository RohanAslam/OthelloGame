package othello;

/**
 * Determine whether the first player or second player has the advantage when
 * both are playing a Random Strategy.
 * 
 * Do this by creating two players which use a random strategy and have them
 * play each other for 10000 games. What is your conclusion, does the first or
 * second player have some advantage, at least for a random strategy? 
 * State the null hypothesis H0, the alternate hypothesis Ha and 
 * about which your experimental results support. Place your short report in
 * randomVsRandomReport.txt.
 *
 */
public class OthelloControllerRandomVSRandom {

	protected Othello othello;
	PlayerRandom player1;
	PlayerRandom player2;

	/**
	 * Initializes the othello board as well as the players and types of players playing it
	 */
	public OthelloControllerRandomVSRandom() {
		this.othello = new Othello();
		this.player1 = new PlayerRandom(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerRandom(this.othello, OthelloBoard.P2);
	}

	/**
	 * Starts a game of Othello for a Random algorithm Vs Random algorithm
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
	public char getWinner() {
		return othello.getWinner();
	}


	/**
	 * Runs 1000 instances of an Othello game counting how much each player wins.
	 * Prints out win percentage of each player based on games played.
	 * Probability P1 wins=.75 
	 * Probability P2 wins=.20
	 * @param args
	 */
	public static void main(String[] args) {

		int p1wins = 0, p2wins = 0, numGames = 10000;
		int gamesPlayed = 0;

		while(gamesPlayed < numGames) {
			OthelloControllerRandomVSRandom oc = new OthelloControllerRandomVSRandom();
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
