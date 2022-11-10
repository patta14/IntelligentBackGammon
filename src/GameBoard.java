import sun.management.resources.agent;

import java.util.ArrayList;
import java.util.Stack;

public class GameBoard {

    ArrayList<Stack<Piece>> positions = new ArrayList<>();
    Agent white;
    Agent black;

    int blackPiecesInGame = 15;
    int whitePiecesInGame = 15;

    public GameBoard(Agent white, Agent black){
        this.white = white;
        this.black = black;
        for(int i = 0; i<27; i++){
            positions.add(new Stack<Piece>());
        }

        positions.add(1, setup(2, true));
        positions.add(6, setup(6,false));
        positions.add(8,setup(3,false));
        positions.add(12,setup(6, true));
        positions.add(13,setup(6, false));
        positions.add(17,setup(3, true));
        positions.add(19,setup(6, true));
        positions.add(24,setup(2, false));
        }

    public Stack<Piece> setup(int count, boolean colour){
        Stack<Piece> stack = new Stack<>();
        for (int i = 0; i < count; i++) {
            stack.push(new Piece(colour));
        }
        return stack;
    }

    /**Überprüft ob alle Steine im letzten Quadranten sind und die Steine nun ins Ziel gewürfelt werden dürfen*/
    public boolean checkEndgame(Agent agent){
        int stoneCount = 0;
        if(agent.isColour()){
            for(int i = 19; i<25; i++) {
                if (positions.get(i).peek().isColour()) {
                    stoneCount = stoneCount + positions.get(i).capacity();
                }
            }
        } else {
            for (int i = 1; i < 6; i++){
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

    public ArrayList<Move> giveMoves(int count, int count2, Agent agent){
        ArrayList<Move> moves = new ArrayList<>();
        for(int i = 1; i<24; i++) {
            if(checkMove(count, i, agent).getNewPosition() != checkMove(count, i, agent).getPreviousPosition()){
                moves.add(checkMove(count, i, agent));
            }
            if(checkMove(count2, i, agent).getNewPosition() != checkMove(count2, i, agent).getPreviousPosition()){
                moves.add(checkMove(count2, i, agent));
            }
        }
        return moves;
    }
    public Move checkMove(int count, int position, Agent agent){
        if(positions.get(position).isEmpty()){
            return new Move(position,position,false);
        }
        if (positions.get(position).peek().isColour() == agent.isColour() &&
            positions.get(position + count).isEmpty()) {
            return new Move(position, position+count, false);
        } else if (positions.get(position).peek().isColour() == agent.isColour() && positions.get(position + count).peek().isColour() == agent.isColour()) {
            return new Move(position, position+count, false);
        }
        if (positions.get(position).peek().isColour() == agent.isColour()
                && (positions.get(position + count).peek().isColour() == false
                && positions.get(position + count).capacity() == 1)){
            return new Move(position, position+count, true);
        }
        return new Move(position,position,false);
    }

    public ArrayList<Stack<Piece>> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Stack<Piece>> positions) {
        this.positions = positions;
    }

    public Agent getWhite() {
        return white;
    }

    public void setWhite(Agent white) {
        this.white = white;
    }

    public Agent getBlack() {
        return black;
    }

    public void setBlack(Agent black) {
        this.black = black;
    }

    public int getBlackPiecesInGame() {
        return blackPiecesInGame;
    }

    public void setBlackPiecesInGame(int blackPiecesInGame) {
        this.blackPiecesInGame = blackPiecesInGame;
    }

    public int getWhitePiecesInGame() {
        return whitePiecesInGame;
    }

    public void setWhitePiecesInGame(int whitePiecesInGame) {
        this.whitePiecesInGame = whitePiecesInGame;
    }
}


