import java.security.GeneralSecurityException;

public class Location {
    private String name;
    private Scene scene;
    private List<Location> neighbors;

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
    public List<Location> getNeighbors(){
        return neighbors;
    }
    public void setNeighbors(List<Location> n){
        this.neighbors = n;
    }
}
