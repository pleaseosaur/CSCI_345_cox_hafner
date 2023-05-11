/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 10 May 2023
 * Purpose: Player: Player object to store player data
 */

// imports
import java.util.List;

public class Player {
    // fields
    private String name;
    private int rank;
    private int credits;
    private int dollars;
    private Role role;
    private int practiceChips;
    private Location location;
    private boolean hasMoved;
    private boolean hasUpgraded;
    private boolean hasActed;
    private boolean hasRehearsed;
    private boolean hasTakenRole;

    // constructor
    public Player(String name, int rank, int credits, int dollars){
        setName(name);
        setRank(rank);
        setCredits(credits);
        setDollars(dollars);
        setRole();
        setPracticeChips(0);
        setHasMoved(false);
        setHasUpgraded(false);
        setHasActed(false);
        setHasRehearsed(false);
        setHasTakenRole(false);
    }

    // hasRole: checks to see if player has taken a role
    public boolean hasRole() {
        return this.role != null;
    }

    // getters and setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCredits() {
        return credits;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public int getDollars() {
        return dollars;
    }

    public void setRole() {
        this.role = null;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setPracticeChips() {
        this.practiceChips += 1;
    }

    public void setPracticeChips(int practiceChips) {
        this.practiceChips = practiceChips;
    }

    public int getPracticeChips() {
        return practiceChips;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasUpgraded(boolean hasUpgraded) {
        this.hasUpgraded = hasUpgraded;
    }

    public boolean getHasUpgraded() {
        return hasUpgraded;
    }

    public void setHasActed(boolean hasActed) {
        this.hasActed = hasActed;
    }

    public boolean getHasActed() {
        return hasActed;
    }

    public void setHasRehearsed(boolean hasRehearsed) {
        this.hasRehearsed = hasRehearsed;
    }

    public boolean getHasRehearsed() {
        return hasRehearsed;
    }

    public void setHasTakenRole(boolean b) {
        this.hasTakenRole = b;
    }

    public boolean getHasTakenRole() {
        return hasTakenRole;
    }
}
