//Note: This file mainly contains 3 different but related classes. 
//Main class descriptions here. 
/*
 * Krish Kumar
 * Period 6
 * Popup.java
 * 
 * A generic framework for a popup. This class is intended to
 * be a framework that other classes extend from.
 */

/*
 * Krish Kumar
 * Period 6
 * OptionsPopup.java
 * 
 * This class is the "Options" popup, where the user can configure
 * different settings.
 * save until other game functions are done. 
 */

/**
 * Krish Kumar
 * Period 6
 * InfoPopup.java
 * 
 * This class is a framework for an "info popup", like the "Help" or
 * "High Scores" popups. It contains a title label, as well as
 * a text area for its content.
 **/
//shared imports 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;//with textarea, for info popup 
import javax.swing.JTextArea;
import javax.swing.JCheckBox;//for options popup 

import java.awt.BorderLayout;//layouts 
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Graphics;//graphics
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionListener;//listener/handlers 
import java.awt.event.ActionEvent;

public class Popup
{
	protected JFrame popupFrame; ///popup frame
	protected JPanel content; ///popup content
	protected String title; ///popup title
	
	public Popup(String titleIn)
	{
		title = titleIn;//initialize title to parameter input 
		
		//create frame and its information 
		popupFrame = getFrame();
		content = getContentPanel();
		
		//fill the frame 
		popupFrame.setContentPane(content);
		popupFrame.setResizable(false);//user cannot edit frame size 
	}
	
	/*
	 * This method is responsible for setting up the JFrame that will
	 * be the actual popup.
	 * Can be overridden to make a completely new JFrame with different properties. 
	 */
	public JFrame getFrame()
	{
		JFrame toReturn = new JFrame(title); ///creates new JFrame
		
		///frame setup
		toReturn.setSize(600, 500);//frame size 
		toReturn.setLocationRelativeTo(null);//popup in center of screen 
		
		return toReturn;
	}
	
	/*
	 * This method is responsible for setting up the panel content. 
	 * Intended to be overridden to make completely new panel content 
	 * with different properties. 
	 */
	public JPanel getContentPanel()
	{
		JPanel toReturn = new JPanel(new BorderLayout());//make a new jpanel with border layout 
		
		//add a close button jpanel holder at the top 
		JPanel closeBtnHolder = getCloseBtn();
		toReturn.add(closeBtnHolder, BorderLayout.NORTH);

		return toReturn;
	}

	/*
	 * This method is responsible for setting up the close button
	 * of the popup, as well as its holder and handler.
	 * Holder JPanel centers the button to the far right. 
	 * 
	 * Workshopped Idea: Make a JMenu, only initialized if there is more 
	 * than 1 card in a CardLayout so each popup can show different panels. 
	 */
	public JPanel getCloseBtn()
	{
		///creates new Button, JPanel to hold it, and CloseButtonHandler
		JPanel toReturn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		CloseButtonHandler closeBtnHandler = new CloseButtonHandler();
		Button goBack = new Button("X", closeBtnHandler, 25);
		
		toReturn.setBackground(Color.LIGHT_GRAY);//set header background to light gray 
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
			popupFrame.setVisible(false); //HIDES popup, NOT disposes of it. 
				//This is very important! This means it just shows the SAME popup again, 
				//retaining any fv information it might have. 
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
		super(titleIn);//calls Popup constructor with title 
		//gets all Popup benefits. 
	}

