import java.util.List;

public class CastingOffice extends Location{
    private final List<String> upgrades;

    public CastingOffice(String n, Scene s, List<Role> r, List<String> l, List<Take> takes, List<String> upgrades) {
        super(n, s, r, l, takes);
        this.upgrades = upgrades;
    }

    public boolean actionAllowed(){
        // do logic here
        return false; // dummy return
    }
}
