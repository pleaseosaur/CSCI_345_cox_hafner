/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 10 May 2023
 * Purpose: UI: Handles output and player input
 */

// imports
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class UI {
    // fields
    final Scanner scanner;

    // constructor
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    // displayMessage: prints message to terminal
    public void displayMessage(String message){
        for(char c : message.toCharArray()){
            try {
                System.out.print(c);
                System.out.flush();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println();
    }

    public void displayPrompt(String message){
        for(char c : message.toCharArray()){
            try {
                System.out.print(c);
                System.out.flush();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // displayWelcomeMessage: initial welcome prompt
    public void displayWelcomeMessage() {
        displayMessage("Welcome to Deadwood!\n"); // basic - add more later if desired
        displayMessage("Once the game has started, you may type any of the following commands at any time:\n");
        helpMessage();
    }

    public void helpMessage() {
        displayMessage("'help' - displays this message");
        displayMessage("'stats' - displays your current stats");
        displayMessage("'quit' - ends the game\n");
    }

    public void startDayMessage(int days) {
        displayMessage("\nA new day has begun!");
        displayMessage("There are " + days + " days remaining.");
    }

    // getPlayerCount: prompts for and gets player count from user
    public int getPlayerCount() {
        displayPrompt("Please enter the number of players: ");
        try {
            String numPlayersInput = scanner.next().toLowerCase();
            int numPlayers = 0;
            if(numPlayersInput.equals("quit")) {
                quitGame();
            } else {
                numPlayers = Integer.parseInt(numPlayersInput);
            }
            if(numPlayers < 2 || numPlayers > 8) {
                displayMessage("Sorry, the number of players should be between 2 and 8.");
                return getPlayerCount();
            } else {
                return numPlayers;
            }
        } catch(NumberFormatException e) {
            displayMessage("That doesn't look like any number I've ever seen!");
            return getPlayerCount();
        }
    }

    // promptRename: asks users if they want custom names
    public boolean promptRename(){
        Map<String, String> options = new HashMap<>();
        options.put("y", "yes");
        options.put("n", "no");

        displayPrompt("Would you like to create custom names? (y/n): ");

        String choice = scanner.next().toLowerCase();

        if(options.containsKey(choice)) {
            return options.get(choice).equals("yes");
        } else if(options.containsValue(choice)) {
            return choice.equals("yes");
        } else {
            displayMessage("Please enter 'y' ('yes') or 'n' ('no').");
            return promptRename();
        }
    }

    // getPlayerName: assigns player name for given player
    public String getPlayerName(String name) {

        displayPrompt("\nPlease enter new name for " + name + ": ");
        String newName;

        try {
            newName = scanner.next();
        } catch (Exception e) {
            return getPlayerName(name);
        }

        return newName;
    }

    // startTurnMessage: message at start of turn
    public void startTurnMessage(Player player) {
        String playerName = player.getName();
        String locationName = player.getLocation().getName();

        displayMessage("\nIt is " + playerName + "'s turn.");
        displayMessage("\nYour current location is: " + locationName + "\n");
    }

    // getPlayerAction: prompts user for action
    public String getPlayerAction(Player player, Map<String, String> availableRoles) {

        Map<String, String> availableActions = new HashMap<>();

        switch (player.getLocation().getName()) {
            case "Trailer" -> trailerActions(player, availableActions);
            case "Casting Office" -> officeActions(player, availableActions);
            default -> setActions(player, availableActions, availableRoles);
        }

        displayMessage(buildPrompt(availableActions));

        displayPrompt("Please enter your desired action: ");

        return getChoiceInput(availableActions);
    }

    // trailerActions: gets actions for when player is at trailer
    private void trailerActions(Player player, Map<String, String> availableActions) {
        buildActions(availableActions, "move", player.getHasMoved());
        buildActions(availableActions, "end turn");
    }

    // officeActions: gets actions for when player is at casting office
    private void officeActions(Player player, Map<String, String> availableActions) {
        if(player.getRank() < 6) {
            buildActions(availableActions, "move", player.getHasMoved());
            buildActions(availableActions, "upgrade", player.getHasUpgraded());

        } else {
            displayMessage("You are already at the highest rank. No upgrades are available.\n\n");
            buildActions(availableActions, "move", player.getHasMoved());

        }
        buildActions(availableActions, "end turn");
    }

    // setActions: sets actions for player based on available actions
    private void setActions(Player player, Map<String, String> availableActions, Map<String, String> availableRoles) {
        if(player.hasRole() && !player.getHasActed() && !player.getHasTakenRole()) {
            if(player.getRole().isOnCard()) {
                buildActions(availableActions, "rehearse", player.getHasRehearsed());
            }
            buildActions(availableActions, "act", player.getHasActed());
        } else if(!availableRoles.containsKey("0")) {
            buildActions(availableActions, "take role", player.hasRole());
        }
        buildActions(availableActions, "end turn");
    }

    // builds hashmap connecting available actions to prompts
    private void buildActions(Map<String, String> availableActions, String action, boolean alreadyDone) {
        if(!alreadyDone) {
            int i = availableActions.size() + 1;
            availableActions.put(String.valueOf(i), action);
        }
    }

    private void buildActions(Map<String, String> availableActions, String action) {
        buildActions(availableActions, action, false);
    }

    // builds prompts for player to choose action
    public String buildPrompt(Map<String, String> availableActions) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("\nYour available actions are:\n");

        for(Map.Entry<String, String> input : availableActions.entrySet()) {
            prompt.append(input.getKey()).append(". ").append(input.getValue()).append("\n");
        }
        return prompt.toString();
    }

    // gets choice from player
    public String getChoiceInput(Map<String, String> availableActions) {
        String choice = scanner.next().toLowerCase();

        if(choice.equals("quit")) {
            quitGame();
        } else if (choice.equals("help")) {
            helpMessage();
            return getChoiceInput(availableActions);
        } else if(choice.equals("stats")) {
            return "stats";
        } else if (choice.equals("cancel")) {
            return "cancel";
        } else if (availableActions.containsKey(choice)) {
            return availableActions.get(choice);
        } else if (availableActions.containsValue(choice)) {
            return choice;
        }

        displayMessage("That is not a valid choice.");
        displayMessage("Please enter a valid choice: ");

        return getChoiceInput(availableActions);
    }

    // gets destination locations when player chooses to move
    public String promptMove(Player player) {
        List<Location> neighbors = player.getLocation().getNeighbors();
        Map<String, String> options = new HashMap<>();
        StringBuilder prompt = new StringBuilder();

        int i = 1;

        prompt.append("\nThe available locations are: \n");

        for(Location neighbor : neighbors) {
            prompt.append(i).append(". ").append(neighbor.getName()).append("\n");
            options.put(Integer.toString(i), neighbor.getName());
            i++;
        }

        displayMessage(prompt.toString());
        displayPrompt("\nPlease enter the name or number of the location you would like to move to: ");

        return getChoiceInput(options);
    }

    // gets available roles when player decides to take role
    public String promptRole(Map<String, String> availableRoles) {
        Map<String, String> options = new HashMap<>();
        StringBuilder prompt = new StringBuilder();

        int i = 1;

        prompt.append("\nThe available roles are: \n");

        for(Map.Entry<String, String> role : availableRoles.entrySet()) {
            prompt.append(i).append(". ").append(role.getKey()).append(role.getValue()).append("\n");
            options.put(Integer.toString(i), role.getKey());
            i++;
        }

        displayMessage(prompt.toString());
        displayPrompt("\nPlease enter the number of the role you would like to take: ");

        return getChoiceInput(options);
    }

    // gets available ranks when player decides to upgrade
    // TODO -- additional options needed for upgrades -- may display same number w/ different price options
    // TODO -- need to handle case where player does not have enough money for any upgrades
    public String promptUpgrade(List<String> upgrades) {
        Map<String, String> options = new HashMap<>();
        StringBuilder prompt = new StringBuilder();

        int i = 1;

        // TODO -- duplicate code - refactor
        prompt.append("\nThe available upgrades are: \n");
        for(String upgrade : upgrades) {
            prompt.append(i).append(". ").append(upgrade).append("\n");
            options.put(Integer.toString(i), upgrade);
            i++;
        }

        displayMessage(prompt.toString());

        return getChoiceInput(options);
    }

    // displays where each player is
    public void displayBoard(List<Player> players){
        for(Player player : players){
            displayMessage(player.getName() + " is at " + player.getLocation().getName());
        }
    }

    // displays player's stats
    public void displayStats(Player p){
        if(p.hasRole()) {
            displayMessage("\nHere are your current stats: \n" +
                    "Location: " + p.getLocation().getName() + "\n" +
                    "Role: " + p.getRole().getName() + "\n" +
                    "Rank: " + p.getRank() + "\n" +
                    "Credits: " + p.getCredits() + "\n" +
                    "Dollars: " + p.getDollars() + "\n" +
                    "Practice Chips: " + p.getPracticeChips() + "\n");
        } else {
            displayMessage("\nHere are your current stats: \n" +
                    "Location: " + p.getLocation().getName() + "\n" +
                    "Rank: " + p.getRank() + "\n" +
                    "Credits: " + p.getCredits() + "\n" +
                    "Dollars: " + p.getDollars() + "\n" +
                    "Practice Chips: " + p.getPracticeChips() + "\n");
            }
    }

    // quit game
    public void quitGame() {
        displayMessage("\nYour loss! Enjoy not being a world-renown thespian.");
        System.exit(0); // TODO -- this should probably be "setGameActive(false)" or something
    }
}
