// Core game logic


import java.util.ArrayList;
import java.util.List;

public class Deadwood {

    final UI ui;
    private final GameManager manager;
    private boolean gameActive;


    public Deadwood(GameManager manager) {
        this.manager = manager;
        this.ui = new UI();
    }

    public void startGame() {
        ui.displayWelcomeMessage();
        int players = ui.getPlayerCount();
        manager.setupGame(players);
        setGameActive(true);
        if(ui.promptRename()){
            renamePlayers();
        }
        runGame();
    }

    public void setGameActive(boolean b) {
        this.gameActive = b;
    }

    public boolean getGameActive() {
        return this.gameActive;
    }

    public void runGame() {
        // while game is active (no. days > 0)
            // while day is active (no. open Scenes > 1)
                // while current player is active
                    // display available actions (end turn should always be an option)
                    // if player chooses move, display available locations
                    // perform player action choice
                    // display available actions again
                    // if player chooses end turn, end the turn
                    // if end turn is the only available option, end turn automatically
                    // get next player
                // when open scenes = 1, end the day
            // when days = 0, end the game
    }

    public void move(Location location) {
        // move logic
    }

    public void upgrade(int rank) {
        manager.upgrade(rank);
    }

    public void takeRole(Role role) {
        manager.takeRole(role);
    }

    public void rehearse() {
        manager.rehearse();
    }

    public void act() {
        // act logic
    }

    public void endTurn() {
        manager.endTurn();
    }

    public void endDay() {
        // end day logic
    }

    public void endGame() {
        // end game logic
    }

    public void renamePlayers(){

        for(Player player : manager.getPlayers()){
            String name = ui.getPlayerName(player.getName());
            manager.renamePlayer(player, name);
        }
    }
}
