// 
// 
// PutItTogether.java  
// 

/// imports
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//~ import java.awt.event.KeyEvent;
//~ import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Dimension;

public class PutItTogether
{	
	public PutItTogether()
	{
	}
	
	public static void main(String [] args)
	{
		PutItTogether pit = new PutItTogether();
		pit.run();
	}
	
	public void run()
	{
		JFrame frame = new JFrame("PutItTogether");
		frame.setSize( 800, 800);				
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE); 
		frame.setLocation(00,50);
		frame.setResizable(true);
		PutItTogetherHolder pith = new PutItTogetherHolder(); 		
		frame.getContentPane().add( pith );		
		frame.setVisible(true);		
	}
}

/** this panel holds the main cards/panel 
 * the way this program is structured: pass CardLayout and JPanel through all the other panels
 * so that the other panels can show each other (call the method)**/
class PutItTogetherHolder extends JPanel 
{	
	public PutItTogetherHolder()
	{
		setBackground(Color.CYAN);

		CardLayout cards = new CardLayout();
		setLayout(cards);
		
		Information info = new Information();
		FirstPagePanel fpp = new FirstPagePanel(this, cards, info);
		FixedPanelHolder hph = new FixedPanelHolder(info);
		
		add(fpp, "First");
		add(hph, "Home");
	}
}

// First page to show up.  Gives information, asks for name.  Goes to Home page.
class FirstPagePanel extends JPanel
{
	private PutItTogetherHolder panelCards;
	private CardLayout cards;
	private Information info;
	private JTextField tfName;
	
	public FirstPagePanel(PutItTogetherHolder panelCardsIn, CardLayout cardsIn, Information infoIn)
	{
		setBackground(Color.CYAN);
 		setLayout(new FlowLayout(FlowLayout.CENTER, 10000, 50));		//insanely long horizontal gap, vertical gap
 		
 		cards = cardsIn;
 		panelCards = panelCardsIn;
 		info = infoIn;
 		
 							//how to set textarea font 
 		String areaText = "Welcome to my program. This program is an " + 
 			"attempt to try and put together what we have learned this " + 
 			"year. It has examples of multiple layouts and components " + 
 			"as well as using graphics to draw pictures.\n\n\n";
 		JTextArea jta = new JTextArea(areaText, 8, 20);					//rows, columns
 		jta.setFont(new Font("Arial", Font.PLAIN, 23));	
 		jta.setLineWrap(true);
 		jta.setWrapStyleWord(true);
 		jta.setEditable(false);
 		JScrollPane scroll = new JScrollPane(jta);
 		add(jta);
 		
 		tfName = new JTextField("Type your name", 20);
 		tfName.addActionListener(new AListen());
 		add(tfName);
 		
 		JCheckBox iUnderstand = new JCheckBox("I Understand the " + 
 			"directions. Take me to the next page");
 		iUnderstand.addActionListener(new CheckBoxHandler());
 		add(iUnderstand);
	}
	class AListen implements ActionListener
 	{
 		public void actionPerformed(ActionEvent evt)
 		{
 			info.setName(tfName.getText());
 			//cards.show(panelCards, "Home");
 			
 		}
 	}
	class CheckBoxHandler implements ActionListener						//has to be inside to access cards
 	{
 		public void actionPerformed(ActionEvent evt)
 		{
 			/*String text = tfName.getText();								//if user edited text in text field 
 			if(!text.equalsIgnoreCase("Type your name"))
 			*/
 				cards.show(panelCards, "Home");							//show next 
 			/*
 			else
 			{
 				repaint();
 			}
 			*/
 		}
 	}
}

/** just the home panel at bottom **/
class FixedPanelHolder extends JPanel
{
	private Information info;
	private JButton homeButton;
	private HomePanelHolder hpholder;
	
	public FixedPanelHolder(Information infoIn)
	{
		info = infoIn;
		
		setBackground(Color.YELLOW);
		setLayout(new BorderLayout());
		
		hpholder = new HomePanelHolder(info);
		
		homeButton = new JButton("Home");
		homeButton.addActionListener(new HomeButtonHandler());
		
		JPanel hbpan = new JPanel();									//homebuttonpanel (bottom yellow bar with home button)
		hbpan.setBackground(Color.YELLOW);
		hbpan.setLayout(new FlowLayout());
		hbpan.add(homeButton);											//homebutton
		
		add(hbpan, BorderLayout.SOUTH);
		add(hpholder, BorderLayout.CENTER);
	}
	class HomeButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			hpholder.getHomePan().repaint();
			hpholder.getCardLayout().show(hpholder, "Home");
		}
	}
	
}
/** has the CardLayout; contains the other JPanels  **/
class HomePanelHolder extends JPanel
{
	private Image picture;
	private Information info;
	private String pictName;
	private CardLayout cards;
	private HomePanel homepan;
	
	public HomePanelHolder (Information infoIn)
	{
		info = infoIn;
		cards = new CardLayout();
		
		setLayout(cards);
		homepan = new HomePanel(this, cards, info);
		add(homepan, "Home");
		BothPictPanel bothpictpan = new BothPictPanel();
		add(bothpictpan, "BothPicts");
		MyPictPanel mypictpan = new MyPictPanel();
		add(mypictpan, "MyPict");
		FriendPictPanel friendpictpan = new FriendPictPanel();
		add(friendpictpan, "FriendPict");
		DrawPanel drawpan = new DrawPanel();
		add(drawpan, "DrawPan");
		
		//~ Class classReference = new Class();
		//~ in panelHolder class: add(classReference, "ClassName");
		//~ to show: cardLayout.show(panelHolder, "ClassName");
	}
	
