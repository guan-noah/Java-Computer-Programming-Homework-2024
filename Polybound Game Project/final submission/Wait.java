/*
 * Krish Kumar 
 * Period 6
 * Wait.java
 * 
 * This class, an extension of Timer, represents a wait time. Pass in a wait 
 * time and the code will wait that many seconds/ms before moving on to 
 * the next command. 
 */
//imports
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * Wait class. 
 */
public class Wait extends Timer implements ActionListener
{
    private int timeElapsed;//time elapsed since object made 
    private int waitTime;//time we want to wait before moving on to the next statement 
 
	/*
	 * Initializes field variables to parameter inputs. 
	 * Defaults to seconds (1000 ms). 
	 */
    public Wait(int waitTimeIn, ActionListener a)
    {
        super(1000, a);//calls timer constructor with delay and listener. 
        addActionListener(this);//see actionPerformed method for listener effects. 
		
        timeElapsed = -1;
        waitTime = waitTimeIn;//set wait time to parameter input 
		
		//start timer! 
        start();
    }    
	
	/*
	 * Initializes field variables to parameter inputs. 
	 * Wait time is input. 
	 * Same logic as before, just user gets to decide the second/ms delay now. 
	 */
    public Wait(int millisDelay, int waitTimeIn, ActionListener a)
    {
        super(millisDelay, a);//calls timer constructor with delay and listener
        addActionListener(this);
		
        timeElapsed = -1;
        waitTime = waitTimeIn;
		
        start();
    }    

	/*
	 * This method returns a boolean to see if the time set is reached. 
	 */
    public boolean isComplete()
    {
        return timeElapsed == waitTime;
    }

	/*
	 * This method increments the time elapsed counter every time the 
	 * method is called. 
	 */
    public void actionPerformed(ActionEvent evt)
    {
		//if time set reached 
        if(isComplete())
        {
			//stop the timer. 
            stop();
        }
        else
        {
			//increment 
            timeElapsed++;
        }
    }
}