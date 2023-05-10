/*
 * Author: Peter Hafner and Andrew Cox
 * Date: 10 May 2023
 * Purpose: Deck: Deck object to hold scene cards
 */

// imports
import java.util.Collections;
import java.util.List;

public class Deck {
    // fields
    private List<Scene> cards;
    private int nextCard;

    // constructor
    public Deck(List<Scene> c){
        setCards(c);
        this.nextCard = 0;
    }

    // getters and setters
    public void setCards(List<Scene> c){
        this.cards = c;
    }

    public List<Scene> getCards(){
        return cards;
    }

    // shuffleDeck: randomizes card order
    public static void shuffleDeck(List<Scene> cards) {
        Collections.shuffle(cards);
    }

    // drawScene: draws scene card to be assigned to location
    //TODO - add functionality for assigning scenes to location
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
