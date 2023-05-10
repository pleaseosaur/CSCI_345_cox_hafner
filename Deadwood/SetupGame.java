/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 10 May 2023
 * Purpose: SetupGame: Handles initial setup for game
 */

// imports
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SetupGame {
    // fields
    private GameData data;
    private List<Player> players;
    private int days;
    private Deck deck;

    // constructor
    public SetupGame(int numPlayers) {
        // Exception catch
        try{
            GameData.initializeGameData("xml/board.xml", "xml/cards.xml");
            setPlayers(numPlayers);
            setDays(numPlayers);
            this.deck = Deck.getInstance();
            setupBoard();
        }
        catch (Exception e){
            System.out.println("Error loading Game Data.");
            e.printStackTrace();
        }
    }

    // getters and setters
    public void setPlayers(int numPlayers) {

        List<Player> players = new ArrayList<>();

        int rank = 1;
        int credits = 0;
        int dollars = 0;

        if(numPlayers == 5) {
            credits = 2;
        } else if(numPlayers == 6) {
            credits = 4;
        } else if(numPlayers == 7 || numPlayers == 8) {
            rank = 2;
        }

        for(int i = 1; i <= numPlayers; i++) {
            Player player = new Player("Player " + i, rank, credits, dollars);
            players.add(player);
        }

        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setDays(int numPlayers) {
        if(numPlayers == 2 || numPlayers == 3) {
            this.days = 3;
        } else {
            this.days = 4;
        }
    }

    public int getDays() {
        return days;
    }

    public void setupBoard() {
        Map<String, Location> locations = Board.getInstance().getAllLocations();

        for (Map.Entry<String, Location> entry : locations.entrySet()) {
            if(entry.getKey().equals("Trailer") || entry.getKey().equals("Casting Office")) {
                continue;
            }
            Set location = (Set) entry.getValue();
            location.setScene(deck.drawScene());
        }
    }

}
