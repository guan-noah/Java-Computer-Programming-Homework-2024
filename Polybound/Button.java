import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Font;

public class Button extends JButton
{
	public Button(String text, ActionListener buttonListener)
	{
		super(text);
		addActionListener(buttonListener);
	}
	
	public Button(String text, ActionListener buttonListener, Font buttonFont)
	{
		super(text);
		setFont(buttonFont);
		addActionListener(buttonListener);
	}
}
