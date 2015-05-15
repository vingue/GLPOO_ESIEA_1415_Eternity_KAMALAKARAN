package fr.esiea.glpoo;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Main implements ActionListener, ItemListener{
    
	Pieces piece;
	

	String couleur;
	
	private int val=0;
	JLabel text= new JLabel();
	private int height=480;
	private int width=480;
	private int i;
	private int j;
	private int catiputi;
	private int jeu_num;

	//Declaration de la barre de menu
	private JFrame frame;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu jeu = new JMenu("Jeu");
	private JMenu aide = new JMenu("Aide");
	private JMenuItem reset = new JMenuItem("Recommencer");
	private JMenuItem undo = new JMenuItem("Annuler");
	private JMenuItem regle = new JMenuItem("Regle");
	private JLabel[][] plateau = new JLabel[4][4];
	
    public Main() {
    	
        frame=new JFrame("Eternity II"); //Initialisation de notre fenetre
        
        //Création des pieces
        piece = new Pieces();
        couleur=piece.run();
		  
        //Préparation de la fenetre
        frame.setMinimumSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(piece);
    
        //Prepartion et integration de la barre de menu
        undo.setActionCommand("undo");
        undo.addActionListener(this);
        reset.setActionCommand("reset");
        reset.addActionListener(this);
        jeu.add(reset);
        jeu.add(undo);
        aide.add(regle);
        menuBar.add(jeu);
        menuBar.add(aide);
        frame.setJMenuBar(menuBar);

        Dimension taillePlateau = new Dimension(480,480);
        Dimension taillePieces = new Dimension(width/4,height/4);
        
        
        //Organisation de l'interieur de la fenetre
        frame.setLayout(new BorderLayout());
        JPanel buttonPanel=new JPanel();
        JPanel aidePanel=new JPanel();
        JPanel piecePanel=new JPanel();
        JPanel plateauJeu = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        plateauJeu.setPreferredSize(taillePlateau);
        plateauJeu.addMouseListener(new MouseAdapter() {// empty implementation of all
            // MouseListener`s methods
        		@Override //I override only one method for presentation
        		public void mousePressed(MouseEvent e) {
        			changeImage(e.getX(),e.getY());
        			System.out.println(e.getX() + "," + e.getY());
        		}
        });
        JButton resetButton = new JButton("Reset");
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
        File file2=new File("src/main/ressources/depotpiece.jpg");
        JLabel lab2 = new JLabel(new ImageIcon(file2.getPath()));
        
        text.setText("Valeur de départ: "+val);
        
        for(i=0; i<4; i++) {
        	for(j=0; j<4; j++) {
        		plateau[i][j]= new JLabel();
        		plateau[i][j].setPreferredSize(taillePieces);
        		plateau[i][j].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        		plateauJeu.add(plateau[i][j]);
        	}
        }
        jeu_num=piece.recupJeu();      
        buttonPanel.setLayout(new GridLayout(2,2));
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        buttonPanel.add(rotateButton);
        buttonPanel.add(resetButton);
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Boutons"));
        piecePanel.setLayout(new GridLayout(1,1));
        piecePanel.add(lab2);
        piecePanel.setBorder(BorderFactory.createTitledBorder("Pieces"));
        aidePanel.setLayout(new BorderLayout());
        aidePanel.setBorder(BorderFactory.createTitledBorder("Aide"));
        aidePanel.add(buttonPanel, BorderLayout.NORTH);
        aidePanel.add(text,BorderLayout.CENTER);
        aidePanel.add(piecePanel, BorderLayout.SOUTH);
        frame.add(aidePanel, BorderLayout.EAST);
        frame.add(plateauJeu, BorderLayout.WEST);
        frame.setResizable(false);
        frame.pack();
       
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
    	new Main();
    	
    }
    
    //Methode pour reinitialiser a partie
    public void restart() {
    	piece = new Pieces();
        couleur=piece.run();
    }
    
    //Methode pour faire rotater le JPanel et donc notre image
    public void rotateCase(JLabel panel){
    	
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
    
    //Methode pour identifier la case du plateau dont l'image va changer
    public void changeImage(int x, int y) {
    	System.out.println(x + "," + y);
    	if(x>=0 && x<120 && y>=0 && y<120) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[0][0]);
    	}
    	
    	if(x>=120 && x<240 && y>=0 && y<120) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[0][1]);
    	}
    	
    	if(x>=240 && x<360 && y>=0 && y<120) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[0][2]);
    	}
    	
    	if(x>=360 && x<480 && y>=0 && y<120) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[0][3]);
    	}
    	
    	if(x>=0 && x<120 && y>=120 && y<240) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[1][0]);
    	}
    	
    	if(x>=120 && x<240 && y>=120 && y<240) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[1][1]);
    	}
    	
    	if(x>=240 && x<360 && y>=120 && y<240) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[1][2]);
    	}
    	
    	if(x>=360 && x<480 && y>=120 && y<240) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[1][3]);
    	}
    	
    	if(x>=0 && x<120 && y>=240 && y<360) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[2][0]);
    	}
    	
    	if(x>=120 && x<240 && y>=240 && y<360) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[2][1]);
    	}
    	
    	if(x>=240 && x<360 && y>=240 && y<360) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[2][2]);
    	}
    	
    	if(x>=360 && x<480 && y>=240 && y<360) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[2][3]);
    	}
    	
    	if(x>=0 && x<120 && y>=360 && y<480) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[3][0]);
    	}
    	
    	if(x>=120 && x<240 && y>=360 && y<480) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[3][1]);
          	
    	}
    	
    	if(x>=240 && x<360 && y>=360 && y<480) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[3][2]);
    	}
    	
    	if(x>=360 && x<480 && y>=360 && y<480) {
    		System.out.println(piece.recupName());
    		changeLabel(plateau[3][3]);
    	}
    }
    
    //Methode pour placer l'image avec la rotation souhaitée dans le label en parametre
    public void changeLabel(JLabel label) {
    	File file=new File("src/main/ressources/jeu_"+jeu_num+"/"+piece.recupName()+".jpg");
        ImageIcon icon=new ImageIcon(file.getPath());
        label.setIcon(icon);
        catiputi=piece.recupRot();
        for(int f=0;f<catiputi;f++){
      	  rotateCase(label);
      	}
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
				System.out.println(piece.recupName());
				System.out.println(piece.recupRot()); 
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
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
