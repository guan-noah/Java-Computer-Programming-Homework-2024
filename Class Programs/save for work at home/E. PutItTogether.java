// Noah Guan
// Started 04/07/2025, Restarted 4/10/2025, V1 finished
/* Work Timeline: 
4/10/2025
	2:00 to 6:00 P.M.
	10:00 to 1:00 A.M.
4/11/2025 
	8:00 to 8:20 A.M. 
	2:30 to 6:00 A.M.
	10:40 to 11:00 P.M.
Colors: 
	dawn colors: pink (0), light grayish blue (1), grayish blue (2), dark blue (3), dark purple (4)
	dusk colors: bluish gray (0), peach-pink (1), purple (2), faded purple (3)
	note: in V1, some colors (i think later ones) don't work for some reason. If wanted, check back on code. 
*/
// PutItTogether.java  
// Period 6 Java w/ Mr. Yu

/// imports
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.io.File;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

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
		frame.setLocation(00,00);
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
		JTextArea jta = new JTextArea(areaText, 8, 20);
		jta.setFont(new Font("Arial", Font.PLAIN, 23));	
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setEditable(false);
		JScrollPane scroll = new JScrollPane(jta);
		scroll.setVisible(true);
		add(scroll);
				
		tfName = new JTextField("Type your name");						//create textfield
		tfName.addActionListener(new AListen());
		add(tfName);
		
		JCheckBox iUnderstand = new JCheckBox("I Understand the " + 
			"directions. Take me to the next page");					//create jcheckbox
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
class FixedPanelHolder extends JPanel									///CRUCIAL: MUST CHANGE TO NULLLAYOUT. PLEASE COME BACK AND REVISE THIS WHEN DONE. 
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
		BothPictPanel bothpictpan = new BothPictPanel(this, cards);
		add(bothpictpan, "BothPicts");
		MyPictPanel mypictpan = new MyPictPanel(this, cards);
		add(mypictpan, "MyPict");
		FriendPictPanel friendpictpan = new FriendPictPanel(this, cards);
		add(friendpictpan, "FriendPict");
		DrawPanel drawpan = new DrawPanel();
		add(drawpan, "DrawPan");
		//~ MasterPiecePan masterpiece = new MasterPiecePan();
		//~ add(masterpiece, "Masterpiece");
		
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
	private CardLayout cards;
	private Information info;
	private JLabel username;
	private ButtonGroup homegroup;
	public HomePanel(HomePanelHolder hphIn, CardLayout cardsIn, Information infoIn)
	{
		hph = hphIn;
		cards = cardsIn;
		info = infoIn;
		username = new JLabel("Welcome " + info.getName());				//currently says null at the time of construction 
		
		setBackground(Color.YELLOW);
		addComponents();
	}
	// Since the label for the name was created when the classes constructor was called
	// it needs to be updated after the user types in the name into the text field.
	// Update that label in paintComponent.
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//~ setBackground(Color.YELLOW);
		username.setText("Welcome " + info.getName());					///NOTE!! I screwed up a ton. If you create a JLabel, you should just use the same one instead of creating a new one. The new one doesn't even change. 
	}
	
	public void addComponents()
 	{
 		Font largeBold = new Font("Arial", Font.BOLD, 40);
 		Font smallBold = new Font("Times New Roman", Font.BOLD, 20);
 		setBackground(Color.YELLOW);
 		setLayout(new BorderLayout(0, 100));							//hgap, vgap so the 0 shouldn't really matter 
 		
 		username.setFont(largeBold);
 		//~ username.setPreferredSize(new Dimension(800, 80));				//800x800 panel ...note for future: this made it go left aligned. better to use the FlowLayout left. 
 		JPanel centerLabel = new JPanel();
 		centerLabel.setBackground(Color.YELLOW);
 		centerLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
 		centerLabel.add(username);
 		add(centerLabel, BorderLayout.NORTH);
  		
																		//build JTextArea and scrollbar
		String areaText = "Welcome to my program. This program is an " + 
			"attempt to try and put together what we have learned this " + 
			"year. It has examples of multiple layouts and components " + 
			"as well as using graphics to draw pictures.\n\n\n\n\n\n\n";//if the text isn't long enough, it won't show the jscrollbar.
		JTextArea jta = new JTextArea(areaText, 8, 20);
		jta.setFont(new Font("Arial", Font.PLAIN, 23));	
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setEditable(false);
		JScrollPane scroll = new JScrollPane(jta);
		scroll.setVisible(true);
  		jta.setBackground(Color.YELLOW);
  		
  		JLabel selectOne = new JLabel("Select one of the two options");	//build JLabel
  		selectOne.setBackground(Color.YELLOW);
  		selectOne.setFont(smallBold);
  		
  		JPanel centerPan = new JPanel();								///builds center textarea formatting 
  		centerPan.setBackground(Color.YELLOW);
  		centerPan.setLayout(new BorderLayout(15, 50));					//hgap, vgap
  		centerPan.add(scroll, BorderLayout.NORTH);
  		centerPan.add(selectOne, BorderLayout.CENTER);
  		
  		homegroup = new ButtonGroup();
  		RadioButtonHandler rbhandler = new RadioButtonHandler();
  		JRadioButton b1 = new JRadioButton("To see information about a friend and me.");//according to prompt; "A picture of two people" in pictures
  		homegroup.add(b1);
  		b1.addActionListener(rbhandler);
  		
  		JRadioButton b2 = new JRadioButton("To make some colors and draw some shapes.");//according to prompt; "Draw Panel" in picture
  		homegroup.add(b2);
  		b2.addActionListener(rbhandler);
  		
  		//~ JRadioButton b3 = new JRadioButton("Masterpiece");
  		//~ homegroup.add(b3);
  		//~ b3.addActionListener(rbhandler);
  		
  		JPanel radioButtonPan = new JPanel();							///builds button formatting JPanel (bottom)
  		radioButtonPan.setPreferredSize(new Dimension(10000, 150));		//has to setPreferredSize 
  		radioButtonPan.setBackground(Color.YELLOW);
  		radioButtonPan.setLayout(new GridLayout(2, 1, 0, 20));			//rows, columns, hgap, vgap; change to 3 1 0 20 when you add Masterpiece panel
  		radioButtonPan.add(b1);											//add all buttons to radioButtonPan
  		radioButtonPan.add(b2);
  		//~ radioButtonPan.add(b3);
  		
  		add(centerPan, BorderLayout.CENTER);
  		add(radioButtonPan, BorderLayout.SOUTH);						//add radioButtonPan to actual panel
 	}
 	class RadioButtonHandler implements ActionListener
 	{
 		public void actionPerformed(ActionEvent evt)
 		{
			homegroup.clearSelection();
 			String buttonname = evt.getActionCommand();
 			if(buttonname.equalsIgnoreCase("To see information about a friend and me."))
 				cards.show(hph, "BothPicts");
 			else if(buttonname.equalsIgnoreCase("To make some colors and draw some shapes."))
 				cards.show(hph, "DrawPan");
 			//~ else if(buttonname.equalsIgnoreCase("Masterpiece"))
 				//~ cards.show(cardsPan, "");
 		}
 	}
}

