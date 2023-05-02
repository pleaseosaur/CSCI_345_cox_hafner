import java.util.List;

public class CastingOffice extends Location{
    public CastingOffice(String n, Scene s, List<Role> r, List<Location> l, List<Take> takes) {
        super(n, s, r, l, takes);
    }

    public boolean actionAllowed(){
        // do logic here
        return false; // dummy return
    }
}
