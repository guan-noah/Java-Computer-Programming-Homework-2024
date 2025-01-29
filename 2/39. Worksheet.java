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
	private int[] num1, num2;									//fvs num1, num2, and answer 
	private String[] answer;
	public Worksheet()
	{
		num1 = num2 = new int[20];                             //initialize; could potentially ask user for number of questions
	    answer = new String[20];
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
			return (new int[] {second, first});			                //return bounds: lesser number, then greater number 
		else 
			return (new int[] {first, second});			                //return bounds: lesser number, then greater number 
	}
	public void getRandomNums(int[] boundsIn) 
	{
		int lesser = boundsIn[0];
		int greater = boundsIn[1];
		int range = greater - lesser + 1;
		for (int index = 0; index < num1.length; index++)				//could also use for(int index: num1)
		{
			num1[index] = lesser + (int)(Math.random()*range);			//the lesser number times the range (greater - lesser) 
			num2[index] = lesser + (int)(Math.random()*range);			//d&i both numbers with same formula (may be a better way to do this)
		    System.out.println(num1[index] + " " + num2[index] + "\n");
		}
	}
	public void getAnswer(String input)						            //no parameters or return
	{
  		for (int index = 0; index < num1.length; index++) 				//could also use for(int index: num1)
		{
			
			if (input.equals("random"))
			{
    			if ((int)(Math.random()*2) >= 1)//50/50 chance 
       			{
       				answer[index] = num1[index] + " + " + num2[index] + " = " + (num1[index] + num2[index]);
       			}
    	   		else 
    	  		{
    	  			answer[index] = num1[index] + " - " + num2[index] + " = " + (num1[index] - num2[index]);
    	  		}
			}
			else if (input.equals("addition"))
			{
			    answer[index] = num1[index] + " + " + num2[index] + " = " + (num1[index] + num2[index]);
			}
			else if (input.equals("subtraction"))
			{
			    answer[index] = num1[index] + " - " + num2[index] + " = " + (num1[index] - num2[index]);
			}
		}
	}
	public String getInput(String get, String prompt)
	{
		System.out.print(prompt + "\n\t--> ");                          //print prompt
		Scanner keyboard = new Scanner(System.in);                      //new Scanner called keyboard
		String fileName = "";                                           //what we're trying to get to return 
		if(get.equals("int"))
			fileName = "" + keyboard.nextInt();                         //get an int (will have to parse it later)
		else if(get.equals("next"))
			fileName = keyboard.next();                                 //get next word 
		else if(get.equals("line"))
			fileName = keyboard.nextLine();                             //get next line
		else
			System.out.println("getInput method: unrecognized get " + 
			    "parameter");                                           //error message
		keyboard.nextLine();                                            //clear Scanner
		return fileName;
	}
	public void writeFile()
	{
 		System.out.println("\n\n\n");
 		String outFileName = getInput("next", "Please enter the file name:");
		File outFile = new File(outFileName);
		PrintWriter output = null;
		try
		{
			output = new PrintWriter(outFile);
		}
		catch(IOException e)
		{
			System.err.println("\n\n\nError: Can't create " + outFileName + 
				" file.\n\n\n");
			System.exit(2);
		}
		
		//get worksheet info 
		int numOfQuestions = Integer.parseInt(getInput("int", "How many problems " + 
		    "would you like?"));
        num1 = new int[numOfQuestions];
        num2 = new int[numOfQuestions];
        answer = new String[numOfQuestions];
		int[] bounds = getBounds(); 
		getRandomNums(bounds);
		getAnswer(getInput("next", "Would you like to create a purely addition " + 
		    "worksheet, a purely subtraction worksheet, or a random assortment " + 
	        "of the two? [addition, subtraction, random]"));
		
		//write Worksheet file 
        //method: write(output, "")
		output.printf("%-49sName___________________________\n", "");            //header 1
		output.printf("%-56sDate____________________\n", "Addition and " + 
            "subtraction practice using numbers " + bounds[0] + " to " + 
            bounds[1] + "\n\n");                                                //header 2 (print bounds)
		String question;
		for(int i = 1; i <= numOfQuestions; i++)
		{
		    question = answer[i-1];
		    output.printf("%-20s",(i + ". " + question.substring(0, (question.indexOf('=')+1)) ));
		    if(i%4==0)
		        output.println("");
		}
		output.println("\n\n\n\n\nAnswer Key:");
		String finalAnswer;
		for(int i = 1; i <= numOfQuestions; i++)
		{
		    finalAnswer = answer[i-1];
		    output.printf("%-20s", i + ". " + finalAnswer.substring(finalAnswer.indexOf('=')+1));
		    if(i%4==1)
		        output.println("");
		}
		
		if(output.checkError())
		{
			System.out.println("There was an error in outputting text file. ");
			output.close();
			System.exit(3);
		}
		
		
		
		//ending algorithm 
		System.out.println("Confirmed worksheet export. Thank you for using " + 
		    "Worksheet.java. ");
		output.close();
	}
}
/*
allow 80 char spaces in between 
*/
