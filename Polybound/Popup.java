import javax.swing.JFrame;
import javax.swing.JPanel;

public class Popup
{
	protected JFrame popupFrame;
	protected JPanel content;
	protected String title;
	
	public Popup(String titleIn)
	{
		title = titleIn;
		
		popupFrame = getFrame();
		content = getContentPanel();
		
		popupFrame.setContentPane(content);
		popupFrame.setResizable(false);
	}
	
	/**
	 * This method is responsible for setting up the JFrame that will
	 * be the actual popup.
	 **/
	public JFrame getFrame()
	{
		JFrame toReturn = new JFrame(title); ///creates new JFrame
		
		///frame setup
		toReturn.setSize(600, 500);
		toReturn.setLocationRelativeTo(null);
		
		return toReturn;
	}
	
	public JPanel getContentPanel()
	{
		JPanel toReturn = new JPanel();
		return toReturn;
	}
	
	/**
	 * This method is responsible for showing the popup.
	 **/
	public void show()
	{
		popupFrame.setVisible(true); ///makes frame visible
	}
	
	
}
