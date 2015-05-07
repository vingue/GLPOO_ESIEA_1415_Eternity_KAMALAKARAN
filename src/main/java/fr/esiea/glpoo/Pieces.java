package fr.esiea.glpoo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Pieces {

	
	public static void main(String[] args) {
		 
		Pieces obj = new Pieces();
		obj.run();
	 
	  }
	
	public String run() {
		 
		String csvFile = "src/test/ressources/faces-01.csv";
		BufferedReader br = null;
		String line = ";";
		String cvsSplitBy = ",";
	 
		try {
	 
			
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 
				// use comma as separator
				String[] couleur = line.split(cvsSplitBy);
	            
			
	 return couleur[0];
			}
	 
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		
		return null;
	  }
	 
}
