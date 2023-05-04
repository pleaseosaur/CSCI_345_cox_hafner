import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Scene> cards;
    private int nextCard;

    public Deck(List<Scene> c){
        setCards(c);
        this.nextCard = 0;
    }

    public void setCards(List<Scene> c){
        this.cards = c;
    }

    public List<Scene> getCards(){
        return cards;
    }
    public static void shuffleDeck(List<Scene> cards) {
        Collections.shuffle(cards);
    }
    //TODO - add functionality for assigning scenes to locations
    public Scene drawScene(){
        if (nextCard < cards.size()) {
            Scene card = cards.get(nextCard);
            nextCard++;
            return card;
        } else {
            throw new IllegalStateException("No more cards in deck");
        }
    }
}
