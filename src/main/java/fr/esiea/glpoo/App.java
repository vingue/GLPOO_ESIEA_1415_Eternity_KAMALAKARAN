package fr.esiea.glpoo;

/**
 * Hello world!
 *
 */
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class App
{
	Color couleur=null;
	
	
	public App() {
        JFrame frame=new JFrame("Eternity");
        frame.setMinimumSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(couleur);
        frame.pack();
        frame.setVisible(true);
        
        
        
    }
    
    public static void main(String[] args) {
        new App();
    }
}
