
// Player interface and game tracking

public class GameManager {
    private SetupGame setup;
    private Deadwood game;
    private boolean gameActive;

    public GameManager(SetupGame setup) {
        this.setup = setup;
        this.game = new Deadwood(this);
        this.gameActive = true;
    }

    public void runGame() {
        // while loop for playing game
    }

    private void playerTurn(Player player) {
        // turn logic per player
    }

    private boolean checkEnd() {
        // check for end of game condition and return t/f
        return false; // dummy return so method will compile
    }

    private void scoreGame() {
        // tally scores when endgame is triggered
    }
}
