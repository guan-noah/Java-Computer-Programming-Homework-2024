//ExecuteRide.java
/* Noah Guan
 * 11-01-2024
 * Per.6 Java w/ Mr. Yu
 * ExecuteRide.java
 * Program #XX
 * This program is from homework; it goes with Ride. 
 * no pseudocode required 
 * Testing Plan: 
 */
import java.util.Scanner;
public class ExecuteRide
{
	private int miles;
	private String location;
	
	public ExecuteRide()
	{
		location = new String("");
		miles = 0;
	}
	
	public static void main(String[] args)
	{
		ExecuteRide er = new ExecuteRide();
		er.runRide();
	}
	public void runRide()
	{
		input();
		Ride r = new Ride(miles);
		location = r.whereAmI();
		output();
	}
	public void input()
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter how many miles you will travel.");
		miles = kb.nextInt();
	}
	public void output()
	{
		Ride r = new Ride(miles);
		if (miles <= 30)
		{
			System.out.println("You'll be in " + r.whereAmI() + " after " + 
				miles + " miles.");
		}
		else
			System.out.println("Reread the prompt.");
	}
}


