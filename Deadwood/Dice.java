import java.util.List;
import java.util.Random;

public class Dice {
    private int sides;

    public Dice(int sides) {
<<<<<<< HEAD
        this.sides = sides;
=======
        setSides(sides);
>>>>>>> 077c2bba6b07c61f00a9d782242e1be0e5ac69b6
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
