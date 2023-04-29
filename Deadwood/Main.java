public class Main {
    public static void main (String[] args) {
        Deadwood game = new Deadwood();
        UI ui = new UI(game);
        ui.run();
    }
}
