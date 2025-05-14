/**
 * Krish Kumar
 * Period 6
 * TextField.java
 * 
 * This class extends from JTextField. The purpose of it is to allow for a
 * much simpler implementation of text fields and their FocusListeners
 * within the game.
 * 
 * NOTE: THIS CLASS IS VERY MUCH SUBJECT TO CHANGE!
 **/

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class TextField extends JTextField
{
    private final Font SELECTED;
    private final Font UNSELECTED;
    private String defaultText;

    public TextField(String defaultTextIn, int cols, int fontSize)
    {
        super(defaultTextIn, cols);
        defaultText = defaultTextIn;

        SELECTED = new Font("SansSerif", Font.BOLD, fontSize);
        UNSELECTED = new Font("SansSerif", Font.ITALIC, fontSize);

        setForeground(Color.GRAY);
        setFont(UNSELECTED);

        TextFieldFocusHandler tfFocusHandler = new TextFieldFocusHandler();
        addFocusListener(tfFocusHandler);
    }
	
    class TextFieldFocusHandler implements FocusListener
    {
        public void focusGained(FocusEvent evt)
        {
			String existingText = getText();
			if(existingText.equals("")||existingText.equals(defaultText))
			{
				setForeground(Color.BLACK);
				setFont(SELECTED);
				setText("");
			}
        }

        public void focusLost(FocusEvent evt)
        {
			String existingText = getText();
			if(existingText.equals("")||existingText.equals(defaultText))
			{
				setForeground(Color.GRAY);
				setFont(UNSELECTED);
				setText(defaultText);
			}
			//else don't reset text because the user actually inputted something
        }
    }
}
