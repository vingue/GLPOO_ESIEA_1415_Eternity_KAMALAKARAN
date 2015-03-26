package fr.esiea.glpoo;

import java.awt.Color;

import junit.framework.Assert;

public class ColorTest {

	@Test
	public void testColorConversion() {
		String s = "bleu";
		Color c = new Colorinfrench().parse(s);
		Assert.assertSame(c, Color.blue);;
	}
	
}
