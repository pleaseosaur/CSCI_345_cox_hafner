// Core game logic


public class Deadwood {

    final UI ui;
    private Player currentPlayer;
    private GameManager manager;


    public Deadwood() {
        this.ui = new UI();
    }

    public void startGame() {
        ui.displayWelcomeMessage();
        int players = ui.getPlayerCount();
        this.manager = new GameManager(players);
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
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

    public void act() {
        // act logic
    }

    public void endTurn() {
        int currentIndex = manager.getPlayers().indexOf(currentPlayer);
        int nextIndex = (currentIndex + 1) % manager.getPlayers().size();
        setCurrentPlayer(manager.getPlayers().get(nextIndex));
    }

    public void endDay() {
        // end day logic
    }

    public void endGame() {
        // end game logic
    }


}
