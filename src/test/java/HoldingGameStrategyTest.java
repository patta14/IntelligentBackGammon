import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HoldingGameStrategyTest {
    GameBoard gameBoard = new GameBoard(new SimulationState(10));
    Dices dices = new Dices();
    Strategy holdingGame = new HoldingGameStrategy();

    ArrayList<Move> moves1 = new ArrayList<>();
    ArrayList<Move> moves2 = new ArrayList<>();
    ArrayList<Move> moves3 = new ArrayList<>();
    ArrayList<Move> moves4 = new ArrayList<>();
    ArrayList<Move> moves5 = new ArrayList<>();
    ArrayList<Move> moves6 = new ArrayList<>();
    ArrayList<Move> moves7 = new ArrayList<>();

    @Test
    void run() {
        assertTrue(holdingGame.run(moves1, gameBoard, dices, gameBoard.getWhite()).getNewPosition() == 0 && holdingGame.run(moves1, gameBoard, dices, gameBoard.getWhite()).getPreviousPosition()== 0);

    }

    @Test
    void getName() {
        assertEquals("HoldingGame", holdingGame.getName());
    }

    @Test
    void checkGoldenPoint(){
        Move move = new Move(1 , 5, false, true, false);
        moves2.add(move);
        assertEquals(move, holdingGame.run(moves2, gameBoard, dices, gameBoard.getWhite()));

        Move move3 = new Move(1 , 4 , false, true, false);
        moves4.add(move3);
        assertEquals(move3, holdingGame.run(moves4, gameBoard, dices, gameBoard.getWhite()));

        Move move5 = new Move(1 , 6 , false, true, false);
        moves6.add(move5);
        assertEquals(move5, holdingGame.run(moves6, gameBoard, dices, gameBoard.getWhite()));

        Move move2 = new Move(22 , 20, false, true, false);
        moves3.add(move2);
        assertEquals(move2, holdingGame.run(moves3, gameBoard, dices, gameBoard.getBlack()));

        Move move4 = new Move(22 , 21, false, true, false);
        moves5.add(move4);
        assertEquals(move4, holdingGame.run(moves5, gameBoard, dices, gameBoard.getBlack()));

        Move move6 = new Move(22 , 19, false, true, false);
        moves7.add(move6);
        assertEquals(move6, holdingGame.run(moves7, gameBoard, dices, gameBoard.getBlack()));
    }

    @Test
    void checkDoubles() {
        while (dices.getFace1() != dices.getFace2()) {
            dices = new Dices();
        }
        if (dices.getFace1() == dices.getFace2()) {
            Move move = new Move(5 , 8, false, true, false);
            moves2.add(move);
            assertEquals(move, holdingGame.run(moves2, gameBoard, dices, gameBoard.getWhite()));

            Move move3 = new Move(4 , 8 , false, true, false);
            moves4.add(move3);
            assertEquals(move3, holdingGame.run(moves4, gameBoard, dices, gameBoard.getWhite()));

            Move move5 = new Move(6 , 8 , false, true, false);
            moves6.add(move5);
            assertEquals(move5, holdingGame.run(moves6, gameBoard, dices, gameBoard.getWhite()));

            Move move2 = new Move(20 , 15, false, true, false);
            moves3.add(move2);
            assertEquals(move2, holdingGame.run(moves3, gameBoard, dices, gameBoard.getBlack()));

            Move move4 = new Move(21 , 15, false, true, false);
            moves5.add(move4);
            assertEquals(move4, holdingGame.run(moves5, gameBoard, dices, gameBoard.getBlack()));

            Move move6 = new Move(19 , 15, false, true, false);
            moves7.add(move6);
            assertEquals(move6, holdingGame.run(moves7, gameBoard, dices, gameBoard.getBlack()));
        }
    }
}