 /*
  * Noah Guan and Krish Kumar
  * Period 6
  * GameData.java
  * 
  * Contains shared data between classes, as well as utility methods
  * that are used throughout the game.
  * 
  * NOTE: THIS CLASS IS NOT COMPLETE!
  */

///import libraries
import javax.swing.JPanel;

import java.awt.Image;
import java.awt.CardLayout;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

 public class GameData //static class 
 {
 	private static CardLayout polyCards;
 	private static JPanel cardHolder;
 	private static boolean gameStarted;
 	//also called gameStarted; once user presses 'Continue' this is set to true
 	
 	//all set methods 
	///Gets the main CardLayout and its holder
 	public static void setCardHolder(JPanel holderIn)
 	{
		cardHolder = holderIn;
		polyCards = (CardLayout) cardHolder.getLayout();
	}

	public static void gameStarted(boolean gameStartedIn)
	{
		gameStarted = gameStartedIn;
	}
 	
 	//all get methods 
	///Returns the main CardLayout
 	public static CardLayout getCardLayout()
 	{
 		return polyCards;
 	}
 	
 	///Returns the main CardLayout holder
 	public static JPanel getCardHolder()
 	{
 		return cardHolder;
 	}
 	
	///Attempts to load the image from the designated file name
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
			System.err.printf("Error: Could not read from file \"%s\".%n", fileName);
		}
		
		return toReturn;
	}
 }
