/**
 * Krish Kumar
 * Period 6
 * OptionsPopup.java
 * 
 * This class is the "Options" popup, where the user can configure
 * different settings.
 **/

///import libraries
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class OptionsPopup extends Popup
{
	public OptionsPopup()
	{
		super("Options");
	}

	public JPanel getContentPanel()
	{
		JPanel toReturn = super.getContentPanel();
		OptionsPopupContent newContent = new OptionsPopupContent();

		toReturn.add(newContent, BorderLayout.CENTER);

		return toReturn;
	}

	class OptionsPopupContent extends JPanel
	{
		public OptionsPopupContent()
		{
			setLayout(new BorderLayout());
			
			JPanel titleHolder = getTitle();
			///put in config code here

			add(titleHolder, BorderLayout.NORTH);
		}

		/**
		 * This method is responsible for setting up the title of the
		 * popup, as well as its holder.
		 **/
		public JPanel getTitle()
		{
			JPanel toReturn = new JPanel(); ///creates new JPanel for holder
			Label titleLabel = new Label(title, 30); ///creates new Label
			
			toReturn.setBackground(Color.LIGHT_GRAY);
			toReturn.add(titleLabel); ///adds button to holder
			
			return toReturn;
		}

		public JScrollPane getConfigurationPane()
		{
			JPanel configPanel = getConfigPanel();
			JScrollPane toReturn = new JScrollPane(configPanel);

			return toReturn;
		}

		public JPanel getConfigPanel()
		{
			JPanel toReturn = new JPanel(new GridLayout(1,1)); ///will change rows later

			return toReturn;
		}
	}
}
