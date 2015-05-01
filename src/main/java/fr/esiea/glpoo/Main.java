package fr.esiea.glpoo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    
	Color couleur=Color.cyan;
	
    public Main() {
        JFrame frame=new JFrame("On est les + mieux!");


        frame.setMinimumSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        
        frame.pack();
       
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Main();
    }
    
}
