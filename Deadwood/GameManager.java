import java.util.List;
// Player interface and game tracking

public class GameManager {
    private Deadwood game;
    private List<Player> players;
    private boolean gameActive; // TODO - move this to Deadwood
    private int days;

    public GameManager(int numPlayers) {
        setGameActive(true);
        setupGame(numPlayers);
    }

    public void setupGame(int numPlayers) {
        SetupGame setup = new SetupGame(numPlayers);
        setPlayers(setup.getPlayers());
        setDays(setup.getDays());
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void runGame() {
        // while loop for playing game
    }

    private void playerTurn(Player player) {
        // turn logic per player
    }

    public void setGameActive(boolean b) {
        this.gameActive = b;
    }

    public boolean gameIsActive() {
        return gameActive;
    }

    public void setDays(int n) {
        this.days = n;
    }

    public int getDays() {
        return this.days;
    }

    private boolean checkEnd() {
        return getDays() == 0;
    }

    private void scoreGame() {
        // tally scores when endgame is triggered
    }
}
