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

import java.awt.Font;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Button extends JButton
{
	private boolean isHoveredOver;

	/**
	 * One of the constructors, responsible for setting up the Button
	 * instance based on the parameters given. In this case, the superclass
	 * constructor is called, and the ActionListener is added.
	 **/
	public Button(String text, ActionListener buttonListener)
	{
		super(text); ///superclass constructor call
		addActionListener(buttonListener); ///adds the ActionListener
		setFocusPainted(false);

		isHoveredOver = false;
		ButtonHandler btnHandler = new ButtonHandler();
		addMouseListener(btnHandler);
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
		setFocusPainted(false);

		isHoveredOver = false;
		ButtonHandler btnHandler = new ButtonHandler();
		addMouseListener(btnHandler);
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
		setFocusPainted(false);

		//set hovered over to false, initialize button handler
		isHoveredOver = false;
		ButtonHandler btnHandler = new ButtonHandler();
		addMouseListener(btnHandler);
	}
	/*
	 * paint component method. called by repaint()
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;//create graphics 
		
		//if user hovered over 
		if(isHoveredOver)
		{
			g2d.setStroke(new BasicStroke(10));
			g2d.setColor(Color.YELLOW);
			g2d.drawRect(0, 0, getWidth(), getHeight());
		}
		
		g2d.dispose();
	}
	/*
	 * class determines if hovered over. that's all it does 
	 */
	class ButtonHandler implements MouseListener
	{
		/*
		 * if mouse enters component 
		 */
		public void mouseEntered(MouseEvent evt)
		{
			isHoveredOver = true;
			repaint();
		}

		/*
		 * if mouse exits component 
		 */
		public void mouseExited(MouseEvent evt)
		{
			isHoveredOver = false;
			repaint();
		}
		/*
		 * empty methods to finish implementation 
		 */
		public void mousePressed(MouseEvent evt)
		{}
		public void mouseClicked(MouseEvent evt)
		{}
		public void mouseReleased(MouseEvent evt)
		{}
	}
}