	/*
	 * This method is responsible for creating the content
	 * panel, which will hold the title label, as well as
	 * the content text area.
	 * Overrides Popup getContentPanel method; at runtime, Java calls 
	 * this method instead of the 
	 */
	public JPanel getContentPanel()
	{
		//call Popup's getContentPanel method and store in this JPanel to return 
		JPanel toReturn = super.getContentPanel();
		//add specializes content to center 
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
	
	/*
	 * This class is the JPanel that stores the actual content of the
	 * popup (that differentiates itself from other popups and holds its function).
	 * In InfoPopupContent, it's simply a JTextArea. 
	 */
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
			contentTextArea.setEditable(false);//user cannot edit text area
			
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
/* same logic as InfoPopup. Read InfoPopup logic first before reading 
 * these comments (as it's essentially the same thing to set up the popup)
 * and these repetitive comments will be more concise than InfoPopup logic moving forward. 
 * 
 * P.S. This specific class, OptionsPopup, will be put on hold until everything else is finished. 
 */
class OptionsPopup extends Popup
{
	/*
	 * call the Popup superclass constructor 
	 */
	public OptionsPopup()
	{
		super("Options");
	}
	
	/*
	 * overrides superclass method 
	 */
	public JPanel getContentPanel()
	{
		JPanel toReturn = super.getContentPanel();//calls superclass method
		//initializes content specific to this popup to center 
		OptionsPopupContent newContent = new OptionsPopupContent();
		toReturn.add(newContent, BorderLayout.CENTER);

		return toReturn;
	}
	
	/*
	 * main popup content in center 
	 */
	class OptionsPopupContent extends JPanel
	{
		/*
		 * constructor - initializes panel 
		 */
		public OptionsPopupContent()
		{
			setLayout(new BorderLayout());
			
			JPanel titleHolder = getTitle();
			///put in config code here
			//maybe for now, a Polybound II advertisement as a placeholder 
			
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
		
		/*
		 * returns JScrollPane with gridlayout of configuration components. 
		 * Calls getConfigPanel to execute this. 
		 */
		public JScrollPane getConfigurationPane()
		{
			JPanel configPanel = getConfigPanel();
			JScrollPane toReturn = new JScrollPane(configPanel);

			return toReturn;
		}
		/*
		 * Helper method to getConfigurationPanel(), which adds a JScrollPane to this panel. 
		 * Can only be called by this class. 
		 * A gridlayout of configuration components for the user. 
		 */
		private JPanel getConfigPanel()
		{
			JPanel toReturn = new JPanel(new GridLayout(1,1)); ///will change rows later
			//~ toReturn.setBackground(Color.LIGHT_GRAY);
			toReturn.setBackground(Color.BLACK);
			
			return toReturn;
		}
	}
}
class GameFoundPopup extends Popup
{
	/*
	 * calls superclass constructor with title 
	 */
    public GameFoundPopup()
    {
        super("Ongoing Game Found!");
    }

	/*
	 * initializes main popup content to center 
	 */
    public JPanel getContentPanel()
    {
        JPanel toReturn = super.getContentPanel();//superclass' content panel with close button panel 
        //initialize and add specific content to center
		GameFoundPopupContent newContent = new GameFoundPopupContent();
		toReturn.add(newContent, BorderLayout.CENTER);

		return toReturn;
    }

	/*
	 * main popup content in center. 
	 */
    class GameFoundPopupContent extends JPanel
    {
		/*
		 * constructor to initialize JPanel and components in panel. 
		 */
        public GameFoundPopupContent()
        {
			//flowlayout 
            setLayout(new FlowLayout(FlowLayout.CENTER, 30, 150));
			
			//initialize buttons with optionhandler 
            OptionHandler optionHandler = new OptionHandler();
            Button yes = new Button("Continue", optionHandler, 40);
            Button no = new Button("New Game", optionHandler, 40);
			
			//light gray background
            setBackground(Color.LIGHT_GRAY);
			
			//add buttons to panel 
            add(yes);
            add(no);
        }

		/*
		 * paintComponent method. called by repaint() and draws title 
		 * and "Continue?" prompt. 
		 */
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
			
			//set font 
            g.setFont(new Font("Share Tech Regular", Font.BOLD, 55));
            g.drawString(title, 50, 50);//draw title 
            g.drawString("Continue?", 190, 100);//draw continue prompt 
        }
		
		/*
		 * Option handler for buttons 
		 */
        class OptionHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                String command = evt.getActionCommand();

                if(command.equals("Continue"))
                {
					//get current save for user to continue from (call GameData method) 
					GameData.getSaves();
                }
                else if(command.equals("New Game"))
                {
					//regather user information for a new game (call GameData method)
                    GameData.switchCard("user info");
                }
				
				//hide popup 
                popupFrame.setVisible(false);
            }
        }
    }
}
class UpgradePopup extends Popup
{
    private int pointsLeft;//how many points the user has left to allocate 
    private int[] hpPoints;//hp upgrades 
    private int[] manaPoints;//mana upgrades 
    private int[] defPoints;//defense upgrades 
    private UpgradePopupContent contentPanel;
	
