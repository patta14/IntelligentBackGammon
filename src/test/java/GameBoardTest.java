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
        GameBoard gameBoard = new GameBoard(new SimulationState(1));
        Move testMove1 = gameBoard.checkMove(2, 19, gameBoard.getWhite());
        assertTrue(testMove1.isLegal());
        assertFalse(testMove1.isCanKick());
        assertFalse(testMove1.isOut());
        assertTrue(testMove1.getNewPosition() == 17 && testMove1.getPreviousPosition() == 19);

        Move testMove2 = gameBoard.checkMove(4, 17, gameBoard.getWhite());
        assertFalse(testMove2.isLegal());
        assertFalse(testMove2.isCanKick());
        assertFalse(testMove2.isOut());
        assertTrue(testMove2.getNewPosition() == 13 && testMove2.getPreviousPosition() == 17);

        Move testMove3 = gameBoard.checkMove(1, 19, gameBoard.getWhite());
        assertTrue(testMove3.isLegal());
        assertFalse(testMove3.isCanKick());
        assertFalse(testMove3.isOut());
        assertTrue(testMove3.getNewPosition() == 18 && testMove3.getPreviousPosition() == 19);

        gameBoard.getPositions().get(8).pop();
        gameBoard.getPositions().get(8).pop();
        Move testMove4 = gameBoard.checkMove(4, 12, gameBoard.getWhite());
        assertTrue(testMove4.isLegal());
        assertTrue(testMove4.isCanKick());
        assertFalse(testMove4.isOut());
        assertTrue(testMove4.getNewPosition() == 8 && testMove4.getPreviousPosition() == 12);
    }

    @Test
    void exitJail() {
        GameBoard gameBoard = new GameBoard(new SimulationState(1));
        gameBoard.getPositions().get(25).push(gameBoard.getPositions().get(19).pop());
        gameBoard.getDices().setFace1(1);
        gameBoard.getDices().setFace2(3);
        ArrayList<Move> moves = gameBoard.exitJail(gameBoard.getWhite(), gameBoard.getDices());
        assertFalse(moves.get(0).isLegal());
        assertTrue(moves.get(1).isLegal());
        assertEquals(24, moves.get(0).getNewPosition());
        assertEquals(22, moves.get(1).getNewPosition());

        gameBoard.getPositions().get(0).push(gameBoard.getPositions().get(6).pop());
        gameBoard.getDices().setFace1(1);
        gameBoard.getDices().setFace2(3);
        ArrayList<Move> moves1 = gameBoard.exitJail(gameBoard.getBlack(), gameBoard.getDices());
        assertFalse(moves.get(0).isLegal());
        assertTrue(moves.get(1).isLegal());
        assertEquals(1, moves1.get(0).getNewPosition());
        assertEquals(3, moves1.get(1).getNewPosition());
    }

    @Test
    void checkFinal() {
        GameBoard gameBoard = new GameBoard(new SimulationState(1));
        for (int i = 0; i < 26; i++) {
            while (gameBoard.getPositions().get(i).size() > 0 && gameBoard.getPositions().get(i).peek().isColour()) {
                    gameBoard.getPositions().get(i).pop();
                }
            }
        assertTrue(gameBoard.checkFinal(gameBoard.getWhite()));
        gameBoard = new GameBoard(new SimulationState(1));
        for (int i = 0; i < 26; i++) {
            while (gameBoard.getPositions().get(i).size() > 0 && !gameBoard.getPositions().get(i).peek().isColour()) {
                gameBoard.getPositions().get(i).pop();
            }
        }
        assertTrue(gameBoard.checkFinal(gameBoard.getBlack()));
    }


    @Test
    void giveFinalMoves() {
        GameBoard gameBoard = new GameBoard(new SimulationState(1));
        assertTrue(gameBoard.giveFinalMoves(gameBoard.getWhite(), gameBoard.getDices()).isEmpty());
        assertTrue(gameBoard.giveFinalMoves(gameBoard.getBlack(), gameBoard.getDices()).isEmpty());

        createEndgame(gameBoard.getWhite(), gameBoard);
        gameBoard.getDices().setFace1(1);
        gameBoard.getDices().setFace2(2);
        ArrayList<Move> moves1 = gameBoard.giveFinalMoves(gameBoard.getWhite(), gameBoard.getDices());

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