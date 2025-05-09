/*
 * Noah Guan
 * Period 6
 * SelectUserInfoPanel.java
 * 
 * This class 


 */

import javax.swing.JPanel;
import javax.swing.JLabel;									//components
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;							//for labels/instructions
import java.awt.Dimension;								//preferred size
import java.awt.BorderLayout;							//layout imports
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;				//for the buttons 
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentListener;			//for the JScrollBar
import java.awt.event.AdjustmentEvent;
import javax.swing.event.ChangeListener;			//for the JSliders
import javax.swing.event.ChangeEvent;


/* class that asks for user information, including but not limited to: 
name 
favorite color (steal rgb sliders from PutItTogether
email
school id 
address 
social security number 
*/
public class SelectUserInfoPanel extends JPanel
{	
	public SelectUserInfoPanel()
	{
		//panel size is 1200 by 750 
		setLayout(new BorderLayout());
		resetValues();
	}
	/* for resetting and for extended constructor the first time */
	public void resetValues()
	{
		JPanel selection = getSelection();
		add(selection, BorderLayout.CENTER);
		
		JPanel bottomButtonPan = getBottomButtons();
		add(bottomButtonPan, BorderLayout.SOUTH);
	}
	
	/* gets selection */
	public JPanel getSelection()
	{
		JPanel selection = new JPanel();
		selection.setPreferredSize(new Dimension(1200, 650));
		selection.setBackground(Color.GRAY /*GameData.getUserColor()*/);
		selection.setBorder(BorderFactory.createEmptyBorder());
		//~ selection.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), 
			//~ "Please enter your information")); //just a test 
		
		//add label
		Font titleFont = new Font("Serif", Font.BOLD, 180);
		Label label = new Label("Please enter your information: ", titleFont, Color.WHITE);
		//~ add grid
		//~ add jradiobutton selection 
		
		return selection;
	}
	
