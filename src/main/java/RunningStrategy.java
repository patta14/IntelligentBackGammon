import ec.util.MersenneTwisterFast;

import java.util.ArrayList;

public class RunningStrategy implements Strategy{

    private MersenneTwisterFast rng = new MersenneTwisterFast();

    private String name = "Running";

    @Override
    public Move run(ArrayList<Move> moves, GameBoard gameBoard, Dices dices, Agent agent) {
        Move maxMove = moves.get(0);
        for(Move move: moves){
            if(agent.isColour() ? (move.getNewPosition() < maxMove.getNewPosition()) :  move.getNewPosition() > maxMove.getNewPosition()){
                maxMove = move;
            }

        }

        for(Move move: moves){
            if(move == maxMove &&
                    !gameBoard.getPositions().get(move.getNewPosition()).isEmpty() &&
                    gameBoard.getPositions().get(move.getNewPosition()).peek().isColour() == agent.isColour() &&
                    gameBoard.getPositions().get(move.getNewPosition()).size() > 1){
                return move;
            }
            if(move == maxMove &&
                    (gameBoard.getPositions().get(move.getNewPosition()).isEmpty() || gameBoard.getPositions().get(move.getNewPosition()).peek().isColour() == agent.isColour())){
                return move;
            }
        }
        return maxMove;
    }

    @Override
    public String getName() {
        return name;
    }
}
