import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class BlockageStrategy implements Strategy{

    private String name = "Blockadespiel";

    public BlockageStrategy(){
    }

    public Move run(ArrayList<Move> moves, GameBoard gameBoard, Dices dices) {
        if(moves.isEmpty()){
            return(new Move(0, 0, false, false, false));
        }
        HashMap<Move, Integer> movePriorities = new HashMap<>();
        for (Move move : moves) {
            if(move.isCanKick()){
                return move;
            }
        movePriorities.put(move, checkSurrounding(move, gameBoard));
        }
        AtomicReference<Move> bestMove = new AtomicReference<>(moves.get(0));
        int max = Collections.max(movePriorities.values());
        movePriorities.forEach((key, value) -> {
            if(value == max){
                bestMove.set(key);
            }
        });
        return bestMove.get();
    }

    public int checkSurrounding(Move move, GameBoard gameBoard){
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