	/* gets bottom button panel */
	public JPanel getBottomButtons()
	{
		//the panel 
		JPanel bottomButtonPan = new JPanel();
		bottomButtonPan.setPreferredSize(new Dimension(1200, 100));
		bottomButtonPan.setBackground(Color.BLACK);
		
		//initialization of names -- very easily changeable
		String[] names = new String[] {"Back", "Continue"};
		Button[] bottomButtons = new Button[names.length];
		bottomButtonPan.setLayout(new FlowLayout(names.length));
		//initialize 
		for(int i = 0; i < names.length; i++)
		{
			bottomButtons[i] = new Button(names[i], new ButtonListener(), 90);//font size 90
			bottomButtons[i].setPreferredSize(new Dimension(200, 10));
			bottomButtonPan.add(bottomButtons[i]);
		}
		return bottomButtonPan;
	}
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			CardLayout cards = GameData.getCardLayout();
			JPanel holder = GameData.getCardHolder();
			if(command.equals("Back"))
			{
				resetValues();
				cards.show(holder, "main menu");
			}
			else if(command.equals("Continue"))
			{
				cards.show(holder, "");
			}
			else if(command.equals(""))
			{
				cards.show(holder, "");
			}
		}
	}
	
	
	
	
	
	/* an example from Noah's PutItTogether.java */
	class DrawPanel extends JPanel
	{
		private RightPanel rp;
		private int amtRed, amtGreen, amtBlue;
		private int size;
		private JSlider[] sliders;
		private JScrollBar changeSize;
		
		public DrawPanel()
		{
			rp = new RightPanel();
			LeftPanel lp = new LeftPanel();
			lp.setPreferredSize(new Dimension(450, 500));					//x, y
			JPanel bottomPanel = new JPanel();
			bottomPanel.setPreferredSize(new Dimension(800, 100));
			
			setLayout(new BorderLayout());
			add(lp, BorderLayout.WEST);
			add(rp, BorderLayout.EAST);
			add(bottomPanel, BorderLayout.SOUTH);									//block at bottom (doesn't work :sob:)
		}
		
		public class LeftPanel extends JPanel
		{
			public LeftPanel()
			{
				setLayout(new GridLayout(4, 2, 0, 50));
				Font labelFont = new Font("Arial", Font.BOLD, 15);
				
				String[] colors = new String[] {"red", "green", "blue", ""};//variables initialized in for loop or helping with initialization
				JLabel[] changeLabels = new JLabel[4];
				sliders = new JSlider[4];									//first 3 for color, last slider for size 
				
				for(int i = 0; i < colors.length; i++)						//for loop that iterates through all 3 color panels 
				{
					changeLabels[i] = new JLabel("Amount " + colors[i]);	//amount <color> label 
					changeLabels[i].setFont(labelFont);
					
					sliders[i] = makeSlider();								//make the slider (general)
					if(i == 0)
						sliders[i].addChangeListener(new RedSliderListener());//slider0: also red; adds a different color/size listener for each slider
					else if(i == 1)
						sliders[i].addChangeListener(new GreenSliderListener());
					else if(i == 2)
						sliders[i].addChangeListener(new BlueSliderListener());
					//~ else if(i == 3)
						//~ sliders[i].addChangeListener(new SizeSliderListener());
					
					add(changeLabels[i]);
					if(i != colors.length-1)
						add(sliders[i]);									//save the last one for jscrollbar 
				}
				changeSize = new JScrollBar(JScrollBar.HORIZONTAL, 100, 25, 50, 250);
				changeSize.addAdjustmentListener(new SizeSliderListener());
				add(changeSize);
				/*
				changeLabels[3].setText("Change size");
				sliders[3].setMinimum(50);
				sliders[3].setValue(100);
				*/
				
				
			}
			public JSlider makeSlider()
			{
				JSlider slider = new JSlider(0, 250, 0);					//from 0 to 250 default location 0
				slider.setMajorTickSpacing(25);								// create tick marks on slider every 5 units
				slider.setPaintTicks(true);
				slider.setLabelTable(slider.createStandardLabels(25) ); 	// create labels on tick marks
				slider.setPaintLabels(true);
				slider.setOrientation(JSlider.HORIZONTAL);
				return slider;
			}
			class RedSliderListener implements ChangeListener
			{
				public void stateChanged(ChangeEvent evt)
				{
					int val = sliders[0].getValue();						//red slider
					amtRed = val;											//change fv color value to slider value 
					/* //
					String sliderName = slider.getString();
					if(sliderName.equalsIgnoreCase("Red Slider"))
						amtRed = val;
					else if(sliderName.equalsIgnoreCase("Blue Slider"))
						amtBlue = val;
					else if(sliderName.equalsIgnoreCase("Green Slider"))
						amtGreen = val;
					*/
					rp.repaint();											//NOTE!! It won't repaint itself. You will have to repaint it. 
				}
			}
			class GreenSliderListener implements ChangeListener
			{
				public void stateChanged(ChangeEvent evt)
				{
					int val = sliders[1].getValue();
					amtGreen = val;
					rp.repaint();
				}
			}
			class BlueSliderListener implements ChangeListener
			{
				public void stateChanged(ChangeEvent evt)
				{
					int val = sliders[2].getValue();
					amtBlue = val;
					rp.repaint();
				}
			}
			/*
			class SizeSliderListener implements ChangeListener
			{
				public void stateChanged(ChangeEvent evt)
				{
					int val = sliders[3].getValue();
					size = val;
					rp.repaint();
				}
			}
			*/
			class SizeSliderListener implements AdjustmentListener
			{
				public void adjustmentValueChanged(AdjustmentEvent evt)
				{
					int val = changeSize.getValue();
					size = val;
					rp.repaint();
				}
			}
		}
		
		public class RightPanel extends JPanel
		{
			public RightPanel()
			{
				setLayout(new BorderLayout());
				setBackground(Color.WHITE);
				
				JLabel descPan = new JLabel();
				descPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));//only one in line so hgap doesn't matter
				descPan.setPreferredSize(new Dimension(300, 200));
				
				JLabel description = new JLabel("This is the drawing Panel");
				description.setFont(new Font("Arial", Font.BOLD, 15));
				
				descPan.add(description);
				add(descPan, BorderLayout.NORTH);
				
				size = 100;
			}
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(new Color(amtRed, amtGreen, amtBlue));
				g.fillRect(0, 200, size, size);
			}
		}
	}
}
