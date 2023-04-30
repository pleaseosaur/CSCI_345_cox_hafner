import java.util.List;

public class Player {
    private String name;
    private int rank;
    private int credits;
    private int dollars;
    private Role role;
    private int practiceChips;
    private Location location;

    public Player(String name){
        this.name = name;
        this.rank = 1;
        this.credits = 0;
        this.dollars = 0;
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
}
