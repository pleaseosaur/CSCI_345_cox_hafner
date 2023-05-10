/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 10 May 2023
 * Purpose: Main
 */

// imports
import java.util.Set;

public class Main {
    public static void main (String[] args) {
        GameManager manager = new GameManager();
        Deadwood game = new Deadwood(manager);
        game.startGame();
    }
}
