package model;/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 16 May 2023
 * Purpose: model.Scene: model.Scene object
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
        this.name = n;
        this.number = num;
        this.description = d;
        this.budget = b;
        this.roles = r;
        setWrap(w);
    }


    // getters and setters
    public int getBudget(){
        return budget;
    }

    public List<Role> getRoles(){
        return roles;
    }

    public boolean isWrapped(){
        return wrap;
    }
    public void setWrap(boolean w){
        this.wrap = w;
    }
}
