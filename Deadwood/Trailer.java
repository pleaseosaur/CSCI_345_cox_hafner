import java.util.List;

public class Trailer extends Location{
    public Trailer(String n, Scene s, List<Role> r, List<String> l, Area a, List<Take> takes) {
        super(n, s, r, l, takes, a);
    }

    public boolean actionAllowed(){
        // do logic here
        return false;
    }
}
