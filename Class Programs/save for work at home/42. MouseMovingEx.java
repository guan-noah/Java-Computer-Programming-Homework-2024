// Noah Guan
// 03/03/2025 
// P.6 Java 
// MouseMovingEx.java

// This is an example of using the mouse to move an oval across the panel.

/* importing JFrame and JPanel */
import javax.swing.JFrame;
import javax.swing.JPanel;

/*importing javax*/
import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseMovingEx
{
	public MouseMovingEx()
	{
	}

	public static void main(String[] args)
	{
		MouseMovingEx mve = new MouseMovingEx();
		mve.runIt();
	}

	public void runIt()
	{
		JFrame frame = new JFrame("Dragging an oval with a mouse");		//JFrame name 
		frame.setSize( 600, 600 );										//frame size = 600 px by 600 px
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//frame closes when clicked x button 
		frame.setLocation(10, 10);										//upper left corner is at 10, 10
		frame.setResizable(true);										//frame is resizable 
		MouseMovingExPanel mMovePanel = new MouseMovingExPanel();		//d&i new instance of the panel (calling constructor)
		frame.getContentPane().add( mMovePanel );						//add panel to frame 
		frame.setVisible(true);											//set frame to visible 
	}
}  // end class MouseMovingEx

class MouseMovingExPanel extends JPanel implements MouseMotionListener, MouseListener
{
	private boolean dragging;	// tells if the rectangle is being dragged
	private int xloc, yloc;  // x and y location of oval
	private int width, height;  // width and height of JPanel
	private int xMouse, yMouse;  // location of the mouse
	
	public MouseMovingExPanel()
	{
		//setBackground(Color.WHITE);										//set background color to white 
		xloc = yloc = 100;												//initialize x location & y location to 100
		xMouse = yMouse = 0;											//initialize x mouse & y mouse to 100
		dragging = false;												//set to not dragging 
		
		addMouseMotionListener(this);									//add the MouseMotionListener and the MouseListener to this object (listen for mouse movements and clicks)
		addMouseListener(this);
	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);										//fill color white
		width = this.getWidth();										//call getWidth from this program and store in width
		height = this.getHeight();										//same logic for height 
		
		g.setColor(Color.BLUE);
		g.fillOval(xloc,yloc, 50,50);									//draw blue circle at x location and y location
		
		requestFocusInWindow();											//request focus, get ready for user input 
	}
	
	/*  when mouse is pressed, get x position and y position of mouse (at click). 
	 * then if x location is (greater than starting position and less than 
	 * starting position + 50) and y location is the same logic but for y coordinates, dragging is true. 
	 * after that, call repaint again to show moving circle where mouse is. */
	public void mousePressed ( MouseEvent evt )
	{
		xMouse = evt.getX();
		yMouse = evt.getY();
		
		if (xloc < xMouse && xMouse < (xloc + 50) && yloc < yMouse && yMouse < (yloc + 50) )
			dragging = true;
		repaint();
	}
	
	/* if mouse is released, the dragging is false or send signal that we're not dragging anymore */
	public void mouseReleased ( MouseEvent evt ) 
	{
		// stop dragging
		dragging = false;
	}
	/* since this implements MouseMotionListener and MouseListener, we have to complete implementation */
	public void mouseClicked ( MouseEvent evt ) {}
	public void mouseEntered ( MouseEvent evt ) {}
	public void mouseExited ( MouseEvent evt ) {}
	
	/* if user is dragging mouse, add current x coordinate - starting x 
	 * coordinate to x location (of circle) and do the same with y coordinate; 
	 * then reinitialize xMouse and yMouse with current coordinates of mouse. 
	 * Finally, repaint the component again. */
	public void mouseDragged(MouseEvent evt) 
	{
		if (dragging)
		{
			xloc = xloc + (evt.getX() - xMouse);
			yloc = yloc + (evt.getY() - yMouse);
			xMouse = evt.getX();
			yMouse = evt.getY();
			repaint();
		}
	}
	/* again, we have to complete implementation */
	public void mouseMoved(MouseEvent evt) {}
}  // end class MouseMovingExPanel
