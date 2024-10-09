package othello;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerRandomTest {
    Othello othello;
    PlayerRandom playerRandom;

    @BeforeEach
    public void setUp() throws Exception {
        othello = new Othello();
        playerRandom = new PlayerRandom(othello, OthelloBoard.P1); // Assuming player 1 is the greedy player

        othello.move(2, 4);
        othello.move(2, 5);
        othello.move(2, 6);
        othello.move(2, 3);

        // Board now looks like
        //   0 1 2 3 4 5 6 7
        //  +-+-+-+-+-+-+-+-+
        // 0| | | | | | | | |0
        //  +-+-+-+-+-+-+-+-+
        // 1| | | | | | | | |1
        //  +-+-+-+-+-+-+-+-+
        // 2| | | |O|X|X|X| |2
        //  +-+-+-+-+-+-+-+-+
        // 3| | | |O|O| | | |3
        //  +-+-+-+-+-+-+-+-+
        // 4| | | |O|X| | | |4
        //  +-+-+-+-+-+-+-+-+
        // 5| | | | | | | | |5
        //  +-+-+-+-+-+-+-+-+
        // 6| | | | | | | | |6
        // +-+-+-+-+-+-+-+-+
        // 7| | | | | | | | |7
        //  +-+-+-+-+-+-+-+-+
        //   0 1 2 3 4 5 6 7
        //
        // X:4 O:4  X moves next
    }

    @org.junit.jupiter.api.Test
    void testGetMove() {
        String testBoardString = "  " +
                  "0 1 2 3 4 5 6 7 \n" +
                " +-+-+-+-+-+-+-+-+\n" +
                "0| | | | | | | | |0\n" +
                " +-+-+-+-+-+-+-+-+\n" +
                "1| | | | | | | | |1\n" +
                " +-+-+-+-+-+-+-+-+\n" +
                "2| | | |O|X|X|X| |2\n" +
                " +-+-+-+-+-+-+-+-+\n" +
                "3| | | |O|O| | | |3\n" +
                " +-+-+-+-+-+-+-+-+\n" +
                "4| | | |O|X| | | |4\n" +
                " +-+-+-+-+-+-+-+-+\n" +
                "5| | | | | | | | |5\n" +
                " +-+-+-+-+-+-+-+-+\n" +
                "6| | | | | | | | |6\n" +
                " +-+-+-+-+-+-+-+-+\n" +
                "7| | | | | | | | |7\n" +
                " +-+-+-+-+-+-+-+-+\n" +
                "  0 1 2 3 4 5 6 7 \n";

        ArrayList<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(2, 2));
        validMoves.add(new Move(4, 2));
        validMoves.add(new Move(5, 2));
        Move randomMove = playerRandom.getMove();

        boolean isValidMove = false;
        for (int i = 0; i < validMoves.size(); i++) {
            if (randomMove.getRow() == validMoves.get(i).getRow() && randomMove.getCol() == validMoves.get(i).getCol()) {
                isValidMove = true;
                break;
            }
        }

        assertTrue(isValidMove);
    }

}