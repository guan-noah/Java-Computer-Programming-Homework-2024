/**
 * Krish Kumar
 * Period 6
 * Popup.java
 * 
 * A generic framework for a popup. This class is intended to
 * be extended from.
 **/

/**
 * Krish Kumar
 * Period 6
 * OptionsPopup.java
 * 
 * This class is the "Options" popup, where the user can configure
 * different settings.
 **/

/**
 * Krish Kumar
 * Period 6
 * InfoPopup.java
 * 
 * This class is a framework for an "info popup", like the "Help" or
 * "High Scores" popups. It contains a title label, as well as
 * a text area for its content.
 **/

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

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
		
		popupFrame = getFrame();
		content = getContentPanel();
		
		popupFrame.setContentPane(content);
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
	
	public JPanel getContentPanel()
	{
		JPanel toReturn = new JPanel(new BorderLayout());
		JPanel closeBtnHolder = getCloseBtn();

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
class InfoPopup extends Popup
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
		infoContent.setContent(contentIn); ///requests change of text
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
			contentTextArea.setFont(new Font("Share Tech Regular", Font.PLAIN, 25));
			contentTextArea.setBackground(Color.DARK_GRAY);
			contentTextArea.setForeground(Color.WHITE);
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
			Label titleLabel = new Label(title, 35); ///creates new Label
			
			toReturn.setBackground(Color.LIGHT_GRAY);
			toReturn.add(titleLabel); ///adds button to holder
			
			return toReturn;
		}
	}
}
class OptionsPopup extends Popup
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
			Label titleLabel = new Label(title, 35); ///creates new Label
			
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
class GameFoundPopup extends Popup
{
    public GameFoundPopup()
    {
        super("Ongoing Game Found!");
    }

    public JPanel getContentPanel()
    {
        JPanel toReturn = super.getContentPanel();
		GameFoundPopupContent newContent = new GameFoundPopupContent();

		toReturn.add(newContent, BorderLayout.CENTER);

		return toReturn;
    }

    class GameFoundPopupContent extends JPanel
    {
        public GameFoundPopupContent()
        {
            setLayout(new FlowLayout(FlowLayout.CENTER, 30, 150));

            OptionHandler optionHandler = new OptionHandler();
            Button yes = new Button("Continue", optionHandler, 40);
            Button no = new Button("New Game", optionHandler, 40);

            setBackground(Color.LIGHT_GRAY);

            add(yes);
            add(no);
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            g.setFont(new Font("Share Tech Regular", Font.BOLD, 55));
            g.drawString(title, 50, 50);
            g.drawString("Continue?", 190, 100);
        }

        class OptionHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                String command = evt.getActionCommand();

                if(command.equals("Continue"))
                {
					GameData.getSaves();
                }
                else if(command.equals("New Game"))
                {
                    GameData.switchCard("user info");
                }

                popupFrame.setVisible(false);
            }
        }
    }
}
class UpgradePopup extends Popup
{
    private int pointsLeft;
    private int hpPoints;
    private int manaPoints;
    private int defPoints;
    private UpgradePopupContent contentPanel;

    public UpgradePopup()
    {
        super("Upgrade");
        pointsLeft = 0;
    }

    public JPanel getContentPanel()
	{
		JPanel toReturn = super.getContentPanel();
		contentPanel = new UpgradePopupContent();
		
		toReturn.add(contentPanel, BorderLayout.CENTER);

		return toReturn;
	}

    public void resetPoints()
    {
        pointsLeft += 3;
        manaPoints = 0;
        hpPoints = 0;
        defPoints = 0;
        contentPanel.updatePointLabel();
    }

    class UpgradePopupContent extends JPanel
    {
        private Label pointsLabel;

        public UpgradePopupContent()
        {
            setLayout(new BorderLayout());

            JPanel titlePanel = getTitle();
            JPanel pointsPanel = getPointAllocationButtons();
            JPanel confirmPanel = getConfirmButton();

            add(titlePanel, BorderLayout.NORTH);
            add(confirmPanel, BorderLayout.SOUTH);
            add(pointsPanel, BorderLayout.CENTER);
        }

        public void updatePointLabel()
        {
            pointsLabel.setText("Points Left: " + pointsLeft);
        }

        public JPanel getConfirmButton()
        {
            JPanel toReturn = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
            ConfirmationHandler confirmHandler = new ConfirmationHandler();
            Button confirmButton = new Button("Confirm", confirmHandler, 35);
            
            toReturn.setBackground(Color.DARK_GRAY);
            toReturn.add(confirmButton);
            
            return toReturn;
        }

        public JPanel getTitle()
        {
            JPanel toReturn = new JPanel(); ///creates new JPanel for holder
		    pointsLabel = new Label("Points Left: " + pointsLeft, 35); ///creates new Label
			
			toReturn.setBackground(Color.LIGHT_GRAY);
			toReturn.add(pointsLabel); ///adds button to holder
			
			return toReturn;
        }

        public JPanel getPointAllocationButtons()
        {
            JPanel toReturn = new JPanel(new GridLayout(3, 2));
            PointAllocationHandler pointHandler = new PointAllocationHandler();
            Button addHP = new Button("+ HP Point", pointHandler, 35);
            Button removeHP = new Button("- HP Point", pointHandler, 35);
            Button addMana = new Button("+ Mana Point", pointHandler, 35);
            Button removeMana = new Button("- Mana Point", pointHandler, 35);
            Button addDefense = new Button("+ Defense Point", pointHandler, 35);
            Button removeDefense = new Button("- Defense Point", pointHandler, 35);

            toReturn.setBackground(Color.LIGHT_GRAY);
            toReturn.add(addHP);
            toReturn.add(removeHP);
            toReturn.add(addMana);
            toReturn.add(removeMana);
            toReturn.add(addDefense);
            toReturn.add(removeDefense);

            return toReturn;
        }

        class PointAllocationHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                String command = evt.getActionCommand();

                if(command.equals("+ HP Point"))
                {
                    if(pointsLeft > 0)
                    {
                        hpPoints++;
                        pointsLeft--;
                    }
                }
                else if(command.equals("- HP Point"))
                {
                    hpPoints--;
                    if(hpPoints < 0)
                    {
                        hpPoints = 0;
                    }
                    else
                    {
                        pointsLeft++;
                    }
                }
                else if(command.equals("+ Mana Point"))
                {
                    if(pointsLeft > 0)
                    {
                        manaPoints++;
                        pointsLeft--;
                    }
                }
                else if(command.equals("- Mana Point"))
                {
                    manaPoints--;
                    if(manaPoints < 0)
                    {
                        manaPoints = 0;
                    }
                    else
                    {
                        pointsLeft++;
                    }
                }
                else if(command.equals("+ Defense Point"))
                {
                    if(pointsLeft > 2)
                    {
                        defPoints++;
                        pointsLeft-=3;
                    }
                }
                else if(command.equals("- Defense Point"))
                {
                    defPoints--;
                    if(defPoints < 0)
                    {
                        defPoints = 0;
                    }
                    else
                    {
                        pointsLeft+=3;
                    }
                }

                updatePointLabel();
            }
        }

        class ConfirmationHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                Character player = GameData.getPlayerCharacter();
                player.increaseMaxHP(hpPoints*5);
                player.increaseMaxMana(manaPoints*3);
                player.increaseDefense(defPoints);
                
                System.out.println(player.getMaxHP());
                System.out.println(player.getMaxMana());

                popupFrame.setVisible(false);
                GameData.refreshStats();
                GameData.writeData(true); ///writes data
            }
        }
    }
}