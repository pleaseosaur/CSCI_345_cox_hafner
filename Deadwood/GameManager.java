/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 10 May 2023
 * Purpose: GameManager: handles player interface and game tracking
 */

// imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {
    // fields
    private List<Player> players;
    private Player currentPlayer;
    private int days;
    private Board board;

    // constructor
    public GameManager() {

    }

    // setupGame: does game setup based on number of players
    public void setupGame(int numPlayers) {
        SetupGame setup = new SetupGame(numPlayers);
        setPlayers(setup.getPlayers());
        setDays(setup.getDays());
        setCurrentPlayer();
        this.board = Board.getInstance();
        setStartingLocation();
    }

    // player actions
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
                if(currentPlayer.getRank() >= role.getRank()) {
                    currentPlayer.setRole(role);
                    currentPlayer.setHasTakenRole(true);
                } else {
                    System.out.println("\nYou do not have a high enough rank to take this role.");
                }
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
        int currentIndex = getPlayers().indexOf(currentPlayer); // get index of current player
        int nextIndex = (currentIndex + 1) % getPlayers().size(); // get index of next player
        currentPlayer.setHasMoved(false); // reset player actions
        currentPlayer.setHasUpgraded(false);
        currentPlayer.setHasActed(false);
        currentPlayer.setHasRehearsed(false);
        currentPlayer.setHasTakenRole(false);
        setCurrentPlayer(getPlayers().get(nextIndex)); // set next player as current player
    }

    // getters and setters
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void renamePlayer(Player player, String name){
        player.setName(name);
    }

    // setCurrentPlayer: sets current player to first player in list
    public void setCurrentPlayer() {
        this.currentPlayer = getPlayers().get(0);
    }

    // setCurrentPlayer: sets current player to given player
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    // setStartingLocation: sets all players to starting location
    public void setStartingLocation() {
        for (Player player : getPlayers()) {
            player.setLocation(board.getLocation("Trailer")); // set all players to trailer
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

    public boolean endDay() {
        if(board.checkEndDay()) {
            setStartingLocation();
            board.setOpenScenes(10);
            board.dealCards();
            setDays(getDays() - 1); // decrement days
            if(checkEndGame()) {
                scoreGame();
            }
            return true;
        }
        return false;
    }

    private boolean checkEndGame() {
        return getDays() == 0;
    }

    private void scoreGame() {
        // tally scores when endgame is triggered
    }

    // getAvailableRoles: makes list of roles available for taking
    public Map<String, String> getAvailableRoles() {

        Location playerLocation = currentPlayer.getLocation(); // get player location
        Map<String, String> availableRoles = new HashMap<>(); // create map of available roles


        if(playerLocation.isSet()){ // if player is on a set
            Set set = (Set) playerLocation; // cast player location to set

            if(set.getScene().isWrapped()){ // if scene is wrapped, no roles are available
                availableRoles.put("0", "Scene is wrapped. No roles are available.");
            }

            else { // if scene is not wrapped, add available roles to map
                List<Role> offCardRoles = set.getRoles(); // get off card roles
                List<Role> onCardRoles = set.getScene().getRoles(); // get on card roles

                for(Role role : offCardRoles) { // add off card roles to map
                    if(!role.isTaken()){ // if role is not taken
                        availableRoles.put(role.getName(), "(rank " + role.getRank() + " - off card)"); // add role to map
                    }
                }

                for(Role role : onCardRoles) { // add on card roles to map
                    if(!role.isTaken()){ // if role is not taken
                        availableRoles.put(role.getName(), "(rank " + role.getRank() + " - on card)"); // add role to map
                    }
                }
            }

        }
        return availableRoles;
    }

    // getAvailableUpgrades: makes list of upgrades available for taking
    public List<String> getAvailableUpgrades() {
        List<String> availableUpgrades = new ArrayList<>();
        int rank = currentPlayer.getRank();

        if(currentPlayer.getLocation() instanceof CastingOffice office) {

            List<Upgrade> upgrades = office.getUpgrades();
            for (Upgrade upgrade : upgrades) {
                if (upgrade.getRank() > rank) {
                    if((upgrade.getCurrency().equals("dollars")) && (upgrade.getPrice() <= currentPlayer.getDollars())) {
                        availableUpgrades.add(upgrade.getRank() + ": " + upgrade.getPrice() + " " + upgrade.getCurrency());
                    }
                    else if((upgrade.getCurrency().equals("credits")) && (upgrade.getPrice() <= currentPlayer.getCredits())) {
                        availableUpgrades.add(upgrade.getRank() + ": " + upgrade.getPrice() + " " + upgrade.getCurrency());
                    }
                    availableUpgrades.add(upgrade.getRank() + ": " + upgrade.getPrice() + " " + upgrade.getCurrency());
                }
            }
        }
        if(availableUpgrades.size() == 0) {
            availableUpgrades.add("You cannot afford any upgrades at this time.");
        }
        return availableUpgrades;
    }

    public void displayBoard() {
        board.displayBoard();
    }
}
