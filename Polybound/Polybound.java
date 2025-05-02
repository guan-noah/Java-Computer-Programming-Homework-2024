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
		Character[] ch = new Character[10];
		for(Character c : ch)
		{
			c = new Character("Dummy");
			c.print();
			System.out.println();
		}
				
		JFrame frame = new JFrame("Playing Polybound");
		JPanel deck = new JPanel(new CardLayout());
		
		GameData.setCardHolder(deck);
		GameData.gameStarted(false);
		
		frame.setSize(1200, 750);	//normal: 600, 500
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //centers everything
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		MainMenuPanel mPanel = new MainMenuPanel();
		IntermissionPanel imPanel = new IntermissionPanel();
		GamePanel gPanel = new GamePanel();
		deck.add(mPanel, "main menu");
		deck.add(imPanel, "intermission");
		deck.add(gPanel, "game");
		frame.setContentPane(deck);
		frame.setVisible(true);
	}
}
