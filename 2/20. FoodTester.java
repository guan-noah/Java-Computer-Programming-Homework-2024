//FoodTester.java
/*
 * Noah Guan
 * 10-23-2024
 * P.6 Java w/ Mr. Yu
 * FoodTester.java
 * Program #20
 * This program essentially just loads Food.java's constructor and runs/prints it.
 * It also loads Pizza.java's constructor and prints it as well. 
*/
public class FoodTester
{
	public FoodTester()
	{
		//empty constructor
	}
	public static void main(String[] args)
	{
		System.out.println("\n\n\n");	//3 lines
		Food food = new Food();
			//sending in parameters for printing
		food.printForSale();
			//calling the actual method
		Food food1 = new Food ("baked", "banana", "muffins");
			//sending in parameters for printing
		food1.printForSale();
			//calling the actual method
		Food food2 = new Food ("fried", "yam", "fritters");
			//sending in parameters for printing
		food2.printForSale();
			//calling the actual method
		Pizza pizza = new Pizza ("pizza", "pepperoni");
			//sending in parameters for printing
		pizza.printForSale();
			//calling the actual method
		DeepDishPizza ddp = new DeepDishPizza ("saucy sauce");
			//sending in parameters for printing
		ddp.printForSale();
			//calling the actual method
		System.out.println("\n\n\n");	//3 lines
	}
}
