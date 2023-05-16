/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 10 May 2023
 * Purpose: Dice: Dice object containing randomizer and dice rolling logic
 */

// imports
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Dice {
    // fields
    private int sides; // signifies dice type, will be d6 here
    private Random rand;

    // constructor
    public Dice(int sides) {
        setSides(sides);
        this.rand = new Random();
    }

    // getters and setters
    public void setSides(int num) {
        this.sides = num;
    }

    public int getSides() {
        return this.sides;
    }

    // rollDie: signifies one dice roll
    public int rollDie() {
        return rand.nextInt(sides)+1+5;
    }

    // wrapRoll: loop to get all dice rolls for scene wrap
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
