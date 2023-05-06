// Core game logic


public class Deadwood {

    final UI ui;
    private final GameManager manager;
    private boolean gameActive;


    public Deadwood(GameManager manager) {
        this.manager = manager;
        this.ui = new UI();
    }

    public void startGame() {
        ui.displayWelcomeMessage();
        int players = ui.getPlayerCount();
        manager.setupGame(players);
        setGameActive(true);
        runGame();
    }

    public void setGameActive(boolean b) {
        this.gameActive = b;
    }

    public boolean getGameActive() {
        return this.gameActive;
    }

    public void runGame() {
        // game loop
    }

    public void move(Location location) {
        // move logic
    }

    public void upgrade(int rank) {
        manager.upgrade(rank);
    }

    public void takeRole(Role role) {
        manager.takeRole(role);
    }

    public void rehearse() {
        manager.rehearse();
    }

    public void act() {
        // act logic
    }

    public void endTurn() {
        manager.endTurn();
    }

    public void endDay() {
        // end day logic
    }

    public void endGame() {
        // end game logic
    }


}
