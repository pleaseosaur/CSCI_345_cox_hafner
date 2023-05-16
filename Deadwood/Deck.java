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
    private static Deck deck;
    private final List<Scene> cards;
    private int nextCard;

    // constructor
    private Deck(List<Scene> c){
        this.cards = c;
        this.nextCard = 0;
        Collections.shuffle(cards);
    }

    // getters and setters
    public static void initializeDeck(List<Scene> c){
        if(deck != null){
            throw new IllegalStateException("Deck has already been initialized");
        }
        deck = new Deck(c);
    }

    public static Deck getInstance(){
        if(deck == null){
            throw new IllegalStateException("Deck has not been initialized");
        }
        return deck;
    }

    public List<Scene> getCards(){
        return cards;
    }

    // drawScene: draws scene card to be assigned to location
    public Scene drawScene(){
        if (nextCard < cards.size()) {
            System.out.println("Drawing card " + nextCard);
            Scene card = cards.get(nextCard);
            nextCard++;
            System.out.println("There are " + (cards.size() - nextCard) + " cards left in the deck");
            return card;
        } else {
            throw new IllegalStateException("No more cards in deck");
        }
    }
}
