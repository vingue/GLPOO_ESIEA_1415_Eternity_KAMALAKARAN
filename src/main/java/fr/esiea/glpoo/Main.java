package fr.esiea.glpoo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main implements ActionListener{
    
	Pieces piece = new Pieces();
	 MoveComponents mvcp = new MoveComponents();
	String couleur=piece.run();
	 	
	Colorinfrench color =new Colorinfrench();
	
	Color test=color.parse(couleur);
	private int val=0;
	JLabel text= new JLabel();

	
    public Main() {
        JFrame frame=new JFrame("Eternity II");


		  
        frame.setMinimumSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mvcp);
        frame.getContentPane().setBackground(test);

     
        
        frame.setLayout(new BorderLayout());
        JPanel buttonPanel=new JPanel();
        JPanel aidePanel=new JPanel();
        JPanel piecePanel=new JPanel();
        JButton resetButton = new JButton("Restart");
        resetButton.setActionCommand("reset");
        resetButton.addActionListener(this);
        JButton undoButton = new JButton("Undo");
        undoButton.setActionCommand("undo");
        undoButton.addActionListener(this);
        JButton rotateButton = new JButton("Rotate");
        rotateButton.setActionCommand("rotate");
        rotateButton.addActionListener(this);
        JButton redoButton = new JButton("Redo");
        redoButton.setActionCommand("redo");
        redoButton.addActionListener(this);
        File file=new File("src/main/ressources/Googolopoly.jpg");
        JLabel lab = new JLabel(new ImageIcon(file.getPath())); 
        File file2=new File("src/main/ressources/piece1.jpg");
        JLabel lab2 = new JLabel(new ImageIcon(file2.getPath()));
        File file3=new File("src/main/ressources/piece2.png");
        JLabel lab3 = new JLabel(new ImageIcon(file3.getPath()));
        File file4=new File("src/main/ressources/piece3.png");
        JLabel lab4 = new JLabel(new ImageIcon(file4.getPath()));
        File file5=new File("src/main/ressources/piece4.png");
        JLabel lab5 = new JLabel(new ImageIcon(file5.getPath()));
        text.setText("Valeur de départ: "+val);
                
        buttonPanel.setLayout(new GridLayout(2,2));
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        buttonPanel.add(rotateButton);
        buttonPanel.add(resetButton);
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Boutons"));
        piecePanel.setLayout(new GridLayout(2,2));
        piecePanel.add(lab2);
        piecePanel.add(lab3);
        piecePanel.add(lab4);
        piecePanel.add(lab5);
        piecePanel.setBorder(BorderFactory.createTitledBorder("Pieces"));
        aidePanel.setLayout(new BorderLayout());
        aidePanel.setBorder(BorderFactory.createTitledBorder("Aide"));
        aidePanel.add(buttonPanel, BorderLayout.NORTH);
        aidePanel.add(text,BorderLayout.CENTER);
        aidePanel.add(piecePanel, BorderLayout.SOUTH);
        frame.add(aidePanel, BorderLayout.EAST);
        frame.add(lab, BorderLayout.WEST);
        
        frame.pack();
       
        frame.setVisible(true);
        
       

//System.out.println(couleur);
    }
    
    public static void main(String[] args) {
        new Main();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
			case "reset":
				val=0;
				text.setText("La valeur a été réinitialisé à "+val);
				break;

			case "undo":
				val--;
				text.setText("On a rétiré 1 à notre valeur on a donc: "+val);
				break;

			case "redo":
				val++;
				text.setText("On a ajouté 1 à notre valeur on a donc: "+val);
				break;
				
			case "rotate":
				val*=-1;
				text.setText("On a inversé notre valeur on a donc: "+val);
				break;
		}
	}
}
