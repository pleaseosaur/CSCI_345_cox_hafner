/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 16 May 2023
 * Purpose: GameManager: handles player interface and game tracking
 */

// imports
import java.util.*;

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
        this.players = setup.setPlayers(numPlayers);
        setDays(setup.setDays(numPlayers));
        setCurrentPlayer();
        this.board = Board.getInstance();
        this.dice = new Dice(6);
        resetPlayers();
    }


    // player actions
    public void move(String location) {
            currentPlayer.setLocation(currentPlayer.getLocation().getNeighbor(location));
            currentPlayer.setHasMoved(true);
    }

    public void upgrade(Upgrade upgrade, String currency) {
        int rank = upgrade.getRank();
        int price = upgrade.getPrice();

        if(currency.equals("dollars")) {
            currentPlayer.setDollars(currentPlayer.getDollars() - price);
        } else {
            currentPlayer.setCredits(currentPlayer.getCredits() - price);
        }

        currentPlayer.setRank(rank);
        currentPlayer.setHasUpgraded(true);
    }

    public void takeRole(String r) {
        Set set = (Set) currentPlayer.getLocation();
        List<Role> allRoles = new ArrayList<>(set.getRoles());
        List<Role> onCardRoles = new ArrayList<>(set.getScene().getRoles());

        allRoles.removeAll(onCardRoles);
        allRoles.addAll(onCardRoles);

        for(Role role : allRoles) {
            if(role.getName().equals(r)) {
                if(!role.isTaken()) {
                    if(currentPlayer.getRank() >= role.getRank()) {
                        currentPlayer.setRole(role);
                        currentPlayer.setHasTakenRole(true);
                        role.setTaken(true);
                    } else {
                        System.out.println("\nYou do not have a high enough rank to take this role.");
                    }
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
        if((currentPlayer.getPracticeChips() < (budget-1))){ // if act not guaranteed
            currentPlayer.addPracticeChips();
            currentPlayer.setHasRehearsed(true);
        } else { // if act is guaranteed
            System.out.println("\nYou have enough practice chips to guarantee act. Acting instead.");
            act();
        }
    }

    public boolean act() {
        Set set = (Set) currentPlayer.getLocation(); // get current set
        int budget = set.getScene().getBudget(); // get budget

        int diceResult = dice.rollDie(); // roll die

        System.out.println("\nBudget is "+budget+".");
        System.out.print("You rolled a "+diceResult+". ");

        diceResult += currentPlayer.getPracticeChips(); // add practice chips
        System.out.println("With practice chips, the result is "+diceResult+".");

        boolean isSuccess = false;
        if(diceResult >= budget){ // acting success
            System.out.println("\nYour act was a success!");
            isSuccess = true;

            // TODO -- move to UI for GUI implementation
            int takesNum = set.getCurrentTakeIndex()+1;
            System.out.println((set.getTakes().size()-takesNum)+" takes remaining.");

            set.decrementTakes(); // decrement takes

        } else { // acting failure
            System.out.println("\nYour act was not successful.");
        }
        
        //payout
        actPay(currentPlayer.getRole().isOnCard(), isSuccess);
        currentPlayer.setHasActed(true);
        
        if(set.getScene().isWrapped()){
            wrapScene();
            return true;
        }
        return false;
    }

    // actPay: pays players for acting
    public void actPay(Boolean onCard, Boolean isSuccess){
        if(isSuccess) {
            if(onCard) { // if star
                currentPlayer.addCredits(2); // add 2 credits
            } else { // if extra
                currentPlayer.addDollars(1); // add 1 dollar
                currentPlayer.addCredits(1); // add 1 credit
            }

        } else if(!onCard) { // if unsuccessful and extra
            currentPlayer.addDollars(1); // add 1 dollar
        }
    }

    // wrapScene: wraps scene and pays players
    public void wrapScene() {

        Location location = currentPlayer.getLocation();

        List<Player> allPlayers = new ArrayList<>(); // list of all players
        List<Player> onCardPlayers = new ArrayList<Player>(); // list of on card players
        List<Player> offCardPlayers = new ArrayList<Player>(); // list of off card players
        List<Role> onCardRoles = new ArrayList<Role>(); // list of on card roles

        for(Player player : getPlayers()) { // for each player
            if(player.getLocation().equals(location)) { // if player is on current location
                if(player.getRole()!=null) {
                    if(player.getRole().isOnCard()) {
                        onCardPlayers.add(player); // add to on card players
                        onCardRoles.add(player.getRole()); // add to on card roles
                        allPlayers.add(player); // add to all players
                    } else {
                        offCardPlayers.add(player); // add to off card players
                        allPlayers.add(player); // add to all players
                    }
                }
            }
        }

        if(onCardPlayers.size() > 0) { // if there are on card players
            wrapBonus(onCardPlayers, offCardPlayers, onCardRoles); // roll for wrap bonuses
        }

        for(Player player : allPlayers) { // for each player
            player.setRole(null); // remove role from all players
            player.resetPracticeChips(); // reset practice chips for all players
        }
        // decrement Open Scenes
        board.setOpenScenes(board.getOpenScenes()-1);
        System.out.println(board.getOpenScenes()+" open scenes remaining."); // TODO -- move to UI for GUI implementation
    }

    // wrapBonus: rolls for wrap bonuses if players are on card
    public void wrapBonus(List<Player> onCardPlayers, List<Player> offCardPlayers, List<Role> onCardRoles) {
        Scene scene = ((Set) currentPlayer.getLocation()).getScene();
        List<Integer> results = dice.wrapRoll(scene.getBudget()); // roll number of dice equal to budget
        Map<Role, Integer> distribution = new HashMap<>(); // map of roles to results

        onCardRoles.sort(Comparator.comparing(Role::getRank).reversed()); // sorts roles by rank

        for(Role role : onCardRoles) {
            distribution.put(role, 0); // add roles to distribution map
        }

        Iterator<Integer> resultIterator = results.iterator(); // iterator for results
        Iterator<Role> roleIterator = onCardRoles.iterator(); // iterator for roles

        while(resultIterator.hasNext()) { // while there are more results
            if(!roleIterator.hasNext()) { // if there are no more roles
                roleIterator = onCardRoles.iterator(); // reset role iterator
            }

            Role role = roleIterator.next(); // get next role
            int result = resultIterator.next(); // get next result

            distribution.put(role, distribution.get(role) + result); // add result to role in distribution map
        }

        for(Player player : onCardPlayers) {
            player.addDollars(distribution.get(player.getRole())); // distribute dollars to players
        }

        for(Player player : offCardPlayers) { // for each off card player
            player.addDollars(player.getRole().getRank()); // add dollars = role rank to player
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
    public List<Player> getPlayers() {
        return players;
    }

    // renames player
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
    public void resetPlayers() {
        for (Player player : getPlayers()) {
            player.setLocation(board.getLocation("Trailer")); // set all players to trailer
            player.setHasActed(false); // reset player actions
            player.setHasMoved(false);
            player.setHasRehearsed(false);
            player.setHasTakenRole(false);
            player.setHasUpgraded(false);
            player.setRole(); // remove role from all players
        }
    }

    // resetRoles: resets all off-card roles to be available for next day
    public void resetRoles() {
        // iterate through set locations and reset the default roles
        for(Location location : board.getAllLocations().values()) {
            if(location instanceof Set set) { // if location is a set
                for(Role role : set.getRoles()) {
                    role.setTaken(false);
                }
            }
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

    // endDay: checks if day is over and resets players and roles
    public boolean endDay() {
        if(board.checkEndDay()) {
            if(!checkEndGame()){
                resetPlayers();
                resetRoles();
                board.setOpenScenes(10);
                board.dealCards(); // deal cards
            }
            return true;
        }
        return false;
    }

    public void decrementDay() {
        setDays(getDays() - 1);
    }

    private boolean checkEndGame() {
        return getDays() == 1; // TODO -- decrements before checking -- change in GUI implementation
    }

    // scoreGame: tallies scores and returns list of winners
    public LinkedList<String> scoreGame() {
        // tally scores when endgame is triggered
        LinkedList<String> winners = new LinkedList<>();
        int topScore = Integer.MIN_VALUE;

        for(Player player : players) {
            int score = player.getDollars() + player.getCredits() + (player.getRank()*5);
            System.out.println(player.getName()+" has a score of "+score+".");
            // update top score if higher
            if(score > topScore) {
                winners.clear();
                winners.add(player.getName());
                topScore = score;
            } else if (score == topScore) {
                winners.add(player.getName());
            }
        }

        return winners;
    }

    // getAvailableRoles: makes list of roles available for taking
    public Map<String, String> getAvailableRoles() {

        Location playerLocation = currentPlayer.getLocation();
        Map<String, String> availableRoles = new HashMap<>();


        if(playerLocation.isSet()){ // if player is on a set
            Set set = (Set) playerLocation; // cast player location to set

            if(set.getScene().isWrapped()){ // if scene is wrapped, no roles are available
                availableRoles.put("0", "Scene is wrapped. No roles are available.");
            }

            else { // if scene is not wrapped, add available roles to map
                List<Role> offCardRoles = set.getRoles();
                List<Role> onCardRoles = set.getScene().getRoles();

                for(Role role : offCardRoles) { // add off card roles to map
                    if(!role.isTaken()){ // if role is not taken
                        availableRoles.put(role.getName(), " (rank " + role.getRank() + " - off card)"); // add role to map
                    }
                }

                for(Role role : onCardRoles) { // add on card roles to map
                    if(!role.isTaken()){ // if role is not taken
                        availableRoles.put(role.getName(), " (rank " + role.getRank() + " - on card)"); // add role to map
                    }
                }
            }

        }
        return availableRoles;
    }

    // getAvailableUpgrades: makes list of upgrades available for taking
    public Map<Integer, List<String>> getAvailableUpgrades() {
        Map<Integer, List<String>> availableUpgrades = new HashMap<>();
        int rank = currentPlayer.getRank();

        if(currentPlayer.getLocation() instanceof CastingOffice office) {
            List<Upgrade> upgrades = office.getUpgrades();

            for (Upgrade upgrade : upgrades) {
                if (upgrade.getRank() > rank) {
                    String options = upgrade.getPrice() + " " + upgrade.getCurrency();

                    if(availableUpgrades.containsKey(upgrade.getRank())) {
                        availableUpgrades.get(upgrade.getRank()).add(options);
                    } else {
                        List<String> newUpgrade = new ArrayList<>();
                        newUpgrade.add(options);
                        availableUpgrades.put(upgrade.getRank(), newUpgrade);
                    }
                }
            }
        }
        if(availableUpgrades.size() == 0) {
            availableUpgrades.put(0, List.of("No upgrades available."));
        }
        return availableUpgrades;
    }

    // calls displayBoard method in Board class
    public void displayBoard() {
        board.displayBoard();
    }
}
