//GardenGrows.java
/* Noah Guan
 * 01-31-2025
 * Per. 6 Java w/ Mr. Yu
 * GardenGrows.java
 * Program #43
 * Pseudocode: (comments outlined; I built my program on the comments) 
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.event.*;

public class GardenGrows
{
	public GardenGrows()
	{
		
	}
	public static void main(String[] args)
	{
		GardenGrows gg = new GardenGrows();
		gg.growMyGarden();
	}
	public void growMyGarden()
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
		GardenPanel gPanel = new GardenPanel();
		frame.setContentPane(gPanel);
		frame.setVisible(true);
	}
}
class GardenPanel
{
	
}
