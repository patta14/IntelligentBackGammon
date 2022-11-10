import sim.engine.SimState;

import java.util.ArrayList;
import java.util.Stack;

public class SimulationState extends SimState {
	private GameBoard gameBoard;

	Agent alice;
	Agent bob;
	public SimulationState(long seed) {
		super(seed);	
	}

	public static void main(String[] args) {
		doLoop(SimulationState.class, args);
		System.exit(0);
	}
	
	@Override
	public void start() {


		super.start();

		bob = new Agent("Bob", false);
		alice = new Agent("Alice", true);
		gameBoard = new GameBoard(alice, bob);

		Dice dice = new Dice();
		int dice1 = dice.roll();
		int dice2 = dice.roll();
		System.out.println(dice1 + " und " + dice2);
		for (Move move :gameBoard.giveMoves(dice1, dice2, alice)) {
			System.out.println(move.getPreviousPosition() + " " + move.getNewPosition() + " " + move.isCanKick());
		}
		
		//make sure you understand the different version of the scheduleOnce() und scheduleRepeating() methods (read documentation)
		//agent order is random if agents with same ordering are called at the same time
		/*

		schedule.scheduleRepeating(alice, 0, 1.0);


		schedule.scheduleRepeating(bob, 1, 1.0);
		*/
	}
	
	//call finish() to terminate gracefully
	@Override
    public void finish() {
        super.finish();
        System.out.println("simulation finished");
    }

}
