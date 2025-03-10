import java.awt.*;
import javax.swing.*;
public class DrawInClass extends JPanel
{
	public DrawInClass()
	{
		super();
		setBackground(Color.PINK);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//g.drawString("hello", 50, 100);
		//g.drawLine(50, 100, 100, 200);
	}
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Masterpiece.java");
		int widthScalable = 1920*4/5;
		int heightScalable = (1080-50)*4/5;								//1920 x 1080 screen with a 50 px toolbar
		frame.setSize(600, 500);										
		frame.setLocation(widthScalable-600, heightScalable-500);		//sets it to the perfect screen edge (plus 50 to give space for toolbar)
		//frame.setLocation(1320, 530);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);		
		DrawInClass dic = new DrawInClass();
		frame.setContentPane(dic);
		frame.setVisible(true);
		
		//dic.paintComponent();
	}
}
/*
Drawing in JPanels 
We'll always draw in a JPanel (not the Frame). 
Modify Panel class! Inside paintComponent(g) add:
	g.drawString("hello", 50, 100)
	g.drawLine(50, 100, 100, 200)
Try it! 50, 100 is where with respect to the word? 

Play1: If the Frame is placed at a new location, what happens to the location of the word and line? 
Play2: make a font (per your reading questions) of size 50, change the color, and write the "hello " using it. 
*/
