import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class UI {

    final Scanner scanner;


    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMessage(String message){
        for(char c : message.toCharArray()){
//            System.out.print(c);
//            System.out.flush();
            try {
                System.out.print(c);
                System.out.flush();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println();
    }

    public void displayWelcomeMessage() {
        displayMessage("Welcome to Deadwood!"); // basic - add more later if desired
        displayMessage("You may type 'quit' at any time to terminate the game.");
    }

    public int getPlayerCount() {
        displayMessage("Please enter the number of players: ");
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

    public boolean promptRename(){

        Map<String, String> options = new HashMap<>();
        options.put("y", "yes");
        options.put("n", "no");

        displayMessage("Would you like to create custom names? (y/n): ");

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

    public String getPlayerName(String name) {

        displayMessage("Please enter new name for " + name + ": ");
        String newName;

        try {
            newName = scanner.next();
        } catch (Exception e) {
            return getPlayerName(name);
        }

        return newName;
    }

    public void startTurnMessage(Player player) {
        String playerName = player.getName();
        String locationName = player.getLocation().getName();

        displayMessage("It is " + playerName + "'s turn.");
        displayMessage("Your current location is: " + locationName);
        displayStats(player);
    }

    //TODO -- remove "move" option when player has moved already
    //TODO -- remove "upgrade" option when player has upgraded already
    public String getPlayerAction(Player player, List<String> availableRoles) {

        String locationName = player.getLocation().getName();
        Role playerRole = player.getRole();

        StringBuilder prompt = new StringBuilder();
        Map<String, String> availableActions = new HashMap<>();

        if (locationName.equals("Trailer")) {

            prompt.append("""
                    Your available actions are:\s
                    1. move
                    2. end turn""");

            availableActions.put("1", "move");
            availableActions.put("2", "end turn");
            availableActions.put("end", "end turn");

        } else if (locationName.equals("Casting Office")) {

            if(player.getRank() < 6) {
                prompt.append("""
                        Your available actions are:\s
                        1. move
                        2. upgrade
                        3. end turn""");

                availableActions.put("1", "move");
                availableActions.put("2", "upgrade");
                availableActions.put("3", "end turn");
                availableActions.put("end", "end turn");

            } else {
                prompt.append("You are already at the highest rank. No upgrades are available.\n");
                prompt.append("""
                        Your available actions are:\s
                        1. move
                        2. end turn""");

                availableActions.put("1", "move");
                availableActions.put("2", "end turn");
                availableActions.put("end", "end turn");
            }

        } else if (player.hasRole()) {
            displayMessage("You are currently playing the role of " + playerRole + ".\n");

            if (playerRole.isOnCard()) {

                prompt.append("""
                        Your available actions are:\s
                        1. rehearse
                        2. act
                        3. end turn""");

                availableActions.put("1", "rehearse");
                availableActions.put("2", "act");
                availableActions.put("3", "end turn");
                availableActions.put("end", "end turn");

            } else {

                prompt.append("""
                        Your available actions are:\s
                        1. act
                        2. end turn""");

                availableActions.put("1", "act");
                availableActions.put("2", "end turn");
                availableActions.put("end", "end turn");

            }
        } else {
            prompt.append("You are not currently playing a role.\n");

            if ((availableRoles.size() == 1) && (availableRoles.contains("available"))) {

                for (String role : availableRoles) {
                    displayMessage(role);
                }

                prompt.append("""
                            Your available actions are:\s
                            1. move
                            2. end turn""");

                availableActions.put("1", "move");
                availableActions.put("2", "end turn");
                availableActions.put("end", "end turn");

            } else {
                prompt.append("The available roles are: \n");

                for (String role : availableRoles) {
                    prompt.append(role).append("\n");
                }

                prompt.append("""
                        Your available actions are:\s
                        1. move
                        2. take role
                        3. end turn""");

                availableActions.put("1", "move");
                availableActions.put("2", "take role");
                availableActions.put("role", "take role");
                availableActions.put("3", "end turn");
                availableActions.put("end", "end turn");
            }
        }

        displayMessage(prompt.toString());

        return getChoiceInput(availableActions);
    }

    public String getChoiceInput(Map<String, String> availableActions) {
        String choice = scanner.next().toLowerCase();

        if(choice.equals("quit")) {
            quitGame();
        } else if(availableActions.containsKey(choice)) {
            return availableActions.get(choice);
        } else if(availableActions.containsValue(choice)) {
            return choice;
        }

        displayMessage("That is not a valid choice.");
        displayMessage("Please enter a valid choice: ");

        return getChoiceInput(availableActions);
    }

    public String promptMove(Player player) {
        List<Location> neighbors = player.getLocation().getNeighbors();
        Map<String, String> options = new HashMap<>();
        StringBuilder prompt = new StringBuilder();

        int i = 1;

        prompt.append("The available locations are: \n");

        for(Location neighbor : neighbors) {
            prompt.append(i).append(". ").append(neighbor.getName()).append("\n");
            options.put(Integer.toString(i), neighbor.getName());
            i++;
        }
        prompt.append("\nPlease enter the name or number of the location you would like to move to: ");
        displayMessage(prompt.toString());

        return getChoiceInput(options);
    }

    public String promptRole(List<String> availableRoles) {
        Map<String, String> options = new HashMap<>();
        StringBuilder prompt = new StringBuilder();

        int i = 1;

        prompt.append("The available roles are: \n");

        for(String role : availableRoles) {
            prompt.append(i).append(". ").append(role).append("\n");
            options.put(Integer.toString(i), role);
            i++;
        }

        displayMessage(prompt.toString());

        return getChoiceInput(options);
    }

    // TODO -- additional options needed for upgrades -- may display same number w/ different price options
    // TODO -- need to handle case where player does not have enough money for any upgrades
    public String promptUpgrade(List<String> upgrades) {
        Map<String, String> options = new HashMap<>();
        StringBuilder prompt = new StringBuilder();

        int i = 1;

        // TODO -- duplicate code - refactor
        prompt.append("The available upgrades are: \n");
        for(String upgrade : upgrades) {
            prompt.append(i).append(". ").append(upgrade).append("\n");
            options.put(Integer.toString(i), upgrade);
            i++;
        }

        displayMessage(prompt.toString());

        return getChoiceInput(options);
    }

    public void displayBoard(List<Player> players){
        for(Player player : players){
            displayMessage(player.getName() + " is at " + player.getLocation().getName());
        }
    }

    public void displayStats(Player p){
        displayMessage("Your current stats are: \n" +
                "Rank: " + p.getRank() + "\n" +
                "Credits: " + p.getCredits() + "\n" +
                "Dollars: " + p.getDollars() + "\n" +
                "Practice Chips: " + p.getPracticeChips() + "\n");

    }

    public void quitGame() {
        displayMessage("Your loss! Enjoy not being a world-renown thespian.");
        System.exit(0); // TODO -- this should probably be "setGameActive(false)" or something
    }
}
