import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Dice {
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

    public List<Integer> wrapRoll(int budget){
        // do wrap calculations
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < budget; i++){
            result.add(rollDie());
        }
        result.sort(Collections.reverseOrder());
        return result;
    }

}
