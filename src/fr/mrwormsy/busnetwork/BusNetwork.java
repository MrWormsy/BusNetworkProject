package fr.mrwormsy.busnetwork;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

import fr.mrwormsy.busnetwork.Graph.Graph;

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
			
			Graph.getGraphFromFile(scanner);
			
			//Close the file at the end
			if (file != null) {
				file.close();
			}
	      } catch (IOException e) {
			e.printStackTrace();
		}
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
