import java.util.ArrayList;
import java.util.Stack;

public class GameBoard {

    ArrayList<Stack<Piece>> positions;
    Agent white;
    Agent black;

    int blackPiecesInGame = 15;
    int whitePiecesInGame = 15;

    public GameBoard(ArrayList<Stack<Piece>> positions, Agent white, Agent black){
        this.positions = positions;
        this.white = white;
        this.black = black;
    }

    /**Überprüft ob alle Steine im letzten Quadranten sind und die Steine nun ins Ziel gewürfelt werden dürfen*/
    public boolean checkEndgame(Agent agent){
        int stoneCount = 0;
        if(agent.colour){
            for(int i = 18; i<24; i++) {
                if (positions.get(i).peek().isColour()) {
                    stoneCount = stoneCount + positions.get(i).capacity();
                }
            }
        } else {
            for (int i = 0; i < 6; i++){
                if(!positions.get(i).peek().isColour()){
                    stoneCount = stoneCount + positions.get(i).capacity();
                }
            }
        }

        if(stoneCount == whitePiecesInGame || stoneCount == blackPiecesInGame) {
            return true;
        } else {
            return false;
        }
    }


}
