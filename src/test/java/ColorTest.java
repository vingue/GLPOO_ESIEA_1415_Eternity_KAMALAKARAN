package fr.esiea.glpoo;

import java.awt.Color;

import org.junit.Test;

import fr.ybonnel.csvengine.validator.ValidateException;
import junit.framework.Assert;

public class ColorTest {

	@Test
	public void testColorConversion() throws ValidateException {
		String s = "bleu";
		Color c = new Colorinfrench().parse(s);
		Assert.assertSame(c, Color.blue);;
	}
	
}
