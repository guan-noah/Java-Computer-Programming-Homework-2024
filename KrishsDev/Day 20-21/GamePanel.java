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
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

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
	private Button prevChar;
	private Button startTurn;
	private Button nextChar;
	private Font optionFont;
	private Round round;

	private boolean enemyShake;
	private boolean[] playerShake;

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

		enemyShake = false;
		playerShake = new boolean[3];

		upgradePopup = new UpgradePopup();
		
		optionFont = new Font("Oswald Regular", Font.BOLD, 30);
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
		GameData.setCharViewing(0);
		user = GameData.getPlayerCharacter(); ///gets actual char

		refactorMoves();
		

		gameUI.requestFocusInWindow();
		round = new Round(isTutorial); ///starts round
	}

	public void setPlayerShake(boolean isShaking, int index)
	{
		playerShake[index] = isShaking;
	}

	public void setEnemyShake(boolean isShaking)
	{
		enemyShake = isShaking;
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
		gameTurnInfo.setFont(new Font("Share Tech Regular", Font.PLAIN, 25));
		gameTurnInfo.setEditable(false);
	}
	/* initializes the menubar */
	public JMenuBar createMenuBar()
	{
		JMenuBar outputMenuBar = new JMenuBar();
		outputMenuBar.add(createMoveSetMenu());
		
		return outputMenuBar;
	}
	///FIX THIS
	public void refactorMoves()
	{
		user = GameData.getPlayerCharacter();
		moves.remove(moveMenu);
		moveMenu = createMoveSetMenu();

		moves.add(moveMenu);
		moves.revalidate();
	}
	/* initializes the menu with the menuitems*/
	public JMenu createMoveSetMenu()
	{
		moveMenu = new JMenu("           Moves");
		moveMenu.setFont(optionFont);
		moveMenu.setPreferredSize(new Dimension(300, 100));
		//~ for each move in Moves file: 
			//~ JMenuItem tempMenuItem = getMenuItem(enemy.getMoveSet(index));
			//~ tempMenuItem.addActionListener(new MovesListener());
			//~ outputMenu.add(tempMenuItem));

		MovesListener moveHandler = new MovesListener();
		Font menuItemFont = new Font("Oswald Regular", Font.ITALIC, 20);
		ArrayList<String> moveSet = user.getMoveset();
		int bound = user.getLevel()/3 + 3;
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
		private Move[] plrToExecute;
		private boolean isTutorial;
		private boolean enemyMoveExecuted;
		private boolean turnStarted;

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
			int level = GameData.getRandom(1+user.getLevel()/3, 4+user.getLevel()/3);

			enemy = new Character(enemyNames[random], level);
		}

		public Move[] getMovesToExecute()
		{
			return plrToExecute;
		}

		/**
		 * Starts a new turn. Checks to see if either the player or enemy
		 * was defeated.
		 */
		public void startTurn()
		{
			turnStarted = true;
			plrToExecute = new Move[3];
			moveMenu.setEnabled(true);
			guide.setEnabled(true);
			exit.setEnabled(true);
			prevChar.setEnabled(true);
			startTurn.setEnabled(true);
			nextChar.setEnabled(true);
			
			if(!enemy.isDefeated() && !GameData.playerCharsDefeated()) ///round continues
			{
				turn++;

				gameTurnInfo.append("Turn " + turn + "\n");
				gameTurnInfo.append("Make your move... Select a move from the moves menu.\n");
			}
			else ///round has ended
			{
				if(!isTutorial)
				{
					if(enemy.isDefeated())
					{
						gameTurnInfo.append("\nYou won! (Press \"Exit\" to continue)");
						GameData.incrementEnemiesDefeated();
					}
					else
					{
						gameTurnInfo.append("\nGame over...");
						GameData.writeHighScore();
					}
				}
				else
				{
					///adds the new save
					GameData.getSaveList().add(new Save(GameData.getUserName(), 1, GameData.getPlayerChars()));
					GameData.setSaveIndex(GameData.getSaveList().size()-1);

					gameTurnInfo.append("\nCongratulations! You have completed the tutorial.\n" +
						"Now, get ready to start fending off the Polygon Empire. Good luck! " +
						"(Press \"Exit\" to continue)");
					GameData.setGameStarted(true);
					GameData.incrementEnemiesDefeated();
				}

				GameData.writeData(enemy.isDefeated()); ///writes data to file
				moveMenu.setEnabled(false);
				guide.setEnabled(false);
				prevChar.setEnabled(false);
				startTurn.setEnabled(false);
				nextChar.setEnabled(false);
			}
		}

		/**
		 * Configures the player's move.
		 */
		public boolean setMoveToExecute(String moveName, int charNum)
		{
			plrToExecute[charNum] = new Move(moveName);

			if(plrToExecute[charNum].getCost() <= user.getMana())
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
				Character[] plrChars = GameData.getPlayerChars();
				String toPrint = "";

				for(int i=0; i<plrChars.length; i++)
				{
					if(!enemy.isDefeated() && !plrChars[i].isDefeated())
					{
						toPrint = plrToExecute[i].executeMove(plrChars[i], enemy, turn);
						gameTurnInfo.append(toPrint + "\n");
					}
				}
			}
			else if(GameData.isDemoModeOn()) ///if demo mode is on
			{
				gameTurnInfo.append("As a result of demo mode, the move miraculously was executed.\n");

				Character[] plrChars = GameData.getPlayerChars();
				String toPrint = "";

				for(int i=0; i<plrChars.length; i++)
				{
					if(!enemy.isDefeated() && !plrChars[i].isDefeated())
					{
						toPrint = plrToExecute[i].executeMove(plrChars[i], enemy, turn);
						gameTurnInfo.append(toPrint + "\n");
					}
				}
			}
			else
			{
				Character[] plrChars = GameData.getPlayerChars();
				for(int i=0; i<plrChars.length; i++)
				{
					if(!enemy.isDefeated() && !plrChars[i].isDefeated())
					{
						gameTurnInfo.append(plrChars[i] + " failed to use " + plrToExecute[i] + ".\n");
					}
				}
			}

			configureEnemyMove();
		}

		class EnemyMoveHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				Wait wait = (Wait) evt.getSource();

				if(wait.isComplete() && !enemyMoveExecuted)
				{
					executeEnemyMove();
				}
			}
		}

		class NextTurnHandler implements ActionListener
		{
			private int[] prevHealth;

			public NextTurnHandler(int[] prevHealthIn)
			{
				prevHealth = prevHealthIn;
			}

			public void actionPerformed(ActionEvent evt)
			{
				Wait wait = (Wait) evt.getSource();

				if(wait.isComplete() && !turnStarted)
				{
					Character[] playerChars = GameData.getPlayerChars();
					for(int i=0; i<playerChars.length; i++)
					{
						int currHP = playerChars[i].getHP();
		
						if(prevHealth[i] != currHP && currHP < 1)
						{
							gameTurnInfo.append(playerChars[i].getName() + " has fallen...\n");
						}
					}
		
					gameTurnInfo.append("\n");

					startTurn();
				}
			}
		}

		/**
		 * Configures the enemy's move.
		 * Assumptions:
		 * -All characters have at least 1 no mana move.
		 */
		public void configureEnemyMove()
		{
			enemyMoveExecuted = false;

			moveMenu.setEnabled(false);
			exit.setEnabled(false);
			prevChar.setEnabled(false);
			startTurn.setEnabled(false);
			nextChar.setEnabled(false);

			String name = enemy.getName();
			if(name.equals("Funky Monkey") && enemy.getHP() ==  enemy.getMaxHP()/2)
			{
				toExecute = new Move("Vertex of Finality");
				EnemyMoveHandler enemyMoveHandler = new EnemyMoveHandler();
				Wait wait = new Wait(2, enemyMoveHandler);
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
						EnemyMoveHandler enemyMoveHandler = new EnemyMoveHandler();
						Wait wait = new Wait(2, enemyMoveHandler);
						moveMenu.setEnabled(false);
						exit.setEnabled(false);
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
			enemyMoveExecuted = true;
			turnStarted = false;

			Character[] playerChars = GameData.getPlayerChars();
			int[] prevHealth = new int[3]; ///stores the health before this move
			for(int i=0; i<playerChars.length; i++)
			{
				prevHealth[i] = playerChars[i].getHP();
			}

			Character toExecuteOn = playerChars[GameData.getRandom(0, playerChars.length-1)];

			String toPrint = toExecute.executeMove(enemy, toExecuteOn, turn);
			gameTurnInfo.append(toPrint + "\n");

			NextTurnHandler nextTurnHandler = new NextTurnHandler(prevHealth);
			Wait wait = new Wait(2, nextTurnHandler);
		}
	}
	/* GameUI; CENTER */
	class GameUI extends JPanel implements ActionListener
	{
		private Timer updateTimer;

		public GameUI()
		{
			setFocusable(true);
			updateTimer = new Timer(0, this);
			updateTimer.start();

			StartTurnHandler turnHandler = new StartTurnHandler();
			startTurn = new Button("Start Turn!", turnHandler, 30);
			prevChar = new Button("<", turnHandler, 30);
			nextChar = new Button(">", turnHandler, 30);
			add(prevChar);
			add(startTurn);
			add(nextChar);
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			//~ int xLoc; //fixed location for now 
			//~ int yLoc;
			
			//x, y, width, height
			//draws user image
			//~g.drawImage(user.getImage(), 200, 150, 200, 200, this);
			g.setFont(new Font("Share Tech Regular", Font.BOLD, 25));
			g.setColor(Color.BLUE);
			
			if(GameData.getPlayerChars() != null)
			{
				drawPlayerChars(g);
			}
			//draws enemy image
			//~g.drawImage(enemy.getImage(), 600, 150, 200, 200, this);
			
			if(enemy != null)
			{
				if(!enemy.isDefeated())
				{
					drawEnemy(g, enemy.getName());
				}
			}
			
			g.setColor(Color.BLACK);

			if(enemy != null)
			{
				g.drawString(enemy.getName(), 600, 375);
				g.drawString("Level " + enemy.getLevel(), 600, 400);
				g.drawString("HP: " + enemy.getHP() + "/" + enemy.getMaxHP(), 600, 425);
				g.drawString("Defense: " + enemy.getDefense(), 600, 450);
			}
		}

		public void drawPlayerChars(Graphics g)
		{
			Character[] playerChars = GameData.getPlayerChars();
			Character player = GameData.getPlayerCharacter();

			for(int i=0; i<playerChars.length; i++)
			{
				g.drawImage(playerChars[i].getImage(), 50+(i%2*200), 80+(i/2*200), 150, 150, this);
				if(player == playerChars[i])
				{
					int shakeOffset = GameData.getRandom(-10, 10);

					g.setColor(Color.YELLOW);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setStroke(new BasicStroke(5));

					if(playerShake[i])
						g2d.drawRect(50+(i%2*200)+shakeOffset, 80+(i/2*200)+shakeOffset, 150, 150);
					else
						g2d.drawRect(50+(i%2*200), 80+(i/2*200), 150, 150);
				}

				g.setColor(Color.BLACK);
				g.drawString(playerChars[i].getName(), 50+(i%2*200), 75+(i/2*200));

				if(round.getMovesToExecute()[i] != null)
				{
					g.setColor(Color.BLUE);
					g.drawString("READY!", 50+(i%2*200), 230+(i/2*200));
				}
			}
		}

		public void drawEnemy(Graphics g, String name)
		{
			Graphics2D g2d = (Graphics2D) g;

			int shakeOffset = GameData.getRandom(-10, 10);
			int x = 600;
			int y = 150;

			if(enemyShake)
			{
				x+=shakeOffset;
				y+=shakeOffset;
			}

			if(name.equals("Circle"))
			{
				g.setColor(Color.RED);
				g.fillOval(x, y, 200, 200);

				g.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(8));
				g2d.drawOval(x, y, 200, 200);
			}
			else if(name.equals("Circle Rebel"))
			{
				g.setColor(Color.BLUE);
				g.fillOval(x, y, 200, 200);

				g.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(8));
				g2d.drawOval(x, y, 200, 200);
			}
			else
			{
				g.fillRect(x, y, 200, 200);
			}
		}

		public void actionPerformed(ActionEvent evt)
		{
			if(evt.getSource() == updateTimer)
			{
				repaint();
			}
		}

		class StartTurnHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				String command = evt.getActionCommand();
				if(command.equals("Start Turn!"))
				{
					Move[] movesToExecute = round.getMovesToExecute();
					Character[] playerChars = GameData.getPlayerChars();
					boolean allMovesReady = true;

					for(int i=0; i<movesToExecute.length; i++)
					{
						if(movesToExecute[i] == null && !playerChars[i].isDefeated())
						{
							allMovesReady = false;
						}
					}

					if(allMovesReady)
					{
						GameData.getProblem();
					}
					else
					{
						gameTurnInfo.append("Please select a move for all team members.\n");
					}
				}
				else if(command.equals("<"))
				{
					GameData.decrementCharViewing();
					refactorMoves();
				}
				else if (command.equals(">"))
				{
					GameData.incrementCharViewing();
					refactorMoves();
				}
			}
		}
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

			Character player = GameData.getPlayerCharacter();

			g.setFont(new Font("Share Tech Regular", Font.BOLD, 35));
			g.drawString(player.getName(), 0, 35);

			g.setFont(new Font("Share Tech Regular", Font.BOLD, 25));
			g.drawString("HP: " + player.getHP() + "/" + player.getMaxHP(), 0, 70);
			g.drawString("Mana: " + player.getMana() + "/" + player.getMaxMana(), 0, 105);
			g.drawString("Defense: " + player.getDefense(), 0, 140);
			
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
			boolean enoughMana = round.setMoveToExecute(moveName, GameData.getCharViewing());

			if(!enoughMana)
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
			
			if(command.equals("Guide"))
			{
				gameTurnInfo.append("\n\t(Guide button pressed.)");
				///add actual code later
			}
			else if(command.equals("Exit")) 
			{
				if(enemy.isDefeated())
				{
					int defeatedEnemies = GameData.getEnemiesDefeated();
					boolean showUpgrades = false;

					Character[] playerChars = GameData.getPlayerChars();
					for(int i=0; i<playerChars.length; i++)
					{
						Character character = playerChars[i];
						int level = character.getLevel();
						
						if(defeatedEnemies >= level*5)
						{
							character.increaseLevel();
							showUpgrades = true;
						}
					}

					if(showUpgrades)
					{
						upgradePopup.resetPoints();
						upgradePopup.show();
					}

					GameData.setCharViewing(0);
					GameData.writeData(true); ///writes data
					GameData.switchCard("intermission");
				}
				else
				{
					if(GameData.playerCharsDefeated())
					{
						GameData.writeData(false);
						GameData.switchCard("main menu");
					}
					else
					{
						GameData.switchCard("intermission");
					}
				
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
