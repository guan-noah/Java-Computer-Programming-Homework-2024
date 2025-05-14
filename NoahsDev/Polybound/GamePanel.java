/*
 * Noah Guan
 * Period 6
 * GamePanel.java
 */
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
public class GamePanel extends JPanel
{
	//Field vars 
		
	private JTextArea gameTurnInfo;			//CENTER; jtextarea at bottom
	private GameUI gameUI;			//SOUTH; game ui (w/ paintComponent)
	//goes in gamePanel - user & enemy image sprite, 0 & 1 respectively
	private Character user, enemy;					//GameUI information
	private Color gamePanColor;
	private JPanel sideInfoPan;						//WEST; info panel 
	
	//these go in the west sideInfoPan 
	private UserInfo uInfo;		//JPanel for userInfo (w/ paintComponent) 
	private JMenuBar moves;				//Options arrangement components 
	
	/* Constructor for GamePanel 
	 - Makes the 3 main panels and applies it to a BorderLayout 
	 - Initializes the component/other needed variables 
	 - Connects the components together 
	 */
	 //CONSTRUCTOR AND ALL INITIALIZER METHODS LISTED BELOW.
	public GamePanel()
	{
		setLayout(new BorderLayout());
		
		gamePanColor = Color.GRAY/*new Color()*/;//note: we will add a color later
		enemy = null /*new Character()*/;
		user = null /*new Character()*/;
		
		getGameUI();								//makes the game pan
		add(gameUI, BorderLayout.CENTER);					//adds panel
		
		getInfoPan();										//same logic
		add(sideInfoPan, BorderLayout.WEST);
		
		///add variable or smth idk
		getGameTurnInfo();
		JScrollPane scrollGameTurnInfo = new JScrollPane(gameTurnInfo);
		add(scrollGameTurnInfo, BorderLayout.SOUTH);
	}
	/* initialize */
	public void getGameUI()
	{
		gameUI = new GameUI(); ///kept like this for development purposes
		gameUI.setPreferredSize(new Dimension(1000, 550));//follows game proposal pseudocode
		gameUI.setBackground(gamePanColor);
	}
	/* initializes the information panel */
	public void getInfoPan()
	{
		sideInfoPan = new JPanel(new BorderLayout());
		sideInfoPan.setPreferredSize(new Dimension(200, 550));
		
		uInfo = new UserInfo();				//initialize user info panel
		uInfo.setPreferredSize(new Dimension(200, 150));
		sideInfoPan.add(uInfo, BorderLayout.NORTH);
		
		//holder for GridLayout of menu + buttons (0 gap between buttons for now)
		JPanel options = new JPanel(new GridLayout(3, 1, 0, 0));
		options.setPreferredSize(new Dimension(200, 400));
		getOptions(options);
		sideInfoPan.add(options, BorderLayout.SOUTH);
		
	}
	/* helper initializer method to getInfoPan's options JPanel */
	public void getOptions(JPanel optionsIn)
	{
		//initialize panel that moves will be centered in (workshopped idea) -- right now moves is a bit to the side 
		//~ JPanel centerMoves = new JPanel(new FlowLayout(FlowLayout.CENTER));
		moves = createMenuBar();					//initialize moves
		moves.setPreferredSize(new Dimension(200, 400));
		moves.setBackground(Color.CYAN);
		//~ centerMoves.add(moves); //workshopped idea 
		optionsIn.add(moves /*centerMoves*/);
		//for adding, optionsIn reference should still point to the same JPanel object; should be fine
		
		JButton guide = new JButton("Guide");			//guide button
		guide.addActionListener(new ShowOther());
		guide.setPreferredSize(new Dimension(200, 266));
		guide.setOpaque(true);					//prep for background color
		guide.setBackground(Color.YELLOW);
		optionsIn.add(guide);
		
		JButton exit = new JButton("Exit");				//exit button
		exit.addActionListener(new ShowOther());
		exit.setPreferredSize(new Dimension(200, 266));
		exit.setOpaque(true);					//prep for background color
		exit.setBackground(Color.RED);
		optionsIn.add(exit);
		
		/*
		 * workshopped idea: instead of exit, make a Quit Game button that 
		 * makes a popup; popup has 2 options: either start over - link to main menu
		 * ...or actually quit program - System.exit(0)
		
		JButton quit = new JButton("Quit Game");
		quit.addActionListener(new ShowOther());
		optionsIn.add(quit);
		*/
	}
	/* initializes the south jtextarea */
	public void getGameTurnInfo()
	{
		gameTurnInfo = new JTextArea("Welcome to the GamePanel! Play the game in this panel.");
		gameTurnInfo.setPreferredSize(new Dimension(1200, 200));
		gameTurnInfo.setEditable(false);
	}
	/* initializes the menubar */
	public JMenuBar createMenuBar()
	{
		JMenuBar outputMenuBar = new JMenuBar();
		outputMenuBar.setPreferredSize(new Dimension(200, 268));
		outputMenuBar.setBackground(Color.CYAN);
		outputMenuBar.add(createMoveSetMenu());
		
		return outputMenuBar;
	}
	/* initializes the menu with the menuitems*/
	public JMenu createMoveSetMenu()
	{
		JMenu outputMenu = new JMenu("Moves");
		//~ for each move in Moves file: 
			//~ JMenuItem tempMenuItem = getMenuItem(enemy.getMoveSet(index));
			//~ tempMenuItem.addActionListener(new MovesListener());
			//~ outputMenu.add(tempMenuItem));
		for (int i = 0; i < 3; i++)
		{
			JMenuItem temp = getMenuItem("Move " + i);
			outputMenu.add(temp);
		}
		
		return outputMenu;
	}
	/* intializes each item in menu */
	public JMenuItem getMenuItem(String menuItemName)
	{
		JMenuItem item = new JMenuItem(menuItemName);//we can add an int mnemonic if we want 
		item.addActionListener(new MovesListener());
		return item;
	}
	
