//Constellation2.java
/* Noah Guan
 * 02-28-2025
 * Per. 6 Java w/ Mr. Yu
 * Constellation2.java
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
import java.awt.Font;
//import javax.swing.*;
//import java.awt.*;
public class Constellation2
{
	public Constellation2()
	{
		
	}
	public static void main(String[] args)
	{
		Constellation2 consta = new Constellation2();
		consta.drawConstellation2Panel();
	}
	public void drawConstellation2Panel()
	{
		JFrame frame = new JFrame("The Big Dipper");
		int widthScalable = 1920*4/5;
		int heightScalable = (1080-50)*4/5;								//1920 x 1080 screen with a 50 px toolbar
		int xFrameSize = 630;
		int yFrameSize = 450; 
		frame.setSize(630, 450);										
		frame.setLocation(widthScalable-630, heightScalable-450);		//sets it to the perfect screen edge (plus 50 to give space for toolbar)
		//frame.setLocation(1320, 530);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);		
		Constellation2Panel cPanel = new Constellation2Panel();
		frame.setContentPane(cPanel);
		frame.setVisible(true);
	}
}
class Constellation2Panel extends JPanel
{
	private int[] frameSizes;
	public Constellation2Panel()
	{
		super.setBackground(Color.BLUE);
	}
	public Constellation2Panel(int xFrameSizeIn, int yFrameSizeIn)
	{
		frameSizes[0] = xFrameSizeIn;
		frameSizes[1] = yFrameSizeIn;
		Constellation2Panel();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int x = 0; x < 4*frameSizes[0]; x+=frameSizes[0])
		{
			for(int y = 0; y < 4*frameSizes[1]; y+=frameSizes[1])
			{
				/* draw Constellation with both x and y scaled down by 4 */
				
			}
		}
		//drawConstellation2(g, 0, 0, 1);									//frameshifts 0; original code 
	}
	public void drawConstellation2(Graphics g, int fsx, int fsy, int dB)//frameShiftX, frameShiftY, divideBy
	{
		//~ d&i colors (already have Color.RED, Color.YELLOW, Color.BLUE)
		Color brown = new Color(140, 60, 30);
		Color gridNumBlue = new Color(0, 255, 255);
		Color gridTextBlue = new Color(0, 155, 155);
		
		
		//~ store crucial connection points (for polygonic drawings)
		
		int[] dipperX = new int[] {360/dB+fsx, 540/dB+fsx, 560/dB+fsx, 400/dB+fsx};
		int[] dipperY = new int[] {220/dB+fsx, 140/dB+fsx, 240/dB+fsx, 300/dB+fsx};
		int[] otherPointsX = new int[] {40/dB+fsx, 180/dB+fsx, 240/dB+fsx};
		int[] otherPointsY = new int[] {240/dB+fsx, 180/dB+fsx, 200/dB+fsx};
		
		//~ draw circles denoting constellation connection points 
		g.setColor(Color.WHITE);
		
		for(int i = 0; i < dipperX.length; i++)							//for loop to iterate through int arrays essentially removes the need for input of several points
		{
			drawPoints(g, dipperX[i], dipperY[i]);						//for-each (enhanced for) loop would not work (because we need coords x AND y) and we haven't learned that yet
			if (i < otherPointsX.length)								//uses .length to make it flexible if we were to change 
				drawPoints(g, otherPointsX[i], otherPointsY[i]);
		}
		/* //~ this is what I mean: 
		drawPoints(g, 40, 240);
		drawPoints(g, 180, 180);
		drawPoints(g, 240, 200);
		drawPoints(g, 360, 220);										//this marks the start of the quadrilateral polygon (onwards)
		drawPoints(g, 540, 140);										//could technically use the integers to be faster
		drawPoints(g, 560, 240);
		drawPoints(g, 400, 300);
		*/
		
		//~ polygon + 3 lines 
		g.drawPolygon(dipperX, dipperY, 4);
		for(int i = 0; i < otherPointsX.length; i++)					//4 points, not 3 (last one is part of polygon) -- these lines most likely to throw an error for me
		{
			int connectX = 0, connectY = 0, nextConnectX = 0, nextConnectY = 0;
			connectX = otherPointsX[i];
			connectY = otherPointsY[i];
			if(i < otherPointsX.length - 1)								//-1 ensures that otherPoints[i+1] does not throw ArrayIndexOutOfBounds error
			{
				nextConnectX = otherPointsX[i+1];
				nextConnectY = otherPointsY[i+1];
			}
			else 														//i = otherPoints.length - 1
			{
				nextConnectX = dipperX[0];
				nextConnectY = dipperY[0];
			}
			
			g.drawLine(connectX, connectY, nextConnectX, nextConnectY);
		}
		
		//~ Title the Big Dipper (font and drawString)
		g.setColor(Color.WHITE);
		Font titleFont = new Font("Times New Roman", Font.ITALIC, 47);	//height ~~= 40, should end at x ~~= 460 (eyeballed if not italicized) or ~~= 470 (italicized)
		g.setFont(titleFont);
		g.drawString("The Big Dipper", 150, 80);
		
		//~ draw box over Times New Roman italicized 
		g.drawRect(140, 35, (480-140)/dB+fsx, (100-35)/dB+fsy);						//delta length: ending coordinates - beginning coordinates
		
		//~ 2 circles for moon (1 white, covers 1 yellow) 
		g.setColor(Color.YELLOW);
		g.fillOval(-5, -5, 110, 110);
		g.setColor(Color.BLUE);
		g.fillOval(-25, -25, 110, 110);
		
		//~ half an oval filled in the bottom (note: example picture's oval (half) ends at 430), supposed to end at 480
		g.setColor(brown);
		g.fillArc(0, 380, 630, 100/dB+fsx, 0, 180);
		//~ time to draw the rocket. 
			//~ draw 2 filled triangles and a polygon 
		g.setColor(Color.RED);
		g.fillArc(33, 331, 50, 50, 190, 20); 							//72, 355 center, 17 radius
		g.fillArc(38, 346, 50, 50, 190, 20);							//74, 370 center, 17 radius 
			//~ draw an arc and spaceship polygon 
		g.drawArc(0, 341, 630, 100/dB+fsx, 0, 125);
		int[] xArr = new int[] {140, 120, 65, 57, 110};
		int[] yArr = new int[] {350, 370, 380, 345, 336};
		g.drawPolygon(xArr, yArr, 5);
	}
	public void drawPoints(Graphics g, int centerX, int centerY)
	{
		int shiftBack = - 5;											//Sam claims it was -8
		g.fillOval((centerX + shiftBack), (centerY + shiftBack), (-2*shiftBack), (-2*shiftBack));
	}
}
