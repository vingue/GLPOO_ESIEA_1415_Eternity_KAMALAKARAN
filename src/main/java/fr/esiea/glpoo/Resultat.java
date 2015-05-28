package fr.esiea.glpoo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
public class Resultat{
 
  public static void main(String[] args) {
 
	Pieces obj = new Pieces();
	obj.run();
 
  }
 
  @SuppressWarnings("finally")
public String[] run() {
 
	String csvFile = "src/test/ressources/fr/esiea/glpoo/compar.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	String tab[];
	tab=new String[32];
	int i=0;

	try {
 
		br = new BufferedReader(new FileReader(csvFile));
	
		while ((line = br.readLine()) != null) {
			
		        // use comma as separator
			String[] country = line.split(cvsSplitBy);
 
			//System.out.println(country[0]+ ""+country[1]+""+country[2]+""+country[3]+""+country[4]+""+country[5]);
tab[i]=country[0];
tab[i+1]=country[1];

	
i=i+2;
}
		
	
	
	
	}catch (FileNotFoundException e) {
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
	return tab;
 
}
	}
}
