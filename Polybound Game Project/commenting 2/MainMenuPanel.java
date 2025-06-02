/**
 * Krish Kumar and Noah Guan
 * Period 6
 * MainMenuPanel.java
 * 
 * This class is a JPanel for the main menu that appears when you open
 * the game. It contains the navigation buttons, and displays the game
 * logo as well.
 **/

///import libraries
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMenuPanel extends JPanel
{
	private Image logo; ///Image for the game logo
	private InfoPopup helpPopup; ///InfoPopup for the "Help" popup
	private InfoPopup highScorePopup; ///InfoPopup for the "High Scores" popup
	private OptionsPopup optionsPopup;
	private GameFoundPopup gameFoundPopup;
	
	/**
	 * The default constructor, responsible for setting up this
	 * instance of MainMenuPanel. Specifically, the layout is set to null,
	 * the game logo is loaded, and the components that are to be displayed
	 * are set up and added.
	 **/
	public MainMenuPanel()
	{
		setLayout(null); ///sets layout to null layout
		
		logo = GameData.loadImage("polyboundLogo.jpg"); ///loads game logo
		
		///gets the popups
		helpPopup = getHelpPopup();
		highScorePopup = getHighScorePopup();
		optionsPopup = getOptionsPopup();
		gameFoundPopup = new GameFoundPopup();
		
		///button setup
		int buttonFont = 45; ///font size for buttons
		int buttonWidth = 100; ///width for buttons
		MainMenuButtonHandler mmBtnHandler = new MainMenuButtonHandler();
		Button startButton = new Button("START", mmBtnHandler, buttonFont);
		Button optionsButton = new Button("OPTIONS", mmBtnHandler, buttonFont);
		Button quitButton = new Button("QUIT", mmBtnHandler, buttonFont);
		Button highScoreButton = new Button("HI-SCORES", mmBtnHandler, buttonFont);
		Button helpButton = new Button("HELP", mmBtnHandler, buttonFont);
		
		///sets bounds of buttons
		startButton.setBounds(400, 410, 400, buttonWidth);
		optionsButton.setBounds(400, 510, 400, buttonWidth);
		quitButton.setBounds(400, 610, 400, buttonWidth);
		highScoreButton.setBounds(50, 350, 300, buttonWidth);
		helpButton.setBounds(850, 350, 300, buttonWidth);
		
		///adds Buttons to panel
		add(startButton);
		add(optionsButton);
		add(quitButton);
		add(highScoreButton);
		add(helpButton);
		
		setBackground(Color.GRAY); ///sets background color to gray
	}
	
	/*
	 * This method is responsible for setting up the InfoPopup that will
	 * be the "Options" popup.
	 */
	public OptionsPopup getOptionsPopup()
	{
		OptionsPopup toReturn = new OptionsPopup(); //creates new OptionsPopup
		//add implementation last if time permits 
		return toReturn;
	}
	
	/**
	 * This method is responsible for setting up the InfoPopup that will
	 * be the "Help" popup.
	 **/
	public InfoPopup getHelpPopup()
	{
		InfoPopup toReturn = new InfoPopup("Help"); ///creates new InfoPopup
		toReturn.setContent("Welcome to Polybound!\n\nThis game is a turn-based " +
			"RPG style game where you must take on the role of one of three characters, " +
			"and use your polynomial skills to save the world from the Polygon army.\n\n" +
			"In Polybound, you will be tested on a few skills concerning polynomials, " +
			"including polynomial arithmetic, end behavior, factoring, and the Polynomial " +
			"Remainder Theorem.\n\nTo begin playing, press the START button. If this is " +
			"your first time playing, you will be prompted to create a new game. If you " +
			"have played before, you will be prompted to load your game.\n\nOnce your " +
			"game is loaded, you will be taken to the game screen, where you will be able " +
			"to view information about your character. When you're ready, press the " +
			"Continue button, and enjoy the game!\n\nIf you would like to see the lore for the " +
			"game, refer to below.\n\nLore:\nThe Cartesian plane was a peaceful place, " +
			"where polynomials and shapes lived in harmony. However, one day, that all changed.\n\n" +
			"It is unknown what caused the Great Split; some say it was a " +
			"polynomial gone rogue, while others say it was a shape that was too " +
			"powerful. Some even whispered ideas of it being a circle.\n\nRegardless, " +
			"the Great Split caused the Cartesian plane to split in two, the Polynomial Federation " +
			"and the Polygon Empire.\n\nWithin the Polygon Empire, the Square Overlord rules " +
			" with an iron fist, and has sent out his minions to destroy the Polynomial Federation. " +
			"Circles in the empire also suffered heavy discrimination from polygons, now yearning " +
			"for the day they re-achieve autonomy.\n\nThe Polynomial Federation is now in all-out war " +
			"with the Polygon Empire, and it is up to you to save the day. You must defeat the seemingly " +
			"unstoppable Polygon army, and restore peace to the Cartesian plane.");

		
		return toReturn;
	}
	
	/**
	 * This method is responsible for setting up the InfoPopup that will
	 * be the "High Scores" popup.
	 **/
	public InfoPopup getHighScorePopup()
	{
		InfoPopup toReturn = new InfoPopup("High Scores"); ///new InfoPopup
		
		return toReturn;
	}
	
	/**
	 * This is the paintComponent() method inherited from JPanel, overrided
	 * to display the game's logo.
	 **/
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); ///superclass paintComponent() call
		
		Graphics2D g2d = (Graphics2D) g; ///cast Graphics to Graphics2D
		g2d.drawImage(logo, 400, 100, this); ///draws the image
	}
	
	/**
	 * This class is responsible for handling the main menu buttons.
	 **/
	class MainMenuButtonHandler implements ActionListener
	{
		/**
		 * This method is called when a main menu button is pressed,
		 * and determines what to do depending on which button was pressed.
		 **/
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand(); ///gets action command

			if(command.equals("HELP")) ///shows the "Help" popup
			{
				helpPopup.show();
			}
			else if(command.equals("HI-SCORES")) ///shows the "High Scores" popup
			{
				//standard file io logic
				String fileName = "highscores.txt";
				File dataFile = new File(fileName);
				Scanner read = null;
				//for adding to popup data
				String setContent = "";

				try
				{
					read = new Scanner(dataFile);
					
					//only executes if scanner initialized successfully 
					setContent = "High Scores by Enemies Defeated:\n\n"; ///initializes content
					String line = read.nextLine();//get line from high scores
					if(!line.equals("No high scores."))
					{
						//parse line into set content 
						setContent += line + "\n";
						//while scanner has next 
						while(read.hasNext())
						{
							//parse rest of lines into set content 
							setContent += read.nextLine() + "\n";
						}
					}
					else
					{
						//default no high scores message 
						setContent = "No high scores :(\nPlay a game to achieve one! :D";
					}
				}
				catch(FileNotFoundException e)
				{
					//error 
					setContent = "Oh no! We couldn't load your high " + 
						"score data!\n\t(Error: Couldn't access highScores.txt)";
				}
				
				//set high score popup content to setContent  
				highScorePopup.setContent(setContent); 
				//show high score popup 
				highScorePopup.show();
			}
			else if(command.equals("QUIT")) ///quits the game
			{
				//~ gameTurnInfo.append("\n\t(Exit button pressed.)"); //in the future, uncomment with the popup; 
					//~ commented out right now because of program inefficiency if kept
				//~ in the future, we'll have a popup (are you sure you want to exit?)
				System.exit(0);
			}
			else if(command.equals("START")) ///switch to intermission panel
			{
				//show game found popup if already a game started 
				if(GameData.gameIsStarted())
				{
					gameFoundPopup.show();
				}
				else
				{
					GameData.switchCard("user info");
				}
			}
			else if(command.equals("OPTIONS")) ///shows the "Options" popup
			{
				optionsPopup.show();
			}
		}
	}
}