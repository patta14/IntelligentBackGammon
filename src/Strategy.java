import java.util.ArrayList;

public interface Strategy {
    public Move run(ArrayList<Move> moves, GameBoard gameBoard, Dices dices);
}
