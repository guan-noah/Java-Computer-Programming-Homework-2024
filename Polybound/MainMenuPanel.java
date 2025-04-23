import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

public class MainMenuPanel extends JPanel
{
	private Image logo;
	
	public MainMenuPanel()
	{
		logo = GameData.loadImage("polyboundLogo.jpg");
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(logo, 400, 100, this);
	}
}
