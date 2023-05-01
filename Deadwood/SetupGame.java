import java.util.List;


public class SetupGame {

    private GameData data;
    private List<Player> players;
    private Board board;
    private Deck deck;

    public SetupGame(int numPlayers) {
        this.data = new GameData();
        setPlayers(data.createPlayers(numPlayers));
        setBoard(data.createBoard());
        setDeck(data.createDeck());
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
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
