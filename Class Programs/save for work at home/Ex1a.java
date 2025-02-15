import java.awt.*;				import javax.swing.*;
public class Ex1a extends JFrame
{
	public static void main(String[] args)
	{
		Ex1a ex = new Ex1a();												//needed to run constructor 
		ex.runIt();
	}
	public Ex()
	{
		
	}
	public void runIt()
	{
		JFrame frame = new JFrame("Pink Panel in Frame");
		frame.setSize(200, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocation(400, 400);
		frame.setResizable(true);												//true anyway 
		Panel pan = new Panel();
		frame.setContentPane(pan);
		frame.setVisible(true);
	}
}

class Panel extends JPanel
{
	public Panel()
	{
		setBackground(Color.RED);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawString("hello", 50, 100);
		g.drawLine(50, 100, 100, 200);
	}
}
