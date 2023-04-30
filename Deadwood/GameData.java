import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;


public class GameData {

    public GameData() {
        // parse xml
    }

    public List<Player> createPlayers(int numPlayers) {
        List<Player> players = new ArrayList<>();
        if(numPlayers == 5) {
            for(int i = 1; i <= numPlayers; i++) {
                Player player = new Player("Player " + i, 1, 2, 0);
                players.add(player);
            }
        }
        if(numPlayers == 6) {
            for(int i = 1; i <= numPlayers; i++) {
                Player player = new Player("Player " + i, 1, 4, 0);
                players.add(player);
            }
        }
        if(numPlayers == 7 || numPlayers == 8) {
            for(int i = 1; i <= numPlayers; i++) {
                Player player = new Player("Player " + i, 2, 0, 0);
                players.add(player);
            }
        } else {
            for (int i = 1; i <= numPlayers; i++) {
                Player player = new Player("Player " + i, 1, 0, 0);
                players.add(player);
            }
        }
        return players;
    }

    public Board createBoard() {
        // xml parser for board data
        // data = "dummy data"; //dummy data so return will compile
        // return Board(data); //dummy return so method compiles
        return null; //dummy return
    }

    public Deck createDeck() {
        // xml parser for scene data to create a deck of scenes
        // data = "dummy data"; //dummy data so return will compile
        // return Deck(data); //dummy return so method compiles
        return null; //dummy return
    }
}
