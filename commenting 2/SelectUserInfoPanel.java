/*
 * Noah Guan
 * Period 6
 * SelectUserInfoPanel.java
 * 
 * This class gets some information from the user to
 * configurate their new game.
 */

///import libraries
import javax.swing.JPanel; 									///components
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.Dimension;								//preferred size
import java.awt.BorderLayout;							//layout imports
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;				//for the buttons 
import java.awt.event.ActionEvent;

/* class that asks for user information */
public class SelectUserInfoPanel extends JPanel
{
	private TextField nameField;
	private TextField passwordField;
	private String[] userCharacters;
	
	public SelectUserInfoPanel()
	{
		//~ userCharacter = getCharacters();//after GameProgression is done, 
			//we will get user character list, add it here, and create method
		
		userCharacters = new String[] {"Line", "Quadratic", "Cubic"};
		//if your favorite color is not in here, potentially add a color picker 
		
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
		JPanel centerSelect = getCenter();//create center fields
		selection.add(centerSelect, BorderLayout.CENTER);
		return selection;
	}

	public JPanel getCenter()
	{
		JPanel toReturn = new JPanel(new GridLayout(2, 2,20, 20));
		JPanel nameFieldName = getFieldName("Name");
		JPanel passwordFieldName = getFieldName("Password");

		JPanel nameFieldHolder = new JPanel();
		JPanel passwordFieldHolder = new JPanel();
		nameField = new TextField("Enter in your name.", 15, 20);
		passwordField = new TextField("Enter in your password.", 18, 20);

		toReturn.setBackground(Color.LIGHT_GRAY);
		nameFieldHolder.add(nameField);
		passwordFieldHolder.add(passwordField);
		toReturn.add(nameFieldName);
		toReturn.add(nameFieldHolder);
		toReturn.add(passwordFieldName);
		toReturn.add(passwordFieldHolder);

		return toReturn;
	}

	public JPanel getFieldName(String nameIn)
	{
		JPanel toReturn = new JPanel();
		Label name = new Label(nameIn, 20);

		toReturn.add(name);

		return toReturn;
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
				if(nameField.isSelected() && passwordField.isSelected())
				{
					///configures the username/password and initializes the game
					GameData.setUserName(nameField.getText());
					GameData.setPassword(passwordField.getText());

					Character[] chars = new Character[3];
					for(int i=0; i<chars.length; i++)
					{
						chars[i] = new Character(userCharacters[i], 1);

						if(GameData.gameIsLiked()) ///+5 hp for liking the game!
						{
							chars[i].changeHP(5, false);
						}
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
