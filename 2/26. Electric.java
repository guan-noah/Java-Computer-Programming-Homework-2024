//Electric.java
/* Noah Guan
 * 10-30-2024
 * Per. 6 Java w/ Mr. Yu
 * Electric.java
 * Program #26
 * This program creates different rates for different users
 * Pseudocode: 
 * class header 
 * 		d&i all field variables 
 * 		d&i ALL variables, so all methods can access 
 * 	constructor 
 * 		initialize all field variables 
 * 	main
 * 		call computeIt
 * 	method computeIt
 * 		call getData
 * 		call calculateCost
 * 		call printInfo
 * 	method getData
 * 		prompt user: "Welcome to Electric.java! This program will take in 
 * 			your billing method and the number of kilowatt hours used; 
 * 			based on this it will calculate your electrical bill.  
 * 			Please input your billing method ('R' for a residential bill, 
 * 			'C for a commercial bill, and 'I' for an industrial bill.): 
 * 		userBillingMethod = Scanner.next();
 * 		if userBillingMethod = R or C, 
 * 			ask for peak hour kwatt hours used 
 * 		if userBillingMethod = I, 
 * 			ask for peak hour kwatt hours used 
 * 			ask for non-peak hour kwatt hours used 
 * 	method calculateCost
 * 		residentialHoursRate = 12 + 0.095(kwattHours)
 * 		commercialHoursRate = 120 + 0.083(kwattHours)
 * 		industrialPeakHoursRate = 152 + 0.109(kwattHours)
 * 		industrialNonPeakHoursRate = 108 + 0.047(kwattHours)
 * 			**of course, all of these numbers will be finals (not magic #s)
 * 		if userBillingMethod = residential 
 * 			userRate = residentialHoursRate
 * 		else if userBillingMethod = commercial 
 * 			userRate = commercialHoursRate
 * 		else if userBillingMethod = industrial 
 * 			userRate = industrialPeakHoursRate + industrialNonPeakHoursRate
 * 		else 
 * 			print "please enter another valid rate description"
 *  method printInfo
 * 		if has errors 
 * 			if error = invalid rate description
 * 				ask user to enter a valid rate description
 * 			if error = negative number
 * 				ask user to print valid number 
 * 		if no errors
 *	 		print Residential Bill
 * 		
 * Testing Plan: 
 * 1.	Residential, 100 kwatt hours --> $21.50
 * 2.	Commmercial, 2000 kwatt hours --> $203.00
 * 3.	Industrial, 2000 on-peak kwatt hours, 2000 non-peak kwatt hours
 * 			--> $
 * 4.	Industrial, 1000 on-peak kwatt hours, 1000 non-peak kwatt hours
 * 			--> $
 * 5.	Industrial, 0 on-peak kwatt hours, 0 non-peak kwatt hours 
 * 			--> $
 * 6.	Industrial, 0 on-prsk kwatt hours, 2000 non-peak kwatt hours 
 * 			--> $
 */ 
