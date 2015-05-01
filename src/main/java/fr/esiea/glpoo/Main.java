package fr.esiea.glpoo;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
    
    public Main() {
        JFrame frame=new JFrame("Coucou");
        frame.setMinimumSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
       
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Main();
    }
    
}
