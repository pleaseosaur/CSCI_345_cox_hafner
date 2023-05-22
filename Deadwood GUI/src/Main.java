/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 16 May 2023
 * Purpose: Main
 */

// imports

import controller.GameManager;
import javafx.application.Application;
import javafx.stage.Stage;
import view.DisplayGame;

public class Main extends Application {
    public static void main (String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        GameManager manager = new GameManager();
//        Deadwood game = new Deadwood(manager);
        DisplayGame view = new DisplayGame();
//        game.startGame();
        primaryStage.setScene(view.getScene());
        primaryStage.show();
        view.startGame(manager);
    }
}
