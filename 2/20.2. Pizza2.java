//Pizza2.java
/*
 * Noah Guan
 * 10-23-2024
 * Java P.6 w/ Mr. Yu
 * Pizza2.java
 * Program #20.2
 * This program extends off of Food2.java. 
*/
public class Pizza2 extends Food2
{
	public Pizza2()
	{
		//empty constructor
	}
	public Pizza2(String nameIn, String ingredientIn, int numOfPiecesIn,
		double costPerPieceIn)//taking in parameters
	{
		super("baked", ingredientIn, nameIn, numOfPiecesIn, costPerPieceIn);
			//sending parameters to Food2
		/* Another way of doing this: 
		 * totalCost = numOfPieces*costPerPiece;
		String name = "pizza";
		str3 = " will be be sold for " + costPerPiece + " cents each. With " + 
			numOfPieces + " " + name + "(s), $"
			+ totalCost + " can be made. ";
		 * super.str = "At the sale: pizza baked with " + ingredient + str3;
		 */
	}
}
