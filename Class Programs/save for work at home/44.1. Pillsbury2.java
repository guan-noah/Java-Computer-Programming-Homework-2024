/*
Noah Guan
03/12/2025
P.6 JAVA w/ Mr. Yu
Pillsbury2.java
A panel in which a button is used to make the panel giggle.
Pillsbury Dough Boy. poke his belly and he giggled.  
User interacts with a button labeled "press my tummy."  Upon pressing, "tee hee" 
is drawn and the button gets relabeled "reset."  Upon pressing, screen refreshes, 
meaning the text goes away.

This version uses a separate class to handle the button action. 
ButtonHandler then implements ActionListener, not the JPanel. 
THIS IS OUR STANDARD!!!!!!!!!!!

Working on:
1.  create an ActionListener class
2.  create a button to print a string when pressed.
3.  print string when button is pressed

*/

//import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
*/

import javax.swing.JPanel;
import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JLabel;												//pretty sure not needed in this example

public class Pillsbury2
{
	public Pillsbury2()
	{
		
	}
	public static void main(String[] args)
	{
		Pillsbury2 pills2 = new Pillsbury2();
		pills2.runIt();
	}
	public void runIt()
	{
		JFrame frame = new JFrame("Pillsbury Dough Boy");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 50);
		frame.setResizable(true);										//default true 
		PillsburyP2 pillsPanel2 = new PillsburyP2();
		frame.setContentPane(pillsPanel2);
		frame.setVisible(true);
	}
}

class PillsburyP2 extends JPanel
{
	private boolean bbpressed, obpressed;
	private JButton bbutton, obutton;
	private Font font;
	
	public PillsburyP2()
	{
		font = new Font("Serif", Font.BOLD, 30);
		
		bbpressed = false;
		bbutton = new JButton("Press my belly.");						//construct button 		
		BellyButtonHandler bbhandler = new BellyButtonHandler();		//this is so the actionPerformed is dedicated to this button only
		bbutton.addActionListener(bbhandler);							//add listener to button
		add(bbutton);													//add button to panel (Pillsbury2)
		
		obpressed = false;
		obutton = new JButton("special button");
		OtherButtonHandler obhandler = new OtherButtonHandler();
		obutton.addActionListener(obhandler);
		add(obutton);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setFont(font);
		if(bbpressed)
		{
			g.drawString("tee hee", 100, 100);
			//pressed = false;											//original code; flawed
		}
	}
	
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
	}
	
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
			repaint();
		}
	}																	//end class ButtonHandler
}
