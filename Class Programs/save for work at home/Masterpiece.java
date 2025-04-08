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
import java.awt.Font;
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
		int frameX = 170;
		int frameY = 570;
		frame.setSize(frameX, frameY);									//normal: 600, 500
		frame.setLocation(widthScalable-frameX, heightScalable-frameY);	//sets it to the perfect screen edge (plus 50 to give space for toolbar)
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
		//Color gridNumBlue = new Color(0, 255, 255);
		Color brown = new Color(140, 60, 30);
		
		
		/*//draw the gridlines
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
		*/
		
		//draw the body outline arcs 
			//radius is 170 
		g.setColor(Color.WHITE);
		g.drawArc(frameShiftX-190, frameShiftY-70, (2*170), 2*170, 45, -90);
		g.drawArc(frameShiftX+10, frameShiftY-70, (2*170), 2*170, 135, 90);
		g.setFont(new Font("Times New Roman", Font.BOLD, 10));
		g.drawString("Masterpiece Rocket", 40, 20);
		
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
		g.setColor(Color.GREEN);
		g.drawLine(60, 20, 60, 180);
		g.drawLine(100, 20, 100, 180);
		
		//draw the window carrier rectangle
		g.setColor(Color.CYAN);
		g.drawRect(70, 40, 20, 100);									//start at (70, 40), go 20 right & 100 down
		
		//draw the windows within central carrier 
		g.setColor(Color.BLUE);
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
			chooseColor(g);
			g.fillArc(60, 140+10*i, 40, 160-20*i, 0, -180);
		}
		
		//(55, 220), (95, 220) go 10, 20
		for(int i = 0; i < 2; i++)
		{
			g.setColor(Color.RED);
			g.fillOval(55+40*i, 220, 10, 20);
		}
		
		//draw the center side rocket propulsion; x-coordinates first arr, y-coordinates second arr
			//in order of center left right (small one) then enter, then center, left, right (large one) 
		
		g.setColor(Color.YELLOW);
		drawPoly(g, new int[] {80, 100, 80, 60}, new int[] {160, 180, 260, 180});//large center
		drawPoly(g, new int[] {40, 40, 20, 30}, new int[] {160, 180, 240, 170});//large left 
		drawPoly(g, new int[] {120, 130, 140, 120}, new int[] {160, 170, 240, 180});//large right
		
		g.setColor(Color.ORANGE);
		drawPoly(g, new int[] {80, 90, 80, 70}, new int[] {160, 170, 220, 170});//small center
		drawPoly(g, new int[] {40, 40, 30, 30}, new int[] {160, 180, 200, 170});//small left
		drawPoly(g, new int[] {120, 130, 130, 120}, new int[] {160, 170, 200, 180});//small right 


		
		
		
		
		//draw the clouds 
		/*//custom:
		int[] cloudX = new int[] {0, 30, 60};
		int[] cloudY = new int[] {310, 350, 390};
		//int[] cloudProportion = new int[] {randomNum(), randomNum(), randomNum()};
		*/
		
		for(int i = 0; i < 6/*cloudXProportion.length*/; i++)
		{
			//custom:
			//drawCloud(g, cloudX[i], cloudY[i]/*, cloudProportion[i]*/);
			//drawCloud(g, 30, 310+60*i);
			chooseColor(g);
			drawCloud(g, 40*(i%2), 310+30*i);		//first one is the x between pics; second one is the y between pics 
		}	
	}
	public int randomNum()
	{
		return (int)(Math.random()*10);
	}
	public void drawPoly(Graphics gIn, int[] polyX, int[] polyY)
	{
		gIn.fillPolygon(polyX, polyY, 4);
	}
	public void chooseColor(Graphics g)									//from GraphicsExample.java
	{
		Color VIOLET = new Color(128, 0, 128);
		Color INDIGO = new Color(75, 0, 130);
		Color ORANGE = new Color(255, 120, 0);
		
		Color[] colors = {  Color.WHITE, Color.YELLOW, Color.GREEN, Color.RED,
			 Color.CYAN, Color.GRAY, Color.BLUE, VIOLET, INDIGO, ORANGE};  
		
		int randNum = 0;
		randNum = (int)(Math.random()*colors.length);
			
		g.setColor(colors[randNum]);
	}
	public void drawCloud(Graphics g, int startX, int startY)
	{
		//g.setColor(Color.GRAY);
		//from 0 to 90
		g.fillArc(startX + 40, startY, 40, 40, 0, 90);
		g.fillArc(startX + 60, startY + 20, 40, 20, 0, 90);
		
		//from 90 to 180
		g.fillArc(startX, startY + 30, 20, 20, 90, 90);
		g.fillArc(startX + 10, startY + 10, 40, 40, 90, 90);
		g.fillArc(startX + 30, startY, 60, 20, 90, 90);
		
		//from 180 to 270
		g.fillArc(startX + 40, startY + 60, 20, 10, 180, 90);
		g.fillArc(startX + 30, startY + 55, 20, 10, 180, 90);
		g.fillArc(startX + 15, startY + 50, 30, 10, 180, 90);
		g.fillArc(startX + 10, startY + 35, 10, 20, 180, 90);
		g.fillArc(startX, startY + 35, 20, 10, 180, 90);
		
		//from 270 to 360
		g.fillArc(startX + 40, startY, 60, 60, 270, 90);
		g.fillArc(startX + 30, startY + 50, 40, 20, 270, 90);
		
		//fill inside 
		g.fillRect(startX + 30, startY + 10, 30, 10);
		g.fillRect(startX + 30, startY + 20, 60, 10);
		g.fillRect(startX + 10, startY + 30, 80, 15);
		g.fillRect(startX + 15, startY + 45, 75, 5);
		g.fillRect(startX + 15, startY + 50, 55, 5);
		g.fillRect(startX + 30, startY + 55, 40, 5);
		g.fillRect(startX + 40, startY + 60, 10, 5);
	}
}
