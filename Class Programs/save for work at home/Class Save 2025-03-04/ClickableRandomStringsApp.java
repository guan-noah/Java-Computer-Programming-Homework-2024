import java.awt.Component;
import java.awt.event.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 * Displays a window that shows 25 copies of the string "Java!" in
 * random colors, fonts, and positions.  The content of the window
 * is an object of type RandomStringsPanel.  When the user clicks
 * the window, the content of the window is repainted, with the 
 * strings in newly selected random colors, fonts, and positions.
 */
public class ClickableRandomStringsApp
{
   
   public static void main( String[] args ) 
   {
      JFrame window = new JFrame( "Random Strings" );
      RandomStringsPanel content = new RandomStringsPanel();
      content.addMouseListener( new RepaintOnClick() );  // Register mouse listener.
      window.setContentPane( content );
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      window.setLocation( 100, 75 );
      window.setSize( 300, 240 );
      window.setVisible( true );
   }

   private static class RepaintOnClick implements MouseListener 
   {

      public void mousePressed( MouseEvent evt ) 
      {
         Component source = (Component)evt.getSource();
         source.repaint();
      }

      public void mouseClicked( MouseEvent evt ) { }
      public void mouseReleased( MouseEvent evt ) { }
      public void mouseEntered( MouseEvent evt ) { }
      public void mouseExited( MouseEvent evt ) { }

   }
}
/**
 * An object of type RepaintOnClick is a MouseListener that
 * will respond to a mousePressed event by calling the repaint()
 * method of the source of the event.  That is, a RepaintOnClick
 * object can be added as a mouse listener to any Component;
 * when the user clicks that component, the component will be
 * repainted.
 *
class RepaintOnClick implements MouseListener 
{

   public void mousePressed( MouseEvent evt )
   {
      Component source = (Component)evt.getSource();
      source.repaint();  // Call repaint() on the Component that was clicked.
   }

   public void mouseClicked( MouseEvent evt ) { }
   public void mouseReleased( MouseEvent evt ) { }
   public void mouseEntered( MouseEvent evt ) { }
   public void mouseExited( MouseEvent evt ) { }

}
//this wastes space as a nested class.
*/


/**
//RepaintOnClick listener = new RepaintOnClick();  // Create MouseListener object.
//panel.addMouseListener(listener);  // Register MouseListener with the panel.
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class ClickableRandomStringsApp 
{
   
   public static void main(String[] args) 
   {
      JFrame window = new JFrame("Random Strings");
      RandomStringsPanel content = new RandomStringsPanel();

      content.addMouseListener( new MouseAdapter() { 
            // Register a mouse listener that is defined by an anonymous subclass
            // of MouseAdapter.  This replaces the RepaintOnClick class that was
            // used in the original version.
         public void mousePressed(MouseEvent evt) {
            Component source = (Component)evt.getSource();
            source.repaint();
         }
      } );

      window.setContentPane(content);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setLocation(100,75);
      window.setSize(300,240);
      window.setVisible(true);
   }

}



**/

