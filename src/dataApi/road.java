package dataApi;

public class road {
    private node[] node;
    private String[] tag;

    road(node[] nodes, String tags) {
        for (int i = 0; i < nodes.length; i++) {
            this.node[i] = nodes[i];
            this.tag[i] = tags;
        }
    }

    /*public static double[][] getRoadsCoordinates(road[] roads) {
        double[][] result = new double[roads.length][2];
        for (int i = 0; i < roads.length; i++) {
            result[i][0] = roads[i].node.getLat();
            result[i][1] = roads[i].getLon();
        }

        for (int i = 0; i < result.length; i++) {
            System.out.println("lat = " + result[i][0] + " lon = " + result[i][1]);
        }

        return result;
    }
    */

}
