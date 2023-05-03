import java.util.List;

public class Board {
    private String name;
    private List<Location> locations;
    private int openScenes;

    public Board(String n, List<Location> l, int o){
        this.name = n;
        this.locations = l;
        this.openScenes = o;
    }

    public List<Location> getLocations(){
        return this.locations;
    }
    public void setLocations(List<Location> l){
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
        // end day logic
        return false; // dummy return
    }
}
