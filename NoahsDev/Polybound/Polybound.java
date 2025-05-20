/*
 * Project started 4/23/2025
 * 
 * Noah Guan and Krish Kumar
 * Period 6
 * Polybound.java
 * 
 * The class with main(), creates the JFrame, CardLayout, and generally
 * sets up the rest of the game.
 * 
 * NOTE: THIS CLASS IS VERY MUCH SUBJECT TO CHANGE!
 */
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Polybound
{
	///main method, calls run()
	public static void main(String[] args)
	{
		System.out.println("\n\n\n");
		Polybound polyGame = new Polybound();
		polyGame.run();
		System.out.println("\n\n\n");
	}

	///CardLayout stuff by Krish, rest by Noah
	///Sets up the game
	public void run()
	{
		///this is some debug code for Characters
		//~ Character[] ch = new Character[10];
		//~ for(int i=0; i<ch.length; i++)
		//~ {
			//~ ch[i] = new Character("Circle");
			//~ ch[i].print();
			//~ System.out.println();
		//~ }
		
		//gives the user a demo upon start 
		GameData.setDemoMode(true);
		
		//the rest of the method sets up the main jframe with its panels. 
		JFrame frame = new JFrame("Polybound");
		//the cardlayout with all the panels inside 
		JPanel deck = new JPanel(new CardLayout());
		//initializes all panels 
		MainMenuPanel mPanel = new MainMenuPanel();
		IntermissionPanel imPanel = new IntermissionPanel();
		GamePanel gPanel = new GamePanel();
		ProblemPanel pPanel = new ProblemPanel();
		SelectUserInfoPanel uInfoPanel = new SelectUserInfoPanel();
		
		//get saved data 
		checkForData();
		
		//pass all cardlayout information to GameData 
		GameData.setCards(deck);
		GameData.setProblemPanel(pPanel);//developer's note: 
		GameData.setGamePanel(gPanel);
		GameData.setIntermissionPanel(imPanel);
		
		//add all panels to cardlayout 
		deck.add(mPanel, "main menu");
		deck.add(uInfoPanel, "user info");
		deck.add(imPanel, "intermission");
		deck.add(gPanel, "game");
		deck.add(pPanel, "problem");
		
		frame.setSize(1200, 750);	//normal: 600, 500
		frame.setResizable(false);		//user can't change size 
		frame.setLocationRelativeTo(null); //centers everything
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		//set background of frame to cardlayout 
		frame.setContentPane(deck);
		frame.setVisible(true);
	}
	/*
	 * This method loads previous game data into current game via saveData.txt, 
	 * the game's save file. Utilizes loadToGameData. 
	 */
	public void checkForData()
	{
		String fileName = "saveData.txt";
		File dataFile = new File(fileName);
		Scanner read = null;
		try
		{
			read = new Scanner(dataFile);

			loadToGameData(read);
		}
		catch(FileNotFoundException e)
		{
			System.err.printf("Error: Could not find file \"%s\"", fileName);
		}
	}
	/*
	 * This method loads data from scanner into GameData. 
	 * helper method to checkForData, helps with encapsulation. 
	 */
	public void loadToGameData(Scanner read)
	{
		String line = read.nextLine();
		System.out.println("Line: \"" + line + "\"");
		if(!line.equals("No save found."))
		{
			GameData.setUserName(line); ///sets username
			
			String charName = read.nextLine();

			int level = read.nextInt();
			int enemiesDefeated = read.nextInt();
			read.nextLine();

			line = read.nextLine();
			int hp = Integer.parseInt(GameData.getDataTo(line, "/"));
			int maxHP = Integer.parseInt(GameData.dataAfter(line, "/"));

			line = read.nextLine();
			int mana = Integer.parseInt(GameData.getDataTo(line, "/"));
			int maxMana = Integer.parseInt(GameData.dataAfter(line, "/"));

			int defense = read.nextInt();

			Character playerChar = new Character(charName, level);
			playerChar.overrideStats(hp, maxHP, mana, maxMana, defense);
			GameData.setEnemiesDefeated(enemiesDefeated);
			GameData.setPlayerCharacter(playerChar);
			GameData.setGameStarted(true);
		}
		else
		{
			GameData.setGameStarted(false);
		}
	}
}
