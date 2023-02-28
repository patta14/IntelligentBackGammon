
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class GameBoard {

    public static int ROUNDS = 0;
    public static boolean FINISHED = false;
    private Dices dices = new Dices();

    public Dices getDices() {
        return dices;
    }

    public void setDices(Dices dices) {
        this.dices = dices;
    }

    private ArrayList<Stack<Piece>> positions = new ArrayList<>();
    private Agent white;
    private Agent black;

    int blackPiecesInGame = 15;
    int whitePiecesInGame = 15;

    private SimulationState simulationState;

    public GameBoard(SimulationState simulationState){
        this.simulationState = simulationState;
        this.white = new Agent("Alice", true, this);
        this.black = new Agent("Bob", false, this);
        for(int i = 0; i<27; i++){
            positions.add(new Stack<Piece>());
        }
        //setup der Steine auf dem Spielfeld
        positions.add(1, setup(2, true));
        positions.add(6, setup(5,false));
        positions.add(8,setup(3,false));
        positions.add(12,setup(5, true));
        positions.add(13,setup(5, false));
        positions.add(17,setup(3, true));
        positions.add(19,setup(5, true));
        positions.add(24,setup(2, false));
        }
    //Hilfsmethode um die Startpositionen herzustellen
    public Stack<Piece> setup(int count, boolean colour){
        Stack<Piece> stack = new Stack<>();
        for (int i = 0; i < count; i++) {
            stack.push(new Piece(colour));
        }
        return stack;
    }
    //Spielt einen Spielzug aus
    public ArrayList<Stack<Piece>> playMove(Move move, Agent agent){
        if(!move.isLegal()){
            return positions;
        }
        if(move.isCanKick()
                && (positions.get(move.getNewPosition()).size() == 1 && positions.get(move.getNewPosition()).peek().isColour() != agent.isColour())){
            int temp = !agent.isColour() ? 25 : 0;
            positions.get(temp).push(positions.get(move.getNewPosition()).pop());
        }
        if(checkEndgame(agent) && !positions.get(move.getPreviousPosition()).isEmpty() && move.isOut()){
            positions.get(move.getPreviousPosition()).pop();
            if(checkFinal(agent)){
                finishGame(agent, agent.isColour() ? black : white);
            }
            return positions;
        }
        if(!positions.get(move.getPreviousPosition()).isEmpty()) {
            positions.get(move.getNewPosition()).push(positions.get(move.getPreviousPosition()).pop());
        }
        if(checkFinal(agent)){
            finishGame(agent, agent.isColour() ? black : white);
        }
        return positions;
    }
    //Beendet das Spiel
    public void finishGame(Agent winner, Agent loser){
        if(!FINISHED) {
            System.out.println(winner.getName() + " hat gewonnen!" + " " + ROUNDS);
            try {
                File myObj = new File("result.txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            try {
                FileWriter myWriter = new FileWriter("result.txt", true);
                myWriter.append(winner.getName() + " " + winner.getStrategy().getName() + " " + loser.getName() + " " + loser.getStrategy().getName() + " " + ROUNDS + "\n");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        FINISHED = true;
        simulationState.finish();
    }

    /**Überprüft ob alle Steine im letzten Quadranten sind und die Steine nun ins Ziel gewürfelt werden dürfen*/
    public boolean checkEndgame(Agent agent){
        int stoneCount = 0;
        if(!agent.isColour()){
            for(int i = 19; i<25; i++) {
                if (!positions.get(i).isEmpty() && !positions.get(i).peek().isColour()) {
                    stoneCount = stoneCount + positions.get(i).size();
                }
            }
        } else {
            for (int i = 1; i < 6; i++){
                if(!positions.get(i).isEmpty() && positions.get(i).peek().isColour()){
                    stoneCount = stoneCount + positions.get(i).size();
                }
            }
        }

        if((agent.isColour() && stoneCount == this.getWhitePiecesInGame()) || (!agent.isColour() && stoneCount == this.getBlackPiecesInGame())) {
            return true;
        } else {
            return false;
        }
    }
    /**Gibt einem eine Liste mit allen legalen Würfen für den Würfel wieder*/
    public ArrayList<Move> giveMoves(int count, Agent agent){
        ArrayList<Move> moves = new ArrayList<>();
        //muss das hier nicht <25?
        for(int i = 1; i<25; i++) {
            if(positions.get(i).size() != 0 && positions.get(i).peek().isColour() == agent.isColour() && checkMove(count, i, agent).isLegal()){
                moves.add(checkMove(count, i, agent));
            }
        }
        return moves;
    }
    //Überprüft die Legalität und Eigenschaften eines Zugs
    public Move checkMove(int count, int position, Agent agent){
        int newPosition = agent.isColour() ? position-count : position+count;
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
    //Hilft beim Verlassen des Gefängnis
    public ArrayList<Move> exitJail(Agent agent, Dices dices){
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(checkMove(dices.getFace1(), agent.isColour() ? 25 : 0, agent));
        moves.add(checkMove(dices.getFace2(), agent.isColour() ? 25 : 0, agent));
        return moves;
    }

    //Überprüft ob das Spiel in der Endphase ist und ob Steine rausgewürfelt werden können
    public boolean checkFinal(Agent agent){
        boolean won = true;
        for(Stack<Piece> stack: positions){
            if(!stack.isEmpty() && stack.peek().isColour() == agent.isColour()){
                won = false;
            }
        }
        return won;
    }
    //Ist für die Züge in der Endphase des Spiels verantwortlich und gibt diese zurück
    public Move giveFinalMoves(Agent agent, int count){
        if(!checkEndgame(agent)){
            return new Move(0 , 0 , false, false);
        }
        if(!positions.get(agent.isColour() ? count : 25-count).isEmpty() && (agent.isColour() ? positions.get(count).peek().isColour() : !positions.get(25-count).peek().isColour())){
            return new Move(agent.isColour() ? count : 25-count, 0, false, true, true);
        }
        Move move = giveFinalMovesHelper(agent, count);
        if(move.isLegal()){
            return move;
        }
        if(agent.isColour()){
            for(int i = 6; i > 0; i--){
                if(!positions.get(i).isEmpty() && positions.get(i).peek().isColour() && i-count <= 0){
                    return new Move(i, 0, false, true, true);
                }
            }
        } else {
            for(int i = 19; i < 25; i++){
                if(!positions.get(i).isEmpty() && !positions.get(i).peek().isColour() && i+count >= 25){
                    return new Move(i, 0, false, true, true);
                }
            }
        }
        return new Move(0, 0, false, false);
    }
    //Hilfsmethode für die vorherige Methode
    public Move giveFinalMovesHelper(Agent agent, int count){
        if(agent.isColour()){
            for(int i = 6; i > 0; i--){
                Move move = checkMove(count, i, agent);
                if(move.isLegal()){
                    return move;
                }
            }
        } else {
            for(int i = 19; i < 25; i++){
                Move move = checkMove(count, i, agent);
                if(move.isLegal()){
                    return move;
                }
            }
        }
        return new Move(0, 0, false, false);
    }



    public ArrayList<Stack<Piece>> getPositions() {
        return positions;
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
        int temp = 0;
        for (Stack<Piece> stack:positions) {
            if(!stack.isEmpty() && !stack.peek().isColour()){
                temp += stack.size();
            }
        }
        blackPiecesInGame = temp;
        return blackPiecesInGame;
    }

    public int getWhitePiecesInGame() {
        int temp = 0;
        for (Stack<Piece> stack:positions) {
            if(!stack.isEmpty() && stack.peek().isColour()){
                temp += stack.size();
            }
        }
        whitePiecesInGame = temp;
        return whitePiecesInGame;
    }

}


