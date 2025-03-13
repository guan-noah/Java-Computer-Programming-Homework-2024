// Conlin
// 
// Pillsbury.java  
// A panel in which a button is used to make the panel giggle.
// Pillsbury Dough Boy. poke his belly and he giggled.  
// User interacts with a button labeled "press my tummy."  Upon pressing, "tee hee" 
// is drawnand the button gets relabeled "reset."  Upon pressing, screen refreshes, 
// meaning the text goes away.
// Working on:
// 1.  create an ActionListener class
// 2.  create a button to print a string when pressed.
// 3.  print string when button is pressed
//these 5 are for drawing the pillsbury file 
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//these next 5 are for standard awt 
import java.awt.Color;	
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JFrame;	
import javax.swing.JPanel;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

///////////////////////////////////
// 1.  import libraries for JButton
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pillsbury
{	
	public static void main( String[] args )
	{
		Pillsbury pills = new Pillsbury();
		pills.runIt();
	}
	
	public Pillsbury()
	{
	}
	
	public void runIt()
	{
		JFrame frame = new JFrame("Pillsbury Dough boy");
		frame.setSize( 400, 300);				
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setLocation(400,50);
		frame.setResizable(true);
		PillsburyPanel pillsPanel = new PillsburyPanel(); 		
		frame.getContentPane().add( pillsPanel );	
		frame.setVisible(true);		
	}
}

////////////////////////////// class with ActionListener  /////////////////
// 1.  
class PillsburyPanel extends JPanel implements ActionListener, MouseListener
{
	private boolean pressed;											// used to tell is button pressed
	/////////
	// 2.  declare the button
	private JButton pillsButton;
	
	////////////// write constructor
	public PillsburyPanel()
	{
		pressed = false;												//initialize to false (button not pressed yet)
		//////
		// 2.  instantiate the button.  add everything needed.
		pillsButton = new JButton("Press my belly. <initial constructor>");
		pillsButton.addActionListener(this);									
		add(pillsButton);													//add new button with label "press my belly" and actionlistener method call 
	}
	
	public void paintComponent(Graphics g)	// paint component
	{
		super.paintComponent (g);	// draw background		
		Font font = new Font ("Serif", Font.BOLD, 30);					//
		g.setFont( font );	
		
		////////////////////////////
		// 3.  conditionally draw string, reset.
		if ( pressed )													//
		{
			g.drawString("tee hee", 10, 100);
			//resized = (getWidth() != originalWidth || getHeight() != originalHeight)
		}
	}

	////////////////////////////////
	// 2. write event handler method for button 
	public void actionPerformed( ActionEvent evt )
	{ 	// Here is an example showing getText.  We will use a different one
		String command = pillsButton.getText();								//the text of the button that we initialized it with 
		if ( command.equals("Press my belly. <initial constructor>") || 
			command.equals("Press my belly. <new button reset>")) // This must be exactly same string!
		{
			pressed = true;
			pillsButton.setText("reset");
		}
		else
		{
			pressed = false
			//pressed = (false || pressed);								//tee hee doesn't go away after first initialization 
			pillsButton.setText("Press my belly. <new button reset>");
		}
		repaint();
	}
	
	public void mousePressed(MouseEvent evt) {}
	public void mouseReleased(MouseEvent evt) {}
	public void mouseClicked(MouseEvent evt)
	{
		
	}
	public void mouseEntered(MouseEvent evt) {}
	public void mouseExited(MouseEvent evt) {}
}