	/*
	 * call superclass constructor and initialize "temporary" hp, mana, and 
	 * defense point selections 
	 * "temporary" in the sense that they aren't committed to GameData yet. 
	 * the class will filter user actions and ensure they don't give themselves 
	 * overpowered stats, then commit to GameData. 
	 */
    public UpgradePopup()
    {
        super("Upgrade");
        //initialize upgrade point values 
        pointsLeft = 0;
		hpPoints = new int[3];//3 characters, so needs 3 
		manaPoints = new int[3];
		defPoints = new int[3];
    }

	/*
	 * override superclass contentpanel and add content 
	 */
    public JPanel getContentPanel()
	{
		JPanel toReturn = super.getContentPanel();
		contentPanel = new UpgradePopupContent();
		
		toReturn.add(contentPanel, BorderLayout.CENTER);

		return toReturn;
	}

	/*
	 * reset fvs and all point values (except for pointsLeft)
	 */
    public void resetPoints()
    {
        pointsLeft += 9;//ADD 9 to points left, not set 9 to points left 
			//this is to keep the previous points a user may have saved 
        //in each character, reset 
        for(int i=0; i<hpPoints.length; i++)
		{
			hpPoints[i] = 0;
			manaPoints[i] = 0;
			defPoints[i] = 0;
		}
		//update point label 
        contentPanel.updatePointLabel();
    }

	/*
	 * upgrade popup content class. contains the components and selection
	 * for user to allocate points toward their stats. 
	 */
    class UpgradePopupContent extends JPanel
    {
        private Label pointsLabel;
		private String[] charNames;

		/*
		 * constructor sets up the panel and defines charNames as the 3 
		 * user character names. 
		 */
        public UpgradePopupContent()
        {
            setLayout(new BorderLayout());//borderlayout jpanel 
			charNames = new String[] {"Line", "Quadratic", "Cubic"};
			
			//initialize JPanels with respective methods 
            JPanel titlePanel = getTitle();
            JPanel pointsPanel = getPointAllocationButtons();
            JPanel confirmPanel = getConfirmButton();
			
			//add JPanels to respective locations 
            add(titlePanel, BorderLayout.NORTH);//title at top
            add(confirmPanel, BorderLayout.SOUTH);//confirmation at bottom
            add(pointsPanel, BorderLayout.CENTER);//selection and allocation in middle 
        }

		/*
		 * update the point label so user knows how many upgrade points 
		 * they have left to spend. 
		 */
        public void updatePointLabel()
        {
            pointsLabel.setText("Points Left: " + pointsLeft);
        }

		/*
		 * returns the confirmation button that user presses to continue. 
		 */
        public JPanel getConfirmButton()
        {
			//initializes JPanel with flowlayout 
            JPanel toReturn = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
            //initializes confirm button with handler 
            ConfirmationHandler confirmHandler = new ConfirmationHandler();
            Button confirmButton = new Button("Confirm", confirmHandler, 35);
            
            toReturn.setBackground(Color.DARK_GRAY);//set background to dark gray 
            toReturn.add(confirmButton);//add button to panel 
            
            return toReturn;
        }

		/*
		 * returns the title label (pointsLeft) and add it to title panel
		 */
        public JPanel getTitle()
        {
            JPanel toReturn = new JPanel(); ///creates new JPanel for holder
		    pointsLabel = new Label("Points Left: " + pointsLeft, 35); ///creates new Label
			
			toReturn.setBackground(Color.LIGHT_GRAY);//light gray background
			toReturn.add(pointsLabel); ///adds button to holder
			
			return toReturn;
        }

