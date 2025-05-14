//Food.java
/*
 * Noah Guan
 * 10-23-2024
 * P.6 Java w/ Mr. Yu
 * Food.java
 * Program #17
 * This program takes in commands from FoodTester.java and prints out a string.
*/
public class Food
{
	protected String str;		//this declares the string used for printing
								//that I will use in subclasses
	
	public Food ()
	{
		str = new String("");	//this initializes the same string mentioned
	}
	
	public Food (String prepMethodIn, String ingredientIn, String nameIn)
	{	
		str = "At the sale: " + nameIn + " " + prepMethodIn + " with " + ingredientIn;
		//this builds the string from the input (variables)
	}

	public void printForSale()
	{
		System.out.println(str);
		//this prints the string out
	}
}


/*
In FoodTester's main method
		Food food1 = new Food ("baked", "banana", "muffins");
		food1.printForSale();
		Food food2 = new Food ("fried", "yam", "fritters");
		food2.printForSale();
		Pizza pizza = new Pizza ("pepperoni");
		pizza.printForSale();
*/
