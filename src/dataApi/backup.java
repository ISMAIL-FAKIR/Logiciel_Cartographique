/*package dataApi;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Scale;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static dataApi.api.getNodesDataMember;
import static dataApi.node.getNodesCoordinates;


public class itineraire extends Application {


    public Graph g = new Graph();
    public Double tab[] = new Double[4]; //declaration du tab pour pouvoir modifier les points
    public String[] coordonnees = new String[4];
    public Circle depart = new Circle(4, Color.RED);
    public Circle arrivee = new Circle(4, Color.BLUE);
    public Group group = new Group();
    public Group group2 = new Group();
    public Group group3 = new Group();
    public Group group4 = new Group();
    List<Circle> cercles = new ArrayList<>();
    List<Polygon> polygonsforet = new ArrayList<>();
    List<Polygon> polygonseau = new ArrayList<>();
    public Button affichage = new Button("Afficher");
    public CheckBox checkBoxForet = new CheckBox("cacher les forêts");
    public CheckBox checkBoxEau = new CheckBox("cacher les eaux");
    public CheckBox checkBoxMall = new CheckBox("cacher les zones commerciales");
    public CheckBox checkBoxArret = new CheckBox("cacher les arrets de bus");
    Pane pane3 = new Pane();


    public Group createLine(Group group, Double[][] coordonnees) {
        for (Double[] coordonnee : coordonnees) {
            for (int j = 0; j < coordonnee.length; j = j + 2) {
                if (coordonnee[j] != null && coordonnee[j + 1] != null && coordonnee[j + 2] != null && coordonnee[j + 3] != null) {
                    //create line
                    //System.out.println(coordonnee[j] + " " + coordonnee[j + 1] + " " + coordonnee[j + 2] + " " + coordonnee[j + 3]);
                    Line Line = new Line(coordonnee[j], coordonnee[j + 1], coordonnee[j + 2], coordonnee[j + 3]);
                    Line.setStroke(Color.DARKGREY);
                    group.getChildren().add(Line);
                }
            }
        }
        return group;
    }

    /*public Group createLinecolorForest(Group group, Double[][] coordonnees) {
        if (coordonnees == null) {
            System.out.println("coordonnees null");
            return group;
        }

        for (int i = 0; i < coordonnees.length; i+=2) {
            for (int j = 0; j < coordonnees[i].length ; j += 2) {
                if(coordonnees[i][j] != null && coordonnees[i][j+1] != null && coordonnees[i+1][j] != null && coordonnees[i+1][j+1] != null) {

                    Line Line = new Line(coordonnees[i][j], coordonnees[i][j + 1] , coordonnees[i + 1][j], coordonnees[i + 1][j + 1]);
                    Line.setStroke(Color.GREEN);
                    group.getChildren().add(Line);
                }
            }
        }

        return group;
    }*/

    /*public Group createLinecolorWater(Group group, Double[][] coordonnees) {
        {
            if (coordonnees == null) {
                System.out.println("coordonnees null");
                return group;
            }

            int length = 0;

            //2 boucles pour trouver la longueur du tableau
            for (int i = 0; i < coordonnees.length; i++) {
                for (int j = 0; j < coordonnees[i].length; j++) {
                    if (coordonnees[i][j] != null) {
                        length++;
                    }
                }
            }

            for (int i = 0; i < length; i += 2) {
                for (int j = 0; j <length; j += 2) {
                    if (coordonnees[i][j] != null && coordonnees[i][j + 1] != null && coordonnees[i + 1][j] != null && coordonnees[i + 1][j + 1] != null) {

                        Line Line = new Line(coordonnees[i][j], coordonnees[i][j + 1], coordonnees[i + 1][j], coordonnees[i + 1][j + 1]);
                        Line.setStroke(Color.BLUE);
                        group.getChildren().add(Line);
                    }
                }
            }
        }
        return group;
    }*/

   /* public Group createLinecolorWater(Group group, Double[][] coordonnees) {
        for (Double[] coordonnee : coordonnees) {
            for (int j = 0; j < coordonnee.length; j = j + 2) {
                if (coordonnee[j] != null && coordonnee[j + 1] != null && coordonnee[j + 2] != null && coordonnee[j + 3] != null) {
                    //create line
                    Line Line = new Line(coordonnee[j], coordonnee[j + 1], coordonnee[j + 2], coordonnee[j + 3]);
                    Line.setStroke(Color.BLUE);
                    group.getChildren().add(Line);
                }
            }
        }
        return group;
    }

    public Group createLinecolorMall(Group group, Double[][] coordonnees) {
        for (Double[] coordonnee : coordonnees) {
            for (int j = 0; j < coordonnee.length; j = j + 2) {
                if (coordonnee[j] != null && coordonnee[j + 1] != null && coordonnee[j + 2] != null && coordonnee[j + 3] != null) {
                    //create line
                    Line Line = new Line(coordonnee[j], coordonnee[j + 1], coordonnee[j + 2], coordonnee[j + 3]);
                    Line.setStroke(Color.RED);
                    group.getChildren().add(Line);
                }
            }
        }
        return group;
    }

    public Group createLineColor(Group group, Double[][] coordonnees) {

        for (Double[] coordonnee : coordonnees) {
            for (int j = 0; j < coordonnee.length; j = j + 2) {
                if (coordonnee[j] != null && coordonnee[j + 1] != null && coordonnee[j + 2] != null && coordonnee[j + 3] != null) {
                    //create line
                    System.out.println(coordonnee[j]);
                    Line Line = new Line(coordonnee[j], coordonnee[j + 1], coordonnee[j + 2], coordonnee[j + 3]);
                    tab[0] =  coordonnee[0];
                    System.out.println("tab 0 "+tab[0]);
                    tab[1] =  coordonnee[1];
                    tab[2] =  Line.getEndX();
                    tab[3] =  Line.getEndY();
                    Line.setStroke(Color.RED);
                    group.getChildren().add(Line);
                }
            }
        }
        return group;
    }

    //formule trouvée sur internet
    private static float convertisseur( float lon1, float lon2, float lat1, float lat2)
    {
        float rayonTerre = (float) 6378.137; // rayon de la terre
        float res_lat = (float) (lat2 * Math.PI / 180 - lat1 * Math.PI / 180);
        float res_lon = (float) (lon2 * Math.PI / 180 - lon1 * Math.PI / 180);
        float a = (float) (Math.sin(res_lat/2) * Math.sin(res_lat/2) + Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) * Math.sin(res_lon/2) * Math.sin(res_lon/2));
        float c = (float) (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)));
        float nbkilometre = rayonTerre * c; //on convertie en kilometre
        return nbkilometre * 1000; // on convertie en mettre
    }


    public URL urldescoordonnees(String ville, String rue, String numero) throws IOException { //appel des fonctions pour avoir l'url de la requete
        if(ville == null ) {
            return null;
        }
        if(numero != null ) {
            return api.getAllData(ville, rue, numero);
        }
        else if(rue != null ) {
            return api.getVilleAndRueData(ville, rue);
        }
        else
        {
            return api.getVilleData(ville);
        }
    }

    public node[][] recherchecoordonnees(URL url) throws ParserConfigurationException, IOException, SAXException {
        return api.getNodesData(url);
    }


    public void recup(node[][] point1, node[][] point2) //met les nodes dans un tableau de string
    {
        //add data from point1 to coordonnees1
        if(point1 != null || point2 != null) {

            coordonnees[0] = String.valueOf(point1[0][0].getLat());
            coordonnees[1] = String.valueOf(point1[0][0].getLon());
            coordonnees[2] = String.valueOf(point2[0][0].getLat());
            coordonnees[3] = String.valueOf(point2[0][0].getLon());
            System.out.println(coordonnees[0]);
            System.out.println(coordonnees[1]);
            System.out.println(coordonnees[2]);
            System.out.println(coordonnees[3]);

        }
    }

    public void putDataInXMLFile() throws IOException, ParserConfigurationException, SAXException, TransformerException { //mettre les données dans le fichier xml
        String latdep = coordonnees[0];
        String londep = coordonnees[1];
        String latar = coordonnees[2];
        String lonar = coordonnees[3];
        System.out.println(latdep);
        System.out.println(londep);
        System.out.println(latar);
        System.out.println(lonar);

        api.searchNodeForItineraire(latdep, londep, latar, lonar);
    }


    public Group createLineColorItineraire(Group group, Double[][] coordonnees) {

        for (Double[] coordonnee : coordonnees) {
            for (int j = 0; j < coordonnee.length; j = j + 2) {
                if (coordonnee[j] != null && coordonnee[j + 1] != null && coordonnee[j + 2] != null && coordonnee[j + 3] != null) {
                    //create line
                    System.out.println(coordonnee[j]);
                    Line Line = new Line(coordonnee[j], coordonnee[j + 1], coordonnee[j + 2], coordonnee[j + 3]);
                    tab[0] =  coordonnee[0];
                    System.out.println("tab 0 "+tab[0]);
                    tab[1] =  coordonnee[1];
                    tab[2] =  Line.getEndX();
                    tab[3] =  Line.getEndY();
                    Line.setStroke(Color.RED);
                    group.getChildren().add(Line);
                }
            }
        }
        return group;
    }



    public node[][] ajout() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        Double nodelat = Double.parseDouble(coordonnees[0]);
        Double nodelon = Double.parseDouble(coordonnees[1]);

        Double node2lat = Double.parseDouble(coordonnees[2]);
        Double node2lon = Double.parseDouble(coordonnees[3]);

        g = g.initialise(nodelat, nodelon, node2lat, node2lon);
        node[][] tableau = new node[g.nodes.size()][g.nodes.size()];
        for (int j = 0; j <g.nodes.size(); j++) {
            tableau[0][j] = g.nodes.get(j);
            System.out.println(tableau[0][j].getLat());
            System.out.println(tableau[0][j].getLon());

        }
        return tableau;
    }

    public node[][] tableau_arret_de_bus(File file) throws IOException, ParserConfigurationException, SAXException {
        // Mettre dans un tableau de noeuds les noeuds des arrêts de bus qui sont dans le fichier xml
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("node");
        node[][] tab = new node[nList.getLength()][1];

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                double lat = Double.parseDouble(element.getAttribute("lat"));
                double lon = Double.parseDouble(element.getAttribute("lon"));
                tab[i][0] = new node(lat, lon);
            }
        }
        return tab;
    }

    public Group afficher_arret_de_bus(Double[][] tableau) {
        // affiche les arrêts de bus sur la carte
        Group group = new Group();
        for (int i = 0; i < tableau.length; i++) {
            Double lon = tableau[i][0];
            Double lat = tableau[i][1];
            if (lon != null && lat != null) {
                Circle circle = new Circle(lon, lat, 3);
                circle.setFill(Color.ORANGE);
                group.getChildren().add(circle);
            }
        }
        return group;
    }

    public void setdepAr()
    {
        System.out.println("tab 0 "+tab[0]);
        System.out.println("tab 1 "+tab[1]);
        System.out.println("tab 2 "+tab[2]);
        System.out.println("tab 3 "+tab[3]);

        depart.setCenterX(tab[0]);
        depart.setCenterY(tab[1]);
        arrivee.setCenterX(tab[2]);
        arrivee.setCenterY(tab[3]);
    }

    public void Stopitineraire(Group group)
    {
        if(depart.isVisible() && arrivee.isVisible())
        {
            depart.setVisible(false);
            arrivee.setVisible(false);
            group.getChildren().clear();
        }
    }

    /*public void afficherarrets()
    {

        for (Circle cercle : cercles) {
            if(cercle.isVisible()){
                cercle.setVisible(false);
            }
            else
            {
                cercle.setVisible(true);
            }
        }
    }*/

    /*public void afficherelements()
    {
        if(checkBoxForet.isSelected())
        {
            for (Polygon polygon : polygonsforet) {
                polygon.setVisible(false);
            }
        }
        else
        {
            for (Polygon polygon : polygonsforet) {
                polygon.setVisible(true);
            }
        }
        if(checkBoxEau.isSelected())
        {
            for (Polygon polygon : polygonseau) {
                polygon.setVisible(false);
            }
            for (javafx.scene.Node node : group4.getChildren()) {
                if (node instanceof Line) {
                    node.setVisible(false);
                }
            }
        }
        else
        {
            for (Polygon polygon : polygonseau) {
                polygon.setVisible(true);
            }
            for (javafx.scene.Node node : group4.getChildren()) {
                if (node instanceof Line) {
                    node.setVisible(true);
                }
            }
        }
        if(checkBoxMall.isSelected())
        {
            //boucle for pour setVisible(false) les ligne contenue dans le group3
            for (javafx.scene.Node node : group3.getChildren()) {
                if (node instanceof Line) {
                    node.setVisible(false);
                }
            }
        }
        else
        {
            for (javafx.scene.Node node : group3.getChildren()) {
                if (node instanceof Line) {
                    node.setVisible(true);
                }
            }
        }
        if(checkBoxArret.isSelected())
        {
            for (Circle cercle : cercles) {
                cercle.setVisible(false);
            }
        }
        else
        {
            for (Circle cercle : cercles) {
                cercle.setVisible(true);
            }
        }

    }


    // launch the application
    public void start(Stage stage) throws IOException, SAXException, ParserConfigurationException, TransformerException {



        //declaration node et tableau de nodes de la carte
        URL url = api.getUrl("Avignon"); // appel de l'api ici, pour ne faire qu'un appel

        double tab[] = api.getMinMaxLatLon(getNodesDataMember(url)); // min et max lat et lon

        Task<Double[][]> contourTask = new Task<>() {
            @Override
            protected Double[][] call() throws Exception {
                return api.getContours(url,tab);
            }
        };
        //Double[][] contour = api.getContours(url,tab); // contour ville

        contourTask.setOnSucceeded(e -> {
            Double[][] contour = contourTask.getValue();
            group = createLine(group, contour);
            pane3.getChildren().add(group);
        });


        Task<Double[][]> resteTask = new Task<>() {
            @Override
            protected Double[][] call() throws Exception {
                return api.getReste(url,tab);
            }
        };
        //Double[][] reste = api.getReste(url,tab); // buildings & routes

        resteTask.setOnSucceeded(e -> {
            Double[][] reste = resteTask.getValue();
            group2 = createLine(group2, reste);
            pane3.getChildren().add(group2);
        });



        URL urlmall = api.getMallsInCity("Avignon");

        Task<Double[][]> mallTask = new Task<>() {
            @Override
            protected Double[][] call() throws Exception {
                return api.getMall(urlmall,tab);
            }
        };
        //Double[][] centrecom = api.getMall(urlmall,tab); // centre commercial

        mallTask.setOnSucceeded(e -> {
            Double[][] centrecom = mallTask.getValue();
            group3 = createLinecolorMall(group3, centrecom);
            pane3.getChildren().add(group3);
        });


        URL urlforest = api.getParksAndForestsData("Avignon");

        Task<Double[][]> forestTask = new Task<>() {
            @Override
            protected Double[][] call() throws Exception {
                return api.getForest(urlforest, tab);
            }
        };
        /*Double[][] foret = api.getForest(urlforest,tab); // foret
        polygonsforet = api.createPolygonsFromNodes(foret);
        //set color for forest
        for (Polygon polygon : polygonsforet) {
            polygon.setFill(Color.GREEN);
        }*/

        /*forestTask.setOnSucceeded(event -> {
            Double[][] foret = forestTask.getValue();
            polygonsforet = api.createPolygonsFromNodes(foret);

            //set color for forest
            for (Polygon polygon : polygonsforet) {
                polygon.setFill(Color.GREEN);
            }

            // Ajout des polygones de forêt au BorderPane
            pane3.getChildren().addAll(polygonsforet);
        });

        URL urlwater = api.getWaterPointsInCity("Avignon");

        Task<Double[][]> waterTask = new Task<>() {
            @Override
            protected Double[][] call() throws Exception {
                return api.getWater(urlwater,tab);
            }
        };
        /*Double[][] eau = api.getWater(urlwater,tab); // eau
        //affichage de la latitude et longitude du 1er noeud mis dans eau
        polygonseau = api.createPolygonsFromNodes(eau);
        //set color for water
        for (Polygon polygon : polygonseau) {
            polygon.setFill(Color.BLUE);
        }*/

        /*waterTask.setOnSucceeded(event -> {
            Double[][] eau = waterTask.getValue();
            polygonseau = api.createPolygonsFromNodes(eau);
            //set color for water
            for (Polygon polygon : polygonseau) {
                polygon.setFill(Color.BLUE);
            }
            // Ajout des polygones d'eau au BorderPane
            pane3.getChildren().addAll(polygonseau);
        });

        URL urlwater2 = api.getWaterPointsInCity2("Avignon");

        Task<Double[][]> waterTask2 = new Task<>() {
            @Override
            protected Double[][] call() throws Exception {
                return api.getWater2(urlwater2,tab);
            }
        };

        waterTask2.setOnSucceeded(event -> {
            Double[][] centrecom = waterTask2.getValue();
            group4 = createLinecolorWater(group4, centrecom);
            pane3.getChildren().add(group4);
        });



        //placement du boouton et des checkbox
        affichage.setLayoutX(10);
        affichage.setLayoutY(90);
        checkBoxForet.setLayoutX(10);
        checkBoxForet.setLayoutY(10);
        checkBoxEau.setLayoutX(10);
        checkBoxEau.setLayoutY(30);
        checkBoxMall.setLayoutX(10);
        checkBoxMall.setLayoutY(50);
        checkBoxArret.setLayoutX(10);
        checkBoxArret.setLayoutY(70);




        // set title for the stage
        stage.setTitle("Creating popup");

        // create a button
        Button button = new Button("Changer d'itinéraire");
        Button button2 = new Button("Changer de ville");


        // creation d'un popup
        Popup popup = new Popup();
        Popup popup2 = new Popup();


        //Parent root = FXMLLoader.load(getClass().getResource("/src/fxml/TheCeriMap.fxml"));



            /*group = createLine(group, reste);
            group2 = createLine(group2, contour);
            //group3 = createLinecolorForest(group3, foret);
            /*group4 = createLinecolorWater(group4, eau);*/
        //group5 = createLinecolorMall(group5, centrecom);

        /*Group group7 = new Group();
        File file = new File("C:\\Users\\Nicolas\\IdeaProjects\\pierre\\projet-map-groupe-8-PierreBlondeau\\xml\\bus_stop.xml");

        group7 = afficher_arret_de_bus(getNodesCoordinates(tableau_arret_de_bus(file),tab));
        //add circle of group7 to a pane
        for (javafx.scene.Node node : group7.getChildren()) {
            if (node instanceof Circle) {
                node.setVisible(false);
                cercles.add((Circle) node);
            }
        }

        // creation d'un Pane qui contient les 2 points et le bouton chercher
        Pane pane = new Pane(depart, arrivee);
        pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        // creation d'un Pane qui la carte et l'itineraire
        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        new Thread(contourTask).start();
        new Thread(resteTask).start();
        new Thread(mallTask).start();
        new Thread(waterTask).start();
        new Thread(waterTask2).start();
        new Thread(forestTask).start();



        pane3.getChildren().add(button);
        pane3.getChildren().add(affichage);
        pane3.getChildren().add(checkBoxForet);
        pane3.getChildren().add(checkBoxEau);
        pane3.getChildren().add(checkBoxMall);
        pane3.getChildren().add(checkBoxArret);
        pane3.getChildren().addAll(cercles);


            /*pane3.getChildren().add(group);
            pane3.getChildren().add(group2);
            pane3.getChildren().add(group3);
            pane3.getChildren().addAll(polygonseau);
            pane3.getChildren().addAll(polygonsforet);

            pane3.getChildren().add(group5);*/
        /*pane3.getChildren().add(group4);
        pane3.getChildren().add(group5);*/
        /*pane3.getChildren().add(pane);


        borderPane.setCenter(pane3);



        //zone de test
        Scene scene = new Scene(borderPane, 1200, 900);
        button.setLayoutX(0.8 * scene.getWidth());
        button.setLayoutY(0);
        button2.setLayoutX(0.2 * scene.getWidth());
        button2.setLayoutY(0);

        scene.setOnScroll(event -> {
            double zoomFactor = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0) {
                zoomFactor = 2.0 - zoomFactor;
            }
            Scale scale = new Scale(zoomFactor, zoomFactor, event.getSceneX(), event.getSceneY());
            borderPane.getTransforms().add(scale);
            button.setScaleX(button.getScaleX() * 1 / zoomFactor);
            button.setScaleY(button.getScaleY() * 1 / zoomFactor);
            button2.setScaleX(button2.getScaleX() * 1 / zoomFactor);
            button2.setScaleY(button2.getScaleY() * 1 / zoomFactor);
            checkBoxForet.setScaleX(checkBoxForet.getScaleX() * 1 / zoomFactor);
            checkBoxForet.setScaleY(checkBoxForet.getScaleY() * 1 / zoomFactor);
            checkBoxEau.setScaleX(checkBoxEau.getScaleX() * 1 / zoomFactor);
            checkBoxEau.setScaleY(checkBoxEau.getScaleY() * 1 / zoomFactor);
            checkBoxMall.setScaleX(checkBoxMall.getScaleX() * 1 / zoomFactor);
            checkBoxMall.setScaleY(checkBoxMall.getScaleY() * 1 / zoomFactor);
            checkBoxArret.setScaleX(checkBoxArret.getScaleX() * 1 / zoomFactor);
            checkBoxArret.setScaleY(checkBoxArret.getScaleY() * 1 / zoomFactor);
            affichage.setScaleX(affichage.getScaleX() * 1 / zoomFactor);
            affichage.setScaleY(affichage.getScaleY() * 1 / zoomFactor);
        });

        depart.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> { //afficher info quand la souris survole le point
            if (show) {

                String coordx = String.valueOf(0.1 * depart.getCenterX());
                String coordy = String.valueOf(0.1 * depart.getCenterY());
                Label message = new Label(coordx + " " + coordy);
                message.setTextFill(Color.RED);
                message.setLayoutX(depart.getCenterX() + 20); //positionnement du message par rapport au point
                message.setLayoutY(depart.getCenterY());
                pane.getChildren().add(message);
            } else { //cacher quand elle ne le survole plus
                pane.getChildren().remove(pane.getChildren().size() - 1);
                //get the number of children of pane
            }
        });

        arrivee.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> { // afficher info quand la souris survole le point
            if (show) {
                String coordx = String.valueOf(0.1 * arrivee.getCenterX());
                String coordy = String.valueOf(0.1 * arrivee.getCenterY());
                Label message2 = new Label(coordx + " " + coordy);
                message2.setTextFill(Color.RED);
                message2.setLayoutX(arrivee.getCenterX() + 30);
                message2.setLayoutY(arrivee.getCenterY());
                pane.getChildren().add(message2);
            } else { //cacher quand elle ne le survole plus
                pane.getChildren().remove(pane.getChildren().size() - 1);
            }
        });

        affichage.setOnAction(event -> {
            afficherelements();
        });

        depart.setFill(Color.GREEN); //couleur du cercle
        arrivee.setFill(Color.BLUE);

        // create a scene


        // set the scene
        stage.setScene(scene);
        stage.show();

        // Evenement popup
        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent e) {
                        if (!popup.isShowing()) {

                            //button to hide pop up
                            button.setText("Itinéraire");


                            //New stage to show
                            Stage stage2 = new Stage();

                            //submit button
                            Button submit = new Button("Submit");


                            // creation Textfield
                            TextField villedepart = new TextField("Avignon");
                            TextField ruedepart = new TextField("Rue Thiers");
                            TextField addressedepart = new TextField("5");
                            TextField villearrivee = new TextField("Avignon");
                            TextField ruearrivee = new TextField("Rue Thiers");
                            TextField addressearrive = new TextField("25");




                            //taille du Textfield
                            villedepart.setPrefWidth(60);
                            ruedepart.setPrefWidth(60);
                            addressedepart.setPrefWidth(60);
                            villearrivee.setPrefWidth(60);
                            ruearrivee.setPrefWidth(60);
                            addressearrive.setPrefWidth(60);



                            //creation label
                            Label ledepart = new Label("Ville de Départ");
                            Label ruedep = new Label("Rue de Départ");
                            Label addepart = new Label("Adresse de Départ");
                            Label larrive = new Label("Ville d'Arrivée");
                            Label ruearr = new Label("Rue d'Arrivée");
                            Label adarrive = new Label("Adresse d'Arrivée");

                            //creation gridpane qui permet d'oganiser les TextField et mettre des colonnes
                            GridPane gridpane = new GridPane();

                            // organisation dans le popup
                            GridPane.setConstraints(ledepart, 1, 0); //label ville de depart
                            GridPane.setConstraints(villedepart, 1, 1); //champs de texte ville depart
                            GridPane.setConstraints(ruedep, 1, 2); //label rue depart
                            GridPane.setConstraints(ruedepart, 1, 3); //champs de texte rue depart
                            GridPane.setConstraints(addepart, 1, 4);//label numero depart
                            GridPane.setConstraints(addressedepart, 1, 5); //champs de texte du numero de depart

                            GridPane.setConstraints(larrive, 2, 0); //label ville d'arrivee
                            GridPane.setConstraints(villearrivee, 2, 1); //champs de texte ville arrivée
                            GridPane.setConstraints(ruearr, 2, 2); //label rue arrivée
                            GridPane.setConstraints(ruearrivee, 2, 3); //champs de texte rue arrivée
                            GridPane.setConstraints(adarrive, 2, 4);//label du numero arrivée
                            GridPane.setConstraints(addressearrive, 2, 5); //champs de texte du numero d'arrivée

                            GridPane.setConstraints(submit, 3, 3); //bouton submit
                            gridpane.setHgap(15); //distance entre les attributs
                            gridpane.setVgap(15);
                            gridpane.setAlignment(Pos.TOP_CENTER);


                            //ajout au gridpane
                            gridpane.getChildren().addAll(ledepart, larrive,addepart,villedepart,villearrivee,ruedepart,ruearrivee,ruedep,ruearr, adarrive,addressedepart,addressearrive, submit);

                            // creation d'une scene
                            Scene scene2 = new Scene(gridpane, 350, 350);

                            // association scene à la fenetre

                            stage2.setScene(scene2);
                            //popup.getScene();
                            stage2.show();

                            //action eventsubmit
                            EventHandler<ActionEvent> event2 =
                                    new EventHandler<ActionEvent>() {

                                        public void handle(ActionEvent e) {

                                            //cast string in double
                                            String ville1 = villedepart.getText();
                                            String rue1 = ruedepart.getText();
                                            String adresse1 = addressedepart.getText();
                                            String ville2 = villearrivee.getText();
                                            String rue2 = ruearrivee.getText();
                                            String adresse2 = addressearrive.getText();

                                            URL urldepart = null;
                                            URL urlarrivee = null;
                                            node[][] coordonnees1 = null;
                                            node[][] coordonnees2 = null;
                                            node[][] itineraire = null;
                                            URL url = null;
                                            Double[][] reste = null;
                                            Double[][] contour = null;

                                            //url des coordonnées du départ
                                            try {
                                                urldepart = urldescoordonnees(ville1, rue1, adresse1); //on met les coordonnées du depart sous forme d'url
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                            }
                                            //url des coordonnées de l'arrivée
                                            try {
                                                urlarrivee = urldescoordonnees(ville2, rue2, adresse2); //on met les coordonnées de l'arrivée sous forme d'url
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                            }

                                            //coordonnées du départ
                                            try {
                                                coordonnees1 = recherchecoordonnees(urldepart); //on met les coordonnées du depart dans un node[][]
                                            } catch (ParserConfigurationException ex) {
                                                ex.printStackTrace();
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                            } catch (SAXException ex) {
                                                ex.printStackTrace();
                                            }

                                            //coordonnées de l'arrivée
                                            try {
                                                coordonnees2 = recherchecoordonnees(urlarrivee); //on met les coordonnées de l'arrivée dans un node[][]
                                            } catch (ParserConfigurationException ex) {
                                                ex.printStackTrace();
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                            } catch (SAXException ex) {
                                                ex.printStackTrace();
                                            }
                                                /*System.out.println(coordonnees1[0][0].getLat());
                                                System.out.println(coordonnees1[0][0].getLon());
                                                System.out.println(coordonnees2[0][0].getLat());
                                                System.out.println(coordonnees2[0][0].getLon());*/
                                            /*recup(coordonnees1, coordonnees2); //on met les coordonnées du depart et de l'arrivée dans un tableau de string

                                            //ajout des coordonnées du départ et de l'arrivée dans un tableau de string
                                            try {
                                                putDataInXMLFile();
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                            } catch (ParserConfigurationException ex) {
                                                ex.printStackTrace();
                                            } catch (SAXException ex) {
                                                ex.printStackTrace();
                                            } catch (TransformerException ex) {
                                                ex.printStackTrace();
                                            }

                                            //initialisation de l'itinéraire
                                            try {
                                                itineraire = ajout();
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                            } catch (ParserConfigurationException ex) {
                                                ex.printStackTrace();
                                            } catch (SAXException ex) {
                                                ex.printStackTrace();
                                            } catch (TransformerException ex) {
                                                ex.printStackTrace();
                                            }


                                            Group group6 = new Group();
                                            Pane pane4 = new Pane();


                                            //group4 = createLine(group4, reste);
                                            //group5 = createLine(group5, contour);
                                            Double tab2[][] = getNodesCoordinates(itineraire,tab);
                                            group6 = createLineColorItineraire(group6, tab2);

                                            //af


                                            setdepAr();
                                            System.out.println("dep : " + depart.getCenterY() + " " + depart.getCenterX());
                                            System.out.println("arr : " + arrivee.getCenterY() + " " + arrivee.getCenterX());

                                            Button stop = new Button("Stop");




                                            //suppression de l'itineraire courant
                                            Group copie = group6;
                                            stop.setOnAction(o -> {
                                                Stopitineraire(copie);
                                            });
                                            group6 = copie;

                                            pane4.getChildren().addAll(group,group2);
                                            pane4.getChildren().addAll(polygonseau);
                                            pane4.getChildren().addAll(polygonsforet);
                                            pane4.getChildren().addAll(depart,arrivee,button,group3,group4);
                                            pane4.getChildren().addAll(cercles);
                                            pane4.getChildren().addAll(group6);
                                            pane4.getChildren().addAll(stop);
                                            pane4.getChildren().addAll(affichage);
                                            pane4.getChildren().addAll(checkBoxForet,checkBoxEau,checkBoxMall,checkBoxArret);
                                            borderPane.setCenter(pane4);


                                            depart.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> { //afficher info quand la souris survole le point
                                                if (show) {

                                                    String coordx = String.valueOf(0.1 * depart.getCenterX());
                                                    String coordy = String.valueOf(0.1 * depart.getCenterY());
                                                    Label message = new Label(coordx + " " + coordy);
                                                    message.setTextFill(Color.RED);
                                                    message.setLayoutX(depart.getCenterX() + 20); //positionnement du message par rapport au point
                                                    message.setLayoutY(depart.getCenterY());
                                                    pane4.getChildren().add(message);
                                                } else { //cacher quand elle ne le survole plus
                                                    pane4.getChildren().remove(pane4.getChildren().size() - 1);
                                                }
                                            });

                                            arrivee.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> { // afficher info quand la souris survole le point
                                                if (show) {
                                                    String coordx = String.valueOf(0.1 * arrivee.getCenterX());
                                                    String coordy = String.valueOf(0.1 * arrivee.getCenterY());
                                                    Label message2 = new Label(coordx + " " + coordy);
                                                    message2.setTextFill(Color.RED);
                                                    message2.setLayoutX(arrivee.getCenterX() + 30);
                                                    message2.setLayoutY(arrivee.getCenterY());
                                                    pane4.getChildren().add(message2);
                                                } else { //cacher quand elle ne le survole plus
                                                    pane4.getChildren().remove(pane4.getChildren().size() - 1);
                                                }
                                            });


                                            scene.setRoot(borderPane);
                                            stage2.close();
                                            stage.show();
                                            popup.hide();
                                            stage2.close();
                                        }
                                    };
                            submit.setOnAction(event2);

                        }
                    }
                };

        // when button is pressed
        button.setOnAction(event);


        EventHandler<ActionEvent> event2 =
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent e) {
                        if (!popup2.isShowing()) {

                            //button to hide pop up
                            button2.setText("Changer d'itinéraire");


                            //New stage to show
                            Stage stage2 = new Stage();

                            //submit button
                            Button submit = new Button("Submit");

                            // creation Textfield
                            TextField ville = new TextField();

                            //taille du Textfield
                            ville.setPrefWidth(60);

                            //creation gridpane qui permet d'oganiser les TextField et mettre des colonnes
                            GridPane gridpane = new GridPane();

                            //ajout au gridpane
                            gridpane.getChildren().addAll(ville, submit);

                            // creation d'une scene
                            Scene sc = new Scene(gridpane, 250, 150);

                            // association scene à la fenetre

                            stage2.setScene(sc);
                            stage2.show();

                        }
                    }
                };
    }



    // Main Method
    public static void main(String args[])
    {

        // launch the application
        launch(args);
    }
}*/

