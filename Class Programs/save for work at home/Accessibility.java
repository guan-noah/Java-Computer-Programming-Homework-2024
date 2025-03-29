//Accessibility.java
/* Noah Guan
 * 03-04-2025
 * Per. 6 Java w/ Mr. Yu
 * Accessibility.java
 * Program #XX
 * Pseudocode: (comments outlined; I built my program on the comments) 
 * Homework for 03-04-2025
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.event.*;

public class Accessibility
{
	public static void main(String[] args)
	{
		Accessibility a = new Accessibility();
		a.growMyGarden();
	}
	public void growMyGarden()
	{
		JFrame frame = new JFrame("Accessibility");
		
		double scale = (double)4/5;										//easier to change scale later through this
		int toolBarHeight = 50;											//toolbar is 50 pixels tall
		int widthScalable = (int)(1920*scale);
		int heightScalable = (int)((1080-toolBarHeight)*scale);			//1920 x 1080 screen with a 50 px toolbar
		//supposed to be x = 1000, y = 800
		int xFrameSize = 1000;
		int yFrameSize = 800;
		xFrameSize = checkSizes(xFrameSize, widthScalable);
		yFrameSize = checkSizes(yFrameSize, heightScalable);
		//System.out.println(scale + " " + widthScalable + " " + heightScalable + " " + xFrameSize + " " + yFrameSize);
		
		frame.setSize(xFrameSize, yFrameSize);										
		frame.setLocation(widthScalable-xFrameSize, heightScalable-yFrameSize);//sets it to the perfect screen edge (plus 50 to give space for toolbar)
		//frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);		
		AccessibilityPanel aPanel = new AccessibilityPanel();
		frame.setContentPane(aPanel);
		frame.setVisible(true);
	}
	public int checkSizes(int frameValue, int screenValue)
	{
		if(frameValue > screenValue)
			return screenValue;
		else
			return frameValue; 
	}
}
/* //tester replacement class to see if JFrame works 
class AccessibilityPanel extends JPanel
{
	public AccessibilityPanel()
	{
		setBackground(Color.RED);
	}
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 1000, 200);
		g.setFont(new Font("Times New Roman", Font.BOLD + Font.ITALIC, 100));
		g.drawString("hi", 0, 200);
	}
}
*/
//~ /*
class AccessibilityPanel extends JPanel implements MouseListener, KeyListener
{
	private Color backgroundColor;
	private int fontSize, colorParameterVar;
	
	//private int count;
	public AccessibilityPanel()
	{
		colorParameterVar = 100;
		backgroundColor = new Color(colorParameterVar, colorParameterVar, colorParameterVar);
		setBackground(backgroundColor);
		fontSize = 12;
		this.addMouseListener(this);
		this.addKeyListener(this);
		//count = 0;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//System.out.println("backgroundcolor: " + colorParameterVar);
		setBackground(new Color(colorParameterVar, colorParameterVar, colorParameterVar));
		drawRectanglesWithLabels(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.PLAIN, fontSize));
		medicalPageContent(g);
		writeDirections(g);
	}
	public void drawRectanglesWithLabels(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(10, 10, 100, 20);
		g.fillRect(115, 10, 100, 20);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		g.drawString("brighter", 10+5, 30-5);
		g.drawString("darker", 115+5, 30-5);
	}
	public void medicalPageContent(Graphics g) {} //Assume this method is complete.
	public void writeDirections(Graphics g)	
	{	/* You can assume some magic allows the words to wrap so the text is not off of  
		 the screen. For us, this allows us to see the font size change in the text. */
		g.drawString ("Press mouse on the panel.  To increase the font size, press the UP arrow. \n" +
			"To decrease the font size, press the DOWN arrow. \n" +
			"To make the screen brighter, click the box labelled brighter. \n" +
			"To make the screen darker, click the box labelled darker. \n", 50, 300); 
	}
	public void mousePressed(MouseEvent evt) 
	{
		requestFocusInWindow();
		int currentX = evt.getX();
		int currentY = evt.getY();
		if(currentX > 10 && currentX < 110 && currentY > 10 && currentY < 30)//if mouse coordinates are inside box (for brighter)
			colorParameterVar += 3;
		else if(currentX > 115 && currentX < 215 && currentY > 10 && currentY < 30)//if mouse coordinates are inside box (for darker)
			colorParameterVar -= 3;
		if (colorParameterVar > 255)
			colorParameterVar = 255;
		else if(colorParameterVar < 34)
			colorParameterVar = 34;
		//System.out.println("mouse coords: " + currentX + " " + currentY);
		repaint();
	}
	public void mouseClicked(MouseEvent evt) {}
	public void mouseReleased(MouseEvent evt){}
	public void mouseEntered(MouseEvent evt){}
	public void mouseExited(MouseEvent evt){}
	
	public void keyPressed(KeyEvent evt) 
	{
		int keyTyped = evt.getKeyCode();
		if(keyTyped == KeyEvent.VK_UP)
			fontSize += 5;
		else if(keyTyped == KeyEvent.VK_DOWN)
			fontSize -= 5;
		
		if(fontSize > 80)
			fontSize = 80;
		else if(fontSize < 8)
			fontSize = 8;
		/*
		System.out.print("keyTyped: ");
		if(keyTyped == KeyEvent.VK_UP)
			System.out.print("VK_UP ");
		else if(keyTyped == KeyEvent.VK_DOWN)
			System.out.print("VK_DOWN ");
		else
			System.out.print(keyTyped);
		System.out.println("font size: " + fontSize);
		*/
		repaint();
	}
	public void keyReleased(KeyEvent evt) {}
	public void keyTyped(KeyEvent evt) {}
}
//~ */
