/**
 * Krish Kumar
 * Period 6
 * IntermissionPanel.java 
 * 
 * The intermission panel, which allows players to continue on to another
 * battle, or to exit back to the main menu. It also displays the
 * player's current stats.
 **/

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IntermissionPanel extends JPanel
{
	public IntermissionPanel()
	{
		setLayout(new BorderLayout());
		
		JPanel selectionPanel = getSelections();
		StatsPanel statsPanel = new StatsPanel();
		
		add(selectionPanel, BorderLayout.SOUTH);
		add(statsPanel, BorderLayout.CENTER);
	}
	
	/**
     * This method is responsible for the creation of the
	 * options to continue the game/return to menu, as well
	 * as the panel that holds them.
     **/
	public JPanel getSelections()
	{
		JPanel toReturn = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
		int buttonFont = 55;
		SelectionHandler selectHandler = new SelectionHandler();
		Button continueButton = new Button("Continue", selectHandler, buttonFont);
		Button returnButton = new Button("Return to Menu", selectHandler, buttonFont);
		
		toReturn.setBackground(Color.DARK_GRAY);
		toReturn.add(continueButton);
		toReturn.add(returnButton);
		
		return toReturn;
	}
	
	/**
     * This class is responsible for handling the options
	 * to continue/return to menu.
     **/
	class SelectionHandler implements ActionListener
	{
		/**
     	 * This method is responsible for deciding what to do
		 * based on which option was selected.
    	 **/
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			
			if(command.equals("Continue"))
			{
				GameData.setGameStarted(true);
				
				CardLayout cards = GameData.getCardLayout();
				JPanel holder = GameData.getCardHolder();
				cards.show(holder, "game");
				///add actual code later
			}
			else if(command.equals("Return to Menu"))
			{
				CardLayout cards = GameData.getCardLayout();
				JPanel holder = GameData.getCardHolder();
				cards.show(holder, "main menu");
			}
		}
	}
	
	/**
	 * This class is a JPanel, responsible for displaying
	 * the player's current stats.
	 */
	class StatsPanel extends JPanel
	{
		private Image hpIcon;
		
		public StatsPanel()
		{
			setBackground(Color.GRAY);
			hpIcon = GameData.loadImage("healthIcon.png"); ///this is subject to change
		}
		
		/**
		 * This is the paintComponent() method inherited from JPanel, overrided
		 * to display the stat icons as well as the stats themselves.
	 	**/
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.setFont(new Font("SansSerif", Font.BOLD, 130));

			///as of now, only health is displayed for testing
			g2d.drawString("100/100", 440, 280);
			g2d.drawImage(hpIcon, 50, 100, 345, 240, this);
		}
	}
}
