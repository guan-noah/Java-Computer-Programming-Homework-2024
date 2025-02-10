// GraphicsExample.java
// An example of different graphics shapes, colors, 

// Practice:  
	// 1. Make a magenta arc with center at (100, 300), starts at 300 degrees and ends at  
	//   200 degrees. The distance from the center to the top is 60 and to the left is 40
	// 2. Draw a pink sector that looks like the light from a flash light shining left.  
	//   The center is at (140, 400).  The distance from the center to the left edge is 80 
	// 	 and to the top is 30.  The light starts at 150 degrees and ends at 210 degrees.

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