	//ALL GAME FUNCTION METHODS LISTED BELOW. 
	public void showProblem()
	{
		CardLayout cards = GameData.getCardLayout();
 		JPanel holder = GameData.getCardHolder();
 		
		ProblemPanel problemPanel = GameData.getProblemPanel();
		problemPanel.getProblem();//gets problempan from gamedata and gets problem from it
		
		cards.show(holder, "problem");//shows actual panel
	}
	
	//ALL CLASSES LISTED BELOW. 
	/* GameUI; CENTER */
	class GameUI extends JPanel
	{
		public GameUI()//should never be used; just kept as a safety crutch
		{
			System.out.println("GameUI initialized without user name " + 
				"(kept for programming purposes)");
		}
		public GameUI(String userSpriteNameIn)
		{
			//no layout (as of now)
			loadUser(userSpriteNameIn);		//loads the user character
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			//~ int xLoc; //fixed location for now 
			//~ int yLoc;
			
			//x, y, width, height
			//draws user image
			//~g.drawImage(user.getImage(), 200, 150, 200, 200, this);
			g.setFont(new Font("Times New Roman", Font.BOLD, 25));
			g.setColor(Color.BLUE);
			
			g.fillRect(200, 150, 200, 200);//temporary placeholder
			//draws enemy image
			//~g.drawImage(enemy.getImage(), 600, 150, 200, 200, this);
			g.fillRect(600, 150, 200, 200);//temporary placeholder
			
			g.setColor(Color.MAGENTA);
			g.drawString("User", 200, 150); //draw user and enemy labels
			g.drawString("Enemy", 600, 150);
		}
		/*as of now, only used the first time GameUI is called*/
		public void loadUser(String userSpriteName)
		{
			user = new Character(userSpriteName);
		}
		/*overloaded method; used when we're changing enemy sprite*/
		public void loadEnemy(String enemyName)
		{
			enemy = new Character(enemyName);
		}
	}
	/* User Info panel; SOUTH */
	class UserInfo extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setFont(new Font("Monospaced", Font.BOLD, 25));
			//can also be "Serif"
			//cannot get hp and mana at the moment as user is not set 
			int userHP = 0;
			int userMana = 0;
			if(user != null)
			{
				userHP = user.getHP();
				userMana = user.getMana();
			}
			else
			{
				g.setColor(Color.RED);
				g.drawString("Warning: user = null (GamePanel.java)", 0, 25);
			}
			g.drawString(("HP:" + userHP), 25, 75);
			g.drawString(("Mana:" + userMana), 25, 125);
			
