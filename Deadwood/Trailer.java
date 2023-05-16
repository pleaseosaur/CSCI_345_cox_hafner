/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 16 May 2023
 * Purpose: Trailer: Location child object representing the trailer
 */

// imports
import java.util.List;

public class Trailer extends Location{

    public Trailer(String name, List<String> neighbors, Area area) {
        super(name, neighbors, area);
    }

    public boolean actionAllowed(){ // --- probably don't need this now that Location is abstract
        // do logic here
        return false;
    }
}
