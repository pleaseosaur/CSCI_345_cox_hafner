import java.util.Scanner;

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


    public void playerAction(){
        //player input here
    }

    public void displayBoard(Board b){
        // display board logic here
    }
    public void displayStats(Player p){
        // stat print logic here
    }
}
