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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


//Importation de la librairie pour la lecture de CSV
import au.com.bytecode.opencsv.CSVWriter;

//Class principale pour l'interface du jeu et le bon déroulement du jeu
public class Main implements ActionListener, ItemListener{
    
	//Création des pièces (Pieces.java)
	Pieces piece = new Pieces();
	//Lancement du csv pour les pièces
	String [] debut = piece.run();
	Comparaison compar=new Comparaison();
	private String comparr= "src/test/ressources/fr/esiea/glpoo/compar.csv"; //Variable du fichier de comparaison csv

String compp="";
	

	//Initialisation des variables 
	private int height=480;
	private int width=480;
	private int i;
	private int j;
	private int catiputi;
	private int jeu_num;
	private String memo;
	private int nombre=0;
	//Declaration de la barre de menu
	private JFrame frame;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu jeu = new JMenu("Jeu");
	private JMenu aide = new JMenu("Aide");
	private JMenuItem reset = new JMenuItem("Recommencer");
	private JMenuItem undo = new JMenuItem("Annuler");
	private JMenuItem regle = new JMenuItem("Regle");
	
	//Déclaration du plateau de jeu
	private JLabel[][] plateau = new JLabel[4][4];
	JLabel text= new JLabel();
	private int[][] rota = new int[4][4];

	
    public Main() {
    	
    	
    	
    	frame=new JFrame("Eternity II"); //Initialisation de notre fenetre
        
        
		  
        //PrÃ©paration de la fenetre
        frame.setMinimumSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Interdire l'agrandissemnt de la fenetre
        frame.setContentPane(piece); //Ajout des pièces
    
        //Preparation et integration de la barre de menu
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

        //Initialisation de la taille des plateau et pièces
        Dimension taillePlateau = new Dimension(480,480);
        Dimension taillePieces = new Dimension(width/4,height/4);
        
        
        //Organisation de l'interieur de la fenetre
        frame.setLayout(new BorderLayout());
        JPanel buttonPanel=new JPanel();
        JPanel aidePanel=new JPanel();
        JPanel piecePanel=new JPanel();
        JPanel plateauJeu = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        plateauJeu.setPreferredSize(taillePlateau);
        //Ajout d'un listener(lors d'un clic de la souris) a chaque case du plateau de jeu
        plateauJeu.addMouseListener(new MouseAdapter() {
        		@Override 
        		public void mousePressed(MouseEvent e) {
        			changeImage(e.getX(),e.getY());
        		}
        });
        //Création des boutons et de l'action
        JButton jeu1 = new JButton("Jeu1");
        jeu1.setActionCommand("jeu1");
        jeu1.addActionListener(this);
        JButton jeu2 = new JButton("Jeu2");
        jeu2.setActionCommand("jeu2");
        jeu2.addActionListener(this);
        JButton jeu3 = new JButton("Jeu3");
        jeu3.setActionCommand("jeu3");
        jeu3.addActionListener(this);
        JButton jeu4 = new JButton("Jeu4");
        jeu4.setActionCommand("jeu4");
        jeu4.addActionListener(this);
        
        //Création du dépot de pièce sur la fenetre
        File file2=new File("src/main/ressources/depotpiece.jpg");
        JLabel lab2 = new JLabel(new ImageIcon(file2.getPath()));
        
        text.setText("Déposer la pièce à la corbeille");
        
        //Ajout des 16 cases du plateau en JLabel
        for(i=0; i<4; i++) {
        	for(j=0; j<4; j++) {
        		plateau[i][j]= new JLabel();
        		plateau[i][j].setPreferredSize(taillePieces);
        		plateau[i][j].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        		plateauJeu.add(plateau[i][j]);
        	}
        }
        //Récupération du numéro du jeu que l'utilisateur souhaite jouer
        jeu_num=piece.recupJeu();      
        buttonPanel.setLayout(new GridLayout(2,2));
        buttonPanel.add(jeu1);
        buttonPanel.add(jeu2);
        buttonPanel.add(jeu3);
        buttonPanel.add(jeu4);
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choix du jeu:"));
        piecePanel.setLayout(new GridLayout(1,1));
        //Ajout du dépot de piècce
        piecePanel.add(lab2);
        piecePanel.setBorder(BorderFactory.createTitledBorder("Pieces"));
        aidePanel.setLayout(new BorderLayout());
        
        aidePanel.add(buttonPanel, BorderLayout.NORTH);
        aidePanel.add(text,BorderLayout.CENTER);
        aidePanel.add(piecePanel, BorderLayout.SOUTH);
        frame.add(aidePanel, BorderLayout.EAST);
        frame.add(plateauJeu, BorderLayout.WEST);
        frame.setResizable(false);
        frame.pack();
        
        try
        {
        FileOutputStream fos = new FileOutputStream(comparr);
        fos.close( );

        }
        catch (IOException e)
        {
        // Exception dÃ©clenchÃ©e si un problÃ¨me survient pendant l'accÃ¨s au fichier
        System.out.println (e);
        }
        
        //Ajout de l'action du bouton des règle
        regle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				JOptionPane.showMessageDialog(null,"Dans ce jeu, chaque piece est unique, cest-a-dire que chaque piece possede une combinaison unique de faces.\nLes pieces peuvent se ressembler par symetrie mais pas par rotation\n\nR - Rotation\nCliquer sur une piece pour la selectionner\nClique une deuxiÃ¨me fois sur une piece pour la deposer\nEnsuite depose la piece dans la corbeille.\n\n Bon jeu !");
			}	    	
	    });
        //Ajout de l'action du bouton "recommencer"
        reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
			recommencer();
			}	    	
	    });
        undo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
			annuler();
			}	    	
	    });
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
    	new Main();
    	
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
    
    //Methode pour identifier la case du plateau dont l'image va changer ou être récuperé
    public void changeImage(int x, int y) {
    	CSVWriter writer = null;
    	if(x>=0 && x<120 && y>=0 && y<120) {
    		if(plateau[0][0].getName()!=null){
    		
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[0][0].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[0][0].setName(null);
        		plateau[0][0].setIcon(null);
        		}
    		else{
    		
    		changeLabel(plateau[0][0]);
    		rota[0][0]=piece.recupRot();
    	}
    	}
    	if(x>=120 && x<240 && y>=0 && y<120) {
    		if(plateau[0][1].getName()!=null){
    			
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[0][1].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[0][1].setName(null);
        		plateau[0][1].setIcon(null);
        		}
    		else{
    		
    		changeLabel(plateau[0][1]);
    		rota[0][1]=piece.recupRot();
    		}
    	}
    	
    	if(x>=240 && x<360 && y>=0 && y<120) {
    		if(plateau[0][2].getName()!=null){
    			
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[0][2].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[0][2].setName(null);
        		plateau[0][2].setIcon(null);
        		}
    		else {
    		
    		changeLabel(plateau[0][2]);
    		rota[0][2]=piece.recupRot();
    		}
    	}
    	
    	if(x>=360 && x<480 && y>=0 && y<120) {
    		if(plateau[0][3].getName()!=null){
    			
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[0][3].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[0][3].setName(null);
        		plateau[0][3].setIcon(null);
        		}
    		else{
    		
    		changeLabel(plateau[0][3]);
    		rota[0][3]=piece.recupRot();
    		}
    	}
    	
    	if(x>=0 && x<120 && y>=120 && y<240) {
    		if(plateau[1][0].getName()!=null){
    			
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[1][0].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[1][0].setName(null);
        		plateau[1][0].setIcon(null);
        		}
    		else{
    		
    		changeLabel(plateau[1][0]);
    		rota[1][0]=piece.recupRot();
    		}
    	}
    	
    	if(x>=120 && x<240 && y>=120 && y<240) {
    		if(plateau[1][1].getName()!=null){
    			
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[1][1].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[1][1].setName(null);
        		plateau[1][1].setIcon(null);
        		}
    		else{
    		changeLabel(plateau[1][1]);
    		rota[1][1]=piece.recupRot();
    		}
    	}
    	
    	if(x>=240 && x<360 && y>=120 && y<240) {
    		if(plateau[1][2].getName()!=null){
    			
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[1][2].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[1][2].setName(null);
        		plateau[1][2].setIcon(null);
        		}
    		else{
    		
    		changeLabel(plateau[1][2]);
    		rota[1][2]=piece.recupRot();
    	
    		}
    		
    	}
    	
    	if(x>=360 && x<480 && y>=120 && y<240) {
    		if(plateau[1][3].getName()!=null){
    		
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[1][3].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[1][3].setName(null);
        		plateau[1][3].setIcon(null);
        		}
    		else{
    		
    		changeLabel(plateau[1][3]);
    		rota[1][3]=piece.recupRot();
    		}
    	}
    	
    	if(x>=0 && x<120 && y>=240 && y<360) {
    		if(plateau[2][0].getName()!=null){
    			
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[2][0].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[2][0].setName(null);
        		plateau[2][0].setIcon(null);
        		}
    		else{
    		changeLabel(plateau[2][0]);
    		rota[2][0]=piece.recupRot();
    		}
    	}
    	
    	if(x>=120 && x<240 && y>=240 && y<360) {
    		if(plateau[2][1].getName()!=null){
    		
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[2][1].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[2][1].setName(null);
        		plateau[2][1].setIcon(null);
        		}
    		else{
    		
    		changeLabel(plateau[2][1]);
    		rota[2][1]=piece.recupRot();
    	}
    	}
    	
    	
    	if(x>=240 && x<360 && y>=240 && y<360) {
    		if(plateau[2][2].getName()!=null){
    			
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[2][2].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[2][2].setName(null);
        		plateau[2][2].setIcon(null);
        		}
    		else{	
    		changeLabel(plateau[2][2]);
    		rota[2][2]=piece.recupRot();
    		}
    	}
    	if(x>=360 && x<480 && y>=240 && y<360) {
    		if(plateau[2][3].getName()!=null){
    		
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[2][3].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[2][3].setName(null);
        		plateau[2][3].setIcon(null);
        		}
    		else{	
    		changeLabel(plateau[2][3]);
    		rota[2][3]=piece.recupRot();
    		}
    	}
    	if(x>=0 && x<120 && y>=360 && y<480) {
    		if(plateau[3][0].getName()!=null){
    			
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[3][0].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[3][0].setName(null);
        		plateau[3][0].setIcon(null);
        		}
    		else{	
    		changeLabel(plateau[3][0]);
    		rota[3][0]=piece.recupRot();
    		}
    	}
    	if(x>=120 && x<240 && y>=360 && y<480) {
    		if(plateau[3][1].getName()!=null){
    			
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[3][1].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[3][1].setName(null);
        		plateau[3][1].setIcon(null);
        		}
    		else{	
    		changeLabel(plateau[3][1]);
    		rota[3][1]=piece.recupRot();
    	}
    	}
    	if(x>=240 && x<360 && y>=360 && y<480) {
    		if(plateau[3][2].getName()!=null){
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[3][2].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[3][2].setName(null);
        		plateau[3][2].setIcon(null);
        		}
    		else{	
    		changeLabel(plateau[3][2]);
    		rota[3][2]=piece.recupRot();
    	}
    	}
    	if(x>=360 && x<480 && y>=360 && y<480) {
    		if(plateau[3][3].getName()!=null){
    		
    			for(int k=0;k<16;k++){
    				
    				if(piece.getComponent(k).getName()==plateau[3][3].getName()){
    					piece.getComponent(k).setLocation(502,283 );
    					
    				}
    			}
    			memo=null;
        		plateau[3][3].setName(null);
        		plateau[3][3].setIcon(null);
        	
        		}
    		else {
    		
    		changeLabel(plateau[3][3]);
    		rota[3][3]=piece.recupRot();
    		}
    	}
    	
    	
		
    }
    
   
   //Methode pour recommencer une partie ou charger le jeu1
    public void recommencer(){
    	//Remise a 0 de compar.csv
    	try
        {
        FileOutputStream fos = new FileOutputStream(comparr);
        fos.close( );

        }
        catch (IOException e)
        {
        // Exception dÃ©clenchÃ©e si un problÃ¨me survient pendant l'accÃ¨s au fichier
        System.out.println (e);
        }
    	for(int p=0;p<16;p++){
			//Component recuppiece = piece.getComponent(p);
		      int lower = 1;
		      int higher = 5;

		      int random = (int)(Math.random() * (higher-lower)) + lower;
		       
		        
	     //Valeur de gauche haut (505,283) dans le dÃƒÂ©pot des piÃƒÂ¨ces
			if(random==1)
			 piece.getComponent(p).setLocation(502,283 );
		//Valeur de droite haut (594,283)
			if(random==2)
			 piece.getComponent(p).setLocation(594,283 );
		//Valeur de droite bas (594,374)
			if(random==3)
			 piece.getComponent(p).setLocation(594,374 );
		//Valeur de gauche bas (505 ,374)
			if(random==4)
			 piece.getComponent(p).setLocation(505,374 );
			
			
			
	
		}
		memo=null;
		for(int yy=0;yy<4;yy++){
			for(int xx=0;xx<4;xx++){
		plateau[yy][xx].setName(null);
		plateau[yy][xx].setIcon(null);
			}
		}
    }
    
    //Methode pour annuler la dernière pièce déposer
    public void annuler(){
    	for(int h=0;h<16;h++){
    		if(piece.getComponent(h).getName()==memo)
    			piece.getComponent(h).setLocation(502,283);
    	}
    	for(int yy=0;yy<4;yy++){
			for(int xx=0;xx<4;xx++){
				if(plateau[yy][xx].getName()==memo){
		plateau[yy][xx].setName(null);
		plateau[yy][xx].setIcon(null);
				}
			}
		}
    	memo=null;
    }
    //Methode pour vérifier que la pièce a bien été posé avant de la placer à la corbeille
    public void verification(){
    	int test=0;
    	int testcomplet=0;
    	
    	for(int yy=0;yy<4;yy++){
			for(int xx=0;xx<4;xx++){
				if(plateau[yy][xx].getName()==piece.recupName()){
		piece.memodepo(true);
		test++;
				
				}
				if(plateau[yy][xx].getName()!=null){
					 
					testcomplet++;
				}
			}
    	
    }
    	if(test==0){
    		piece.memodepo(false);
    		JOptionPane.showMessageDialog(null,"La pièce n'a pas été placé sur le plateau !!");
    		
    }
    	
    	if(testcomplet==16){
    	
    	
    	for(int yy=0;yy<4;yy++){
			for(int xx=0;xx<4;xx++){
				CSVWriter writer=null;
				//Ajout dans le csv le placement des pieces
		    	try {
					writer = new CSVWriter(new FileWriter("src/test/ressources/fr/esiea/glpoo/compar.csv",true), ',');
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
     	String[][] entree = {{plateau[yy][xx].getName(),Integer.toString(rota[yy][xx])}};
     	
     	for(String elem[]:entree)
     		writer.writeNext(elem);
		try {
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			}
    	}
    	 finDuJeu();
    	}
    }
    public void finDuJeu(){
       
	  compp=compar.compar();
	  JOptionPane.showMessageDialog(null,compp);
	  recommencer();
  
    } 
  //Methode pour placer l'image avec la rotation souhaitÃ©e dans le label en parametre
    public void changeLabel(JLabel label) {
    	if(label.getIcon()==null && memo!=piece.recupName()){
    	File file=new File("src/main/ressources/jeu_"+jeu_num+"/"+piece.recupName()+".png");
    	memo=piece.recupName();
        ImageIcon icon=new ImageIcon(file.getPath());
        label.setIcon(icon);
        label.setName(memo);
        catiputi=piece.recupRot();
        for(int f=0;f<catiputi;f++){
      	  rotateCase(label);
      	}
        
        verification();
       
    	}
    }
    
    
    @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
			case "jeu1":
			recommencer();
				break;
			case "jeu2":
				JOptionPane.showMessageDialog(null,"Non disponible pour le moment.");
				
				
				break;

			case "jeu3":
				JOptionPane.showMessageDialog(null,"Non disponible pour le moment.");
				
				break;
				
			case "jeu4":
				JOptionPane.showMessageDialog(null,"Non disponible pour le moment.");
				
				break;
		}
		
		
 
 

	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}