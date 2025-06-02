/**
 * Krish Kumar
 * Period 6
 * SavesPanel.java
 * 
 * This class allows for the user to manage their list of saves
 * with a simple GUI. The user can either load or delete a save if
 * they have the password to said save.
 **/

///import libraries
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

    ///Creates the SavesPanel.
    public SavesPanel()
    {
        setLayout(new BorderLayout());

        contentPane = new JScrollPane();
        JPanel returnBtnHolder = getReturnButton();

        add(contentPane, BorderLayout.CENTER);
        add(returnBtnHolder, BorderLayout.SOUTH);
    }

    ///Creates and initializes the "Return to Menu" button.
    public JPanel getReturnButton()
    {
        ///initializes button, holder, and handler
        JPanel toReturn = new JPanel();
        ReturnButtonHandler returnBtnHandler = new ReturnButtonHandler();
        Button returnButton = new Button("Return to Menu", returnBtnHandler, 40);

        ///setup
        toReturn.setBackground(Color.DARK_GRAY);
        toReturn.add(returnButton);

        return toReturn;
    }

    ///Retrieves the saves from the saveList in GameData, and displays them.
    public void getSaves()
    {
        ///gets saves
        ArrayList<Save> savesList = GameData.getSaveList();
        saveInfos = new ArrayList<>();

        ///creates and sets display
        JPanel content = new JPanel(new GridLayout(savesList.size(), 1));
        for(int i=0; i<savesList.size(); i++)
        {
            SaveInfo info = new SaveInfo(savesList.get(i), i);
            saveInfos.add(info);
            content.add(info);
        }
        contentPane.setViewportView(content);
    }

    ///Handler class for the "Return to Menu" button.
    class ReturnButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if(command.equals("Return to Menu"))
            {
                ///switch to main menu
                GameData.switchCard("main menu");
            }
        }
    }

    /**
     * This class represents one of the visible "saves" in the
     * SavesPanel, where the information about the save is shown,
     * and the options concerning the save are available.
     **/
    class SaveInfo extends JPanel
    {
        private TextField passwordField; ///TextField for password enter
        private Save save; ///this SaveInfo's save
        private int index; ///this SaveInfo's index

        ///Initializes this SaveInfo with the specified index and save.
        public SaveInfo(Save saveIn, int indexIn)
        {
            save = saveIn;
            index = indexIn;

            setBackground(Color.LIGHT_GRAY);
            setLayout(new BorderLayout());

            ///content initialization
            Label name = new Label(saveIn.getUserName(), 30);
            JPanel center = getCenter(saveIn);
            JPanel optionsHolder = getOptionButtons();

            ///adds content to this SaveInfo
            add(name, BorderLayout.NORTH);
            add(center, BorderLayout.CENTER);
            add(optionsHolder, BorderLayout.SOUTH);
        }

        ///Creates and initializes the center panel displaying the info and text field.
        public JPanel getCenter(Save saveIn)
        {
            ///content and holder initialization
            JPanel toReturn = new JPanel(new GridLayout(5, 1));
            Label enemiesDefeated = new Label("Enemies defeated: "+saveIn.getEnemiesDefeated(), 20);
            Character[] chars = saveIn.getPlayerCharacters();
            Label[] charLabels = new Label[3];
            passwordField = new TextField("Enter in your password...", 15, 40);
            toReturn.setBackground(Color.LIGHT_GRAY);
            toReturn.add(enemiesDefeated);

            ///setup
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

        ///Creates and initializes the option buttons (load and delete).
        public JPanel getOptionButtons()
        {
            ///initializes buttons, handler, and holder
            JPanel toReturn = new JPanel(new GridLayout(1, 2));
            OptionButtonHandler optionBtnHandler = new OptionButtonHandler();
            Button loadSave = new Button("Load Save", optionBtnHandler, 30);
            Button deleteSave = new Button("Delete Save", optionBtnHandler, 30);

            ///setup
            toReturn.setBackground(Color.LIGHT_GRAY);
            toReturn.add(loadSave);
            toReturn.add(deleteSave);

            return toReturn;
        }

        ///A handler for the option buttons. (load and delete)
        class OptionButtonHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                String command = evt.getActionCommand();
                String password = save.getPassword();

                ///if password is correct
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

                        ///switches to intermission panel
                        GameData.switchCard("intermission");
                    }
                    else if (command.equals("Delete Save"))
                    {
                        ///removes save
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
    
                        getSaves(); ///refreshes saves
                    }
                }
            }
        }
    }
}
