//AreWeThereYet.java
/* Noah Guan
 * 11-06-2024
 * Per. 6 Java w/ Mr. Yu
 * AreWeThereYet.java
 * Program #27a
 * It uses only local variables 
 * 		We'll modify local variables so that it prints 2 sets of 
 * 			question/response by a tab on each line. 
 * 
 * Working on: While loop
 * 
 * Testing: No user input = no testing plan! Yay! 
 * This program is a typealong in class. 
*/

public class AreWeThereYet//class header
{
	public static void main(String[] args)//method header
	{
		AreWeThereYet awty = new AreWeThereYet();
			//make new instance of AWTY 
		awty.journey();
			//call journey() method 
	}
	public void journey()
	{
		System.out.println("\n\n\n"); 
			//3 blank lines, ALWAYS 
		int miles = 50;
			//D&I miles to 50 
		int counter = 0;
			//D&I counter for formatting purposes
		while(miles >= 0)
		{
			counter++;
				//increment counter by one
			printLine(miles, counter);
				//call printLine method
			miles -= 5;
				//subtract 5 from miles 
		}
		System.out.println("\n\n\n");
			//3 blank lines, ALWAYS
	}
	public void printLine(int milesIn, int counterIn)
	{
		if(milesIn == 0)
		{
			System.out.println("Yes, we finally made it there! ");
				//Print final answer
		}
		else 
		{
			System.out.print("Are we there yet?");
				//Print out the question
			System.out.print("\tNo, " + milesIn + " miles to go.");
				//Print out the response 
			
			if (counterIn % 2 == 0)
				//if counter is even, it will print a new line. 
				System.out.println("");
			else 
				//otherwise, print out a tab for the next number
				System.out.print("\t");
		}
	}
}
