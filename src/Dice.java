import ec.util.MersenneTwisterFast;



public class Dice{
    private int face;
    private MersenneTwisterFast rng = new MersenneTwisterFast();

    public Dice(){
        this.face = roll();
    }


    public int roll(){
        this.face = rng.nextInt(6);
        return (this.face+1);
    }
}
