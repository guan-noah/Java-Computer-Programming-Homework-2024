/**
 * Krish Kumar
 * Period 6
 * Button.java
 * 
 * This class extends from JButton. The purpose of it is to allow for a
 * much simpler implementation of buttons within the game.
 * 
 * NOTE: THIS CLASS IS VERY MUCH SUBJECT TO CHANGE!
 **/

///import libraries
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;

public class Button extends JButton
{
	/**
	 * One of the constructors, responsible for setting up the Button
	 * instance based on the parameters given. In this case, the superclass
	 * constructor is called, and the ActionListener is added.
	 **/
	public Button(String text, ActionListener buttonListener)
	{
		super(text); ///superclass constructor call
		addActionListener(buttonListener); ///adds the ActionListener
	}
	
	/**
	 * One of the constructors, responsible for setting up the Button instance
	 * based on the parameters given. In this case, the superclass constructor
	 * is called, the ActionListener is added, and the font is configured
	 * based on the font size passed in.
	 **/
	public Button(String text, ActionListener buttonListener, int fontSize)
	{
		super(text); ///superclass constructor call
		setFont(new Font("SansSerif", Font.BOLD, fontSize)); ///font is configured
		addActionListener(buttonListener); ///adds the ActionListener
	}
	
	/**
	 * One of the constructors, responsible for setting up the Button
	 * instance based on the parameters given. In this case, the superclass
	 * constructor is called, the ActionListener is added, and the font is
	 * set to the Font passed in.
	 **/
	public Button(String text, ActionListener buttonListener, Font buttonFont)
	{
		super(text); ///superclass constructor call
		setFont(buttonFont); ///font is configured
		addActionListener(buttonListener); ///adds the ActionListener
	}
}
