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
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // create document builder factory
            DocumentBuilder db = dbf.newDocumentBuilder(); // create document builder
            Document doc = null; // create document

            try {
                doc = db.parse(filename); // parse file into document
            } catch (Exception ex) {
                System.out.println("XML parse failure");
                ex.printStackTrace();
            } // exception handling

            return doc; // return document
        } // exception handling
    }

    public void createBoard(Document d) {
        d.getDocumentElement().normalize(); // normalize document

        String boardName = d.getDocumentElement().getAttribute("name"); // get board name
        NodeList locationNodes = d.getElementsByTagName("set"); // get location nodes
        List<Location> locations = new ArrayList<>(); // create list of locations

        for(int i = 0; i < locationNodes.getLength(); i++) {
            Node locationNode = locationNodes.item(i); // get location node

            if (locationNode.getNodeType() == Node.ELEMENT_NODE) {
                Element locationElement = (Element) locationNode; // cast node to element
                Location location = createLocation(locationElement); // create location
                locations.add(location); // add location to list
            }
        }
        // TODO - could probably refactor further to allow createLocation to handle trailer and office
        Location trailer = createTrailer((Element) d.getElementsByTagName("trailer").item(0)); // get trailer location
        Location office = createOffice((Element) d.getElementsByTagName("office").item(0)); // get office location

        locations.add(trailer); // add trailer to list
        locations.add(office); // add office to list

        this.board = new Board(boardName, locations, 10); // create board
    }

    public Board getBoard() {
        return board;
    }

    public void createDeck(Document d) {
        d.getDocumentElement().normalize(); // normalize document

        NodeList cardNodes = d.getElementsByTagName("card"); // get card nodes

        List<Scene> cards = new ArrayList<>(); // create list of cards

        for(int i = 0; i < cardNodes.getLength(); i++) {
            Node cardNode = cardNodes.item(i); // get card node

            if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
                Element cardElement = (Element) cardNode; // cast node to element
                Scene card = createCard(cardElement); // create card

                cards.add(card); // add card to list
            }
        }

        this.deck = new Deck(cards); // create deck
    }

    private Scene createCard(Element cardElement) {
        String cardName = cardElement.getAttribute("name"); // get card name
        int sceneNumber = Integer.parseInt(cardElement.getAttribute("number")); // get scene number
        int budget = Integer.parseInt(cardElement.getAttribute("budget")); // get card budget
        String sceneDescription = cardElement.getElementsByTagName("scene").item(0).getTextContent(); // get scene description
        List<Role> roles = createRoles(cardElement.getElementsByTagName("part")); // get part/role nodes

        return new Scene(cardName, sceneNumber, sceneDescription, budget, roles, false); // create card;
    }

    public Deck getDeck() {
        return deck;
    }

    private Location createLocation(Element locationElement) {

        String locationName = locationElement.getAttribute("name"); // get location name
        List<String> neighbors = createNeighbors(locationElement.getElementsByTagName("neighbor")); // get neighbor nodes
        Area locationArea = getArea((Element) locationElement.getElementsByTagName("area").item(0)); // get location area
        List<Take> takes = createTakes(locationElement.getElementsByTagName("take")); // get take nodes
        List<Role> roles = createRoles(locationElement.getElementsByTagName("part")); // get part/role nodes

        return new Location(locationName, null, roles, neighbors, takes, locationArea); // create location
    }

    private List<String> createNeighbors(NodeList neighborNodes) {

        List<String> neighbors = new ArrayList<>(); // create list of neighbors

        // iterate through neighbor nodes
        for (int j = 0; j < neighborNodes.getLength(); j++) {
            Node neighborNode = neighborNodes.item(j); // get neighbor node

            if (neighborNode.getNodeType() == Node.ELEMENT_NODE) {
                Element neighborElement = (Element) neighborNode; // cast node to element
                neighbors.add(neighborElement.getAttribute("name")); // add neighbor name to list
            }
        }
        return neighbors;
    }

    private List<Take> createTakes(NodeList takeNodes) {

        List<Take> takes = new ArrayList<>(); // create list of takes

        // iterate through take nodes
        for (int j = 0; j < takeNodes.getLength(); j++) {
            Node takeNode = takeNodes.item(j); // get take node

            if (takeNode.getNodeType() == Node.ELEMENT_NODE) {
                Element takeElement = (Element) takeNode; // cast node to element
                Take take = createTake(takeElement); // create take
                takes.add(take); // add take to list
            }
        }
        return takes;
    }

    private Take createTake(Element takeElement) {

        int takeNumber = Integer.parseInt(takeElement.getAttribute("number")); // get take number
        Area takeArea = getArea((Element) takeElement.getElementsByTagName("area").item(0)); // get area

        return new Take(takeNumber, takeArea); // create take
    }

    private List<Role> createRoles(NodeList partNodes) {

        List<Role> roles = new ArrayList<>(); // create list of parts/roles

        // iterate through part/role nodes
        for(int j = 0; j < partNodes.getLength(); j++) {
            Node partNode = partNodes.item(j); // get part/role node

            if(partNode.getNodeType() == Node.ELEMENT_NODE) {
                Element partElement = (Element) partNode; // cast node to element
                Role role = createRole(partElement); // create role/part
                roles.add(role); // add part/role to list
            }
        }
        return roles;
    }

    private Role createRole(Element partElement) {

        String partName = partElement.getAttribute("name"); // get part/role name
        int partLevel = Integer.parseInt(partElement.getAttribute("level")); // get part/role level
        Area partArea = getArea((Element) partElement.getElementsByTagName("area").item(0)); // get area
        String partLine = partElement.getAttribute("line"); // get part/role line

        return new Role(partName, partLevel, partArea, partLine, false, false); // create role/part
    }

    private Location createTrailer(Element trailerElement) {

        List<String> trailerNeighbors = createNeighbors(trailerElement.getElementsByTagName("neighbor")); // get trailer neighbor nodes
        Area trailerArea = getArea((Element) trailerElement.getElementsByTagName("area").item(0)); // get trailer area

        return new Location("Trailer", null, null, trailerNeighbors, null, trailerArea); // create trailer
    }

    private Location createOffice(Element officeElement) {

        List<String> officeNeighbors = createNeighbors(officeElement.getElementsByTagName("neighbor")); // get office neighbor nodes
        Area officeArea = getArea((Element) officeElement.getElementsByTagName("area").item(0)); // get office area

        return new Location("Casting Office", null, null, officeNeighbors, null, officeArea); // create office
    }

    private static Area getArea(Element areaElement) {

        int x = Integer.parseInt(areaElement.getAttribute("x")); // get x coordinate
        int y = Integer.parseInt(areaElement.getAttribute("y")); // get y coordinate
        int h = Integer.parseInt(areaElement.getAttribute("h")); // get height
        int w = Integer.parseInt(areaElement.getAttribute("w")); // get width

        return new Area(x, y, h, w); // create area
    }

}