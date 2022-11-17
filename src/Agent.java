import ec.util.MersenneTwisterFast;
import sim.engine.SimState;
import sim.engine.Steppable;

public class Agent implements Steppable {

	private MersenneTwisterFast rng = new MersenneTwisterFast();
	private Dices dices = new Dices();
	private String name;

	private Strategy strategy;

	private GameBoard gameBoard;
	private boolean colour;
	
	public Agent(String name, boolean colour, GameBoard gameBoard) {
		this.name = name;
		this.colour = colour;
		this.gameBoard = gameBoard;

		int i = 1; //rng.nextInt(1);
		switch (i){
			case 1:
				strategy = new RandomMovesStrategy();
		}
	}

	//step() method is basically the start point for agent behavior
	@Override
	public void step(SimState simState) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println(this.name + " beginnt einen Zug!");
		SimulationState simulationState = (SimulationState) simState;
		dices.roll();
		System.out.println(this.name + " hat eine " + dices.getFace1() + " und eine " + dices.getFace2()+ " gewürfelt.");
		System.out.println();
		if(dices.getFace1() == dices.getFace2()){
			for(int i = 0; i < 4; i++){
				internalPlayMove(strategy.run(gameBoard.giveMoves(dices.getFace1(), this), gameBoard, dices));

			}
		} else {
			internalPlayMove(strategy.run(gameBoard.giveMoves(dices.getFace1(), this), gameBoard, dices));
			internalPlayMove(strategy.run(gameBoard.giveMoves(dices.getFace1(), this), gameBoard, dices));
		}
		for(int i = 13; i< 25; i++){
			if(gameBoard.getPositions().get(i).isEmpty() || gameBoard.getPositions().get(i).size() == 0){
				System.out.print(" Frei ;");
			} else{
				System.out.print(" "
						+ (gameBoard.getPositions().get(i).peek().isColour() ? "weiß" : "schwarz")
						+ " und " + gameBoard.getPositions().get(i).size() + " ;");
			}
		}
		System.out.println();
		for(int i = 12; i> 0; i--){
			if(gameBoard.getPositions().get(i).isEmpty() || gameBoard.getPositions().get(i).size() == 0){
				System.out.print(" Frei ;");
			} else{
				System.out.print(" "
						+ (gameBoard.getPositions().get(i).peek().isColour() ? "weiß" : "schwarz")
						+ " und " + gameBoard.getPositions().get(i).size() + " ;");
			}
		}
		System.out.println();
		System.out.println(this.name + " beendet seinen Zug!");
		dices.roll();
	}
	private void internalPlayMove(Move move){
		if(move.getNewPosition() == 0 && move.getPreviousPosition() == 0){

		} else {
			gameBoard.playMove(move, this);
		}
	}
	public Dices getDices() {
		return dices;
	}

	public void setDices(Dices dices) {
		this.dices = dices;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isColour() {
		return colour;
	}

	public void setColour(boolean colour) {
		this.colour = colour;
	}
}
