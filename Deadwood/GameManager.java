import java.util.ArrayList;
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
        setStartingLocation();
    }

    private void playerTurn(Player player) {
        // TODO - implement turn logic -- may need to be in Deadwood
    }

    public void move(String location) {
        currentPlayer.setLocation(currentPlayer.getLocation().getNeighbor(location));
        currentPlayer.setHasMoved(true);
    }

    public void upgrade(int rank) {
        currentPlayer.setRank(rank);
        currentPlayer.setHasUpgraded(true);
    }

    public void takeRole(String r) {
        Set set = (Set) currentPlayer.getLocation();
        List<Role> allRoles = set.getRoles();
        allRoles.addAll(set.getScene().getRoles());
        for (Role role : allRoles) {
            if (role.getName().equals(r)) {
                currentPlayer.setRole(role);
            }
        }
    }

    public void rehearse() {
        currentPlayer.setPracticeChips();
        currentPlayer.setHasRehearsed(true);
    }

    public void act() {
        // TODO - implement act
        currentPlayer.setHasActed(true);
    }

    public void endTurn() {
        int currentIndex = getPlayers().indexOf(currentPlayer);
        int nextIndex = (currentIndex + 1) % getPlayers().size();
        currentPlayer.setHasMoved(false);
        currentPlayer.setHasUpgraded(false);
        currentPlayer.setHasActed(false);
        currentPlayer.setHasRehearsed(false);
        setCurrentPlayer(getPlayers().get(nextIndex));
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void renamePlayer(Player player, String name){
        player.setName(name);
    }

    public void setCurrentPlayer() {
        this.currentPlayer = getPlayers().get(0);
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void setStartingLocation() {
        for (Player player : getPlayers()) {
            player.setLocation(board.getLocation("Trailer"));
        }
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
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

    public boolean endDay() {
        return board.checkEndDay();
    }

    private boolean checkEndGame() {
        return getDays() == 0;
    }

    private void scoreGame() {
        // tally scores when endgame is triggered
    }

    public List<String> getAvailableRoles() {

        Location playerLocation = currentPlayer.getLocation();
        List<String> availableRoles = new ArrayList<>();

        if(playerLocation.isSet()){
            Set set = (Set) playerLocation;

            if(set.getScene().isWrapped()){
                availableRoles.add("Scene is wrapped. No roles are available.");
            }
            else {
                List<Role> offCardRoles = set.getRoles();
                List<Role> onCardRoles = set.getScene().getRoles();
                for(Role role : offCardRoles) {
                    if(!(role.isTaken())){
                        availableRoles.add(role.getName() + " (rank " + role.getRank() + " - off card)");
                    }
                }
                for(Role role : onCardRoles) {
                    if(!(role.isTaken())){
                        availableRoles.add(role.getName() + " (rank " + role.getRank() + " - on card)");
                    }
                }
            }

        }
        return availableRoles;
    }

    public List<String> getAvailableUpgrades() {
        List<String> availableUpgrades = new ArrayList<>();
        int rank = currentPlayer.getRank();

        if(currentPlayer.getLocation() instanceof CastingOffice office) {

            List<Upgrade> upgrades = office.getUpgrades();
            for (Upgrade upgrade : upgrades) {
                if (upgrade.getRank() > rank) {
                    if((upgrade.getCurrency().equals("dollars")) && (upgrade.getPrice() <= currentPlayer.getDollars())) {
                        availableUpgrades.add(String.valueOf(upgrade.getRank()) + ": " + String.valueOf(upgrade.getPrice()) + " " + upgrade.getCurrency());
                    }
                    else if((upgrade.getCurrency().equals("credits")) && (upgrade.getPrice() <= currentPlayer.getCredits())) {
                        availableUpgrades.add(String.valueOf(upgrade.getRank()) + ": " + String.valueOf(upgrade.getPrice()) + " " + upgrade.getCurrency());
                    }
                    availableUpgrades.add(String.valueOf(upgrade.getRank()) + ": " + String.valueOf(upgrade.getPrice()) + " " + upgrade.getCurrency());
                }
            }
        }
        if(availableUpgrades.size() == 0) {
            availableUpgrades.add("You cannot afford any upgrades at this time.");
        }
        return availableUpgrades;
    }
}
