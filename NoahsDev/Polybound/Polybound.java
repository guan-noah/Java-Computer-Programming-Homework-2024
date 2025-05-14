/*
 * Project started 4/23/2025
 * 
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
		///this is some debug code for Characters
		//~ Character[] ch = new Character[10];
		//~ for(int i=0; i<ch.length; i++)
		//~ {
			//~ ch[i] = new Character("Circle");
			//~ ch[i].print();
			//~ System.out.println();
		//~ }
				
		JFrame frame = new JFrame("Polybound");
		JPanel deck = new JPanel(new CardLayout());
		
		GameData.setCardHolder(deck);
		GameData.setGameStarted(false);
		
		frame.setSize(1200, 750);	//normal: 600, 500
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //centers everything
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		MainMenuPanel mPanel = new MainMenuPanel();
		IntermissionPanel imPanel = new IntermissionPanel();
		SelectUserInfoPanel suiPanel = new SelectUserInfoPanel();
		GamePanel gPanel = new GamePanel();
		ProblemPanel pPanel = new ProblemPanel();
		deck.add(mPanel, "main menu");
		deck.add(suiPanel, "select info");
		deck.add(imPanel, "intermission");
		deck.add(gPanel, "game");
		deck.add(pPanel, "problem");
		
		GameData.setProblemPanel(pPanel);
		
		frame.setContentPane(deck);
		frame.setVisible(true);
	}
}
