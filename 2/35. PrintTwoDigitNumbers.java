//PrintTwoDigitNumbers.java
/* Noah Guan
 * 01-06-2025
 * Per. 6 Java w/ Mr. Yu
 * PrintTwoDigitNumbers.java
 * Program #35
 * NOTE: This is a typealong. Use this for formatting reference. 
 * 
 * Program uses nested loops to print a table of two digit numbers from the 
 * 		user's start number to the user's end number. 
 * Testing Plan: 

 */
import java.util.Scanner;
public class PrintTwoDigitNumbers
{
	private int start;
		//the starting value of the chart 
	private int end;
		//the ending value of the chart 
	
	public PrintTwoDigitNumbers()
	{
		start = end = 0;
	}
	
	public static void main(String[] args)
	{
		PrintTwoDigitNumbers ptdn = new PrintTwoDigitNumbers();
		ptdn.printIt();
	}
	/* printIT controls the program and prints the welcome message. */
	public void printIt() //the "fake" main
	{
		System.out.println("\n\n\n");
		System.out.println("\t\tWelcome to PrintTwoDigitNumber\nThis program " + 
			"asks the user for the start and end number, then prints the numbers " + 
			"in a table\n");
		getValues();
		printNumbers();
		System.out.println("\n\n\n");
	}
	/* Prompts and reads the user's numbers to start and end the table. */
	public void getValues()
	{
		Scanner keyboard = new Scanner(System.in);
		
		do
		{
			System.out.print("Enter the start value for the two digit numbers chart " + 
				"(10-99) -> ");
			start = keyboard.nextInt();
			
		} while(start < 10 || start > 99);
			//genius error-checking! basically !(start > 10 || start < 99)
		
		do 
		{
			System.out.printf("Enter the end value for the numbers chart " + 
				"(%d - 99) -> ", start);
			end = keyboard.nextInt();
		} while(end < start || end > 99);
			//more genius error-checking
	}
	/* Uses nested loops to print the chart. The outer loop prints the tens digit
	 * and the inner loop prints the units digit. */
	public void printNumbers()
	{
		for(int outer = start/10; outer < end/10 + 1; outer++)
			//for printing multiple lines (tens' place)
		{
			System.out.println();
			for (int inner = 0; inner < 10; inner++)
				//for printing each individual line (ones' place) 
			{
				if(outer == start/10 && inner < start%10)
					System.out.print("\t");
					//only print a tab if ones' digit place is less than 
						//ones' place of start number 
				else if(outer < end/10)
					System.out.printf("%d%d\t", outer, inner);
					//print the first digit and second digit (whole number)  
						//if tens' digit is less than tens' digit of end number 
				else if(outer == end/10 && inner <= end%10)
					System.out.print(outer + "" + inner + "\t");
					//print the first digit and second digit (whole number) 
						//if tens' digit is the same as the tens' digit of 
						//end number and the ones' digit is less than or 
						//equal to the ones' digit of inner number 
					//essentially, print the number until the end number 
			}
			System.out.println();
		}
	}
}
