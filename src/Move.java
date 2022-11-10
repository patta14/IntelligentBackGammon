public class Move {
    private int previousPosition;
    private int newPosition;
    private boolean canKick;

    public Move(int previousPosition, int newPosition, boolean canKick){
        this.previousPosition = previousPosition;
        this.newPosition = newPosition;
        this.canKick = canKick;
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
}
