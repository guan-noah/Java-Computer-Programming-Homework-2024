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
 	
	///Gets the main CardLayout and its holder
 	public static void setCards(JPanel holderIn)
 	{
		cardHolder = holderIn;
		polyCards = (CardLayout) cardHolder.getLayout();
	}

	public static void switchCard(String cardIn)
	{
		polyCards.show(cardHolder, cardIn);
	}

	public static void setGameStarted(boolean gameStartedIn)
	{
		gameStarted = gameStartedIn;
	}

	public static boolean gameIsStarted()
	{
		return gameStarted;
	}

	public static void setDemoMode(boolean isOn)
	{
		demoMode = isOn;
	}

	public static boolean isDemoModeOn()
	{
		return demoMode;
	}

	public static void setGamePanel(GamePanel gamePanelIn)
	{
		gamePanel = gamePanelIn;
	}
	
	public static void startGame(boolean isTutorial)
	{
		switchCard("game");
		gamePanel.start(isTutorial);
	}

	public static void setIntermissionPanel(IntermissionPanel imPanelIn)
	{
		intermissionPanel = imPanelIn;
	}

	public static void refreshStats()
	{
		intermissionPanel.refreshStats();
	}

	public static void executeUserMove(boolean success)
	{
		gamePanel.executeUserMove(success);
	}

	public static void setUserName(String userNameIn)
	{
		userName = userNameIn;
	}

	//returns the username 
	public static String getUserName()
	{
		return userName;
	}

	public static void setEnemiesDefeated(int count)
	{
		enemiesDefeated = count;
	}

	public static void incrementEnemiesDefeated()
	{
		enemiesDefeated++;
	}

	public static int getEnemiesDefeated()
	{
		return enemiesDefeated;
	}

	public static void setPlayerCharacter(Character playerIn)
	{
		player = playerIn;
		System.out.println(player.getName());
	}

	public static Character getPlayerCharacter()
	{
		return player;
	}

	public static void setProblemPanel(ProblemPanel pPanelIn)
	{
		problemPanel = pPanelIn;
	}

	public static void getProblem()
	{
		problemPanel.getProblem();
		switchCard("problem");
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

	public static void writeData(boolean saveContinues)
	{
		String fileName = "saveData.txt";
		File dataFile = new File(fileName);
		PrintWriter write = null;

		try
		{
			write = new PrintWriter(dataFile);

			if(saveContinues)
			{
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
				write.println("No save found.");
			}
			write.close();
		}
		catch(IOException e)
		{
			System.err.printf("Error: Could not write to file \"%s\"", fileName);
		}
	}

	public static void writeHighScore()
	{
		String fileName = "highscores.txt";
		File dataFile = new File(fileName);
		PrintWriter write = null;

		try
		{
			write = new PrintWriter(dataFile);

			write.print(userName + " - ");
			write.print(enemiesDefeated + " enemies defeated (");
			write.println(player.getName() + ")");

			write.close();
		}
		catch(IOException e)
		{
			System.err.printf("Error: Could not write to file \"%s\"", fileName);
		}
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
	
	/* reads from a text file of moves and outputs move 
	 * loads the move in GameData and when we have to access it, we request it */
	//~ public static ArrayList<Move> getMoveset(String characterName)
	//~ {
		//~ reads from a file with a list of all the moves based on character name
		//~ returns move object arraylist 
	//~ }
	
 }