import java.util.Scanner;
public class Electric
{
	final int R_FLAT_FEE = 12;
		//residential flat fee
	final double R_RATE = 0.095;
		//residential rate per kilowatt hour
	final int C_FLAT_FEE = 120;
		//commercial flat fee
	final double C_RATE = 0.083;
		//commercial rate per kilowatt hour 
	final int I_NON_PEAK_CONSTANT = 108;
		//industrial non-peak hour constant pay up to 1000 kilowatt hours
	final double I_NON_PEAK_RATE = 0.047;
		//industrial non-peak hour rate per kilowatt hours after 1000
	final int I_PEAK_CONSTANT = 152;
		//industrial peak hour constant pay up to 1000 kilowatt hours 
	final double I_PEAK_RATE = 0.109;
		//industrial peak hour rate per kilowatt hours after 1000
	protected String userBillingMethod, errorMessage;
		//the first is the selection the user makes (R, C, I)
		//the second is the error message I reference in printInfo
	protected double userRate;
		//this reinitializes to rHrsRate, cHrsRate, or sum of iPeakHoursRate 
			//and iNonPeakHoursRate based on the selection (R, C, I)
	protected double kwattHrs, iPeakKwattHrs, iNonPeakKwattHrs;
		//kilowatt hours, peak kilowatt hours, non-peak kilowatt hours
			//(1st for Residential and Commercial, 2nd & 3rd for Industrial)
	protected double rHrsRate, cHrsRate, iPeakHrsRate, iNonPeakHrsRate;
		//all full rates (taking into account everything): 
		//residential hours rate, commercial hours rate, industrial peak
			//hours rate, industrial non-peak hours rate
		//this is what userRate reinitializes to  
	protected boolean hasErrors;
		//this is the condition on printing errors (if true) 
	public Electric()//initialize all field variables 
	{
		userBillingMethod = new String("");
		userRate = 0.0;
		kwattHrs = 0.0;
		
		iPeakKwattHrs = 0.0;
		iNonPeakKwattHrs = 0.0;
		rHrsRate = 0.0;
		cHrsRate = 0.0;
		iPeakHrsRate = 0.0;
		iNonPeakHrsRate = 0.0;
		errorMessage = new String("");
		hasErrors = false;
	}
	public static void main(String[] args)
		//create a new instance of everything and call method computeIt
	{
		Electric e = new Electric();
		e.computeIt();
	}
	public void computeIt() 
		//runs all the other methods in the class 
	{
		getData();
		calculateCost();
		printInfo();
	}
	public void getData() //overall, prompts user and collects info
	{
		Scanner keyboard = new Scanner(System.in);//new instance of Scanner
		System.out.println("\n\n\n");//3 new lines 
		System.out.printf("\tWelcome to Electric.java! \nThis program will " + 
			"take in your billing method and the number of kilowatt hours used; " +
			"based on this it will calculate your electrical bill.  " +
			"\nEnter your billing method, R for residential, C for " + 
			"commercial, and I for industrial%1s-> ", "");
			//introduction and user prompt for billing method 
			//it would be nicer if we used "please input" 
		userBillingMethod = keyboard.next();//stores user input
		if (userBillingMethod.equalsIgnoreCase("R") || 
			userBillingMethod.equalsIgnoreCase("C"))
			//essentially, both Residential and Commercial only have 
					//1 peak hour to gather and print out, 
					//so we can combine them
					//also, the .equalsIgnoreCase accepts both 'r' and 'R'
		{
			System.out.printf("Enter the number of kilowatt " + 
				"hours of electricity used%33s-> ", "");
				//prompt user
				//it would be nicer if we used "please input"
			kwattHrs = keyboard.nextDouble();
				//store user input
		}
		else if (userBillingMethod.equalsIgnoreCase("I"))
		{
			System.out.printf("Enter the number of peak hour " + 
				"kilowatt hours you use%33s-> ", "");
				//it would be nicer if we used "please input"
			iPeakKwattHrs = keyboard.nextDouble();
			System.out.printf("Enter the number of non-peak hour " + 
				"kilowatt hours you use%29s-> ", "");
				//it would be nicer if we used "please input"
			iNonPeakKwattHrs = keyboard.nextDouble();
				//store user input
		}
		else 
		{
			errorMessage = "invalid billing method";
				//referenced later in printInfo; send error message if 
				//user does not input billing method properly
			hasErrors = true;
				//referenced later in printInfo; prints error messages 
				//instead of receipt
		}
	}
	public void calculateCost() 
	{
		rHrsRate = R_FLAT_FEE + R_RATE*(kwattHrs);
			//reference the final ints and doubles if confusion arises 
		//cHrsRate = C_FLAT_FEE + C_RATE*(kwattHrs);
		if (kwattHrs < 0) //if user input is negative 
		{
			errorMessage = "negative hours";
				//referenced later in printInfo; send error message if 
				//user inputs negative hours 
			hasErrors = true;
				//referenced later in printInfo; prints error messages 
				//instead of receipt
		}
		else if (kwattHrs >= 1000) //if after 1000 hours 
			//this "else if / else" structure accounts for the 1st 1000 hours 
			//as a constant price, then starting to increase the price after 
			//1000 hours 
		{
			cHrsRate = C_FLAT_FEE
				+ C_RATE*(kwattHrs-1000);
				//increased price after 1000 hours 
		}
		else //only section left is between 0 (inclusive) and 1000 hours
			//second part of the "else if / else" structure 
		{
			cHrsRate = C_FLAT_FEE;
				//price is the flat fee between 0 and 1000 hours 
		}
		
		if (iPeakKwattHrs < 0) //if user input is negative 
		{
			errorMessage = "negative hours";
				//referenced later in printInfo; send error message if 
				//user inputs negative hours 
			hasErrors = true;
				//referenced later in printInfo; prints error messages 
				//instead of receipt
		}
		else if (iPeakKwattHrs >= 1000)
			//same structure as before; this time, instead of calculating 
			//for commercial, this calculates for industrial peak hours 
		{
			iPeakHrsRate = I_PEAK_CONSTANT 
				+ I_PEAK_RATE*(iPeakKwattHrs-1000);
		}
		else 
		{
			iPeakHrsRate = I_PEAK_CONSTANT;
		}
		if (iNonPeakKwattHrs < 0) //if user input is negative 
		{
			errorMessage = "negative hours";
				//referenced later in printInfo; send error message if 
				//user inputs negative hours 
			hasErrors = true;
				//referenced later in printInfo; prints error messages 
				//instead of receipt
		}
		else if (iNonPeakKwattHrs >= 1000)
			//same structure as before, just this time with industrial
			//non-peak hours
		{
			iNonPeakHrsRate = I_NON_PEAK_CONSTANT 
				+ I_NON_PEAK_RATE*(iNonPeakKwattHrs-1000);
		}
		else 
		{
			iNonPeakHrsRate = I_NON_PEAK_CONSTANT;
		}
	}
	public void printInfo()
	{
		if (hasErrors == true)
			//if there are errors, this gets printed instead of the typical bill
		{
			if (errorMessage == "invalid billing method")
				//this is what was referenced from the initial R, C, I choice
			{
				System.out.println("Error, please try again");
					//another more accurate error: 
					//"Error, please enter another valid rate description."
			}
			else if (errorMessage == "negative hours")
				//this is what was referenced from the kilowatt hour input choice
			{
				System.out.println("Please enter a positive number of hours.");
			}
			else
			{
				System.out.println("Something's wrong. Please try again.");
					//This shouldn't ever run because I should never have 
						//another error message. 
			}
		}
		else
			//if there aren't errors, the bill gets printed 
		{
			if (userBillingMethod.equalsIgnoreCase("R"))
				//if the bill is residential: 
			{
				//printing out info for residential, starting with the 
				//bill title
				System.out.println("\n\nResidential Bill\n\n");
				userRate = rHrsRate;
					//makes the userRate a residential rate 
				System.out.printf("Peak Hours%,16.2f\n", kwattHrs);
				System.out.printf("Cost%10s$%,11.2f\n", "", userRate);
			}
			else if (userBillingMethod.equalsIgnoreCase("C"))
			{
				//printing out info for commmercial, starting with the 
				//bill title
				System.out.println("\n\nCommercial Bill\n\n");
				userRate = cHrsRate;
					//makes the userRate a commercial rate 
				System.out.printf("Peak Hours%,16.2f\n", kwattHrs);
				System.out.printf("Cost%10s$%,11.2f\n", "", userRate);
			}
			else if (userBillingMethod.equalsIgnoreCase("I"))
			{
				//printing out info for industrial, starting with the 
				//bill title
				System.out.println("\n\nIndustrial Bill\n\n");
				userRate = iPeakHrsRate + iNonPeakHrsRate;
					//makes the userRate the sum of the peak and non-peak
					//hours rate 
				System.out.printf("Peak Hours%,16.2f\n", iPeakKwattHrs);
				System.out.printf("Off-Peak Hours%,12.2f\n", iNonPeakKwattHrs);
				System.out.printf("Cost%10s$%,11.2f\n", "", userRate);
			}
			else
			{
				//nothing needed -- if I put anything else here, it will  
					//not show
			}
		}
		System.out.println("\n\n\n");//3 new lines 
	}
}
