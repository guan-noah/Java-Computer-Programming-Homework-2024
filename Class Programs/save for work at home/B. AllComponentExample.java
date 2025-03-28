// AllComponentExample.java
// This program gives an example of using several components as examples.

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;	
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AllComponentExample
{
	//private JPanel north, south, east, west, center;
	private JButton button1, button2;
	private JCheckBox checkBox1, checkBox2;
	private JTextField textField1;
	private JTextArea textArea1;
	private JRadioButton choice1, choice2, choice3;
	private ButtonGroup bg;
	private JSlider slider1;
	
	public static void main(String[] args) 
	{
		AllComponentExample ce = new AllComponentExample();
		ce.makeThem();
	}
	
	//panel holder 
	public void makeThem() 
	{
		// Create a frame to hold everything
		JFrame frame = new JFrame ("Component Example");
		frame.setSize(500, 600);
		frame.setLocation(10, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.GRAY);
		
		// Initialize panels, Note, Given that the same basic thing is occurring on each
		// line, it is ok to have these 2 statements on each line.
		JPanel north = new JPanel(new GridLayout(1,3));  north.setBackground(Color.RED);
		JPanel south = new JPanel(new GridLayout(3,1));  south.setBackground(Color.BLUE);
		JPanel east = new JPanel(new GridLayout(3,1));	 east.setBackground(Color.YELLOW);
		JPanel west = new JPanel(new BorderLayout(0, 20) );  west.setBackground(Color.GRAY);
		JPanel center = new JPanel(new BorderLayout(0, 20)); center.setBackground(Color.MAGENTA);
		
		// JButtons
		JLabel jl1 = new JLabel("JButtons");
		jl1.setFont(new Font("Serif", Font.BOLD, 20));
		north.add(jl1);
		button1 = new JButton("Hello");
		Button1Handler b1handler = new Button1Handler();
		button1.addActionListener(b1handler);
		button2 = new JButton("Goodbye");
		Button2Handler b2handler = new Button2Handler();
		button2.addActionListener(b2handler);
		north.add(button1);
		north.add(button2);
		
		// JCheckBox
		makeCheckBoxes(east);
		
		// JTextField & JTextArea
		//~ /*
		JLabel jl3 = new JLabel("Text Inputs");
		jl3.setFont(new Font("Serif", Font.BOLD, 20));
		south.add(jl3);
		//~ */
		textField1 = new JTextField("I'm a JTextField", 20); // take out the 20.  Did it change? Why?
		textField1.setEditable(true);
		south.add(textField1);
		
		textArea1 = new JTextArea("This is a JTextArea");
		// comment out the line above and the JScrollPane line,  uncomment out the next line with textArea1.		
		//textArea1 = new JTextArea("This is a JTextArea", 10, 5);  // change the width to see the effect
		textArea1.setLineWrap(true);  // goes to the next line when printing the text.
		textArea1.setWrapStyleWord(true); // prevents a word from being split 
		JScrollPane scroller = new JScrollPane(textArea1);	// type inside the area and see the effect
		south.add(textArea1);		// press enter inside repeatedly notice what gets resized and what does not
		
		// JMenu
		JLabel jl4 = new JLabel("Menu");
		jl4.setFont(new Font("Serif", Font.BOLD, 20));
		west.add(jl4, BorderLayout.NORTH);

		JMenuBar calendarBar = makeCalendarMenuBar();
		west.add(calendarBar, BorderLayout.CENTER);	 // Change CENTER to SOUTH and see how 
													// the look changes.
		// To help you play with it, comment out line west.add(calendarBar, BorderLayout.CENTER);	
		// then un-comment out the following code
		/*
		JPanel menuPanel = new JPanel(); 
		menuPanel.setBackground(Color.YELLOW);
		menuPanel.add(calendarBar);
		west.add(menuPanel, BorderLayout.CENTER);
		*/
		
		// JSlider
		JLabel jl5 = new JLabel("JSlider");
		jl5.setFont(new Font("Serif", Font.BOLD, 20));
		center.add(jl5);
		makeSlider();
		center.add(slider1, BorderLayout.NORTH);
		
		// JRadioButtons
		JPanel rButtonPanel = makeRBPanel();
		center.add(rButtonPanel, BorderLayout.CENTER);
		
		// Put frame together
		frame.getContentPane().add(north, BorderLayout.NORTH);
		frame.getContentPane().add(south, BorderLayout.SOUTH);
		frame.getContentPane().add(east, BorderLayout.EAST);
		frame.getContentPane().add(west, BorderLayout.WEST);
		frame.getContentPane().add(center, BorderLayout.CENTER);
		frame.setVisible(true);
		textField1.requestFocusInWindow();	// this makes the textField have the focus
	}
	
	// the following methods are examples of making the previous method shorter and doing 
	// the work in another method.  There are different types, one that takes a JPanel,
	// another that returns a JPanel, another with neither.
	
	public void makeCheckBoxes(JPanel eastIn)
	{
		JLabel jl2 = new JLabel("JCheckBox");
		jl2.setFont(new Font("Serif", Font.BOLD, 20));
		eastIn.add(jl2);
		
		checkBox1 = new JCheckBox("hungry");
		CheckBoxListener cblistener = new CheckBoxListener();
		checkBox1.addActionListener(cblistener);
		checkBox1.setSelected(true);
		eastIn.add(checkBox1);
		checkBox2 = new JCheckBox("tired");
		checkBox2.addActionListener(cblistener);
		checkBox2.setSelected(false);
		eastIn.add(checkBox2);
	}
	
	public JMenuBar makeCalendarMenuBar()
	{
		JMenuBar bar = new JMenuBar();
		JMenu calendar = new JMenu("Calendar");
	
		JMenuItem jan = new JMenuItem("January");
		JMenuItem feb = new JMenuItem("February");
		JMenuItem mar = new JMenuItem("March");
	
		CalendarMenuHandler cmh = new CalendarMenuHandler();		
		jan.addActionListener(cmh);
		feb.addActionListener(cmh);	
		mar.addActionListener(cmh);
		
		calendar.add( jan );
		calendar.add( feb );
		calendar.add( mar );
	
		bar.add( calendar);
	
		return bar;
	}
	
	public void makeSlider()
	{
		slider1 = new JSlider(0, 20, 5);
		slider1.setMajorTickSpacing(5);	// create tick marks on slider every 5 units
		slider1.setPaintTicks(true);
		slider1.setLabelTable( slider1.createStandardLabels(5) ); // create labels on tick marks
		slider1.setPaintLabels(true);
		slider1.setOrientation(JSlider.HORIZONTAL);
		SliderListener slistener1 = new SliderListener();
		slider1.addChangeListener(slistener1);
	}
	
	public JPanel makeRBPanel()
	{
		JPanel rbPanel = new JPanel();
		rbPanel.setLayout( new FlowLayout(FlowLayout.CENTER, 200, 50) );
		ButtonGroup bg = new ButtonGroup();

		JLabel question1 = new JLabel("Select a color");
		question1.setFont(new Font("Serif", Font.BOLD, 20));
		rbPanel.add( question1 );
		
		RButtonHandler rbh = new RButtonHandler();
		choice1 = new JRadioButton("RED");	// construct button  
		bg.add(choice1);					// add button to panel	
		choice1.addActionListener(rbh); 	// add listener to JRadioButton
		rbPanel.add(choice1);				// add JRadioButton to Panel
		
		choice2 = new JRadioButton( "BLUE" );	// construct button  
		bg.add( choice2 );		// add b2utton to panel	
		choice2.addActionListener(rbh); 		// add listener to button
		rbPanel.add( choice2 );
		
		choice3 = new JRadioButton( "MAGENTA" );	// construct button  
		bg.add( choice3 );		// add b3utton to panel	
		choice3.addActionListener(rbh); 	// add listener to button
		rbPanel.add( choice3 );
		
		return rbPanel;
	}
	
	// Listener/Handler class for the menu in the west panel
	class CalendarMenuHandler implements ActionListener 
	{
		public void actionPerformed( ActionEvent evt ) 
		{
			String phrase = "";
			String command = evt.getActionCommand();
			if ( command.equals( "January" ) )
				phrase = "The first month";	
			else if ( command.equals( "February" ) )
				phrase = "The second month";	
			else if ( command.equals( "March" ) )	
				phrase = "The third month";				
	
			textArea1.setText(phrase + " " + command + " was selected in the menu.");	
		}
	}
	
	// Listener for button1 in north panel
	class Button1Handler implements ActionListener 
	{
		public void actionPerformed(ActionEvent evt) 
		{
			String command = evt.getActionCommand();
			if (command.equals("Hello")) 
			{
				button1.setText("Huh?");
				button2.setText("Click me");
			}
			else 
			{
				button1.setText("Hello");
				button2.setText("Goodbye");
			}
		}
	}	// end class Button1Handler	
	
	// Listener for button2 in north panel
	class Button2Handler implements ActionListener 
	{
		public void actionPerformed(ActionEvent evt) 
		{
			String command = evt.getActionCommand();
			if (command.equals("Click me")) 
				button2.setText("Try again");
			else if (command.equals("Try again")) 
				button2.setText("Sorry");
			else 
				button2.setText("Goodbye");
		}
	}	// end class Button2Handler
	
	// Listener for JCheckBox
	class CheckBoxListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent evt) 
		{
			String cmd = evt.getActionCommand();
			if (cmd.equals("hungry")) 
			{
				checkBox1.setSelected(true);
				checkBox2.setSelected(false);
				textField1.setText("hungry is selected; tired is not selected");
			}
			else 
			{
				checkBox1.setSelected(false);
				checkBox2.setSelected(true);
				textField1.setText("hungry is not selected; tired is selected");
			}
		}
	}

	class SliderListener implements ChangeListener 
	{
		public void stateChanged (ChangeEvent evt) 
		{
			int val = slider1.getValue();	// get the value of the slider
			textField1.setText("JSlider value = " + val);
		}
	}
	
	// Listener/Handler class for the JRadioButtons in the west panel
	class RButtonHandler implements ActionListener 
	{
		public void actionPerformed( ActionEvent evt ) 
		{
			String phrase = "";
			String command = evt.getActionCommand();
			if ( command.equals( "RED" ) )
				phrase = "I like it.";	
			else if ( command.equals( "BLUE" ) )
				phrase = "I like this too";	
			else if ( command.equals( "MAGENTA" ) )	
				phrase = "This is my favorite";				
	
			textField1.setText(command + " radio button was selected. " + phrase);	
		}
	}
	
}	// end class ComponentExample
