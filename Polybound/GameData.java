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
 	private static boolean gameStarted;
 	//also called gameStarted; once user presses 'Continue' this is set to true
 	private static String userName, enemyName;//for getting info to GamePanel 
 	private static Character user, enemy;
 		//note: in the future, maybe we can change enemy character to 
			//an enemy character arraylist (for multiple enemies) 
 	
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
	public static void setUserName(String userNameIn)
	{
		userName = userNameIn;
	}
	
	public static void setEnemyName(String enemyNameIn)
	{
		enemyName = enemyNameIn;
	}
	//for creating the first enemy and changing when new enemy comes 
	public static void changeEnemy(String enemyNameIn)
 	{
		enemy = new Character(enemyName);
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
			System.err.printf("Error: Could not read from file \"%s\".", fileName);
		}
		
		return toReturn;
	}
	
	//returns the username 
	public static String getUserName()
	{
		return userName;
	}
	//returns the enemy name
	public static String getEnemyName()
	{
		return enemyName;
	}
	//inclusive # generator
	public static int getRandom(int low, int high)
	{
		return (int)(Math.random()*(high-low+1)+low);
	}
	
	/* reads from a text file of moves and outputs move 
	 * loads the move in GameData and when we have to access it, we request it */
	//~ public static ArrayList<Move> getMoveset(String characterName)
	//~ {
		//~ reads from a file with a list of all the moves based on character name
		//~ returns move object arraylist 
	//~ }
	
 }
