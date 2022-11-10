import sim.engine.SimState;
import sim.engine.Steppable;

public class Agent implements Steppable {

	private Dice dice = new Dice();
	private String name;

	private boolean colour;
	
	public Agent(String name, boolean colour) {
		this.name = name;
		this.colour = colour;
	}

	//step() method is basically the start point for agent behavior
	@Override
	public void step(SimState simState) {
		SimulationState simulationState = (SimulationState) simState;
		System.out.println("my name is " + name + " and I rolled a " + dice.roll());
	}

	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
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
