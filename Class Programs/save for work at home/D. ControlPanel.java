/**

ControlPanel.java
The goal of this program is to get practice with multiple components controlling 
some aspect of the program as well as work on layouts.  All field variables are given.  
You may not add any more.  You will need local variables for things like components, 
panels, etc.  Use the structure of the program given.  Do not change it.

1.  Make a JFrame that is 800 pixels wide by 600 pixels tall.  
To the frame, add a panel, called CpPanelHolder, which holds two panels 
named PictPanel and RightControlPanel.

2.  PictPanel will use a border layout and has a label called welcome, 
draws one of four images, and a text area called tAComponentInfo, which 
will be changed when the components in the right panel are invoked, or used.  
The images will be loaded using arrays and a for loop.

--> !! crucial for pseudocode 
3.  RightControlPanel will have a border layout.  Part of the original 
goal of this program was for you to determine the needed layout in order 
to get the final result, but since we are doing things differently, I will 
give you some more information.  There are a lot of different panels inside 
the RightControlPanel.  The label and the text field are in one panel in the 
north of the right panel.  The menu is in one panel.  The color label and 
radio buttons are in one panel, with a grid layout.  Both of these panels 
(menu and radio buttons) are in another panel with a grid layout.  The slider 
needs to expand, so it is not in its own panel, but is added to the grid layout.

4.  When the user types in a name into the text field, the welcome label 
in PictPanel changes to say: 
Welcome “name typed in” 
and the text area displays:  
“name typed in” was entered in the text field

5.  When the picture menu is clicked, a picture is selected.  That picture 
is then drawn on the PictPanel.  Also, the message: 
The picture “picture name” was selected. 
is added to the text area.  Note any previous text remains there.  Recall, 
that is called appending.  The method is:  textArea.append(string…);

6.  When a radio button color is selected, the color of the welcome label 
is changed to that color.  The method used to change the text color of a 
component is componentName.setForeground(Color.CYAN);   RGB colors work 
just as well.  Also the text area is changed to:  The color of the label 
was changed to “color picked”, for example Red.  No other text will be there.

7.  The slider will change the size—both width and height—of the picture 
by adding the value of the slider to each width and height.  Also, the 
text area will change to display:  
The size of the picture was changed by “the value of the slider”, for example 100.  
Make the maximum width to be 20 pixels less than the width of the PictPanel 
and the maximum height to be 250 pixels less than the height of the PictPanel.  
Recall, to get the width of the panel, you use panel.getWidth(), etc.   
The original pictures are too big to show up on the panel.  Make the width 
and height ¼ of the original size.

Depending if there is time, I would like to do the following.
Save the program as ControlPanel2.java.
1.  Take out all of the changes to the text area.  
2.  Open file QuotesTextFile.txt.  Read the contents of the file and 
	store into a String array.
2.  Add a button in the below the slider that says, "Press to randomly select a quote".
  When pressed, randomly select one quote from the array and print it, 
  then a blank line, then a – followed by the person’s name that said it, 
  to the text area.

**/
// ControlPanel.java
// This program gives an example of using several components in order to change the
// different components and/or images.

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;	
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel
{
	public static void main(String[] args) 
	{
		ControlPanel ce = new ControlPanel();
		ce.run();
	}
	
	/** standard frame, added JPanel CpPanelHolder **/
	public void run() 
	{
		JFrame frame = new JFrame ("Control Panel for Picture");
		frame.setSize(800, 600);
		frame.setLocation(10, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CpPanelHolder cph = new CpPanelHolder();
		frame.getContentPane().add(cph);
		frame.setVisible(true);
	}
}

class CpPanelHolder extends JPanel
{
	private int selected;  												// the index for the picture selected to draw
	private JTextArea tAComponentInfo;									// text area in the PictPanel, but changed in RightControlPanel2
	private JLabel welcome;												// label in the PictPanel, but changed in RightControlPanel2
	private Font font;  												// most fonts are the same, so there is one
	private PictPanel pp; 												// the variables in the RightControlPanel2 need access to use repaint
	private int val; 													// value of the slider to change the picture size
	private int width;
	private int height;
	private int [] widthOfImages;  										// stores the width of each image
	private int [] heightOfImages;  									// stores the height of each image
	
	public CpPanelHolder()
	{
		setLayout(new BorderLayout());
		selected = 0;													//initialize all fvs to initial values 
		//tAComponentInfo = 
		//welcome = 
		font = new Font("Arial", Font.BOLD, 10);
		pp = new PictPanel();
		val = 0;
		width = 0;
		height = 0;
		widthOfImages = new int[0];
		heightOfImages = new int[0];
		
		add(new RightControlPanel(), BorderLayout.EAST);
		add(new PictPanel(), BorderLayout.CENTER);
	}
	
	
	/** PictPanel, which has a border layout,  has a label and a text area, both declared above.
	*	most of the code for loading the images is given.  add the rest for the images
	*	plus add the code for the text area, label and font (not necessarily in that order).
	*	the fonts, unless otherwise stated are size 20, bold and Serif.  
	**/
	class PictPanel extends JPanel
	{
		private String[] names;											// the names of the pictures
		private Image[] images;											// array of images to be drawn
		
		public PictPanel()
		{
			setLayout(new BorderLayout());
			setBackground(Color.CYAN);
			
			names = new String[] {"mountains.jpg", "shanghai.jpg", "trees.jpg", "water.jpg"};
			images = new Image[names.length];
			widthOfImages = new int[names.length];						// create the array for the heights
			
			for (int i = 0; i < names.length; i++)						// load all of the pictures
			{	
				/** since all of the images are in a directory called pictures, each file
				// name needs the following before the file name:   "pictures/" + 
				// e.g. it could be:  pictures/mountains.jpg **/
				images[i] = getMyImage(names[i]);						// finish this line  
				
				widthOfImages[i] = images[i].getWidth(this);			// find the heights of each picture
			}
			
		
		}
		
		/** this has been started for you **/ //finished
		public Image getMyImage(String pictName) 
		{
			Image picture = null;
			
			try
			{
				picture = ImageIO.read(new File(pictName));
			}
			catch(IOException e)
			{
				System.err.println("\n\n" + pictName + " can't be found.\n\n");
				e.printStackTrace();
			}
			
			return picture;
		}
		
		/** draw the image on a blank screen with the top left corner at (20,20) **/
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(images[selected], 20, 20, this);				///draws the selected image 
		}
	}	
		
	/* Make all panels on the right be cyan.
	* RightControlPanel has a border layout.
	* On this panel are:  label, which font size already done, the text field, the menu,
	* the radio buttons and the slider.
	* You will have to determine the layouts in order to make them show up like the sample
	* run provided.
	*/
	class RightControlPanel extends JPanel
	{
		private JTextField tfName; 										// text field for user to type in their name
		private ButtonGroup bg;											// to select the color so only one is selected
		private JRadioButton color1, color2, color3;					// color choices
		private JSlider sSize;											// slider for changing the size of the picture
		
		public RightControlPanel()
		{
			setLayout(new BorderLayout());
			add(new TitlePanel(new GridLayout(), BorderLayout.NORTH));
			add(makePictureMenuBar(), BorderLayout.WEST);				//add jmenubar to west 
			add(new SelectLabelPanel(new GridLayout()), BorderLayout.EAST);
		}
		
		/** There are a some more classes that you will need here to add to RightControlPanel
		// You will need to figure them out based on the directions/prompt and the 
		// sample run in the prompt.  You can figure them out based on your drawing of the
		// layout, i.e. your pseudocode for this. **/
		
		public JMenuBar makePictureMenuBar()
		{
			JMenuBar returnjmb = new JMenuBar();
			JMenu jm = new JMenu("Picture");
			
			JMenuItem mountains = new JMenuItem("mountains");
			JMenuItem water = new JMenuItem("water");
			JMenuItem trees = new JMenuItem("trees");
			JMenuItem shanghai = new JMenuItem("shanghai");
			
			jm.add(mountains);
			jm.add(water);
			jm.add(trees);
			jm.add(shanghai);
			
			returnjmb.add(jm);
			return returnjmb;
		}
		
		
		/** Write the Listener/Handler class for the menu **/
		//jmi.addActionListener(new MenuHandler)
		class MenuHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				e.getActionCommand();		//gets the JMenuItem name clicked on
			}
		}
		
		
		/** write the Listener/Handler class for the text field **/
		class TextFieldHandler implements ActionListener
		{//tf.addActionListener(textFieldHandler)
			public void actionPerformed(ActionEvent e)
			{
				String userText = e.getActionCommand();//getText()
			}
		}
		
		
		/** write the Listener/Handler class for the slider **/
		class SliderHandler implements ChangeListener
		{//jsl.addSliderListener(new SliderHandler())
			public void stateChanged(ChangeEvent e)
			{
				/*
				int userValue = e.getValue();
				slider1.getValue
				*/
			}
		}
		
		
		/** write Listener/Handler class for the JRadioButtons **/
		class RadioButtonHandler implements ActionListener
		{//rb.add(new RadioButtonHandler())
			public void actionPerformed(ActionEvent e)
			{
				String buttonSelected = e.getActionCommand();
				
			}
		}
		
		class TitlePanel extends JPanel
		{
			public TitlePanel()
			{
				setLayout(new GridLayout());
				Font titleFont = new Font("Arial", Font.BOLD, 16);
				add(new JLabel("Control Panel"));
				add(new JTextField(""));
			}
		}
		
		class SelectLabelPanel extends JPanel
		{
			public SelectLabelPanel()
			{
				setLayout(new GridLayout());
				add(new JLabel("Select color of label");
				add(new JRadioButton());
			}
		}
		
	}
}	// end class ControlPanel
