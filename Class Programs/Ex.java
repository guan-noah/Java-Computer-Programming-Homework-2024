/*
This was a typealong on 2/12/2025. 
*/
import java.awt.*;				import javax.swing.*;
public class Ex extends JFrame
{
	public static void main(String[] args)
	{
		Ex ex = new Ex();												//needed to run constructor 
		
		Ex1a ex1 = new Ex1a();
		ex1.runIt();
	}
	public Ex()
	{
		super("Pink Panel in Frame");
		setSize(200,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(400, 400);
		setResizable(true);												//true anyway 
		Panel pan = new Panel();
		setContentPane(pan);
		setVisible(true);
	}
}
/*
You can put two classes in same Genie file. Classes ARE NOT nested. 
second header does NOT have "public", else program throws an error. 
nice choice for classes that work together and aren't related (not super/subclasses). 
 */
class Panel extends JPanel												
{
	public Panel()
	{
		setBackground(Color.PINK);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawString();
	}
}

class Ex1a
{
	public Ex1a()
	{
		
	}
	public static void main(String[] args)
	{
		Ex1a ex1 = new Ex1a();
		ex1.runIt();
	}
	public void runIt()
	{
		JFrame frame = new JFrame("Pink Panel in Frame");
		frame.setSize(200, 300);
		frame.setLocation(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel pan = new Panel();
		frame.setContentPane(pan);
		frame.setVisible(true);
	}
}
