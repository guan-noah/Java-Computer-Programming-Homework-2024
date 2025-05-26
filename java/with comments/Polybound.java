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
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

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

	/*
	 * Krish and Noah wrote this method
	 */
	public void run()
	{
		//gives the user a demo upon start
		GameData.setDemoMode(true);
		initFonts();//initialize fonts 
		
		
		//the rest of the method sets up the main JFrame with its panels. 
		JFrame frame = new JFrame("Polybound");
		//the cardlayout with all the panels inside
		JPanel deck = new JPanel(new CardLayout());
		//initializes all panels 
		MainMenuPanel mPanel = new MainMenuPanel();
		IntermissionPanel imPanel = new IntermissionPanel();
		GamePanel gPanel = new GamePanel();
		ProblemPanel pPanel = new ProblemPanel();
		SelectUserInfoPanel uInfoPanel = new SelectUserInfoPanel();
		SavesPanel sPanel = new SavesPanel();
		
		//get saved data
		checkForData();
		GameData.setCards(deck);
		GameData.setProblemPanel(pPanel);
		GameData.setGamePanel(gPanel);
		GameData.setIntermissionPanel(imPanel);
		GameData.setSavesPanel(sPanel);
		
		//add all panels to cardlayout 
		deck.add(mPanel, "main menu");
		deck.add(sPanel, "saves");
		deck.add(uInfoPanel, "user info");
		deck.add(imPanel, "intermission");
		deck.add(gPanel, "game");
		deck.add(pPanel, "problem");
		
		frame.setSize(1200, 750);	//normal: 600, 500
		frame.setResizable(false);		//user can't change frame dimensions
		frame.setLocationRelativeTo(null); //centers everything
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		//set background of frame to cardlayout 
		frame.setContentPane(deck);
		frame.setVisible(true);
	}
	
	/*
	 * This method calls / initializes the fonts 
	 */
	public void initFonts()
	{
		loadFont("oswald.ttf");
		loadFont("shareTech.ttf");
	}
	
	/*
	 * This method loads the font given a file name. 
	 */
	public void loadFont(String fileName)
	{
		File fontfile = new File(fileName);

		try
		{
			//initialize custom font 
			Font font = Font.createFont(Font.TRUETYPE_FONT, fontfile).deriveFont(24f);
			//register the font with the graphics environment 
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		}
		catch(Exception e)
		{
			//give error message 
			System.err.printf("Error: Could not load font file \"%s\"%n", fileName);
		}
	}
	
	/*
	 * This method loads previous game data into current game via saveData.txt, 
	 * the game's save file. Utilizes loadToGameData. 
	 */
	public void checkForData()
	{
		//standard file reading logic 
		String fileName = "saveData.txt";
		File dataFile = new File(fileName);
		Scanner read = null;
		try
		{
			read = new Scanner(dataFile);
			
			//only load to game data if scanner initialized correctly 
			//enhances code clarity
			loadToGameData(read);
		}
		catch(FileNotFoundException e)
		{
			//give an error message
			System.err.printf("Error: Could not find file \"%s\"%n", fileName);
		}
	}
	/*
	 * Method for encapsulation and code clarity. 
	 * Can only be called by methods in this class. 
	 */
	private void loadToGameData(Scanner read)
	{
		//create an arraylist of saves (goal is to read this into game data in the end 
		ArrayList<Save> saves = new ArrayList<>();
		
		//while scanner has next 
		while(read.hasNext())
		{
			//read in the next line 
			String line = read.nextLine();
			//if any save found 
			if(!line.equals("No save found."))
			{
				GameData.setUserName(line); ///sets username
				String name = line;//user name = the first line

				int enemiesDefeated = read.nextInt();//next int is enemies defeated 
				read.nextLine();//clear the rest of the line 

				Character[] characters = new Character[3];//array of the 3 characters 
				
				//for each of the 3 characters 
				for(int i=0; i<characters.length; i++)
				{
					//~ System.out.println(i);//debugging - print out the index
					
					//read in the character information and cache as temp variables 
					String charName = read.nextLine();//character name 

					int level = read.nextInt();//cache in user level 
					read.nextLine();//get rid of the rest of line (including return carriage)

					line = read.nextLine();//use line to parse hp out of max hp 
					int hp = Integer.parseInt(GameData.getDataTo(line, "/"));
					int maxHP = Integer.parseInt(GameData.dataAfter(line, "/"));

					line = read.nextLine();//use line to parse mana out of max mana 
					int mana = Integer.parseInt(GameData.getDataTo(line, "/"));
					int maxMana = Integer.parseInt(GameData.dataAfter(line, "/"));

					int defense = read.nextInt();//cache in defense 
					//if there's another line/return carriage in scanner 
						//after reading in defense, DISCARD it 
					if(read.hasNext())
						read.nextLine();
					
					//input all that character information into characters array 
					characters[i] = new Character(charName, level);
					characters[i].overrideStats(hp, maxHP, mana, maxMana, defense);
				}
				
				//add character array into saves
				saves.add(new Save(name, enemiesDefeated, characters));
				
				//unused code - commented out for now 
				///Character playerChar = new Character(charName, level);
				///playerChar.overrideStats(hp, maxHP, mana, maxMana, defense);
				///GameData.setEnemiesDefeated(enemiesDefeated);
				///GameData.setPlayerCharacter(playerChar);
				
				//set game started to true! user already started the game before. 
				GameData.setGameStarted(true);
			}
			else
			{
				//game started stays false. 
				GameData.setGameStarted(false);
			}
		}
		
		//save the list of characters to gamedata 
		GameData.setSaveList(saves);
	}
}

