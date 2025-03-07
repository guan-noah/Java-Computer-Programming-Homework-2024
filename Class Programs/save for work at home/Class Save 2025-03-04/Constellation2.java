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
		int xSize = (630+50*3);
		int ySize = (450+50*3);
		frame.setSize(xSize, ySize);										
		frame.setLocation(widthScalable-xSize, heightScalable-ySize);	//sets it to the perfect screen edge (plus 50 to give space for toolbar)
		//frame.setLocation(1320, 530);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);		
		Constellation2Panel c2Panel = new Constellation2Panel();
		frame.setContentPane(c2Panel);
		frame.setVisible(true);
	}
}
class Constellation2Panel extends JPanel
{
	public Constellation2Panel()
	{
		super.setBackground(Color.BLUE);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		for(int x = 0; x < (630+200); x+=(630/4))
		{
			for(int y = 0; y < (450+200); y+=(450/4))
			{
				g.setColor(Color.RED);
				g.drawRect(x, y, 630/4, 450/4);
				g.setColor(Color.WHITE);
				drawConstellation(g, 4, x, y);							//frameshifts 
				y+=50;
			}
			x+=50;
		}
		
	}
	public int sN(int coord, int scaleNumIn, int shift)					//scaleNumbers
	{
		return (coord/scaleNumIn+shift);
	}
	public void drawConstellation(Graphics g, int sN, int shiftX, int shiftY)//scaleNum, frameShiftX, frameShiftY 
	{
		//~ d&i colors (already have Color.RED, Color.YELLOW, Color.BLUE)
		Color brown = new Color(140, 60, 30);
		Color gridNumBlue = new Color(0, 255, 255);
		Color gridTextBlue = new Color(0, 155, 155);
		
		//~ store all points in format s### (shift###) or sn### (shiftNegative###) or s###m### (shift(###-###))
		
		//~ store crucial connection points (for polygonic drawings)
		int[] dipperX = new int[] {sN(360, sN, shiftX), sN(540, sN, shiftX), sN(560, sN, shiftX), sN(400, sN, shiftX)};
		int[] dipperY = new int[] {sN(220, sN, shiftY), sN(140, sN, shiftY), sN(240, sN, shiftY), sN(300, sN, shiftY)};
		int[] otherPointsX = new int[] {sN(40, sN, shiftX), sN(180, sN, shiftX), sN(240, sN, shiftX)};
		int[] otherPointsY = new int[] {sN(240, sN, shiftY), sN(180, sN, shiftY), sN(200, sN, shiftY)};
		
		//~ draw circles denoting constellation connection points 
		g.setColor(Color.WHITE);
		
		for(int i = 0; i < dipperX.length; i++)							//for loop to iterate through int arrays essentially removes the need for input of several points
		{
			drawPoints(g, dipperX[i], dipperY[i], sN, shiftX, shiftY);						
			if (i < otherPointsX.length)								//uses .length to make it flexible if we were to change 
				drawPoints(g, otherPointsX[i], otherPointsY[i], sN, shiftX, shiftY);
		}
		
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
		Font titleFont = new Font("Times New Roman", Font.ITALIC, sN(47, sN, 0));
																		//height ~~= 40, should end at x ~~= 460 (eyeballed if not italicized) or ~~= 470 (italicized)
		g.setFont(titleFont);
		g.drawString("The Big Dipper", sN(150, sN, shiftX), sN(80, sN, shiftY));
		
		//~ draw box over Times New Roman italicized 
		g.drawRect(sN(140, sN, shiftX), sN(35, sN, shiftY), sN(480-140, sN, 0), sN(100-35, sN, 0));
																		//delta length: ending coordinates - beginning coordinates
		
		//~ 2 circles for moon (1 white, covers 1 yellow) 
		g.setColor(Color.YELLOW);
		g.fillOval(sN(-5, sN, shiftX), sN(-5, sN, shiftY), sN(110, sN, 0), sN(110, sN, 0));
		g.setColor(Color.BLUE);
		g.fillOval(sN(-5, sN, shiftX), sN(-25, sN, shiftY), sN(110, sN, 0), sN(110, sN, 0));
		
		
		//~ oval filled in the bottom (note: example picture's oval (half) ends at 430), supposed to end at 480
		g.setColor(brown);
		g.fillArc(sN(0, sN, shiftX), sN(380, sN, shiftY), sN(630, sN, 0), sN(100, sN, 0), 0, 180);
		
		//~ time to draw the rocket. 
			//~ draw 2 filled triangles and a polygon 
		g.setColor(Color.RED);
		g.fillArc(sN(33, sN, shiftX), sN(331, sN, shiftY), sN(50, sN, shiftX), sN(50, sN, shiftY), 190, 20); 
			//72, 355 center, 17 radius
		g.fillArc(sN(38, sN, shiftX), sN(346, sN, shiftY), sN(50, sN, shiftX), sN(50, sN, shiftY), 190, 20);
			//74, 370 center, 17 radius 
		
		//~ draw an arc and spaceship polygon 
		g.drawArc(sN(0, sN, shiftX), sN(341, sN, shiftY), sN(630, sN, shiftX), sN(100, sN, shiftY), 0, 125);//arc
		int[] xArr = new int[] {sN(140, sN, shiftX), sN(120, sN, shiftX), sN(65, sN, shiftX), sN(57, sN, shiftX), sN(110, sN, shiftX)};
		int[] yArr = new int[] {sN(350, sN, shiftY), sN(370, sN, shiftY), sN(380, sN, shiftY), sN(345, sN, shiftY), sN(336, sN, shiftY)};
		g.drawPolygon(xArr, yArr, 5);
		g.drawString("hi", 40, 40);
		
	}
	public void drawPoints(Graphics g, int centerX, int centerY, int sN, int shiftX, int shiftY)
	{
		//int shiftBack = sN(-5, sN, shiftX);								//Sam claims it was -8
		g.fillOval((centerX + sN(-5, sN, shiftX)), (centerY + sN(-5, sN, shiftY)), (-2*sN), (-2*sN));
	}
}
