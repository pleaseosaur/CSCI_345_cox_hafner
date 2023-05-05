import java.util.List;

public abstract class Location {
    private String name;
    private List<String> neighbors; // may need to use HashSet<> for undirected graph functionality
    private Area area;

    public Location(String name, List<String> neighbors, Area area){
        setName(name);
        setNeighbors(neighbors);
        setArea(area);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String n){
        this.name = n;
    }

    public List<String> getNeighbors(){
        return neighbors;
    }

    public void setNeighbors(List<String> n){
        this.neighbors = n;
    }

    public void setArea(Area a){
        this.area = a;
    }

    public Area getArea(){
        return this.area;
    }
}
