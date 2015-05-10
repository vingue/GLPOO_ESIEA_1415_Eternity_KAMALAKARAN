package fr.esiea.glpoo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
 



import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class MoveComponents extends JPanel {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MoveComponents() {
 
        setLayout(null); // on supprime le layout manager
 
        ComponentMove listener = new ComponentMove(this);
        
       //     add(createComponent());
        
        addMouseListener(listener);
        addMouseMotionListener(listener);
 
    }
 
//    private final static Color[] COLORS= {Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.PINK, Color.WHITE, Color.BLACK};
 
 /*   private JComponent createComponent() {
        JPanel component=new JPanel(); // ici on peut faire n'importe quel JComponent, JLabel, par exemple
        component.setLocation(150,200 ); // position aléatoire
        //component.setSize(10+(int)(Math.random()*100), 10+(int)(Math.random()*100)); // taille aléatoire
        //component.setBackground(COLORS[(int)(Math.random()*COLORS.length)]); // couleur aléatoire

          File file5=new File("src/main/ressources/testpiece.png");
        JLabel lab5 = new JLabel(new ImageIcon(file5.getPath()));
       // JLabel i = new JLabel( new ImageIcon( "src/main/ressources/testpiece.png"));
        
        component.add(lab5);
        component.setEnabled(false); // les composants ne doivent pas intercepter la souris
        return component;
    }
 */
    private static class ComponentMove extends MouseAdapter {
 
        private boolean move;
        private int relx;
        private JComponent component;
        private int rely;
        private Container container;
 
        public ComponentMove(Container container) {
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
            	
                
            		component = getComponent(e.getX(),e.getY()); // on mémorise le composant en déplacement
                if ( component!=null ) {
                    container.setComponentZOrder(component,0); // place le composant le plus haut possible
                    relx = e.getX()-component.getX(); // on mémorise la position relative
                    rely = e.getY()-component.getY(); // on mémorise la position relative
                    move=true; // démarrage du mouvement
                    component.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // on indique le composant sélectionné par une bordure noire
                }
            }
        }
 
        private JComponent getComponent(int x, int y) {
            // on recherche le premier composant qui correspond aux coordonnées de la souris
            for(Component component : container.getComponents()) {
                if ( component instanceof JComponent && component.getBounds().contains(x, y) ) {
                   System.out.println(""+component.hashCode());
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
 
    }
 
   
 
}