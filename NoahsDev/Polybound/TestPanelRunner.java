/*
 * Noah Guan
 * Period 6
 * TestPanelRunner.java
 * 
 * Tests a panel of your choice (edit the panel reference and size to change) 
 */
// imports
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
import javax.swing.JScrollBar;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//~ import java.awt.event.KeyEvent;
//~ import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.io.File;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

import java.awt.Dimension;

import java.util.ArrayList;

public class TestPanelRunner
{	
	public TestPanelRunner()
	{
	}
	
	public static void main(String [] args)
	{
		TestPanelRunner tpr = new TestPanelRunner();
		tpr.run();
	}
	
	public void run()
	{
		JFrame frame = new JFrame("TestPanelRunner");
		frame.setSize( 1200, 750 );				
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE); 
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		SelectUserInfoPanel testPanel = new SelectUserInfoPanel(); 		
		frame.getContentPane().add( testPanel );		
		frame.setVisible(true);		
	}
}
