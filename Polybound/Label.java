import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

public class Label extends JLabel
{
	public Label(String text, Font labelFont)
	{
		super(text);
		setFont(labelFont);
	}
	
	public Label(String text, Font labelFont, Color labelColor)
	{
		super(text);
		setFont(labelFont);
		setForeground(labelColor);
	}
}
