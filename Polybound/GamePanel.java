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
	private JMenuBar moves;
	private JButton guide, exit;
	private JTextArea gameTurnInfo;
	
	public GamePanel()
	{
		moves = createMenuBar();
		
		add(moves);
	}
	public JMenuBar createMenuBar()
	{
		JMenu moveSetMenu = new JMenu();
		ArrayList<String> moveSets = new ArrayList<> {};
		addMoves();
		
		for(int i = 0; i < moveSets.length())
			moveSet.add(moveSets.get(index));
	}
	public JMenuItem getMenuItem(String menuItemName)
	{
		return new JMenuItem(menuItemName);//we can add an int mnemonic if we want 
	}
	
}
