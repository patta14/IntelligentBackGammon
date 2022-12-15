import ec.util.MersenneTwisterFast;

import java.util.ArrayList;

public class AttackMovesStrategy implements Strategy {
    private MersenneTwisterFast rng = new MersenneTwisterFast();

    private String name = "Attack";
    public AttackMovesStrategy(){
    }
    public Move run(ArrayList<Move> moves, GameBoard gameBoard, Dices dices){
        if(moves.size() == 0){
            return new Move(0, 0, false, false);
        }
        //random move als Standard festlegen
        Move finalmove = moves.get(rng.nextInt(moves.size()));

        for (Move move : moves) {
            int range = move.getNewPosition();
            int max = 0;
            //Move, der gegnerische Steine kickt, wird präferiert
            if (move.isCanKick()) {
                return move;
            //ansonsten Move, der am nächsten zum Heimfeld führt.
            } else if (range > max) {
                max = range;
                finalmove = move;
            }
        }
        //sonst random Move
        return finalmove;
    }

    public String getName() {
        return name;
    }
}
