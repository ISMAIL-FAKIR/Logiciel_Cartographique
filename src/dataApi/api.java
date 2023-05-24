package dataApi;

import javafx.scene.shape.Polygon;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class api {

    public static URL getDataFromApi(String city) throws IOException {        //back up
        String urlStr = ("https://overpass-api.de/api/interpreter?data=" +
                URLEncoder.encode("[out:xml];\n" +
                        "area[name='France'] -> .de;\n" +
                        "area[admin_level=8][name='"+city+"'] -> .zip;\n" +
                        "rel[admin_level=8][name='"+city+"'];\n" +
                        "out geom;\n" +
                        "(\n" +
                        "way(area.de)(area.zip)[highway];\n" +
                        "way(area.de)(area.zip)[building];\n" +
                        ");\n" +
                        "out geom;", "UTF-8"));

        URL url = new URL(urlStr);
        return url;
    }

    /*public static URL getDataFromApi(String city) throws IOException { //récupérer routes et bâtiments
        String urlStr = ("https://overpass-api.de/api/interpreter?data=" +
                URLEncoder.encode("[out:xml];\n" +
                        "area[name='France'] -> .de;\n" +
                        "area[admin_level=8][name="+city+"] -> .zip;\n" +
                        "node[\"place\"=\"city\"][\"name\"="+city+"] -> .center;\n" +
                        "rel[admin_level=8][name='"+city+"'];\n" +
                        "out geom;\n" +
                        "(\n" +
                        "way(area.de)(area.zip)[building];\n" +
                        "way(area.de)(area.zip)[highway];\n" +
                        "way(around.center:4000)[\"highway\"];\n" +
                        ");\n" +
                        "out geom;", "UTF-8"));

        URL url = new URL(urlStr);
        return url;
    }*/

    public static URL getMallsInCity(String city) throws IOException {
        String query = "[out:xml];" +
                "area[name=\"" + city + "\"]->.city;" +
                "(" +
                "  way(area.city)[shop]; " +
                "  relation(area.city)[shop]; " +
                "); " +
                "out geom;";

        String urlStr = "https://overpass-api.de/api/interpreter?data=" + URLEncoder.encode(query);
        URL url = new URL(urlStr);
        return url;
    }

    public static URL getParksAndForestsData(String ville) throws IOException {
        String query = "[out:xml];\n"
                + "area[name='" + ville + "'][admin_level=8][boundary=administrative]->.searchArea;\n"
                + "(\n"
                + "  way['leisure'='park'](area.searchArea);\n"
                + "  way['landuse'='forest'](area.searchArea);\n"
                + ");\n"
                + "(._;>;);\n"
                + "(\n"
                + "  way._;\n"
                + ")->.ways;\n"
                + "foreach .ways -> .way {\n"
                + "  (\n"
                + "    way.way;\n"
                + "    node(w)->.nodes;\n"
                + "  )->.s;\n"
                + "  (\n"
                + "    rel(bw.s);\n"
                + "    rel(br.s);\n"
                + "    rel(bn.s);\n"
                + "  )->.relations;\n"
                + "  (\n"
                + "    .nodes;\n"
                + "    way(bn.nodes)->.nodes;\n"
                + "  )->.inner;\n"
                + "  way.way(around.inner:0.01)->.outer;\n"
                + "  (\n"
                + "    .inner;\n"
                + "    .outer;\n"
                + "  )->.all;\n"
                + "  (.all; - .relations;)->.c;\n"
                + "  .c out meta;\n"
                + "}";

        String urlStr = "https://overpass-api.de/api/interpreter?data=" + URLEncoder.encode(query);
        return new URL(urlStr);
    }

    public static URL getForestsAndParksInCity(String city) throws IOException {
        String query = "[out:xml];\n" +
                "area[name='" + city + "'][admin_level=8][boundary=administrative]->.searchArea;\n" +
                "(\n" +
                "  way['leisure'='park'](area.searchArea);\n" +
                "  way['landuse'='forest'](area.searchArea);\n" +
                ");\n" +
                "/*added by auto repair*/\n" +
                "(._;>;);\n" +
                "/*end of auto repair*/\n" +
                "node(w);\n" +
                "out meta;\n" +
                "out bb;";

        String urlStr = "https://overpass-api.de/api/interpreter?data=" + URLEncoder.encode(query);

        URL url = new URL(urlStr);
        return url;
    }

    /*public static URL getWaterPointsInCity(String city) throws IOException {
        String urlStr = ("https://overpass-api.de/api/interpreter?data=" +
                URLEncoder.encode("[out:xml];" +
                        "area[name='France'] -> .de;" +
                        "area[admin_level=8][name='"+city+"'] -> .zip;" +
                        "rel[admin_level=8][name='"+city+"'];" +
                        "out geom;" +
                        "(" +
                        //"    way(area.de)(area.zip)[waterway=river];" +
                       // "    way(area.de)(area.zip)[waterway=canal];" +
                        "    way(area.de)(area.zip)[natural=water];" +
                        ");" +
                        "out geom;", "UTF-8"));

        URL url = new URL(urlStr);
        return url;
    }*/

    public static URL getWaterPointsInCity(String city) throws IOException {
        String urlStr = ("https://overpass-api.de/api/interpreter?data=" +
                URLEncoder.encode("[out:xml];" +
                        "area[name='France'] -> .de;" +
                        "area[admin_level=8][name='"+city+"'] -> .zip;" +
                        "rel[admin_level=8][name='"+city+"'] -> .relations;" +
                        "out geom;" +
                        "(" +
                        "    way(area.de)(area.zip)[natural=water];" +
                        ");" +
                        "out geom;", "UTF-8"));

        URL url = new URL(urlStr);
        return url;
    }

    public static URL getWaterPointsInCity2(String city) throws IOException {
        String urlStr = ("https://overpass-api.de/api/interpreter?data=" +
                URLEncoder.encode("[out:xml];" +
                        "area[name='France'] -> .de;" +
                        "area[admin_level=8][name='"+city+"'] -> .zip;" +
                        "rel[admin_level=8][name='"+city+"'] -> .relations;" +
                        "out geom;" +
                        "(" +
                        "    way(area.de)(area.zip)[waterway=river];" +
                        "    way(area.de)(area.zip)[waterway=canal];" +
                        ");" +
                        "out geom;", "UTF-8"));

        URL url = new URL(urlStr);
        return url;
    }



    public static String getData(String latdep, String londep, String latar, String lonar) throws IOException { //recuperer ensemble de noeuds entre 2 cordonnées
        String query = "[out:xml];way[highway][area!=yes](around:500, %s, %s, %s, %s);>;out;";
        String formattedQuery = String.format(query, latdep, londep, latar, lonar);
        return "https://overpass-api.de/api/interpreter?data=" + URLEncoder.encode(formattedQuery, "UTF-8");
    }

    public static URL getAllData(String ville, String rue, String adresse) throws IOException { //recuperer coordonnées d'une adresse
        String urlStr = ("https://overpass-api.de/api/interpreter?data=" +
                URLEncoder.encode("[out:xml];\n"
                        + "area[\"name\"=\""+ville+"\"]->.ville;\n"
                        + "node(area.ville)[\"place\"=\"city\"][\"name\"=\""+ville+"\"];\n"
                        + "node(area.ville)[\"addr:street\"~\""+rue+"\"];\n"
                        + "node(around:10)[\"addr:housenumber\"=\""+adresse+"\"];\n"
                        + "out body;\n"
                        + ">;\n"
                        + "out skel qt;"));

        URL url = new URL(urlStr);
        return url;
    }

    public static URL getVilleAndRueData(String ville, String rue) throws IOException { //recuperer coordonnées d'une rue
        String urlStr = ("https://overpass-api.de/api/interpreter?data=" +
                URLEncoder.encode("[out:xml];\n"
                        + "area[\"name\"=\""+ville+"\"]->.ville;\n"
                        + "node(area.ville)[\"place\"=\"city\"][\"name\"=\""+ville+"\"];\n"
                        + "node(area.ville)[\"addr:street\"~\""+rue+"\"];\n"
                        + "out body;\n"
                        + ">;\n"
                        + "out skel qt;"));

        URL url = new URL(urlStr);
        return url;
    }

    public static URL getVilleData(String ville) throws IOException { //recuperer coordonnées d'une ville
        String urlStr = ("https://overpass-api.de/api/interpreter?data=" +
                URLEncoder.encode("[out:xml];\n"
                        + "area[\"name\"=\""+ville+"\"]->.ville;\n"
                        + "node(area.ville)[\"place\"=\"city\"][\"name\"=\""+ville+"\"];\n"
                        + "out body;\n"
                        + ">;\n"
                        + "out skel qt;"));

        URL url = new URL(urlStr);
        return url;
    }

    public static URL streetnameCity(String ville) throws IOException {
        String urlStr = ("https://overpass-api.de/api/interpreter?data=" +
                URLEncoder.encode("[out:xml];\n"
                        + "area[\"name\"=\"" + ville + "\"] -> .ville;\n"
                        + "way(area.ville)[highway][name];\n"
                        + "out geom;"));

        URL url = new URL(urlStr);
        return url;
    }

    public static void searchNodeForItineraire(String latdep, String londep, String latar, String lonar) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        String url = getData(latdep, londep, latar, lonar);
        // Ouvrir une connexion HTTP
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

        // Récupérer la réponse du serveur OSM
        InputStream in = conn.getInputStream();

        // Écrire la réponse dans un fichier XML
        FileOutputStream out = new FileOutputStream("C:\\Users\\Nicolas\\IdeaProjects\\pierre\\projet-map-groupe-8-PierreBlondeau\\xml\\itineraire.fxml");
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        in.close();
        out.close();
    }


    public static node[][] getNodesDataMember(URL url) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //Création d'une fabrique de parseurs
        DocumentBuilder builder = factory.newDocumentBuilder(); //Création d'un parseur
        Document document = builder.parse(url.openStream()); //Création d'un Document

        NodeList nList = document.getElementsByTagName("member"); //Récupération de la liste des noeuds avec le tag "member"

        if (nList.getLength() > 0) { //Si la liste n'est pas vide
            node[][] point = new node[nList.getLength()][10000]; //Création d'un tableau de noeuds

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) { //Si le noeud est un élément
                    Element eElement = (Element) node; //On récupère l'élément
                    for (int i = 0; i < eElement.getElementsByTagName("nd").getLength(); i++) {
                        point[temp][i] = new node( //On ajoute des noeuds dans le tableau point, avec la latitute et longitude
                                Double.parseDouble(eElement.getElementsByTagName("nd").item(i).getAttributes().getNamedItem("lat").getNodeValue()),
                                Double.parseDouble(eElement.getElementsByTagName("nd").item(i).getAttributes().getNamedItem("lon").getNodeValue()));

                    }
                }
            }
            return point;
        }
        return null;
    }

    public static node[][] getNodesDataWay(URL url) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(url.openStream());

        NodeList nListWay = document.getElementsByTagName("way");
        if (nListWay.getLength() > 0) {
            node[][] pointWay = new node[nListWay.getLength()][10000];

            for (int temp = 0; temp < nListWay.getLength(); temp++) {
                Node node = nListWay.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    for (int i = 0; i < eElement.getElementsByTagName("nd").getLength(); i++) {
                        pointWay[temp][i] = new node(
                                Double.parseDouble(eElement.getElementsByTagName("nd").item(i).getAttributes().getNamedItem("lat").getNodeValue()),
                                Double.parseDouble(eElement.getElementsByTagName("nd").item(i).getAttributes().getNamedItem("lon").getNodeValue()));
                    }
                }
            }
            return pointWay;
        }
        System.out.println("no way");
        return null;
    }

    public static node[][] getNodesDataRiver(URL url) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(url.openStream());

        NodeList nListWay = document.getElementsByTagName("way");
        if (nListWay.getLength() > 0) {
            node[][] pointWay = new node[nListWay.getLength()][];
            for (int temp = 0; temp < nListWay.getLength(); temp++) {
                Node node = nListWay.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    NodeList ndList = eElement.getElementsByTagName("nd");
                    pointWay[temp] = new node[ndList.getLength()];
                    for (int i = 0; i < ndList.getLength(); i++) {
                        Node nd = ndList.item(i);
                        if (nd.getNodeType() == Node.ELEMENT_NODE) {
                            Element ndElement = (Element) nd;
                            pointWay[temp][i] = new node(
                                    Double.parseDouble(ndElement.getAttributes().getNamedItem("lat").getNodeValue()),
                                    Double.parseDouble(ndElement.getAttributes().getNamedItem("lon").getNodeValue())
                            );
                        }
                    }
                }
            }
            System.out.println("cnicnd " + pointWay[0][0].getLat() + " " + pointWay[0][0].getLon());
            return pointWay;
        }
        System.out.println("no way");
        return null;
    }

    public static node[][] getNodesDataRiver2(URL url) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(url.openStream());

        NodeList nListWay = document.getElementsByTagName("way");
        if (nListWay.getLength() > 0) {
            node[][] pointWay = new node[nListWay.getLength()][];
            for (int temp = 0; temp < nListWay.getLength(); temp++) {
                Node node = nListWay.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    NodeList tagList = eElement.getElementsByTagName("tag");
                    boolean isRiverOrCanal = false;
                    for (int i = 0; i < tagList.getLength(); i++) {
                        Node tagNode = tagList.item(i);
                        if (tagNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element tagElement = (Element) tagNode;
                            String key = tagElement.getAttribute("k");
                            if (key.equals("waterway")) {
                                String value = tagElement.getAttribute("v");
                                if (value.equals("river") || value.equals("canal")) {
                                    isRiverOrCanal = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (isRiverOrCanal) {
                        NodeList ndList = eElement.getElementsByTagName("nd");
                        pointWay[temp] = new node[ndList.getLength()];
                        for (int i = 0; i < ndList.getLength(); i++) {
                            Node nd = ndList.item(i);
                            if (nd.getNodeType() == Node.ELEMENT_NODE) {
                                Element ndElement = (Element) nd;
                                pointWay[temp][i] = new node(
                                        Double.parseDouble(ndElement.getAttributes().getNamedItem("lat").getNodeValue()),
                                        Double.parseDouble(ndElement.getAttributes().getNamedItem("lon").getNodeValue())
                                );
                            }
                        }
                    }
                }
            }
            System.out.println("cnicnd " + pointWay[0][0].getLat() + " " + pointWay[0][0].getLon());
            return pointWay;
        }
        System.out.println("no way");
        return null;
    }

    /*
    area[name='Avignon'][admin_level=8][boundary=administrative]->.searchArea;
(
  way['leisure'='park'](area.searchArea);
  way['landuse'='forest'](area.searchArea);
);
/*added by auto repair*/
/*(._;>;);
    /*end of auto repair*/
    /*node(w); // retourne les nœuds avec au moins un tag lié
    out meta;
    out bb;

      area[name='Avignon'][admin_level=8][boundary=administrative]->.searchArea;
(
  way['leisure'='park'](area.searchArea);
  way['landuse'='forest'](area.searchArea);
);
(._;>;);
node(w); // retourne les nœuds avec au moins un tag
out meta;
out geom body;

     */


    public static node[][] getNodesDataForestsAndParks(URL url) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(url.openStream());

        NodeList nList = document.getElementsByTagName("node");
        if (nList.getLength() > 0) {
            node[][] pointWay = new node[nList.getLength()][nList.getLength()];

            int temp = 0;
            while (temp < nList.getLength()) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    int i = 0;
                    Node nextNode = node.getNextSibling();
                    while (nextNode != null && nextNode.getNodeName() != "way") {
                        if (nextNode.getNodeType() == Node.ELEMENT_NODE && nextNode.getNodeName() == "node") {
                            Element nextElement = (Element) nextNode;
                            pointWay[temp][i] = new node(
                                    Double.parseDouble(nextElement.getAttributes().getNamedItem("lat").getNodeValue()),
                                    Double.parseDouble(nextElement.getAttributes().getNamedItem("lon").getNodeValue())
                            );
                            i++;
                        }
                        nextNode = nextNode.getNextSibling();
                    }
                    temp++;
                } else {
                    temp++;
                }
            }
            return pointWay;
        }
        System.out.println("no node data");
        return null;
    }

    public static List<Polygon> createPolygonsFromNodes(Double[][] nodes) {
        List<Polygon> polygons = new ArrayList<>();
        System.out.println("createpolygonsfromnodes " + nodes[0][0] + " " + nodes[0][1]);
        int i = 0;
        while (i < nodes.length) {
            int j = 0;
            Polygon polygon = new Polygon();
            while (j < nodes[i].length && nodes[i][j] != null) {
                double lat = nodes[i][j];
                double lon = nodes[i][j+1];
                polygon.getPoints().addAll(lat, lon);
                j += 2;
            }
            if (!polygon.getPoints().isEmpty()) {
                polygons.add(polygon);
            }
            i++;
        }

        return polygons;
    }

    public static node[][] getNodesData(URL url) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(url.openStream());

        NodeList nodeList = document.getElementsByTagName("node");
        if (nodeList.getLength() > 0) {
            node[][] pointWay = new node[nodeList.getLength()][1];
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    double lat = Double.parseDouble(element.getAttribute("lat"));
                    double lon = Double.parseDouble(element.getAttribute("lon"));
                    pointWay[i][0] = new node(lat, lon);
                }
            }
            return pointWay;
        } else {
            System.out.println("No node found in document");
            return null;
        }
    }

    public static node[][] getNodesDataStreet(URL url) throws ParserConfigurationException, IOException, SAXException {
        double minlat = 0;
        double minlon = 0;
        double maxlat = 0;
        double maxlon = 0;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(url.openStream());

        NodeList nListWay = document.getElementsByTagName("way");
        if (nListWay.getLength() > 0) {
            node[][] pointWay = new node[nListWay.getLength()][3];

            for (int temp = 0; temp < nListWay.getLength(); temp++) {
                Node node = nListWay.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    NodeList boundsList = eElement.getElementsByTagName("bounds");
                    if (boundsList.getLength() > 0) {
                        Element boundsElement = (Element) boundsList.item(0);
                        minlat = Double.parseDouble(boundsElement.getAttribute("minlat"));
                        maxlat = Double.parseDouble(boundsElement.getAttribute("maxlat"));
                        minlon = Double.parseDouble(boundsElement.getAttribute("minlon"));
                        maxlon = Double.parseDouble(boundsElement.getAttribute("maxlon"));
                    }
                    NodeList tagList = eElement.getElementsByTagName("tag");
                    for (int i = 0; i < tagList.getLength(); i++) {
                        Element tagElement = (Element) tagList.item(i);
                        if (tagElement.getAttribute("k").equals("name")) {
                            String name = tagElement.getAttribute("v");
                            pointWay[temp][0] = new node(minlat, minlon);
                            pointWay[temp][1] = new node(maxlat, maxlon);
                            pointWay[temp][2] = new node(name);
                            break; // On sort de la boucle car on a trouvé l'attribut "name"
                        }
                    }
                }
            }
            return pointWay;
        }
        System.out.println("no way");
        return null;
    }

    public static double[] getMinMaxLatLon(node[][] point) {
        double minLat = Double.POSITIVE_INFINITY;
        double maxLat = Double.NEGATIVE_INFINITY;
        double minLon = Double.POSITIVE_INFINITY;
        double maxLon = Double.NEGATIVE_INFINITY;
        double tab[] = new double[4];
        double assos[] = new double[4];

        for (int i = 0; i < point.length; i++) {
            for (int j = 0; j < point[i].length; j++) {
                if (point[i][j] != null) {
                    double lat = point[i][j].getLat();
                    double lon = point[i][j].getLon();

                    if (lat < minLat) {
                        minLat = lat;
                        assos[0] = lon;
                    }
                    if (lat > maxLat) {
                        maxLat = lat;
                        assos[1] = lon;
                    }
                    if (lon < minLon) {
                        minLon = lon;
                        assos[2] = lat;
                    }
                    if (lon > maxLon) {
                        maxLon = lon;
                        assos[3] = lat;
                    }
                }
            }
        }

        tab[0] = minLat;
        tab[1] = maxLat;
        tab[2] = minLon;
        tab[3] = maxLon;

        System.out.println("minLat : " + minLat + " maxLat : " + maxLat + " minLon : " + minLon + " maxLon : " + maxLon);
        System.out.println("assos : " + assos[0] + " " + assos[1] + " " + assos[2] + " " + assos[3]);
        return tab;

    }


    public static Double[][] getReste(URL url,double tab[]) throws ParserConfigurationException, IOException, SAXException {
        Double[][] reste = node.getNodesCoordinates(getNodesDataWay(url),tab);
        return reste;
    }

    public static Double[][] getContours(URL url,double tab[]) throws ParserConfigurationException, IOException, SAXException {
        Double[][] contours = node.getNodesCoordinates(getNodesDataMember(url),tab);
        return contours;
    }

    public static Double[][] getWater(URL url,double tab[]) throws ParserConfigurationException, IOException, SAXException {
        Double[][] contours = node.getNodesCoordinates(getNodesDataRiver(url),tab);
        return contours;
    }

    public static Double[][] getWater2(URL url,double tab[]) throws ParserConfigurationException, IOException, SAXException {
        Double[][] contours = node.getNodesCoordinates(getNodesDataRiver2(url),tab);
        return contours;
    }

    public static Double[][] getForest(URL url,double tab[]) throws ParserConfigurationException, IOException, SAXException {
        Double[][] contours = node.getNodesCoordinates(getNodesDataForestsAndParks(url),tab);
        return contours;
    }

    public static Double[][] getMall(URL url,double tab[]) throws ParserConfigurationException, IOException, SAXException {
        Double[][] contours = node.getNodesCoordinates(getNodesDataRiver(url),tab);
        return contours;
    }

    public static URL getUrl(String Url) throws IOException {
        URL url = getDataFromApi(Url);
        return url;
    }


}
