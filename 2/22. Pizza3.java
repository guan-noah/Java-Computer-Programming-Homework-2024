//Pizza3.java
/*
 * Noah Guan
 * 10-23-2024
 * Java P.6 w/ Mr. Yu
 * Pizza3.java
 * Program #22
 * This program extends off of Food3.java. 
*/
public class Pizza3 extends Food3
{
	public Pizza3()
	{
		//empty constructors
	}
	public Pizza3(String name, String ingredient, int numOfPieces, 
		double costPerPiece)//accepting parameters
	{
		super("baked", ingredient, name, numOfPieces, costPerPiece);
			//sending parameters to Food3
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
