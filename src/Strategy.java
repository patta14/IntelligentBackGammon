import java.util.ArrayList;

public interface Strategy {
    Move run(ArrayList<Move> moves, GameBoard gameBoard, Dices dices);
}
