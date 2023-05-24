package dataApi;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
	//query on overpass turbo to get nodes between two addresses



	//private Set<Node> nodes = new HashSet<>(); // l'ensemble Des noeuds de graph
	public ArrayList<node> nodes = new ArrayList<>();
	public node source;
	public node Destination;
	public void addNode(node nodeA) {
        nodes.add(nodeA);
    }

	public ArrayList<node> getArraylist()
	{
		return this.nodes;
	}

	private static Double toRad(Double value) {
		 return value * Math.PI / 180;
	}
	public static double calculerLadistance(Double lat1,Double lon1,Double lat2, Double lon2) {
		 final int R = 6371;
		 Double latDistance = toRad(lat2-lat1);
		 Double lonDistance = toRad(lon2-lon1);
		 Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
		 Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * 
		 Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		 Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		 Double distance = R * c;
		 return distance;
		
	}


	public static node plusProcheVoisin(node source, node Destination) throws IOException {
		File file = new File("C:\\Users\\ismail\\Desktop\\projet-map-groupe-8-derniereversion4\\projet-map-groupe-8-PierreBlondeau\\xml\\itineraire.fxml");
		FileInputStream fileStream = new FileInputStream(file);
		InputStreamReader input = new InputStreamReader(fileStream);
		BufferedReader reader = new BufferedReader(input);
		//System.out.println(source.getLat()+" "+source.getLon()+" "+Destination.getLat()+" "+Destination.getLon());
		String line;
		Graph graph = new Graph();
		Double distanceFromsource = Graph.calculerLadistance(source.getLat(),source.getLon(),Destination.getLat(),Destination.getLon());

		double distancePrecedent = Double.MAX_VALUE;
		double distanceCurrent = 0.0;
		double distanceCurrentFromDesti;
		String id = null;
		String maLat = null;
		String maLon = null;
		boolean passage = false;

		while ((line = reader.readLine()) != null) {
			if(line.contains("<node")) {
				String [] pass2 = line.split("id=\"");
				String idNode1 = pass2[1];
				String[] idNode = idNode1.split("\" lat=\"");

				String [] latitude = idNode[1].split("\" lon=\"");
				//System.out.print(latitude[0] +" ");//latitude
				String [] longitude = latitude[1].split("\"");
				//System.out.println(longitude[0] +" "); // longitude
				distanceCurrent = Graph.calculerLadistance(source.getLat(),source.getLon(),Double.parseDouble(latitude[0]),Double.parseDouble(longitude[0]));
				distanceCurrentFromDesti = Graph.calculerLadistance(Destination.getLat(),Destination.getLon(),Double.parseDouble(latitude[0]),Double.parseDouble(longitude[0]));
				if(distanceCurrent != 0.0 && distanceCurrent < distancePrecedent && distanceCurrentFromDesti < distanceFromsource ) {
					distancePrecedent = distanceCurrent;
					id = idNode[0];
					maLat = latitude[0];
					maLon = longitude[0];
					passage = true;
					//System.out.println("id du if: "+id+ " idNode[0] "+idNode[0]+ " line " + line);
				}
				//System.out.println("id du while: "+id + " idNode[0] "+idNode[0] + " line " + line);
			}
		}
		reader.close();
		System.out.println("id de fin : "+id+" lat : "+maLat+" lon : "+maLon);
		node plusProcheVoisin = new node(Double.parseDouble(maLat),Double.parseDouble(maLon));
		return plusProcheVoisin;

	}

	public Graph initialise(Double nodelat, Double nodelon, Double node2lat, Double node2lon) throws IOException, ParserConfigurationException, SAXException, TransformerException {

		this.source = new node(nodelat,nodelon);
		this.Destination = new node(node2lat, node2lon);
		node plusProche = source;
		int i = 0;
		while(plusProche.getLat() != Destination.getLat() && plusProche.getLat() != Destination.getLon() && i< 10 ) {
			System.out.println("lat : "+plusProche.getLat()+" lon : "+plusProche.getLon());
			plusProche =  plusProcheVoisin(source,Destination);
			LinkedList<node> shortestPath = new LinkedList<>();
			shortestPath.add(source);
			plusProche.setShortestPath(shortestPath);
			source = plusProche;
			i++;
		}

		LinkedList<node> shortestPath = new LinkedList<>();
		shortestPath.add(plusProche);
		Destination.setShortestPath(shortestPath);
		this.source = new node(nodelat,nodelon);
		Graph graph = new Graph();
		graph.source = this.source;
		graph.Destination = this.Destination;
		graph.addNode(Destination);
		while(Destination.shortestPath.get(0).getLon() != source.getLon()) {
			graph.addNode(Destination);
			Destination = Destination.shortestPath.get(0);
		}



		return graph;
	}


	public static void main (String[] arg) throws IOException, ParserConfigurationException, SAXException, TransformerException
{
		
 
        Graph a1 = new Graph();
        Graph a2 = new Graph();
        
        //a2 = a1.initialise();
        
        System.out.println(a2.nodes.size());
        
        
        
        int i = 0;
        while(i != a2.nodes.size()-1) {
        	System.out.println(a2.nodes.get(i).getLat() + " || " + a2.nodes.get(i).getLon() + "||" + i);
        	i++;
        }

	}
}
