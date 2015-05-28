package fr.esiea.glpoo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



//Class permettant de crÃƒÂ©er les piÃƒÂ¨ces et de les mettres dans le dÃƒÂ©pÃƒÂ´t de piÃƒÂ¨ce du jeu et de les dÃƒÂ©placer/rotater
//Class permettant de crÃ©er les piÃ¨ces et de les mettres dans le dÃ©pÃ´t de piÃ¨ce du jeu et de les dÃ©placer/rotater
public class Pieces extends JPanel{


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int num_j=1; //Variable permettant de choisir le jeu(puzzle) choisit par le joueur
	private String name_p;
	private int rota;
	private PiecesMove listener;
	

//Constructeur de la piece 
	public Pieces() {
		
      setLayout(null); // On supprime le layout manager

      listener = new PiecesMove(this); //Permet de creer le listener permettant de bouger la piece
    
      for(int num=1; num<17; num++) { //Creation des 16 pieces du jeu grace a la methode createPieces
          add(createPieces(num,num_j));
          
      }

      //Ajout des listener permettant de rotater et dÃ©placer/dÃ©poser une piÃ¨ce
      addKeyListener(listener); 
      addMouseListener(listener);
      addMouseMotionListener(listener);
      name_p=listener.getPieces_Name(); //VINCEIN
      //Pour le KeyListener 
      setFocusable(true);
      requestFocus();
      
      
  }
	
	//Getter pour le nom
	public String recupName() {
		name_p=listener.getPieces_Name();
		return name_p;
	}
	
	//Getter pour la rotation
	public int recupRot() {
		rota=listener.getVal_Rotation();
		return rota;
	}
	
	//Getter pour le jeu de piece
	public int recupJeu() {
		return num_j;
	}
	public void memodepo(boolean deposeer){
		listener.setDeposer(deposeer);
	}
	
	
	//Methode permettant de crÃ©er les piÃ¨ces du jeu
	  private JLabel createPieces(int num, int num_j) {
		  
		    //On rÃ©cupÃ¨re l'image de la piÃ¨ce selon son numÃ©ro, ainsi que le numÃ©ro du jeu et on l'ajoute au Label
		   ImageIcon image = new ImageIcon("src/main/ressources/jeu_"+num_j+"/piece_"+num+".png");
		   JLabel piece=new JLabel(image);
	        
			  //Variable random permettant de mettre au hasard les piÃ¨ces dans le dÃ©pot
		      int lower = 1;
		      int higher = 5;

		      int random = (int)(Math.random() * (higher-lower)) + lower;
		       
		        
	     //Valeur de gauche haut (505,283) dans le dÃ©pot des piÃ¨ces
			if(random==1)
			 piece.setLocation(502,283 );
		//Valeur de droite haut (594,283)
			if(random==2)
			 piece.setLocation(594,283 );
		//Valeur de droite bas (594,374)
			if(random==3)
			 piece.setLocation(594,374 );
		//Valeur de gauche bas (505 ,374)
			if(random==4)
			 piece.setLocation(505,374 );
		
			//On affecte la taille de la piÃ¨ce et son nom pour l'identifier
	        piece.setSize(80,80); 
	        piece.setName("piece_"+num); 
	        
	      
	        return piece;
	    }
	 
	  
	  
	  
	  
	  
	  
	  
	  //Debut de la class PiecesMove permettant de crÃ©er un listener permettant de faire les mouvements de rotation et dÃ©placement d'une piÃ¨ce
	    private static class PiecesMove extends MouseAdapter implements KeyListener{
	    	
	    	
			private boolean move; //Variable permettant de savoir si la piÃ¨ce est dÃ©placer
	        private int relx;
	        private JLabel component;
	        private int rely;
	        private Container container;
			private int Val_Rotation=0; //La valeur de rotation est nulle au dÃ©part
	        private String Pieces_Name;
	        boolean deposer;
	        public PiecesMove(Container container) {
	            this.container=container;
	        }
	        
	       

	 
	        @Override //Listener permettant de dÃ©poser une piÃ¨ce lors de son dÃ©palcement et de dÃ©placer une piÃ¨ce
	        public void mousePressed(MouseEvent e) {
	        	
	        	//Si on a un mouvement
	            if ( move ) {
	            	
					if(getDeposer()==true){
	                move=false; //On le met en arrÃªt
	                component.setBorder(null); //Et on supprime la bordure noire qui indique que nous dÃ©plaÃ§ons une piÃ¨ce
	              
	                //setPieces_Name(component.getName());//On r�cup�re le nom du component qu'on d�pose
	                
	             component.setLocation(1000, 1000); //On le supprime dans la corbeille ( changer de place)
	             for(int ro=0;ro<4-Val_Rotation;ro++){
	             rotate(component);
	             }
	             //   component=null; //On oublie la component
	            	}
					else{
						JOptionPane.showMessageDialog(null,"La pi�ce n'a pas �t� plac� sur le plateau !!");
			    		
					}
	               setDeposer(false);
	            }
	            else { // Si il n'y a pas de mouvement
	            	
	                component = getPiece(e.getX(),e.getY()); //On mÃ©morise la piÃ¨ce Ã  dÃ©placer avec la mÃ©thode getPiece
	               
	                if ( component!=null ) { 
	                	 setPieces_Name(component.getName());
	 	                setRota();
	                    container.setComponentZOrder(component,0); //Place le composant le plus haut possible
	                    relx = e.getX()-component.getX(); //On mÃ©morise la position relative
	                    rely = e.getY()-component.getY(); //On mÃ©morise la position relative
	                    move=true; //Indication du dÃ©marrage du mouvement
	                    component.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //On indique la piÃ¨ce sÃ©lectionnÃ©e par une bordure noire
	                   
	                }
	            }
	        }
	 
