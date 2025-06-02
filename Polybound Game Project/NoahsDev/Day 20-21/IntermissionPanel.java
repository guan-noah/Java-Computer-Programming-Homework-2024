/**
 * Krish Kumar
 * Period 6
 * IntermissionPanel.java 
 * 
 * The intermission panel, which allows players to continue on to another
 * battle, or to exit back to the main menu. It also displays the
 * player's current stats.
 **/

///import libraries
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

public class IntermissionPanel extends JPanel
{
	private JTextArea infoTextArea;//the text area for user information 
	private StatsPanel statsPanel;//the large drawing area; also displays user stats
	private Label charLabel;//the current character user is looking at data for
	
	/*
	 * constructor to initialize intermissionpanel 
	 */
	public IntermissionPanel()
	{
		//initialize intermission panel with border layout 
		setLayout(new BorderLayout());
		
		//initialize panels; call methods to initialize if needed 
		JPanel selectionPanel = getSelections();
		JScrollPane infoPane = getPlayerInfo();
		statsPanel = new StatsPanel();
		
		//add panels to intermission panel in borderlayout 
		add(infoPane, BorderLayout.EAST);
		add(selectionPanel, BorderLayout.SOUTH);
		add(statsPanel, BorderLayout.CENTER);
	}
	
	/*
     * This method is responsible for the creation of the
	 * options to continue the game/return to menu, as well
	 * as the panel that holds them.
	 * This panel holds the components in the south. 
     */
	public JPanel getSelections()
	{
		//create bottom panel with flowlayout 
		JPanel toReturn = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
		int buttonFont = 55;
		//create button handler 
		SelectionHandler selectHandler = new SelectionHandler();
		//create the navigation on this slide (to see different user character info)
		Button prevCharacterBtn = new Button("<", selectHandler, buttonFont);
		//the label that shows you which user character data you currently see 
		charLabel = new Label("Character", 40);
		//create buttons with handler 
		Button nextCharacterBtn = new Button(">", selectHandler, buttonFont);
		Button continueButton = new Button("Continue", selectHandler, buttonFont);
		Button returnButton = new Button("Return to Menu", selectHandler, buttonFont);
		
		//set label text to white 
		charLabel.setForeground(Color.WHITE);
		//set bottom panel to dark gray 
		toReturn.setBackground(Color.DARK_GRAY);
		//add all components 
		toReturn.add(prevCharacterBtn);
		toReturn.add(charLabel);
		toReturn.add(nextCharacterBtn);
		toReturn.add(continueButton);
		toReturn.add(returnButton);
		
		return toReturn;
	}
	
	/*
	 * This method sets up the player information jtextarea on the right. 
	 */
	public JScrollPane getPlayerInfo()
	{
		///creates new JTextArea and JScrollPane
		infoTextArea = new JTextArea(30, 18);//fv
		JScrollPane toReturn = new JScrollPane(infoTextArea);
			
		///text area setup
		infoTextArea.setFont(new Font("Share Tech Regular", Font.PLAIN, 25));
		infoTextArea.setBackground(Color.DARK_GRAY);//dark gray background 
		infoTextArea.setForeground(Color.WHITE);//white text. 
		infoTextArea.setLineWrap(true);
		infoTextArea.setWrapStyleWord(true);
		infoTextArea.setEditable(false);//user cannot edit 
			
		return toReturn;
	}
	
	/*
	 * This method calls the paintComponent of the center panel. It refreshes
	 * the stats you see in the main large panel. 
	 */
	public void refreshStats()
	{
		statsPanel.repaint();
	}
	
