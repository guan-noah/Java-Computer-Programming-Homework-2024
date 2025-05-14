//FoodTester2.java
/*
 * Noah Guan
 * 10-23-2024
 * P.6 Java w/ Mr. Yu
 * FoodTester2.java
 * Program #20.4
 * This program essentially just loads Food3.java's constructor and runs/prints it.
 * It also loads Pizza3.java's constructor and prints it as well. 
*/
public class FoodTester2
{
	public FoodTester2()
	{
		//empty constructor
	}
	public static void main(String[] args)
	{
		System.out.println("\n\n\n");	//3 lines
		Food2 food = new Food2();
			//sending in parameters for printing
		food.printForSale();
			//calling the actual method
		Food2 food1 = new Food2("baked", "banana", "muffin", 12, 50);
			//sending in parameters for printing
		food1.printForSale();
			//calling the actual method
		Food2 food2 = new Food2("fried", "yam", "fritter", 3, 100);
			//sending in parameters for printing
		food2.printForSale();
			//calling the actual method
		Pizza2 pizza = new Pizza2("pizza", "pepperoni", 8, 250);
			//sending in parameters for printing
		pizza.printForSale();
			//calling the actual method
		DeepDishPizza2 ddp = new DeepDishPizza2 ("saucy sauce", 8, 250);
			//sending in parameters for printing
		ddp.printForSale();
			//calling the actual method
		System.out.println("\n\n\n");	//3 lines
	}
}
