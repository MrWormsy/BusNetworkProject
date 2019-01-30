package fr.mrwormsy.busnetwork;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

import fr.mrwormsy.busnetwork.arc.Arc;
import fr.mrwormsy.busnetwork.graph.Graph;

public class BusNetwork {

	private static Connection connection;
	private static ArrayList<Graph> listOfBusLines;
	
	public static void main(String[] args) {
		
		//First we open the SQL Stream
		//connection = BusNewtorkSQL.connect();
		
		//The file
		FileInputStream file = null;
		
		//We init the list of bus stop to empty
		setListOfBusLines(new ArrayList<Graph>());
		
		try {
			//The file
			file = new FileInputStream("2_Piscine-Patinoire_Campus.txt");
			
			//We will use a scanner which is easier to use
			Scanner scanner = new Scanner(file);
			
			Graph graph = Graph.getGraphFromFile(scanner);
			graph.buildArcs();
			
			listOfBusLines.add(graph);
			
			for(Arc arc : graph.getArcListFirstWay()) {
				System.out.print(arc.getBefore().getName() + " (" + arc.getHMFormat() + ") --> " + arc.getAfter().getName() + " *** ");
			}
			
			askTheClient();
			
			//Close the file at the end
			if (file != null) {
				file.close();
			}
	      } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Ask the client where he wants to go and when (optional, if this option is not set we return all the trips with the times related)
	public static void askTheClient() {
		
		//First we need to display where the person wants to go
		System.out.println("\nWhere do you want to go ? ");
		Scanner scanner = new Scanner(System.in);
		System.out.println(scanner.nextLine());
		
		//Then from where
		
		//And ask after with hours and minute with the hh:mm format (if the client sets -1)
		
	}
	
	//Get the SQL connection
	public static Connection getConnection() {
		return connection;
	}

	public static ArrayList<Graph> getListOfBusLines() {
		return listOfBusLines;
	}

	public static void setListOfBusLines(ArrayList<Graph> listOfBusLines) {
		BusNetwork.listOfBusLines = listOfBusLines;
	}
	
	public static void addBusLineToListOfBusLines(Graph BusLines) {
		BusNetwork.listOfBusLines.add(BusLines);
	}

}
