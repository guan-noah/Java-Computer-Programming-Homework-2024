//GardenGrows.java
/*
Pseudocode: 
 - every time you click with mouse, it should call repaint 
 - every time you click inside rectangle and if the next thing user does = water, it should turn green 
 - else, return to not-clicked state 
 - shouldn't do much inside paintComponent 
 - because timer doesn't run logically inside paintComponent 
 - rectangle = 1500 

mouseClicked = 

 */
//import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class GardenGrows
{
	public static void main(String[] args)
	{
		GardenGrows gg = new GardenGrows();
		gg.run();
	}
	public void run()
	{
		JFrame frame = new JFrame("Watch the garden grow");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		frame.setLocation(10, 10);
		frame.setSize(1200, 700);										//garden in middle with 
		frame.setResizable(true);
		Garden garden = new Garden();
		frame.setContentPane(garden);
		frame.setVisible(true);
		//System.out.println("ran");
	}
}
class Garden extends JPanel implements MouseListener, KeyListener//, MouseMotionListener
{
	private boolean clicked, water, flowers;
	public Garden()
	{
		clicked = false;
		water = false;
		flowers = false;
		requestFocusInWindow();
		this.addMouseListener(this);
		this.addKeyListener(this);
		//setBackground(new Color(0, 0, 0));//turn surrounding area black
		System.out.println("constructor");
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		drawGarden(g);
	}
	public void drawGarden(Graphics g)
	{
		int[] gardenInfo = new int[] {50, 50, 1000, 500};
		if (water)														//
			g.setColor(Color.GREEN);//draw garden green
		else
			g.setColor(Color.PINK);//draw garden pink
		
		//System.out.println("gardenSizes: "+gardenInfo[0]+", "+gardenInfo[1]+", "+gardenInfo[2]+", "+gardenInfo[3]);
		g.fillRect(gardenInfo[0], gardenInfo[1], gardenInfo[2], gardenInfo[3]);//actually draw the garden 
		showFlowers(g, gardenInfo[0], gardenInfo[2], gardenInfo[3]);
	}
	public void showFlowers(Graphics g, int gardenStart, int gardenWidth, int gardenHeight)
	{
		System.out.println("in showFlowers: flowers = "+flowers);
		if(flowers && water)											//
		{
			System.out.println("showing flowers before loops: ");
			g.setColor(Color.YELLOW);
			//draw 5 x 3 circles in nested for loop (50 x 50 px, 200 px between)
			int circleDiameter = 50; 
			int overlap = 25;
			int space = 200-2*overlap;									//one overlap per circle "sandwich"ing it 
			int howManyAcross = 5;
			int howManyDown = 3;
			double xFlowerBox = (double)(howManyAcross*circleDiameter+(howManyAcross-1)*space);
			double yFlowerBox = (double)howManyDown*circleDiameter+(howManyDown-1)*space;
			if(xFlowerBox > gardenWidth)
				g.drawString("Error: flowerBox width is greater than garden.", 10, 10);
			if(yFlowerBox > gardenWidth)
				g.drawString("Error: flowerBox height is greater than garden.", 10, 10);
			System.out.println("xFlower: "+xFlowerBox+"yFlower:"+yFlowerBox);
			
			int xStartFrom = (int)(gardenStart+(gardenWidth-xFlowerBox)/2);//make sure that startFrom follows garden sizes 
			int yStartFrom = (int)(gardenStart+(gardenHeight-yFlowerBox)/2);//... and that startFrom is centered correctly 
			System.out.println("x: "+xStartFrom+"y: "+yStartFrom);
			
			for (int x = 0; x < howManyAcross; x++)						//make it adjustable
			{
				for (int y = 0; y < howManyDown; y++)					//make it adjustable
				{
					g.fillOval(xStartFrom+(space)*x, yStartFrom+(space)*y, circleDiameter, circleDiameter);				
				}
			}System.out.println("flowers shown.");
		}
		//else do nothing 
	}
	
	public void mousePressed(MouseEvent evt)
	{
		//~ get coords
		int mouseX = evt.getX();
		int mouseY = evt.getY();
		
		clicked = ((mouseX >= 100 && mouseX <= 1100) && (mouseY >= 100 && mouseY <= 600));
			//~ bool clicked = if in range 
		if(clicked)
		{
			requestFocusInWindow();System.out.println("clicked.");
		}
		
		repaint();
	}
	public void mouseReleased(MouseEvent evt) {}
	public void mouseClicked(MouseEvent evt) {}
	//public void mouseDragged(MouseEvent evt) {}
	public void mouseEntered(MouseEvent evt) {}
	public void mouseExited(MouseEvent evt) {}
	//public void mouseMoved(MouseEvent evt) {}
	
	public void keyPressed(KeyEvent evt) 
	{
		requestFocusInWindow();
		System.out.println("clicked: " + clicked + " watered" + water);
		if (clicked && water)
		{
			if(evt.getKeyCode() == KeyEvent.VK_UP)
			{
				flowers = true; System.out.println("flowered");
				clicked = false;
			}
			//else flowers = flowers 
		}
		repaint();
	}
	public void keyTyped(KeyEvent evt)
	{
		char charIn = evt.getKeyChar();
		if(clicked)
		{
			if(charIn == '%'&&!water)
			{
				water = true;System.out.println("watered.");
				clicked = false;
			}
		}
		if(charIn == ' ')
		{
			flowers = false;
			water = false;
		}
		//else flowers = flowers; water = water
		repaint();
	}
	public void keyReleased(KeyEvent evt) {}
}
