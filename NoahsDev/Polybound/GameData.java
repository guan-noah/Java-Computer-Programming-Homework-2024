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
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

 public class GameData //static class 
 {
 	private static CardLayout polyCards;//these 2 fvs for the CardLayout
 	private static JPanel cardHolder;
	private static ProblemPanel problemPanel;
	private static GamePanel gamePanel;
	private static IntermissionPanel intermissionPanel;
	
	private static boolean demoMode; ///if true, answers don't affect moves

	///actual game data
	private static Character player; ///player's character

	private static String userName; ///player name
	private static int enemiesDefeated; ///enemies defeated
	private static boolean gameStarted; ///tutorial beat
 	
 	//CARDLAYOUT AND PANEL SWITCHING METHODS 
	/**
	 * Gets the main CardLayout and its holder
	 */
 	public static void setCards(JPanel holderIn)
 	{
		cardHolder = holderIn;
		polyCards = (CardLayout) cardHolder.getLayout();
	}
	
	/*
	 * This method sets the Game Panel to a parameter input. 
	 */
	public static void setGamePanel(GamePanel gamePanelIn)
	{
		gamePanel = gamePanelIn;
	}
	
	/*
	 * This method switches cards based on the string passed into it. 
	 */
	public static void switchCard(String cardIn)
	{
		polyCards.show(cardHolder, cardIn);
	}
	
	/*
	 * This method sets the problem panel to a parameter input. 
	 */
	public static void setProblemPanel(ProblemPanel pPanelIn)
	{
		problemPanel = pPanelIn;
	}
	
	/*
	 * This method gets the current problem and switches the current panel
	 * shown on the screen to the Problem Panel. 
	 */
	public static void getProblem()
	{
		problemPanel.getProblem();
		switchCard("problem");
	}
	
	/*
	 * This method sets intermission panel to a paramter input. 
	 */
	public static void setIntermissionPanel(IntermissionPanel imPanelIn)
	{
		intermissionPanel = imPanelIn;
	}
 	
	//GAME HANDLING METHODS (progression) 
	/*
	 * This method sets the game started (including tutorial) boolean to 
	 * true; it saves the user input data gathered in SelectUserInfoPanel. 
	 */
	public static void setGameStarted(boolean gameStartedIn)
	{
		gameStarted = gameStartedIn;
	}
	
	/*
	 * This method checks (and returns) if the game is started. 
	 */
	public static boolean gameIsStarted()
	{
		return gameStarted;
	}
	
	/*
	 * This method sets the demo mode to a parameter input. 
	 */
	public static void setDemoMode(boolean isOn)
	{
		demoMode = isOn;
	}
	
	/*
	 * This method checks (and returns) if the game is on demo mode. 
	 */
	public static boolean isDemoModeOn()
	{
		return demoMode;
	}
	
	/*
	 * This method refreshes the intermission panel's stats (user information). 
	 */
	public static void refreshStats()
	{
		intermissionPanel.refreshStats();
	}
	
	/*
	 * This method shows the game panel and starts the game. 
	 */
	public static void startGame(boolean isTutorial)
	{
		switchCard("game");
		//Passes in the isTutorial boolean to game panel to process. 
		gamePanel.start(isTutorial);
	}
	
	/*
	 * This method executes the user's move. (intermediary step, passes 
	 * boolean into GamePanel. 
	 */
	public static void executeUserMove(boolean success)
	{
		gamePanel.executeUserMove(success);
	}
	
	//USER NAME HANDLING METHODS 
	/*
	 * This method sets the user's name. 
	 */
	public static void setUserName(String userNameIn)
	{
		userName = userNameIn;
	}
	
	/*
	 * This method returns the user's name. 
	 */
	public static String getUserName()
	{
		return userName;
	}
	
	//ENEMIES DEFEATED METHODS 
	/*
	 * This method sets the enemies defeated to a certain number. 
	 * Used for resetting game. 
	 */
	public static void setEnemiesDefeated(int count)
	{
		enemiesDefeated = count;
	}
	
	/*
	 * This method adds 1 to enemies defeated. 
	 * Called when user defeats an enemy. 
	 */
	public static void incrementEnemiesDefeated()
	{
		enemiesDefeated++;
	}
	
	/*
	 * This method returns the number of enemies defeated. 
	 */
	public static int getEnemiesDefeated()
	{
		return enemiesDefeated;
	}
	
	//CHARACTER METHODS 
	/*
	 * This method sets the player character to parameter input. 
	 */
	public static void setPlayerCharacter(Character playerIn)
	{
		player = playerIn;
		System.out.println(player.getName());
	}
	
	/*
	 * This method returns the player character. 
	 */
	public static Character getPlayerCharacter()
	{
		return player;
	}
	
	///Attempts to load the image from the designated file name
	public static Image loadImage(String fileName)
	{
		//standard file io logic structure. initializes image if possible. 
		File imgFile = new File(fileName);
		Image toReturn = null;
		
		try
		{
			toReturn = ImageIO.read(imgFile);
		}
		catch(IOException e)
		{
			//if not possible, give a system error with a new line at the end for readability. 
			System.err.printf("Error: Could not load from file \"%s\".%n", fileName);
		}
		
		return toReturn;
	}
	
	//FILE OUTPUT METHODS 
	/*
	 * idea for polymorphism (only implement if extra time): 
	 * recode it so the file input/output data structures 
	 * parameters: boolean io (to see which io object to initialize) and 
	 * String method. + saveContinue, of course
	 * calls action(method, io object, saveContinue) inside try{} block 
	 * and action() has if/else logic to determine what to do 
	 * 
	 */
	public static void writeData(boolean saveContinues)
	{
		//standard file output logic structure. 
		String fileName = "saveData.txt";
		File dataFile = new File(fileName);
		PrintWriter write = null;

		try
		{
			write = new PrintWriter(dataFile);
			
			//only saves user information to save file if there is a 
				//file to write to AND saveContinues = true. Otherwise, 
				//wipes saved data. 
			if(saveContinues)
			{
				// write/save user information
				write.println(userName);
				write.println(player.getName());
				write.println(player.getLevel());
				write.println(enemiesDefeated);
				write.println(player.getHP() + "/" + player.getMaxHP());
				write.println(player.getMana() + "/" + player.getMaxMana());
				write.println(player.getDefense());
			}
			else
			{
				// write "No save found" to file 
				write.println("No save found.");
			}
			//close printwriter 
			write.close();
		}
		catch(IOException e)
		{
			//if cannot write to file (maybe read-only), system will print an error 
			System.err.printf("Error: Could not write to file \"%s\".%n", fileName);
		}
	}
	
	/*
	 * This method writes the high scores to the text file. Called when 
	 * user fails to defeat an enemy and game is over. 
	 */
	public static void writeHighScore()
	{
		//standard file output logic 
		String fileName = "highscores.txt";
		File dataFile = new File(fileName);
		PrintWriter write = null;

		try
		{
			write = new PrintWriter(dataFile);
			
			//only write user info to high scores after determining we can access it
			write.print(userName + " - ");
			write.print(enemiesDefeated + " enemies defeated (");
			write.println(player.getName() + ")");
			
			//close scanner 
			write.close();
		}
		catch(IOException e)
		{
			//give an error 
			System.err.printf("Error: Could not write to file \"%s\".%n", fileName);
		}
	}

	//USEFUL UTILITIES METHODS 
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
	
	/* reads from a text file of moves and outputs move 
	 * loads the move in GameData and when we have to access it, we request it */
	//~ public static ArrayList<Move> getMoveset(String characterName)
	//~ {
		//~ reads from a file with a list of all the moves based on character name
		//~ returns move object arraylist 
	//~ }
	
 }
