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
		JFrame frame = new JFrame("Dragging an oval with a mouse");
		frame.setSize( 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(10, 10);
		frame.setResizable(true);
		MouseMovingExPanel mMovePanel = new MouseMovingExPanel();
		frame.getContentPane().add( mMovePanel );
		frame.setVisible(true);
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
		setBackground(Color.WHITE);
		xloc = yloc = 100;
		xMouse = yMouse = 0;
		dragging = false;
		
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);
		width = this.getWidth();
		height = this.getHeight();
		
		g.setColor(Color.BLUE);
		g.fillOval(xloc,yloc, 50,50);
		
		requestFocusInWindow();
	}
	
	/*  Comments */
	public void mousePressed ( MouseEvent evt )
	{
		xMouse = evt.getX();
		yMouse = evt.getY();
		
		if (xloc < xMouse && xMouse < (xloc + 50) && yloc < yMouse && yMouse < (yloc + 50) )
			dragging = true;
		repaint();
	}
	
	/* Comments  */
	public void mouseReleased ( MouseEvent evt ) 
	{
		// stop dragging
		dragging = false;
	}
	
	public void mouseClicked ( MouseEvent evt ) {}
	public void mouseEntered ( MouseEvent evt ) {}
	public void mouseExited ( MouseEvent evt ) {}
	
	/* Comments  */
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
	
	public void mouseMoved(MouseEvent evt) {}
}  // end class MouseMovingExPanel
