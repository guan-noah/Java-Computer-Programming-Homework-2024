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
import java.awt.Font;
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
		JFrame frame = new JFrame("The Big Dipper");
		int widthScalable = 1920*4/5;
		int heightScalable = (1080-50)*4/5;								//1920 x 1080 screen with a 50 px toolbar
		frame.setSize(630, 450);										
		frame.setLocation(widthScalable-630, heightScalable-450);		//sets it to the perfect screen edge (plus 50 to give space for toolbar)
		//frame.setLocation(1320, 530);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);		
		ConstellationPanel cPanel = new ConstellationPanel();
		frame.setContentPane(cPanel);
		frame.setVisible(true);
	}
}
class ConstellationPanel extends JPanel
{
	public ConstellationPanel()
	{
		super.setBackground(Color.BLUE);
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
		
		
		//~ store crucial connection points (for polygonic drawings)
		
		int[] dipperX = new int[] {360, 540, 560, 400};
		int[] dipperY = new int[] {220, 140, 240, 300};
		int[] otherPointsX = new int[] {40, 180, 240};
		int[] otherPointsY = new int[] {240, 180, 200};
		
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
		g.drawRect(140, 35, (480-140), (100-35));						//delta length: ending coordinates - beginning coordinates
		
		//~ 2 circles for moon (1 white, covers 1 yellow) 
		g.setColor(Color.YELLOW);
		g.fillOval(-5, -5, 110, 110);
		g.setColor(Color.BLUE);
		g.fillOval(-25, -25, 110, 110);
		
		//~ oval filled in the bottom (note: example picture's oval (half) ends at 430), supposed to end at 480
		g.setColor(brown);
		g.fillOval(0, 380, 630, 100);
		//~ time to draw the rocket. 
			//~ draw 2 filled triangles and a polygon 
		g.setColor(Color.RED);
		g.fillArc(33, 331, 50, 50, 190, 20); 							//72, 355 center, 17 radius
		g.fillArc(38, 346, 50, 50, 190, 20);							//74, 370 center, 17 radius 
			//~ draw an arc and spaceship polygon 
		g.drawArc(0, 341, 630, 100, 0, 125);
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
