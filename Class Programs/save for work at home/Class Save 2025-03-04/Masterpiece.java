
//Masterpiece.java
/* Noah Guan
 * 01-31-2025
 * Per. 6 Java w/ Mr. Yu
 * Masterpiece.java
 * Program #40
 * Pseudocode: (comments) 

import javax.swing.*;
import java.awt.*;
class header 
	constructor header 
	main header 
		new instance of Masterpiece 
		m.drawRocket()
	drawRocket header 
		make empty arcs (-190, -70, 2(170), 2(170), 45, -45)
			and (10, -70, 2(170), 2(170), 45, -45)

class header 
	

 */
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
//import javax.swing.*;
//import java.awt.*;

public class Masterpiece
{
	public Masterpiece()
	{
		
	}
	public static void main(String[] args)
	{
		Masterpiece m = new Masterpiece();
		m.drawMasterpiece();
	}
	public void drawMasterpiece()
	{
		JFrame frame = new JFrame("Masterpiece.java");
		int widthScalable = 1920*4/5;
		int heightScalable = (1080-50)*4/5;								//1920 x 1080 screen with a 50 px toolbar
		frame.setSize(600, 500);										
		frame.setLocation(widthScalable-600, heightScalable-500);		//sets it to the perfect screen edge (plus 50 to give space for toolbar)
		//frame.setLocation(1320, 530);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);		
		MasterpiecePanel mPanel = new MasterpiecePanel();
		frame.setContentPane(mPanel);
		frame.setVisible(true);
	}
}

class MasterpiecePanel extends JPanel
{
	public MasterpiecePanel()
	{
		super.setBackground(Color.BLACK);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		drawRocket(g, 0, 0);
	}
	public void drawRocket(Graphics g, int frameShiftX, int frameShiftY)
	{
		//make some colors! 
		Color gridNumBlue = new Color(0, 255, 255);
		Color brown = new Color(140, 60, 30);
		
		//draw the gridlines
		int gridCoord = 0;
		int gridYCoord = 0;
		for(int i = 0; i < 1000; i += 10)
		{
			g.setColor(gridNumBlue);
			if((i/10)%2 == 0)
				g.drawString("" + i, i, 10);							//the x coordinates (columns)
			else
				g.drawString("" + i, i + 5, 20);						//to shift everything down so you can actually read it
			g.drawString("" + i, 5, i);									//the y coordinates (rows)
			
			g.setColor(Color.BLUE);
			g.drawLine(i, 0, i, 1000);									//x coords (column)
			g.drawLine(0, i, 1000, i);									//y coords (rows)
		}
		g.setColor(Color.WHITE);										//change color when done 
		
		//draw the body outline arcs 
			//radius is 170 
		g.drawArc(frameShiftX-190, frameShiftY-70, (2*170), 2*170, 45, -90);
		g.drawArc(frameShiftX+10, frameShiftY-70, (2*170), 2*170, 135, 90);
		
		//finish body outline 
			//lines from (80, -40something) to (60, -20) and (100, -20)
			//arc radius = 20(root2)
		g.drawLine(frameShiftX+80, frameShiftY-45, 60, -20);
		g.drawLine(frameShiftX+80, frameShiftY-45, -100, -20);
		g.drawArc(frameShiftX+(int)(80 - 20*Math.sqrt(2)), frameShiftY+(int)(-20*Math.sqrt(2)), 
			(int)(40*Math.sqrt(2)), (int)(40*Math.sqrt(2)), 45, 90);
				///editor's note: let's assume that this works and move on for now (you currently can't see it; it's at the top)
		g.drawLine(60, 220, 100, 220);
		drawPoly(g, new int[] {60, 80, 100, 80}, new int[] {220, 230, 220, 210});
		/* //~ ...this polygon takes care of: 
		g.drawLine(60, 220, 80, 230);
		g.drawLine(80, 230, 100, 220);
		//~ for tip of rocket 
		*/
		//draw the central carrier lines 
			//x = 60 and x = 100 
		g.drawLine(60, 20, 60, 180);
		g.drawLine(100, 20, 100, 180);
		
		//draw the window carrier rectangle
		g.drawRect(70, 40, 20, 100);									//start at (70, 40), go 20 right & 100 down
		
		//draw the windows within central carrier 
		for(int y = 40; y <= 120; y += 20)								//increment by 20, there are 5 circles 
		{
			g.fillOval(70, y, 20, 20);
		}
		
		//draw the center rocket propulsion
			//format: drawing polygons go from top clockwise 
				//draw large polygon first and small polygon second 
		g.drawArc(60, 85, 40, 160, -45, -90);
		for(int i = 0; i < 5; i++)										//for every 10 down the bottom, the starting goes down 5. 
		{
			g.drawArc(60, 140+10*i, 40, 160-20*i, 0, -180);
		}
		
		//(60, 220), (80, 220) mark the lengths 
		//80 down from 220
		g.drawArc(30, 140, 40, 160, 0, -180);
		
		//draw the center side rocket propulsion
		drawPoly(g, new int[] {80, 100, 80, 60}, new int[] {160, 180, 260, 180});
			//x-coordinates first one, y-coordinates second one
		drawPoly(g, new int[] {80, 90, 80, 70}, new int[] {160, 170, 220, 170});
		
		
		//draw the left side rocket propulsion 
		drawPoly(g, new int[] {40, 40, 20, 30}, new int[] {160, 180, 240, 170});
		drawPoly(g, new int[] {40, 40, 30, 30}, new int[] {160, 180, 200, 170});
		
		
		//draw the right side rocket propulsion
		drawPoly(g, new int[] {120, 130, 140, 120}, new int[] {160, 170, 240, 180});
		drawPoly(g, new int[] {120, 130, 130, 120}, new int[] {160, 170, 200, 180});
		
	}
	public void drawPoly(Graphics gIn, int[] polyX, int[] polyY)
	{
		gIn.drawPolygon(polyX, polyY, 4);
	}
	public void chooseColor(Graphics g)									//from GraphicsExample.java
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
}
