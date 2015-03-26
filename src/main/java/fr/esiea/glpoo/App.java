package fr.esiea.glpoo;

/**
 * Hello world!
 *
 */
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class App extends Colorinfrench
{
	Color couleur=null;
	
	Couleur.couleur;
	
	public App() {
        JFrame frame=new JFrame("Eternity");
        frame.setMinimumSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(couleur);
        frame.pack();
        frame.setVisible(true);
        
        
        
    }
    
    public static void main(String[] args) {
        new App();
    }
}