class BothPictPanel extends JPanel implements MouseListener
{
	protected HomePanelHolder hph;
	protected CardLayout cards;
	
	protected Color[] dawn, dusk;										//color scheming times!! 
	
	protected JLabel username;
	
	protected String imgName;
	protected Image image;
	final int FULL_X = 670, FULL_Y = 447, SPLIT_VAL_L = 340, SPLIT_VAL_R = FULL_X-SPLIT_VAL_L;
		//basic image dimensions //note: 447/670 = .667164179!! really close to 2/3 so cropping tool use https://www.resizepixel.com/
	protected int xpos, ypos, xend, yend;								//synthesized image dimensions 
	protected int xsplitL, xsplitR;										//for separating pics; x left and right splits respectively 
	protected int destX1, destY1, destX2, destY2, origX1, origY1, origX2, origY2;//for inheritance changes 
	
	
	protected Font font;												//string dimensions 
	protected String stringDrawn;
	protected Color stringColor;
	protected int xString, yString;
	
	public BothPictPanel(HomePanelHolder hphIn, CardLayout cardsIn)
	{
		hph = hphIn;
		cards = cardsIn;
		setLayout(null);
		setBackground(Color.BLUE);
		
		imgName = "ni-ki_jungwon.jpeg";
		//~ imgName = "ni-ki_jungwon.jpg";									//TAKE THIS OUT WHEN RETURN BACK HOME
		image = null;
		setImage();
		
		addMouseListener(this);
	}
	public Color[] setColorSet(int[] colorSetIn)
	{
		if(colorSetIn.length%3 != 0)
			System.out.println("Error: Incorrect number of RGB modifiers");
		
		Color[] outputColors = new Color[colorSetIn.length/3];
		for(int i = 0; i < outputColors.length; i++)					//go through outputColors; another way to do it: go through 3 at a time 
			outputColors[i/3] = new Color(colorSetIn[i], colorSetIn[++i], colorSetIn[++i]);//intialize outputColors while incrementing index 
		return outputColors;
	}
	public void setImage()												//sets all the variables needed to create the image
	{
		//DAWN COLORS: 210, 125, 140 (pink); 155, 165, 190 (light grayish blue); 155, 155, 180 (grayish blue); 61, 63, 95 (dark blue); 110, 65, 115 (dark purple) 
			//ordered from red & light to blue & dark 
		dawn = new Color[5];
		int[] dawnVals = new int[] {210, 125, 140, 155, 165, 190, 155, 155, 180, 61, 63, 95, 110, 65, 115};
		dawn = setColorSet(dawnVals);

		//DUSK COLORS: 135, 150, 175 (bluish gray), 250, 215, 225 (peach-pink); 200, 170, 220 (purple); 185, 175, 220 (faded purple)
			//ordered from red & dark to blue & light 
		dusk = new Color[4];
		int[] duskVals = new int[] {135, 150, 175, 250, 215, 225, 200, 170, 220, 185, 175, 220};
		dusk = setColorSet(duskVals);
		
		xpos = 25;														//initialize synthesized image dimensions (adjusted lengths)
		ypos = 100;														//xpos and ypos may be changed at discretion 
		xend = xpos + FULL_X;
		yend = ypos + FULL_Y;
		
		xsplitL = xpos + SPLIT_VAL_L;									//initialize pic separation dimensions (also adjusted lengths)
		xsplitR = xpos + SPLIT_VAL_R;
		
																		//inheritance changes: can change to whatever class initializes it to 
		destX1 = xpos;													//by definition; never changes 
		destY1 = ypos;													//same logic 
		destX2 = xend;													//will change later
		destY2 = yend;													//never changes 
		origX1 = 0;														//will change
		origY1 = 0;														//never changes
		origX2 = FULL_X;												//will change 
		origY2 = FULL_Y;												//never changes
				
		font = new Font("Roboto", Font.BOLD, 30);						//Label font 
		stringDrawn = "Click on Ni-Ki or Jungwon to see their pages";	//String information 
		stringColor = Color.WHITE;
		xString = 20;
		yString = 50;
		
		createImg();
		
		//~ repaint();
	}
	public void createImg()
	{
		File imageFile = new File(imgName);
		try
		{
			image = ImageIO.read(imageFile);
		}
		catch(IOException e)
		{
			System.err.println("\n\n\n" + imgName + " can't be found.\n\n");
			e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);										//if I don't put super.paintComponent, the background doesn't set. 
		g.setColor(stringColor);
		g.setFont(font);
		g.drawString(stringDrawn, xString, yString);//give it 10 px headspace 
		
		g.drawImage(image, destX1, destY1, destX2, destY2, origX1, origY1, origX2, origY2, this);
		//convert from img, xpos, ypos, origsizeX, origsizeY
		//img, [canvas: (top left), (bottom right)], [image file: (top left), (bottom right)], ImgObserver
		//old: 
			//~ g.drawImage(image, xpos, ypos, end, yend, origStart, 0, end, yend, this);
			//~ //img, destx1, desty1 (top left), destx2, desty2 (bottom right), origx1, origy1, origx2, origy2, ImgObserver		
	}
	public void mouseClicked(MouseEvent evt)							//nikiX = 340; jungwonX = sizeX-nikiX; heights stay 447
 	{
		//~ System.out.print("mouse clicked: y pos ");
		int nikiX = 340;												//how far over niki is
		//~ int jungwonX = sizeX-nikiX;
		int x = evt.getX();
		int y = evt.getY();
		if(ypos <= y && y <= ypos + yend)
		{
			//~ System.out.println("correct; ");
			if(xpos <= x && x <= nikiX)
				cards.show(hph, "MyPict");								//call MyPictPan
			else if(nikiX <= x && x <= xend)
				cards.show(hph, "FriendPict");							//call FriendPictPan
		}
		//~ else
			//~ System.out.println("incorrect; ");							//only printed out for y pos; could have done x pos deeper inside the if statements if wanted  
 	}
 	public void mousePressed(MouseEvent evt) {}
 	public void mouseReleased(MouseEvent evt) {}
 	public void mouseEntered(MouseEvent evt) {}
 	public void mouseExited(MouseEvent evt) {}
}

