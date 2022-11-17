import jdk.nashorn.internal.runtime.regexp.joni.constants.StringType;
import ec.util.MersenneTwisterFast;

import java.util.ArrayList;

public class RandomMovesStrategy implements Strategy {
    private MersenneTwisterFast rng = new MersenneTwisterFast();

    public RandomMovesStrategy(){

    }
    public Move run(ArrayList<Move> moves, GameBoard gameBoard, Dices dices){
        if(moves.size() == 0){
            return new Move(0, 0, false, false);
        }
        return moves.get(rng.nextInt(moves.size()));
    }
}
