//AreWeThereYet3.java
/* Noah Guan
 * 11-06-2024
 * Per. 6 Java w/ Mr. Yu
 * AreWeThereYet3.java
 * Program #27c 
 * It uses only local variables 
 * 		We'll modify local variables so that it prints 2 sets of 
 * 			question/response by a tab on each line. 
 * 
 * Working on: For loop
 * 
 * Testing: No user input = no testing plan! Yay! 
 * This program is a typealong in class. 
 * 
*/

public class AreWeThereYet3//class header
{
	public static void main(String[] args)//method header
	{
		AreWeThereYet3 awty = new AreWeThereYet3();
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
		for(miles = 50; miles >= 0; miles -= 5)
			//reinitialize miles, declare sentinel value, decrement 
				//miles by 5
		{
			counter++;
				//increment counter by one
			printLine(miles, counter);
				//call printLine method
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
			
			if ((counterIn - 0) % 2 == 0)
				//this code shifts the counter by zero, then if counter 
					//is even, it will print a new line. 
				System.out.println("");
			else 
				//otherwise, print out a tab for the next number 
				System.out.print("\t");
		}
	}
}