//EXTENSIONS OF JAVA COMPONENTS 
/**
 * Krish Kumar
 * Period 6
 * Button.java
 * 
 * This class extends from JButton. The purpose of it is to allow for a
 * much simpler implementation of buttons within the game.
 * 
 * NOTE: THIS CLASS IS VERY MUCH SUBJECT TO CHANGE!
 **/
class Button extends JButton
{
	private boolean isHoveredOver;

	/**
	 * One of the constructors, responsible for setting up the Button
	 * instance based on the parameters given. In this case, the superclass
	 * constructor is called, and the ActionListener is added.
	 **/
	public Button(String text, ActionListener buttonListener)
	{
		super(text); ///superclass constructor call
		addActionListener(buttonListener); ///adds the ActionListener
		setFocusPainted(false);//not painted by focus yet 

		isHoveredOver = false;//not hovered over
		//add mouse listener to button
		ButtonHandler btnHandler = new ButtonHandler();
		addMouseListener(btnHandler);
	}
	
	/**
	 * One of the constructors, responsible for setting up the Button instance
	 * based on the parameters given. In this case, the superclass constructor
	 * is called, the ActionListener is added, and the font is configured
	 * based on the font size passed in.
	 **/
	public Button(String text, ActionListener buttonListener, int fontSize)
	{
		super(text); ///superclass constructor call
		setFont(new Font("Oswald Regular", Font.BOLD, fontSize)); ///font is configured
		addActionListener(buttonListener); ///adds the ActionListener
		setFocusPainted(false);//not painted by focus yet 
		
		//same logic as previous constructor 
		isHoveredOver = false;
		ButtonHandler btnHandler = new ButtonHandler();
		addMouseListener(btnHandler);
	}
	
	/**
	 * One of the constructors, responsible for setting up the Button
	 * instance based on the parameters given. In this case, the superclass
	 * constructor is called, the ActionListener is added, and the font is
	 * set to the Font passed in.
	 **/
	public Button(String text, ActionListener buttonListener, Font buttonFont)
	{
		super(text); ///superclass constructor call
		setFont(buttonFont); ///font is configured
		addActionListener(buttonListener); ///adds the ActionListener
		setFocusPainted(false);//not painted by focus yet 
		
		//same logic as previous constructor 
		isHoveredOver = false;
		ButtonHandler btnHandler = new ButtonHandler();
		addMouseListener(btnHandler);
	}
	
	/*
	 * paintComponent method. Called by repaint() and upon initialization. 
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;//make graphics 2d 

		if(isHoveredOver)//boolean determined in buttonhandler and mouse events 
		{
			g2d.setStroke(new BasicStroke(10));//set shape border to 10 px
			g2d.setColor(Color.YELLOW);//draw yellow 
			g2d.drawRect(0, 0, getWidth(), getHeight());//draw a rectangle over the panel behind the component
		}
		
		//dispose the graphics 2d object
		g2d.dispose();
	}
	
	/*
	 * Button handler class. Implements MouseListener but only sets 
	 * isHoveredOver to true/false using mouseEntered and mouseExited. 
	 */
	class ButtonHandler implements MouseListener
	{
		public void mouseEntered(MouseEvent evt)
		{
			isHoveredOver = true;//Hovered over
			repaint();//call paintComponent
		}
		
		/*
		 * User mouse exited component boundaries. 
		 */
		public void mouseExited(MouseEvent evt)
		{
			isHoveredOver = false;//Not hovered over
			repaint();//call paintComponent
		}
		
