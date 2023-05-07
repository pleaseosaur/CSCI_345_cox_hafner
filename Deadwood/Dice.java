import java.util.List;
import java.util.Random;

public class Dice {
    private int numDice;
    private int sides;

    public Dice(int sides) {
        setSides(sides);
    }

    public void setSides(int num) {
        this.sides = num;
    }

    public int getSides() {
        return this.sides;
    }

    public int rollDie() {
        Random rand = new Random();
        int dieRoll = rand.nextInt(sides)+1;
        return dieRoll;
    }

    public int actRoll(){
        // do acting calculations
        return -1;
    }

    public List<Integer> wrapRoll(){
        // do wrap calculations
        return null; //dummy return
    }

}
