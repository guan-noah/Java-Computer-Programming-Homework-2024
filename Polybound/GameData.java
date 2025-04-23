/* Noah Guan
 * GameData.java
 * Project started 4/23/2025
*/
import java.awt.CardLayout;
import javax.swing.JPanel;
public class GameData
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
		
		MainMenuPanel menu = new MainMenuPanel(this);				//must feed in to access CardLayout; easier way would just to encapsulate all classes in GameModuleFiles and have fvs 
		add(menu, "menu");
		
		GamePanel game = new GamePanel(this);
		add(game, "game");
		
		OptionPanel options = new OptionPanel(this);
		add(options, "options");
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
