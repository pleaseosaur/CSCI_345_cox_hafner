/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 10 May 2023
 * Purpose: Deadwood: Core game logic
 */

// imports
import java.util.*;

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
            ui.startDayMessage(manager.getDays());
            // while day is active (no. open Scenes > 1)
            while(!endDay()) {
                // while current player is active
                Player currentPlayer = manager.getCurrentPlayer();
                ui.startTurnMessage(currentPlayer);
                // display available actions (end turn should always be an option)
                boolean turnActive = true;
                while(turnActive) {
                    Map<String, String> availableRoles = getAvailableRoles(currentPlayer);
                    String action = ui.getPlayerAction(currentPlayer, availableRoles);
                    switch (action) {
                        case "move" -> {
                            move(ui.promptMove(currentPlayer));
                            ui.displayMessage("\nYou have moved to: " + currentPlayer.getLocation().getName());
                        }
                        case "take role" -> {
                            takeRole(ui.promptRole(availableRoles));
                            if(currentPlayer.getHasTakenRole()) {
                                ui.displayMessage("\nYou have taken the role of: " + currentPlayer.getRole().getName());
                            }
                        }
                        case "rehearse" -> rehearse();
                        case "act" -> act(); // TODO -- need act logic + dice rolling
                        case "upgrade" -> upgrade(ui.promptUpgrade(manager.getAvailableUpgrades())); // TODO -- this needs work too
                        case "end turn" -> {
                            endTurn();
                            turnActive = false;
                        }
                        case "end" -> endGame(); // TODO -- not implemented yet
                        case "back" -> { // TODO -- current setup doesn't allow for this to be called
                            ui.displayMessage("\nNo problem!");
                            ui.getPlayerAction(currentPlayer, availableRoles);
                        }
                        case "stats" -> ui.displayStats(currentPlayer);
                        case "view" -> manager.displayBoard();
                        default -> {
                            ui.displayMessage("Invalid action");
                        }
                    }
                }
                // end turn will check no. of open scenes and trigger end day if necessary
            }

        }
            // end day will check no. of days and trigger end game if necessary
    }

    public Map<String, String> getAvailableRoles(Player player) {
        Map<String, String> availableRoles = new HashMap<>();
        if(!player.hasRole()) {
            availableRoles = manager.getAvailableRoles();
            if(availableRoles.size() == 0) {
                availableRoles.put("0", "Unfortunately, all available roles have been taken");
            }
        }
        return availableRoles;
    }

    // move: do movement for active player
    public void move(String location) {
        manager.move(location);
    }

    // upgrade: do upgrade for active player
    public void upgrade(String rank) {
        //TODO -- could probably handle similar to role selection

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
        manager.act();
    }

    // endTurn: ends current turn
    public void endTurn() {
        manager.endTurn();
    }

    // endDay: ends current day
    // TODO need to implement this
    public boolean endDay() {
        return manager.endDay();
    }

    // endGame: calculates score and allows premature end
    // TODO need to implement this
    public void endGame() {
        // end game logic
    }

    // rename player loop -- currently allows duplicate names
    public void renamePlayers(){
        for(Player player : manager.getPlayers()){
            String name = ui.getPlayerName(player.getName());
            manager.renamePlayer(player, name);
        }
    }
}
