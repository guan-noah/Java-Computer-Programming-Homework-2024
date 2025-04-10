// Noah Guan
// 4/3/2025
// PutItTogether.java  
// P.6 Java w/ Mr. Yu

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
		frame.setSize( 800, 800 );				
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE); 
		frame.setLocation(0,0);
		frame.setResizable(true);
		PutItTogetherHolder pith = new PutItTogetherHolder(new Information()); 		
		frame.getContentPane().add( pith );		
		frame.setVisible(true);		
	}
}

/// this panel holds the main cards/panel 
class PutItTogetherHolder extends JPanel 
{
	private Information info;
	public PutItTogetherHolder()
	{
		info = infoIn;
		setBackground(Color.CYAN);

		CardLayout cards = new CardLayout();							//has 6 different cards 
		setLayout(cards);
		
		Information info = new Information();
		FirstPagePanel fpp = new FirstPagePanel(this, cards, info);
		//~ FixedPanelHolder fph = new FixedPanelHolder(info);
		HomePanelHolder hph = new HomePanelHolder();
		BothPictPanel bpp = new BothPictPanel();
		DrawPanel dp = new DrawPanel();
		
		add(fpp, "First");												
		/*card 1 = welcome (JLabel); terms and conditions (JTextArea & JScrollPane); enter name (JTextField); accept (JCheckBox)*/
		add(hph, "Home");												
		/*card 2 = welcome name (JLabel); programDescription (JTextArea); please select... (JLabel); TwoPeople/Colors/Masterpiece (JRadioButtons); Home (JButton)*/
		add(bpp, "BothPicts");						
		/*card 3 = */
		add(dp, "DrawPan");						
		/*card 4 = */
		//~ add();						
		/*card 5 = */
		//~ add();						
		/*card 6 = */
		cards.show(this, "First");
	}
}

/// First page to show up.  Gives information, asks for name.  Goes to Home page.
class FirstPagePanel extends PutItTogetherPan
{
	private PutItTogetherHolder panelCards;
	private CardLayout cards;
	private Information info;
	private JTextField tfName;
	
	public FirstPagePanel()
	{
		super();
	}
	public FirstPagePanel(Information infoIn)							//this is supposed to take the place of parameters cardsIn, cardPanIn, cardPanNameIn, and infoIn
	{
		super(infoIn);
	}
	
	public FirstPagePanel(PutItTogetherHolder panelCardsIn, CardLayout cardsIn, Information infoIn)//simple prep panel
	{
		
		setLayout(new FlowLayout());
		setBackground(Color.CYAN);
		
		cards = cardsIn;
		panelCards = panelCardsIn;
		info = infoIn;
		
		tfName = new JTextField("type your name", 18);
		tfName.addActionListener(new AListen());
		add(tfName);
		
		add(new JLabel("As soon as you type your name, you will be transported to the next panel."));
	}
	class AListen implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			System.out.println(tfName.getText());
			info.setName(tfName.getText());
			cards.show(panelCards, "Home");
		}
	}
}

class FixedPanelHolder extends JPanel
{
	private Information info;
	private JButton homeButton;
	public FixedPanelHolder()
	{
		
	}
	public FixedPanelHolder(Information infoIn)
	{
		info = infoIn;
		
	}
	
}

class HomePanelHolder extends PutItTogetherPan
{
	private Image picture;
	private String username;
	//~ private Information info;
	//~ private CardLayout cards;
	//~ private JPanel cardPan;
	
	public HomePanelHolder()
	{
		super();
		
		addComponents();
	}
	
	public HomePanelHolder (Information infoIn)
	{
		super(infoIn);
		username = info.getName();
		
		addComponents();
	}
	/*//not sure if we need yet
	public HomePanelHolder(Information infoIn, CardLayout cardsIn)
	{
		info = infoIn;
		cards = cardsIn;
	}*/
	
