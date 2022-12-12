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

    public void setColour(boolean colour) {
        this.colour = colour;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
