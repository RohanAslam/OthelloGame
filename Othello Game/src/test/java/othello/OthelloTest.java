package othello;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class OthelloTest {
    Othello othello;
    Move[] moves = {new Move(2, 4), new Move(2, 5), new Move(2, 6), new Move(2, 3), new Move(2, 2), new Move(3, 2),
            new Move(4, 2), new Move(5, 4), new Move(6, 4)};

    @BeforeEach
    public void setUp() throws Exception {
        othello = new Othello();
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
    public void testGetWhosTurn() {

        assertEquals(OthelloBoard.P1, othello.getWhosTurn());
        othello.move(2, 2);
        assertEquals(OthelloBoard.P2, othello.getWhosTurn());
    }

    @org.junit.jupiter.api.Test
    public void testSwitchTurn() {
        othello.switchTurn();
        assertEquals(OthelloBoard.P2, othello.getWhosTurn());
        othello.move(1, 4);
        assertEquals(OthelloBoard.P1, othello.getWhosTurn());

    }

    @org.junit.jupiter.api.Test
    public void testMove() {
        assertFalse(othello.move(1, 4));
        assertTrue(othello.move(2, 2));
    }

    @org.junit.jupiter.api.Test
    public void testGetCount() {
        assertEquals(4, othello.getCount(OthelloBoard.P1));
        assertEquals(4, othello.getCount(OthelloBoard.P2));
        othello.move(2, 2);
        assertEquals(7, othello.getCount(OthelloBoard.P1));
        assertEquals(2, othello.getCount(OthelloBoard.P2));
    }

    @org.junit.jupiter.api.Test
    public void testGetWinner() {
        Othello o = new Othello();
        for (int i = 0; i < moves.length; i++) {
            assertEquals(OthelloBoard.EMPTY, o.getWinner(), "During play");
            o.move(moves[i].getRow(), moves[i].getCol());
        }
        assertEquals(OthelloBoard.P1, o.getWinner(), "After winner");
    }

    @org.junit.jupiter.api.Test
    public void testIsGameOver() {
        Othello o = new Othello();
        for (int i = 0; i < moves.length; i++) {
            assertEquals(false, o.isGameOver(), "During play");
            o.move(moves[i].getRow(), moves[i].getCol());
        }
        assertEquals(true, o.isGameOver(), "After winner");
    }

    @org.junit.jupiter.api.Test
    public void testGetBoardString() {
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
        assertEquals(testBoardString, othello.getBoardString());
        System.out.println(othello.toString());


    }

}
