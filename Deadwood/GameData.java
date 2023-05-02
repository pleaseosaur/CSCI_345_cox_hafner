import java.io.File;
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
        //empty constructor
    }


    public Board createBoard()
    throws ParserConfigurationException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = null;

        try {
            doc = db.parse("board.xml");



        } catch(Exception e) {
            System.out.println("XML parse failure");
            e.printStackTrace();
        }
        return doc;
    }

    public Deck createDeck() {
        // xml parser for scene data to create a deck of scenes
        // data = "dummy data"; //dummy data so return will compile
        // return Deck(data); //dummy return so method compiles
        return null; //dummy return
    }
}
