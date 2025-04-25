/**
 * Krish Kumar
 * Period 6
 * InfoPopup.java
 * 
 * This class is a framework for an "info popup", like the "Help" or
 * "High Scores" popups. It contains a title label, a text area for content,
 * and a button to close the popup.
 **/

///import libraries
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoPopup
{
	private JFrame popupFrame; ///frame to contain the popup
	private PopupContent content; ///panel with popup content
	private String title; ///title of the popup
	
	/**
	 * The constructor, responsible for setting up the instance of
	 * InfoPopup. Specifically, the passed in popup title is stored,
	 * and the popup frame/panel are set up.
	 **/
	public InfoPopup(String titleIn)
	{
		title = titleIn; ///stores popup title in a field
		
		popupFrame = getFrame(); ///gets the popup frame
		content = new PopupContent(); ///creates new PopupContent
		
		popupFrame.add(content); ///adds content to frame
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
	
	/**
	 * This method is responsible for showing the popup.
	 **/
	public void show()
	{
		popupFrame.setVisible(true); ///makes frame visible
	}
	
	/**
	 * This method is responsible for requesting that the text of
	 * the content JTextArea is changed.
	 **/
	public void setContent(String contentIn)
	{
		content.setContent(contentIn); ///requests change of text
	}
	
	/**
	 * This class is the JPanel that stores the actual content of the
	 * popup.
	 **/
	class PopupContent extends JPanel
	{
		private JTextArea contentTextArea; ///the content text area
		
		/**
		 * The default constructor, responsible for setting up the
		 * instance of PopupContent. Specifically, the layout is set to
		 * a BorderLayout, and the components that make up the content
		 * are created/added.
		 **/		
		public PopupContent()
		{
			setLayout(new BorderLayout()); ///sets layout to BorderLayout
			
			JPanel topPanel = getTop(); ///gets top of the popup
			JScrollPane contentPane = getScrollPane(); ///gets text area
			
			///adds components to panel
			add(topPanel, BorderLayout.NORTH);
			add(contentPane, BorderLayout.CENTER);
		}
		
		/**
		 * This method is responsible for changing the content of the
		 * content text area.
		 **/
		public void setContent(String contentIn)
		{
			contentTextArea.setText(contentIn); ///changes the text
		}
		
		/**
		 * This method is responsible for not only the setup of the
		 * content JTextArea, but also the JScrollPane that will encase
		 * it.
		 **/
		public JScrollPane getScrollPane()
		{
			///creates new JTextArea and JScrollPane
			contentTextArea = new JTextArea();
			JScrollPane toReturn = new JScrollPane(contentTextArea);
			
			///text area setup
			contentTextArea.setLineWrap(true);
			contentTextArea.setWrapStyleWord(true);
			contentTextArea.setEditable(false);
			
			return toReturn;
		}
		
		/**
		 * This method is responsible for the setup of the top of the
		 * popup. (close button and title)
		 **/
		public JPanel getTop()
		{
			///creates new JPanel to hold top content holders
			JPanel toReturn = new JPanel(new GridLayout(2, 1));
			
			///creates new JPanels to hold top content
			JPanel goBackHolder = getGoBackBtn();
			JPanel titleHolder = getTitle();
			
			///adds holders to top content panel
			toReturn.add(goBackHolder);
			toReturn.add(titleHolder);
			
			return toReturn;
		}
		
		/**
		 * This method is responsible for setting up the close button
		 * of the popup, as well as its holder and handler.
		 **/
		public JPanel getGoBackBtn()
		{
			///creates new Button, JPanel to hold it, and GoBackButtonHandler
			JPanel toReturn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
			GoBackButtonHandler goBackBtnHandler = new GoBackButtonHandler();
			Button goBack = new Button("X", goBackBtnHandler, 25);
			
			toReturn.add(goBack); ///adds button to holder
			
			return toReturn;
		}
		
		/**
		 * This method is responsible for setting up the title of the
		 * popup, as well as its holder.
		 **/
		public JPanel getTitle()
		{
			JPanel toReturn = new JPanel(); ///creates new JPanel for holder
			Label titleLabel = new Label(title, 30); ///creates new Button
			
			toReturn.add(titleLabel); ///adds button to holder
			
			return toReturn;
		}
	}
	
	/**
	 * This class is responsible for handling the closing of the
	 * popup when the close button is pressed.
	 **/
	class GoBackButtonHandler implements ActionListener
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
