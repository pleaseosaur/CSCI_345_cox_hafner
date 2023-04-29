import java.util.Set;

public class Main {
    public static void main (String[] args) {
        GameData data = new GameData();
        SetupGame setup = new SetupGame(Integer.parseInt(args[0]), data);
        GameManager manager = new GameManager(setup);

    }
}
