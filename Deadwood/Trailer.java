import java.util.List;

public class Trailer extends Location{
    public Trailer(String name, List<String> neighbors, Area area) {
        super(name, neighbors, area);

    }

    public boolean actionAllowed(){
        // do logic here
        return false;
    }
}
