import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Scene> cards;

    public void setCards(List<Scene> c){
        this.cards = c;
    }
    public static void shuffleDeck(List<Scene> cards) {
        Collections.shuffle(cards);
    }
    public Scene drawScene(){
        //draw scene logic
        return null; //dummy return
    }
}
