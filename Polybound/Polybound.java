import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class Polybound
{
	//noah wrote this
	public static void main(String[] args)
	{
		System.out.println("\n\n\n");
		Polybound polyGame = new Polybound();
		polyGame.run();
		System.out.println("\n\n\n");
	}
	
	/**
	 * CardLayout stuff written by Krish, rest written by Noah
	 **/
	public void run()
	{
		JFrame frame = new JFrame("Playing Polybound");
		CardLayout cards = new CardLayout();
		JPanel deck = new JPanel(cards);
		int widthScalable = 1920*4/5;
		int heightScalable = (1080-50)*4/5;	//1920 x 1080 screen with a 50 px toolbar
		int frameX = 1200;
		int frameY = 750;
		
		GameData.setCardLayout(cards);
		frame.setSize(frameX, frameY);	//normal: 600, 500
		//~ frame.setLocation(widthScalable-frameX, heightScalable-frameY);	//sets it to the perfect screen edge (plus 50 to give space for toolbar)
		frame.setLocationRelativeTo(null); //centers everything
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		MainMenuPanel mPanel = new MainMenuPanel();
		deck.add(mPanel, "main menu");
		frame.setContentPane(deck);
		frame.setVisible(true);
	}
}
