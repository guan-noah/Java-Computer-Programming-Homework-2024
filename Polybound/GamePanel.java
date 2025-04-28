/*
 * Noah Guan
 * Period 6
 * InfoPopup.java
 * 
 */
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GamePanel
{
	private 
	private JMenuBar moves;
	private JButton guide, exit;
	
	private UserInfo uInfo;
	
	private JTextArea gameTurnInfo;
	private JPanel sideInfoPan;
	private GamePanel gameUI;
	
	public GamePanel()
	{
		setLayout(new BorderLayout());
		
		gamePanColor = 
		
		gameUI.setPreferredSize(1000, 550);//follows game proposal pseudocode
		getGameUI();						//makes the rest of game pan
		add(gameUI, BorderLayout.CENTER);				//adds panel
		
		sideInfoPan.setPreferredSize(200, 550);
		getInfoPan();										//same logic
		add(sideInfoPan, BorderLayout.WEST);
		
		gameTurnInfo.setPreferredSize(1200, 200);
		getGameTurnInfo();
		add(gameTurnInfo, BorderLayout.SOUTH);
	}
	class GamePanel extends JPanel;
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
		}
	}
	class UserInfo extends JPanel;
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
		}
	}
	public void getGameUI()
	{
		gameUI.setBackground(gamePanColor);
		
	}
	public void getInfoPan()
	{
		moves = createMenuBar();					//initialize moves
		sideInfoPan.setLayout(new BorderLayout());
	}
	public void getGameTurnInfo()
	{
		
	}
	public JMenuBar createMenuBar()
	{
		JMenu moveSetMenu = new JMenu();
		ArrayList<String> moveSets = new ArrayList<> ();
		addMoves();
		
		for(int i = 0; i < moveSets.size(), i++)
			moveSet.add(moveSets.get(index));
	}
	public JMenuItem getMenuItem(String menuItemName)
	{
		return new JMenuItem(menuItemName);//we can add an int mnemonic if we want 
	}
	
}
