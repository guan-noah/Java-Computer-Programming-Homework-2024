// Noah Guan
// Date:
// TimerExample.java
// This program demonstrates a simple timer animation with a bouncing ball.

// Topics:
//	1. Create an ActionListener class
//	2. Implement timer for JPanel
// After the program runs correctly, you will add the following:
//  3. Add in a MouseListner so that when you press the mouse on the panel, the ball slows down
//		When you click it again, it resumes the normal speed.
/// You do the following
///	4.  Add in a KeyListener, so when you press the up/down/right/left arrow, the ball is
///		move 20 pixels in that direction.

import java.awt.Color;		
import java.awt.Graphics;
import java.awt.Font;

import java.awt.event.ActionListener;	
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;	
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;	
import java.awt.event.KeyEvent;
		
import javax.swing.JFrame;	
import javax.swing.JPanel;
//////////////////2.   import for Timer	///////////////
import javax.swing.Timer;

public class TimerExample 
{
	public TimerExample()
	{
	}	
	
	public static void main (String[] args) 
	{
		TimerExample te = new TimerExample();
		te.run();
	}
	
	public void run() 
	{
		JFrame frame = new JFrame("Timer Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create JPanel and add to frame
		DrawPanel panel = new DrawPanel();
		frame.getContentPane().add(panel);	// add panel to frame
				
		frame.setSize(500, 600);	// explicitly set size in pixels
		frame.setLocation(600,0);
		frame.setVisible(true);		// set to false to make invisible
	}
}	// end class TimerExample

// JPanel with a private ActionListener class called "Mover"
class DrawPanel extends JPanel 
{
	private int x, y, count;
	private boolean left, up;
	// create a Timer field variable
	private Timer timer;
	
	///////////////////////////////////////////////////
	//	Create a class called Mover that implements ActionListener 
	//
	private class Mover implements ActionListener, MouseListener, KeyListener
	{
		public Mover()
		{
			addMouseListener(this); // add the listener			
		}

		public void actionPerformed(ActionEvent evt)
		{
			// moves the ball right and left
			if (!left && x < getWidth()-50)  //note, this.getWidth will not work here.
				x++;
			else 
			{ 
				left = true; 
				x--; 
			}
			
			if ( left && x > 0 ) 
				x--;
			else 
			{ 
				left = false; 
				x++; 
			}
		
			// moves the ball up and down
			if (!up && y < getHeight()-50) 
				y++;
			else 
			{ 
				up = true; 
				y--; 
			}
			
			if ( up && y > 0 ) 
				y--;
			else 
			{ 
				up = false; 
				y++; 
			}
			repaint();
			requestFocusInWindow();
		}
		
		//////////////////// MouseListener methods/////////////////////////
		public void mousePressed(MouseEvent evt)
		{
			count++;
			if(count%2 == 1)
			{	
				//if(control)
					timer.stop();
				
				timer.setDelay(50);
			}
			else
			{
				//if(control)
					timer.start();
				timer.setDelay(5);
			}
		 }
	 
		public void mouseReleased(MouseEvent evt){}
		public void mouseClicked(MouseEvent evt){}
		public void mouseEntered(MouseEvent evt){}
		public void mouseExited(MouseEvent evt){}

		//////////////////// KeyListener methods/////////////////////////
		public void keyPressed(KeyEvent evt)
		{
			if(evt.getKeyCode() == KeyEvent.VK_UP && y > 0)
			{
				up = true;
			}
			else 
				up = false;
			
			if(evt.getKeyCode() == KeyEvent.VK_DOWN && y < getHeight() - 50)
			{
				up = false;
			}
			else 
				up = true;
			
			if(evt.getKeyCode() == KeyEvent.VK_LEFT && x > 0)
			{
				left = true;
			}
			else 
				left = false;
			
			if(evt.getKeyCode() == KeyEvent.VK_RIGHT && x < getWidth() - 50)
			{
				left = false;
			}
			else 
				left = true;
		}
		public void keyReleased(KeyEvent evt){}
		public void keyTyped(KeyEvent evt){}
	}
	
	// The JPanel's constructor
	public DrawPanel () 
	{
		count = 0;
		x = 0; y = 30;			// initial upper left corner location of red ball
		left = up = false;		// initialize ball going right and down
		setBackground(Color.MAGENTA);
		////////////////////////////////////////////////
		//	Declare and initialize
		//	- an ActionListener object
		//	- a Timer
		Mover mover = new Mover();
		timer = new Timer(5, mover);
		////////////////////////////////////////////////
		//	Start the timer
		timer.start();
	}
	
	// paintComponent() draws the circle and increments the location.
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillOval(x, y, 50, 50);
	}
}	// end class DrawPanel
