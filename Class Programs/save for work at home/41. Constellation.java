//Constellation.java
/* Noah Guan
 * 01-31-2025
 * Per. 6 Java w/ Mr. Yu
 * Constellation.java
 * Program #41
 * Pseudocode: (comments outlined; I built my program on the comments) 

import javax.swing.*;
import java.awt.*;
class header 
	constructor header 
	main header 
		new instance of Constellation 
		m.drawConstellation()
	drawConstellation header 
		d&i colors 
		Color.RED, Color.YELLOW, Color.BLUE
		
		make grid (for loop)
		
		draw constellation
		polygon + 3 lines 
		
		2 circles for moon (1 blank) 
		first circle covers second 
		
		Title the Big Dipper 
		draw box over Times New Roman italicized, font ~40
		
		Earth:
		brown oval filled in the bottom 
		
		rocket:
		draw 2 filled triangles and a polygon 
		draw an arc 

class header 
	

 */
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
//import javax.swing.*;
//import java.awt.*;
public class Constellation
{
	public Constellation()
	{
		
	}
	public static void main(String[] args)
	{
		Constellation consta = new Constellation();
		consta.drawConstellationPanel();
	}
	public void drawConstellationPanel()
	{
		JFrame frame = new JFrame();
		int widthScalable = 1920*4/5;
		int heightScalable = (1080-50)*4/5;								//1920 x 1080 screen with a 50 px toolbar
		frame.setSize(630, 450);										
		frame.setLocation(widthScalable-630, heightScalable-450);		//sets it to the perfect screen edge (plus 50 to give space for toolbar)
		//frame.setLocation(1320, 530);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);		
		ConstellationPanel cPanel = new ConstellationPanel();
		frame.setContentPane(cPanel);
		frame.setVisible(true);
		System.out.println("\nhi :)");
	}
}
class ConstellationPanel extends Panel
{
	public ConstellationPanel()
	{
		super.setBackground(Color.WHITE);
		//~ super.setBackground(Color.BLUE);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		drawConstellation(g, 0, 0);										//frameshifts 
	}
	public void drawConstellation(Graphics g, int fsx, int fsy)			//frameShiftX, frameShiftY
	{
		//~ d&i colors (already have Color.RED, Color.YELLOW, Color.BLUE)
		Color brown = new Color(140, 60, 30);
		Color gridNumBlue = new Color(0, 255, 255);
		Color gridTextBlue = new Color(0, 155, 155);
		
		//~ make grid (for loop)
		for(int i = 0; i < 1000; i++)
		{
			g.setColor(gridNumBlue);
			if((i/10)%2 == 0)
				g.drawString("" + i, i, 10);							//the x coordinates (columns)
			else
				g.drawString("" + i, i + 5, 20);						//to shift everything down so you can actually read it
			g.drawString("" + i, 5, i);									//the y coordinates (rows)
			
			g.setColor(gridTextBlue);
			g.drawLines(0, i, 1000, i);
			g.drawLines(i, 0, i, 1000);
		}
		//~ draw circles denoting constellation connection points 
		drawPoints(g, 40, 240);
		drawPoints(g, 180, 180);
		drawPoints(g, 240, 200);
		drawPoints(g, 360, 220);										//this marks the start of the quadrilateral polygon (onwards)
		drawPoints(g, 
		
		//~ polygon + 3 lines 
		g.drawPolygon();
		g.drawLines();
		g.drawLines();
		g.drawLines();
		
		//~ 2 circles for moon (1 white, covers 1 yellow) 
		g.fillOval();
		g.fillOval();
		
		//~ Title the Big Dipper 
		g.drawString();
				
		//~ draw box over Times New Roman italicized 
		g.drawRects();
		
		//~ oval filled in the bottom (note: example picture's oval (half) ends at 430), supposed to end at 480
		g.fillOval(0, 380, 630, 100);
		
		//~ draw 2 filled triangles and a polygon 
		//~ draw an arc 
	}
	public void drawPoints(Graphics g, int centerX, int centerY)
	{
		g.gdrawOval(centerX - 
	}
}
