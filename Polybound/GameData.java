import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.CardLayout;
import javax.swing.JPanel;

 /* Noah Guan
  * GameData.java
  * Project started 4/23/2025
 */
 //noah
 ///krish
 /* noah */
 /** krish **/
 public class GameData //static class 
 {
 	private static CardLayout polyCards;
 	
 	/**
 	 * Written by Krish, rewrote it for simplicity, also I dunno if you
 	 * understood the whole static class thing...
 	 **/
 	public static void setCardLayout(CardLayout cardsIn)
 	{
		polyCards = cardsIn;
	}
 	
 	 /**
 	 * Written by Krish for simplicity
 	 **/
 	public static CardLayout getCardLayout()
 	{
 		return polyCards;
 	}
 	
 	/**
	 * This code was written by Krish.
	 * 
	 **/
	public static Image loadImage(String fileName)
	{
		File imgFile = new File(fileName);
		Image toReturn = null;
		
		try
		{
			toReturn = ImageIO.read(imgFile);
		}
		catch(IOException e)
		{
			System.err.printf("Error: Could not read from file \"%s\".", fileName);
		}
		
		return toReturn;
	}
 }
