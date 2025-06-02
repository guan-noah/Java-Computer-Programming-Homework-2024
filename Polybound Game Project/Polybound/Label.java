/**
 * Krish Kumar
 * Period 6
 * Label.java
 * 
 * This class extends from JLabel. The purpose of it is to allow for a
 * much simpler implementation of labels within the game.
 * 
 * NOTE: THIS CLASS IS VERY MUCH SUBJECT TO CHANGE!
 **/

///import libraries
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

public class Label extends JLabel
{
	/**
	 * One of the constructors, responsible for setting up the Label
	 * instance based on the parameters given. In this case, the superclass
	 * constructor is called, and the font is set to the Font passed in.
	 **/	
	public Label(String text, Font labelFont)
	{
		super(text); ///superclass constructor call
		setFont(labelFont); ///font is configured
	}
	
	/**
	 * One of the constructors, responsible for setting up the Label
	 * instance based on the parameters given. In this case, the superclass
	 * constructor is called, and the font is configured based on the font
	 * size passed in.
	 **/	
	public Label(String text, int fontSize)
	{
		super(text); ///superclass constructor call
		setFont(new Font("SansSerif", Font.BOLD, fontSize)); ///font is configured
	}
	
	/**
	 * One of the constructors, responsible for setting up the Label
	 * instance based on the parameters given. In this case, the superclass
	 * constructor is called, the font is set to the Font passed in, and the
	 * color is set to the Color passed in.
	 **/	
	public Label(String text, Font labelFont, Color labelColor)
	{
		super(text); ///superclass constructor call
		setFont(labelFont); ///font is configured
		setForeground(labelColor); ///color is set
	}
	
	/**
	 * One of the constructors, responsible for setting up the Label
	 * instance based on the parameters given. In this case, the superclass
	 * constructor is called, the font is configured based on the font size
	 * passed in, and the color is set to the Color passed in.
	 **/	
	public Label(String text, int fontSize, Color labelColor)
	{
		super(text); ///superclass constructor call
		setFont(new Font("SansSerif", Font.BOLD, fontSize)); ///font is configured
		setForeground(labelColor); ///color is set
	}
}