	public void addComponents()
	{
		Font largeBold = new Font("Arial", Font.BOLD, 40);
		Font smallBold = new Font("Arial", Font.BOLD, 15);
		setBackground(Color.YELLOW);
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 100));
		
		JLabel welcomeName = new JLabel("Welcome " + username);
		welcomeName.setFont(largeBold);
		add(welcomeName);
 		
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
 		JRadioButton b1 = new JRadioButton("A picture of two people");
 		homegroup.add(b1);
 		JRadioButton b2 = new JRadioButton("Draw Panel");
 		homegroup.add(b2);
 		//~ JRadioButton b3 = new JRadioButton("Masterpiece");
 		//~ homegroup.add(b3);
 		
 		radioButtonPan.add(selectOne);									//add all components to radioButtonPan
 		radioButtonPan.add(b1);
 		radioButtonPan.add(b2);
 		//~ radioButtonPan.add(b3);
 		
 		add(radioButtonPan);											//add radioButtonPan to actual panel
	}
	
	public CardLayout getCardLayout()
	{
		return cards;
	}
	class RadioButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String buttonname = evt.getActionCommand();
			if(buttonname.equalsIgnoreCase("A picture of two people"))
				cards.show(cardPan, "BothPicts");
			else if(buttonname.equalsIgnoreCase("Draw Panel"))
				cards.show(cardPan, "DrawPan");
			//~ else if(buttonname.equalsIgnoreCase("Masterpiece"))
				//~ cards.show(cardsPan, "");
		}
	}
}

class HomePanel extends PutItTogetherPan
{
	
	/** Since the label for the name was created when the classes constructor was called
	// it needs to be updated after the user types in the name into the text field.
	// Update that label in paintComponent. **/
	public void paintComponent(Graphics g)
	{
	}
	
	
}

class BothPictPanel extends PutItTogetherPan implements MouseListener
{
	public BothPictPanel() {}
	public BothPictPanel(Information infoIn)
	{
		super(infoIn);
	}
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

class MyPictPanel extends PutItTogetherPan implements ActionListener
{
	public MyPictPanel()
	{
		
	}
	public void actionPerformed(ActionEvent evt)
	{
		
	}
}


class FriendPictPanel extends PutItTogetherPan implements ActionListener
{
	public FriendPictPanel()
	{
		
	}
	public void actionPerformed(ActionEvent evt)
	{
		
	}
}

class DrawPanel extends PutItTogetherPan
{
	private RightPanel rp;
	private int amtRed, amtGreen, amtBlue;
	private int size;
	
	public DrawPanel() {}
	public DrawPanel(Information infoIn)
	{
		super(infoIn);
	}
	
	public class LeftPanel extends JPanel
	{
	}
	
	public class RightPanel extends JPanel
	{
	}
}

/**  **/


class PutItTogetherPan extends JPanel									//parent class, integrated class Information 
{
	protected Information info;
	protected CardLayout cards;
	protected JPanel cardPan;
	protected String cardPanName;
	public PutItTogetherPan()
	{
		info = null;
		cards = null;
		cardPan = null;
		cardPanName = null;
	}
	public PutItTogetherPan(Information infoIn)
	{
		info = infoIn;
		getInfoData();
	}
	public void getInfoData()
	{
		//assumed that info is already 
		cards = info.getCardLayout();
		cardPan = info.getCardPan();
		cardPanName = info.getCardPanName();
	}
}

class Information														//powerful class! 
{
	private String name, cardPanName;
	private CardLayout cards;
	private JPanel cardPan;
	
	public Information()
	{
		name = null;
		cards = null;
		cardPan = null;
		cardPanName = null;
	}
	
	public Information(Information infoIn)
	{
		name = infoIn.getName();
		cards = infoIn.getCardLayout();
		cardPan = infoIn.getCardPan();
		cardPanName = infoIn.getCardPanName();
	}
	
	public Information(CardLayout cardsIn, JPanel cardPanIn, String cardPanNameIn)
	{
		cards = cardsIn;
		cardPan = cardPanIn;
		cardPanName = cardPanNameIn;
	}
	public String getName()
	{
		return name;
	}
	
	public void setName(String nameIn)
	{
		name = nameIn;
	}
	
	public CardLayout getCardLayout()
	{
		return cards;
	}
	
	public void setCardLayout(CardLayout cardsIn)
	{
		cards = cardsIn;
	}
	
	public JPanel getCardPan()
	{
		return cardPan;
	}
	
	public void setCardPan(JPanel cardPanIn)
	{
		cardPan = cardPanIn;
	}
	
	public String getCardPanName()
	{
		return cardPanName;
	}
	
	public void setCardPanName(String cardPanNameIn)
	{
		cardPanName = cardPanNameIn;
	}
}
