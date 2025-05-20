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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

public class IntermissionPanel extends JPanel
{
	private JTextArea infoTextArea;
	private StatsPanel statsPanel;

	public IntermissionPanel()
	{
		setLayout(new BorderLayout());
		
		JPanel selectionPanel = getSelections();
		JScrollPane infoPane = getPlayerInfo();
		statsPanel = new StatsPanel();
		
		add(infoPane, BorderLayout.EAST);
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
		JPanel toReturn = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
		int buttonFont = 55;
		SelectionHandler selectHandler = new SelectionHandler();
		Button continueButton = new Button("Continue", selectHandler, buttonFont);
		Button returnButton = new Button("Return to Menu", selectHandler, buttonFont);
		
		toReturn.setBackground(Color.DARK_GRAY);
		toReturn.add(continueButton);
		toReturn.add(returnButton);
		
		return toReturn;
	}

	public JScrollPane getPlayerInfo()
	{
		///creates new JTextArea and JScrollPane
		infoTextArea = new JTextArea(30, 13);
		JScrollPane toReturn = new JScrollPane(infoTextArea);
			
		///text area setup
		infoTextArea.setFont(new Font("Verdana", Font.PLAIN, 25));
		infoTextArea.setBackground(Color.DARK_GRAY);
		infoTextArea.setForeground(Color.WHITE);
		infoTextArea.setLineWrap(true);
		infoTextArea.setWrapStyleWord(true);
		infoTextArea.setEditable(false);
			
		return toReturn;
	}

	public void refreshStats()
	{
		statsPanel.repaint();
	}

	public void updatePlayerInfo()
	{
		String userName = GameData.getUserName();
		int enemiesDefeated = GameData.getEnemiesDefeated();
		Character player = GameData.getPlayerCharacter();

		infoTextArea.setText(userName + "\n\nCharacter Info\nLevel " + player.getLevel() +
			"\nDefeated " + enemiesDefeated + " enemies\n\n" + player.getDescription() +
			"\n\nMoves:\n");

		ArrayList<String> moveSet = player.getMoveset();
		int bound = player.getLevel()+2;
		if(bound > moveSet.size())
		{
			bound = moveSet.size();
		}
		for(int i=0; i<bound; i++)
		{
			String moveName = moveSet.get(i);
			Move move = new Move(moveName);
			infoTextArea.append(moveName + " - " + move.getDescription() + "\n\n");
		}
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
				GameData.startGame(!GameData.gameIsStarted());
				
				GameData.switchCard("game");
				///add actual code later
			}
			else if(command.equals("Return to Menu"))
			{
				GameData.switchCard("main menu");
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
		private Image manaIcon;
		private Image defIcon;
		
		public StatsPanel()
		{
			setBackground(Color.GRAY);
			hpIcon = GameData.loadImage("healthIcon.png");
			defIcon = GameData.loadImage("defenseIcon.png");
			manaIcon = GameData.loadImage("manaIcon.png");
		}
		
		/**
		 * This is the paintComponent() method inherited from JPanel, overrided
		 * to display the stat icons as well as the stats themselves.
	 	**/
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			g.setFont(new Font("Verdana", Font.BOLD, 100));

			///as of now, only health is displayed for testing
			Character player = GameData.getPlayerCharacter();

			g.drawString(player.getName(), 20, 120);

			g.drawImage(hpIcon, -80, 100, 311, 216, this);
			g.drawImage(manaIcon, -80, 250, 311, 216, this);
			g.drawImage(defIcon, -80, 400, 311, 216, this);
			g.drawString(player.getHP() + "/" + player.getMaxHP() + " HP", 160, 260);
			g.drawString(player.getMana() + "/" + player.getMaxMana() + " Mana", 160, 400);
			g.drawString(player.getDefense() + " Defense", 160, 540);
			updatePlayerInfo();
		}
	}
}
