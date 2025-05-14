//DeepDishPizza.java
/*
 * Noah Guan
 * 10-23-2024
 * P.6 Java w/ Mr. Yu
 * DeepDishPizza.java
 * Program #19
 * This program extends off of Pizza.java.
 * This is also supposed to be the "Food2.java", that's why the number skips to 3. 
 */
public class DeepDishPizza extends Pizza		//this makes a subclass of Pizza
{
	protected String ingredientIn, specialName;	/*these are extra :)
												(just for fun)
												technically, we don't need this
												*/
	public DeepDishPizza()
	{
		specialName = "the Pride of Chicago"; //initializing extra 
		ingredientIn = "a LOT of sauce";//default (my backup)
	}
	public DeepDishPizza(String ingredientIn)
	{
		super("deep dish pizza", ingredientIn);//sending in these variables
		/*super.str = "At the sale: deep dish pizza, " + specialName + 
			" baked with " + ingredient;
			//this also works but is more complicated 
		*/
	}
}