/** person 1: ni-ki **/
class MyPictPanel extends BothPictPanel implements ActionListener		//my lazy way of not copying over every method except the mouseListener parts; that minor inefficiency is fine
{
	protected JButton other;											//unique to MyPictPan and FriendPictPan
	
	protected String name, alias, birthday, bloodType, zodiac, nationality, occupation, aboutPerson;
	protected JTextArea personTA;
	protected JScrollPane scroller;
	protected JPanel personPanel;
	public MyPictPanel(HomePanelHolder hphIn, CardLayout cardsIn)
	{
		super(hphIn, cardsIn);											//now we should have all the fvs and methods of the super class 
		finishConstructing();											//this allows for class-specific "constructor" editing 
	}
	public void finishConstructing()
	{
		setBackground(Color.BLUE/*dusk[3]*/);											//background color blue //(faded purple)
		stringColor = dusk[1];											//initialize string info (color = peach-pink)
		stringDrawn = "This is the MyPictPanel";
		
																		//niki changes: destX2 (xend --> xsplitL), origX2 (xend --> SPLIT_VAL_L)
		destX2 = xsplitL;												//will change 
		origX2 = SPLIT_VAL_L;											//will change 
		
		//old formula:  xpos, ypos, end, yend, origStart, 0, end, yend; end cannot be twice
		
																		///unique to MyPictPan and FriendPictPan from here
		setLayout(new BorderLayout());									//will this override the previous layout? yes. the picture draws as background.
		other = new JButton("See info for the other person");			//build switch panel JButton
		other.addActionListener(this);
  		
		//Nishimura Riki (Japanese: 西村力 (にしむら りき), Hangul: 니시무라 리키) -- more well known by his stage name NI-KI (Hangul: 니키, Japanese: ニキ)
																		//build JTextArea and scrollbar
		name = "Nishimura Riki (Japanese: 西村力 (にしむら りき), Hangul: 니시무라 리키)";
		alias = "NI-KI (Hangul: 니키, Japanese: ニキ)";
		birthday = "2005 December 9";
		bloodType = "B";
		zodiac = "Sagittarius (Western)🐆 , Rooster (Chinese)🐥";
		nationality = "Japanese";
		occupation = "Member of ENHYPEN, K-pop boy group (Main Dancer, Vocalist, Rapper, Maknae)";
		buildPersonTA();
		
		personTA = new JTextArea("", 8, 20);
		personTA.setFont(new Font("Dialog", Font.PLAIN, 20));	
		personTA.setLineWrap(true);
		personTA.setWrapStyleWord(true);
		personTA.setEditable(false);
		scroller = new JScrollPane(personTA);
		scroller.setPreferredSize(new Dimension(300, 400));
		scroller.setVisible(true);
  		personTA.setBackground(dusk[0]);								//text area background = bluish gray 
  		setPersonText();
  		
  		personPanel = new JPanel(new FlowLayout());						//to format; to make sure that it doesn't take up half of the card space 
  		//~ personPanel.setPreferredSize(new Dimension(300, 400));		//no need as the scroller preferred size already makes it fill up 300 px wide
  		personPanel.setBackground(dusk[2]);								//text area panel background = purple 
  		personPanel.add(scroller);
  		
  		add(personPanel, BorderLayout.EAST);
		add(other, BorderLayout.SOUTH);
	}
	public void buildPersonTA()
	{
		aboutPerson = "This is:\n " + name + "\n\nAlso known as:\n " + alias + 
			"\n\nBirthday:\n " + birthday + "\n\nBlood Type:\n " + bloodType + 
			"\n\nZodiac:\n " + zodiac + "\n\nNationality:\n " + nationality + 
			"\n\nOccupation:\n " + occupation + "\n\n\n\n";				//if the text isn't long enough, it won't show the jscrollbar.
	}
	public void setPersonText()
	{
		personTA.setText(aboutPerson);
	}
	public void paintComponent(Graphics g)								///make BothPictPanel polymorphic 
	{
		
		super.paintComponent(g);
	}
	public void actionPerformed(ActionEvent evt)
 	{
		cards.show(hph, "FriendPict");
 	}
 	public void mouseClicked(MouseEvent evt) {} 						//simple overwrite; maybe later, as a challenge, implement the same sectioning; edit BothPictPanel to be polymorphic
}