	/*
	 * This method updates the player information in the class to draw to
	 * the panel. 
	 */
	public void updatePlayerInfo()
	{
		//get user information 
		String userName = GameData.getUserName();
		int enemiesDefeated = GameData.getEnemiesDefeated();
		Character player = GameData.getPlayerCharacter();
		
		//set the navigation bottom panel label to the current character's name 
		charLabel.setText(player.getName());
		
		//set current character info in text area 
		infoTextArea.setText(userName + "\n" + "Defeated " + enemiesDefeated +
			" enemies\n\n" + player.getName() + "\nLevel " + player.getLevel() +
			"\n\n" + player.getDescription() +
			"\n\nMoves:\n");
		
		//get user character moveset and input into arraylist 
		ArrayList<String> moveSet = player.getMoveset();
		int bound = player.getLevel()/3 + 3;//bound = minimum 3, + 1/3 of the player level 
		if(bound > moveSet.size())//restrict bound to the size of the player's moveset 
		{
			bound = moveSet.size();
		}
		
		//iterate through moveset, bound + 1 times. 
		for(int i=0; i<bound; i++)
		{
			String moveName = moveSet.get(i);//get each move name iterated through 
			Move move = new Move(moveName);//create a move based on the name 
			//add information to the text area 
			infoTextArea.append(moveName + " - " + move.getDescription() + "\n\n");
		}
	}
	
	/**
     * This class is responsible for handling the options
	 * to continue/return to menu.
     **/
	class SelectionHandler implements ActionListener
	{
		/**
     	 * This method is responsible for deciding what to do
		 * based on which option was selected.
    	 **/
		public void actionPerformed(ActionEvent evt)
		{
			//get the name of the component 
			String command = evt.getActionCommand();
			
			//based on the name of the component, do different actions 
			if(command.equals("Continue"))
			{
				//start game (pass in boolean: has user defeated any enemies yet AKA tutorial or not) 
				GameData.startGame(GameData.getEnemiesDefeated() < 1);
				//show game panel 
				GameData.switchCard("game");
			}
			else if(command.equals("Return to Menu"))
			{
				//show main menu 
				GameData.switchCard("main menu");
			}
			else if(command.equals("<"))
			{
				//switch to the character with 1 less degree than current one 
				GameData.decrementCharViewing();
				refreshStats();//call paintcomponent of drawing center panel 
			}
			else if(command.equals(">"))
			{
				//switch the character with 1 more degree than current one
				GameData.incrementCharViewing();
				refreshStats();//call paintcomponent of drawing center panel 
			}
		}
	}
	
	/**
	 * This class is a JPanel, responsible for displaying
	 * the player's current stats.
	 */
	class StatsPanel extends JPanel
	{
		private Image hpIcon;
		private Image manaIcon;
		private Image defIcon;
		
		/*
		 * initializes the icons to draw and the background color 
		 */
		public StatsPanel()
		{
			setBackground(Color.GRAY);
			//3 images to draw 
			hpIcon = GameData.loadImage("healthIcon.png");
			defIcon = GameData.loadImage("defenseIcon.png");
			manaIcon = GameData.loadImage("manaIcon.png");
		}
		
		/**
		 * This is the paintComponent() method inherited from JPanel, overrided
		 * to display the stat icons as well as the stats themselves.
	 	**/
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			//set the font for strings drawn 
			g.setFont(new Font("Share Tech Regular", Font.BOLD, 100));
			
			Character player = GameData.getPlayerCharacter();
			
			//draws the user character name 
			g.drawString(player.getName(), 20, 120);
			
			//draws the images and the labels with user information in it. 
			//very large! takes up the center of the intermission panel. 
			g.drawImage(hpIcon, -80, 100, 311, 216, this);
			g.drawImage(manaIcon, -80, 250, 311, 216, this);
			g.drawImage(defIcon, -80, 400, 311, 216, this);
			g.drawString(player.getHP() + "/" + player.getMaxHP() + " HP", 160, 260);
			g.drawString(player.getMana() + "/" + player.getMaxMana() + " Mana", 160, 400);
			g.drawString(player.getDefense() + " Defense", 160, 540);
			updatePlayerInfo();
		}
	}
}
