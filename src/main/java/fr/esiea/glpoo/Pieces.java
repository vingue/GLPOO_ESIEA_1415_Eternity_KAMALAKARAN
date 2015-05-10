package fr.esiea.glpoo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class Pieces extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//Constructeur de la piece 
	public Pieces() {
		
        setLayout(null); // on supprime le layout manager
 
        PiecesMove listener = new PiecesMove(this); //Permet de créer le listener permettant de bouger la piece
       for(int num=1; num<17; num++) { //Créer les 16 pièces du jeu
            add(createPieces(num));
        }
        addMouseListener(listener);
        addMouseMotionListener(listener);
        
        
    }
	
	//Methode pour créer la piece
	  private JComponent createPieces(int num) {
	        
			JLabel piece=new JLabel(new ImageIcon("src/main/ressources/piece_"+num+".jpg"));
	        //Creation du JLabel ( la pièce) avec l'image dedans
	       
	     
	        piece.setLocation(500,300 ); // position vers l'endroit des pieces
	       piece.setSize(120,120); //Taille de la piece
	        piece.setName("piece_"+num); //Nom de la piece
	     
	        return piece;
	    }
	 
	  
	  //Class permettant de créer un sorte de listener qui permet de bouger la piece
	  //Debut de la class piecesmove//
	    private static class PiecesMove extends MouseAdapter {
	 
	        private boolean move;
	        private int relx;
	        private JComponent component;
	        private int rely;
	        private Container container;
	 
	        public PiecesMove(Container container) {
	            this.container=container;
	        }
	 
	        @Override
	        public void mousePressed(MouseEvent e) {
	            if ( move ) {
	                move=false; // arrêt du mouvement
	                component.setBorder(null); // on  supprime la bordure noire
	                component=null;
	            }
	            else {
	                component = getPiece(e.getX(),e.getY()); // on mémorise le composant en déplacement
	                if ( component!=null ) {
	                    container.setComponentZOrder(component,0); // place le composant le plus haut possible
	                    relx = e.getX()-component.getX(); // on mémorise la position relative
	                    rely = e.getY()-component.getY(); // on mémorise la position relative
	                    move=true; // démarrage du mouvement
	                    component.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // on indique le composant sélectionné par une bordure noire
	                }
	            }
	        }
	 
	        private JComponent getPiece(int x, int y) {
	        	
	            // on recherche le premier composant qui correspond aux coordonnées de la souris
	            for(Component component : container.getComponents()) {
	            	// Il faut que getName soit différent de null puisque seul les pièces ont un nom comme composant, et on veut seulement déplacer les pièces
	                if ( component instanceof JComponent && component.getBounds().contains(x, y) && component.getName()!=null ) { 
	                   System.out.println(component.getName());
	                	return (JComponent)component;
	                }
	            }
	            return null;
	        }
	 
	        @Override
	        public void mouseMoved(MouseEvent e) {
	            if ( move ) {
	                // si on déplace
	                component.setLocation(e.getX()-relx, e.getY()-rely);
	            }
	        }
	 
	    } //Fin de la class PiecesMove
	    
	    
	    
	    
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
	 
	 
}