	public HomePanel getHomePan()
	{
		return homepan;
	}
	
	public CardLayout getCardLayout()									//crucial for any class that wants to go to another page 
	{
		return cards;
	}
}
/** the second yellow page **/
class HomePanel extends JPanel
{
	HomePanelHolder hph;
	CardLayout cards;
	Information info;
	JLabel username;
	public HomePanel(HomePanelHolder hphIn, CardLayout cardsIn, Information infoIn)
	{
		hph = hphIn;
		cards = cardsIn;
		info = infoIn;
	 	System.out.println("Constructor: " + info.getName());
		username = new JLabel("Welcome " + info.getName());				//currently says null at the time of construction 
		
		setBackground(Color.YELLOW);
		addComponents();
	}
	// Since the label for the name was created when the classes constructor was called
	// it needs to be updated after the user types in the name into the text field.
	// Update that label in paintComponent.
	public void paintComponent(Graphics g)
	{
		System.out.println("paintComponent: " + info.getName());
		username.setText("Welcome " + info.getName());					///NOTE!! I screwed up a ton. If you create a JLabel, you should just use the same one instead of creating a new one. The new one doesn't even change. 
	}
	
	public void addComponents()
 	{
 		Font largeBold = new Font("Arial", Font.BOLD, 40);
 		Font smallBold = new Font("Arial", Font.BOLD, 15);
 		setBackground(Color.YELLOW);
 		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 100));
 		
 		username.setFont(largeBold);
 		add(username);
  		
  							//how to set textarea + font 
  		String areaText = "Welcome to my program. This program is an " + 
  			"attempt to try and put together what we have learned this " + 
  			"year. It has examples of multiple layouts and components " + 
  			"as well as using graphics to draw pictures.\n\n\n\n\n";
  		JTextArea jta = new JTextArea(areaText, 8, 30);
  		jta.setFont(new Font("Arial", Font.PLAIN, 30));	
  		jta.setLineWrap(true);
  		jta.setWrapStyleWord(true);
  		//~ jta.setEditable(false);
  		//~ jta.setBackground(Color.YELLOW);
  		
  		JScrollPane scroll = new JScrollPane(jta);
  		add(jta);
 																		///builds button formatting at bottom
  		JPanel radioButtonPan = new JPanel();
  		radioButtonPan.setLayout(new GridLayout(3, 1, 15, 0));			//rows, columns, hgap, vgap
 																		//change to 4 1 15 0 when you add Masterpiece panel 
  		
  		JLabel selectOne = new JLabel("Select one of the two options");
  		selectOne.setBackground(Color.YELLOW);
  		selectOne.setFont(smallBold);
  		
  		ButtonGroup homegroup = new ButtonGroup();
  		RadioButtonHandler rbhandler = new RadioButtonHandler();
  		JRadioButton b1 = new JRadioButton("A picture of two people");
  		homegroup.add(b1);
  		b1.addActionListener(rbhandler);
  		
  		JRadioButton b2 = new JRadioButton("Draw Panel");
  		homegroup.add(b2);
  		b2.addActionListener(rbhandler);
  		
  		//~ JRadioButton b3 = new JRadioButton("Masterpiece");
  		//~ homegroup.add(b3);
  		
  		radioButtonPan.add(selectOne);									//add all components to radioButtonPan
  		radioButtonPan.add(b1);
  		radioButtonPan.add(b2);
  		//~ radioButtonPan.add(b3);
  		
  		add(radioButtonPan);											//add radioButtonPan to actual panel
 	}
 	class RadioButtonHandler implements ActionListener
 	{
 		public void actionPerformed(ActionEvent evt)
 		{
 			String buttonname = evt.getActionCommand();
 			if(buttonname.equalsIgnoreCase("A picture of two people"))
 				cards.show(hph, "BothPicts");
 			else if(buttonname.equalsIgnoreCase("Draw Panel"))
 				cards.show(hph, "DrawPan");
 			//~ else if(buttonname.equalsIgnoreCase("Masterpiece"))
 				//~ cards.show(cardsPan, "");
 		}
 	}
}

class BothPictPanel extends JPanel implements MouseListener
{
	public void mouseClicked(MouseEvent evt)
 	{
		
 	}
 	public void mousePressed(MouseEvent evt)
 	{
 
 	}
 	public void mouseReleased(MouseEvent evt)
 	{
 
 	}
 	public void mouseEntered(MouseEvent evt)
 	{
 
 	}
 	public void mouseExited(MouseEvent evt)
 	{
 
 	}
}

/** person 1 **/
class MyPictPanel extends JPanel implements ActionListener
{
	public void actionPerformed(ActionEvent evt)
 	{
		
 	}
}

/** person 2 **/
class FriendPictPanel extends JPanel implements ActionListener
{
	public void actionPerformed(ActionEvent evt)
 	{
		
 	}
}

class DrawPanel extends JPanel
{
	private RightPanel rp;
	private int amtRed, amtGreen, amtBlue;
	private int size;
	
	public class LeftPanel extends JPanel
	{
	}
	
	public class RightPanel extends JPanel
	{
	}
}

class Information
{
	private String name;
	
	public Information()
	{
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String nameIn)
	{
		name = nameIn;
	}
}

