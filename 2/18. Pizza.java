//Pizza.java
/*
 * Noah Guan
 * 10-23-2024
 * Java P.6 w/ Mr. Yu
 * Pizza.java
 * Program #18
 * This program extends off of Food.java. 
*/
public class Pizza extends Food		//this makes the first and only 
									//subclass of Food
{
	public Pizza()
	{
		//empty constructor
	}
	public Pizza(String nameIn, String ingredientIn)
	{
		super.str = "At the sale: " + nameIn + " baked with " + ingredientIn;
		//this formulates the string (super string overwritten)
	}
}
