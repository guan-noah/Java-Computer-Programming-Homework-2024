/* Noah Guan
 * GameData.java
 * Project started 4/23/2025
*/
import java.awt.CardLayout;
import javax.swing.JPanel;
//noah
///krish
/* noah */
/** krish **/
public class GameData													//static class 
{
	private static CardLayout polyCards;
	private static JPanel polyPanel;
	
	public GameData()
	{
		initializePanelAndCardLayout();
	}
	public GameData(CardLayout polyCardsIn, JPanel polyPanelIn)
	{
		polyCards = polyCardsIn;
		polyPanel = polyPanelIn;
	}
	
	public static CardLayout initializePanelAndCardLayout()
	{
		polyCards = new CardLayout();
		polyPanel = new JPanel();
		polyPanel.setLayout(polyCards);
		
		MainMenuPanel menuPan = new MainMenuPanel();						//must feed in to access CardLayout; easier way would just to encapsulate all classes in GameModuleFiles and have fvs 
		add(menuPan, "menu");
		/*
		GamePanel gamePan = new GamePanel();
		add(gamePan, "game");
		
		OptionPanel optionsPan = new OptionPanel();
		add(optionsPan, "options");
		
		StatsPanel statsPan = new StatsPanel();
		add(statsPan, "stats");
		
		ImagesPanel imagesPan = new ImagesPanel();
		add(imagesPan, "images");
		
		ProblemPanel problemPan = new ProblemPanel();
		add(problemPan, "problem");
		
		ContentPanel contentPan = new ContentPanel();
		add(contentPan, "content");
		*/
	}
	
	
	public static CardLayout getPolyCards()
	{
		return polyCards;
	}
	
	public static JPanel getPolyPanel()
	{
		return polyPanel;
	}
}
