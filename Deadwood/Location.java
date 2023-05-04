import java.util.List;

public class Location {
    private String name;
    private Scene scene;
    private List<Role> roles;
    private List<String> neighbors; // may need to use HashSet<> for undirected graph functionality
    private List<Take> takes;
    private Area area;

    public Location(String n, Scene s, List<Role> r, List<String> l, List<Take> takes, Area area){
        setName(n);
        setScene(s);
        setRoles(r);
        setNeighbors(l);
        setTakes(takes);
        setArea(area);
    }

    public String getName(){
        return this.name;
    }
    public void setName(String n){
        this.name = n;
    }
    public Scene getScene(){
        return this.scene;
    }
    public void setScene(Scene s){
        this.scene = s;
    }
    public List<String> getNeighbors(){
        return neighbors;
    }
    public void setNeighbors(List<String> n){
        this.neighbors = n;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Take> getTakes() {
        return takes;
    }

    public void setTakes(List<Take> takes) {
        this.takes = takes;
    }

    public void setArea(Area a){
        this.area = a;
    }

    public Area getArea(){
        return this.area;
    }
}
