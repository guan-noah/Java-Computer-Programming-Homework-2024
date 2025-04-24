//written by Krish

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.CardLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends JPanel
{
	private Image logo;
	private Popup helpPopup;
	private Popup highScorePopup;
	private CardLayout cards;
	
	public MainMenuPanel()
	{
		setLayout(null);
		
		logo = GameData.loadImage("polyboundLogo.jpg");
		cards = GameData.getCardLayout();
		
		helpPopup = getHelpPopup();
		highScorePopup = getHighScorePopup();
		
		MainMenuButtonHandler mmBtnHandler = new MainMenuButtonHandler();
		Button startButton = new Button("START", mmBtnHandler);
		Button optionsButton = new Button("OPTIONS", mmBtnHandler);
		Button quitButton = new Button("QUIT", mmBtnHandler);
		Button highScoreButton = new Button("HI-SCORES", mmBtnHandler);
		Button helpButton = new Button("HELP", mmBtnHandler);
		
		startButton.setBounds(400, 410, 400, 100);
		optionsButton.setBounds(400, 510, 400, 100);
		quitButton.setBounds(400, 610, 400, 100);
		highScoreButton.setBounds(50, 350, 300, 100);
		helpButton.setBounds(850, 350, 300, 100);
		
		add(startButton);
		add(optionsButton);
		add(quitButton);
		add(highScoreButton);
		add(helpButton);
		
		setBackground(Color.BLUE);
	}
	
	public Popup getHelpPopup()
	{
		Popup toReturn = new Popup("Help");
		toReturn.setContent("Hello!");
		
		return toReturn;
	}
	
	public Popup getHighScorePopup()
	{
		Popup toReturn = new Popup("High Scores");
		
		return toReturn;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(logo, 400, 100, this);
	}
	
	class MainMenuButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();

			if(command.equals("HELP"))
			{
				helpPopup.show();
			}
			else if(command.equals("HI-SCORES"))
			{
				highScorePopup.show();
			}
			else if(command.equals("QUIT"))
			{
				System.exit(0);
			}
		}
	}
}
