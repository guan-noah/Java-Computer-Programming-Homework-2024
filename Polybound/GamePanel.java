/*
 * Noah Guan and Krish Kumar
 * Period 6
 * GamePanel.java
 */
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.JScrollPane;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

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
	private JMenu moveMenu;
	private Button guide;
	private Button exit;
	private Font optionFont;
	private Round round;

	private UpgradePopup upgradePopup;
	
	/* Constructor for GamePanel 
	 - Makes the 3 main panels and applies it to a BorderLayout 
	 - Initializes the component/other needed variables 
	 - Connects the components together 
	 */
	 //CONSTRUCTOR AND ALL INITIALIZER METHODS LISTED BELOW.
	public GamePanel()
	{
		setLayout(new BorderLayout());

		upgradePopup = new UpgradePopup();
		
		optionFont = new Font("SansSerif", Font.BOLD, 30);
		gamePanColor = Color.GRAY/*new Color()*/;//note: we will add a color later
		enemy = null;
		user = new Character(); ///a placeholder for when the actual character is chosen
		
		getGameUI();								//makes the game pan
		add(gameUI, BorderLayout.CENTER);					//adds panel
		
		getInfoPan();										//same logic
		add(sideInfoPan, BorderLayout.WEST);
		
		///add variable or smth idk
		getGameTurnInfo();
		JScrollPane scrollGameTurnInfo = new JScrollPane(gameTurnInfo);
		add(scrollGameTurnInfo, BorderLayout.SOUTH);
	}

	///starts the round
	public void start(boolean isTutorial)
	{
		user = GameData.getPlayerCharacter(); ///gets actual char

		///removes the components
		remove(gameUI);
		remove(sideInfoPan);

		///remakes the components
		getGameUI();
		getInfoPan();

		///readds the components
		add(sideInfoPan, BorderLayout.WEST);
		add(gameUI, BorderLayout.CENTER);

		round = new Round(isTutorial); ///starts round
	}

	public void executeUserMove(boolean success)
	{
		round.executeUserMove(success);
	}

	/* initialize */
	public void getGameUI()
	{
		gameUI = new GameUI();
		gameUI.setPreferredSize(new Dimension(1000, 550));//follows game proposal pseudocode
		gameUI.setBackground(gamePanColor);
	}
	/* initializes the information panel */
	public void getInfoPan()
	{
		sideInfoPan = new JPanel(new BorderLayout());
		sideInfoPan.setPreferredSize(new Dimension(250, 550));
		
		uInfo = new UserInfo();				//initialize user info panel
		uInfo.setPreferredSize(new Dimension(200, 150));
		sideInfoPan.add(uInfo, BorderLayout.NORTH);
		
		//holder for GridLayout of menu + buttons (0 gap between buttons for now)
		JPanel options = new JPanel(new GridLayout(3, 1));
		getOptions(options);
		sideInfoPan.add(options, BorderLayout.CENTER);
		
	}
	/* helper initializer method to getInfoPan's options JPanel */
	public void getOptions(JPanel optionsIn)
	{
		//initialize panel that moves will be centered in (workshopped idea) -- right now moves is a bit to the side 
		//~ JPanel centerMoves = new JPanel(new FlowLayout(FlowLayout.CENTER));
		moves = createMenuBar();					//initialize moves
		moves.setBackground(Color.CYAN);
		//~ centerMoves.add(moves); //workshopped idea 
		optionsIn.add(moves);
		//for adding, optionsIn reference should still point to the same JPanel object; should be fine
		
		ShowOther showOtherHandler = new ShowOther();
		guide = new Button("Guide", showOtherHandler, optionFont);			//guide button
		guide.setPreferredSize(new Dimension(200, 266));
		optionsIn.add(guide);
		
		exit = new Button("Exit", showOtherHandler, optionFont);				//exit button
		exit.setPreferredSize(new Dimension(200, 266));				//prep for background color
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
		gameTurnInfo = new JTextArea(8, 50);
		gameTurnInfo.setForeground(Color.WHITE);
		gameTurnInfo.setBackground(Color.DARK_GRAY);
		gameTurnInfo.setFont(new Font("Verdana", Font.PLAIN, 20));
		gameTurnInfo.setEditable(false);
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
		moveMenu = new JMenu("Moves");
		moveMenu.setFont(optionFont);
		moveMenu.setPreferredSize(new Dimension(300, 100));
		//~ for each move in Moves file: 
			//~ JMenuItem tempMenuItem = getMenuItem(enemy.getMoveSet(index));
			//~ tempMenuItem.addActionListener(new MovesListener());
			//~ outputMenu.add(tempMenuItem));

		MovesListener moveHandler = new MovesListener();
		Font menuItemFont = new Font("SansSerif", Font.ITALIC, 20);
		ArrayList<String> moveSet = user.getMoveset();
		int bound = user.getLevel()+2;
		if(bound > moveSet.size())
		{
			bound = moveSet.size();
		}
		for(int i=0; i<bound; i++)
		{
			JMenuItem item = new JMenuItem(moveSet.get(i));
			Move move = new Move(moveSet.get(i));

			item.addActionListener(moveHandler);
			item.setFont(menuItemFont);
			item.setToolTipText(move.getDescription());
			moveMenu.add(item);
		}
		
		return moveMenu;
	}
	
	//ALL CLASSES LISTED BELOW. 
	/**
	 * Written by Krish, a representation of a round/battle.
	 **/
	class Round
	{
		private int turn;
		private Move toExecute;
		private boolean isTutorial;

		public Round(boolean isTutorialIn)
		{
			turn = 0;
			isTutorial = isTutorialIn;

			gameTurnInfo.setText("");

			if(isTutorial)
			{
				enemy = new Character("Training Dummy", 1);
				gameTurnInfo.append("Welcome to your first battle. Here, you will " +
					"learn the basics of battle, as well as some polynomial skills " +
					"to help you execute moves effectively.\n");
				gameTurnInfo.append("To execute a move, go over to the Moves menu on the left, " +
					"and select a move. If you forgot what a move does, hover over it to view its " +
					"description.\n");
				gameTurnInfo.append("If you ever need help with a polynomial problem, a guide is " +
					"provided showing how to solve all problem types.\n");
				gameTurnInfo.append("Try and use your moves to defeat the Training Dummy.\n\n");
			}
			else
			{
				getEnemy();
			}

			startTurn();
		}

		public void getEnemy()
		{
			String[] enemyNames = {"Circle"/*, "Circle Rebel", "Funky Monkey"*/};
			int random = GameData.getRandom(0, enemyNames.length-1);
			int level = GameData.getRandom(1, 10);

			enemy = new Character(enemyNames[random], level);
		}
		/**
		 * Starts a new turn. Checks to see if either the player or enemy
		 * was defeated.
		 */
		public void startTurn()
		{
			if(!enemy.isDefeated() && !user.isDefeated()) ///round continues
			{
				turn++;

				ArrayList<String> moveSet = user.getMoveset();
				int bound = user.getLevel()+2;
				if(bound > moveSet.size())
				{
					bound = moveSet.size();
				}
				int random = GameData.getRandom(0, bound-1);
				Move checkForMana = new Move(moveSet.get(random));

				///assumes all characters have a no mana move like Recover or Pass
				while(checkForMana.getCost() > user.getMana())
				{
					random = GameData.getRandom(0, bound-1);
					checkForMana = new Move(moveSet.get(random));
				}

				gameTurnInfo.append("Turn " + turn + "\n");
				gameTurnInfo.append("Make your move... Select a move from the moves menu.\n");
				gameTurnInfo.append("Stuck on what to use? Consider using " + moveSet.get(random) + ".\n");
			}
			else ///round has ended
			{
				if(!isTutorial)
				{
					if(enemy.isDefeated())
					{
						gameTurnInfo.append("\nYou won!");
						GameData.incrementEnemiesDefeated();
					}
					else
					{
						gameTurnInfo.append("\n\nGame over...");
						GameData.writeHighScore();
					}
				}
				else
				{
					gameTurnInfo.append("\nCongratulations! You have completed the tutorial.\n" +
						"Now, get ready to start fending off the Polygon Empire. Good luck!");
					GameData.setGameStarted(true);
					GameData.incrementEnemiesDefeated();
				}

				GameData.writeData(enemy.isDefeated());
				moveMenu.setEnabled(false);
				guide.setEnabled(false);
			}
		}

		/**
		 * Configures the player's move.
		 */
		public boolean setMoveToExecute(String moveName)
		{
			toExecute = new Move(moveName);

			if(toExecute.getCost() <= user.getMana())
			{
				return true;
			}

			return false;
		}

		/**
		 * Executes the player's move.
		 */
		public void executeUserMove(boolean success)
		{
			if(success)
			{
				String toPrint = toExecute.executeMove(user, enemy, turn);
				gameTurnInfo.append(toPrint + "\n");
			}
			else if(GameData.isDemoModeOn()) ///demo mode is on
			{
				gameTurnInfo.append("As a result of demo mode, the move miraculously was executed.\n");
				String toPrint = toExecute.executeMove(user, enemy, turn);
				gameTurnInfo.append(toPrint + "\n");
			}
			else
			{
				gameTurnInfo.append(user.getName() + " failed to use " + toExecute.getName() + ".\n");
			}

			configureEnemyMove();
		}

		/**
		 * Configures the enemy's move.
		 * Assumptions:
		 * -All characters have at least 1 no mana move.
		 */
		public void configureEnemyMove()
		{
			String name = enemy.getName();
			if(name.equals("Funky Monkey") && enemy.getHP() ==  enemy.getMaxHP()/2)
			{
				toExecute = new Move("Vertex of Finality");
				executeEnemyMove();
			}
			else
			{
				ArrayList<String> moveSet = enemy.getMoveset();
				int getMove = GameData.getRandom(0, moveSet.size()-1);
	
				toExecute = new Move(moveSet.get(getMove));
	
				if(toExecute.getCost() <= enemy.getMana())
				{
					if(!enemy.isDefeated())
					{
						executeEnemyMove();
					}
					else
					{
						startTurn();
					}
				}
				else
				{
					configureEnemyMove();
				}
			}
		}

		/**
		 * Executes the enemy's move.
		 */
		public void executeEnemyMove()
		{
			String toPrint = toExecute.executeMove(enemy, user, turn);
			gameTurnInfo.append(toPrint + "\n\n");
			startTurn();
		}
	}
	/* GameUI; CENTER */
	class GameUI extends JPanel implements ActionListener, MouseMotionListener
	{
		private Timer updateTimer;
		private boolean descriptionShowing;

		public GameUI()
		{
			updateTimer = new Timer(0, this);
			updateTimer.start();
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			//~ int xLoc; //fixed location for now 
			//~ int yLoc;
			
			//x, y, width, height
			//draws user image
			//~g.drawImage(user.getImage(), 200, 150, 200, 200, this);
			g.setFont(new Font("Verdana", Font.BOLD, 25));
			g.setColor(Color.BLUE);
			
			g.fillRect(200, 150, 200, 200);//temporary placeholder
			//draws enemy image
			//~g.drawImage(enemy.getImage(), 600, 150, 200, 200, this);
			
			if(enemy != null)
			{
				drawEnemy(g, enemy.getName());
			}
			
			g.setColor(Color.BLACK);

			g.drawString(user.getName(), 200, 375); //draw user and enemy labels

			if(enemy != null)
			{
				g.drawString(enemy.getName(), 600, 375);
				g.drawString("Level " + enemy.getLevel(), 600, 400);
				g.drawString("HP: " + enemy.getHP() + "/" + enemy.getMaxHP(), 600, 425);
				g.drawString("Defense: " + enemy.getDefense(), 600, 450);
			}
		}

		public void drawEnemy(Graphics g, String name)
		{
			Graphics2D g2d = (Graphics2D) g;

			if(name.equals("Circle"))
			{
				g.setColor(Color.RED);
				g.fillOval(600, 150, 200, 200);

				g.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(8));
				g2d.drawOval(600, 150, 200, 200);
			}
			else if(name.equals("Circle Rebel"))
			{
				g.setColor(Color.BLUE);
				g.fillOval(600, 150, 200, 200);

				g.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(8));
				g2d.drawOval(600, 150, 200, 200);
			}
			else
			{
				g.fillRect(600, 150, 200, 200);
			}
		}

		public void actionPerformed(ActionEvent evt)
		{
			if(evt.getSource() == updateTimer)
			{
				repaint();
			}
		}

		public void mouseMoved(MouseEvent evt)
		{
			int x = evt.getX();
			int y = evt.getY();

			if(x >= 600 && x <= 200 && y >= 150 && y <= 350)
			{
				descriptionShowing = true;
			}
			else
			{
				descriptionShowing = false;
			}
		}
		public void mouseDragged(MouseEvent evt) {}
	}
	/* User Info panel; SOUTH */
	class UserInfo extends JPanel implements ActionListener
	{
		private Timer updateInfoTimer;

		public UserInfo()
		{
			setBackground(Color.LIGHT_GRAY);
			updateInfoTimer = new Timer(0, this);
			updateInfoTimer.start();
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			//can also be "Serif"
			//cannot get hp and mana at the moment as user is not set 

			g.setFont(new Font("Verdana", Font.BOLD, 35));
			g.drawString(user.getName(), 0, 35);

			g.setFont(new Font("Verdana", Font.BOLD, 25));
			g.drawString("HP: " + user.getHP() + "/" + user.getMaxHP(), 0, 70);
			g.drawString("Mana: " + user.getMana() + "/" + user.getMaxMana(), 0, 105);
			g.drawString("Defense: " + user.getDefense(), 0, 140);
			
			//placeholders 
		}

		public void actionPerformed(ActionEvent evt)
		{
			if(evt.getSource() == updateInfoTimer)
			{
				repaint();
			}
		}
	}
	class MovesListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String moveName = evt.getActionCommand();

			///integrate later, for now here for testing
			boolean enoughMana = round.setMoveToExecute(moveName);

			if(enoughMana)
			{
				//round.executeUserMove(true);
				GameData.getProblem();
			}
			else
			{
				gameTurnInfo.append("Not enough mana!\n");
			}
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
				GameData.switchCard("main menu");
				///add actual code later
			}
			else if(command.equals("Exit")) //if user pressed exit button, exit game and print to console. 
			{
				//~ gameTurnInfo.append("\n\t(Exit button pressed.)"); //in the future, uncomment with the popup; 
					//~ commented out right now because of program inefficiency if kept
				//~ in the future, we'll have a popup (are you sure you want to exit?)
				//~ (for debugging purposes)
				if(!user.isDefeated())
				{
					GameData.switchCard("intermission");
					int defeatedEnemies = GameData.getEnemiesDefeated();
					int level = user.getLevel();

					if(defeatedEnemies >= level*5)
					{
						user.increaseLevel();
						upgradePopup.resetPoints();
						upgradePopup.show();

						GameData.writeData(true); ///writes data
					}
				}
				else
				{
					GameData.setGameStarted(false);
					GameData.switchCard("main menu");
				}
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
