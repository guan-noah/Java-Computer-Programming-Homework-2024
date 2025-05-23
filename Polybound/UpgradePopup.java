/*
 * Krish Kumar
 * Period 6
 * UpgradePopup.java
 * 
 * This class, a Popup (see Popup.java), represents the upgrade 
 */
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpgradePopup extends Popup
{
    private int pointsLeft;	//upgrade points available to spend 
    private int hpPoints;		//upgrade user hp
    private int manaPoints;		//upgrade user mana
    private int defPoints;		//upgrade user defense 
    
    private UpgradePopupContent contentPanel;	//the actual popup panel
	
    public UpgradePopup()
    {
        super("Upgrade");			//initialize popup, titled Upgrade
        pointsLeft = 0;
    }
	/*
	 * This method implements the parent class' popup effectively. It 
	 * gets the previous popup and replaces the actual content in the center. 
	 */
    public JPanel getContentPanel()
	{
		JPanel toReturn = super.getContentPanel();
		contentPanel = new UpgradePopupContent();
		
		//add popup content to panel's center 
		toReturn.add(contentPanel, BorderLayout.CENTER);

		return toReturn;
	}
	/*
	 * This class sets the upgrade ui to a prime state. All variables are wiped clean. 
	 * Updates points label to state how many points left. 
	 */
    public void resetPoints()
    {
		//if user had saved points, we'd add those previous points to 
			//the 3 given in this round
        pointsLeft += 3;
        manaPoints = 0;
        hpPoints = 0;
        defPoints = 0;
        //updates points label 
        contentPanel.updatePointLabel();
    }
	/*
	 * This class holds the main information and components in the center.
	 */
    class UpgradePopupContent extends JPanel
    {
		//the JLabel that displays how many upgrade points user has left 
        private Label pointsLabel;

        public UpgradePopupContent()
        {
            setLayout(new BorderLayout());

            JPanel titlePanel = getTitle();//title = pointsLabel 
            //the grid of 6 buttons in center 
            JPanel pointsPanel = getPointAllocationButtons();
            JPanel confirmPanel = getConfirmButton();

            add(titlePanel, BorderLayout.NORTH);
            add(confirmPanel, BorderLayout.SOUTH);
            add(pointsPanel, BorderLayout.CENTER);
        }
		
		/*
		 * This method updates the point label. 
		 */
        public void updatePointLabel()
        {
            pointsLabel.setText("Points Left: " + pointsLeft);
        }
		
		/*
		 * This method returns the panel with the confirmation button (attached to 
		 * ConfirmationHandler, which saves the upgraded points to the character). 
		 * Only updates the character when confirmation button pressed. 
		 */
        public JPanel getConfirmButton()
        {
            JPanel toReturn = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
            ConfirmationHandler confirmHandler = new ConfirmationHandler();
            Button confirmButton = new Button("Confirm", confirmHandler, 35);
            
            toReturn.setBackground(Color.DARK_GRAY);
            toReturn.add(confirmButton);
            
            return toReturn;
        }
		
		/*
		 * This method returns the JPanel which holds the JLabel and a light gray background. 
		 */
        public JPanel getTitle()
        {
            JPanel toReturn = new JPanel(); ///creates new JPanel for holder
		    pointsLabel = new Label("Points Left: " + pointsLeft, 30); ///creates new Label
			
			toReturn.setBackground(Color.LIGHT_GRAY);
			toReturn.add(pointsLabel); ///adds button to holder
			
			return toReturn;
        }
		
		/*
		 * This method returns the grid of buttons, all connected to the
		 * PointAllocationHandler. 
		 */
        public JPanel getPointAllocationButtons()
        {
			//jpanel of a 3x2 grid 
            JPanel toReturn = new JPanel(new GridLayout(3, 2));
            PointAllocationHandler pointHandler = new PointAllocationHandler();
            //create all buttons with their descriptions and add 
				//pointHandler to them 
            Button addHP = new Button("+ HP Point", pointHandler, 35);
            Button removeHP = new Button("- HP Point", pointHandler, 35);
            Button addMana = new Button("+ Mana Point", pointHandler, 35);
            Button removeMana = new Button("- Mana Point", pointHandler, 35);
            Button addDefense = new Button("+ Defense Point", pointHandler, 35);
            Button removeDefense = new Button("- Defense Point", pointHandler, 35);
			
			//add all buttons to the grid 
            toReturn.add(addHP);
            toReturn.add(removeHP);
            toReturn.add(addMana);
            toReturn.add(removeMana);
            toReturn.add(addDefense);
            toReturn.add(removeDefense);

            return toReturn;
        }
		
		/*
		 * This class, comparmentalized by button through if-else statements, 
		 * changes the user's data based on which button the user clicks. 
		 */
        class PointAllocationHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
				//get which button clicked
                String command = evt.getActionCommand();
				
				//compartmentalize method by button. all methods move 1 POINT. 
                if(command.equals("+ HP Point"))//from 'points left' to hp 
                {
					//only executes if user has points remaining
                    if(pointsLeft > 0)
                    {
                        hpPoints++;
                        pointsLeft--;
                    }
                }
                else if(command.equals("- HP Point"))//from hp to points
                {
                    hpPoints--;
                    //only executes if user has hp points to take away. 
                    if(hpPoints < 0)
                    {
                        hpPoints = 0;
                    }
                    else
                    {
                        pointsLeft++;
                    }
                }
                else if(command.equals("+ Mana Point"))//from points to mana
                {
					//only executes if user has points remaining
                    if(pointsLeft > 0)
                    {
                        manaPoints++;
                        pointsLeft--;
                    }
                }
                else if(command.equals("- Mana Point"))//from mana to points
                {
                    manaPoints--;
                    //only executes if user has hp points 
                    if(manaPoints < 0)
                    {
                        manaPoints = 0;
                    }
                    else
                    {
                        pointsLeft++;
                    }
                }
                else if(command.equals("+ Defense Point"))//from points to defense
                {
					//only executes if user has ENOUGH points remaining
					//note: defense needs 3 points to upgrade. 
                    if(pointsLeft > 2)
                    {
                        defPoints++;
                        pointsLeft-=3;
                    }
                }
                else if(command.equals("- Defense Point"))//from defense to points
                {
                    defPoints--;
                    //only executes if user has defense points 
                    if(defPoints < 0)
                    {
                        defPoints = 0;
                    }
                    else
                    {
                        pointsLeft+=3;
                    }
                }
				//update label that shows user how many points they have left 
                updatePointLabel();
            }
        }
		
		/*
		 * This class actually commits the information in upgrade popup 
		 * to the player save file. 
		 */
        class ConfirmationHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
				//gets user character from GameData
                Character player = GameData.getPlayerCharacter();
                //changes user information 
                player.increaseMaxHP(hpPoints*5);
                player.increaseMaxMana(manaPoints*3);
                player.increaseDefense(defPoints);
                
                //debugging 
                //prints out the user max hp and max mana to console 
                System.out.println(player.getMaxHP());
                System.out.println(player.getMaxMana());
				
				//set popup frame to not visible 
                popupFrame.setVisible(false);
                GameData.refreshStats();	//refresh 
                GameData.writeData(true); ///writes data
            }
        }
    }
}
