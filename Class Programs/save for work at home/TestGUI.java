//TestGUI.java
/* Noah Guan
 * 03-06-2025
 * Per. 6 Java w/ Mr. Yu
 * TestGUI.java
 * Program #XX
 * Pseudocode: (comments outlined; I built my program on the comments) 
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.event.*;

public class TestGUI
{
	public TestGUI()
	{
		
	}
	public static void main(String[] args)
	{
		TestGUI tgui = new TestGUI();
		tgui.run();
	}
	public void run()
	{
		JFrame frame = new JFrame("Watch the garden grow");
		int widthScalable = 1920*4/5;
		int heightScalable = (1080-50)*4/5;								//1920 x 1080 screen with a 50 px toolbar
		int xFrameSize = 630;
		int yFrameSize = 450; 
		frame.setSize(630, 450);										
		frame.setLocation(widthScalable-630, heightScalable-450);		//sets it to the perfect screen edge (plus 50 to give space for toolbar)
		//frame.setLocation(1320, 530);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);		
		TestGUIPanel tguiPanel = new TestGUIPanel();
		frame.setContentPane(tguiPanel);
		frame.setVisible(true);
	}
}
class TestGUIPanel
{
	
}
