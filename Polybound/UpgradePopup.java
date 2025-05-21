import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpgradePopup extends Popup
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
		    pointsLabel = new Label("Points Left: " + pointsLeft, 30); ///creates new Label
			
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
