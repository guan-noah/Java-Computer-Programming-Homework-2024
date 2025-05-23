/**
 * Krish Kumar
 * Period 6
 * Popup.java
 * 
 * A generic framework for a popup. This class is intended to
 * be extended from.
 **/
//imports 
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Popup
{
	protected JFrame popupFrame; ///popup frame
	protected JPanel content; ///popup content
	protected String title; ///popup title
	
	public Popup(String titleIn)
	{
		title = titleIn;
		
		popupFrame = getFrame();//create the frame 
		content = getContentPanel();//create the information in the frame
		
		popupFrame.setContentPane(content);//fill the frame
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
	/*
	 * This method creates the information in frame. 
	 * It makes a BorderLayout JPanel, which holds the close button at the top. 
	 */
	public JPanel getContentPanel()
	{
		JPanel toReturn = new JPanel(new BorderLayout());
		JPanel closeBtnHolder = getCloseBtn();
		
		//add close button to jpanel 
		toReturn.add(closeBtnHolder, BorderLayout.NORTH);

		return toReturn;
	}

	/**
	 * This method is responsible for setting up the close button
	 * of the popup, as well as its holder and handler.
	 **/
	public JPanel getCloseBtn()
	{
		///creates new Button, JPanel to hold it, and CloseButtonHandler
		JPanel toReturn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		CloseButtonHandler closeBtnHandler = new CloseButtonHandler();
		Button goBack = new Button("X", closeBtnHandler, 25);
		
		toReturn.setBackground(Color.LIGHT_GRAY);
		toReturn.add(goBack); ///adds button to holder
			
		return toReturn;
	}
	
	/**
	 * This method is responsible for showing the popup.
	 **/
	public void show()
	{
		popupFrame.setVisible(true); ///makes frame visible
	}
	
	/**
	 * This class is responsible for handling the closing of the
	 * popup when the close button is pressed.
	 **/
	class CloseButtonHandler implements ActionListener
	{
		/**
		 * This method is called when the close popup button is clicked,
		 * and is responsible for hiding the popup.
		 **/
		public void actionPerformed(ActionEvent evt)
		{
			popupFrame.setVisible(false); ///hides popup
		}
	}
}
