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
		JPanel garden = new JPanel();
		frame.setContentPane(garden);
		frame.setVisible(true);
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
	}
	public void paintComponent(Graphics g)
	{
		drawGarden(g);
		showFlowers(g);
	}
	public void drawGarden(Graphics g)
	{
		int[] gardenInfo = new int[] {100, 100, 1000, 500};
		if (water)														//
		{
			g.setColor(Color.GREEN);//draw garden green
		}
		else
		{
			g.setColor(Color.PINK);//draw garden pink
		}
		g.fillRect(gardenInfo[0], gardenInfo[1], gardenInfo[2], gardenInfo[3]);//actually draw the garden 
	}
	public void showFlowers(Graphics g)
	{
		if(flowers)														//
		{
			g.setColor(Color.YELLOW);
			//draw 5 x 3 circles in nested for loop (50 x 50 px, 200 px between)
			int circleDiameter = 50; 
			int space = 200;
			for (int x = 0; x < 5; x++)
			{
				for (int y = 0; x < 3; y++)
				{
					g.fillOval(150+(circleDiameter + space)*x, (150+circleDiameter + space)*y, circleDiameter, circleDiameter);				
				}
			}
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
		if (clicked)
			flowers = (evt.getKeyCode() == KeyEvent.VK_UP);
	}
	public void keyTyped(KeyEvent evt)
	{
		if(clicked)
			water = (evt.getKeyChar() == '%');
	}
	public void keyReleased(KeyEvent evt) {}
}
