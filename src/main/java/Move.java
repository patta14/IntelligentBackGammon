public class Move {
    private int previousPosition;
    private int newPosition;
    private boolean canKick;

    private boolean legal;

    private boolean out;

    public Move(int previousPosition, int newPosition, boolean canKick, boolean legal){
        this.previousPosition = previousPosition;
        this.newPosition = newPosition;
        this.canKick = canKick;
        this.legal = legal;
        this.out = false;
    }

    public Move(int previousPosition, int newPosition, boolean canKick, boolean legal, boolean out){
        this.previousPosition = previousPosition;
        this.newPosition = newPosition;
        this.canKick = canKick;
        this.legal = legal;
        this.out = out;
    }

    public int getPreviousPosition() {
        return previousPosition;
    }

    public void setPreviousPosition(int previousPosition) {
        this.previousPosition = previousPosition;
    }

    public int getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(int newPosition) {
        this.newPosition = newPosition;
    }

    public boolean isCanKick() {
        return canKick;
    }

    public void setCanKick(boolean canKick) {
        this.canKick = canKick;
    }

    public boolean isLegal() {
        return legal;
    }

    public void setLegal(boolean legal) {
        this.legal = legal;
    }

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }
}
