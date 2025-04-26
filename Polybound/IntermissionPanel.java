import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IntermissionPanel extends JPanel
{
	public IntermissionPanel()
	{
		setLayout(new BorderLayout());
		
		JPanel selectionPanel = getSelections();
		
		add(selectionPanel, BorderLayout.SOUTH);
	}
	
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
	
	class SelectionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			
			if(command.equals("Continue"))
			{
				
			}
			else if(command.equals("Return to Menu"))
			{
				CardLayout cards = GameData.getCardLayout();
				JPanel holder = GameData.getCardHolder();
				cards.show(holder, "main menu");
			}
		}
	}
	
	class StatsPanel extends JPanel
	{
		public StatsPanel()
		{
			setBackground(Color.GRAY);
		}
		
		public void paintComponent()
		{
			
		}
	}
}
