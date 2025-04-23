import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class GameData
{
	/**
	 * This code was written by Krish.
	 * 
	 **/
	public static Image loadImage(String fileName)
	{
		File imgFile = new File(fileName);
		Image toReturn = null;
		
		try
		{
			toReturn = ImageIO.read(imgFile);
		}
		catch(IOException e)
		{
			System.err.printf("Error: Could not read from file \"%s\".", fileName);
		}
		
		return toReturn;
	}
}
