
import java.awt.event.MouseListener;	
import java.awt.event.MouseEvent;

import javax.swing.JFrame;	
import javax.swing.JPanel;

import java.awt.Color;		
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Dimension;
//import java.awt.event.ChangeListener;
//import java.awt.event.ChangeEvent;
import javax.swing.JScrollBar;

///////////////////////// add Classes needed for Layouts ////////////////////////
import java.awt.FlowLayout;
/*
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
*/


public class MyLayout 
{
	private JFrame borderFrame;
	
	public static void main(String[] args) 
	{
		MyLayout ml = new MyLayout();
		ml.run();
	}
	
	public void run() 
	{
		makeBorderLayout();		// BorderLayout window
	}
	
	public void makeBorderLayout()
	{
		MyPanel panBL1;
		MyColorPanel colorPanel;
		// Create a JFrame with BorderLayout
		borderFrame = new JFrame("BorderLayout");	// Create the JFrame
		
		/////////////////////////////////////////////////
		// Set the layout to BorderLayout (default for JFrame)
		//
		borderFrame.setLayout(new FlowLayout());	// BorderLayout is default
		
		borderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		borderFrame.setSize(300, 300);
		borderFrame.setLocation(10, 10);
		
		// add grid
		int widthScalable = 1920*4/5;
		int heightScalable = (1080-50)*4/5;								//1920 x 1080 screen with a 50 px toolbar
		borderFrame.setSize(600, 500);										
		borderFrame.setLocation(widthScalable-600, heightScalable-500);		//sets it to the perfect screen edge (plus 50 to give space for toolbar)
		//frame.setLocation(1320, 530);
		
		System.out.println("frame width: " + borderFrame.getWidth() + "\nframe height: " + borderFrame.getHeight());
		System.out.println("widthScalable: " + widthScalable + "\nheightScalable: " + heightScalable + 
			"\nframeSize: (600, 500) \nframeLocation (" + (widthScalable-600) + ", " + (heightScalable-500) + ")");
		
		//set background to grid
		GridPanel grid = new GridPanel(new Dimension(borderFrame.getWidth(), borderFrame.getHeight()));
		grid.setPreferredSize(new Dimension(300, 120));
		borderFrame.setContentPane(grid);								//sets background to grid; ignores layout 
		//borderFrame.setContentPane(scroll);
		
		//create panels (Panel 1, Panel 2, JScrollBar, grid)
		panBL1 = new MyPanel(1, Color.RED); 
		panBL1.setPreferredSize(new Dimension(300, 120)); // 50, 30
		borderFrame.getContentPane().add(panBL1); 
				
		JPanel panBL2 = new MyPanel(2, Color.BLUE);
		panBL2.setPreferredSize(new Dimension(300, 120));
		borderFrame.getContentPane().add(panBL2);
		
		JScrollBar scroll = new JScrollBar(JScrollBar.VERTICAL, 50, 100, 0, 1000);
		borderFrame.add(scroll);
		
		// Make the JFrame visible
		borderFrame.setVisible(true);
	}
	
	class GridPanel extends JPanel
	{
		public int panelWidth, panelHeight;
		public GridPanel(Dimension xy)
		{
			panelWidth = (int)(xy.getWidth());
			panelHeight = (int)(xy.getHeight());
			System.out.println("panelWidth: " + panelWidth + "\npanelHeight: " + panelHeight);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			panelWidth = (int)(getWidth());
			panelHeight = (int)(getHeight());
			System.out.println("panelWidth: " + panelWidth + "\npanelHeight: " + panelHeight);
			
			Color gridNumBlue = new Color(0, 255, 255);
			
			//draw the gridlines
			for(int i = 0; i < panelWidth; i += 10)
			{
				g.setColor(Color.BLACK);
				if((i/10)%2 == 0)
					g.drawString("" + i, i, 10);						//the x coordinates (columns)
				else
					g.drawString("" + i, i + 5, 20);					//to shift everything down so you can actually read it
				
				g.setColor(Color.BLUE);
				g.drawLine(i, 0, i, panelHeight);						//x coords (column)
			}
			for(int i = 0; i < panelHeight; i += 10)
			{
				g.setColor(Color.BLACK);
				g.drawString("" + i, 5, i);								//the y coordinates (rows)
				
				g.setColor(Color.BLUE);
				g.drawLine(0, i, panelWidth, i);						//y coords (rows)
			}
			
			g.setColor(Color.WHITE);									//change color when done 
			//everything I draw below here will be white 
		}
	}
}
