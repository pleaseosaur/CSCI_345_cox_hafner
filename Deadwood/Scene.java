/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 16 May 2023
 * Purpose: Scene: Scene object
 */

// imports
import java.util.List;

public class Scene {
    // fields
    private String name;
    private int number;
    private String description;
    private int budget;
    private List<Role> roles;
    private boolean wrap;


    // constructor
    public Scene(String n, int num, String d, int b, List<Role> r, boolean w) {
        setName(n);
        setNumber(num);
        setDescription(d);
        setBudget(b);
        setRoles(r);
        setWrap(w);
    }


    // getters and setters
    public void setName(String n){
        this.name = n;
    }
    public String getName(){
        return name;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }

    public void setDescription(String d){
        this.description = d;
    }
    public String getDescription(){
        return description;
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

    public boolean isWrapped(){
        return wrap;
    }
    public void setWrap(boolean w){
        this.wrap = w;
    }
}
