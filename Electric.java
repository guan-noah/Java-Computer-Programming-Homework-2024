//Electric.java
/* Noah Guan
 * 10-30-2024
 * Per. 6 Java w/ Mr. Yu
 * Electric.java
 * Program #26
 * This program creates different rates for different users
 * Pseudocode: 
 * class header 
 * 		constructor 
 * 	method getData
 * 		prompt user: "Welcome to Electric.java! This program will take in 
 * 			your billing method and the number of kilowatt hours used; 
 * 			based on this it will calculate your electrical bill.  
 * 			Please input your billing method ('R' for a residential bill, 
 * 			'C for a commercial bill, and 'I' for an industrial bill.): 
 * 		userBillingMethod = Scanner.next();
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
 *  printInfo
 * 		print Residential Bill
 * 		
 * Testing Plan: 
 * 	
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
	protected String userBillingMethod;
		//this is the selection the user makes (R, C, I)
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
	public Electric()
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
	}
	public static void main(String[] args)
	{
		Electric e = new Electric();
		e.computeIt();
	}
	public void computeIt() 
	{
		getData();
		calculateCost();
		printInfo();
	}
	public void getData() 
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.printf("\tWelcome to Electric.java! \nThis program will " + 
			"take in your billing method and the number of kilowatt hours used; " +
			"based on this it will calculate your electrical bill.  " +
			"\nEnter your billing method, R for residential, C for " + 
			"commercial, and I for industrial%1s-> ", "");
			//it would be nicer if we used "please input" 
		userBillingMethod = keyboard.next();
		if (userBillingMethod.equals("R") || userBillingMethod.equals ("C"))
		{
			System.out.print("Enter the number of kilowatt " + 
				"hours of electricity used%33s-> ", "");
				//it would be nicer if we used "please input"
			kwattHrs = keyboard.nextDouble();
		}
		else if (userBillingMethod.equals("I"))
		{
			System.out.print("Enter the number of peak hour " + 
				"kilowatt hours you use%33s-> ", "");
				//it would be nicer if we used "please input"
			iPeakKwattHrs = keyboard.nextDouble();
			System.out.print("Enter the number of non-peak hour " + 
				"kilowatt hours you use%33s-> ", "");
				//it would be nicer if we used "please input"
			iNonPeakKwattHrs = keyboard.nextDouble();
		}
		else 
		{
			System.out.println("Error, please try again");
				//another more accurate error: 
				//"Error, please enter another valid rate description."
		}
	}
	public void calculateCost() 
	{
		rHrsRate = R_FLAT_FEE + R_RATE*(kwattHrs);
			//reference the final ints and doubles if confusion arises 
		cHrsRate = C_FLAT_FEE + C_RATE*(kwattHrs);
		if (kwattHrs < 1000)
		{
			iPeakHrsRate = I_PEAK_CONSTANT 
				+ I_PEAK_RATE*(iPeakKwattHrs-1000);
			iNonPeakHrsRate = I_NON_PEAK_CONSTANT 
				+ I_NON_PEAK_RATE*(iNonPeakKwattHrs-1000);
		}
		else 
		{
			iPeakHrsRate = I_PEAK_CONSTANT;
			iNonPeakHrsRate = I_NON_PEAK_CONSTANT;
		}
		
		if (userBillingMethod.equals("R"))
		//note: could use userBillingMethod.equalsIgnoreCase("R") but 
			//Mr. Yu didn't teach that yet
		{
			System.out.println("\n\nResidential Bill\n\n");
			userRate = rHrsRate;
		}
		else if (userBillingMethod.equals("C"))
		{
			System.out.println("\n\nCommercial Bill\n\n");
			userRate = cHrsRate;
		}
		else if (userBillingMethod.equals("I"))
		{
			System.out.println("\n\nIndustrial Bill\n\n");
			userRate = iPeakHrsRate + iNonPeakHrsRate;
		}
		else
		{
			
		}
	}
	public void printInfo()
	{
		if (userBillingMethod.equals("R") || userBillingMethod.equals("C"))
		{
			System.out.printf("Peak Hours%,15f\n", kwattHrs);
			System.out.printf("Cost%10s$%,10f\n", "", userRate);
		}
		else if (userBillingMethod.equals("I"))
		{
			System.out.printf("Peak Hours%,15f\n", iPeakKwattHrs);
			System.out.printf("Off-Peak Hours%,14f\n", iNonPeakKwattHrs);
			System.out.printf("Cost%10s$%,21f\n", "", userRate);
		}
		else
		{
			
		}
	}
}
