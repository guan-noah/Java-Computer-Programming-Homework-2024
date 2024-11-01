//DeepDishPizza3.java
/*
 * Noah Guan
 * 10-23-2024
 * P.6 Java w/ Mr. Yu
 * DeepDishPizza3.java
 * Program #20.3
 * This program extends off of Pizza.java.
 */
public class DeepDishPizza2 extends Pizza2
{
	protected String ingredientIn, specialName;	/*these are extra :)
												(just for fun)
												technically, we don't need this
												*/
	public DeepDishPizza2()
	{
		specialName = "the Pride of Chicago"; //initializing just for fun
		ingredientIn = "a LOT of sauce"; 
		//constructor could be empty if wanted
	}
	public DeepDishPizza2(String ingredientIn, int numOfPiecesIn, 
		double costPerPieceIn)//accepting parameters
	{
		super("deep dish pizza, the Pride of Chicago", ingredientIn,
			numOfPiecesIn, costPerPieceIn);//sending in parameters to Pizza2
		/* Another way of doing this: 
		 * totalCost = numOfPieces*costPerPiece;
		 * String name = "pizza";
		 * str3 = " will be be sold for " + costPerPiece + " cents each. With " + 
			numOfPieces + " " + name + "(s), $"
			+ totalCost + " can be made. ";
		 * super.str = "At the sale: deep dish pizza" + ", " + specialName + 
			" baked with " + ingredient + str3;
		 */
	}
}
