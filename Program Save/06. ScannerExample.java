//Noah Guan
//9-16-24 (Monday) 
//Per 6 Java
/*
 * ScannerExample.java
 * Program #6
 * This program gives an example of using the Scanner class. 
 * Working on:	1. Inputting from the keyboard 
 * 				2. Using a Math method 
*/

import java.util.Scanner;

public class ScannerExample
{	
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in); 	//Opens the Scanner
													//System.in is the keyboard; file.io are files 
		
		System.out.println("\n\n\n");
		System.out.print("Input an integer please\t--> "); //prompt user for an integer input
		int input = 0; //D&I input, initialize to 0
		input = keyboard.nextInt(); //read integer input
		System.out.println("Your integer is " + input); //print out the input
		
		//The Scanner code format is basically the same for nextDouble, nextFloat, nextLong, etc. 
		keyboard.nextLine();//a void method; flush the buffer to use nextLine; 
		//when you type 3 and enter, it stores 3 and stops; what you type in next is an enter; 
		//the 3 gets read but the enter stays in the system. This is our buffer. 
		
		System.out.print("\nNow, input a sentence please\t--> "); //prompt user for a sentence input
		String str = new String(""); //declaring String value (creating instance of the class)
		
		
		/*
		System.out.println("Your sentence is: " + str); //print out the input sentence
		*/
		
		str = keyboard.next(); //reads up to the first word
		System.out.println("Your first word of the sentence is: \"" + str + "\""); //prints out the first word
		str = keyboard.nextLine(); //reads up to the end of the line
		System.out.println("The rest of your sentence is: \"" + str + "\""); //prints out the rest of the line
		
		//Using Math methods 
		
		double power = 0.0;
		power = Math.pow(input, 3);
		System.out.println("\nYour input number cubed = " + power);
		
		power = Math.round(power);
		System.out.println("power round to nearest whole number = " + power);
			/*
			it is a double, so it gives the first decimal place
			Math.round(float) returns an int
			Math.round(double) returns a long, so you cannot save it to an int! 
			To get around this, cast the double as a float, then round or use a long vs. int
			*/
		System.out.println("\n\n\n");
		
		//Other Math methods: sqrt (see APIs) 
	}
}
