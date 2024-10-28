//DeepDishPizza3.java
/*
 * Noah Guan
 * 10-23-2024
 * P.6 Java w/ Mr. Yu
 * DeepDishPizza3.java
 * Program #23
 * This program extends off of Pizza3.java.
 */
public class DeepDishPizza3 extends Pizza3
{
	protected String ingredient, specialName;	/*these are extra :)
												(just for fun)
												technically, we don't need this
												*/
	public DeepDishPizza3()
	{
		specialName = "the Pride of Chicago";//initializing just for fun
		ingredient = "a LOT of sauce";
		//technically, this should be an empty constructor
	}
	public DeepDishPizza3(String ingredient, int numOfPieces, double costPerPiece)
	{//accepting parameters
		super("deep dish pizza, the Pride of Chicago", ingredient,
			numOfPieces, costPerPiece);//sending parameters to Pizza3
			//the Pride of Chicago part is just for fun because I had extra time
			// -- the format will look weird but that's part of it
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
