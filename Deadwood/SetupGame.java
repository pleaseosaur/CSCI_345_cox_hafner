import java.util.List;


public class SetupGame {

    private GameData data;
    private List<Player> players;
    private Board board;
    private Deck deck;

    public SetupGame(int numPlayers, GameData data) {
        this.data = data;
        this.players = data.createPlayers(numPlayers);
        this.board = data.createBoard();
        this.deck = data.createDeck();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public Deck getDeck() {
        return deck;
    }
}
