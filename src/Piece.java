public class Piece {
    private boolean colour;
    private int position;

    public Piece(boolean colour){
        this.colour = colour;
    }

    public int getPosition() {
        return position;
    }

    public boolean isColour() {
        return colour;
    }

    public int move(int count){
        position = position + count;
                return position;
    }
}