		/*
		 * returns the point allocation buttons user will press to 
		 */
        public JPanel getPointAllocationButtons()
        {
			//jpanel with gridlayout 6 rows, 3 columns (1 col/user character) 
            JPanel toReturn = new JPanel(new GridLayout(6, 3));
            //initialize point allocation handler for all of them 
            PointAllocationHandler pointHandler = new PointAllocationHandler();

			toReturn.setBackground(Color.LIGHT_GRAY);
			for(int i=0; i<charNames.length; i++)
			{
				//create buttons for character 
				Button addHP = new Button(charNames[i] + ": +5 HP [2pts]", pointHandler, 12);
				Button addMana = new Button(charNames[i] + ": +3 Mana [1pt]", pointHandler, 12);
				Button addDef = new Button(charNames[i] + ": +1 Defense [3pts]", pointHandler, 12);
				Button removeHP = new Button(charNames[i] + ": -5 HP [returns 2pts]", pointHandler, 12);
				Button removeMana = new Button(charNames[i] + ": -3 Mana [returns 1pt]", pointHandler, 12);
				Button removeDef = new Button(charNames[i] + ": -1 Defense [returns 3pts]", pointHandler, 12);
				
				//add buttons to panel 
				toReturn.add(addHP);
				toReturn.add(addMana);
				toReturn.add(addDef);
				toReturn.add(removeHP);
				toReturn.add(removeMana);
				toReturn.add(removeDef);
			}

            return toReturn;
        }

		/*
		 * point allocation handler 
		 */
        class PointAllocationHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                String command = evt.getActionCommand();//get name of button
				
				//iterate through characterss 
				for(int i=0; i<charNames.length; i++)
				{
					int colonIndex = command.indexOf(":");//get index of colon
					//character name = everything before colon
					String charName = command.substring(0, colonIndex);//find character name 
					
					//if current iterated character is the one button references 
					if(charNames[i].equals(charName))
					{
						//if command has HP in its name 
						if(command.indexOf("HP") != -1)
						{
							//if user wants to increment hp 
							if(command.indexOf("+") != -1)
							{
								//if user has enough points 
								if(pointsLeft > 1)
								{
									//exchange points 
									hpPoints[i]++;
									pointsLeft-=2;
								}
								//else, don't do anything 
							}
							else//command user to decrement hp 
							{
								hpPoints[i]--;//decrement hp for upgrade points 
								if(hpPoints[i] < 0)//if user doesn't have enough points 
								{
									hpPoints[i] = 0;//reset to 0 
								}
								else
								{
									pointsLeft+=2;//give 2 upgrade points 
								}
							}
						}//note: it's the same logic for the rest of the options. 
						else if(command.indexOf("Mana") != -1)
						{
							if(command.indexOf("+") != -1)//wants to increment mana
							{
								//check if enough points 
								if(pointsLeft > 0)
								{
									//exchange if so
									manaPoints[i]++;
									pointsLeft--;
								}
							}
							else//wants to trade mana for upgrade points 
							{
								manaPoints[i]--;//decrement 
								if(manaPoints[i] < 0)//if doesn't have enough  
								{
									manaPoints[i] = 0;//hard cap at 0 mana points 
								}
								else
								{
									pointsLeft++;//give an upgrade point back 
								}
							}
						}
						else//command has defense as its subject
						{
							if(command.indexOf("+") != -1)//wants to increment defense 
							{
								if(pointsLeft > 2)//if has enough pts 
								{
									//exchange 
									defPoints[i]++;
									pointsLeft-=3;
								}
							}
							else
							{
								defPoints[i]--;//decrement
								if(defPoints[i] < 0)//check if enough points
								{
									defPoints[i] = 0;//minimum is zero
								}
								else
								{
									pointsLeft+=3;//give 3 points back 
								}
							}
						}
					}
				}
				
				//update label so user knows how many points they have 
                updatePointLabel();
            }
        }

		/*
		 * handler for submit button. 
		 */
        class ConfirmationHandler implements ActionListener
        {
			/*
			 * actionPerformed method, called when user clicks confirm. 
			 * This is when we write the user stat changes into GameData. 
			 */
            public void actionPerformed(ActionEvent evt)
            {
				//get player characters to add info to 
                Character[] playerChars = GameData.getPlayerChars();
                //add upgrade information 
				for(int i=0; i<playerChars.length; i++)
				{
					playerChars[i].increaseMaxHP(5*hpPoints[i]);//pts calable by 5 (pts = points) 
					playerChars[i].increaseMaxMana(3*manaPoints[i]);//pts scalable by 3
					playerChars[i].increaseDefense(defPoints[i]);//pts as-is 
				}
				
				//hide popup
                popupFrame.setVisible(false);
                //refresh game data stats 
                GameData.refreshStats();
                GameData.writeData(true); ///writes data
            }
        }
    }
}
