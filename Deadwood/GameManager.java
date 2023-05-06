import java.util.List;
// Player interface and game tracking

public class GameManager {

    private List<Player> players;
    private Player currentPlayer;
    private int days;
    private Board board;
    private Deck deck;

    public GameManager() {

    }

    public void setupGame(int numPlayers) {
        SetupGame setup = new SetupGame(numPlayers);
        setPlayers(setup.getPlayers());
        setDays(setup.getDays());
        setCurrentPlayer();
        setBoard(setup.getBoard());
        setDeck(setup.getDeck());
    }

    private void playerTurn(Player player) {
        // turn logic per player
    }

    public void move(Location location) {
        // move logic
    }

    public void upgrade(int rank) {
        currentPlayer.setRank(rank);
    }

    public void takeRole(Role role) {
        currentPlayer.setRole(role);
    }

    public void rehearse() {
        currentPlayer.setPracticeChips();
    }

    public void endTurn() {
        int currentIndex = getPlayers().indexOf(currentPlayer);
        int nextIndex = (currentIndex + 1) % getPlayers().size();
        setCurrentPlayer(getPlayers().get(nextIndex));
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setCurrentPlayer() {
        this.currentPlayer = getPlayers().get(0);
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setDays(int n) {
        this.days = n;
    }

    public int getDays() {
        return this.days;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return this.deck;
    }

    private boolean checkEnd() {
        return getDays() == 0;
    }

    private void scoreGame() {
        // tally scores when endgame is triggered
    }
}
