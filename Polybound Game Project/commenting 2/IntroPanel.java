/*
 * Krish Kumar 
 * Period 6
 * Wait.java
 * 
 * This class, an extension of JPanel, shows the introduction to the game. 
 * supposed to include Krish's moving text proof of concept. 
 * 
 * Put on hold until the rest of the project is done. 
 * Currently goes 2x as fast per click -- buggy. 
 */
//imports 
import javax.swing.JPanel;

import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class IntroPanel extends JPanel
{
    private ArrayList<String> toWrite;
    private Label text;
    private int arrIndex;
    private int lineIndex;
    private boolean letterWritten;

    public IntroPanel()
    {
        // setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        // toWrite = new ArrayList<>();
        // text = new Label("", 30);
        // arrIndex = 0;
        // lineIndex = 0;

        // setupText();
        // add(text);
        // write();
    }
    
	/*
	 * This method sets up the introduction text. 
	 */
    public void setupText()
    {
		//standard file io logic 
        String fileName = "introText.txt";
        File file = new File(fileName);
        Scanner read = null;

        try
        {
            read = new Scanner(file);
			
			//once scanner has been made, we've got to add the lines in the file to the arraylist fv.  
            while (read.hasNext())
            {
                toWrite.add(read.nextLine() + "_");
            }
        }
        catch(FileNotFoundException e)
        {
			//error message
            System.err.printf("Error: Could not find file \"%s\"%n", fileName);
        }
    }

	/*
	 * This method writes 
	 * Assumes arrIndex and lineIndex preexist and are initialized properly. 
	 */
    public void write()
    {
		//checks if array index is less than size of array
        if(arrIndex < toWrite.size())
        {
			String currentToWriteItem = toWrite.get(arrIndex);
			//if line index is less than length of array list item 
            if(lineIndex < currentToWriteItem.length())
            {
                //set letter written to false 
                letterWritten = false;
                //set text field to the first lineIndex + 1 chars. 
                text.setText(currentToWriteItem.substring(0, lineIndex+1));
                lineIndex++;//increment lineIndex 
				
				//pass in next letter handler for action to constantly update. 
                NextLetterHandler nextLetterHandler = new NextLetterHandler();
                Wait wait = new Wait(100, 1, nextLetterHandler);//wait 1/10 of a second 
            }
            else
            {
                arrIndex++;//increment array index 
                lineIndex = 0;//reset line index to 0 
                write();//call again! recursion 
            }
        }
    }

	/*
	 * This class handles the next letter. 
	 */
    class NextLetterHandler implements ActionListener
    {
		/*
		 * initializes letterWritten to false. 
		 */
        public NextLetterHandler()
        {
            letterWritten = false;
        }

		/*
		 * This method is called every second. 
		 */
        public void actionPerformed(ActionEvent evt)
        {
			//initialize wait to see if task is complete
            Wait wait = (Wait) evt.getSource();
			
			//if action is done (timer is reached) and letterwritten is false
            if(wait.isComplete() && !letterWritten)
            {
                letterWritten = true;//set letter written to true 
                write();//write letter 
            }
        }
    }
}
