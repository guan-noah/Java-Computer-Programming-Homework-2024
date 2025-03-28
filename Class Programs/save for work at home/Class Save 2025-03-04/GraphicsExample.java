// GraphicsExample.java
// An example of different graphics shapes, colors, 

// Practice:  
	// 1. Make a magenta arc with center at (100, 300), starts at 300 degrees and ends at  
	//   200 degrees. The distance from the center to the top is 60 and to the left is 40
	// 2. Draw a pink sector that looks like the light from a flash light shining left.  
	//   The center is at (140, 400).  The distance from the center to the left edge is 80 
	// 	 and to the top is 30.  The light starts at 150 degrees and ends at 210 degrees.
/* LIST OF MOST IMPORTANT DRAWING METHODS: 

Here is a list of some of the most important drawing methods. With all these commands, any drawing that is done outside the boundaries of the component is ignored. Note that all these methods are in the Graphics class, so they all must be called through an object of type Graphics.

drawString(String str, int x, int y) -- Draws the text given by the string str. The string is drawn using the current color and font of the graphics context. x specifies the position of the left end of the string. y is the y-coordinate of the baseline of the string. The baseline is a horizontal line on which the characters rest. Some parts of the characters, such as the tail on a y or g, extend below the baseline.
drawLine(int x1, int y1, int x2, int y2) -- Draws a line from the point (x1,y1) to the point (x2,y2). The line is drawn as if with a pen that hangs one pixel to the right and one pixel down from the (x,y) point where the pen is located. For example, if g refers to an object of type Graphics, then the command g.drawLine(x,y,x,y), which corresponds to putting the pen down at a point, colors the single pixel with upper left corner at the point (x,y).
drawRect(int x, int y, int width, int height) -- Draws the outline of a rectangle. The upper left corner is at (x,y), and the width and height of the rectangle are as specified. If width equals height, then the rectangle is a square. If the width or the height is negative, then nothing is drawn. The rectangle is drawn with the same pen that is used for drawLine(). This means that the actual width of the rectangle as drawn is width+1, and similarly for the height. There is an extra pixel along the right edge and the bottom edge. For example, if you want to draw a rectangle around the edges of the component, you can say "g.drawRect(0, 0, getWidth()-1, getHeight()-1);", where g is a graphics context for the component. If you use "g.drawRect(0, 0, getWidth(), getHeight());", then the right and bottom edges of the rectangle will be drawn outside the component and will not appear on the screen.
drawOval(int x, int y, int width, int height) -- Draws the outline of an oval. The oval is one that just fits inside the rectangle specified by x, y, width, and height. If width equals height, the oval is a circle.
drawRoundRect(int x, int y, int width, int height, int xdiam, int ydiam) -- Draws the outline of a rectangle with rounded corners. The basic rectangle is specified by x, y, width, and height, but the corners are rounded. The degree of rounding is given by xdiam and ydiam. The corners are arcs of an ellipse with horizontal diameter xdiam and vertical diameter ydiam. A typical value for xdiam and ydiam is 16, but the value used should really depend on how big the rectangle is.
draw3DRect(int x, int y, int width, int height, boolean raised) -- Draws the outline of a rectangle that is supposed to have a three-dimensional effect, as if it is raised from the screen or pushed into the screen. The basic rectangle is specified by x, y, width, and height. The raised parameter tells whether the rectangle seems to be raised from the screen or pushed into it. The 3D effect is achieved by using brighter and darker versions of the drawing color for different edges of the rectangle. The documentation recommends setting the drawing color equal to the background color before using this method. The effect won't work well for some colors.
drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) -- Draws part of the oval that just fits inside the rectangle specified by x, y, width, and height. The part drawn is an arc that extends arcAngle degrees from a starting angle at startAngle degrees. Angles are measured with 0 degrees at the 3 o'clock position (the positive direction of the horizontal axis). Positive angles are measured counterclockwise from zero, and negative angles are measured clockwise. To get an arc of a circle, make sure that width is equal to height.
fillRect(int x, int y, int width, int height) -- Draws a filled-in rectangle. This fills in the interior of the rectangle that would be drawn by drawRect(x,y,width,height). The extra pixel along the bottom and right edges is not included. The width and height parameters give the exact width and height of the rectangle. For example, if you wanted to fill in the entire component, you could say "g.fillRect(0, 0, getWidth(), getHeight());"
fillOval(int x, int y, int width, int height) -- Draws a filled-in oval.
fillRoundRect(int x, int y, int width, int height, int xdiam, int ydiam) -- Draws a filled-in rounded rectangle.
fill3DRect(int x, int y, int width, int height, boolean raised) -- Draws a filled-in three-dimensional rectangle.
fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) -- Draw a filled-in arc. This looks like a wedge of pie, whose crust is the arc that would be drawn by the drawArc method.

useful link: https://mvhs-fuhsd.org/john_conlin/Java/HW_folder_Java/EckTextbook/c6/s3.html

*/


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsExample
{
	public static void main(String[] args) 
	{
		GraphicsExample ge = new GraphicsExample();
		ge.runIt();
	}
	
	public void runIt()
	{
		JFrame frame = new JFrame("Graphics Drawing Example");
		frame.setSize(600, 500);
		frame.setLocation(200, 100);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);		
		GraphicsExPanel gePanel = new GraphicsExPanel();
		frame.getContentPane().add(gePanel);
		frame.setVisible(true);
	}
}	

