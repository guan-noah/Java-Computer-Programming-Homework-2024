//Written by Krish

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Popup
{
	private JFrame popupFrame;
	private PopupContent content;
	private String title;
	
	public Popup(String titleIn)
	{
		title = titleIn;
		
		popupFrame = getFrame();
		content = new PopupContent();
		
		popupFrame.add(content);
	}
	
	public JFrame getFrame()
	{
		JFrame toReturn = new JFrame(title);
		
		toReturn.setSize(600, 500);
		toReturn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		toReturn.setLocationRelativeTo(null);
		
		return toReturn;
	}
	
	public void show()
	{
		popupFrame.setVisible(true);
	}
	
	public void setContent(String contentIn)
	{
		content.setContent(contentIn);
	}
	
	class PopupContent extends JPanel
	{
		private JTextArea contentTextArea;
				
		public PopupContent()
		{
			setLayout(new BorderLayout());
			
			JPanel topPanel = getTop();
			JScrollPane contentPane = getScrollPane();
			
			add(topPanel, BorderLayout.NORTH);
			add(contentPane, BorderLayout.CENTER);
		}
		
		public void setContent(String contentIn)
		{
			contentTextArea.setText(contentIn);
		}
		
		public JScrollPane getScrollPane()
		{
			contentTextArea = new JTextArea();
			JScrollPane toReturn = new JScrollPane(contentTextArea);
			
			contentTextArea.setLineWrap(true);
			contentTextArea.setWrapStyleWord(true);
			contentTextArea.setEditable(false);
			
			return toReturn;
		}
		
		public JPanel getTop()
		{
			JPanel toReturn = new JPanel(new GridLayout(2, 1));
			JPanel goBackHolder = getGoBackBtn();
			JPanel titleHolder = getTitle();
			
			toReturn.add(goBackHolder);
			toReturn.add(titleHolder);
			
			return toReturn;
		}
		
		public JPanel getGoBackBtn()
		{
			JPanel toReturn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
			GoBackButtonHandler goBackBtnHandler = new GoBackButtonHandler();
			Button goBack = new Button("X", goBackBtnHandler);
			
			toReturn.add(goBack);
			
			return toReturn;
		}
		
		public JPanel getTitle()
		{
			JPanel toReturn = new JPanel();
			Label titleLabel = new Label(title);
			
			toReturn.add(titleLabel);
			
			return toReturn;
		}
	}
	
	class GoBackButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			popupFrame.setVisible(false);
		}
	}
}
