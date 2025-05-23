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
	private static SavesPanel savesPanel;
	
	private static boolean demoMode; ///if true, answers don't affect moves

	///actual game data
	private static ArrayList<Save> saveList;
	private static Character[] playerChars;
	private static int saveIndex; ///use to update save
	private static int charViewing;
	private static Character player; ///player's character

	private static String userName; ///player name
	private static int enemiesDefeated; ///enemies defeated
	private static boolean gameStarted; ///tutorial beat
 	
	public static void incrementCharViewing()
	{
		charViewing++;

		if(charViewing > 2)
			charViewing = 0;

		player = playerChars[charViewing];
	}

	public static void decrementCharViewing()
	{
		charViewing--;

		if(charViewing < 0)
			charViewing = 2;

		player = playerChars[charViewing];
	}

	public static int getCharViewing()
	{
		return charViewing;
	}

	public static void setPlayerChars(Character[] charsIn)
	{
		playerChars = charsIn;
	}

	public static Character[] getPlayerChars()
	{
		return playerChars;
	}

	public static void setSaveList(ArrayList<Save> savesIn)
	{
		saveList = savesIn;
	}

	public static ArrayList<Save> getSaveList()
	{
		return saveList;
	}

	public static void setSaveIndex(int indexIn)
	{
		saveIndex = indexIn;
	}

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

	public static void setSavesPanel(SavesPanel savesPanelIn)
	{
		savesPanel = savesPanelIn;
	}

	public static void getSaves()
	{
		savesPanel.getSaves();
		switchCard("saves");
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

	/**
	 * If true is passed in, the save is updated and all saves
	 * are written to file. If false is passed in, the current
	 * save will be removed, but the other saves will still be
	 * written to file.
	 **/
	public static void writeData(boolean saveContinues)
	{
		updateSave(); ///updates current save prior to writing
		String fileName = "saveData.txt";
		File dataFile = new File(fileName);
		PrintWriter write = null;

		try
		{
			write = new PrintWriter(dataFile);

			if(saveList.size() < 1)
			{
				write.println("No save found.");
			}
			else
			{
				for(int index=0; index<saveList.size(); index++)
				{
					if(saveContinues && index == saveIndex)
					{
						write.println(userName);
						write.println(enemiesDefeated);
	
						for(int i=0; i<playerChars.length; i++)
						{
							Character plrChar = playerChars[i];
			
							write.println(plrChar.getName());
							write.println(plrChar.getLevel());
							write.println(plrChar.getHP() + "/" + plrChar.getMaxHP());
							write.println(plrChar.getMana() + "/" + plrChar.getMaxMana());
							write.println(plrChar.getDefense());
						}
					}
					else if(index != saveIndex)
					{
						write.println(saveList.get(index).getUserName());
						write.println(saveList.get(index).getEnemiesDefeated());
						Character[] plrChars = saveList.get(index).getPlayerCharacters();
	
						for(int i=0; i<plrChars.length; i++)
						{
							Character plrChar = plrChars[i];
			
							write.println(plrChar.getName());
							write.println(plrChar.getLevel());
							write.println(plrChar.getHP() + "/" + plrChar.getMaxHP());
							write.println(plrChar.getMana() + "/" + plrChar.getMaxMana());
							write.println(plrChar.getDefense());
						}
					}
				}
			}
			write.close();
		}
		catch(IOException e)
		{
			System.err.printf("Error: Could not write to file \"%s\"", fileName);
		}
	}

	public static void updateSave()
	{
		///updates the current save
		Save currSave = new Save(userName, enemiesDefeated, playerChars);

		if(saveList.size() > 0)
			saveList.set(saveIndex, currSave);
		else
		{
			saveList.add(currSave);
			setSaveIndex(0);
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
 }
class Save
{
	private String userName;
	private int enemiesDefeated;
	private Character[] characters;

	public Save(String userNameIn, int defeatedIn, Character[] charsIn)
	{
		userName = userNameIn;
		enemiesDefeated = defeatedIn;
		characters = charsIn;
	}

	public String getUserName()
	{
		return userName;
	}

	public int getEnemiesDefeated()
	{
		return enemiesDefeated;
	}

	public Character[] getPlayerCharacters()
	{
		return characters;
	}
}