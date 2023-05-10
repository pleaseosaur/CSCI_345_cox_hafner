/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 10 May 2023
 * Purpose: CastingOffice: Location child object representing the casting office
 */

// imports
import java.util.List;

public class CastingOffice extends Location{
    // fields
    private List<Upgrade> upgrades;

    // constructor
    public CastingOffice(String name, List<String> neighbors, Area area, List<Upgrade> upgrades) {
        super(name, neighbors, area);
        setUpgrades(upgrades);
    }

    // getters and setters
    public void setUpgrades(List<Upgrade> u){
        this.upgrades = u;
    }

    public List<Upgrade> getUpgrades(){
        return this.upgrades;
    }

    // TODO maybe remove this
    public boolean actionAllowed(){
        // do logic here
        return false; // dummy return
    }
}
