/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 10 May 2023
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
        resetTakes();
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
    
        if(currentIndex == takes.size() -1) {
            this.scene.setWrap(true);
        }
    
        currentTake = takes.get(nextIndex);
    } // decrements the number of takes left

    public void resetTakes() {
        currentTake = takes.get(0);
    } // resets the number of takes to the original number of takes

    public void setRoles(List<Role> r){
        this.roles = r;
    }
    public List<Role> getRoles(){
        return this.roles;
    }
}
