/**
 * Krish Kumar
 * Period 6
 * InfoPopup.java
 * 
 * This class is a framework for an "info popup", like the "Help" or
 * "High Scores" popups. It contains a title label, as well as
 * a text area for its content.
 **/

///import libraries
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.BorderLayout;

public class InfoPopup extends Popup
{
	private InfoPopupContent infoContent; ///allows access to the JTextArea

	/**
	 * The constructor, responsible for setting up the instance of
	 * InfoPopup. Specifically, the superclass' constructor is called
	 * for initialization.
	 **/
	public InfoPopup(String titleIn)
	{
		super(titleIn);
	}

	/**
	 * This method is responsible for creating the content
	 * panel, which will hold the title label, as well as
	 * the content text area.
	 */
	public JPanel getContentPanel()
	{
		JPanel toReturn = super.getContentPanel();
		infoContent = new InfoPopupContent();
		
		toReturn.add(infoContent, BorderLayout.CENTER);

		return toReturn;
	}
	
	/**
	 * This method is responsible for requesting that the text of
	 * the content JTextArea is changed.
	 **/
	public void setContent(String contentIn)
	{
		InfoPopupContent contentPanel = (InfoPopupContent) infoContent; ///gets text area panel
		contentPanel.setContent(contentIn); ///requests change of text
	}
	
	/**
	 * This class is the JPanel that stores the actual content of the
	 * popup.
	 **/
	class InfoPopupContent extends JPanel
	{
		private JTextArea contentTextArea; ///the content text area
		
		/**
		 * The default constructor, responsible for setting up the
		 * instance of InfoPopupContent. Specifically, the layout is set to
		 * a BorderLayout, and the components that make up the content
		 * are created/added.
		 **/		
		public InfoPopupContent()
		{
			setLayout(new BorderLayout()); ///sets layout to BorderLayout
			
			JPanel titlePanel = getTitle(); ///gets title of the popup
			JScrollPane contentPane = getScrollPane(); ///gets text area
			
			///adds components to panel
			add(titlePanel, BorderLayout.NORTH);
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
			contentTextArea.setFont(new Font("SansSerif", Font.PLAIN, 25));
			contentTextArea.setLineWrap(true);
			contentTextArea.setWrapStyleWord(true);
			contentTextArea.setEditable(false);
			
			return toReturn;
		}
		
		/**
		 * This method is responsible for setting up the title of the
		 * popup, as well as its holder.
		 **/
		public JPanel getTitle()
		{
			JPanel toReturn = new JPanel(); ///creates new JPanel for holder
			Label titleLabel = new Label(title, 30); ///creates new Label
			
			toReturn.add(titleLabel); ///adds button to holder
			
			return toReturn;
		}
	}
}
