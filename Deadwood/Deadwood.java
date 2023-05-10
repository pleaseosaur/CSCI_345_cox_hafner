/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 10 May 2023
 * Purpose: Deadwood: Core game logic
 */

// imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deadwood {
    // fields
    final UI ui;
    private final GameManager manager;
    private boolean gameActive;

    // constructor
    public Deadwood(GameManager manager) {
        this.manager = manager;
        this.ui = new UI();
    }

    // startGame: queries for player count, calls GameManager to set up accordingly, then starts game
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

    // runGame: Main gameplay loop
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
            }
                // when open scenes = 1, end the day
        }
            // when days = 0, end the game
    }

    // startTurn: starts turn
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

    // playerAction: make action based on player's input
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

    // move: do movement for active player
    public void move(String location) {
        manager.move(location);
    }

    // upgrade: do upgrade for active player
    public void upgrade(String rank) {
        //TODO -- need to convert first char of string to int
        //TODO -- need to handle cases where the string is empty or there is no int to be converted

        manager.upgrade(Integer.parseInt(rank));
    }

    // takeRole: active player takes role
    public void takeRole(String role) {
        manager.takeRole(role);
    }

    // rehearse: active player rehearses
    public void rehearse() {
        manager.rehearse();
    }

    // act: active player acts
    public void act() {
        // TODO act logic
    }

    // endTurn: ends current turn
    public void endTurn() {
        manager.endTurn();
    }

    // endDay: ends current day
    // TODO need to implement this
    public void endDay() {
        // end day logic
    }

    // endGame: calculates score and allows premature end
    // TODO need to implement this
    public void endGame() {
        // end game logic
    }

    // rename player loop
    public void renamePlayers(){
        for(Player player : manager.getPlayers()){
            String name = ui.getPlayerName(player.getName());
            manager.renamePlayer(player, name);
        }
    }
}
