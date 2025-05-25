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

///import libraries
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
		private JCheckBox likedGame;

		public OptionsPopupContent()
		{
			setLayout(new BorderLayout());
			
			JPanel titleHolder = getTitle();
			JScrollPane configHolder = getConfigurationPane();

			add(titleHolder, BorderLayout.NORTH);
			add(configHolder, BorderLayout.CENTER);
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
			JPanel toReturn = new JPanel(new GridLayout(2, 1)); ///will change rows later
			OptionHandler optionHandler = new OptionHandler();
			Button wipeHighScores = new Button("Clear all high scores [CANNOT BE UNDONE]",
				optionHandler, 25);
			likedGame = getCheckBox("I like this game");

			likedGame.addActionListener(optionHandler);

			toReturn.setBackground(Color.GRAY);
			toReturn.add(likedGame);
			toReturn.add(wipeHighScores);

			return toReturn;
		}

		public JCheckBox getCheckBox(String content)
		{
			JCheckBox toReturn = new JCheckBox(content);

			toReturn.setFont(new Font("Oswald Regular", Font.BOLD, 25));

			return toReturn;
		}

		class OptionHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				String command = evt.getActionCommand();

				if(command.equals("I like this game"))
				{
					///this gives the player +5 hp for liking the game :)
					GameData.gameLiked(likedGame.isSelected());
				}
				else if(command.equals("Clear all high scores [CANNOT BE UNDONE]"))
				{
					GameData.clearHighScores(); ///clears high scores
				}
			}
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
    private int[] hpPoints;
    private int[] manaPoints;
    private int[] defPoints;
    private UpgradePopupContent contentPanel;

    public UpgradePopup()
    {
        super("Upgrade");
        pointsLeft = 0;
		hpPoints = new int[3];
		manaPoints = new int[3];
		defPoints = new int[3];
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
        pointsLeft += 9;
        for(int i=0; i<hpPoints.length; i++)
		{
			hpPoints[i] = 0;
			manaPoints[i] = 0;
			defPoints[i] = 0;
		}
        contentPanel.updatePointLabel();
    }

    class UpgradePopupContent extends JPanel
    {
        private Label pointsLabel;
		private String[] charNames;

        public UpgradePopupContent()
        {
            setLayout(new BorderLayout());
			charNames = new String[] {"Line", "Quadratic", "Cubic"};

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
            JPanel toReturn = new JPanel(new GridLayout(6, 3));
            PointAllocationHandler pointHandler = new PointAllocationHandler();

			toReturn.setBackground(Color.LIGHT_GRAY);
			for(int i=0; i<charNames.length; i++)
			{
				Button addHP = new Button(charNames[i] + ": +5 HP [2pts]", pointHandler, 12);
				Button addMana = new Button(charNames[i] + ": +3 Mana [1pt]", pointHandler, 12);
				Button addDef = new Button(charNames[i] + ": +1 Defense [3pts]", pointHandler, 12);
				Button removeHP = new Button(charNames[i] + ": -5 HP [returns 2pts]", pointHandler, 12);
				Button removeMana = new Button(charNames[i] + ": -3 Mana [returns 1pt]", pointHandler, 12);
				Button removeDef = new Button(charNames[i] + ": -1 Defense [returns 3pts]", pointHandler, 12);

				toReturn.add(addHP);
				toReturn.add(addMana);
				toReturn.add(addDef);
				toReturn.add(removeHP);
				toReturn.add(removeMana);
				toReturn.add(removeDef);
			}

            return toReturn;
        }

        class PointAllocationHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                String command = evt.getActionCommand();

				for(int i=0; i<charNames.length; i++)
				{
					int colonIndex = command.indexOf(":");
					String charName = command.substring(0, colonIndex);

					if(charNames[i].equals(charName))
					{
						if(command.indexOf("HP") != -1)
						{
							if(command.indexOf("+") != -1)
							{
								if(pointsLeft > 1)
								{
									hpPoints[i]++;
									pointsLeft-=2;
								}
							}
							else
							{
								hpPoints[i]--;
								if(hpPoints[i] < 0)
								{
									hpPoints[i] = 0;
								}
								else
								{
									pointsLeft+=2;
								}
							}
						}
						else if(command.indexOf("Mana") != -1)
						{
							if(command.indexOf("+") != -1)
							{
								if(pointsLeft > 0)
								{
									manaPoints[i]++;
									pointsLeft--;
								}
							}
							else
							{
								manaPoints[i]--;
								if(manaPoints[i] < 0)
								{
									manaPoints[i] = 0;
								}
								else
								{
									pointsLeft++;
								}
							}
						}
						else
						{
							if(command.indexOf("+") != -1)
							{
								if(pointsLeft > 2)
								{
									defPoints[i]++;
									pointsLeft-=3;
								}
							}
							else
							{
								defPoints[i]--;
								if(defPoints[i] < 0)
								{
									defPoints[i] = 0;
								}
								else
								{
									pointsLeft+=3;
								}
							}
						}
					}
				}

                updatePointLabel();
            }
        }

        class ConfirmationHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                Character[] playerChars = GameData.getPlayerChars();
				for(int i=0; i<playerChars.length; i++)
				{
					playerChars[i].increaseMaxHP(5*hpPoints[i]);
					playerChars[i].increaseMaxMana(3*manaPoints[i]);
					playerChars[i].increaseDefense(defPoints[i]);
				}

                popupFrame.setVisible(false);
                GameData.refreshStats();
                GameData.writeData(true); ///writes data
            }
        }
    }
}