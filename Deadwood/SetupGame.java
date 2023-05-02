import java.util.ArrayList;
import java.util.List;


public class SetupGame {

    private GameData data;
    private List<Player> players;
    private int days;
    private Board board;
    private Deck deck;

    public SetupGame(int numPlayers) {
        this.data = new GameData();
        setPlayers(numPlayers);
        setDays(numPlayers);
        setBoard(data.createBoard());
        setDeck(data.createDeck());
    }

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

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }
}
