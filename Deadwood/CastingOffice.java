import java.util.List;

public class CastingOffice extends Location{
    private List<Upgrade> upgrades;

    public CastingOffice(String name, List<String> neighbors, Area area, List<Upgrade> upgrades) {
        super(name, neighbors, area);
        setUpgrades(upgrades);
    }

    public void setUpgrades(List<Upgrade> u){
        this.upgrades = u;
    }

    public List<Upgrade> getUpgrades(){
        return this.upgrades;
    }

    public boolean actionAllowed(){
        // do logic here
        return false; // dummy return
    }
}
