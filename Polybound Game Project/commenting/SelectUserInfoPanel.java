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
import javax.swing.BorderFactory;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;							//for labels/instructions
import java.awt.Dimension;								//preferred size
import java.awt.BorderLayout;							//layout imports
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;				//for the buttons 
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentListener;			//for the JScrollBar
import java.awt.event.AdjustmentEvent;
import javax.swing.event.ChangeListener;			//for the JSliders
import javax.swing.event.ChangeEvent;

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
	private Color[] colors;
	private TextField nameField;
	private String charName;
	private JRadioButton[] charButtons;
	private String[] userCharacters;
	//~ ArrayList<JButtonGroup>
	
	public SelectUserInfoPanel()
	{
		//~ userCharacter = getCharacters();//after GameProgression is done, 
			//we will get user character list, add it here, and create method
		
		userCharacters = new String[] {"Line", "Quadratic", "Cubic"};
		//if your favorite color is not in here, potentially add a color picker 
		charName = null;
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
	
	/**
	 * Returns the passed in color as a String if it is in the Color[]
	 * array colors. If not, it returns null.
	 **/
	public String colorToString(Color colorIn)
	{
		if(colorIn == Color.RED)
			return "red";
		if(colorIn == Color.ORANGE)
			return "orange";	
		if(colorIn == Color.YELLOW)
			return "yellow";
		if(colorIn == Color.GREEN)
			return "green";
		if(colorIn == Color.BLUE)
			return "blue";
		if(colorIn == Color.MAGENTA)
			return "magenta";
		if(colorIn == Color.PINK)
			return "pink";
		if(colorIn == Color.CYAN)
			return "cyan";
		if(colorIn == Color.WHITE)
			return "white";
		if(colorIn == Color.GRAY)
			return "gray";
		if(colorIn == Color.DARK_GRAY)
			return "dark gray";
		if(colorIn == Color.BLACK)
			return "black";
			
		return null;
	}
	
	//second half
	/* returns the back and continue buttons */
	public JPanel getBottomButtons()
	{
		JPanel bottomButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
		int buttonFont = 55;
		Button finish = new Button("Finish", new SwitchPanels("intermission"), buttonFont);
		Button toMenu = new Button("Return", new SwitchPanels("main menu"), buttonFont);

		bottomButtons.setPreferredSize(new Dimension(1200, 100));
		bottomButtons.setBackground(Color.DARK_GRAY);
		
		bottomButtons.add(finish);
		bottomButtons.add(toMenu);
		
		return bottomButtons;
	}
	
	//first half (done! good.)
	public JPanel getSelection()
	{
		JPanel selection = new JPanel(new BorderLayout());
		selection.setBackground(Color.GRAY);
		selection.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));//create the border
		
		Label prompt = new Label("Create New Game", 75);
		selection.add(prompt, BorderLayout.NORTH); //create the prompt 
		JPanel centerSelect = getCenter();//create center selection (grid and jradiobuttons)
		selection.add(centerSelect, BorderLayout.CENTER);
		return selection;
	}
	
	public JPanel getCenter()
	{
		//the prompts for the data
		String[] prompts = new String[] {"Name"};
		//the component for the type of answer response we want (for each prompt) 
		String[] types = new String[] {"Field", "Radio|colors", "Radio|userCharacters"};
		
		//get the grid of prompts and types -- the center panel 
		JPanel grid = getGrid(prompts, types);
		grid.setPreferredSize(new Dimension(1150, 600));
		
		return grid;
	}
	//helper 1 to getCenter 
	public JPanel getGrid(String[] prompts, String[] types)
	{
		int rows = prompts.length;
		JPanel grid = new JPanel(new GridLayout(rows, 2, 10, 10));//the jpanel containing grid 
		grid.setPreferredSize(new Dimension(950, 600));
		grid.setBackground(Color.LIGHT_GRAY);
		
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
			String defaultText = "Enter in your " + prompt.toLowerCase() + ".";
			TextField enterText = new TextField(defaultText, defaultText.length(), 20);
			enterData.add(enterText);

			if(prompt.equals("Name"))
			{
				nameField = enterText;
			}
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
		JPanel radioPan = new JPanel(new GridLayout(4, 3));
		JRadioButton[] radioButtons = new JRadioButton[numOfButtons];
		for(int i = 0; i < numOfButtons; i++)
		{
			radioButtons[i] = new JRadioButton(options[i]/*, Icon icon */);
			radioButtons[i].addActionListener(new RadioButtonHandler());
			//~ radioButtons[i].setPreferredSize(new Dimension(100, 150));
			group.add(radioButtons[i]);
			radioPan.add(radioButtons[i]);
		}
		if(options[0].equals("Line"))
		{
			charButtons = radioButtons;
		}
		radioPan.add(clear);
		return radioPan;
	}
	//helper utility method to getCenter
	//userCharacter
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
				output[i] = colorToString(colors[i]);
				//for programming purposes
				System.out.println(output[i]);
			}
		}
		else if(arrayName.equals("userCharacters"))
		{
			String[] userCharacters = {"Line", "Quadratic", "Cubic"};
			output = userCharacters;
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
			String command = evt.getActionCommand();
			boolean isCharacterSelection = false;

			for(int i=0; i<userCharacters.length; i++)
			{
				if(command.equals(userCharacters[i]))
				{
					charName = userCharacters[i];
					isCharacterSelection = true;
				}
			}

			if(!isCharacterSelection)
			{
				///favorite color code here
			}
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
			String command = evt.getActionCommand();

			if(command.equals("Finish"))
			{
				///all required fields are entered
				if(nameField.isSelected())
				{
					GameData.setUserName(nameField.getText());

					Character[] chars = new Character[3];
					for(int i=0; i<chars.length; i++)
					{
						chars[i] = new Character(userCharacters[i], 1);
					}
					GameData.setPlayerChars(chars);

					GameData.setPlayerCharacter(chars[0]);
					GameData.setEnemiesDefeated(0);

					GameData.switchCard(toPanel);
				}
			}
			else if(command.equals("Return"))
			{
				GameData.switchCard(toPanel);//shows the panel 
			}
		}
	}
}
