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
        this.takes = takes;
        this.roles = roles;
        currentTake = this.takes.get(0);
    }


    // getters and setters
    public void setScene(Scene s){
        this.scene = s;
    }
    public Scene getScene(){
        return this.scene;
    }

    public List<Take> getTakes(){
        return this.takes;
    }

    // decrementTakes: decrements the current take and wraps the scene if necessary
    public void decrementTakes() {
        int currentIndex = getCurrentTakeIndex();
        int nextIndex = (currentIndex + 1) % this.takes.size();

        boolean wrap = (currentIndex == takes.size() - 1);

        currentTake = takes.get(nextIndex); // move to the next take
        getScene().setWrap(wrap);
    }

    public List<Role> getRoles(){
        return this.roles;
    }

    public int getCurrentTakeIndex(){
        return takes.indexOf(currentTake);
    }
}
