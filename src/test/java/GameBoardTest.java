import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
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

    GameBoard createEndgame(Agent agent, GameBoard gameBoard){
        if(agent.isColour()){
            gameBoard.getPositions().get(2).push(gameBoard.getPositions().get(19).pop());
            gameBoard.getPositions().get(2).push(gameBoard.getPositions().get(19).pop());
            gameBoard.getPositions().get(2).push(gameBoard.getPositions().get(19).pop());
            gameBoard.getPositions().get(2).push(gameBoard.getPositions().get(19).pop());
            gameBoard.getPositions().get(2).push(gameBoard.getPositions().get(19).pop());

            gameBoard.getPositions().get(3).push(gameBoard.getPositions().get(17).pop());
            gameBoard.getPositions().get(3).push(gameBoard.getPositions().get(17).pop());
            gameBoard.getPositions().get(3).push(gameBoard.getPositions().get(17).pop());

            gameBoard.getPositions().get(4).push(gameBoard.getPositions().get(12).pop());
            gameBoard.getPositions().get(4).push(gameBoard.getPositions().get(12).pop());
            gameBoard.getPositions().get(4).push(gameBoard.getPositions().get(12).pop());
            gameBoard.getPositions().get(4).push(gameBoard.getPositions().get(12).pop());
            gameBoard.getPositions().get(4).push(gameBoard.getPositions().get(12).pop());
        } else {
            gameBoard.getPositions().get(23).push(gameBoard.getPositions().get(6).pop());
            gameBoard.getPositions().get(23).push(gameBoard.getPositions().get(6).pop());
            gameBoard.getPositions().get(23).push(gameBoard.getPositions().get(6).pop());
            gameBoard.getPositions().get(23).push(gameBoard.getPositions().get(6).pop());
            gameBoard.getPositions().get(23).push(gameBoard.getPositions().get(6).pop());

            gameBoard.getPositions().get(22).push(gameBoard.getPositions().get(8).pop());
            gameBoard.getPositions().get(22).push(gameBoard.getPositions().get(8).pop());
            gameBoard.getPositions().get(22).push(gameBoard.getPositions().get(8).pop());

            gameBoard.getPositions().get(21).push(gameBoard.getPositions().get(13).pop());
            gameBoard.getPositions().get(21).push(gameBoard.getPositions().get(13).pop());
            gameBoard.getPositions().get(21).push(gameBoard.getPositions().get(13).pop());
            gameBoard.getPositions().get(21).push(gameBoard.getPositions().get(13).pop());
            gameBoard.getPositions().get(21).push(gameBoard.getPositions().get(13).pop());
        }
        return gameBoard;
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
        createEndgame(gameBoard.getWhite(), gameBoard1);
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
        GameBoard gameBoard = new GameBoard(new SimulationState(1));
        gameBoard.finishGame(new Agent("Charlie", true, gameBoard), new Agent("David", true, gameBoard));
    }

    @Test
    void checkEndgame() {
        GameBoard gameBoard = new GameBoard(new SimulationState(1));
        Agent alice = new Agent("Alice", true, gameBoard);
        Agent bob = new Agent("Bob", false, gameBoard);
        assertFalse(gameBoard.checkEndgame(alice));
        assertFalse(gameBoard.checkEndgame(bob));
        gameBoard = createEndgame(alice, gameBoard);
        assertTrue(gameBoard.checkEndgame(alice));
        assertFalse(gameBoard.checkEndgame(bob));
        gameBoard = new GameBoard(new SimulationState(1));
        gameBoard = createEndgame(bob, gameBoard);
        assertFalse(gameBoard.checkEndgame(alice));
        assertTrue(gameBoard.checkEndgame(bob));
    }

    @Test
    void giveMoves() {
        GameBoard gameBoard = new GameBoard(new SimulationState(1));
        ArrayList<Move> moves = gameBoard.giveMoves(2, new Agent("Alice", true, gameBoard));
        assertTrue(moves.size() == 3);
        moves = gameBoard.giveMoves(2, new Agent("Bob", false, gameBoard));
        assertTrue(moves.size() == 3);
        moves = gameBoard.giveMoves(4, new Agent("Alice", true, gameBoard));
        assertTrue(moves.size() == 1);
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