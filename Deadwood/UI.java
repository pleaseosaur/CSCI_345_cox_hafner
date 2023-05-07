import java.util.Scanner;
import java.util.List;

public class UI {

    final Scanner scanner;


    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMessage(String message){
        System.out.println(message);
    }

    public void displayWelcomeMessage() {
        displayMessage("Welcome to Deadwood!"); // basic - add more later if desired
        displayMessage("You may type 'quit' at any time to terminate the game.");
    }

    public int getPlayerCount() {
        displayMessage("Please enter the number of players: ");
        try {
            String numPlayersInput = scanner.next();
            int numPlayers = 0;
            if(numPlayersInput.equals("quit")) {
                displayMessage("Your loss! Enjoy not being a world-renown thespian.");
                System.exit(0); // TODO -- this should probably be "setGameActive(false)" or something
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

        displayMessage("Would you like to create custom names? (y/n): ");
        try {
            String choice = scanner.next();

            if(choice.equals("y")){
                return true;
            }
            else if(choice.equals("n")){
                return false;
            }
        } catch(Exception e){
            displayMessage("Please choose either 'y' or 'n'");
            return promptRename();
        }
        return false;
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

    // TODO -- implement scanner for player actions
    // TODO -- include more action logic
    // TODO -- include error handling
    public String getPlayerAction(Player player, List<String> availableRoles){
        String playerName = player.getName();
        String locationName = player.getLocation().getName();
        Role playerRole = player.getRole();

        displayMessage("It is " + playerName + "'s turn.");
        displayMessage("You are currently at " + locationName + ".");
        displayStats(player);

        if(locationName.equals("Trailer")){
            displayMessage("Your available actions are: \n" +
                    "1. move\n" +
                    "2. end turn");
        }
        else if(locationName.equals("Casting Office")){
            displayMessage("Your available actions are: \n" +
                    "1. move\n" +
                    "2. upgrade\n" +
                    "3. end turn");
        }
        else if(player.hasRole()){
            displayMessage("You are currently playing the role of " + playerRole + ".");
            if(playerRole.isOnCard()){
                displayMessage("Your available actions are: \n" +
                        "1. rehearse\n" +
                        "2. act\n" +
                        "3. end turn");
            }
            else{
                displayMessage("Your available actions are: \n" +
                        "1. act\n" +
                        "2. end turn");
            }
        }
        else{
            displayMessage("You are not currently playing a role.");
            if((availableRoles.size() == 1) && (availableRoles.contains("available"))){
                for(String role : availableRoles){
                    displayMessage(role);
                    displayMessage("Your available actions are: \n" +
                            "1. move\n" +
                            "2. end turn");
                }
            }
            else {
                displayMessage("The available roles are: ");
                for (String role : availableRoles) {
                    displayMessage(role);
                    displayMessage("Your available actions are: \n" +
                            "1. move\n" +
                            "2. take role\n" +
                            "3. end turn");
                }
            }

        }

        try {
            String choice = scanner.next();

            if(!(choice.equals("move"))
                    || !(choice.equals("take role"))
                    || !(choice.equals("rehearse"))
                    || !(choice.equals("act"))
                    || !(choice.equals("upgrade"))
                    || !(choice.equals("end turn"))){
                displayMessage("Please choose a valid action.");
                getPlayerAction(player, availableRoles);
            }
            else{
                return choice;
            }
        } catch(Exception e){
            displayMessage("Please choose a valid action.");
            getPlayerAction(player, availableRoles);
        }
    }

    public void displayBoard(Board b){
        // display board logic here
    }
    public void displayStats(Player p){
        displayMessage("Your current stats are: \n" +
                "Rank: " + p.getRank() + "\n" +
                "Credits: " + p.getCredits() + "\n" +
                "Dollars: " + p.getDollars() + "\n" +
                "Practice Chips: " + p.getPracticeChips() + "\n");
    }
}
