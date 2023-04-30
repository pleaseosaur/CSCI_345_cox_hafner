// Core game logic


public class Deadwood {

    private UI ui;
    private Player currentPlayer;
    private GameManager manager;


    public Deadwood() {
        this.ui = new UI();
    }

    public void startGame() {
        ui.displayWelcomeMessage();
        ui.getPlayerCount();
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getPlayer() {
        return currentPlayer;
    }



    public void move(Location location) {
        // move logic
    }

    public void upgrade(int rank) {
        // upgrade logic
    }

    public void takeRole(Role role) {
        // role logic
    }

    public void rehearse() {
        // rehearse logic
    }

    public void act() {
        // act logic
    }

    public void endTurn() {
        // end player turn logic
    }

    public void endDay() {
        // end day logic
    }

    public void endGame() {
        // end game logic
    }


}
