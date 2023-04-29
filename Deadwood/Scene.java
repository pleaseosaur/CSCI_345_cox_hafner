import java.util.List;

public class Scene {
    private String name;
    private String description;
    private int budget;
    private List<Role> roles;
    private boolean wrap;

    public String getName(){
        return name;
    }
    public void setName(String n){
        this.name = n;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String d){
        this.description = d;
    }
    public int getBudget(){
        return budget;
    }
    public void setBudget(int b){
        this.budget = b;
    }
    public List<Role> getRoles(){
        return roles;
    }
    public void setRoles(List<Role> r){
        this.roles = r;
    }
    public boolean isWrap(){
        return wrap;
    }
    public boolean setWrap(boolean w){
        return w;
    }
}
