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
import java.awt.BorderLayout;
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
	private Character user, enemy;//GameUI information
	private Color gamePanColor;
	private JPanel sideInfoPan;						//WEST; info panel 
	
	//these go in the west sideInfoPan 
	private UserInfo uInfo;		//JPanel for userInfo (w/ paintComponent) 
	private JMenuBar moves;				//Options arrangement components 
	private JButton guide, exit;
	
	/* Constructor for GamePanel 
	 - Makes the 3 main panels and applies it to a BorderLayout 
	 - Initializes the component/other needed variables 
	 - Connects the components together 
	 */
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
		
		getGameTurnInfo();
		add(gameTurnInfo, BorderLayout.SOUTH);
	}
	/* initialize */
	public void getGameUI()
	{
		gameUI = new GameUI(GameData.getUserName());
		gameUI.setPreferredSize(new Dimension(1000, 550));//follows game proposal pseudocode
		gameUI.setBackground(gamePanColor);
		
	}
	/* initializes the information panel */
	public void getInfoPan()
	{
		moves = createMenuBar();					//initialize moves
		sideInfoPan = new JPanel(new BorderLayout());
		sideInfoPan.setPreferredSize(new Dimension(200, 550));
	}
	/* initializes the south jtextarea */
	public void getGameTurnInfo()
	{
		gameTurnInfo = new JTextArea();
		gameTurnInfo.setPreferredSize(new Dimension(1200, 200));
	}
	/* initializes the menubar */
	public JMenuBar createMenuBar()
	{
		JMenuBar outputMenuBar = new JMenuBar();
		outputMenuBar.add(createMoveSetMenu());
		
		return outputMenuBar;
	}
	/* initializes the menu with the menuitems*/
	public JMenu createMoveSetMenu()
	{
		JMenu outputMenu = new JMenu("Moves");
		//~ for each move in Moves class in GameData: 
			//~ outputMenu.add(getMenuItem(enemy.getMoveSet));
		
		return outputMenu;
	}
	/* intializes each button */
	public JMenuItem getMenuItem(String menuItemName)
	{
		return new JMenuItem(menuItemName);//we can add an int mnemonic if we want 
	}
	
	/* initializes each move */
	public void addMoves()
	{
		
	}
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
			loadUser(userSpriteNameIn);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			//~ int xLoc; //fixed location for now 
			//~ int yLoc;
			
			//x, y, width, height
			//draws user image
			g.drawImage(user.getImage(), 200, 150, 200, 200, this);
			//draws enemy image
			g.drawImage(enemy.getImage(), 600, 150, 200, 200, this);
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
			
		}
	}
}
