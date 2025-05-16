/*
 * Noah Guan
 * Period 6
 * SelectUserInfoPanel.java
 * 
 * This class 


 */

import javax.swing.JPanel;
import javax.swing.JLabel;									//components
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;							//for labels/instructions
import java.awt.Dimension;								//preferred size
import java.awt.BorderLayout;							//layout imports
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import java.awt.event.ActionListener;				//for the buttons 
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentListener;			//for the JScrollBar
import java.awt.event.AdjustmentEvent;
import javax.swing.event.ChangeListener;			//for the JSliders
import javax.swing.event.ChangeEvent;

import java.util.ArrayList;
import java.util.Scanner;
/* class that asks for user information, including but not limited to: 
name 
favorite color (steal rgb sliders from PutItTogether
email
school id 
address 
social security number 
*/
public class SelectUserInfoPanel extends JPanel
{
	//options; we want to gather choice from user 
	private ArrayList<Character> userCharacters;
	private Color[] colors;
	private ArrayList<Integer> numbers;
	//~ ArrayList<JButtonGroup>
	
	//actual data received from user 
	private Color userColor;
	private String first, last;
	private Character userCharacter;
	
	public SelectUserInfoPanel()
	{
		userCharacters = new ArrayList<>();
		//~ userCharacter = getCharacters();//after GameProgression is done, 
			//we will get user character list, add it here, and create method
		numbers = new ArrayList<>();
		
		//if your favorite color is not in here, potentially add a color picker 
		colors = new Color[] {Color.RED, Color.ORANGE, Color.YELLOW, 
			Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK, Color.CYAN, 
			Color.WHITE, Color.GRAY, Color.DARK_GRAY, Color.BLACK/*, null*/};
			//12 colors. if we add color picker, uncomment null and implement method. 
		
		setLayout(new BorderLayout());
		
		JPanel selectionAdd = getSelection();
		add(selectionAdd, BorderLayout.CENTER);
		
		JPanel bottomButtons = getBottomButtons();
		add(bottomButtons, BorderLayout.SOUTH);
	}
	//second half
	/* returns the back and continue buttons */
	public JPanel getBottomButtons()
	{
		JPanel bottomButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 200));
		bottomButtons.setPreferredSize(new Dimension(1200, 100));
		bottomButtons.setBackground(Color.RED);
		
		bottomButtons.add(new Label("Show this", 30, Color.BLACK));
		bottomButtons.add(addLinkedButton("Back", "main menu", Color.RED));
		bottomButtons.add(addLinkedButton("Continue", "intermission", Color.YELLOW));
		//~ bottomButtons.add(new JLabel("Hi"));
		
		return bottomButtons;
	}
	
	/* helper method, like toDiffScreen button in GamePanel*/
	public Button addLinkedButton(String name, String toPanel, Color background)
	{
		Button toDiffScreen = new Button(name, new SwitchPanels(toPanel), 200);//any button
		toDiffScreen.setPreferredSize(new Dimension(200, 266));
		//~ toDiffScreen.setOpaque(true);					//prep for background color
		toDiffScreen.setBackground(background);
		return toDiffScreen;
	}
	
	/* gets the selection part -- the whole panel except for bottom buttons 
	 */
	public JPanel getSelection()
	{
		JPanel selection = new JPanel(new BorderLayout());
		selection.setBackground(Color.BLACK);
		//create the border
		selection.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		
		Label prompt = new Label("Please enter your information:", 75);
		prompt.setForeground(Color.MAGENTA);
		selection.add(prompt, BorderLayout.NORTH); //create the prompt 
		JPanel centerSelect = getGrid();//create center selection (grid)
		selection.add(centerSelect, BorderLayout.CENTER);
		
		return selection;
	}
	
	/* gets the grid of prompts and types -- the center panel 
	 */
	public JPanel getGrid()
	{
		//the prompts for the data
		String[] prompts = new String[] {"First Name", "Last Name", "Character", 
			"Favorite Color"/*, "Email", "School ID", "Country", "Address", 
			"Birthday", "Social Security Number", "Credit Card Number", 
			"Shoe Size", "Face ID"*/};//the rest are jokes. 
		//the component for the type of answer response we want (for each prompt) 
		String[] types = new String[] {"Field", "Field", "Radio|userCharacters", 
			"Radio|colors"/*, "Field", "Field", "Field", "Field", "Radio|numbers", 
			"Field", "Field", "Field", "Field"*/};
		
		int rows = prompts.length;
		JPanel grid = new JPanel(new GridLayout(rows, 2, 10, 10));//the jpanel containing grid 
		grid.setPreferredSize(new Dimension(1150, 600));
		grid.setBackground(Color.CYAN);
		
		for(int index = 0; index < rows; index++)
		{
			String currentPrompt = prompts[index];//the prompt as a label
			
			//create the label that corresponds to the data enter component 
			JPanel prompt = new JPanel();
			Label description = new Label(currentPrompt, 20);
			prompt.add(description);
			
			//create the component where user enters data (here starts the method complexity!)
			JPanel enterData = getEnterData(types[index], currentPrompt);
			
			//add components to grid
			grid.add(prompt);
			grid.add(enterData);
		}
		return grid;
	}
	/* helper 1 to getGrid 
	 * returns the panel with component where user enters data 
	 */
	public JPanel getEnterData(String type, String prompt)
	{
		JPanel enterData = new JPanel();
		int isRadioButton = type.indexOf('|'); //if it is, it will be an int 0 or greater
		if(isRadioButton < 0)
		{
			TextField enterText = new TextField(prompt, prompt.length() + 5, 20);
			enterText.addActionListener(new FieldHandler(enterText, prompt));
			enterData.add(enterText);
		}
		else
		{
			//gets whatever is after the | as the option name
			JPanel choice = getRadioButtons(type, getOptionNames(GameData.dataAfter(type, "|")));
			enterData.add(choice);
		}
		return enterData;
	}
	/* helper1a to helper 1 (getEnterData)
	 * takes in optionType (e.g. colors/characters), jradiobutton options 
	 * 		(e.g. red, blue, etc.)
	 * returns the radio buttons jpanel, takes in the option names 
	 * - draws a square color in a panel. 
	 * OR
	 * - draws a character in a panel. 
	 * adds a label under the panel 
	 */
	public JPanel getRadioButtons(String optionType, String[] options)
	{
		ButtonGroup group = new ButtonGroup();
		//gives the handler the button group to clear selection 
		Button clear = new Button("Clear selection", new ClearGroupHandler(group));
		
		//setting up radiobuttons array 
		int numOfButtons = options.length;
		JRadioButton[] radioButtons = new JRadioButton[numOfButtons];
		
		//panel which buttons will be added to; gridlayout of 3 columns and 5 px spacing
		JPanel radioPan = new JPanel(new GridLayout(3, numOfButtons/3+1, 5, 5));
		
		//for loop to initialize all buttons and add them to panel 
		for(int i = 0; i < numOfButtons; i++)
		{
			//this jpanel contains the drawing panel and the radio button 
			JPanel tempToAdd = new JPanel(new BorderLayout()); 
			
			radioButtons[i] = new JRadioButton(options[i]/*, Icon icon */);
			radioButtons[i].addActionListener(new RadioButtonHandler());
			//~ radioButtons[i].setPreferredSize(new Dimension(100, 150));
			group.add(radioButtons[i]);
			tempToAdd.add(radioButtons[i], BorderLayout.SOUTH);
			
			//if statement is a precaution (still developing) 
			Image tempUserImage = null;
			if(userCharacters.size() != 0)
				tempUserImage = userCharacters.get(i).getImage();
			//creates drawing panel and adds it to the jpanel to add 
				//adds it second to make sure it has precedence 
			//gives the drawing panel the index, the option type, and the user character image
			JPanel drawingPan = getDrawingPan(i, optionType, tempUserImage);
			tempToAdd.add(drawingPan, BorderLayout.NORTH);
			
			radioPan.add(tempToAdd);
		}
		radioPan.add(clear);
		return radioPan;
	}
	/* 
	 */
	public JPanel getDrawingPan(int index, String whatToDraw, Image characterImageIn)
	{
		JPanel drawPan = new DrawingPanel(whatToDraw, characterImageIn);
		if(whatToDraw.equals("color"))
		{
			int[] rgb = toStringToColor(colors[index].toString());
			Color backgroundColor = new Color(rgb[0], rgb[1], rgb[2]);
			drawPan.setBackground(backgroundColor);
		}
		return drawPan;
	}
	/* helper1b to helper1 (getEnterData)
	 * takes in array name and returns a string[] of jradiobutton choices
	 */
	public String[] getOptionNames(String arrayName)
	{
		int arrayLength = 0;
		String[] output = null;
		if(arrayName.equals("colors"))
		{
			arrayLength = colors.length;
			output = new String[arrayLength];
			for(int i = 0; i < arrayLength; i++)
			{
				//parses actual color 
				output[i] = colorToString(colors[i]);
				
				//for programming purposes
				System.out.println(output[i]);
			}
		}
		else if(arrayName.equals("userCharacters"))
		{
			arrayLength = userCharacters.size();
			output = new String[arrayLength];
			for(int i = 0; i < arrayLength; i++)
			{
				output[i] = userCharacters.get(i).getName();
				//for programming purposes
				System.out.println(output[i]);
			}
		}
		if(output == null)
		{
			System.out.println("Warning: getOptions method in Select " + 
				"User Info Panel returns null array!\n\tArrayName:" + arrayName);
		}
		//output feeds into getRadioButtons 
		return output;
	}
	/* helper utility method to getDrawingPan; converts color.toString() 
	 * to int[] array of rgb to better draw square 
	 */
	public int[] toStringToColor(String toStringOutput)
	{
		Scanner decode = new Scanner(toStringOutput);
		//can 'hard code' this because rgb is rigidly 3 values 
		int[] output = new int[3];
		for (int i = 0; i < 3; i++)
		{
			output[i] = decode.nextInt();
		}
		return output;
	}
	
	/**
	 * Returns the passed in color as a String if it is in the Color[]
	 * array colors. If not, it returns null.
	 **/
	public String colorToString(Color colorIn)
	{
		if(colorIn == Color.RED)
			return "red";
		else if(colorIn == Color.ORANGE)
			return "orange";	
		else if(colorIn == Color.YELLOW)
			return "yellow";
		else if(colorIn == Color.GREEN)
			return "green";
		else if(colorIn == Color.BLUE)
			return "blue";
		else if(colorIn == Color.MAGENTA)
			return "magenta";
		else if(colorIn == Color.PINK)
			return "pink";
		else if(colorIn == Color.CYAN)
			return "cyan";
		else if(colorIn == Color.WHITE)
			return "white";
		else if(colorIn == Color.GRAY)
			return "gray";
		else if(colorIn == Color.DARK_GRAY)
			return "dark gray";
		else if(colorIn == Color.BLACK)
			return "black";
			
		return null;
	}
	//FROM HERE ON ARE THE CLASSES. 
	/* drawing panel either draws character image or doesn't. 
	 * if it doesn't, that means the whole panel background was changed
	 */
	class DrawingPanel extends JPanel
	{
		private String whatToDraw;
		private Image characterImage;
		public DrawingPanel(String whatToDrawIn, Image characterImageIn)
		{
			whatToDraw = whatToDrawIn;
			characterImage = characterImageIn;
		}
		public void paintComponent(Graphics g)
		{
			if(whatToDraw.equals("character"))
				g.drawImage(characterImage, 20, 20, this);
		}
	}
	//FROM HERE ON ARE THE HANDLERS. 
	/* handler for the grid radio buttons */
	class RadioButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String componentName = evt.getActionCommand();
			if(componentName.indexOf("Color") != -1)//if has 'color' in name
			{
				
			}
		}
	}
	/* handler for the grid radio buttons */
	class FieldHandler implements ActionListener
	{
		private String prompt;
		private TextField addedTo;//the textfield this handler is added to
		public FieldHandler(TextField textFieldIn, String promptIn)
		{
			prompt = promptIn;
			addedTo = textFieldIn;
		}
		public void actionPerformed(ActionEvent evt)
		{
			if(prompt.equals("First Name"))
				first = addedTo.getText();
			else if(prompt.equals("Last Name"))
				last = addedTo.getText();
			
			//for debugging purposes
			System.out.println("First: "+first);
			System.out.println("Last: "+last);
		}
	}
	/* handler for the clear button */
	class ClearGroupHandler implements ActionListener
	{
		private ButtonGroup group;
		public ClearGroupHandler(ButtonGroup groupIn)
		{
			group = groupIn;
		}
		public void actionPerformed(ActionEvent evt)
		{
			group.clearSelection();//this is all it does. 
		}
	}
	/* handler for the switch panels buttons */
	class SwitchPanels implements ActionListener
	{
		private String toPanel;
		public SwitchPanels(String toPanelIn)
		{
			toPanel = toPanelIn;
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			String componentName = evt.getActionCommand();
			CardLayout cards = GameData.getCardLayout();
			JPanel holder = GameData.getCardHolder();
			
			cards.show(holder, toPanel);//shows the panel 
		}
	}
	
	//DRAWPANEL CLASS from PutItTogether.java. 
	class DrawPanel extends JPanel
	{
		private RightPanel rp;
		private int amtRed, amtGreen, amtBlue;
		private int size;
		private JSlider[] sliders;
		private JScrollBar changeSize;
		
		public DrawPanel()
		{
			rp = new RightPanel();
			LeftPanel lp = new LeftPanel();
			lp.setPreferredSize(new Dimension(450, 500));					//x, y
			JPanel bottomPanel = new JPanel();
			bottomPanel.setPreferredSize(new Dimension(800, 100));
			
			setLayout(new BorderLayout());
			add(lp, BorderLayout.WEST);
			add(rp, BorderLayout.EAST);
			add(bottomPanel, BorderLayout.SOUTH);									//block at bottom (doesn't work :sob:)
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
					//~ else if(i == 3)
						//~ sliders[i].addChangeListener(new SizeSliderListener());
					
					add(changeLabels[i]);
					if(i != colors.length-1)
						add(sliders[i]);									//save the last one for jscrollbar 
				}
				changeSize = new JScrollBar(JScrollBar.HORIZONTAL, 100, 25, 50, 250);
				changeSize.addAdjustmentListener(new SizeSliderListener());
				add(changeSize);
				/*
				changeLabels[3].setText("Change size");
				sliders[3].setMinimum(50);
				sliders[3].setValue(100);
				*/
				
				
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
			/*
			class SizeSliderListener implements ChangeListener
			{
				public void stateChanged(ChangeEvent evt)
				{
					int val = sliders[3].getValue();
					size = val;
					rp.repaint();
				}
			}
			*/
			class SizeSliderListener implements AdjustmentListener
			{
				public void adjustmentValueChanged(AdjustmentEvent evt)
				{
					int val = changeSize.getValue();
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
	}
}
