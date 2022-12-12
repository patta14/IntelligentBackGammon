import ec.util.MersenneTwisterFast;
import sim.engine.SimState;
import sim.engine.Steppable;

import java.util.ArrayList;

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

		int i = rng.nextInt(2) + 1;
		switch (i){
			case 1:
				strategy = new RandomMovesStrategy();
				break;
			case 2:
				strategy = new BlockageStrategy();
				break;
		}
	}

	//step() method is basically the start point for agent behavior
	@Override
	public void step(SimState simState) {
		/*
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		*/
		System.out.println(this.name + " beginnt einen Zug!");
		SimulationState simulationState = (SimulationState) simState;
		dices.roll();
		System.out.println(this.name + " hat eine " + dices.getFace1() + " und eine " + dices.getFace2()+ " gewürfelt.");
		System.out.println();

		agentPlay(dices);

		//printInfo();

		dices.roll();
	}

	private void printInfo(){
		//Dieser Teil bis Zeile 72 bildet einfach nur das Brett ab!
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
		System.out.println("Aktuell im im Gefängnis: " + (!this.isColour() ? gameBoard.getPositions().get(25).size() : gameBoard.getPositions().get(0).size()));
		System.out.println();
		System.out.println(this.name + " beendet seinen Zug!");
	}

	private void agentPlay(Dices dices){
		if(gameBoard.checkFinal(this)){
			internalPlayMove(gameBoard.giveFinalMoves(this, dices).remove(0));
			internalPlayMove(gameBoard.giveFinalMoves(this, dices).remove(0));
		 	return;
		}
		ArrayList<Integer> remainingDices = new ArrayList<>();
		if(dices.getFace1() == dices.getFace2()) {
			for (int i = 0; i < 4; i++) {
				remainingDices.add(dices.getFace1());
			}
		}else {
				remainingDices.add(dices.getFace1());
				remainingDices.add(dices.getFace2());
			}

		while(!remainingDices.isEmpty() && (this.isColour() ? gameBoard.getPositions().get(0).size() >= 1 : gameBoard.getPositions().get(25).size() >= 1)){
			Move move1 = strategy.run(gameBoard.exitJail(this, dices), gameBoard, dices);
			for (Integer i: remainingDices) {
				if(i == Math.abs(move1.getPreviousPosition() - move1.getNewPosition())){
					remainingDices.remove(i);
					break;
				}
			}
			internalPlayMove(move1);
			
		}
		for(Integer i: remainingDices){
			ArrayList<Move> moves = gameBoard.giveMoves(i, this);
			if(!moves.isEmpty()) {
				internalPlayMove(strategy.run(moves, gameBoard, dices));
			}
		}
		GameBoard.ROUNDS++;
	}
	private void internalPlayMove(Move move){
		if(move.isLegal()){
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

	public Strategy getStrategy() {
		return strategy;
	}
}