/** person 2: jungwon **/
class FriendPictPanel extends MyPictPanel implements ActionListener	//again, got lazy :P
{																		///toying with the idea of having FriendPictPanel extend MyPictPanel
	public FriendPictPanel(HomePanelHolder hphIn, CardLayout cardsIn)
	{
		super(hphIn, cardsIn);											//same logic applies! :)
		//~ super.finishConstructing();									//if it is put here instead, the picture will not draw and all info will look like NI-KI's. This is because Jungwon's finishConstructing is never called. 
	}
	public void finishConstructing()
	{
		super.finishConstructing();
		stringDrawn = "This is the FriendPictPanel";					//initialize string info
		
		origX1 = SPLIT_VAL_L;											//jungwon changes
		origX2 = FULL_X;
		destX2 = xsplitR;
		
																		//build JTextArea and scrollbar
		//Yang Jung-won (양정원)
		name = "Yang Jung-won (Hangul: 양정원)";
		alias = "Jungwon (Hangul: 정원)";
		birthday = "2004 February 9";
		bloodType = "AB";
		zodiac = "Aquarius (Western)🐈";
		nationality = "Korean";
		occupation = "Leader of ENHYPEN, K-pop boy group (Dancer, Vocalist)";
		buildPersonTA();
		setPersonText();
		personTA.setBackground(dawn[0]);
	}
	public void paintComponent(Graphics g)
	{
		//~ System.out.println("Repaint FriendPictPan");
		super.paintComponent(g);
	}
	public void actionPerformed(ActionEvent evt)
 	{
		cards.show(hph, "MyPict");
 	}
	public void mouseClicked(MouseEvent evt) {} 						//simple overwrite; challenge for this one too
}

