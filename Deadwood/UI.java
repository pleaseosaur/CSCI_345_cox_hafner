import java.sql.SQLOutput;
import java.util.Scanner;

public class UI {

    private Scanner scanner;


    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to Deadwood!"); // basic - add more later if desired
    }

    public int getPlayerCount() {
        System.out.println("Please enter the number of players: ");
        try {
            int numPlayers = scanner.nextInt();
            if(numPlayers < 2 || numPlayers > 8) {
                System.out.println("Sorry, the number of players should be between 2 and 8.");
                return getPlayerCount();
            } else {
                return numPlayers;
            }
        } catch(NumberFormatException e) {
            System.out.println("That doesn't look like any number I've ever seen!");
            return getPlayerCount();
        }
    }
    public void playerAction(){
        //player input here
    }
    public void displayMessage(String message){
        // message logic here
    }
    public void displayBoard(Board b){
        // display board logic here
    }
    public void displayStats(Player p){
        // stat print logic here
    }
}
