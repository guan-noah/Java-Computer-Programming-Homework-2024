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

/** this panel holds the main cards/panel **/ 
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
		
		String areaText = "Welcome to my program. This program is an " + 
			"attempt to try and put together what we have learned this " + 
			"year. It has examples of multiple layouts and components " + 
			"as well as using graphics to draw pictures.";
		JTextArea jta = new JTextArea(areaText);
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setEditable(false);
		JScrollPane scroll = new JScrollPane(jta);
		//scroll.add(jta);
		add(jta);
		add(scroll);
				
		tfName = new JTextField("Type your name");
		tfName.addActionListener(new TextFieldHandler());
		add(tfName);
		
		cardsIn.show(panelCardsIn, "Home");
	}
	
}
class TextFieldHandler implements ActionListener
{
	public void actionPerformed(ActionEvent evt)
	{
		///implementation
	}
} 

class FixedPanelHolder extends JPanel
{
	private Information info;
	private JButton homeButton;
	public FixedPanelHolder()
	{
		info = new Information();
		homeButton = new JButton("");
		//homeButton.addActionListener();
	}
	public FixedPanelHolder(Information infoIn)
	{
		info = infoIn;
		homeButton = new JButton("");
		//homeButton.addActionListener();
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

