package dataApi;
// Java program to create a popup and
// add it to the stage

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;


public class fx extends Application {

    public static Double[][] reste;
    public static Double[][] contour;
    public static URL url;

    public Group createLine(Group group, Double[][] coordonnees) {
        for (Double[] coordonnee : coordonnees) {
            for (int j = 0; j < coordonnee.length; j = j + 2) {
                if (coordonnee[j] != null && coordonnee[j + 1] != null && coordonnee[j + 2] != null && coordonnee[j + 3] != null) {
                    //create line
                    Line Line = new Line(coordonnee[j], coordonnee[j + 1], coordonnee[j + 2], coordonnee[j + 3]);
                    group.getChildren().add(Line);
                }
            }
        }
        return group;
    }

    public void setUrl(String ville) throws IOException
    {
        url = api.getDataFromApi(ville);
    }

    public void setTab(URL url) throws IOException, ParserConfigurationException, SAXException
    {
         //reste = node.getNodesCoordinates(api.getNodesDataWay(url));
         //contour = node.getNodesCoordinates(api.getNodesDataMember(url));
    }

    // launch the application
    public void start(Stage stage) throws IOException, ParserConfigurationException, SAXException {
        URL url = api.getDataFromApi("Avignon"); // appel de l'api ici, pour ne faire qu'un appel

        //Double[][] reste = node.getNodesCoordinates(api.getNodesDataWay(url)); // buildings & routes
        //Double[][] contour = node.getNodesCoordinates(api.getNodesDataMember(url)); // contour ville

        BorderPane borderPane = new BorderPane();

        Pane pane = new Pane();

        // Contour
        Group group = new Group();
        group = createLine(group, contour);

        // Reste
        Group group1 = new Group();
        group1 = createLine(group1, reste);

        //Boutton
        Button button = new Button("changer de ville");

        pane.getChildren().addAll(group,group1,button);

        borderPane.setCenter(pane);

        // create a scene
        Scene scene = new Scene(borderPane, 1080, 720);

        //unzoom avec la molette
        scene.setOnScroll(event -> {
            double zoomFactor = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0) {
                zoomFactor = 2.0 - zoomFactor;
            }
            Scale scale = new Scale(zoomFactor, zoomFactor, event.getSceneX(), event.getSceneY());
            pane.getTransforms().add(scale);
        });

        // set the scene
        stage.setScene(scene);
        stage.show();

    }
    // Main Method
    public static void main(String args[])
    {

        // launch the application
        launch(args);
    }
}

