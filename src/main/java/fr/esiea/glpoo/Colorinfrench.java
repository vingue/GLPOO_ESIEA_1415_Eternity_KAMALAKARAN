package fr.esiea.glpoo;

import java.awt.Color;




public class Colorinfrench{
	
	

public Color parse(String str) {
	Color color = null;
	switch(str){
	case "bleu":
		color=Color.blue;
		break;
		
	case "jaune":
		color=Color.yellow;
break;
	case "noir":
		color=Color.black;
break;
	case "rouge":
		color=Color.red;
		break;
	
	
	
	}
	return color;
}

public String toString(Color color) {
	// TODO Auto-generated method stub
	return null;
}

}