import org.junit.jupiter.api.Test;
import sim.engine.SimState;

import static org.junit.jupiter.api.Assertions.*;

class AgentTest {
    GameBoard gameBoard = new GameBoard(new SimulationState(1));
    Agent agent = new Agent("Charlie", true, gameBoard);

    @Test
    void printInfo() {
        agent.printInfo();
    }

    @Test
    void agentPlayWhite() {
        gameBoard = new GameBoard(new SimulationState(1));
        gameBoard.getPositions().get(25).push(gameBoard.getPositions().get(19).pop());
        gameBoard.getPositions().get(25).push(gameBoard.getPositions().get(19).pop());
        gameBoard.getPositions().get(25).push(gameBoard.getPositions().get(19).pop());
        agent = new Agent("Charlie", true, gameBoard);
        agent.printInfo();
        agent.agentPlay(new Dices());
        agent.printInfo();

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



        agent = new Agent("Charlie", true, gameBoard1);
        agent.printInfo();
        assertEquals(1, agent.agentPlay(new Dices()));
        agent.printInfo();

        agent.getDices().setFace2(3);
        agent.getDices().setFace1(3);

        agent.agentPlay(agent.getDices());

    }

    @Test
    void agentPlayBlack() {
        agent.setColour(false);
        gameBoard = new GameBoard(new SimulationState(1));
        gameBoard.getPositions().get(0).push(gameBoard.getPositions().get(6).pop());
        gameBoard.getPositions().get(0).push(gameBoard.getPositions().get(6).pop());
        gameBoard.getPositions().get(0).push(gameBoard.getPositions().get(6).pop());
        agent = new Agent("Charlie", false, gameBoard);
        agent.printInfo();
        agent.agentPlay(new Dices());
        agent.printInfo();

        gameBoard = new GameBoard(new SimulationState(1));
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



        agent = new Agent("Charlie", false, gameBoard);
        agent.printInfo();
        agent.agentPlay(new Dices());
        agent.printInfo();

    }



    @Test
    void internalPlayMove() {
    }

    @Test
    void step() {
        agent.step(new SimState(1));
        gameBoard = new GameBoard(new SimulationState(1));
    }

    @Test
    void getDices() {
        assertTrue(agent.getDices().getFace1() < 7 && agent.getDices().getFace1() > 0);
        assertTrue(agent.getDices().getFace2() < 7 && agent.getDices().getFace2() > 0);
    }


    @Test
    void getName() {
        assertTrue(agent.getName() == "Charlie");
    }

    @Test
    void setName() {
        agent.setName("David");
        assertTrue(agent.getName() == "David");
        agent.setName("Charlie");
        assertTrue(agent.getName() == "Charlie");
    }

    @Test
    void isColour() {
        assertTrue(agent.isColour());
    }

    @Test
    void setColour() {
        agent.setColour(false);
        assertFalse(agent.isColour());
        agent.setColour(true);
        assertTrue(agent.isColour());
    }

    @Test
    void getStrategy(){
        assertTrue(agent.getStrategy() instanceof Strategy);
    }

}