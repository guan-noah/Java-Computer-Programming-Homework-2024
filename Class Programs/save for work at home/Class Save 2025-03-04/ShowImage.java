// 
// date:
// ShowImage.java
// This program shows:
//
//		1. Using a null layout
//		2. loading an image from a file and displays it to the screen.
//		3. implementing a JLabel component
//		4. uses Mouse and Key events

////////////////////////////////////////////////////////
// 2. Add imports for classes File, IOExcaption, ImageIO
//
import java.awt.			// for classes Graphics, Color, Font, Image

import java.awt.event.		// for classes KeyListener, MouseListener

import javax.swing.		// for classes JFrame, JPanel, JLabel

import java.			// for classes File, IOException, ImageIO


public class ShowImage
{
	public ShowImage ( )   
	{
	}

	public static void main (String[] args) 
	{
		ShowImage si = new ShowImage();
		si.run();
	}

	
	public void run()
	{
		JFrame frame = new JFrame ( "ShowImage" );
		
		// To close the JFrame
		frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
		
		////////////////////////////////////////////////////////
		// 1. Set the JFrame to a null layout
		
						
		////////////////////////////////////////////////////////
		// 3. Declare and initialize a JLabel object and add to frame
		
		
		
		
		// 3. Set the location and size of the label
		

		// Create the JPanel canvas,  add to frame
		DrawingArea canvas = new DrawingArea ( );
		
				
		frame.setBackground(Color.GRAY);
		frame.setSize( 800, 750 );
		frame.setLocation( 300, 0 );		//moves the frame to the middle of the screen
		frame.setResizable(false);
		frame.setVisible(true);
	}
} // closes ShowImage class

// canvas 
class DrawingArea extends JPanel implements MouseListener, KeyListener 
{
	////////////////////////////////////////////////////////
	// 2. Declare image object


	private int xpos, ypos;
	private boolean keyClear;

	private int sizeX, sizeY; // Calvin

	private String imageName;
	
	public DrawingArea ( )   
	{
		////////////////////////////////////////////////////////
		// 2. image
	
	
		xpos = ypos = 220;		// center the picture in the frame
		keyClear = true;
	
		sizeX = 217;	// Calvin pict 
		sizeY = 301;

		////////////////////////////////////////////////////////
		// 2. Provide Filename and load the image from the file


		// Give panel its color 
		
		
		////////////////////////////////////////////////////////
		// 1. Set the location and size of the canvas
	
	
		///////////////////////////////////////////////////////////
		// 4. add listeners for mouse and key

	}

public void getMyImage() 
{
	///////////////////////////////////////////////////
	// 2. Create a try-catch block for loading the image
	
}
	public void paintComponent ( Graphics g ) 
	{
		if ( keyClear ) 
			super.paintComponent ( g );	// blank the canvas
		///////////////////////////////////////////////////////////
		// 2. Draw the image that was loaded
		
		keyClear = false;
	}

	///////////////////////////////////////////////////////////
	// 4. add all mouse methods
	
	
	///////////////////////////////////////////////////////////
	// 4. add all key methods
	
} // closes DrawingArea
