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
	public Color[] dusk, dawn;
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
		//~ randNum = (int)(Math.random()*(dawn.length + dusk.length));
		
		//~ if(randNum < dawn.length)
			//~ g.setColor(dawn[randNum]);
		//~ else //if(randNum < dawn.length)
			//~ g.setColor(dusk[randNum - dawn.length]);
		g.setColor(colors[randNum]);
	}
	//~ public Color[] setColorSet(int[] colorSetIn)
	//~ {
		//~ if(colorSetIn.length%3 != 0)
			//~ System.out.println("Error: Incorrect number of RGB modifiers");
		
		//~ Color[] outputColors = new Color[colorSetIn.length/3];
		//~ for(int i = 0; i < outputColors.length; i++)					//go through outputColors; another way to do it: go through 3 at a time 
			//~ outputColors[i/3] = new Color(colorSetIn[i], colorSetIn[++i], colorSetIn[++i]);//intialize outputColors while incrementing index 
		//~ return outputColors;
	//~ }
	//~ public void setImage()												//sets all the variables needed to create the image
	//~ {
		//~ //DAWN COLORS: 210, 125, 140 (pink); 155, 165, 190 (light grayish blue); 155, 155, 180 (grayish blue); 61, 63, 95 (dark blue); 110, 65, 115 (dark purple) 
			//~ //ordered from red & light to blue & dark 
		//~ dawn = new Color[5];
		//~ int[] dawnVals = new int[] {210, 125, 140, 155, 165, 190, 155, 155, 180, 61, 63, 95, 110, 65, 115};
		//~ dawn = setColorSet(dawnVals);

		//~ //DUSK COLORS: 135, 150, 175 (bluish gray), 250, 215, 225 (peach-pink); 200, 170, 220 (purple); 185, 175, 220 (faded purple)
			//~ //ordered from red & dark to blue & light 
		//~ dusk = new Color[4];
		//~ int[] duskVals = new int[] {135, 150, 175, 250, 215, 225, 200, 170, 220, 185, 175, 220};
		//~ dusk = setColorSet(duskVals);
	//~ }
	
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
