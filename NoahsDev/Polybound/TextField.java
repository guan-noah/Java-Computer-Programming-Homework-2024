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
	/* //not learned in class 
	public TextField(String defaultTextIn)
	{
		int cols = defaultTextIn.length() + 5;
		int fontSize = 20
		this(defaultTextIn, cols, fontSize);
	}*/
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
            setForeground(Color.BLACK);
            setFont(SELECTED);
            setText("");
        }

        public void focusLost(FocusEvent evt)
        {
            setForeground(Color.GRAY);
            setFont(UNSELECTED);
            setText(defaultText);
        }
    }
}
