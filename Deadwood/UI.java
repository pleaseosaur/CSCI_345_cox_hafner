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
        displayMessage("You may enter 'exit' at any time to terminate the game.");
    }

    public int getPlayerCount() {
        displayMessage("Please enter the number of players: ");
        try {
            if(scanner.next().equals("exit")) {
                displayMessage("Your loss! Enjoy not being a world-renown thespian.");
                System.exit(0);
            }
            int numPlayers = scanner.nextInt();
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
