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
        
        addKeyListener(listener); 
        
        addMouseListener(listener);
        addMouseMotionListener(listener);
        setFocusable(true);
        requestFocus();
        
        
    }
	
	
   
	
	//Methode pour créer la piece
	  private JLabel createPieces(int num) {
		  
	        ImageIcon image = new ImageIcon("src/main/ressources/piece_"+num+".jpg");
			JLabel piece=new JLabel(image);
	        //Creation du JLabel ( la pièce) avec l'image dedans
		      int lower = 1;
		        int higher = 5;

		        int random = (int)(Math.random() * (higher-lower)) + lower;
		        System.out.println(random);
	     //Valeur de gauche haut (505,283)
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
		
		// position vers l'endroit des pieces
			
			 
	       piece.setSize(80,80); //Taille de la piece
	        piece.setName("piece_"+num); //Nom de la piece
	        
	        

	       
	        return piece;
	    }
	 
	  
	  //Class permettant de créer un sorte de listener qui permet de bouger la piece
	  //Debut de la class piecesmove//
	    private static class PiecesMove extends MouseAdapter implements KeyListener{
	    	
	    	
	  
	        private boolean move;
	        private int relx;
	        private JLabel component;
	        private int rely;
	        private Container container;
	        
	 
	        public PiecesMove(Container container) {
	            this.container=container;
	        }
	        
	        @Override
	        public void keyPressed(KeyEvent evt) {
				
				  if(evt.getKeyCode()==82){
					  if(move==true){
				 
				 rotate(component);
				
					  }
				  }
				}
	        	@Override
				public void keyTyped(KeyEvent evt) {
	        		 
	        	}
	        	@Override
				public void keyReleased(KeyEvent evt) { 
	        	
	        	}



	 
	        @Override
	        public void mousePressed(MouseEvent e) {
	            if ( move ) {
	                move=false; // arrêt du mouvement
	                component.setBorder(null); // on  supprime la bordure noire
	              //METTRE ICI POUR RECUPERER LE NOM DE LA PIECE DEPOSER// 
	               
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
	 
	        private JLabel getPiece(int x, int y) {
	        	
	            // on recherche le premier composant qui correspond aux coordonnées de la souris
	            for(Component component : container.getComponents()) {
	            	// Il faut que getName soit différent de null puisque seul les pièces ont un nom comme composant, et on veut seulement déplacer les pièces
	                if ( component instanceof JLabel && component.getBounds().contains(x, y) && component.getName()!=null ) { 
	                   System.out.println(component.getName());
	                	return (JLabel)component;
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
