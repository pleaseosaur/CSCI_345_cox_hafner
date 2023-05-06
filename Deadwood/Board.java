import java.util.List;
import java.util.Map;

public class Board {
    private final String name;
    private Map<String, Location> locations;
    private int openScenes;

    public Board(String n, Map<String, Location> l, int o){
        this.name = n;
        this.locations = l;
        this.openScenes = o;
    }

    public Map<String, Location> getLocations(){
        return this.locations;
    }
    public void setLocations(Map<String, Location> l){
        this.locations = l;
    }
    public int getOpenScenes(){
        return this.openScenes;
    }
    public void setOpenScenes(int o){
        this.openScenes = 0;
    }

    public void updatePlayerLocation(Player p, Location l){
        // do move logic
    }
    public boolean endDay(){
        return getOpenScenes() == 1;
    }
}
