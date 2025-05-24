import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Wait extends Timer implements ActionListener
{
    private int timeElapsed;
    private int waitTime;
 
    ///defaults to seconds
    public Wait(int waitTimeIn, ActionListener a)
    {
        super(1000, a);
        addActionListener(this);

        timeElapsed = -1;
        waitTime = waitTimeIn;

        start();
    }    

    public Wait(int millisDelay, int waitTimeIn, ActionListener a)
    {
        super(millisDelay, a);
        addActionListener(this);

        timeElapsed = -1;
        waitTime = waitTimeIn;

        start();
    }    

    public boolean isComplete()
    {
        return timeElapsed == waitTime;
    }

    public void actionPerformed(ActionEvent evt)
    {
        if(isComplete())
        {
            stop();
        }
        else
        {
            timeElapsed++;
        }
    }
}