		//unused methods, only used for completion of interface  
		public void mousePressed(MouseEvent evt)
		{}
		public void mouseClicked(MouseEvent evt)
		{}
		public void mouseReleased(MouseEvent evt)
		{}
	}
}
/**
 * Krish Kumar
 * Period 6
 * Label.java
 * 
 * This class extends from JLabel. The purpose of it is to allow for a
 * much simpler implementation of labels within the game.
 * 
 * NOTE: THIS CLASS IS VERY MUCH SUBJECT TO CHANGE!
 **/
class Label extends JLabel
{
	/**
	 * One of the constructors, responsible for setting up the Label
	 * instance based on the parameters given. In this case, the superclass
	 * constructor is called, and the font is set to the Font passed in.
	 **/	
	public Label(String text, Font labelFont)
	{
		super(text); ///superclass constructor call
		setFont(labelFont); ///font is configured
	}
	 
	/**
	 * One of the constructors, responsible for setting up the Label
	 * instance based on the parameters given. In this case, the superclass
	 * constructor is called, and the font is configured based on the font
	 * size passed in.
	 **/	
	public Label(String text, int fontSize)
	{
		super(text); ///superclass constructor call
		setFont(new Font("Share Tech Regular", Font.BOLD, fontSize)); ///font is configured
	}
 
	/*
	 * Formats the text using HTML.
	 * Customizes/Supports line breaks and italicized text.
	 * '_' = line break
	 * '*' = italicized text 
	 */
	 public void setText(String text)
	 {
		 //initializes toSet with html header 
		String toSet = "<html>";
		boolean closedBold = true;//initializes closedBold
 
		for(int i=0; i<text.length(); i++)
		{
			char charAt = text.charAt(i);//get current char
			if(charAt == '_')//if it's an underscore
			{
				toSet += "<br>";//add a new line
			}
			else if(charAt == '*')//if it's a star
			{
				//if closed bold, add emphasize text start header (em tag)
				if(closedBold)
					toSet += "<em>";
				else//if not, add the close header 
					toSet += "</em>";
 
				closedBold = !closedBold;//make sure that bold is closed by reversing it
			}
			else//if it's another character 
			{
				toSet += text.charAt(i);
			}
		}
		
		//ending html header 
		toSet += "</html>";
		super.setText(toSet);//call superclass' set text method 
			//(different from super(toSet), which needs to be the first statement!!)
	}
}
/**
 * Krish Kumar
 * Period 6
 * TextField.java
 * 
 * This class extends from JTextField. The purpose of it is to allow for a
 * much simpler implementation of text fields and their FocusListeners
 * within the game.
 * 
 * NOTE: THIS CLASS IS VERY MUCH SUBJECT TO CHANGE!
 **/
class TextField extends JTextField
{
    private final Font SELECTED;//selected font (unchanged)
    private final Font UNSELECTED;//deselected font (unchanged) 
    private String defaultText;
	
	/*
	 * initialize variables and set up textfield 
	 */
    public TextField(String defaultTextIn, int cols, int fontSize)
    {
        super(defaultTextIn, cols);//call jtextfield(text, columns)
        defaultText = defaultTextIn;

        SELECTED = new Font("Oswald Regular", Font.BOLD, fontSize);//bold
        UNSELECTED = new Font("Oswald Regular", Font.ITALIC, fontSize);//italic 

        setForeground(Color.GRAY);//gray background
        setFont(UNSELECTED);//as a default
		
		//add text field handler 
        TextFieldFocusHandler tfFocusHandler = new TextFieldFocusHandler();
        addFocusListener(tfFocusHandler);
    }
	/*
	 * Returns if the text field font matches the font when field is selected. 
	 */
    public boolean isSelected()
    {
        return getFont() == SELECTED;
    }
	
	/*
	 * text field focus handler: implements FocusListener and changes the 
	 * font in text field accordingly based on focus gained or lost. 
	 */
    class TextFieldFocusHandler implements FocusListener
    {
		/*
		 * This method executes when user clicks on textfield component. 
		 */
        public void focusGained(FocusEvent evt)
        {
			if(!isSelected())//if it wasn't selected before 
			{
				setForeground(Color.BLACK);
				setFont(SELECTED);//change the font; changes isSelected() return value 
				setText("");//clear the text field for the 
			}
			//else do nothing 
        }
		
		/*
		 * This method executes when user clicks away. 
		 */
        public void focusLost(FocusEvent evt)
        {
			String existingText = getText();//get text inside textfield 
			if(existingText.equals(""))//if user didn't input anything
			{
				setText(defaultText);//reset default text 
				setForeground(Color.GRAY);//change the text color 
				setFont(UNSELECTED);
			}
			//else don't reset text because the user actually inputted something
        }
    }
}
