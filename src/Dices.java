import ec.util.MersenneTwisterFast;



public class Dices{
    private int face1;
    private int face2;
    private MersenneTwisterFast rng = new MersenneTwisterFast();

    public Dices(){

        this.face1 = rng.nextInt(6) + 1;
        this.face2 = rng.nextInt(6) + 1;
    }


    public Dices roll(){
        this.face1 = rng.nextInt(6) + 1;
        this.face2 = rng.nextInt(6) + 1;
        return this;
    }

    public int getFace1() {
        return face1;
    }

    public void setFace1(int face1) {
        this.face1 = face1;
    }

    public int getFace2() {
        return face2;
    }

    public void setFace2(int face2) {
        this.face2 = face2;
    }
}
