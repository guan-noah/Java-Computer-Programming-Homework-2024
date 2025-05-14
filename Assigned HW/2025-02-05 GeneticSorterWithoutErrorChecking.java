//GeneticSorterWithoutErrorChecking.java
/* Noah Guan 
 * 02/05/25 Period 6 
 * GeneticSorterWithoutErrorChecking.java 
 * basically shorter program that captures the essence of the logic 
 * Reads a poem in (poem.txt), thenwrites the first word of each line to a file 
 * 		called output.txt. 
 * FileIO + Arrays Homework Program 
 * Working on: Reading and writing to files compared to the terminal and arrays. 
 * Also a homework project. 
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
public class GeneticSorterWithoutErrorSorting
{
	private String[] genes;
	public GeneticSorterWithoutErrorSorting()
	{
		genes = new String[] {"rol", "try", "cod", "Pil", "dig", "tal", "mv"};
	}
	public static void main(String[] args)
	{
		GeneticSorterWithoutErrorSorting gs = new GeneticSorterWithoutErrorSorting();
		gs.fakeMain();
	}
	public void fakeMain()
	{
		Scanner keyboard = new Scanner(System.in), fileInput = null;
		String rnaName = "RNA.txt", dnaName = "DNA.txt", proteinName = "protein.txt", 
			originalName = "Original.txt";
		File rnaFile = new File("RNA.txt"), dnaFile = new File("DNA.txt"), 
		proteinFile = new File("protein.txt"), originalFile = new File("Original.txt");
			//d&i all files
		//d&i fileInput from original file  
		try
		{
			fileInput = new Scanner(originalFile);
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Cannot find/open Original.txt file.");
			System.exit(1);
		}
		//d&i printWriters to rna, dna, and protein file
		PrintWriter rnaWriter = null, dnaWriter = null, proteinWriter = null;
		rnaWriter = initializeWriter(rnaFile, "RNA.txt");
		dnaWriter = initializeWriter(dnaFile, "DNA.txt");
		proteinWriter = initializeWriter(proteinFile, "protein.txt");
		
		/* key for deciding which is which: 
		DNA = only A, C, T, Gs 
		RNA = only A, C, U, Gs 
		protein = has M and a mix of A, C, D, E, F, G, H, I, K, L, M, N, O, P, Q, R, S, T, U, V, W, Y 
		 */
		while(fileInput.hasNext())
		{
			//read file and separate into label and sequence 
			String getLine = fileInput.nextLine();
			String label = "", sequence = "";							//first initialize them 
			label = getLine.substring(0, getLine.indexOf(':'));	//this better be an int or it'll throw an error at line 64 
			sequence = getLine.substring((getLine.indexOf(':') + 1), (getLine.length()-1)).trim();
			
			//print to select file using sequence 
			String fileOutput = "";
			fileOutput = genes[Integer.parseInt(label)] + ": " + sequence;			//what to print to file 
			if (contains(sequence, 'M'))
				proteinWriter.println(fileOutput);
			else if (contains(sequence, 'T'))
				dnaWriter.println(fileOutput);
			else if (contains(sequence, 'U'))
				rnaWriter.println(fileOutput);
			else 
				System.out.println("Unrecognized sequence " + sequence + 
					". Does not match protein, DNA, or RNA patterns. ");
		}
		//close printWriters 
		rnaWriter.close();
		dnaWriter.close();
		proteinWriter.close();
		
	}
	public PrintWriter initializeWriter(File fileIn, String fileName)
	{
		PrintWriter fileOutput = null;
		try
		{
			fileOutput = new PrintWriter(fileIn);
		}
		catch(IOException e)
		{
			System.err.println("Cannot write to " + fileName + " file. ");
			System.exit(2);
		}
		return fileOutput;												//don't have to worry about null printwriters because it'll exit the program 
	}
	public boolean contains(String sequenceIn, char checkFor)
	{
		for(int i = 0; i < sequenceIn.length(); i++)
		{
			if (sequenceIn.charAt(i) == checkFor)
				return true;
		}
		//if we went through everything and we didn't get a match 
		return false;
	}
}
