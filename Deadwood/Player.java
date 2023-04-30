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
        this.name = name;
        this.rank = rank;
        this.credits = credits;
        this.dollars = dollars;
        this.role = null;
        this.practiceChips = 0;
        this.location = null;
    }

    public void move(){
        // call method from Deadwood.java to do logic
    }
    public void upgrade(){
        // call method from Deadwood.java to do logic
    }
    public void takeRole(){
        // call method from Deadwood.java to do logic
    }
    public void act(){
        // call method from Deadwood.java to do logic
    }
    public void rehearse(){
        // call method from Deadwood.java to do logic
    }

    public boolean hasRole() {
        return this.role != null;
    }
}
