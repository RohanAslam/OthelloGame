package othello;

import java.util.ArrayList;
import java.util.Random;

public class HypothesisTesting {
    private Random rand = new Random();

    /**
     * Function that runs 10000 games of Othello RandomVsRandom storing the result in an ArrayList.
     * It then randomly samples from the ArrayList 20000 time to applying the bootstrapping statistics technique.
     */
    private void Bootstrapping() {

        ArrayList<Character> winners = new ArrayList<>();

        int numGames = 10000;
        int gamesPlayed = 0;

        while(gamesPlayed < numGames) {
            OthelloControllerRandomVSRandom btGame = new OthelloControllerRandomVSRandom();
            btGame.play();

            char winner = btGame.getWinner();

            if (OthelloBoard.P1 == winner){
                winners.add(OthelloBoard.P1);
            } else if (OthelloBoard.P2 == winner) {
                winners.add(OthelloBoard.P2);
            }
            gamesPlayed += 1;
        }

        int numGamesSampled = 20000;
        int sampleGamesPicked = 0;

        int p1Wins = 0;
        int p2Wins = 0;

        while (sampleGamesPicked < numGamesSampled) {

            int gameWinner = rand.nextInt(1000);

            if (winners.get(gameWinner) == OthelloBoard.P1) p1Wins += 1;
            else if (winners.get(gameWinner) == OthelloBoard.P2) p2Wins += 1;
            sampleGamesPicked += 1;
        }

        System.out.println("Probability P1 wins="  + (float) p1Wins / numGamesSampled);
        System.out.println("Probability P2 wins=" + (float) p2Wins / numGamesSampled) ;

    }

    public static void main(String[] args) {
        HypothesisTesting bt =  new HypothesisTesting();
        bt.Bootstrapping();
    }
}
