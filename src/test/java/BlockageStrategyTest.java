import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.*;

class BlockageStrategyTest {

    BlockageStrategy blockage = new BlockageStrategy();


    @Test
    void run() {
        ArrayList<Move> moves = new ArrayList<>();
        GameBoard testBoard = createTestboard();
        assertFalse(blockage.run(moves, testBoard, new Dices(), testBoard.getWhite()).isLegal());

        moves.add(new Move(20, 16, false, true, false));
        moves.add(new Move(15, 10, true, true, false));
        moves.add(new Move(15, 14, false, true, false));
        moves.add(new Move(14, 13, false, true, false));

        assertTrue(blockage.run(moves, testBoard, new Dices(), testBoard.getWhite()).isCanKick());
        moves.remove(1);

        assertTrue(blockage.run(moves, testBoard, new Dices(), testBoard.getWhite()) == moves.get(0));
    }

    @Test
    void checkSurrounding() {
        assertEquals(3, blockage.checkSurrounding(new Move(20, 16, false, true, false), createTestboard()));

    }

    private GameBoard createTestboard(){
        GameBoard testBoard = new GameBoard(new SimulationState(1));
        for(Stack positions : testBoard.getPositions()) {
            positions.clear();
        }
        testBoard.getPositions().get(20).push(new Piece(true));
        testBoard.getPositions().get(17).push(new Piece(true));
        testBoard.getPositions().get(17).push(new Piece(true));
        testBoard.getPositions().get(15).push(new Piece(true));
        testBoard.getPositions().get(15).push(new Piece(true));
        testBoard.getPositions().get(14).push(new Piece(true));
        testBoard.getPositions().get(14).push(new Piece(true));
        return testBoard;
    }

    @Test
    void getName() {
        assertEquals("Blockadespiel", blockage.getName());
    }
}