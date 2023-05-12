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
    private Dice dice;

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
        this.dice = new Dice(6);
        setStartingLocation();
    }

    // player actions
    public void move(String location) {
        if(location.equals("stats") || location.equals("view") || location.equals("board")){
            System.out.println("You can't do that now. Try again."); // TODO probably a better way to do this, but this prevents crash
        } else if (location.equals("back")) {
            System.out.println("Going back."); // TODO same as above, workaround but probably not best way to fix
        } else{
            currentPlayer.setLocation(currentPlayer.getLocation().getNeighbor(location));
            currentPlayer.setHasMoved(true);
        }
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
        // ensure can't rehearse if act is guaranteed
        int budget = Integer.MAX_VALUE;
        if(currentPlayer.getLocation() instanceof Set set){
            budget = set.getScene().getBudget();
        }
        if(!(currentPlayer.getPracticeChips() >= budget)){ // if act not guaranteed
            currentPlayer.setPracticeChips();
            currentPlayer.setHasRehearsed(true);
        } else { // if act is guaranteed
            System.out.println("\nYou have enough practice chips to guarantee act. Acting instead.");
            act();
        }
    }

    public void act() {
        // TODO - implement act
        Set set = (Set) currentPlayer.getLocation();
        int budget = set.getScene().getBudget();
        int diceResult = dice.rollDie();
        System.out.println("\nBudget is "+budget+".");
        System.out.print("You rolled a "+diceResult+". ");
        if(currentPlayer.getPracticeChips() != 0){
            diceResult += currentPlayer.getPracticeChips();
            System.out.println("With practice chips, the result is "+diceResult+".");
        }

        boolean isSuccess = false;
        if(diceResult >= budget){ // acting success
            System.out.println("\nYour act was a success!");
            isSuccess = true;
        } else { // acting failure
            System.out.println("\nYour act was not successful.");
        }
        
        //payout
        if(currentPlayer.getRole().isOnCard()){
            actPay(true, isSuccess);
        } else {
            actPay(false, isSuccess);
        }
        currentPlayer.setHasActed(true);
    }

    public void actPay(Boolean onCard, Boolean isSuccess){
        if(onCard) { // if star
            if(isSuccess) {
                // do ON CARD SUCCESS stuff
                //remove a shot counter
                //take two credits
                currentPlayer.setCredits(currentPlayer.getCredits()+2);
            } else {
                // do ON CARD FAILURE stuff
                // do NOTHING
            }
        } else { // if extra
            if(isSuccess) {
                // do OFF CARD SUCCESS stuff
                //remove a shot counter
                //take one dollar
                currentPlayer.setDollars(currentPlayer.getDollars()+1);
                //take one credit
                currentPlayer.setCredits(currentPlayer.getCredits()+1);
            } else {
                // do OFF CARD FAILURE stuff
                //take one dollar
                currentPlayer.setDollars(currentPlayer.getDollars()+1);
            }
        }
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
