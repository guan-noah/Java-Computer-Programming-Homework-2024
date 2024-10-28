//Food2.java
/*
 * Noah Guan
 * 10-23-2024
 * P.6 Java w/ Mr. Yu
 * Food2.java
 * Program #20.1
 * This program takes in parameters from FoodTester2.java, makes a string
 * 		from the parameters, and prints it out.
*/
public class Food2
{
	protected String str, str3; //str3 stands for the variable that holds
		//things added to Food2 not in Food. 
	double totalCost;
	
	public Food2()
	{
		//oldStr = new String("");
		str = new String(""); //initializing
		str3 = new String("");
		totalCost = -1.0;
	}
	
	public Food2(String prepMethod, String ingredient, String name, 
		int numOfPieces, double costPerPiece)//accepting parameters
	{	
		totalCost = numOfPieces*costPerPiece/100.0;//making totalCost
		str3 = String.format(" will be be sold for " + costPerPiece + 
			" cents each. With " + numOfPieces + " " + name + 
			"(s), $%,.2f can be made. ", totalCost);	//making the new string
		str = "At the sale: " + name + "s " + prepMethod + " with " + ingredient
			+ str3;	//adding the new string to the old string
		//str = "Food Type: " + name + "\nNumber of Pieces: " + numOfPieces
		//	+ "\nCost per Piece: $" + costPerPiece;
	}

	public void printForSale()
	{
		System.out.println(str); //printing out the new string
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
