//AreWeThereYet2.java
/* Noah Guan
 * 11-06-2024
 * Per. 6 Java w/ Mr. Yu
 * AreWeThereYet2.java
 * Program #27 
 * It uses only local variables 
 * 		We'll modify local variables so that it prints 2 sets of 
 * 			question/response by a tab on each line. 
 * 
 * Working on: Do-While loop
 * 
 * Testing: No user input = no testing plan! Yay! 
 * This program is a typealong in class. 
*/

public class AreWeThereYet2//class header
{
	public static void main(String[] args)//method header
	{
		AreWeThereYet2 awty = new AreWeThereYet2();
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
		do//do loop runs code first 
		{
			counter++;
				//increment counter by one
			printLine(miles, counter);
				//call printLine method
			miles -= 5;
				//subtract 5 from miles 
		} while(miles >= 0);//condition of sentinel value
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
				System.out.println("");
			else 
				System.out.print("\t");
		}
	}
}
