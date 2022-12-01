
import java.util.ArrayList;
import java.util.Stack;

public class GameBoard {

    public static int ROUNDS = 0;

    private ArrayList<Stack<Piece>> positions = new ArrayList<>();
    private Agent white;
    private Agent black;

    int blackPiecesInGame = 15;
    int whitePiecesInGame = 15;

    public GameBoard(){
        this.white = new Agent("Alice", true, this);
        this.black = new Agent("Bob", false, this);
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

    public ArrayList<Stack<Piece>> playMove(Move move, Agent agent){
        if(move.isCanKick()
                && (positions.get(move.getNewPosition()).capacity() == 1 && positions.get(move.getNewPosition()).peek().isColour() != agent.isColour())){
            int temp = agent.isColour() ? 0 : 25;
            positions.get(temp).push(positions.get(move.getNewPosition()).pop());
        }
        positions.get(move.getNewPosition()).push(positions.get(move.getPreviousPosition()).pop());
        return positions;
    }

    /**Überprüft ob alle Steine im letzten Quadranten sind und die Steine nun ins Ziel gewürfelt werden dürfen*/
    public boolean checkEndgame(Agent agent){
        int stoneCount = 0;
        if(agent.isColour()){
            for(int i = 19; i<25; i++) {
                if (!positions.get(i).isEmpty() && positions.get(i).peek().isColour()) {
                    stoneCount = stoneCount + positions.get(i).capacity();
                }
            }
        } else {
            for (int i = 1; i < 6; i++){
                if(!positions.get(i).isEmpty() && !positions.get(i).peek().isColour()){
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
    /**Gibt einem eine Liste mit allen legalen Würfen für den Würfel wieder*/
    public ArrayList<Move> giveMoves(int count, Agent agent){
        ArrayList<Move> moves = new ArrayList<>();
        for(int i = 1; i<24; i++) {
            if(positions.get(i).size() != 0 && checkMove(count, i, agent).isLegal()){
                moves.add(checkMove(count, i, agent));
            }
        }
        return moves;
    }
    public Move checkMove(int count, int position, Agent agent){
        int newPosition = agent.isColour() ? position+count : position-count;
        boolean inRange = (newPosition > 0 && newPosition < 25) ? true : false;

        if(!inRange){
            return new Move(position, newPosition, false, false);
        } else if (inRange && positions.get(newPosition).isEmpty() || positions.get(newPosition).peek().isColour() == agent.isColour()) {
            return new Move(position, newPosition, false, true);
        } else if (inRange && positions.get(newPosition).size() == 1 && positions.get(newPosition).peek().isColour() != agent.isColour()) {
            return new Move(position, newPosition, true, true);
        }
        return new Move(position, newPosition, false, false);
    }

    public ArrayList<Move> exitJail(Agent agent, Dices dices){
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(checkMove(dices.getFace1(), agent.isColour() ? 0 : 25, agent));
        moves.add(checkMove(dices.getFace2(), agent.isColour() ? 0 : 25, agent));
        return moves;
    }


    public boolean checkFinal(Agent agent){
        boolean won = true;
        for(Stack<Piece> stack: positions){
            if(stack.peek().isColour() == agent.isColour()){
                won = false;
            }
        }
        return won;
    }

    public ArrayList<Move> giveFinalMoves(Agent agent, Dices dices){
        ArrayList<Move> moves = new ArrayList<>();
        if(checkEndgame(agent)){
            return moves;
        }

        if(agent.isColour()){
            if(!positions.get(dices.getFace1()).isEmpty() && positions.get(dices.getFace1()).peek().isColour()){
                moves.add(new Move(dices.getFace1(), 0, false, true, true));
            }
            if(!positions.get(dices.getFace2()).isEmpty() && positions.get(dices.getFace2()).peek().isColour()) {
                moves.add(new Move(dices.getFace1(), 0, false, true, true));
            }
        } else {
            if(!positions.get(dices.getFace1()).isEmpty() && positions.get(25 - dices.getFace1()).peek().isColour()){
                moves.add(new Move(dices.getFace1(), 0, false, true, true));
            }
            if(!positions.get(dices.getFace2()).isEmpty() && positions.get(25 - dices.getFace2()).peek().isColour()) {
                moves.add(new Move(dices.getFace1(), 0, false, true, true));
            }
        }
        if(moves.isEmpty()){
            for(Move move : giveMoves(dices.getFace1(), agent)){
                if(move.isLegal())
                    moves.add(move);
            }
            for(Move move : giveMoves(dices.getFace2(), agent)){
                if(move.isLegal())
                    moves.add(move);
            }
        }

        if(moves.isEmpty()){

        }
        return moves;
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


