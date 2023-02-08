import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class BlockageStrategy implements Strategy{

    private String name = "Blockadespiel";

    public BlockageStrategy(){
    }

    public Move run(ArrayList<Move> moves, GameBoard gameBoard, Dices dices, Agent agent) {
        System.out.println("BALLS");
        if(moves.isEmpty()){
            return(new Move(0, 0, false, false, false));
        }
        ArrayList<Integer> priorities = new ArrayList<>();
        for (Move move: moves) {
            if((agent.isColour() ? move.getPreviousPosition() == 25 : move.getPreviousPosition() == 0) && move.isLegal()) {
                return move;
            } else if ((agent.isColour() ? move.getPreviousPosition() == 25 : move.getPreviousPosition() == 0)) {
                return move;
        } else if (move.isCanKick()) {
                return move;
            } else {
                priorities.add(moves.indexOf(move), checkSurrounding(move, gameBoard));
            }
        }
        int maxPriority = 0;
        Move maxMove = moves.get(0);
        for(int i = 0; i < priorities.size(); i++){
            if(priorities.get(i) > maxPriority){
                maxPriority = priorities.get(i);
                maxMove = moves.get(i);
            }
        }
        return maxMove;
    }

    public int checkSurrounding(Move move, GameBoard gameBoard){
        System.out.println("COCK");
        int blockageSize = 0;
        boolean blocked1 = true;
        boolean blocked2 = true;
        for(int i = 1; blocked1 || blocked2; i++) {
            if (!(move.getNewPosition() - i < 0) &&
                    !gameBoard.getPositions().get(move.getNewPosition() - i).isEmpty()
                    && gameBoard.getPositions().get(move.getNewPosition() - i).peek().isColour() == gameBoard.getPositions().get(move.getPreviousPosition()).peek().isColour()
                    && gameBoard.getPositions().get(move.getNewPosition() - i).size() > 1) {
                blockageSize += 1;
            } else{
                blocked1 = false;
            }
            if (!gameBoard.getPositions().get(move.getNewPosition() + i).isEmpty()
                    && gameBoard.getPositions().get(move.getNewPosition() + i).peek().isColour() == gameBoard.getPositions().get(move.getPreviousPosition()).peek().isColour()
                    && gameBoard.getPositions().get(move.getNewPosition() + i).size() > 1) {
                blockageSize += 1;
            } else {
                blocked2 = false;
            }
        }
        return blockageSize;
    }

    public String getName() {
        return name;
    }
}
