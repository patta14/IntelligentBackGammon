import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RandomMovesStrategyTest {
    GameBoard gameBoard = new GameBoard(new SimulationState(10));
    Dices dices = new Dices();
    Strategy random = new RandomMovesStrategy();

    ArrayList<Move> moves1 = new ArrayList<>();
    ArrayList<Move> moves2 = new ArrayList<>();

    @Test
    void run() {
        assertTrue(random.run(moves1, gameBoard, dices, gameBoard.getWhite()).getNewPosition() == 0 && random.run(moves1, gameBoard, dices, gameBoard.getWhite()).getPreviousPosition()== 0);
        Move move = new Move(1 , 5, false, true, false);
        moves2.add(move);
        assertEquals(move, random.run(moves2, gameBoard, dices, gameBoard.getWhite()));
    }

    @Test
    void getName() {
        assertEquals("Random", random.getName());
    }
}