//Ride.java
/* Noah Guan
 * 11-01-2024
 * Per.6 Java w/ Mr. Yu
 * Ride.java
 * Program #XX
 * This program is from homework; it goes with ExecuteRide.
 * no pseudocode required 
 * Testing Plan: 
 */
 public class Ride
{
	private int miles;
	public Ride()
	{
		miles = 0;
	}
	public Ride(int milesIn)
	{
		miles = milesIn;
	}
	public String whereAmI()
	{
		String location = new String("");
		if ((miles == 1) || (miles == 30))
			location = "Cupertino";
		if (((miles >= 2) && (miles <= 6)) || ((miles >= 4) && (miles <= 29)))
			location = "Los Altos";
		if (((miles >= 7) && (miles <= 11)) || ((miles >= 19) && (miles <= 23)))
			location = "Palo Alto";
		if ((miles == 12) || (miles == 13))
			location = "Menlo Park";
		if ((miles >= 14) || (miles <= 18))
			location = "Portola Valley";
		return location;
	}
}
