package fr.esiea.glpoo;

import java.awt.Color;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import fr.ybonnel.csvengine.adapter.AdapterCsv;
import fr.ybonnel.csvengine.annotation.CsvFile;
import fr.ybonnel.csvengine.validator.ValidateException;


public class Colorinfrench extends AdapterCsv<Color>{
	

@Override
public Color parse(String str) throws ValidateException {
	Color color = null;
	switch(str){
	case "bleu":
		color=Color.blue;
		break;
		
	case "jaune":
		color=Color.yellow;


	case "rouge":
		color=Color.red;
		
	case "noir":
		color=Color.black;
	
	
	}
	return color;
}

@Override
public String toString(Color color) {
	// TODO Auto-generated method stub
	return null;
}

}
