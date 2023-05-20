package model;/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 16 May 2023
 * Purpose: model.Trailer: model.Location child object representing the trailer
 */

// imports

import java.util.List;

public class Trailer extends Location {

    public Trailer(String name, List<String> neighbors, Area area) {
        super(name, neighbors, area);
    }

}