			//placeholders 
		}
	}
	class MovesListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String moveName = evt.getActionCommand();

			///integrate later, for now here for testing
			ProblemPanel problemPanel = GameData.getProblemPanel();
			problemPanel.getProblem();
			CardLayout cards = GameData.getCardLayout();
			JPanel holder = GameData.getCardHolder();
			cards.show(holder, "problem");
			if(enemy != null)
			{
				GameProgression.executeMove(moveName, user.getMana());
				gameTurnInfo.append("\n"+ moveName + " was executed by " + GameProgression.whoseTurn(enemy.getName()));
			}
			else
				gameTurnInfo.append("\n\tWarning: user set to null. Move " + moveName + " could not be executed.");
		}
	}
	class ShowOther implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			
			if(command.equals("Guide"))//copied from IntermissionPanel; 
			//~ in the future, we'll create another popup for a game guide. 
			//~ right now, it just sends the user back to the main menu 
			{
				gameTurnInfo.append("\n\t(Guide button pressed.)");
				CardLayout cards = GameData.getCardLayout();
				JPanel holder = GameData.getCardHolder();
				cards.show(holder, "main menu");
				///add actual code later
			}
			else if(command.equals("Exit")) //if user pressed exit button, exit game and print to console. 
			{
				//~ gameTurnInfo.append("\n\t(Exit button pressed.)"); //in the future, uncomment with the popup; 
					//~ commented out right now because of program inefficiency if kept
				//~ in the future, we'll have a popup (are you sure you want to exit?)
				//~ (for debugging purposes)
				CardLayout cards = GameData.getCardLayout();
				JPanel holder = GameData.getCardHolder();
				cards.show(holder, "intermission");
			}
			/* extension of workshopped idea: 
			 * 
			else if(command.equals("Quit Game"))
			{
				make new popup with exit and main menu panel linked to buttons 
			}
			*/
		}
	}
	
}
/* This static class holds the game information. */
class GameProgression 
{
	private static int movesExecuted;
	private static boolean isUserTurn;
	//~ private static Character enemy; //don't add any Characters to this class; 
	//~ input parameters instead. this class is the game PROGRESSION, not the game panel. 
	
	//from here on are all the set methods. 
	/*
	 * This method sets all values to a prime state, much like a constructor. 
	 */
	public static void resetValues()
	{
		movesExecuted = 0;
		isUserTurn = true;
	}
	/* 
	 * This method sets user turn boolean to true or false. 
	 */
	public static void setUserTurn(boolean isUserTurnIn)
	{
		isUserTurn = isUserTurnIn;
	}
	
	//from here on are all the get methods. 
	/*
	 * This method returns the user's name if it is their turn, and the 
	 * enemy name (passed in) if it is its turn. 
	 */
	public static String whoseTurn(String enemyName)
	{
		if(isUserTurn)
			return GameData.getUserName();
		else
			return enemyName;
	}
	
	//from here on are all the game progression tracking methods. 
	public static void executeMove(String moveName, int userMana)
	{
		//match the move with the file input 
		//search for move in file 
		//check if move exceeds user mana 
		/*
		if(userMana < moveMana)				//if not enough mana
		{
			String extendedDescription = "\n" + moveName + " (cost: " + moveMana 
				+ " mana pts) exceeds the " + userMana + " mana pts you have left.";
			gameTurnPanel.append("Not enough mana." *//*+ extendedDescription*//*);
		}
		else
			executeMoveEffects(moveName, moveInfo);
		*/
	}
	/* helper method for executeMove -- does the effects functions */
	public static void executeMoveEffects(String moveNameIn, Move moveIn)
	{
		//parse move effects, to enemy AND to user 
		//execute move effects 
			//change: hp, mana, defense 
		/*
			private int hp; ///current health
			private int mana; ///current mana
			private int defense; ///defense of Character
		*/
	}
}