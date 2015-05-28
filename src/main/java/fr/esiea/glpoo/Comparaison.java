package fr.esiea.glpoo;



public class Comparaison {

	
	
	
	public String compar() {
		Pieces piece = new Pieces();
		String [] debut = piece.run();
		String texte="";
		
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
		int nombre=0;
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
		
	h++;}
		 
		 if(comparaison==taille/2){ texte="C'est gagnée";}
		 else{ texte ="C'est perdu";}
	return texte;
	}
	     
}
