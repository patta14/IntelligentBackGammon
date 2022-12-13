import ec.util.MersenneTwisterFast;

import java.util.ArrayList;

public class HoldingGameStrategy implements Strategy{

    private MersenneTwisterFast rng = new MersenneTwisterFast();

    private String name = "HoldingGame";

    public HoldingGameStrategy(){
    }

    public Move run(ArrayList<Move> moves, GameBoard gameBoard, Dices dices, Agent agent) {
        if (moves.size() == 0) {
            return new Move(0, 0, false, false);
        }
        if(dices.getFace1() == dices.getFace2()){
            return checkDouble(moves, gameBoard, agent);
        }else {
            return checkGoldenPoint(moves, gameBoard, agent);
        }
    }

    public String getName() {
        return name;
    }

    private Move checkGoldenPoint(ArrayList<Move> moves, GameBoard gameBoard, Agent agent){
        Move randomMove = moves.get(rng.nextInt(moves.size()));
        if (agent.isColour()) {
            for (Move move : moves) {
                if (move.getNewPosition() == 5 && gameBoard.getPositions().get(5).size() < 2) {
                    return move;
                }
                else if ((move.getNewPosition() == 4 && gameBoard.getPositions().get(4).size() < 2) ||
                        (move.getNewPosition() == 6 && gameBoard.getPositions().get(6).size() < 2)){
                    return move;
                }
            }
        } else {
            for (Move move : moves) {
                if (move.getNewPosition() == 20 && gameBoard.getPositions().get(20).size() < 2) {
                    return move;
                }
                else if ((move.getNewPosition() == 19 && gameBoard.getPositions().get(19).size() < 2) ||
                        (move.getNewPosition() == 21 && gameBoard.getPositions().get(21).size() < 2)){
                    return move;
                }
            }
        }
        return randomMove;
    }

    private Move checkDouble(ArrayList<Move> moves, GameBoard gameBoard, Agent agent){
        Move randomMove = moves.get(rng.nextInt(moves.size()));
        if (agent.isColour()) {
            for (Move move : moves) {
                if (gameBoard.getPositions().get(4).size() > 1 || gameBoard.getPositions().get(5).size() > 1 ||
                        gameBoard.getPositions().get(6).size() > 1) {
                    return move;
                }
            }
        } else {
            for (Move move : moves) {
                if (gameBoard.getPositions().get(19).size() > 1 || gameBoard.getPositions().get(20).size() > 1 ||
                        gameBoard.getPositions().get(21).size() > 1) {
                    return move;
                }
            }
        }
        return randomMove;
    }

}
