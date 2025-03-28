/*
Noah Guan
03/19/2025
P.6 JAVA w/ Mr. Yu
Pillsbury2Panel.java

This program: 
1. This has a button handler class to respond to the action. 
2. 2 panels are added to 1 panel, which as a FlowLayout, and so is regular size. 
3. The panels use common info 
4. The button is added to the FlowLayout panel, and so is regular size. 
5. Try different layouts. 

*/

//import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;												//add this library in to set size of button for null layout


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*s
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
*/

import javax.swing.JPanel;
import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JLabel;												//pretty sure not needed in this example

public class Pillsbury2Panel
{
	public Pillsbury2Panel()
	{
		
	}
	public static void main(String[] args)
	{
		Pillsbury2Panel pills2P = new Pillsbury2Panel();
		pills2P.runIt();
	}
	public void runIt()
	{
		JFrame frame = new JFrame("Pillsbury Dough Boy");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 50);
		frame.setResizable(true);										//default true 
		Pills2Panel pills2Panel = new Pills2Panel();
		frame.setContentPane(pills2Panel);//could also use frame.getContentPane().add(pills2Panel);
		frame.setVisible(true);
	}
}
/** PANEL 1 (layout panel) **/
class Pills2Panel extends JPanel
{
	private DrawPanel drawP;
	private PressPanel pressP;
	private boolean pressed;
	
	public Pills2Panel()
	{
		setLayout(new GridLayout(1, 2));
		pressP = new PressPanel();										//essentially the button 
		add(pressP);
		drawP = new DrawPanel();
		add(drawP);
	}
	
	/** PANEL 2 () **/
	public class PressPanel extends JPanel
	{
		private JButton button1;
		//private JButton bbutton, obutton;
		//private boolean bbpressed, obpressed;
		
		public PressPanel()
		{
			//setLayout(null);
			
			//button.setSize() won't work with premade layouts. 
			setLayout(new FlowLayout());								//gives size what is needed to fit the string
			//if 1, 2 --> there is only thing added, so the 2 columns is shrunk to 1 
			//if 2, 1 --> there are 2 rows, the button on top and nothing on the bottom 
			
			button1 = new JButton("Press my belly.");						//construct button
			pressed = false;												//from Pillsbury2 
			ButtonHandler bhandler = new ButtonHandler();			//this is so the actionPerformed is dedicated to this button only
			button1.addActionListener(bhandler);							//add listener to button
			add(button1);
			
			//default font: sans serif, size 12
			
			//start without setSize 
			//button1.setSize(new Dimension(70, 150);						//this is only for null layout 
			//this is so the actionPerformed is dedicated to this button only. 
			
			/* //other button 1 (copy of standard)
			bbpressed = false;												//from Pillsbury2 
			bbutton = new JButton("Press my belly.");						//construct button 		
			BellyButtonHandler bbhandler = new BellyButtonHandler();		//this is so the actionPerformed is dedicated to this button only
			bbutton.addActionListener(bbhandler);							//add listener to button
			add(bbutton);													//add button to panel (Pillsbury2Panel)
			
			/* //other button 2
			obpressed = false;
			obutton = new JButton("special button");
			OtherButtonHandler obhandler = new OtherButtonHandler();
			obutton.addActionListener(obhandler);
			add(obutton);
			*/
		}
		class ButtonHandler implements ActionListener						//nested class in order to access button
		{
			public void actionPerformed(ActionEvent evt)				//doesn't need to be nested 
			{
				String command = evt.getActionCommand();				//could take this class out and pass in the button as a parameter 
				if(command.equalsIgnoreCase("Press my belly."))
				{
					pressed = true;
					button1.setText("reset");
				}
				else
				{
					pressed = false;
					button1.setText("Press my belly.");
				}
				drawP.repaint();
			}
		}																	//end class ButtonHandler
	}
	/** PANEL 3 (drawing) **/
	public class DrawPanel extends JPanel
	{
		public DrawPanel()
		{
			setBackground(Color.MAGENTA);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Font font = new Font("Serif", Font.BOLD, 30);
			g.setFont(font);
			if(pressed)
			{
				g.drawString("tee hee", 100, 100);
				//pressed = false;											//original code; flawed
				//notice the position of the String. Remember it is relative to this panel only! 
			}
		}
	}
		
		/*//other button 
		class BellyButtonHandler implements ActionListener						//nested class in order to access button
		{
			public void actionPerformed(ActionEvent evt)
			{
				String command = evt.getActionCommand();
				if(command.equalsIgnoreCase("Press my belly."))
				{
					bbpressed = true;
					bbutton.setText("reset");
				}
				else
				{
					bbpressed = false;
					bbutton.setText("Press my belly.");
				}
				drawP.repaint();
			}
		}																	//end class ButtonHandler
		class OtherButtonHandler implements ActionListener					//same mechanics as BellyButtonHandler 
		{
			public void actionPerformed(ActionEvent evt)
			{
				String command = evt.getActionCommand();
				if(command.equalsIgnoreCase("special button"))
				{
					obpressed = true;
					obutton.setText("clicked");
				}
				else
				{
					obpressed = false;
					obutton.setText("special button");
				}
				repaint();
			}
		}*/
}
