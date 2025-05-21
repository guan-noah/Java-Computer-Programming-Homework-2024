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

            g.setFont(new Font("Verdana", Font.BOLD, 40));
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
                    GameData.switchCard("intermission");
                }
                else if(command.equals("New Game"))
                {
                    GameData.writeData(false); ///overwrites save
                    GameData.setGameStarted(false);
                    GameData.switchCard("user info");
                }

                popupFrame.setVisible(false);
            }
        }
    }
}