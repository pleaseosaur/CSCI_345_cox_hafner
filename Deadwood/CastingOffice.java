import java.util.List;

public class CastingOffice extends Location{
    private final List<Upgrade> upgrades;

    public CastingOffice(String n, Scene s, List<Role> r, List<String> l, List<Take> takes, Area a, List<Upgrade> upgrades) {
        super(n, s, r, l, takes, a);
        this.upgrades = upgrades;
    }

    public boolean actionAllowed(){
        // do logic here
        return false; // dummy return
    }
}
