/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 16 May 2023
 * Purpose: Set: Set object
 */

// imports
import java.util.List;

public class Set extends Location{
    // fields
    private Scene scene;
    private List<Take> takes;
    private List<Role> roles;
    private Take currentTake;

    // constructor
    public Set(String name, List<String> neighbors, Area area, Scene scene, List<Take> takes, List<Role> roles) {
        super(name, neighbors, area);
        setScene(scene);
        setTakes(takes);
        setRoles(roles);
        currentTake = this.takes.get(0);
    }


    // getters and setters
    public void setScene(Scene s){
        this.scene = s;
    }
    public Scene getScene(){
        return this.scene;
    }

    public void setTakes(List<Take> t){
        this.takes = t;
    } // sets up the take objects
    public List<Take> getTakes(){
        return this.takes;
    }

    public void decrementTakes() {
        int currentIndex = this.takes.indexOf(currentTake);
        int nextIndex = (currentIndex + 1) % this.takes.size();

        boolean wrap = (currentIndex == takes.size() - 1);

        currentTake = takes.get(nextIndex); // move to the next take
        getScene().setWrap(wrap);
    }


    public void setRoles(List<Role> r){
        this.roles = r;
    }
    public List<Role> getRoles(){
        return this.roles;
    }

    // used for debugging
    public int getCurrentTakeIndex(){
        return takes.indexOf(currentTake);
    }
}
