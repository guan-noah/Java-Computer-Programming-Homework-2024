/*
 * Noah Guan
 * Period 6
 * InfoPopup.java
 * 
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
public class GamePanel extends JPanel
{
	//Field vars 
		
	private JTextArea gameTurnInfo;			//CENTER; jtextarea at bottom
	private GameUI gameUI;			//SOUTH; game ui (w/ paintComponent)
	//goes in gamePanel - user & enemy image sprite, 0 & 1 respectively
	private ArrayList<Character> characters;//GameUI information
	private Color 
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
		
		Color gamePanColor = Color.GRAY/*new Color()*/;//note: we will add a color later
		characters = new ArrayList<> ();
		
		getGameUI();								//makes the game pan
		add(gameUI, BorderLayout.CENTER);					//adds panel
		
		sideInfoPan.setPreferredSize(200, 550);
		getInfoPan();										//same logic
		add(sideInfoPan, BorderLayout.WEST);
		
		gameTurnInfo.setPreferredSize(1200, 200);
		getGameTurnInfo();
		add(gameTurnInfo, BorderLayout.SOUTH);
	}
	/* GameUI  */
	class GameUI extends JPanel
	{
		public GameUI(String userSpriteNameIn)
		{
			loadUser(userSpriteNameIn);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			//~ int xLoc; //fixed location for now 
			//~ int yLoc;
			
			//x, y, width, height
			g.drawImage(character[0].getImage(), 200, 150, 200, 200, this);
			//draws enemy image
			g.drawImage(character[1].getImage(), 600, 150, 200, 200, this);
		}
		/*as of now, only used the first time GameUI is called*/
		public void loadUser(String userSpriteName)
		{
			characters[0] = new Character(userSpriteName);
		}
		/*overloaded method; used when we're changing enemy sprite*/
		public void loadEnemy(String enemyName)
		{
			sprites[1] = new Character(enemyName);
		}
	}
	class UserInfo extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
		}
	}
	public void getGameUI()
	{
		gameUI = new GameUI();
		gameUI.setPreferredSize(1000, 550);//follows game proposal pseudocode
		gameUI.setBackground(gamePanColor);
		
	}
	/* initializes the information panel */
	public void getInfoPan()
	{
		moves = createMenuBar();					//initialize moves
		sideInfoPan.setLayout(new BorderLayout());
	}
	/* initializes the side panel */
	public void getGameTurnInfo()
	{
		
	}
	/* initializes the menubar */
	public JMenuBar createMenuBar()
	{
		JMenu moveSetMenu = new JMenu();
		ArrayList<String> moveSets = new ArrayList<> ();
		addMoves();
		
		for(int i = 0; i < moveSets.size(); i++)
			moveSets.add(moveSets.get(i));
	}
	/*  */
	public JMenuItem getMenuItem(String menuItemName)
	{
		return new JMenuItem(menuItemName);//we can add an int mnemonic if we want 
	}
	
	/*  */
	//~ public 
}
