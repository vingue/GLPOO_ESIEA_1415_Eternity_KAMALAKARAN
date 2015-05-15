package fr.esiea.glpoo;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Comparaison {

	
	
	
	public void compar() {
		Pieces piece = new Pieces();
		String [] debut = piece.run();
		
		 Resultat resultat = new Resultat();
		 String [] fin = resultat.run();
		 int taille=debut.length;
		 int h=0;
		 int j=0;
		 int i=0;
		 int a=0;
		 int id=0;
		 int rot=0;
		int comparaison=0;
		
		while(h<taille/2){
		for(i=a;i<a+2;i++){
	
		for(j=0;j<taille;j++){
		
		
			
			if(id==0){
				
			           if(i%2==0 && j%2==0){
		                                       	if(debut[i].equals(fin[j])){
		                                       		
		                                        id=1;
		 		                                break;
		                                                                 	}
			                                }
			        }
		
			
			j++;}
			
			break;
			}
		
		
	

		if(id==1 && (debut[i+1].equals(fin[j+1]))){
					rot=1;}
				
		
		if(id==1 && rot==1){comparaison=comparaison+1;
	}
		else{comparaison=comparaison-1;
		}
		
		
		id=0;
		rot=0;
		j=0;
		a=a+2;
		System.out.println(a);
	h++;}
		JFrame frame;
		frame=new JFrame("Résutat"); //Initialisation de notre fenetre
        
        
		  
        //Préparation de la fenetre
        frame.setMinimumSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(piece);
        String texte;
        if(comparaison==taille/2){
        	texte="vous avez gagné";
        	
        }
        else{texte="vous avez perdu";}
        JLabel label = new JLabel(texte);
        
       
        frame.add(label);
    
	System.out.println(comparaison);
	}
	     
}
