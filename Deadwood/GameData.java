import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class GameData {

    private Board board;
    private Deck deck;

    public GameData(String boardFile, String cardFile) throws ParserConfigurationException{
        Document boardDoc = getDocFromFile(boardFile);
        Document cardDoc = getDocFromFile(cardFile);

        createBoard(boardDoc);
        createDeck(cardDoc);

    }


    public Document getDocFromFile(String filename) throws ParserConfigurationException {
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = null;

            try {
                doc = db.parse(filename);
            } catch (Exception ex) {
                System.out.println("XML parse failure");
                ex.printStackTrace();
            }

            return doc;
        } // exception handling
    }

    public void createBoard(Document d) {
        d.getDocumentElement().normalize();

        String boardName = d.getDocumentElement().getAttribute("name");

        NodeList locationNodes = d.getElementsByTagName("set");

        List<Location> locations = new ArrayList<>();

        for(int i = 0; i < locationNodes.getLength(); i++) {
            Node locationNode = locationNodes.item(i);

            if(locationNode.getNodeType() == Node.ELEMENT_NODE) {
                Element locationElement = (Element) locationNode;

                String locationName = locationElement.getAttribute("name");
                NodeList neighborNodes = locationElement.getElementsByTagName("neighbor");
                List<String> neighbors = new ArrayList<>();
                for(int j = 0; j < neighborNodes.getLength(); j++) {
                    Node neighborNode = neighborNodes.item(j);
                    if(neighborNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element neighborElement = (Element) neighborNode;
                        neighbors.add(neighborElement.getAttribute("name"));
                    }
                }

                NodeList takeNodes = locationElement.getElementsByTagName("take");
                List<Take> takes = new ArrayList<>();
                for(int j = 0; j < takeNodes.getLength(); j++) {
                    Node takeNode = takeNodes.item(j);
                    if(takeNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element takeElement = (Element) takeNode;
                        int takeNumber = Integer.parseInt(takeElement.getAttribute("number"));
                        int takeX = Integer.parseInt(takeElement.getAttribute("x"));
                        int takeY = Integer.parseInt(takeElement.getAttribute("y"));
                        int takeH = Integer.parseInt(takeElement.getAttribute("h"));
                        int takeW = Integer.parseInt(takeElement.getAttribute("w"));
                        Take take = new Take(takeNumber, takeX, takeY, takeH, takeW);
                        takes.add(take);
                    }
                }

                NodeList partNodes = locationElement.getElementsByTagName("part");
                List<Role> roles = new ArrayList<>();
                for(int j = 0; j < partNodes.getLength(); j++) {
                    Node partNode = partNodes.item(j);
                    if(partNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element partElement = (Element) partNode;
                        String partName = partElement.getAttribute("name");
                        int partLevel = Integer.parseInt(partElement.getAttribute("level"));
                        int partX = Integer.parseInt(partElement.getAttribute("x"));
                        int partY = Integer.parseInt(partElement.getAttribute("y"));
                        int partH = Integer.parseInt(partElement.getAttribute("h"));
                        int partW = Integer.parseInt(partElement.getAttribute("w"));
                        String partLine = partElement.getAttribute("line");
                        Role role = new Role(partName, partLevel, partX, partY, partH, partW, partLine, false, false);
                        roles.add(role);
                    }
                }
                // TODO - Add functionality for getting Trailer and Casting Office
                Location location = new Location(locationName, null, roles, neighbors, takes);
                locations.add(location);
            }
        }

        // get trailer nodes
        NodeList trailerNodes = d.getElementsByTagName("trailer");
        Node trailerNode = trailerNodes.item(0);
        Element trailerElement = (Element) trailerNode;
        String trailerName = trailerElement.getAttribute("name");
        NodeList trailerNeighborNodes = trailerElement.getElementsByTagName("neighbor");
        List<String> trailerNeighbors = new ArrayList<>();
        for(int j = 0; j < trailerNeighborNodes.getLength(); j++) {
            Node trailerNeighborNode = trailerNeighborNodes.item(j);
            if(trailerNeighborNode.getNodeType() == Node.ELEMENT_NODE) {
                Element trailerNeighborElement = (Element) trailerNeighborNode;
                trailerNeighbors.add(trailerNeighborElement.getAttribute("name"));
            }

            Location trailer = new Location(trailerName, null, null, trailerNeighbors, null);
            locations.add(trailer);
        }

        // get office nodes
        NodeList officeNodes = d.getElementsByTagName("office");
        Node officeNode = officeNodes.item(0);
        Element officeElement = (Element) officeNode;
        String officeName = officeElement.getAttribute("name");
        NodeList officeNeighborNodes = officeElement.getElementsByTagName("neighbor");
        List<String> officeNeighbors = new ArrayList<>();
        for(int j = 0; j < officeNeighborNodes.getLength(); j++) {
            Node officeNeighborNode = officeNeighborNodes.item(j);
            if(officeNeighborNode.getNodeType() == Node.ELEMENT_NODE) {
                Element officeNeighborElement = (Element) officeNeighborNode;
                officeNeighbors.add(officeNeighborElement.getAttribute("name"));
            }

            Location office = new Location(officeName, null, null, officeNeighbors, null);
            locations.add(office);
        }

        this.board = new Board(boardName, locations, 10);
    }

    public Board getBoard() {
        return board;
    }

    public Deck createDeck(Document d) {


        return null;
    }

    public Deck getDeck() {
        return deck;
    }

}