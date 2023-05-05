import java.util.List;

public class Set extends Location{

    private Scene scene;
    private List<Take> takes;
    private List<Role> roles;

    public Set(String name, List<String> neighbors, Area area, Scene scene, List<Take> takes, List<Role> roles) {
        super(name, neighbors, area);
        setScene(scene);
        setTakes(takes);
        setRoles(roles);
    }

    public void setScene(Scene s){
        this.scene = s;
    }

    public Scene getScene(){
        return this.scene;
    }

    public void setTakes(List<Take> t){
        this.takes = t;
    }

    public List<Take> getTakes(){
        return this.takes;
    }

    public void setRoles(List<Role> r){
        this.roles = r;
    }

    public List<Role> getRoles(){
        return this.roles;
    }


}
