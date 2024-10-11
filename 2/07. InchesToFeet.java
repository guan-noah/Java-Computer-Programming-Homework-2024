//Noah Guan
//InchesToFeet.java
//9th Grade Java/Computer Programming w/ Mr. Yu - Monta Vista High School 
//Homework for Wednesday, Sept. 18, 2024 (instructions)
//Program #7

/*
Pseudocode: 
1. Import java.util.Scanner
2. Define class InchesToFeet
3. Define class main (public static void main(Stringp[] args))
4. Print 3 lines
5. Initiate Scanner 
6. Prompt user to input # of inches 
7. Get Scanner to D&I inches 
8. D&I feet to inches/12 (because the value of feet is the number of inches/12)
9. Print out result 
10. Print 3 lines 

Test cases: 
1. 0 --> 0.00
2. 23885 --> 1990.42 
3. 201010 --> 16750.83 
*/
import java.util.Scanner;
public class InchesToFeet
{
	
    public static void main(String[] args) 
    {
        final int CONVERSIONFACTOR = 12; //make the conversion factor a final number
        System.out.println("\n\n\n"); //3 lines
        Scanner input = new Scanner(System.in); //create instance of Scanner
        System.out.print("Please enter the number of inches you want to " 
			+ "convert to feet as a whole number less than 2 billion: \t--> "); 
			//prompt user input
        int inches = input.nextInt(); //D&I inches to the scanner input 
        double feet = 0.0; //D&I feet
        feet = (double)inches/CONVERSIONFACTOR; //reinitialize feet to 12 inches 
        System.out.printf("\n" + inches + " inches is equal to about %-5.2f feet", feet); //print out result 
        
        System.out.println("\n\n\n"); //3 lines
    }
}
