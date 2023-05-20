/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 16 May 2023
 * Purpose: Main
 */

// imports
import controller.Deadwood;
import controller.GameManager;

public class Main {
    public static void main (String[] args) {
        GameManager manager = new GameManager();
        Deadwood game = new Deadwood(manager);
        game.startGame();
    }
}
