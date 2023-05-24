package dataApi;

import java.util.*;

public class node {

    private double lat;
    private double lon;
    private String nomrues;
    /*private static double maxLat = 44.0132115; //enlever commentaires et rajouter à la boucle for dessous pour que ce soit aligné
    private static double maxLon = 4.9276816;
    private static double minLat = 43.883992;
    private static double minLon = 4.7446762;*/
    private static double maxLat = -1080;
    private static double maxLon = -720;
    private static double minLat = 1080;
    private static double minLon = 720;

    node(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    node(String nomrues) {
        this.nomrues = nomrues;
    }



    public double getLat() {
        return this.lat;
    }

    public void setLat( Double lat) {this.lat = lat;}

    public double getLon() {
        return this.lon;
    }

    public void setLon( Double lon) {this.lon = lon;}

    public String getNomrues() {
        return this.nomrues;
    }


    public static Double[][] getNodesCoordinates(node[][] nodes, double tab[]) { // On récupère les coordonnées à l'échelle dans un tableau
        ArrayList<Double> list = new ArrayList<Double>();
        Double[][] result = new Double[nodes.length][10000]; // On crée un tableau de la taille du nombre de noeuds
        //afficher coordonnées du premier noeud

        /*for (dataApi.node[] node : nodes) {
            for (dataApi.node value : node) {
                if (value != null) {
                    if (value.getLat() < minLat) { // On récupère la valeur minimale de latitude
                        minLat = value.getLat();
                    }
                    if (value.getLat() > maxLat) { // On récupère la valeur maximale de latitude
                        maxLat = value.getLat();
                    }
                    if (value.getLon() < minLon) { // On récupère la valeur minimale de longitude
                        minLon = value.getLon();
                    }
                    if (value.getLon() > maxLon) { // On récupère la valeur maximale de longitude
                        maxLon = value.getLon();
                    }
                }
            }
        }*/
        minLat = tab[0];
        maxLon = tab[3];
        maxLat = tab[1];
        minLon = tab[2];
        System.out.println("minLat = " + minLat + " maxLat = " + maxLat + " minLon = " + minLon + " maxLon = " + maxLon);
        double scaleLat = 1080 / (maxLat - minLat); // On calcule l'échelle de latitude
        System.out.println("scaleLat = " + scaleLat);
        double scaleLon = 1200 / (maxLon - minLon); // On calcule l'échelle de longitude
        System.out.println("scaleLon = " + scaleLon);

        for (int i = 0; i < nodes.length; i++) { // On parcourt le tableau de noeuds
            if (nodes[i] != null) {
                for (int j = 0; j < nodes[i].length; j = j + 1) {
                    if (nodes[i][j] != null) {
                        list.add((nodes[i][j].getLon() - minLon) * scaleLon); // On ajoute la longitude à l'échelle dans la liste
                        list.add(((nodes[i][j].getLat() - minLat) * -scaleLat) + 1000); // On ajoute la latitude à l'échelle dans la liste
                    }
                }
                for (int k = 0; k < list.size(); k++) {
                    result[i][k] = list.get(k); // On ajoute les coordonnées à l'échelle dans le tableau
                    //System.out.println("i =: "+ i + " k =: " + k + " result[" + i + "][" + k + "] = " + result[i][k]);
                }
                list.clear();
            }
        }
        return result; // On retourne le tableau de coordonnées à l'échelle
    }

    Map<node, Double> adjacentNodes = new HashMap<>(); // Les noeuds voisins de ce noeud.avec la distance.

    public List<node> shortestPath = new LinkedList<>(); // predecesseur depuis la source

    public List<node> getShortestPath() {
        return shortestPath;
    }



    public void setShortestPath(List<node> shortestPath) {
        this.shortestPath = shortestPath;
    }



    private double distance = Double.MAX_VALUE; // distances de nuds sont initialises avec Integer.MAX_VALUE pour simuler une distance infinie.
    // la distance depuis la source



    public void addDestination(node destination, double distance) {

        adjacentNodes.put(destination,distance); // add un noeud voisin.


    }






    public Map<node, Double> getAdjacentNodes() {
        return adjacentNodes;
    }



    public void setAdjacentNodes(Map<node, Double> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }



    public double getDistance() {
        return distance;
    }



    public void setDistance(double distance) {
        this.distance = distance;
    }

}
