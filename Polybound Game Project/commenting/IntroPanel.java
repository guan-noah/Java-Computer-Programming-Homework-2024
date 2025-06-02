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

    public void setupText()
    {
        String fileName = "introText.txt";
        File file = new File(fileName);
        Scanner read = null;

        try
        {
            read = new Scanner(file);

            while (read.hasNext())
            {
                toWrite.add(read.nextLine() + "_");
            }
        }
        catch(FileNotFoundException e)
        {
            System.err.printf("Error: Could not find file \"%s\"", fileName);
        }
    }

    public void write()
    {
        if(arrIndex < toWrite.size())
        {
            if(lineIndex < toWrite.get(arrIndex).length())
            {
                letterWritten = false;
                text.setText(toWrite.get(arrIndex).substring(0, lineIndex+1));
                lineIndex++;

                NextLetterHandler nextLetterHandler = new NextLetterHandler();
                Wait wait = new Wait(100, 1, nextLetterHandler);
            }
            else
            {
                arrIndex++;
                lineIndex = 0;
                write();
            }
        }
    }

    class NextLetterHandler implements ActionListener
    {
        public NextLetterHandler()
        {
            letterWritten = false;
        }

        public void actionPerformed(ActionEvent evt)
        {
            Wait wait = (Wait) evt.getSource();

            if(wait.isComplete() && !letterWritten)
            {
                letterWritten = true;
                write();
            }
        }
    }
}
