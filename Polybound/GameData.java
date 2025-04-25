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
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.CardLayout;
import javax.swing.JPanel;

 public class GameData //static class 
 {
 	private static CardLayout polyCards;
 	
	///Gets the main CardLayout
 	public static void setCardLayout(CardLayout cardsIn)
 	{
		polyCards = cardsIn;
	}
 	
	///Returns the main CardLayout
 	public static CardLayout getCardLayout()
 	{
 		return polyCards;
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
			System.err.printf("Error: Could not read from file \"%s\".", fileName);
		}
		
		return toReturn;
	}
 }
