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
import javax.swing.JPanel;

//Class permettant de créer les pièces et de les mettres dans le dépôt de pièce du jeu et de les déplacer/rotater
public class Pieces extends JPanel{


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//A VOIR COMMENT FAIRE VINCENT
	private int num_j=1; //Variable permettant de choisir le jeu(puzzle) choisit par le joueur
	private String name_p;
	private int rota;
	private PiecesMove listener;


//Constructeur de la piece 
	public Pieces() {
		
        setLayout(null); // On supprime le layout manager
 
        listener = new PiecesMove(this); //Permet de créer le listener permettant de bouger la piece
      
        for(int num=1; num<17; num++) { //Création des 16 pièces du jeu grâce à la méthode createPieces
            add(createPieces(num,num_j));
        }
  
        //Ajout des listener permettant de rotater et déplacer/déposer une pièce
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
	
	//Methode permettant de créer les pièces du jeu
	  private JLabel createPieces(int num, int num_j) {
		  
		    //On récupère l'image de la pièce selon son numéro, ainsi que le numéro du jeu et on l'ajoute au Label
	        ImageIcon image = new ImageIcon((new ImageIcon("src/main/ressources/jeu_"+num_j+"/piece_"+num+".jpg").getImage()).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
			JLabel piece=new JLabel(image);
	        
			  //Variable random permettant de mettre au hasard les pièces dans le dépot
		      int lower = 1;
		      int higher = 5;

		      int random = (int)(Math.random() * (higher-lower)) + lower;
		       
		        
	     //Valeur de gauche haut (505,283) dans le dépot des pièces
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
		
			//On affecte la taille de la pièce et son nom pour l'identifier
	        piece.setSize(80,80); 
	        piece.setName("piece_"+num); 
	        
	       
	        return piece;
	    }
	 
	  
	  
	  
	  
	  
	  
	  
	  //Debut de la class PiecesMove permettant de créer un listener permettant de faire les mouvements de rotation et déplacement d'une pièce
	    private static class PiecesMove extends MouseAdapter implements KeyListener{
	    	
	    	
			private boolean move; //Variable permettant de savoir si la pièce est déplacer
	        private int relx;
	        private JLabel component;
	        private int rely;
	        private Container container;
			private int Val_Rotation=0; //La valeur de rotation est nulle au départ
	        private String Pieces_Name;
			
	        public PiecesMove(Container container) {
	            this.container=container;
	        }
	        
	       

	 
	        @Override //Listener permettant de déposer une pièce lors de son dépalcement et de déplacer une pièce
	        public void mousePressed(MouseEvent e) {
	        	
	        	//Si on a un mouvement
	            if ( move ) {
	                move=false; //On le met en arrêt
	                component.setBorder(null); //Et on supprime la bordure noire qui indique que nous déplaçons une pièce
	              //METTRE ICI POUR RECUPERER LE NOM DE LA PIECE DEPOSER POUR VINCENT// 
	                setPieces_Name(component.getName());//VINCEIN
	             
	                component=null; //On oublie la component
	                
	               
	            }
	            else { // Si il n'y a pas de mouvement
	            	
	                component = getPiece(e.getX(),e.getY()); //On mémorise la pièce à déplacer avec la méthode getPiece
	                setPieces_Name(component.getName());
	                setRota();
	                if ( component!=null ) { 
	                	
	                    container.setComponentZOrder(component,0); //Place le composant le plus haut possible
	                    relx = e.getX()-component.getX(); //On mémorise la position relative
	                    rely = e.getY()-component.getY(); //On mémorise la position relative
	                    move=true; //Indication du démarrage du mouvement
	                    component.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //On indique la pièce sélectionnée par une bordure noire
	                   
	                }
	            }
	        }
	 
	        //Méthode permettant de définir la pièce que nous avons cliqué dessus
	        private JLabel getPiece(int x, int y) {
	        	
	            //On recherche la première pièce qu'on clique dessus pour la déplacer
	            for(Component component : container.getComponents()) {
	            	// Il faut que getName soit différent de null puisque seul les pièces ont un nom comme composant, et on veut seulement déplacer les pièces
	                if ( component instanceof JLabel && component.getBounds().contains(x, y) && component.getName()!=null ) { 
	                	return (JLabel)component; //On retourne la pièce qu'on a cliqué dessus
	                }
	            }
	            return null;
	        }
	 
	        @Override
	        public void mouseMoved(MouseEvent e) {
	            if ( move ) {
	                //Si on déplace le component (la pièce), on change sa position
	                component.setLocation(e.getX()-relx, e.getY()-rely);
	            }
	        }
	        
	        @Override //Listener KeyPressed pour la Rotation
	        public void keyPressed(KeyEvent evt) {
				
				  if(evt.getKeyCode()==82){ //Vérification de la touche R pour la rotation
					  
					  if(move==true){ //Si on a la pièce en mouvement
				 
						  	rotate(component);//Alors on peut effectuer une rotation
				
				 //Pour nesrine, c'est pour la valeur de rotation
						  	Val_Rotation++; //On augmente la valeur du nombre de rotation
						  	if(Val_Rotation==4)//Si on a effectué un tour complet
						  		Val_Rotation=0;//On remet la valeur à zéro
				
					  }
				  }
				}
	        	@Override
				public void keyTyped(KeyEvent evt) {
	        		 
	        	}
	        	@Override
				public void keyReleased(KeyEvent evt) { 
	        	
	        	}

	        //Méthode permettant d'effectuer une rotation d'un JLabel
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

			//Remettre la rotation a 0
			public void setRota() {
				Val_Rotation=0;
			}

			//Setter pour le nom de la piece
			public void setPieces_Name(String pieces_Name) {
				Pieces_Name = pieces_Name;
			}
	     
	       
} //Fin de la class PiecesMove
	    
	   
	    
	    //NESRINE FAUT QUE TU COMMENTES
	    //Methode run permettant de la class Pieces ..
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
		            
				
		 return couleur[1];
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




		public String getName_p() {//VINCEIN
			return name_p;
		}




		public void setName_p(String name_p) {
			this.name_p = name_p;
		}

}//Fin de la class Pièces

