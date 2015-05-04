package fr.esiea.glpoo;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main extends Pieces
{

	Pieces piece = new Pieces();

	String couleur=piece.run();
	
	Colorinfrench color =new Colorinfrench();
	
	Color test=color.parse(couleur);

	
	public Main() {
     
		
		JFrame frame=new JFrame("Eternity");


    
  
        frame.setMinimumSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setBackground(test);

     
        
        JPanel buttonPanel=new JPanel();
     

        

        frame.pack();
       
        frame.setVisible(true);
        
       

//System.out.println(couleur);
    }
    
    public static void main(String[] args) {
        new Main();
    }
}
