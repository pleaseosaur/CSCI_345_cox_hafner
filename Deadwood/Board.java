import java.util.List;
import java.util.Map;

public class Board {
    private final String name;
    private Map<String, Location> locations;
    private int openScenes;

    public Board(String n, Map<String, Location> l, int o){
        this.name = n;
        setLocations(l);
        setOpenScenes(o);
    }

    public String getName(){
        return this.name;
    }

    public Location getLocation(String name){
        return getAllLocations().get(name);
    }

    public Map<String, Location> getAllLocations(){
        return this.locations;
    }

    public void setLocations(Map<String, Location> l){
        this.locations = l;
    }

    public int getOpenScenes(){
        return this.openScenes;
    }

    public void setOpenScenes(int o){
        this.openScenes = o;
    }

    public void updatePlayerLocation(Player p, Location l){
        // do move logic
    }

    public boolean checkEndDay(){
        return getOpenScenes() == 1;
    }
}
