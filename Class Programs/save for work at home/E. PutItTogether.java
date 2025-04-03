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
		PutItTogetherHolder pith = new PutItTogetherHolder(); 		
		frame.getContentPane().add( pith );		
		frame.setVisible(true);		
	}
}

/// this panel holds the main cards/panel 
class PutItTogetherHolder extends JPanel 
{	
	public PutItTogetherHolder()
	{
		setBackground(Color.CYAN);

		CardLayout cards = new CardLayout();							//has 6 different cards 
		setLayout(cards);
		
		Information info = new Information();
		FirstPagePanel fpp = new FirstPagePanel(this, cards, info);
		FixedPanelHolder hph = new FixedPanelHolder(info);
		
		add(fpp, "First");												
		/*card 1 = welcome (JLabel); terms and conditions (JTextArea & JScrollPane); enter name (JTextField); accept (JCheckBox)*/
		add(hph, "Home");												
		/*card 2 = welcome name (JLabel); programDescription (JTextArea); please select... (JLabel); TwoPeople/Colors/Masterpiece (JRadioButtons); Home (JButton)*/
		//~ add();						
		/*card 3 = */
		//~ add();						
		/*card 4 = */
		//~ add();						
		/*card 5 = */
		//~ add();						
		/*card 6 = */
	}
}

/// First page to show up.  Gives information, asks for name.  Goes to Home page.
class FirstPagePanel extends JPanel
{
	private PutItTogetherHolder panelCards;
	private CardLayout cards;
	private Information info;
	private JTextField tfName;
	
	public FirstPagePanel(PutItTogetherHolder panelCardsIn, CardLayout cardsIn, Information infoIn)
	{

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
	
	/** Since the label for the name was created when the classes constructor was called
	// it needs to be updated after the user types in the name into the text field.
	// Update that label in paintComponent. **/
	public void paintComponent(Graphics g)
	{
	}
	
	
}

class BothPictPanel extends JPanel implements MouseListener
{
	public BothPictPanel()
	{
		
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

class MyPictPanel extends JPanel implements ActionListener
{
	public MyPictPanel()
	{
		
	}
	public void actionPerformed(ActionEvent evt)
	{
		
	}
}


class FriendPictPanel extends JPanel implements ActionListener
{
	public FriendPictPanel()
	{
		
	}
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
