// Core game logic


import java.util.ArrayList;
import java.util.Collections;
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
        while(getGameActive()) {
            // while day is active (no. open Scenes > 1)
            while(!(manager.endDay())) {
                // while current player is active
                startTurn(manager.getCurrentPlayer());
                // display available actions (end turn should always be an option)
                // if player chooses move, display available locations
                // perform player action choice
                // display available actions again
                // if player chooses end turn, end the turn
                // if end turn is the only available option, end turn automatically
                // get next player
                endTurn();
            }
                // when open scenes = 1, end the day
            }
            // when days = 0, end the game
        }
    }

    public void startTurn(Player player) {
        ui.startTurnMessage(player);
        List<String> availableRoles = new ArrayList<>();
        if (!(player.hasRole())) {
            availableRoles = manager.getAvailableRoles();
            if (availableRoles.size() == 0) {
                availableRoles.add("Unfortunately, all available roles have been taken");
            }
        }

        String action = ui.getPlayerAction(player, availableRoles);
        // TODO -- implement player action logic
        playerAction(action, player, availableRoles);
    }

    public void playerAction(String action, Player player, List<String> availableRoles) {
        switch (action) {
            case "move" -> move(ui.promptMove(player));
            case "take role" -> takeRole(ui.promptRole(availableRoles));
            case "rehearse" -> rehearse();
            case "act" -> act(); // TODO -- need act logic + dice rolling
            case "upgrade" -> upgrade(ui.promptUpgrade(manager.getAvailableUpgrades()));
            case "end turn" -> endTurn();
            case "end" -> endGame();
            default -> {
            }
        }
    }

    public void move(String location) {
        manager.move(location);
    }

    public void upgrade(String rank) {

        //TODO -- need to convert first char of string to int
        //TODO -- need to handle cases where the string is empty or there is no int to be converted

        manager.upgrade(Integer.parseInt(rank));
    }

    public void takeRole(String role) {
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
