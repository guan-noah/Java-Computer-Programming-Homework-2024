// Noah Guan
// 2025 03 28
// SneezePanels.java
/*  IDEA of this program:  PanelHolder, which is added to the frame, holds two panels,
LeftPanel and RightPanel, which are added to PanelHolder in a GridLayout. 
The LeftHolder has a BorderLayout and has two panels-a direction panel, with a FlowLayout 
that has a the button, and a textField panel that contains the textField.  When
the button is pressed, Achoo and a yellow oval are drawn on the Right Panel.  
When the user types in "Bless you" in the textField, the RightPanel is erased
and variables are reset.
*/
/// Testing:  Only clicking on the button will draw on the right panel.  Only typing 
/// in "Bless you" will clear it.
/// Try clicking anywhere other that the button.  This should will not change anything.  
/// Typing anything other than "Bless you" will not reset the panels.

import java.awt.event.ActionListener;	
import java.awt.event.ActionEvent;

import javax.swing.JFrame;	
import javax.swing.JPanel;
import javax.swing.JButton;												//this line was not added either 
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;		
import java.awt.Graphics;
import java.awt.Font;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout; 											//this line was not added in original skeleton code but is required for PanelHolder. 

public class SneezePanels
{	
	public static void main( String[] args )
	{
		SneezePanels sp = new SneezePanels();
		sp.run();
	}
	
	public SneezePanels()
	{	
	}

	public void run()
	{
		JFrame sneezeFrame = new JFrame ("Sneeze and Bless you.");
		
		sneezeFrame.setSize( 600, 400);				
		sneezeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		sneezeFrame.setLocation(400,50);
		sneezeFrame.setResizable(true);
		PanelHolder pHolder = new PanelHolder(); 
		sneezeFrame.add( pHolder );
		
		sneezeFrame.setVisible(true);	
	}
}

/** This panel holds two panels-one on the left and one on the right, aptly named **/
class PanelHolder extends JPanel
{
	private RightPanel rp;												// these are field variables so the nested classes have access to them
	private boolean nosePressed;										// otherwise, we have to use getter-setter methods
	private Font font;
	
	public PanelHolder()
	{
		setLayout( new GridLayout(1, 2) );								//initialize layout 
		
		nosePressed = false;											//initialize fvs 
		font = new Font("Serif", Font.BOLD, 20);
		
		LeftPanel lp = new LeftPanel();									//add left panel to PanelHolder
		add(lp);
		
		rp = new RightPanel();											//add right panel to PanelHolder
		add( rp );
	}

	/** This panel will have a BorderLayout
	// It will have the directions panel in the center, and the 
	// textField panel in the south. **/
	class LeftPanel extends JPanel
	{	
		private Color leftBlue;
		public LeftPanel()
		{	
			setLayout(new BorderLayout());
			leftBlue = new Color(150, 200, 230);						// custom color
			setBackground(leftBlue);									// setBackground( Color.MAGENTA );
			
			DirectionPanel dirP = new DirectionPanel ();				//make panels
			TFPanel tfp = new TFPanel();
			
			add ( dirP, BorderLayout.CENTER );							//add panels
			add ( tfp, BorderLayout.SOUTH );
		}
	}
	
	/** DirectionPanel will print the directions and contain the 
	// nose button.  It has a FlowLayout.  It will use a 
	// ButtonHandler for actionPerformed. **/
 	class DirectionPanel extends JPanel
 	{
		private Button1Handler b1h;										//didn't need a fv -- we could have directly added it nose.addActionListener(new Button1Handler())
		private JButton nose;
		
 		public DirectionPanel()
 		{
			setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 55));		//default FlowLayout will not cut it. we need a huge vertical gap. 
			setBackground(Color.CYAN);									//use these #s for now; 1000 makes sure that they're never going to see it
			
			JLabel apple = new JLabel("   ");
			JLabel banana = new JLabel("   ");
			JLabel directions = new JLabel("Directions: Press button to tickle the nose");
			add(apple);
			add(banana);
			add(directions);
			
			nose = new JButton("Nose");
			b1h = new Button1Handler();									//add Button1Handler and TextFieldHandler in the action listener 
			nose.addActionListener(b1h);
			add(nose);
 		}	
 		
 		public void paintComponent( Graphics g )
 		{
			super.paintComponent(g);
 		}
 	}

	/** The TFPanel will have a FlowLayout and contain a text field
	// that will be on the left.  It uses a handler class for 
	// actionPerformed **/
 	class TFPanel extends JPanel
 	{
		private Color tfGreen;
		private JTextField tf1;
 		public TFPanel()
 		{
			//flowLayout = default 
			tfGreen = new Color(215, 240, 175);
			setBackground(tfGreen);
			
			JLabel prompt = new JLabel("Type: Bless you.");				//make a prompt so user knows what to put in
			add(prompt);
			
			tf1 = new JTextField(15);
			tf1.addActionListener(new TextFieldHandler());
			add(tf1);
 		}
 	}
 	
	/** The RightPanel is used to draw "Achoo" and a yellow oval when the
	// button is pressed and cleared when "Bless you" is typed in
	// the textField **/
 	class RightPanel extends JPanel
 	{
		private Color rightRed; 										//fv for efficiency (don't keep creating the color, just store it)
 		public RightPanel()
 		{
			//flowLayout (default)
			
			rightRed = new Color(230, 115, 135);						//initialize and set background lightRed
			setBackground(rightRed);
			
 		}
 		/**paintComponent to draw "Achoo" and the yellow oval when button 
 		//pressed (going to receive command from Button1Handler)**/
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			//g.setColor(rightRed);										//reset background (not needed)
			if(nosePressed)												//nosePressed is set to true in Button1Handler
			{
				g.setFont(font);
				g.setColor(Color.BLACK);
				g.drawString("Achoo!", 110, 70);						//get actual #s centered later 
				
				g.setColor(Color.YELLOW);
				g.fillOval(100, 100, 80, 120);
			}
			else 														//nosePressed is set to false in TextFieldHandler
			{
				nosePressed = false;
				
			}
		}
 	}

	/** When the button is pressed, the method actionPerformed is 
	// used to call paintComponent in RightPanel **/
 	class Button1Handler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			nosePressed = true;
			rp.repaint();												//call paintComponent in RightPanel (as stated 6-5 lines ago)
		}
	}	// end class Button1Handler	
	
	/** When the user types in "Bless you" in the textField, the 
	// boolean is reset	and RightPanel's paintComponent is called **/
	class TextFieldHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			JTextField tf = (JTextField)e.getSource();
			if(tf.getText().trim().equalsIgnoreCase("Bless you."))
			{
				nosePressed = false;
				tf.setText("");
				rp.repaint();											//call paintComponent in RightPanel (as stated 6-5 lines ago)
			}
		}
	}	// end class TextFieldHandler
}
