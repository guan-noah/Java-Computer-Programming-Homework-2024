// Noah Guan
// 04-07-2025
// PutItTogether.java  
// Period 6 Java w/ Mr. Yu

///imports 
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import java.io.File;
import java.io.FileNotFoundException;
import javax.imageio.ImageIO;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

/** the setup class (done) **/
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
		frame.setSize( 800, 700 );				
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE); 
		frame.setLocation(0,0);
		frame.setResizable(true);
		PutItTogetherHolder pith = new PutItTogetherHolder(); 		
		frame.getContentPane().add( pith );		
		frame.setVisible(true);		
	}
}

/** this panel holds the main cards/panel (-- this class holds everything) **/ 
class PutItTogetherHolder extends JPanel 
{	
	public PutItTogetherHolder()
	{
		setBackground(Color.CYAN);

		CardLayout cards = new CardLayout();
		setLayout(cards);
		
		Information info = new Information();
		FirstPagePanel fpp = new FirstPagePanel(this, cards, info);
		HomePanelHolder homeph = new HomePanelHolder(info);
		FixedPanelHolder fph = new FixedPanelHolder(info);
		BothPictPanel bpp = new BothPictPanel(info);
		MyPictPanel mypp = new MyPictPanel();
		FriendPictPanel friendpp = new FriendPictPanel();
		
		add(fpp, "First");
		add(homeph, "Home");
		add(fph, "FixedPanel");
		add(bpp, "BothPicts");
		add(mypp, "MyPictPanel");
		add(friendpp, "FriendPictPanel");
		cards.show(this, "First");
		
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
			"as well as using graphics to draw pictures.\n\n";
		JTextArea jta = new JTextArea(areaText, 8, 15);
		jta.setFont(new Font("Arial", Font.PLAIN, 30));	
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setEditable(false);
		JScrollPane scroll = new JScrollPane(jta);
		scroll.setVisible(true);
		add(scroll);
				
		tfName = new JTextField("Type your name");						//create textfield
		tfName.addActionListener(new TextFieldHandler());				//don't know if we need this
		add(tfName);
		
		JCheckBox iUnderstand = new JCheckBox("I Understand the " + 
			"directions. Take me to the next page");					//create jcheckbox
		iUnderstand.addActionListener(new CheckBoxHandler());
		add(iUnderstand);
	}
	/*
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.RED);
		g.drawString("Please type in your name.", 300, 400);
	}
	*/
	class CheckBoxHandler implements ActionListener						//has to be inside to access cards
	{
		public void actionPerformed(ActionEvent evt)
		{
			String text = tfName.getText();								//if user edited text in text field 
			/*
			if(!text.equalsIgnoreCase("Type your name"))
			*/
			{
				info.setString(text);
				cards.show(panelCards, "Home");							//show next 
			}
			/*
			else
			{
				repaint();
			}
			*/
		}
	}
	/* Text Field Handler -- don't know if we need this*/
	class TextFieldHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			///implementation
		}
	}
}
/*** Handlers ***/



class FixedPanelHolder extends JPanel									//the panel at the bottom that always has the home button
{
	private Information info;
	private JButton homeButton;
	public FixedPanelHolder()
	{
		info = new Information();
		doActions();
	}
	public FixedPanelHolder(Information infoIn)
	{
		info = infoIn;
	}
	public void doActions()
	{
		homeButton = new JButton("Home");
		homeButton.addActionListener(new HomeButtonHandler());
	}
	class HomeButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			//~ CardLayout cards = Info
		}
	}
}

class HomePanelHolder extends JPanel
{
	private Image picture;
	private Information info;
	private String pictName;
	private CardLayout cards;
	
	public HomePanelHolder (Information infoIn)
	{
	}

	public CardLayout getCardLayout()
	{
		return cards;
	}
}

class HomePanel extends JPanel
{
	
	// Since the label for the name was created when the classes constructor was called
	// it needs to be updated after the user types in the name into the text field.
	// Update that label in paintComponent.
	public void paintComponent(Graphics g)
	{
	}
	
	
}

class BothPictPanel extends JPanel implements MouseListener
{
	Information info;
	public BothPictPanel()
	{
		info = null;
	}
	public BothPictPanel(Information infoIn)
	{
		info = infoIn;
	}
	public void mouseClicked(MouseEvent evt) {}
	public void mouseEntered(MouseEvent evt) {}
	public void mouseExited(MouseEvent evt) {}
	public void mouseReleased(MouseEvent evt) {}
	public void mousePressed(MouseEvent evt) {}
}

class MyPictPanel extends JPanel implements ActionListener
{
	public void actionPerformed(ActionEvent evt) 
	{
		
	} 
}


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

class Information														//very, very powerful class! useful for storage and data parameters
{
	private String str;
	private CardLayout cards;
	private JPanel cardHolder, panelHolder;
	
	public Information()
	{
		str = null;
		cards = null;
		cardHolder = null;
		panelHolder = null;
	}
	public Information(String strIn)
	{
		str = strIn;
	}
	public Information(CardLayout cardsIn, JPanel cardHolderIn)
	{
		cards = cardsIn;
		cardHolder = cardHolderIn;
	}
	public Information(JPanel panelHolderIn)
	{
		panelHolder = panelHolderIn;
	}
	
	public String getString()
	{
		return str;
	}
	
	public void setString(String strIn)
	{
		str = strIn;
	}
	
	public JPanel getPanelHeld()
	{
		return panelHolder;
	}
	
	public void setPanelHeld(JPanel panelHolderIn)
	{
		panelHolder = panelHolderIn;
	}
	
	public CardLayout getCardLayout()
	{
		return cards;
	}
	
	public void setCardLayout(CardLayout cardsIn)
	{
		cards = cardsIn;
	}
	
	public JPanel getCardPanel()
	{
		return cardHolder;
	}
	
	public void setCardPanel(JPanel cardHolderIn)
	{
		cardHolder = cardHolderIn;
	}
	
	public Information getInfo()
	{
		return this;
	}
}