	        //MÃ©thode permettant de dÃ©finir la piÃ¨ce que nous avons cliquÃ© dessus
	        private JLabel getPiece(int x, int y) {
	        	
	            //On recherche la premiÃ¨re piÃ¨ce qu'on clique dessus pour la dÃ©placer
	            for(Component component : container.getComponents()) {
	            	// Il faut que getName soit diffÃ©rent de null puisque seul les piÃ¨ces ont un nom comme composant, et on veut seulement dÃ©placer les piÃ¨ces
	                if ( component instanceof JLabel && component.getBounds().contains(x, y) && component.getName()!=null ) { 
	                	return (JLabel)component; //On retourne la piÃ¨ce qu'on a cliquÃ© dessus
	                }
	            }
	            return null;
	        }
	 
	        @Override
	        public void mouseMoved(MouseEvent e) {
	            if ( move ) {
	                //Si on dÃ©place le component (la piÃ¨ce), on change sa position
	                component.setLocation(e.getX()-relx, e.getY()-rely);
	            }
	        }
	        
	        @Override //Listener KeyPressed pour la Rotation
	        public void keyPressed(KeyEvent evt) {
				
				  if(evt.getKeyCode()==82){ //VÃ©rification de la touche R pour la rotation
					  
					  if(move==true){ //Si on a la piÃ¨ce en mouvement
				 
						  	rotate(component);//Alors on peut effectuer une rotation
				
				
						  	Val_Rotation++; //On augmente la valeur du nombre de rotation
						  	if(Val_Rotation==4)//Si on a effectuÃ© un tour complet
						  		Val_Rotation=0;//On remet la valeur Ã  zÃ©ro
						  		
					  }
				  }
				}
	        	@Override
				public void keyTyped(KeyEvent evt) {
	        		 
	        	}
	        	@Override
				public void keyReleased(KeyEvent evt) { 
	        	
	        	}

	        //MÃ©thode permettant d'effectuer une rotation d'un JLabel
	        public void rotate(JLabel panel){
	        	
	        	ImageIcon icon = (ImageIcon) panel.getIcon();
	        	
	        	int w = icon.getIconWidth();
		        int h = icon.getIconHeight();
		        int type = BufferedImage.TYPE_INT_RGB;  // other options, see api
		        BufferedImage image = new BufferedImage(h, w, type);
		        Graphics2D g2 = image.createGraphics();
		        double x = (h - w)/2.0;
		        double y = (w - h)/2.0;
		        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		        at.rotate(Math.toRadians(90), w/2.0, h/2.0);
		        g2.drawImage(icon.getImage(), at, panel);
		        g2.dispose();
		        icon = new ImageIcon(image);
		        panel.setIcon(icon);
		        

      }

	        //Getter pour la rotation
	        public int getVal_Rotation() {
	        	return Val_Rotation;
	        }
	        
	      //Getter pour le nom de la piece
			public String getPieces_Name() {
				return Pieces_Name;
			}
			public boolean getDeposer(){
				return deposer;
			}

			//Remettre la rotation a 0
			public void setRota() {
				Val_Rotation=0;
			}
			public void setDeposer(boolean deposeer) {
				deposer = deposeer;
			}
			
			//Setter pour le nom de la piece
			public void setPieces_Name(String pieces_Name) {
				Pieces_Name = pieces_Name;
			}
	     
	       
} //Fin de la class PiecesMove
	    
 
  public static void main(String[] args) {
 
	Pieces obj = new Pieces();
	obj.run();
 
  }
 
  @SuppressWarnings("finally")
public String[] run() {
 
	String csvFile = "src/test/ressources/fr/esiea/glpoo/test.csv";
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
		/*
		for(int j=0;j<8;j++){
	System.out.println(tab[j]+" ");}*/
	
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

	public String getName_p() {
		return name_p;
	}




	public void setName_p(String name_p) {
		this.name_p = name_p;
	}

}//Fin de la class PiÃ¨ces
