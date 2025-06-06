 /*
  * Noah Guan and Krish Kumar
  * Period 6
  * GameData.java
  * 
  * Contains shared data between classes, as well as utility methods
  * that are used throughout the game.
  * writeData and writeHighScore need documentation
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
import java.util.Scanner;

//static class. 
 public class GameData
 {
	///the CardLayout stuff for the game
 	private static CardLayout polyCards;
 	private static JPanel cardHolder;

	///the panels that are used in GameData
	private static ProblemPanel problemPanel;
	private static GamePanel gamePanel;
	private static IntermissionPanel intermissionPanel;
	private static SavesPanel savesPanel;
	
	private static boolean demoMode; ///if true, answers don't affect moves

	///actual game data
	private static ArrayList<Save> saveList; ///list of saves
	private static Character[] playerChars; ///player's characters
	private static int saveIndex; //the save the user currently uses; use var to update save
	private static int charViewing; ///which character is being viewed
									///0 = Line, 1 = Quadratic, 2 = Cubic
	private static Character player; ///the current character being viewed

	private static String userName; ///player name
	private static int enemiesDefeated; ///enemies defeated
	private static boolean gameStarted; ///essentially if the tutorial is completed
										///since you need to complete it to actually
										///save your save
	//CARDLAYOUT AND PANEL SWITCHING METHODS
	
	///Gets the main CardLayout and its holder.
 	public static void setCards(JPanel holderIn)
 	{
		cardHolder = holderIn;
		polyCards = (CardLayout) cardHolder.getLayout();
	}

	///Switches the CardLayout card to the specified card.
	public static void switchCard(String cardIn)
	{
		polyCards.show(cardHolder, cardIn);
	}
	
	//Initializing panels 
	///Initializes the intermission panel.
	public static void setIntermissionPanel(IntermissionPanel imPanelIn)
	{
		intermissionPanel = imPanelIn;
	}

	///Initializes the problem panel.
	public static void setProblemPanel(ProblemPanel pPanelIn)
	{
		problemPanel = pPanelIn;
	}

	///Initializes the "Saves" panel.
	public static void setSavesPanel(SavesPanel savesPanelIn)
	{
		savesPanel = savesPanelIn;
	}

	///Initializes the game panel.
	public static void setGamePanel(GamePanel gamePanelIn)
	{
		gamePanel = gamePanelIn;
	}

	
	//GAME HANDLING METHODS (PROGRESSION) 
	//character methods 
	/*
	 * Utilized by game panel (switches the selected character) 
	 * Sets the current character being viewed to the specified index.
	 * This will also set the player fv to that character.
	 */
	public static void setCharViewing(int viewing)
	{
		charViewing = viewing;
		player = playerChars[charViewing];
	}
 	
	/**
	 * Increments the character viewing index, and sets the player
	 * to the character at that index.
	 * 
	 * If the index exceeds the number of characters, it will reset to 0.
	 **/
	public static void incrementCharViewing()
	{
		charViewing++; ///increment the character viewing index

		///if the index exceeds the number of characters, reset to 0
		if(charViewing > 2)
			charViewing = 0;

		///set the player to the character at the new index
		player = playerChars[charViewing];
	}

	public static void decrementCharViewing()
	{
		charViewing--; ///decrement the character viewing index

		///if the index is less than 0, reset to the last character
		if(charViewing < 0)
			charViewing = 2;

		///set the player to the character at the new index
		player = playerChars[charViewing];
	}

	///Returns the index of the character being viewed.
	public static int getCharViewing()
	{
		return charViewing;
	}

	///Initializes the player's characters to the specified Character[] array.
	public static void setPlayerChars(Character[] charsIn)
	{
		playerChars = charsIn;
	}

	///Returns the player's characters.
	public static Character[] getPlayerChars()
	{
		return playerChars;
	}

	///Returns whether or not all player characters are defeated.
	public static boolean playerCharsDefeated()
	{
		int currentlyDefeated = 0; ///number of characters currently defeated

		///counts the number of characters that are defeated
		for(int i=0; i<playerChars.length; i++)
		{
			if(playerChars[i].isDefeated())
			{
				currentlyDefeated++;
			}
		}

		///if all characters are defeated, return true, otherwise false
		return currentlyDefeated == 3;
	}
	
	/**
	 * Sets the enemy shake boolean to the specified value.
	 * This is used to shake the enemy when it is hit by a player's attack.
	 * 
	 * This method is a proxy to the method in GamePanel.java, which actually
	 * performs the shake effect on the enemy sprite.
	 **/
	public static void setEnemyShake(boolean isShaking)
	{
		gamePanel.setEnemyShake(isShaking);
	}

	/**
	 * Sets the enemy shake boolean to the specified value.
	 * This is used to shake the targetted player character when it is hit by
	 * the enemy's attack.
	 * 
	 * This method is a proxy to the method in GamePanel.java, which actually
	 * performs the shake effect on the targetted player's sprite.
	 **/
	public static void setPlayerShake(boolean isShaking)
	{
		gamePanel.setPlayerShake(isShaking);
	}

	/**
	 * Sets the players shake boolean to the specified value.
	 * This is used to shake all player characters when the enemy attacks them.
	 * Specifically, this would be used for AoE (Area of Effect) attacks that
	 * hit all player characters at once.
	 * 
	 * This method is a proxy to the method in GamePanel.java, which actually
	 * performs the shake effect on the enemy sprite.
	 **/
	public static void setPlayersShake(boolean isShaking)
	{
		gamePanel.setPlayersShake(isShaking);
	}
	
	///Retrieves the saves from the "saveData.txt" file and switches to the "Saves" panel.
	public static void getSaves()
	{
		savesPanel.getSaves();
		switchCard("saves");
	}
	//progression methods 
	/**
	 * Executes a user move in the game panel.
	 * This method is called when the user has answered a question, and
	 * is a proxy to the method in GamePanel.java, which actually executes
	 * the user move based on whether or not the answer was correct.
	 **/
	public static void executeUserMove(boolean success)
	{
		gamePanel.executeUserMove(success);
	}

	/**
	 * Sets the number of enemies defeated to the specified value.
	 * This method is only used when loading a save.
	 **/
	public static void setEnemiesDefeated(int count)
	{
		enemiesDefeated = count;
	}

	///Increments the number of enemies defeated by 1.
	public static void incrementEnemiesDefeated()
	{
		enemiesDefeated++;
	}

	///Gets the number of enemies defeated.
	public static int getEnemiesDefeated()
	{
		return enemiesDefeated;
	}

	///Sets the currently viewed player character to the specified Character.
	public static void setPlayerCharacter(Character playerIn)
	{
		player = playerIn;
	}

	///Returns the currently viewed player character.
	public static Character getPlayerCharacter()
	{
		return player;
	}

	///Retrieves the problem from the "problems.txt" file and displays it.
	public static void getProblem()
	{
		problemPanel.getProblem();
		switchCard("problem");
	}
	
	///Refreshes the intermission panel's display of stats.
	public static void refreshStats()
	{
		intermissionPanel.refreshStats();
	}

	//USER INFORMATION STORAGE HANDLING METHODS
	
 	//image io 
	///Attempts to load the image from the designated file name.
	public static Image loadImage(String fileName)
	{
		File imgFile = new File(fileName); ///file to read from
		Image toReturn = null;
		
		try ///attempts to read in the image
		{
			toReturn = ImageIO.read(imgFile);
		}
		catch(IOException e) ///if file is not found or cannot be read
		{
			System.err.printf("Error: Could not load from file \"%s\".%n", fileName);
		}
		
		return toReturn;
	}

	/*
	 * Our game autosaves information and writes data constantly throughout the game. 
	 * saveContinues is if the user didn't die (game continues to save data). 
	 * If true is passed in, the save is updated and all saves
	 * are written to file.
	 * 
	 * If false is passed in, the current save will be removed, but
	 * the other saves will still be written to file.
	 */
	public static void writeData(boolean saveContinues)
	{
		if(saveContinues)
			updateSave(); ///updates current save prior to writing
		
		//standard file output logic structure 
		String fileName = "saveData.txt";
		File dataFile = new File(fileName);
		PrintWriter write = null;

		try
		{
			write = new PrintWriter(dataFile);
			
			//only writes it if there's a ready scanner to write to 
			writeDataToFile(saveContinues, write);
		}
		catch(IOException e)
		{
			//if cannot write to file (maybe read-only), system will print an error 
			System.err.printf("Error: Could not write to file \"%s\".%n", fileName);
		}
	}
	/*
	 * Helper method to writeData(); it takes in a ready scanner and writes 
	 * all the data into the file. 
	 * encapsulates writing to file in another method. 
	 */
	 private static void writeDataToFile(boolean saveContinues, PrintWriter write)
	 {
		 //only saves user information to save file if there is a 
				//file to write to AND saveList has something in it, otherwise 
				//prints to console/powershell and wipes possibly saved data. 
		if(saveList.size() < 1)
		{
			System.out.println("Deleting stuff!");
			write.println("No save found.");
		}
		else
		{
			//write/save all user information 
			//iterate through saveList, a list of different user's saves 
			for(int index=0; index<saveList.size(); index++)
			{
				//if saveContinues and the save the user currently uses 
				if(saveContinues && index == saveIndex)
				{
					//write/save user information 
					write.println(userName);
					write.println(enemiesDefeated);

					for(int i=0; i<playerChars.length; i++)
					{
						//write/save character information 
						Character plrChar = playerChars[i];
		
						write.println(plrChar.getName());
						write.println(plrChar.getLevel());
						write.println(plrChar.getHP() + "/" + plrChar.getMaxHP());
						write.println(plrChar.getMana() + "/" + plrChar.getMaxMana());
						write.println(plrChar.getDefense());
					}
				}
				else if(index != saveIndex)//if either of the conditions are false and saveIndex doesn't match 
				{
					//still write the user's data 
					write.println(saveList.get(index).getUserName());
					write.println(saveList.get(index).getEnemiesDefeated());
					Character[] plrChars = saveList.get(index).getPlayerCharacters();
					
					//still write the character information 
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
				//else: only the case (index == saveIndex && !saveContinues)
				//AKA if the current (correct) user died. 
				//if that is the case, don't resave anything, because 
				//you've already saved all the information. 
			}
		}
		//close printwriter 
		write.close();
	 }
	
	/**
	 * Sets the current save to the current game data, or it
	 * adds a new one if the save wasn't saved prior.
	 */
	public static void updateSave()
	{
		///updates the current save
		Save currSave = new Save(userName, enemiesDefeated, playerChars);

		///if the save already exists, updates it
		if(saveList.size() > 0)
			saveList.set(saveIndex, currSave);
		else ///if there are no saves, adds the current save
		{
			saveList.add(currSave);
			setSaveIndex(0);
		}
	}

	/**
	 * Writes the high score to the "highscores.txt" file.
	 * If the current player's score is higher than any of the
	 * existing scores, it will be added to the file.
	 **/
	public static void writeHighScore()
	{
		//standard file output logic 
		String fileName = "highscores.txt";
		File dataFile = new File(fileName);
		PrintWriter write = null;
		Scanner read = null;

		try
		{
			//initialize scanner and printwriter to the same highscores text file 
			write = new PrintWriter(dataFile);
			read = new Scanner(dataFile);
			
			//determine what to write, given the printwriter and scanner both 
			String toWrite = "";
			boolean added = false;
			//as long as there is still data in the scanner 
			while (read.hasNext())
			{
				//read in the next line in scanner 
				String line = read.nextLine();
				
				//only continue if there are high scores. 
				if(!line.equals("No high scores."))
				{
					//get user score 
					int score = Integer.parseInt(GameData.dataAfter(line, "- "));
					
					//if added is false and enemies defeated is greater than current score 
					if(!added && enemiesDefeated > score)
					{
						//add user information to file at this point in the file 
						toWrite += userName + " - " + enemiesDefeated + "\n";
						for(int i=0; i<playerChars.length; i++)
						{
							toWrite += playerChars[i].getName() + " - Level " + playerChars[i].getLevel() + "\n";
						}
						added = true;
					}
					
					//keep the line in toWrite, move on in the file 
					toWrite += line;
					
					//keep the next 3 lines in read scanner. 
					for(int i=1; i<=3; i++)
					{
						if(read.hasNextLine())//precautionary measure
							toWrite += read.nextLine();
						else
							System.out.println("Warning: High scores " + 
								"scanner ran out of lines while parsing.");
					}
				}
				else
					toWrite += "No high scores.";//keeps the line 
			}
			
			//rewrites file to the string 
			write.println(toWrite);
			//close scanner 
			write.close();
		}
		catch(IOException e)
		{
			//print error to console 
			System.err.printf("An error ocurred with the file \"%s\".%n" + 
				"%tEither the PrintWriter or Scanner failed to initialize properly.%n", fileName);
		}
	}
	
	//SAVE INFORMATION
	///Initializes the list of saves.
	public static void setSaveList(ArrayList<Save> savesIn)
	{
		saveList = savesIn;
	}

	///Returns the list of saves.
	public static ArrayList<Save> getSaveList()
	{
		return saveList;
	}

	///Returns the current save index.
	public static void setSaveIndex(int indexIn)
	{
		saveIndex = indexIn;
	}
	
	//more game-progression type save related methods 
	///Sets the username to the specified value.
	public static void setUserName(String userNameIn)
	{
		userName = userNameIn;
	}

	///Returns the username.
	public static String getUserName()
	{
		return userName;
	}
	
	///Sets the game started boolean to the specified value.
	public static void setGameStarted(boolean gameStartedIn)
	{
		gameStarted = gameStartedIn;
	}

	///Returns whether or not the game has started.
	public static boolean gameIsStarted()
	{
		return gameStarted;
	}

	///Sets the demo mode boolean to the specified value.
	public static void setDemoMode(boolean isOn)
	{
		demoMode = isOn;
	}

	/**
	 * Returns whether or not the demo mode is on.
	 * If it is on, the game will execute player moves, regardless
	 * of whether or not their answer is correct.
	 * 
	 * By default, demo mode should be turned off, as it is only really
	 * used for testing purposes.
	 **/
	public static boolean isDemoModeOn()
	{
		return demoMode;
	}
	
	///Starts the game.
	public static void startGame(boolean isTutorial)
	{
		switchCard("game");
		gamePanel.start(isTutorial);
	}

	//USEFUL UTILITY METHODS
	/**
	 * An alternative to Math.random() that returns a random integer
	 * between the specified low and high values, inclusive.
	 **/
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
		///if the regex is not found, return the same String
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
		///if the regex is not found, return the same String
		if(str.indexOf(regex) == -1)
		{
			return str;
		}

		return str.substring(0, str.indexOf(regex));
	}
 }

/*
 * Represents a save in the game, containing the player's username,
 * the number of enemies defeated, and the player's characters.
 * Simply a getter/setter in the class at the moment. Not much else. 
 */
class Save
{
	private String userName;			//saves user name 
	private int enemiesDefeated;		//saves number of enemies defeated 
	private Character[] characters;		//saves user characters 

	/**
	 * Creates a new Save with the specified username, number of enemies defeated,
	 * and player characters.
	 */
	public Save(String userNameIn, int defeatedIn, Character[] charsIn)
	{
		//initialize fvs with parameters 
		userName = userNameIn;
		enemiesDefeated = defeatedIn;
		characters = charsIn;
	}

	///Returns the username of the save.
	public String getUserName()
	{
		return userName;
	}

	///Returns the number of enemies defeated in the save.
	public int getEnemiesDefeated()
	{
		return enemiesDefeated;
	}

	///Returns the player characters in the save.
	public Character[] getPlayerCharacters()
	{
		return characters;
	}
}
