import java.util.List;

public abstract class Location {
    private String name;
    private List<String> temp; // temporary list of neighbors
    private List<Location> neighbors; // list of neighbors as Location objects
    private Area area;

    public Location(String name, List<String> temp, Area area){
        setName(name);
        setTemp(temp);
        setArea(area);
    }

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

    public void setArea(Area a){
        this.area = a;
    }

    public Area getArea(){
        return this.area;
    }
}
