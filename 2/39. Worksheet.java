//Worksheet.java
/* Noah Guan
 * 01-27-2025
 * Per. 6 Java w/ Mr. Yu
 * Worksheet.java
 * Program #39
 * Pseudocode: 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter; 
import java.io.IOException;
fvs: (int)
	int num1[]
	int num2[]
	int answer[]

reqs: 
	

class Worksheet
	main 
		d&i new instance of worksheet 
		worksheet.run()
	void run()
		get fileName 
		
		print confirmation (done)
	int[] getInput()
		print prompt 
		get first number
		get second number 
		if first number > second number, 
			return (new int[] {second number, first number};			//return lesser number, then greater number 
		else 
			return (new int[] {first number, second number};			//return lesser number, then greater number 
	int getRandomNums(int[] firstAndSecondNum) 
 		(lesser num = firstAndSecondNum[0]; greater num = firstAndSecondNum[1];)
		for (int index = 0; index < num1.length; index++)				//could also use for(int index: num1)
			num1[index] = lesser + (int)(Math.random())*range			//the lesser number times the range (greater - lesser) 
			num2[index] = lesser + (int)(Math.random())*range			//d&i both numbers with same formula (may be a better way to do this)
 	void getAnswer()
  		for (int index = 0; index < num1.length; index++) 				//could also use for(int index: num1)
 			if ((int)(Math.random()*2) >= 1)							//50/50 chance 
   				answer[index] = num1[index] + num2[index];
	   		else 
	  			answer[index] = num1[index] - num2[index];
	void writeFile()
 		File outFile = new File(getInput("String", "Please enter the file name:"));
		try
		{
			output = new PrintWriter(outFile)
		}
		catch(IOException e)
		{
			System.err.println("\n\n\nError: Can't create " + outFileName + 
				"file.\n\n\n");
			System.exit(2);
		}
		
	

 */
/* reading from a file (NOT NEEDED) 
inFileName = "";
File inFile = new File(inFileName);
try
{
	input = new Scanner(inFile);
}
catch(FileNotFoundException e)
{
	System.err.println("\n\n\nError: Cannot find/open " + outFileName + 
		"file.\n\n\n");
	System.exit(1);
}
*/

/* writing to file from program 
File outFile = new File(getInput("String", "Please enter the file name:"));
try
{
output = new PrintWriter(outFile)
}
catch(IOException e)
{
	System.err.println("\n\n\nError: Can't create " + outFileName + 
		"file.\n\n\n");
	System.exit(2);
}
*/

/* printing to powershell from a file 
Scanner input;
while (input.hasNext())
{
	word = input.next();
	input.nextLine();
	System.out.println(word);
}
*/
//import java utilities 
import java.io.File;
import java.io.FileNotFoundException;									//don't really need this because we're not reading to the file; 
																		//you just need to know you can write to it 
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
public class Main
{
    public static void main(String args[])
    {
        Worksheet w = new Worksheet();
        w.writeFile();
    }
}

class Worksheet
{
	private int[] num1, num2, answer;									//fvs num1, num2, and answer 
	public Worksheet()
	{
		
	}
	public static void main(String args[])
	{
		Worksheet w = new Worksheet();
		w.writeFile();
	}
	public int[] getBounds()
	{
	    System.out.println("These next numbers will determine the addends or minuend/subtrahend. ");
		int first = Integer.parseInt(getInput("int", "Please enter the first bound value: "));
		int second = Integer.parseInt(getInput("int", "Please enter the second bound value: "));
		if (first > second)
			return (new int[] {second, first});			//return lesser number, then greater number 
		else 
			return (new int[] {first, second});			//return lesser number, then greater number 
	}
	public void getRandomNums(int[] firstAndSecondNum) 
	{
		int lesser = firstAndSecondNum[0];
		int greater = firstAndSecondNum[1];
		int range = greater - lesser + 1;
		for (int index = 0; index < num1.length; index++)				//could also use for(int index: num1)
		{
			num1[index] = lesser + (int)(Math.random())*range;			//the lesser number times the range (greater - lesser) 
			num2[index] = lesser + (int)(Math.random())*range;			//d&i both numbers with same formula (may be a better way to do this)
		}
	}
	public void getAnswer()									//no parameters or return
	{
  		for (int index = 0; index < num1.length; index++) 				//could also use for(int index: num1)
		{
			if ((int)(Math.random()*2) >= 1)							//50/50 chance 
   				answer[index] = num1[index] + num2[index];
	   		else 
	  			answer[index] = num1[index] - num2[index];
		}
	}
	public String getInput(String get, String prompt)
	{
		System.out.print(prompt + "\n\t--> ");
		Scanner keyboard = new Scanner(System.in);
		String fileName = "";
		if(get.equals("int"))
			fileName = "" + keyboard.nextInt();
		else if(get.equals("next"))
			fileName = keyboard.next();
		else if(get.equals("line"))
			fileName = keyboard.nextLine();
		else
			System.out.println("getInput method: unrecognized get parameter");
		return fileName;
	}
	public void writeFile()
	{
 		System.out.println("\n\n\n");
 		String outFileName = getInput("next", "Please enter the file name:");
		File outFile = new File(outFileName);
		try
		{
			PrintWriter output; 
			output = new PrintWriter(outFile);
		}
		catch(IOException e)
		{
			System.err.println("\n\n\nError: Can't create " + outFileName + 
				" file.\n\n\n");
			System.exit(2);
		}
		System.out.println("Confirmed worksheet export. Thank you for using Worksheet.java. ");
		/*
		output.println();
		output.printf("");
		output.print("");
		if(output.checkError())
		{
			System.out.println("There was an error in outputting text file. ");
			output.close();
			System.exit(3);
		}
		output.close();
		*/
	}
}
