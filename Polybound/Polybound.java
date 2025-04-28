//Project started 4/23/2025
/*
 * Noah Guan and Krish Kumar
 * Period 6
 * Polybound.java
 * 
 * The class with main(), creates the JFrame, CardLayout, and generally
 * sets up the rest of the game.
 * 
 * NOTE: THIS CLASS IS VERY MUCH SUBJECT TO CHANGE!
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Dimension;

public class Polybound
{
	///main method, calls run()
	public static void main(String[] args)
	{
		System.out.println("\n\n\n");
		Polybound polyGame = new Polybound();
		polyGame.run();
		System.out.println("\n\n\n");
	}
	
	///CardLayout stuff by Krish, rest by Noah
	///Sets up the game
	public void run()
	{
		JFrame frame = new JFrame("Playing Polybound");
		JPanel deck = new JPanel(new CardLayout());
		
		GameData.setCardHolder(deck);
		
		frame.setMinimumSize(new Dimension(1200, 750));	//sets minimum size
		frame.setLocationRelativeTo(null); //centers everything
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setResizable(false);
		
		MainMenuPanel mPanel = new MainMenuPanel();
		IntermissionPanel imPanel = new IntermissionPanel();
		deck.add(mPanel, "main menu");
		deck.add(imPanel, "intermission");
		frame.setContentPane(deck);
		frame.setVisible(true);
	}
}
