
// Player interface and game tracking

public class GameManager {
    private Deadwood game;
    private boolean gameActive;
    private int days;

    public GameManager(int numPlayers) {
        setGameActive(true);
        setupGame(numPlayers);
    }

    public void setupGame(int numPlayers) {
        SetupGame setup = new SetupGame(numPlayers);
        if(numPlayers == 2 || numPlayers == 3) {
            setDays(3);
        } else {
            setDays(4);
        }
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
