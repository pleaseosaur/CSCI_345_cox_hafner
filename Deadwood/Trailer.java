import java.util.List;

public class Trailer extends Location{
    public Trailer(String n, Scene s, List<Role> r, List<Location> l, List<Take> takes) {
        super(n, s, r, l, takes);
    }

    public boolean actionAllowed(){
        // do logic here
        return false;
    }
}
