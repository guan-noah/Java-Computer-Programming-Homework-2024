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
		GameData.setDemoMode(true);
		initFonts();

		JFrame frame = new JFrame("Polybound");
		JPanel deck = new JPanel(new CardLayout());
		IntroPanel iPanel = new IntroPanel();
		MainMenuPanel mPanel = new MainMenuPanel();
		IntermissionPanel imPanel = new IntermissionPanel();
		GamePanel gPanel = new GamePanel();
		ProblemPanel pPanel = new ProblemPanel();
		SelectUserInfoPanel uInfoPanel = new SelectUserInfoPanel();
		SavesPanel sPanel = new SavesPanel();

		checkForData();
		GameData.setCards(deck);
		GameData.setProblemPanel(pPanel);
		GameData.setGamePanel(gPanel);
		GameData.setIntermissionPanel(imPanel);
		GameData.setSavesPanel(sPanel);
		
		///deck.add(iPanel, "intro");
		deck.add(mPanel, "main menu");
		deck.add(sPanel, "saves");
		deck.add(uInfoPanel, "user info");
		deck.add(imPanel, "intermission");
		deck.add(gPanel, "game");
		deck.add(pPanel, "problem");
		
		frame.setSize(1200, 750);	//normal: 600, 500
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //centers everything
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		

		frame.setContentPane(deck);
		frame.setVisible(true);
	}

	public void initFonts()
	{
		loadFont("oswald.ttf");
		loadFont("shareTech.ttf");
	}

	public void loadFont(String fileName)
	{
		File fontfile = new File(fileName);

		try
		{
			Font font = Font.createFont(Font.TRUETYPE_FONT, fontfile).deriveFont(24f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			System.out.println(font.getName());
		}
		catch(Exception e)
		{
			System.err.printf("Error: Could not load font file \"%s\"", fileName);
		}
	}

	public void checkForData()
	{
		String fileName = "saveData.txt";
		File dataFile = new File(fileName);
		Scanner read = null;
		try
		{
			read = new Scanner(dataFile);
			ArrayList<Save> saves = new ArrayList<>();

			while(read.hasNext())
			{
				String line = read.nextLine();
				if(!line.equals("No save found."))
				{
					GameData.setUserName(line); ///sets username
					String name = line;

					int enemiesDefeated = read.nextInt();
					read.nextLine();

					Character[] characters = new Character[3];

					for(int i=0; i<characters.length; i++)
					{
						System.out.println(i);
						String charName = read.nextLine();

						int level = read.nextInt();
						read.nextLine();

						line = read.nextLine();
						int hp = Integer.parseInt(GameData.getDataTo(line, "/"));
						int maxHP = Integer.parseInt(GameData.dataAfter(line, "/"));

						line = read.nextLine();
						int mana = Integer.parseInt(GameData.getDataTo(line, "/"));
						int maxMana = Integer.parseInt(GameData.dataAfter(line, "/"));

						int defense = read.nextInt();

						if(read.hasNext())
							read.nextLine();

						characters[i] = new Character(charName, level);
						characters[i].overrideStats(hp, maxHP, mana, maxMana, defense);
					}

					saves.add(new Save(name, enemiesDefeated, characters));

					///Character playerChar = new Character(charName, level);
					///playerChar.overrideStats(hp, maxHP, mana, maxMana, defense);
					///GameData.setEnemiesDefeated(enemiesDefeated);
					///GameData.setPlayerCharacter(playerChar);
					GameData.setGameStarted(true);
				}
				else
				{
					GameData.setGameStarted(false);
				}
			}

			GameData.setSaveList(saves);
		}
		catch(FileNotFoundException e)
		{
			System.err.printf("Error: Could not find file \"%s\"", fileName);
		}
	}
}
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
		setFocusPainted(false);

		isHoveredOver = false;
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
		setFocusPainted(false);

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
		setFocusPainted(false);
		
		isHoveredOver = false;
		ButtonHandler btnHandler = new ButtonHandler();
		addMouseListener(btnHandler);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if(isHoveredOver)
		{
			g2d.setStroke(new BasicStroke(10));
			g2d.setColor(Color.YELLOW);
			g2d.drawRect(0, 0, getWidth(), getHeight());
		}
		
		g2d.dispose();
	}

	class ButtonHandler implements MouseListener
	{
		public void mouseEntered(MouseEvent evt)
		{
			isHoveredOver = true;
			repaint();
		}

		public void mouseExited(MouseEvent evt)
		{
			isHoveredOver = false;
			repaint();
		}

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
 
	/**
	 * Formats the text using HTML.
	 * Supports line breaks and italicized text.
	 **/
	 public void setText(String text)
	 {
		String toSet = "<html>";
		boolean closedBold = true;
 
		for(int i=0; i<text.length(); i++)
		{
			char charAt = text.charAt(i);
			if(charAt == '_')
			{
				toSet += "<br>";
			}
			else if(charAt == '*')
			{
				if(closedBold)
					toSet += "<em>";
				else
					toSet += "</em>";
 
				closedBold = !closedBold;
			}
			else
			{
				toSet += text.charAt(i);
			}
		}
 
		toSet += "</html>";
		super.setText(toSet);
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
    private final Font SELECTED;
    private final Font UNSELECTED;
    private String defaultText;

    public TextField(String defaultTextIn, int cols, int fontSize)
    {
        super(defaultTextIn, cols);
        defaultText = defaultTextIn;

        SELECTED = new Font("Oswald Regular", Font.BOLD, fontSize);
        UNSELECTED = new Font("Oswald Regular", Font.ITALIC, fontSize);

        setForeground(Color.GRAY);
        setFont(UNSELECTED);

        TextFieldFocusHandler tfFocusHandler = new TextFieldFocusHandler();
        addFocusListener(tfFocusHandler);
    }

    public boolean isSelected()
    {
        return getFont() == SELECTED;
    }
	
    class TextFieldFocusHandler implements FocusListener
    {
        public void focusGained(FocusEvent evt)
        {
			if(!isSelected())
			{
				setForeground(Color.BLACK);
				setFont(SELECTED);
				setText("");
			}
        }

        public void focusLost(FocusEvent evt)
        {
			String existingText = getText();
			if(existingText.equals(""))
			{
				setText(defaultText);
				setForeground(Color.GRAY);
				setFont(UNSELECTED);
			}
			//else don't reset text because the user actually inputted something
        }
    }
}