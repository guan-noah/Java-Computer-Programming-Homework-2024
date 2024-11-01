//Noah Guan
//9-16-24 (Monday) 
//Per 6 Java
/*
 * ExtraAssignment.java
 * Program #8
 * This program gives an example of using the Scanner class. 
 * Working on:	1. Inputting from the keyboard 
 * 				2. Using a Math method 
 * 				3. Making this code: 
 * 
 Pseudocode: 
 import Scanner 
 public class Clothes (class header)
	public static void main (main header) 
		make instance of Scanner 
		print 3 blank lines 
		
		print(prompt_welcome)
		prompt for cost of one shirt
		scanner.nextDouble() 
		clear scanner
		prompt for number of shirts 
		scanner.nextLong
		*rinse and repeat for pants and shoes* 
		
		print receipt section: 
		final constants for tax & discount
		 - tax
		 - shirts 
		 - pants
		 - shoes
		 - subtotal
		 - discount
		 - total before tax
		 - total
		
		print 3 blank lines 
		
		
 Welcome to ClothesRUS, your one stop shopping for sihrts, pants and shoes. 
 Please enter the cost of one shirt -> 100
 Please enter the number of shirts purchased -> 5 
 Please enter the cost of one pair of pants -> 124.50 
 Please enter the number of pairs of pants purchased -> 3 
 Please enter the cost of one pair of shoes -> 149.99
 Please enter the number of pairs of shoes purchased -> 2
 
 Shirts 	= $		500.00
 Pants 		= $ 	373.50
 Shoes 		= $ 	299.98
 Subtotal 	= $ 	1,173.48 //use the printf technique
 Tax 		= $ 	108.55
 Total 		= $ 	1,282.03 
 
 Testing: 
  - 100 shirts, same # of pants and shoes; everything costs $100 each
	--> accurate run (30K subtotal, 27K total before tax, 29.531.25 total
  - 2B of each of everything; everything costs $1
	--> accurate run (6M subtotal, 5.4M total before tax, 5,906,250 total
	
	  
Shirts              = $2,000,000.00

Pants               = $2,000,000.00

Shoes               = $2,000,000.00

Subtotal            = $6,000,000.00

Discount            = $600,000.00

Total before tax:   = $5,400,000.00

Tax:                = $506,250.00

Total:              = $5,906,250.00
  
  
The total cost for 6,000,000 items is 5,906,250.00. Thank you for shopping at ClothesRUS.
	
  - 10 shirts, $10 per shirt; 1000 pants, $1000 per pair of pants; 
		1M shoes, $1M per pair of shoes 
	--> accurate run (1,000,001,000,100 subtotal, 900,000,900,090.00 total
			before tax, 984,375,984,473.44 total)

Shirts              = $100.00

Pants               = $1,000,000.00

Shoes               = $1,000,000,000,000.00

Subtotal            = $1,000,001,000,100.00

Discount            = $100,000,100,010.00

Total before tax:   = $900,000,900,090.00

Tax:                = $84,375,084,383.44

Total:              = $984,375,984,473.44

The total cost for 1,001,010 items is 984,375,984,473.44. Thank you for shopping at ClothesRUS.
  - 'shirtNumber' shirts
	--> error: 'Exception in thread "main" java.util.InputMismatchException
			...
			at Clothes.main(Clothes.java:85
  - 
*/
/*
 *Mr. Yu's test run: 
Please enter the cost of one shirt as a decimal -> 32.41

Please enter the number of shirts purchased as an integer -> 9

Please enter the cost of one pair of pants as a decimal -> 19.99

Please enter the number of pairs of pants purchased as an integer -> 9821307498723

Please enter the cost of one pair of shoes as a decimal -> 43.1

Please enter the number of pairs of shoespurchased as an integer -> 234213
Shirts              = $291.69

Pants               = $196,327,936,899,472.75

Shoes               = $10,094,580.30

Subtotal            = $196,327,946,994,344.75

Discount            = $19,632,794,699,434.48

Total before tax:   = $176,695,152,294,910.28

Tax:                = $16,565,170,527,647.84

Total:              = $193,260,322,822,558.12


The total cost for 9,821,307,732,945 items is 193,260,322,822,558.12. Thank you for shopping at ClothesRUS. 
*/
import java.util.Scanner;

