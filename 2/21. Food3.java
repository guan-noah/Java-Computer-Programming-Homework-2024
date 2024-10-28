//Food3.java
/*
 * Noah Guan
 * 10-23-2024
 * P.6 Java w/ Mr. Yu
 * Food3.java
 * Program #21
 * This program takes in the parameters sent by FoodTester3.java, 
 * 		makes a string from them, and prints out the string.
*/
public class Food3 
{
	protected String str, str3; //str3 stands for the variable that holds
		//things added to Food3 not in Food. 
	double totalCost;
	
	public Food3 ()
	{
		//oldStr = new String("");
		str = new String("");
		str3 = new String("");
		totalCost = -1.0;
	}
	
	public Food3 (String prepMethod, String ingredient, String name, 
		int numOfPieces, double costPerPiece)//accepting parameters
	{	
		totalCost = numOfPieces*costPerPiece/100;//making the totalCost
		str3 = String.format(" will be be sold for " + costPerPiece + 
			" cents each. With " + numOfPieces + " " + name + 
			"(s), $%.2f can be made. ", totalCost); //making new string
		str = "At the sale: " + name + "s " + prepMethod + " with " + ingredient
			+ str3; //adding old string to new string
		//str = "Food Type: " + name + "\nNumber of Pieces: " + numOfPieces
		//	+ "\nCost per Piece: $" + costPerPiece;
	}

	public void printForSale()
	{
		System.out.println(str);//printing out string
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
