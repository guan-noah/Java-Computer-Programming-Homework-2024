//Written by Krish

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

public class Label extends JLabel
{
	public Label(String text)
	{
		super(text);
		setFont(new Font("SansSerif", Font.BOLD, 30));
	}
	
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
