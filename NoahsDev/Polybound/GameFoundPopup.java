 /*
  * Krish Kumar
  * Period 6
  * GameFoundPopup.java
  * 
  * Determines if game found. Helps load saved game. 
  * 
  */
//imports 
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameFoundPopup extends Popup
{
	//make a popup with "Ongoing Game Found!" as the title 
    public GameFoundPopup()
    {
        super("Ongoing Game Found!");
    }
	/*
  	 * for inheritance purposes
	 */
    public JPanel getContentPanel()
    {
	    //create the content panel 
        JPanel toReturn = super.getContentPanel();
	    //the custom class 
		GameFoundPopupContent newContent = new GameFoundPopupContent();
		//add the custom class to the content panel to return
		toReturn.add(newContent, BorderLayout.CENTER);
		
		return toReturn;
    }
	/*
  	 * class for custom JPanel and paintComponent; the center content panel
	 */
    class GameFoundPopupContent extends JPanel
    {
		/*
  		 * initialize popup components and information 
		 */
        public GameFoundPopupContent()
        {
            setLayout(new FlowLayout(FlowLayout.CENTER, 30, 150));
			//setup buttons 
            OptionHandler optionHandler = new OptionHandler();
            Button yes = new Button("Continue", optionHandler, 40);
            Button no = new Button("New Game", optionHandler, 40);

			//set popup content background 
            setBackground(Color.LIGHT_GRAY);

			//add the buttons 
            add(yes);
            add(no);
        }
		
		/*
 	 	 * paintComponent; called by repaint(). Only drawn 
		 */
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            g.setFont(new Font("Verdana", Font.BOLD, 40));
            g.drawString(title, 50, 50);
            g.drawString("Continue?", 190, 100);
        }
		/*
  		 * optionHandler for the buttons 
		 */
        class OptionHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                String command = evt.getActionCommand();

				//decide which one to execute based on command 
                if(command.equals("Continue"))
                {
					//simply switch the card 
                    GameData.switchCard("intermission");
                }
                else if(command.equals("New Game"))
                {
                    GameData.writeData(false); ///overwrites save - cleans it out
					//restart game and redirect to user info 
                    GameData.setGameStarted(false);
                    GameData.switchCard("user info");
                }
				//hide popup frame 
                popupFrame.setVisible(false);
            }
        }
    }
}
