package othello;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerGreedyTest {
    Othello othello;
    PlayerGreedy playerGreedy;

    @BeforeEach
    public void setUp() throws Exception {
        othello = new Othello();
        playerGreedy = new PlayerGreedy(othello, OthelloBoard.P1); // Assuming player 1 is the greedy player

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
                "2| | |X|X|X|X|X| |2\n" +
                " +-+-+-+-+-+-+-+-+\n" +
                "3| | | |X|O| | | |3\n" +
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

        playerGreedy.getMove();
        othello.move(2, 2);
        assertEquals(testBoardString,othello.board.toString());
    }

}