package fr.esiea.glpoo;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Main extends Pieces
{

	Pieces piece = new Pieces();
	String color=piece.run();
	
	Color test=new Colorinfrench().parse(color);

	
	
	public Main() {
       
		JFrame frame=new JFrame("Eternity");
        frame.setMinimumSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(test);
        frame.pack();
        frame.setVisible(true);
        
        
    }
    
    public static void main(String[] args) {
        new Main();
    }
}
