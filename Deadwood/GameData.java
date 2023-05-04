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

        d.getDocumentElement().normalize(); // normalize text representation

        String boardName = d.getDocumentElement().getAttribute("name"); // get board name

        NodeList locationNodes = d.getElementsByTagName("set"); // get all location nodes
        List<Location> locations = new ArrayList<>(); // create list of locations

        // iterate through location nodes
        for(int i = 0; i < locationNodes.getLength(); i++) {

            Node locationNode = locationNodes.item(i); // get location node

            // check if node is an element node
            if(locationNode.getNodeType() == Node.ELEMENT_NODE) {

                Element locationElement = (Element) locationNode; // cast node to element
                String locationName = locationElement.getAttribute("name"); // get location name
                NodeList neighborNodes = locationElement.getElementsByTagName("neighbor"); // get neighbor nodes
                List<String> neighbors = new ArrayList<>(); // create list of neighbors

                // iterate through neighbor nodes
                for(int j = 0; j < neighborNodes.getLength(); j++) {
                    Node neighborNode = neighborNodes.item(j); // get neighbor node

                    // check if node is an element node
                    if(neighborNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element neighborElement = (Element) neighborNode; // cast node to element
                        neighbors.add(neighborElement.getAttribute("name")); // add neighbor name to list
                    }
                }
                // get location area
                Area locationArea = getArea((Element) locationElement.getElementsByTagName("area").item(0));

                NodeList takeNodes = locationElement.getElementsByTagName("take"); // get take nodes
                List<Take> takes = new ArrayList<>(); // create list of takes

                // iterate through take nodes
                for(int j = 0; j < takeNodes.getLength(); j++) {

                    Node takeNode = takeNodes.item(j); // get take node

                    // check if node is an element node
                    if(takeNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element takeElement = (Element) takeNode; // cast node to element
                        int takeNumber = Integer.parseInt(takeElement.getAttribute("number")); // get take number
                        Area takeArea = getArea((Element) takeElement.getElementsByTagName("area").item(0)); // get area

                        Take take = new Take(takeNumber, takeArea); // create take
                        takes.add(take); // add take to list
                    }
                }
                NodeList partNodes = locationElement.getElementsByTagName("part"); // get part/role nodes

                List<Role> roles = new ArrayList<>(); // create list of parts/roles

                // iterate through part/role nodes
                for(int j = 0; j < partNodes.getLength(); j++) {

                    Node partNode = partNodes.item(j); // get part/role node

                    // check if node is an element node
                    if(partNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element partElement = (Element) partNode; // cast node to element
                        String partName = partElement.getAttribute("name"); // get part/role name
                        int partLevel = Integer.parseInt(partElement.getAttribute("level")); // get part/role level
                        Area partArea = getArea((Element) partElement.getElementsByTagName("area").item(0)); // get area
                        String partLine = partElement.getAttribute("line"); // get part/role line

                        Role role = new Role(partName, partLevel, partArea, partLine, false, false); // create role/part
                        roles.add(role); // add part/role to list
                    }
                }
                Location location = new Location(locationName, null, roles, neighbors, takes, locationArea); // create location
                locations.add(location); // add location to list
            }
        }
        NodeList trailerNodes = d.getElementsByTagName("trailer"); // get trailer nodes
        Node trailerNode = trailerNodes.item(0); // get trailer node
        Element trailerElement = (Element) trailerNode; // cast node to element

//        String trailerName = trailerElement.getAttribute("name"); // get trailer name -- don't need this

        NodeList trailerNeighborNodes = trailerElement.getElementsByTagName("neighbor"); // get trailer neighbor nodes
        List<String> trailerNeighbors = new ArrayList<>(); // create list of trailer neighbors

        // iterate through trailer neighbor nodes
        for(int j = 0; j < trailerNeighborNodes.getLength(); j++) {

            Node trailerNeighborNode = trailerNeighborNodes.item(j); // get trailer neighbor node

            // check if node is an element node
            if(trailerNeighborNode.getNodeType() == Node.ELEMENT_NODE) {

                Element trailerNeighborElement = (Element) trailerNeighborNode; // cast node to element
                trailerNeighbors.add(trailerNeighborElement.getAttribute("name")); // add neighbor name to list
            }
        }
        Area trailerArea = getArea((Element) trailerElement.getElementsByTagName("area").item(0)); // get trailer area

        Location trailer = new Location("Trailer", null, null, trailerNeighbors, null, trailerArea); // create trailer
        locations.add(trailer); // add trailer to list

        NodeList officeNodes = d.getElementsByTagName("office"); // get office nodes
        Node officeNode = officeNodes.item(0); // get office node
        Element officeElement = (Element) officeNode; // cast node to element
//        String officeName = officeElement.getAttribute("name"); // get office name -- don't need this
        NodeList officeNeighborNodes = officeElement.getElementsByTagName("neighbor"); // get office neighbor nodes
        List<String> officeNeighbors = new ArrayList<>(); // create list of office neighbors

        // iterate through office neighbor nodes
        for(int j = 0; j < officeNeighborNodes.getLength(); j++) {

            Node officeNeighborNode = officeNeighborNodes.item(j); // get office neighbor node

            // check if node is an element node
            if(officeNeighborNode.getNodeType() == Node.ELEMENT_NODE) {
                Element officeNeighborElement = (Element) officeNeighborNode; // cast node to element
                officeNeighbors.add(officeNeighborElement.getAttribute("name")); // add neighbor name to list
            }
        }
        Area officeArea = getArea((Element) officeElement.getElementsByTagName("area").item(0)); // get office area

        Location office = new Location("Casting Office", null, null, officeNeighbors, null, officeArea); // create office
        locations.add(office); // add office to list

        this.board = new Board(boardName, locations, 10); // create board
    }

    private static Area getArea(Element e) {
        int x = Integer.parseInt(e.getAttribute("x")); // get x coordinate
        int y = Integer.parseInt(e.getAttribute("y")); // get y coordinate
        int h = Integer.parseInt(e.getAttribute("h")); // get height
        int w = Integer.parseInt(e.getAttribute("w")); // get width
        return new Area(x, y, h, w); // create area
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