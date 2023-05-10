/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 10 May 2023
 * Purpose: Location object containing data for each location
 */

// imports
import java.util.List;

public abstract class Location {
    // fields
    private String name;
    private List<String> temp; // temporary list of neighbors
    private List<Location> neighbors; // list of neighbors as Location objects
    private Area area;
    private boolean set;

    // constructor
    public Location(String name, List<String> temp, Area area){
        setName(name);
        setTemp(temp);
        setArea(area);
    }

    // getters and setters
    public String getName(){
        return this.name;
    }

    public void setName(String n){
        this.name = n;
    }

    public List<String> getTemp(){
        return temp;
    }

    public void setTemp(List<String> n){
        this.temp = n;
    }

    public List<Location> getNeighbors(){
        return this.neighbors;
    }

    public void setNeighbors(List<Location> n){
        this.neighbors = n;
    }

    public Location getNeighbor(String name){
        for (Location neighbor : getNeighbors()) {
            if (neighbor.getName().equals(name)) {
                return neighbor;
            }
        }
        return null;
    }

    public void setArea(Area a){
        this.area = a;
    }

    public Area getArea(){
        return this.area;
    }

    public boolean isSet() {
        return !getName().equals("Trailer") && !getName().equals("Casting Office");
    }
}