class DrawPanel extends JPanel
{
	private RightPanel rp;
	private int amtRed, amtGreen, amtBlue;
	private int size;
	private JSlider[] sliders;
	
	public DrawPanel()
	{
		rp = new RightPanel();
		LeftPanel lp = new LeftPanel();
		lp.setPreferredSize(new Dimension(450, 500));					//x, y
		BottomPanel bp = new BottomPanel();
		bp.setPreferredSize(new Dimension(800, 100));
		
		setLayout(new BorderLayout());
		add(lp, BorderLayout.WEST);
		add(rp, BorderLayout.EAST);
		add(bp, BorderLayout.SOUTH);									//block at bottom (doesn't work :sob:)
	}
	
	public class LeftPanel extends JPanel
	{
		public LeftPanel()
		{
			setLayout(new GridLayout(4, 2, 0, 50));
			Font labelFont = new Font("Arial", Font.BOLD, 15);
			
			String[] colors = new String[] {"red", "green", "blue", ""};//variables initialized in for loop or helping with initialization
			JLabel[] changeLabels = new JLabel[4];
			sliders = new JSlider[4];									//first 3 for color, last slider for size 
			
			for(int i = 0; i < colors.length; i++)						//for loop that iterates through all 3 color panels 
			{
				changeLabels[i] = new JLabel("Amount " + colors[i]);	//amount <color> label 
				changeLabels[i].setFont(labelFont);
				
				sliders[i] = makeSlider();								//make the slider (general)
				if(i == 0)
					sliders[i].addChangeListener(new RedSliderListener());//slider0: also red; adds a different color/size listener for each slider
				else if(i == 1)
					sliders[i].addChangeListener(new GreenSliderListener());
				else if(i == 2)
					sliders[i].addChangeListener(new BlueSliderListener());
				else if(i == 3)
					sliders[i].addChangeListener(new SizeSliderListener());
				
				add(changeLabels[i]);
				add(sliders[i]);
			}
			changeLabels[3].setText("Change size");
			sliders[3].setMinimum(50);
			sliders[3].setValue(100);
			
		}
		public JSlider makeSlider()
		{
			JSlider slider = new JSlider(0, 250, 0);					//from 0 to 250 default location 0
			slider.setMajorTickSpacing(25);								// create tick marks on slider every 5 units
			slider.setPaintTicks(true);
			slider.setLabelTable(slider.createStandardLabels(25) ); 	// create labels on tick marks
			slider.setPaintLabels(true);
			slider.setOrientation(JSlider.HORIZONTAL);
			return slider;
		}
		class RedSliderListener implements ChangeListener
		{
			public void stateChanged(ChangeEvent evt)
			{
				int val = sliders[0].getValue();						//red slider
				amtRed = val;											//change fv color value to slider value 
				/* //
				String sliderName = slider.getString();
				if(sliderName.equalsIgnoreCase("Red Slider"))
					amtRed = val;
				else if(sliderName.equalsIgnoreCase("Blue Slider"))
					amtBlue = val;
				else if(sliderName.equalsIgnoreCase("Green Slider"))
					amtGreen = val;
				*/
				rp.repaint();											//NOTE!! It won't repaint itself. You will have to repaint it. 
			}
		}
		class GreenSliderListener implements ChangeListener
		{
			public void stateChanged(ChangeEvent evt)
			{
				int val = sliders[1].getValue();
				amtGreen = val;
				rp.repaint();
			}
		}
		class BlueSliderListener implements ChangeListener
		{
			public void stateChanged(ChangeEvent evt)
			{
				int val = sliders[2].getValue();
				amtBlue = val;
				rp.repaint();
			}
		}
		class SizeSliderListener implements ChangeListener
		{
			public void stateChanged(ChangeEvent evt)
			{
				int val = sliders[3].getValue();
				size = val;
				rp.repaint();
			}
		}
	}
	
	public class RightPanel extends JPanel
	{
		public RightPanel()
		{
			setLayout(new BorderLayout());
			setBackground(Color.WHITE);
			
			JLabel descPan = new JLabel();
			descPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));//only one in line so hgap doesn't matter
			descPan.setPreferredSize(new Dimension(300, 200));
			
			JLabel description = new JLabel("This is the drawing Panel");
			description.setFont(new Font("Arial", Font.BOLD, 15));
			
			descPan.add(description);
			add(descPan, BorderLayout.NORTH);
			
			size = 100;
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(new Color(amtRed, amtGreen, amtBlue));
			g.fillRect(0, 200, size, size);
		}
	}
	class BottomPanel extends JPanel
	{
		
	}
}

class Information														//basically only used in PutItTogetherHolder, FirstPagePanel, HomePanelHolder, HomePanel
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
