package fr.esiea.glpoo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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


    
<<<<<<< HEAD
  
=======
	Color couleur=Color.cyan;
	
    public Main() {
<<<<<<< HEAD
        JFrame frame=new JFrame("On est 12!");
=======
        JFrame frame=new JFrame("On est les + mimi!");
>>>>>>> branch 'master' of https://github.com/vingue/Eternity.git


>>>>>>> branch 'master' of https://github.com/vingue/Eternity.git
        frame.setMinimumSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
<<<<<<< HEAD

        frame.getContentPane().setBackground(test);

     
        
        JPanel buttonPanel=new JPanel();
     

        

=======
        frame.getContentPane().setBackground(couleur);
        
        JPanel buttonPanel=new JPanel();
        JPanel aidePanel=new JPanel();
        JButton resetButton = new JButton("Restart");
        JButton undoButton = new JButton("Undo");
        JButton rotateButton = new JButton("Rotate");
        JButton rotateButtonn = new JButton("VincentOnAReussi");
        buttonPanel.add(resetButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(rotateButton);
        buttonPanel.add(rotateButtonn);
        aidePanel.setLayout(new BorderLayout());
        aidePanel.setBorder(BorderFactory.createTitledBorder("Aide"));
        aidePanel.add(buttonPanel, BorderLayout.EAST);
        frame.add(aidePanel);
        
>>>>>>> branch 'master' of https://github.com/vingue/Eternity.git
        frame.pack();
       
        frame.setVisible(true);
        
       

//System.out.println(couleur);
    }
    
    public static void main(String[] args) {
        new Main();
    }
}
