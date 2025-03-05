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
		JFrame frame = new JFrame("Watch the garden grow");
		
		double scale = 4/5;												//easier to change scale later through this
		int toolBarHeight = 50;											//toolbar is 50 pixels tall
		int widthScalable = (int)(1920*scale);
		int heightScalable = (int)((1080-toolBarHeight)*scale);			//1920 x 1080 screen with a 50 px toolbar
		//supposed to be x = 1000, y = 800
		int xFrameSize = 1000;
		int yFrameSize = 800;
		frame.setSize(xFrameSize, yFrameSize);										
		frame.setLocation(widthScalable-xFrameSize, heightScalable-yFrameSize);//sets it to the perfect screen edge (plus 50 to give space for toolbar)
		//frame.setLocation(1320, 530);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);		
		AccessibilityPanel aPanel = new AccessibilityPanel();
		frame.setContentPane(aPanel);
		frame.setVisible(true);
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
	public AccessibilityPanel()
	{
		colorParameterVar = 100;
		backgroundColor = new Color(colorParameterVar, colorParameterVar, colorParameterVar);
		setBackground(backgroundColor);
		fontSize = 12;
		this.addMouseListener(this);
		this.addKeyListener(this);
	}
	public void drawRectanglesWithLabels(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(10, 10, 100, 20);
		g.fillRect(115, 10, 100, 20);
		g.setColor(Color.BLACK);
		g.drawString("brighter", 15, 80);
		g.drawString("darker", 115, 80);
	}
	public void medicalPageContent(Graphics g) {} //Assume this method is complete.
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		drawRectanglesWithLabels(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.PLAIN, fontSize));
		medicalPageContent(g);
	}
	public void keyPressed(KeyEvent evt)
	{
		
	}
	public void keyReleased(KeyEvent evt)
	{
		
	}
	public void keyTyped(KeyEvent evt){}
	
	public void mousePressed(MouseEvent evt)
	{
		
	}
	public void mouseClicked(MouseEvent evt)
	{
		
	}
	public void mouseReleased(MouseEvent evt){}
	public void mouseEntered(MouseEvent evt){}
	public void mouseExited(MouseEvent evt){}
}
//~ */
