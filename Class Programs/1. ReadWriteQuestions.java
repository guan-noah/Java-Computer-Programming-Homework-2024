/* Noah Guan 
 * 1/24/25 Period 6 
 * ReadWritePoem.java
 * Reads a poem in (poem.txt), thenwrites the first word of each line to a file 
 * 		called output.txt. 
 * First FileIO Program 
 * Working on: REading and writing to files compared to the terminal. 
 */
import java.util.Scanner; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;


public class ReadWritePoem
{
	private Scanner input; 
		//Scanner obj to read from the file 
	private PrintWriter output;
		//PrintWriter obj to write to file 
	private String inFileName;
		//text file to read from 
	private String outFileName;
		//text file to write on 
	
	public ReadWritePoem()
	{
		input = null;
		output = null; 
		inFileName = new String("poem.txt");
		outFileName = new String("output.txt");
	}
	
	public static void main(String[] args)
	{
		ReadWritePoem rwp = new ReadWritePoem();
		rwp.readIt();
	}
	
	/*This method calls all of the other ones. There are no local variables.*/
	
	public void readIt()
	{
		openIt();
		makeIt();
		getWords();
	}
	
	/* openIt uses a try-catch block to open a file. */
	public void openIt()
	{
		File inFile = new File(inFileName);
		try
		{
			input = new Scanner(inFile);
		}
		catch(FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: cannot find/open file %s. \n\n\n", 
				inFileName);
			System.exit(1);
		}
	}
	
	/* makeIt uses a try-catch block to create a file to write to. */
	
	public void makeIt()
	{
		//create a file to read to 
		File outFile = new File(outFileName);
		try 
		{
			output = new PrintWriter(outFile);
		}
		catch(IOException e)
		{
			System.err.println("\n\n\nERROR: Cannot create " + outFileName + 
				"file.\n\n\n");
				System.exit(2);
		}	
	}
	
	/* getWords reads the input file, saves the 1st word of each line, then 
	 * prints that word to the output file. No parameters or return value. */
	public void getWords()
	{
		System.out.println("\n\n\n");
		String word = "";
		while(input.hasNext())
		{
			word = input.next();
			input.nextLine();
			output.println(word);
		}
		output.close();
	}
}
