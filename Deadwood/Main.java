import java.util.Set;

public class Main {
    public static void main (String[] args) {
        GameManager manager = new GameManager();
        Deadwood game = new Deadwood(manager);
        game.startGame();
    }
}
