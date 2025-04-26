/**
 * Krish Kumar
 * Period 6
 * MainMenuPanel.java
 * 
 * This class is a JPanel for the main menu that appears when you open
 * the game. It contains the navigation buttons, and displays the game
 * logo as well.
 * 
 * NOTE: THIS CLASS IS NOT COMPLETE!
 **/

///import libraries
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.CardLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends JPanel
{
	private Image logo; ///Image for the game logo
	private InfoPopup helpPopup; ///InfoPopup for the "Help" popup
	private InfoPopup highScorePopup; ///InfoPopup for the "High Scores" popup
	private CardLayout cards; ///Field to store the main CardLayout
	
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
		cards = GameData.getCardLayout(); ///gets main CardLayout
		
		///gets the popups
		helpPopup = getHelpPopup();
		highScorePopup = getHighScorePopup();
		
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
	
	/**
	 * This method is responsible for setting up the InfoPopup that will
	 * be the "Help" popup.
	 **/
	public InfoPopup getHelpPopup()
	{
		InfoPopup toReturn = new InfoPopup("Help"); ///creates new InfoPopup
		toReturn.setContent("placeholder"); ///currently sets content to placeholder
		
		return toReturn;
	}
	
	/**
	 * This method is responsible for setting up the InfoPopup that will
	 * be the "High Scores" popup.
	 **/
	public InfoPopup getHighScorePopup()
	{
		InfoPopup toReturn = new InfoPopup("High Scores"); ///new InfoPopup
		toReturn.setContent("placeholder"); ///currently sets content to placeholder
		
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
				highScorePopup.show();
			}
			else if(command.equals("QUIT")) ///quits the game
			{
				System.exit(0);
			}
			else if(command.equals("START"))
			{
				CardLayout cards = GameData.getCardLayout();
				JPanel holder = GameData.getCardHolder();
				cards.show(holder, "intermission");
			}
		}
	}
}
