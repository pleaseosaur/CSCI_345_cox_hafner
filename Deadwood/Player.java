import java.util.List;

public class Player {
    private String name;
    private int rank;
    private int credits;
    private int dollars;
    private Role role;
    private int practiceChips;
    private Location location;

    public Player(String name, int rank, int credits, int dollars){
        setName(name);
        setRank(rank);
        setCredits(credits);
        setDollars(dollars);
        setRole();
        setPracticeChips(0);
    }

    public boolean hasRole() {
        return this.role != null;
    }

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
}