class GraphicsExPanel extends JPanel
{
	public GraphicsExPanel()
	{
		setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		drawOvals(g);
		drawRects(g);
		drawLines(g);
		drawArcs(g);
		drawPolygon(g);
		drawGrid(g);
	}
	
	/*  Remember to have comments! */
	public void chooseColor(Graphics g)
	{
		Color VIOLET = new Color(128, 0, 128);
		Color INDIGO = new Color(75, 0, 130);
		Color ORANGE = new Color(255, 120, 0);
		
		Color[] colors = {  Color.BLACK, Color.YELLOW, Color.GREEN, Color.RED,
			 Color.CYAN, Color.GRAY, Color.BLUE, VIOLET, INDIGO, ORANGE};  
		
		int randNum = 0;
		randNum = (int)(Math.random()*colors.length);
			
		g.setColor(colors[randNum]);
	}
	
	/*  Remember to have comments! */
	public void drawOvals(Graphics g)
	{
		chooseColor(g);
		g.drawOval(50, 10, 100, 80);
		chooseColor(g);
		g.fillOval(20, 100, 50, 100);
	}
	
	/*  Remember to have comments! */
	public void drawArcs(Graphics g)
	{
		chooseColor(g);
		g.drawArc(50, 250, 100, 80, 90, 120);
		chooseColor(g);
		g.fillArc(20, 350, 60, 100, -135, 90);
	}
	
	/*  Remember to have comments! */
	public void drawRects(Graphics g)
	{
		chooseColor(g);
		g.drawRect(190, 50, 40, 100);
		chooseColor(g);
		g.fillRect(200, 190, 120, 90);
	}
	
	/*  Remember to have comments! */
	public void drawLines(Graphics g)
	{
		chooseColor(g);
		g.drawLine(300, 60, 400, 40);
		chooseColor(g);
		g.drawLine(320, 150, 420, 200);
	}
	
	/*  Remember to have comments! */
	public void drawPolygon(Graphics g)
	{
		chooseColor(g);					// add in the last one for each
		int [] xArray = {300, 350, 370, 320, 290}; // try the last at 430
		int [] yArray = {300, 320, 400, 370, 380}; 
		g.drawPolygon(xArray, yArray, 4);
		for(int i = 0; i < xArray.length; i++)
		{
			xArray[i] += 100;
		}
		g.fillPolygon(xArray, yArray, 5);
	}
	
	/*  Remember to have comments! */
	public void drawGrid(Graphics g)
	{
		int size = 20;
		g.setFont(new Font("serif", Font.PLAIN, 15));
		for(int x = 0; x <= 640; x += size)
		{
			g.setColor(new Color(165, 200, 233));
			g.drawLine(x, 0, x, 460);
			if(x%100 == 0)
			{
				g.setColor(Color.BLACK);
				g.drawString(x + "", x-12, 12);
			}
		}

		for(int y = 0; y <= 460; y += size)
		{
			g.setColor(new Color(165, 200, 233));
			g.drawLine(0, y, 640, y);
			if(y%100 == 0)
			{
				g.setColor(Color.BLACK);
				g.drawString(y + "", 0, y+3);
			}
		}
	}
}
