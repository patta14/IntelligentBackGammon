import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void setup() {
        GameBoard gameBoard = new GameBoard(new SimulationState(1));
        Stack<Piece> stack = gameBoard.setup(5, true);
        assertTrue(stack.peek().isColour() && stack.size() == 5);
        stack = gameBoard.setup(69, false);
        assertTrue(!stack.peek().isColour() && stack.size() == 69);
    }

    @Test
    void playMove() {
        GameBoard gameBoard = new GameBoard(new SimulationState(1));
        gameBoard.getPositions().get(21).push(gameBoard.getPositions().get(24).pop());
        gameBoard.playMove(new Move(19, 21, true, true), gameBoard.getWhite());
        assertTrue(gameBoard.getPositions().get(19).size() == 4);
        assertTrue(gameBoard.getPositions().get(21).size() == 1);
        assertTrue(gameBoard.getPositions().get(0).size() == 1 && !gameBoard.getPositions().get(0).peek().isColour());
        assertTrue(gameBoard.getPositions().get(21).peek().isColour());

        GameBoard gameBoard1 = new GameBoard(new SimulationState(1));
        gameBoard1.getPositions().get(2).push(gameBoard1.getPositions().get(19).pop());
        gameBoard1.getPositions().get(2).push(gameBoard1.getPositions().get(19).pop());
        gameBoard1.getPositions().get(2).push(gameBoard1.getPositions().get(19).pop());
        gameBoard1.getPositions().get(2).push(gameBoard1.getPositions().get(19).pop());
        gameBoard1.getPositions().get(2).push(gameBoard1.getPositions().get(19).pop());

        gameBoard1.getPositions().get(3).push(gameBoard1.getPositions().get(17).pop());
        gameBoard1.getPositions().get(3).push(gameBoard1.getPositions().get(17).pop());
        gameBoard1.getPositions().get(3).push(gameBoard1.getPositions().get(17).pop());

        gameBoard1.getPositions().get(4).push(gameBoard1.getPositions().get(12).pop());
        gameBoard1.getPositions().get(4).push(gameBoard1.getPositions().get(12).pop());
        gameBoard1.getPositions().get(4).push(gameBoard1.getPositions().get(12).pop());
        gameBoard1.getPositions().get(4).push(gameBoard1.getPositions().get(12).pop());
        gameBoard1.getPositions().get(4).push(gameBoard1.getPositions().get(12).pop());
        gameBoard1.playMove(new Move(1, 0, false, true, true), gameBoard1.getWhite());
        assertTrue(gameBoard1.checkEndgame(gameBoard1.getWhite()));
        assertTrue(gameBoard1.getPositions().get(1).size() == 1);
        assertTrue(gameBoard1.getWhitePiecesInGame() == 14);

        gameBoard = new GameBoard(new SimulationState(1));
        gameBoard.playMove(new Move(19, 21, false, true), gameBoard.getWhite());
        assertTrue(gameBoard.getPositions().get(19).size() == 4);
        assertTrue(gameBoard.getPositions().get(21).size() == 1 && gameBoard.getPositions().get(21).peek().isColour());

        gameBoard1 = new GameBoard(new SimulationState(1));
        gameBoard1.getPositions().get(19).pop();
        gameBoard1.getPositions().get(19).pop();
        gameBoard1.getPositions().get(19).pop();
        gameBoard1.getPositions().get(19).pop();
        gameBoard1.getPositions().get(19).pop();

        gameBoard1.getPositions().get(17).pop();
        gameBoard1.getPositions().get(17).pop();
        gameBoard1.getPositions().get(17).pop();

        gameBoard1.getPositions().get(12).pop();
        gameBoard1.getPositions().get(12).pop();
        gameBoard1.getPositions().get(12).pop();
        gameBoard1.getPositions().get(12).pop();
        gameBoard1.getPositions().get(12).pop();

        gameBoard1.getPositions().get(1).pop();

        gameBoard1.playMove(new Move(1, 0, false, true, true), gameBoard1.getWhite());
        assertTrue(gameBoard1.getWhitePiecesInGame() == 0);
    }

    @Test
    void finishGame() {

    }

    @Test
    void checkEndgame() {
    }

    @Test
    void giveMoves() {
    }

    @Test
    void checkMove() {
    }

    @Test
    void exitJail() {
    }

    @Test
    void checkFinal() {
    }

    @Test
    void giveFinalMoves() {
    }


    @Test
    void getSetAgents() {
        GameBoard gameBoard = new GameBoard(new SimulationState(1));
        Agent agent1 = new Agent("Alice", true, gameBoard);
        Agent agent2 = new Agent("Bob", true, gameBoard);
        gameBoard.setWhite(agent1);
        gameBoard.setBlack(agent2);
        assertEquals(agent1, gameBoard.getWhite());
        assertEquals(agent2, gameBoard.getBlack());

    }


    @Test
    void getBlackPiecesInGame() {
        GameBoard gameBoard = new GameBoard(new SimulationState(1));
        assertEquals(15, gameBoard.getBlackPiecesInGame());
        gameBoard.getPositions().get(6).pop();
        gameBoard.getPositions().get(6).pop();
        gameBoard.getPositions().get(6).pop();
        gameBoard.getPositions().get(6).pop();
        gameBoard.getPositions().get(6).pop();
        assertEquals(10, gameBoard.getBlackPiecesInGame());
    }


    @Test
    void getWhitePiecesInGame() {
        GameBoard gameBoard = new GameBoard(new SimulationState(1));
        assertEquals(15, gameBoard.getWhitePiecesInGame());
        gameBoard.getPositions().get(19).pop();
        gameBoard.getPositions().get(19).pop();
        gameBoard.getPositions().get(19).pop();
        gameBoard.getPositions().get(19).pop();
        gameBoard.getPositions().get(19).pop();
        assertEquals(10, gameBoard.getWhitePiecesInGame());
    }

}