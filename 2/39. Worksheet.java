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
	writeFile 
		d&i printWriter 
	

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
File outFile = new File(outFileName);
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
public class Worksheet
{
	private int[] num1, num2, answer;									//fvs num1, num2, and answer 
	public Worksheet()
	{
		
	}
	public static void main(String args[])
	{
		Worksheet w = new Worksheet();
		
	}
