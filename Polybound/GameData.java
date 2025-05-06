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
 	private static CardLayout polyCards;//these 2 fvs for the CardLayout
 	private static JPanel cardHolder;
 	public static ProblemPanel problemPanel;
 	private static boolean gameStarted;
 	//also called gameStarted; once user presses 'Continue' this is set to true
 	private static String userName;
 	
 	//all set methods 
	///Gets the main CardLayout and its holder
 	public static void setCardHolder(JPanel holderIn)
 	{
		cardHolder = holderIn;
		polyCards = (CardLayout) cardHolder.getLayout();
	}
	public static void setGameStarted(boolean gameStartedIn)
	{
		gameStarted = gameStartedIn;
	}
	public static boolean gameIsStarted()
	{
		return gameStarted;
	}

	public static void setUserName(String userNameIn)
	{
		userName = userNameIn;
	}
	
	public static void setProblemPanel(ProblemPanel pPanelIn)
	{
		problemPanel = pPanelIn;
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
			System.err.printf("Error: Could not load from file \"%s\".%n", fileName);
		}
		
		return toReturn;
	}
	///Returns the ProblemPanel 
	public static ProblemPanel getProblemPanel()
	{
		return problemPanel;
	}
	
	//returns the username 
	public static String getUserName()
	{
		return userName;
	}
	//inclusive # generator
	public static int getRandom(int low, int high)
	{
		return (int)(Math.random()*(high-low+1)+low);
	}

	/**
	 * Returns the passed in String without the data before or including the first instance
	 * of the specified regex.
	 * Returns the same String if no regex was found.
	 */
	public static String dataAfter(String str, String regex)
	{
		if(str.indexOf(regex) == -1)
		{
			return str;
		}

		return str.substring(str.indexOf(regex)+regex.length());
	}

	/**
	 * Returns the data to the specified regex of the passed in String. Returns the same String if
	 * no regex was found.
	 */
	public static String getDataTo(String str, String regex)
	{
		if(str.indexOf(regex) == -1)
		{
			return str;
		}

		return str.substring(0, str.indexOf(regex));
	}
 }