public class Clothes
{	
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in); 	//Opens the Scanner
													//System.in is the keyboard; file.io are files 
		System.out.println("\n\n\n");
		
		//ClothesRUS header/intro section: 
		System.out.println("\tCLOTHESRUS\n");
		System.out.println("Welcome to ClothesRUS, your one stop " + 
		"shopping for shirts, pants and shoes. \n");
		System.out.println("Disclaimer/Warning: Please note that we " + 
			"do not accept returns; all sales are final." + 
			"\nPlease note that our stock (and pricing!) is near unlimited. " + 
			"Spend to your heart's content! \n" + 
			"\nWhile Supplies Last \n");
		
		//Gathering information from user
		//Shirts: 
		System.out.print("Please enter the cost of one shirt as a " + 
			"decimal -> "); //prompt user for input
		double shirtCost = keyboard.nextDouble(); //D&I cost and 
			//use scanner to initialize
		keyboard.nextLine(); //buffer for scanner
		System.out.print("\nPlease enter the number of shirts purchased " + 
			"as an integer -> "); //prompt user for input 
		long shirtNumber = keyboard.nextLong(); //D&I number of item and 
			//use scanner to initialize
		keyboard.nextLine(); //buffer for scanner
		double totalShirtCost = shirtCost*shirtNumber; //D&I total cost to 
			//the cost times the number of item 
		
		//Pants: (note: basically the same code as shirts; just that the 
			//names of the variables are different)
		System.out.print("\nPlease enter the cost of one pair of pants " + 
			"as a decimal -> ");
		double pantsCost = keyboard.nextDouble();
		keyboard.nextLine();
		System.out.print("\nPlease enter the number of pairs of pants " + 
			"purchased as an integer -> ");
		long pantsNumber = keyboard.nextLong();
		keyboard.nextLine();
		double totalPantsCost = pantsCost*pantsNumber;
		
		//Shoes: (same note)
		System.out.print("\nPlease enter the cost of one pair of shoes " + 
			"as a decimal -> ");
		double shoesCost = keyboard.nextDouble();
		keyboard.nextLine();
		System.out.print("\nPlease enter the number of pairs of shoes " + 
			"purchased as an integer -> ");
		long shoesNumber = keyboard.nextLong();
		keyboard.nextLine();
		double totalShoesCost = shoesCost*shoesNumber;
		
		//Totals
		double subTotal = totalShirtCost + totalPantsCost + totalShoesCost;
		final double SALES_TAX = 0.09375;
		final double DISCOUNT = 0.1;
		double dollarAmtDiscount = DISCOUNT*subTotal;
		double totalBeforeTax = subTotal*(1-DISCOUNT);
		double dollarAmtSalesTax = SALES_TAX*totalBeforeTax;
		double totalCost = totalBeforeTax * (1+SALES_TAX);
		long numOfItemsBought = shirtNumber + pantsNumber + shoesNumber;
		
		//Print out standard java class' receipt 
		/*
		 * this is the better part I think we could incorporate
		System.out.printf("\n%-20sQuantity = %,-10d Cost = $%,5.2f\n\n", 
			"Shirts: ", shirtNumber, totalShirtCost);
		System.out.printf("%-20sQuantity = %,-10d Cost = $%,5.2f\n\n", 
			"Pants: ", shirtNumber, totalPantsCost);
		System.out.printf("%-20sQuantity = %,-10d Cost = $%,5.2f\n\n", 
			"Shoes: ", shirtNumber, totalShoesCost);
		*/
		
		System.out.printf("%-20s= $%,6.2f\n\n", 
			"Shirts", totalShirtCost);
		System.out.printf("%-20s= $%,6.2f\n\n", 
			"Pants", totalPantsCost);
		System.out.printf("%-20s= $%,6.2f\n\n", 
			"Shoes", totalShoesCost);
		System.out.printf("%-20s= $%,6.2f\n\n", 
			"Subtotal", subTotal);
		System.out.printf("%-20s= $%,6.2f\n\n", 
			"Discount", dollarAmtDiscount);
		System.out.printf("%-20s= $%,6.2f\n\n", 
			"Total before tax: ", totalBeforeTax);
		System.out.printf("%-20s= $%,6.2f\n\n", 
			"Tax: ", dollarAmtSalesTax);
		System.out.printf("%-20s= $%,6.2f\n\n", 
			"Total: ", totalCost);
		
		//Print out summary for the java class' receipt
		System.out.printf("\nThe total cost for %,-1d items is " + 
			"$%,-5.2f. Thank you for shopping at ClothesRUS.", numOfItemsBought, totalCost);
		
		//Print out my preferred receipt
		System.out.println("\n\n\n\n...But I think this looks better as a receipt: ");
		System.out.printf("\n%-20sQuantity = %,-10d Cost = $%,-10.2f\n\n", 
			"Shirts: ", shirtNumber, totalShirtCost);
		System.out.printf("%-20sQuantity = %,-10d Cost = $%,-10.2f\n\n", 
			"Pants: ", pantsNumber, totalPantsCost);
		System.out.printf("%-20sQuantity = %,-10d Cost = $%,-10.2f\n\n", 
			"Shoes: ", shoesNumber, totalShoesCost);
		System.out.printf("%-20s$%,-20.2f\n\n", 
			"Subtotal: ", subTotal);
		System.out.printf("%-20s$%,-20.2f\n\n", 
			"Discount: ", dollarAmtDiscount);
		System.out.printf("%-20s$%,-20.2f\n\n", 
			"Total before tax: ", totalBeforeTax);
		System.out.printf("%-20s$%,-20.2f\n\n", 
			"Tax: ", dollarAmtSalesTax);
		System.out.printf("%-20s$%,-20.2f\n\n", 
			"Total: ", totalCost);
		
		/*
		 * code with Format that I used: 
		System.out.printf("\n" + Format.left("Shirts: ", 20) + "Quantity = " + 
			Format.left(shirtNumber, 10) + " Cost = $" + 
			Format.left(totalShirtCost, 10, 2) + "\n\n");
		System.out.printf(Format.left("Pants: ", 20) + "Quantity = " + 
			Format.left(pantsNumber, 10) + " Cost = $" + 
			Format.left(totalPantsCost, 10, 2) + "\n\n");
		System.out.printf(Format.left("Shoes: ", 20) + "Quantity = " + 
			Format.left(shoesNumber, 10) + " Cost = $" + 
			Format.left(totalShoesCost, 10, 2) + "\n\n");
		System.out.printf(Format.left("Subtotal: ", 20) + "$" + 
			Format.left(subTotal, 20, 2) + "\n\n");
			
		System.out.printf(Format.left("Discount: ", 20) + "$" + 
			Format.left(dollarAmtDiscount, 20, 2) + "\n\n");
		System.out.printf(Format.left("Total before tax: ", 20) + "$" + 
			Format.left(totalBeforeTax, 20, 2) + "\n\n");
		System.out.printf(Format.left("Tax: ", 20) + "$" + 
			Format.left(dollarAmtSalesTax, 20, 2) + "\n\n");
		System.out.printf(Format.left("Total: ", 20) + "$" + 
			Format.left(totalCost, 20, 2) + "\n\n");
		*/
		
		//Print out summary for my preferred receipt
		System.out.printf("\nThe total cost for %,-1d items is " + 
			"$%,-5.2f. Thank you for shopping at ClothesRUS.", numOfItemsBought, totalCost);

		System.out.println("\n\n\n");
	}
}
