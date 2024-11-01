//FoodTester3.java
/*
 * Noah Guan
 * 10-23-2024
 * P.6 Java w/ Mr. Yu
 * FoodTester3.java
 * Program #24
 * This program essentially just loads Food3.java's constructor and runs/prints it.
 * It also loads Pizza3.java's constructor and prints it as well. 
*/
public class FoodTester3
{
	public FoodTester3()
	{
		
	}
	public static void main(String[] args)
	{
		System.out.println("\n\n\n");
		Food3 food = new Food3();
		food.printForSale();
		Food3 food1 = new Food3 ("baked", "banana", "muffin", 12, 50);
		food1.printForSale();
		Food3 food2 = new Food3 ("fried", "yam", "fritter", 3, 100);
		food2.printForSale();
		Pizza3 pizza = new Pizza3 ("pizza", "pepperoni", 8, 250);
		pizza.printForSale();
		
		DeepDishPizza3 ddp = new DeepDishPizza3 ("saucy sauce", 8, 250);
		ddp.printForSale();
		
		System.out.println("\n\n\n");
	}
}
