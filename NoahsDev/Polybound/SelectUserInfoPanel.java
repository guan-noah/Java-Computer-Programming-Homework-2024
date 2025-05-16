/*
 * Noah Guan
 * Period 6
 * SelectUserInfoPanel.java
 * 
 * 


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
	ArrayList<Character> userCharacters;
	Color[] colors;
	ArrayList<Integer> numbers;
	//~ ArrayList<JButtonGroup>
	
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
	
	//first half (done! good.)
	public JPanel getSelection()
	{
		JPanel selection = new JPanel(new BorderLayout());
		selection.setBackground(Color.BLACK);
		selection.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));//create the border
		
		Label prompt = new Label("Please enter your information:", 75);
		prompt.setForeground(Color.MAGENTA);
		selection.add(prompt, BorderLayout.NORTH); //create the prompt 
		JPanel centerSelect = getCenter();//create center selection (grid and jradiobuttons)
		selection.add(centerSelect, BorderLayout.CENTER);
		return selection;
	}
	
	public JPanel getCenter()
	{
		JPanel toReturn = new JPanel(new BorderLayout(10, 0)/*FlowLayout(FlowLayout.CENTER, 0, 75)*/);
			//the center panel, including the buttons and the grid 
		toReturn.setPreferredSize(new Dimension(1150, 600));
		
		//the prompts for the data
		String[] prompts = new String[] {"First Name", "Last Name", "Character", 
			"Favorite Color"/*, "Email", "School ID", "Country", "Address", 
			"Birthday", "Social Security Number", "Credit Card Number", 
			"Shoe Size", "Face ID"*/};//the rest are jokes. 
		//the component for the type of answer response we want (for each prompt) 
		String[] types = new String[] {"Field", "Field", "Radio|userCharacters", 
			"Radio|colors"/*, "Field", "Field", "Field", "Field", "Radio|numbers", 
			"Field", "Field", "Field", "Field"*/};
		
		JPanel grid = getGrid(prompts, types);//get the grid of prompts and types 
		toReturn.add(grid, BorderLayout.WEST);
		
		String[] options = new String[] {"option1", "option2", "option3"};
		JPanel radioButtonPan = getRadioButtons(options);
		//note: cannot set size for radio buttons because it is used for both 
		radioButtonPan.setBackground(Color.GREEN);
		toReturn.add(radioButtonPan, BorderLayout.EAST);
		
		return toReturn;
	}
	//helper 1 to getCenter 
	public JPanel getGrid(String[] prompts, String[] types)
	{
		int rows = prompts.length;
		JPanel grid = new JPanel(new GridLayout(rows, 2, 10, 10));//the jpanel containing grid 
		grid.setPreferredSize(new Dimension(950, 600));
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
	//helper 1a to helper1 
	public JPanel getEnterData(String type, String prompt)
	{
		JPanel enterData = new JPanel();
		int isRadioButton = type.indexOf('|'); //if it is, it will be an int 0 or greater
		if(isRadioButton < 0)
		{
			TextField enterText = new TextField(prompt, prompt.length() + 5, 20);
			enterData.add(enterText);
		}
		else
		{
			//needs indexOf('|')+1 to get whatever is after the '|' but not the '|' itself 
			JPanel choice = getRadioButtons(getOptionNames(type.substring(type.indexOf('|')+1)));
			enterData.add(choice);
		}
		return enterData;
	}
	//helper 2 to getCenter and helper1a
	public JPanel getRadioButtons(String[] options)
	{
		ButtonGroup group = new ButtonGroup();
		//gives the handler the button group to clear selection 
		Button clear = new Button("Clear selection", new ClearGroupHandler(group));
		
		int numOfButtons = options.length;
		JPanel radioPan = new JPanel(new GridLayout(3, numOfButtons/3+1, 5, 5));
		JRadioButton[] radioButtons = new JRadioButton[numOfButtons];
		for(int i = 0; i < numOfButtons; i++)
		{
			radioButtons[i] = new JRadioButton(options[i]/*, Icon icon */);
			radioButtons[i].addActionListener(new RadioButtonHandler());
			//~ radioButtons[i].setPreferredSize(new Dimension(100, 150));
			group.add(radioButtons[i]);
			radioPan.add(radioButtons[i]);
		}
		radioPan.add(clear);
		return radioPan;
	}
	//helper utility method to getCenter 
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
				output[i] = colors[i].toString();//can add a .find() method later to parse actual color name
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
				output[i] = userCharacters.get(i).toString();//same logic with .find() method
				//for programming purposes
				System.out.println(output[i]);
			}
		}
		if(output == null)
		{
			System.out.println("Warning: getOptions method in Select " + 
				"User Info Panel returns null array!\n\tArrayName:" + arrayName);
		}
		return output;
	}
	
	//FROM HERE ON ARE THE HANDLERS. 
	/* handler for the grid radio buttons */
	class RadioButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String componentName = evt.getActionCommand();
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
