/*
 * Noah Guan and Krish Kumar
 * Period 6
 * GamePanel.java
 */
//imports 
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
	private Button guide;			//navigation components
	private Button next;
	private Font optionFont;
	private Round round;			//for game progression

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
	
	/* execute user's move */
	public void executeUserMove(boolean success)
	{
		round.executeUserMove(success);
	}

	/* initialize game interface panel */
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
		guide = new Button("Guide", showOtherHandler, optionFont);//guide button
		guide.setPreferredSize(new Dimension(200, 266));
		optionsIn.add(guide);
		
		next = new Button("Next", showOtherHandler, optionFont);//next button to intermission panel
		next.setPreferredSize(new Dimension(200, 266));
		optionsIn.add(next);
		
		/*
		 * workshopped idea: instead of next, make a Quit Game button that 
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
		gameTurnInfo.setForeground(Color.WHITE);//white text
		gameTurnInfo.setBackground(Color.DARK_GRAY);
		gameTurnInfo.setFont(new Font("Verdana", Font.PLAIN, 20));
		gameTurnInfo.setEditable(false);//cannot edit text area
	}
	/* initializes the menubar */
	public JMenuBar createMenuBar()
	{
		JMenuBar outputMenuBar = new JMenuBar();//initialize menu bar
		outputMenuBar.add(createMoveSetMenu());//add move set jmenu to it
		
		return outputMenuBar;
	}
	/* initializes the menu with the menuitems*/
	public JMenu createMoveSetMenu()
	{
		//initialize the move jmenu
		moveMenu = new JMenu("Moves");
		moveMenu.setFont(optionFont);
		moveMenu.setPreferredSize(new Dimension(300, 100));
		
		//initialize handlers and moveset 
		MovesListener moveHandler = new MovesListener();//handler
		Font menuItemFont = new Font("SansSerif", Font.ITALIC, 20);
		ArrayList<String> moveSet = user.getMoveset();//gets user moveset
		
		//upper limit of how many moves user gets = levels + 2, caps at moveset size
		int bound = user.getLevel()+2;
		if(bound > moveSet.size())
		{
			bound = moveSet.size();
		}
		
		//initialize and add items to move jmenu 
		for(int i=0; i<bound; i++)
		{
			//initialize each menu item and each move with name of move 
			JMenuItem item = new JMenuItem(moveSet.get(i));
			Move move = new Move(moveSet.get(i));
			
			//complete initialization of menu item 
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
		private int turn;			//how many turns completed in round
		private Move toExecute;			//user or enemy move to execute 
		private boolean isTutorial;
		
		//initialize round 
		public Round(boolean isTutorialIn)
		{
			turn = 0;
			isTutorial = isTutorialIn;

			gameTurnInfo.setText("");//reset text field 
			
			//deending on the game, it 
			if(isTutorial)
			{
				//default enemy is training dummy. 
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
				//to get actual enemy, call method 
				getEnemy();
			}
			
			//start the round! 
			startTurn();
		}
		
		/*
		 * Gets enemy in game. Sets a random enemy with random level for 
		 * user to oppose. 
		 */
		public void getEnemy()
		{
			String[] enemyNames = {"Circle"/*, "Circle Rebel", "Funky Monkey"*/};
			//random enemy name 
			int random = GameData.getRandom(0, enemyNames.length-1);
			//random level 
			int level = GameData.getRandom(1, 10);
			
			//(re)initialize enemy 
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
				
				//get user moveset 
				ArrayList<String> moveSet = user.getMoveset();
				//again, user moveset is the level+2, capped at the size of the array
				int bound = user.getLevel()+2;
				if(bound > moveSet.size())
				{
					bound = moveSet.size();
				}
				
				//gets a random move's name (within user mana). 
				int random = GameData.getRandom(0, bound-1);
				Move checkForMana = new Move(moveSet.get(random));

				///assumes all characters have a no mana move like Recover or Pass
				while(checkForMana.getCost() > user.getMana())
				{
					random = GameData.getRandom(0, bound-1);
					checkForMana = new Move(moveSet.get(random));
				}
				
				//update information to bottom text area. 
				gameTurnInfo.append("Turn " + turn + "\n");
				gameTurnInfo.append("Make your move... Select a move from the moves menu.\n");
				gameTurnInfo.append("Stuck on what to use? Consider using " + moveSet.get(random) + ".\n");
			}
			else ///round has ended
			{
				if(!isTutorial)//if in actual game
				{
					//decide whether lost or won. 
					if(enemy.isDefeated())
					{
						//if won, 
						gameTurnInfo.append("\nYou won! Press \"Next\" " + 
							"for the next round. ");
						GameData.incrementEnemiesDefeated();
					}
					else
					{
						//if lost, tell them they lost and write their high score 
						//in the future, we can direct them straight to the main menu. 
						gameTurnInfo.append("\n\nGame over... Press " + 
							"\"Next\" and return to the main menu to see " + 
							"how you stack up with other players!");
						GameData.writeHighScore();
					}
				}
				else 		//special tutorial accomodations. 
				{
					//they will win this round. the dummy doesn't do any damage.
					gameTurnInfo.append("\nCongratulations! You have completed the tutorial.\n" +
						"Now, get ready to start fending off the Polygon Empire. Good luck!");
					//start the game! lucky them. they already defeated one "enemy". 
					GameData.setGameStarted(true);
					GameData.incrementEnemiesDefeated();
				}
				
				//Write user information to text file 
				GameData.writeData(enemy.isDefeated());
				
				//restrict access to guide and move menu buttons. Can only 
					//click on "next" button. 
				moveMenu.setEnabled(false);
				guide.setEnabled(false);
			}
		}

		/*
		 * Configures the player's move.
		 * Returns if user has enough mana to execute. 
		 */
		public boolean setMoveToExecute(String moveName)
		{
			toExecute = new Move(moveName);//(re)initialize toExecute to move parameter.
			
			//compare move cost and user mana 
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
			//Logic checks if move success first; then if the demo mode is on
			//At last, it will fail to execute the move
			if(success)					//if the user can execute move
			{
				//execute move via executeMove() in class Move 
				String toPrint = toExecute.executeMove(user, enemy, turn);
				//update text area 
				gameTurnInfo.append(toPrint + "\n");
			}
			else if(GameData.isDemoModeOn()) ///demo mode is on
			{
				//update text area and execute anyway (even though user 
					//shouldn't be able to execute move)
				gameTurnInfo.append("As a result of demo mode, the move " + 
					"was miraculously executed.\n");
				String toPrint = toExecute.executeMove(user, enemy, turn);
				gameTurnInfo.append(toPrint + "\n");
			}
			else
			{
				//update text area with fail message
				gameTurnInfo.append(user.getName() + " failed to use " + toExecute.getName() + ".\n");
			}
			
			//enemy's turn! the second half of the round. 
			configureEnemyMove();
		}

		/**
		 * Configures the enemy's move.
		 * Assumptions:
		 * -All characters have at least 1 no mana move. (has a while loop)
		 */
		public void configureEnemyMove()
		{
			//get name of enemy 
			String name = enemy.getName();
			
			//deadly funky monkey. (also executes if enemy is at exactly half health)
			if(name.equals("Funky Monkey") && enemy.getHP() ==  enemy.getMaxHP()/2)
			{
				//ultimate move -- one-shots opponent. 
				toExecute = new Move("Vertex of Finality");
				executeEnemyMove();
			}
			else
			{
				//initialize enemy move set 
				ArrayList<String> moveSet = enemy.getMoveset();
				//get a random move for enemy to execute 
				int getMove = GameData.getRandom(0, moveSet.size()-1);
				toExecute = new Move(moveSet.get(getMove));
				
				//if enemy has enough mana 
				if(toExecute.getCost() <= enemy.getMana())
				{
					//and if enemy is alive and well
					if(!enemy.isDefeated())
					{
						//execute the move 
						executeEnemyMove();
					}
					else
					{
						//start a new turn!  
						startTurn();
					}
				}
				else
				{
					//recursive. choose another move and start again. 
					configureEnemyMove();
				}
			}
		}

		/**
		 * Executes the enemy's move.
		 */
		public void executeEnemyMove()
		{
			//update the text area
			String toPrint = toExecute.executeMove(enemy, user, turn);
			gameTurnInfo.append(toPrint + "\n\n");
			//start a new turn! 
			startTurn();
		}
	}
	/* GameUI; CENTER */
	class GameUI extends JPanel implements ActionListener, MouseMotionListener
	{
		private Timer updateTimer;			//so we have time for animation
		private boolean descriptionShowing;//
		
		//initialize timer 
		public GameUI()
		{
			updateTimer = new Timer(0, this);
			updateTimer.start();
		}
		/*
		 * paintComponent method. Called by repaint(). 
		 */
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			//THESE VARIABLES ARE FOR PSEUDOCODE PURPOSES (may be implemented later). 
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
			
			//if there is an enemy (not tutorial)
			if(enemy != null)
			{
				drawEnemy(g, enemy.getName());
			}
			
			g.setColor(Color.BLACK);//set color to black to draw strings
			g.drawString(user.getName(), 200, 375); //draw user and enemy labels
			
			//draw enemy information in panel 
			if(enemy != null)
			{
				g.drawString(enemy.getName(), 600, 375);
				g.drawString("Level " + enemy.getLevel(), 600, 400);
				g.drawString("HP: " + enemy.getHP() + "/" + enemy.getMaxHP(), 600, 425);
				g.drawString("Defense: " + enemy.getDefense(), 600, 450);
			}
		}
		
		/*
		 * draws enemy given enemy name 
		 */
		public void drawEnemy(Graphics g, String name)
		{
			Graphics2D g2d = (Graphics2D) g;
			
			//logic differs based on the enemy 
			if(name.equals("Circle"))
			{
				//draw the enemy
				g.setColor(Color.RED);
				g.fillOval(600, 150, 200, 200);

				g.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(8));//the border weight 
				g2d.drawOval(600, 150, 200, 200);
			}
			else if(name.equals("Circle Rebel"))
			{
				//draw the enemy
				g.setColor(Color.BLUE);
				g.fillOval(600, 150, 200, 200);

				g.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(8));
				g2d.drawOval(600, 150, 200, 200);
			}
			else//placeholder is a 200x200 square for now 
			{
				g.fillRect(600, 150, 200, 200);
			}
		}
		/*
  		 * called by game ui timer. calls paintComponent(). 
     		 */
		public void actionPerformed(ActionEvent evt)
		{
			//if the timer fv called it, call repaint 
			if(evt.getSource() == updateTimer)
			{
				repaint();
			}
		}
		//MOUSE EVENTS. 
		/*
  		 * show description if mouse is hovering over range. 
     		 */
		public void mouseMoved(MouseEvent evt)
		{
			//get mouse location 
			int x = evt.getX();
			int y = evt.getY();

			//if in range (200, 150) to (350, 600)
			if(x >= 600 && x <= 200 && y >= 150 && y <= 350)
			{
				//show description
				descriptionShowing = true;
			}
			else
			{
				//hide description
				descriptionShowing = false;
			}
		}
		public void mouseDragged(MouseEvent evt) {}//to complete implementation
	}
	/* User Info panel; SOUTH */
	class UserInfo extends JPanel implements ActionListener
	{
		private Timer updateInfoTimer;		//another one of these guys to update periodically! 
		
		public UserInfo()
		{
			setBackground(Color.LIGHT_GRAY);
			updateInfoTimer = new Timer(0, this);
			updateInfoTimer.start();
		}
		
		/*
		 * paintComponent method. Called by repaint(). Draws user information. 
		 */
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			//can also be "Serif" (font doesn't matter)
			//cannot get hp and mana at the moment as user is not set 
			
			g.setFont(new Font("Verdana", Font.BOLD, 35));
			g.drawString(user.getName(), 0, 35);

			//draw user hp, mana, and defense
			g.setFont(new Font("Verdana", Font.BOLD, 25));
			g.drawString("HP: " + user.getHP() + "/" + user.getMaxHP(), 0, 70);
			g.drawString("Mana: " + user.getMana() + "/" + user.getMaxMana(), 0, 105);
			g.drawString("Defense: " + user.getDefense(), 0, 140);
		}
		
		/*
		 * actionPerformed method. Called by timer, calls repaint(). 
		 */
		public void actionPerformed(ActionEvent evt)
		{
			//if the timer called it
			if(evt.getSource() == updateInfoTimer)
			{
				repaint();
			}
		}
	}
	/*
	 * move listener class. 
  	 */
	class MovesListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String moveName = evt.getActionCommand();

			///integrate later, for now here for testing
			boolean enoughMana = round.setMoveToExecute(moveName);

			//only get the problem if enough mana
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
	/*
	 * the listener class for navigation buttons to show another panel 
	 */
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
			else if(command.equals("Next")) //if user pressed next button, next game and print to console. 
			{
				//based on user status, switch functions
				if(!user.isDefeated())
				{
					//if user is not defeated, show intermission panel 
					GameData.switchCard("intermission");
					//get number of defeated enemies and user level 
					int defeatedEnemies = GameData.getEnemiesDefeated();
					int level = user.getLevel();
					
					//when user defeats the same number of enemies as 5x their level
					if(defeatedEnemies >= level*5)
					{
						//increase user level and give user the upgrade popup 
						user.increaseLevel();
						upgradePopup.resetPoints();
						upgradePopup.show();
						
						GameData.writeData(true); //saves data to file
					}
				}
				else
				{
					//if user is defeated, end game and go to main menu 
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
