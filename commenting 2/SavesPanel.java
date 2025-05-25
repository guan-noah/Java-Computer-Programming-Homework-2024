import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

public class SavesPanel extends JPanel
{
    private ArrayList<SaveInfo> saveInfos; ///list of saves that are shown
    private JScrollPane contentPane; ///content pane

    public SavesPanel()
    {
        setLayout(new BorderLayout());

        contentPane = new JScrollPane();
        JPanel returnBtnHolder = getReturnButton();

        add(contentPane, BorderLayout.CENTER);
        add(returnBtnHolder, BorderLayout.SOUTH);
    }

    public JPanel getReturnButton()
    {
        JPanel toReturn = new JPanel();
        ReturnButtonHandler returnBtnHandler = new ReturnButtonHandler();
        Button returnButton = new Button("Return to Menu", returnBtnHandler, 40);

        toReturn.setBackground(Color.DARK_GRAY);
        toReturn.add(returnButton);

        return toReturn;
    }

    public void getSaves()
    {
        ArrayList<Save> savesList = GameData.getSaveList();
        saveInfos = new ArrayList<>();

        JPanel content = new JPanel(new GridLayout(savesList.size(), 1));
        for(int i=0; i<savesList.size(); i++)
        {
            SaveInfo info = new SaveInfo(savesList.get(i), i);
            saveInfos.add(info);
            content.add(info);
        }

        contentPane.setViewportView(content);
    }

    class ReturnButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if(command.equals("Return to Menu"))
            {
                GameData.switchCard("main menu");
            }
        }
    }

    class SaveInfo extends JPanel
    {
        private TextField passwordField;
        private Save save;
        private int index;

        public SaveInfo(Save saveIn, int indexIn)
        {
            save = saveIn;
            index = indexIn;

            setBackground(Color.LIGHT_GRAY);
            setLayout(new BorderLayout());

            Label name = new Label(saveIn.getUserName(), 30);
            JPanel center = getCenter(saveIn);
            JPanel optionsHolder = getOptionButtons();

            add(name, BorderLayout.NORTH);
            add(center, BorderLayout.CENTER);
            add(optionsHolder, BorderLayout.SOUTH);
        }

        public JPanel getCenter(Save saveIn)
        {
            JPanel toReturn = new JPanel(new GridLayout(5, 1));
            Label enemiesDefeated = new Label("Enemies defeated: "+saveIn.getEnemiesDefeated(), 20);
            Character[] chars = saveIn.getPlayerCharacters();
            Label[] charLabels = new Label[3];
            passwordField = new TextField("Enter in your password...", 15, 40);
            toReturn.setBackground(Color.LIGHT_GRAY);
            toReturn.add(enemiesDefeated);

            for(int i=0; i<charLabels.length; i++)
            {
                String text = chars[i].getName() + " [HP: " + chars[i].getHP() +
                    "/" + chars[i].getMaxHP() + ", Mana: " + chars[i].getMana() +
                    "/" + chars[i].getMaxMana() + ", Defense: " + chars[i].getDefense() +
                    ", Level " + chars[i].getLevel() + "]"; 
                charLabels[i] = new Label(text, 20);
                toReturn.add(charLabels[i]);
            }
            toReturn.add(passwordField);

            return toReturn;
        }

        public JPanel getOptionButtons()
        {
            JPanel toReturn = new JPanel(new GridLayout(1, 2));
            OptionButtonHandler optionBtnHandler = new OptionButtonHandler();
            Button loadSave = new Button("Load Save", optionBtnHandler, 30);
            Button deleteSave = new Button("Delete Save", optionBtnHandler, 30);

            toReturn.setBackground(Color.LIGHT_GRAY);
            toReturn.add(loadSave);
            toReturn.add(deleteSave);

            return toReturn;
        }

        class OptionButtonHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                String command = evt.getActionCommand();
                String password = save.getPassword();
                System.out.println("password: " + password);

                if(passwordField.isSelected() && passwordField.getText().equals(password))
                {
                    if(command.equals("Load Save"))
                    {
                        ///loads all stuff from the save
                        GameData.setUserName(save.getUserName());
                        GameData.setEnemiesDefeated(save.getEnemiesDefeated());
                        GameData.setPlayerChars(save.getPlayerCharacters());
                        GameData.setPlayerCharacter(save.getPlayerCharacters()[0]);
                        GameData.setSaveIndex(index);
                        GameData.switchCard("intermission");
                    }
                    else if (command.equals("Delete Save"))
                    {
                        ///remove save
                        GameData.setSaveIndex(index);
                        GameData.getSaveList().remove(index);
    
                        ///ensures that no extra data is deleted
                        if (GameData.getSaveIndex() > index)
                        {
                            GameData.setSaveIndex(GameData.getSaveIndex() - 1);
                        }
                        else if (GameData.getSaveIndex() == index)
                        {
                            GameData.setSaveIndex(Math.max(0, GameData.getSaveIndex() - 1));
                        }
    
                        ///rewrites to file and removes save visual
                        GameData.writeData(false);
                        saveInfos.remove(index);
    
    
                        if(GameData.getSaveList().size() < 1)
                        {
                            GameData.setGameStarted(false);
                        }
    
                        getSaves();
                    }
                }
            }
        }
    }
}
