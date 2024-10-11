//Noah Guan
//9-11-24 Per 6 Java
/*
 * CalculatePi.java
 * Program #5
 * This approximates pi 2 different ways. It prints each result to 2, then 
 * 6 decimal places. Each result is compared to 3.141592 and the difference is 
 * printed. 
 * Working on: Using printf and the Format class to format the decimals. 
 * Declare and initializing cariables (D&I); a final constant. 
 * 
 * 
 * Pseudocode == the plan 
 * 
 * class header (public class ____.java)
 * 	D&I a final constant for PI
 * 
 * main header (public static void main(String[] args)
 * 	make an instance of the class 
 * 	use the instance to call firstApprox
 * 	use the instance to call secondApprox
 * 
 * firstApprox header 
 * 	declare approx1 as a float
 * 	initialize approx1 to 0.0f
 * 	D&I diff1, set to -100.0f
 * 
 * 	set approx1 to 22/7
 * 	set diff1 to difference between approx1 and PI
 * 	print 3 blank lines, then the intro sent using PI
 * 	print approx1 to 2 decimal places, then 6, then diff1 to 6 places
 * 
 *  * secondApprox header 
 * 	D&I approx2 as a float, initialize it to 0.0f
 * 	D&I diff2, set to -100.0f
 * 
 * 	set approx2 to ((4 + 100)*8 + 62000)/20000 
 *  -- wait why don't we just do 62832/20000 = 3.1416
 * 	set diff2 to difference between approx2 and PI
 * 	print approx2 to 2 decimal places, then 6, then diff2 to 6 places
*/

public class CalculatePi
{
	final float PI = 3.141592f ; 	//this is a final constant
	
	public static void main(String[] args)
	{
		System.out.println("\n\n\n"); //3 lines to separate to start printing
		System.out.println("\tNoah Guan - CalculatePi.java\n"); //my header for this code
		CalculatePi calcPi = new CalculatePi();
		calcPi.firstApprox(); //calls 1st method
		calcPi.secondApprox(); //calls 2nd method
		calcPi.thirdApprox(); //calls 3rd method
		System.out.println("\n\n\n");
	}
	public void firstApprox()
	{
		float approx1; //declare approx1
		approx1 = 0.0f; //initialize approx1
		float diff1 = -100.0f; //D&I diff1
		approx1 = 22/7f; //initialize diff1
		diff1 = PI - approx1; //subtract approx1 from PI
		//printing first approximation using printf
		System.out.printf("I am approximating pi (a bit more than %f). \n" + 
			"\n", PI); 
		System.out.printf("The calculation 22/7 yields %-4.2f or more " + 
			"specifically %-8.6f shown to 6 places. \n", approx1, approx1);
		System.out.printf("It is %-9.6f different from %-8.6f. \n\n", diff1, PI);
		
		/*
		my old code: 
		System.out.printf("This is the first approximation to 2 decimal... 
		//...points: %-5.2f\nThis is the second approximation to 6 decimal points: %-5.6f\nThis is the difference between the 1st approximation and our static PI value to 6 decimal points: %-5.6f\n\n", approx1, approx1, diff1); 
		//print 3 lines; 1st one as approx1 to 2 decimal places, 
		//2nd one as approx1 to 6 decimal places, 
		//3rd one as diff1 to 6 decimal places
		*/
	}
	
	public void secondApprox() 
	{
		float approx2 = 0.0f; //D&I approx2
		float diff2 = -100.0f;	//D&I diff2
		approx2 = ((4f + 100)*8 + 62000)/20000; //initialize approx2 to ...
		//...expected value
		diff2 = PI - approx2; //initialize diff2 to expected value
		//printing second approximation with Format except for final ...
		//...line, where I give more details on inner workings/what's going on
		System.out.println("The calculation (4+100) x 8 + 62000 all " + 
			"divided by 20000 yields " + Format.left(approx2, 4, 2) + 
			" or more specifically " + Format.left(approx2, 8, 6) + 
			" shown to 6 places. "); //use Format to print first sentence.
		System.out.println("It is " + Format.left(diff2, 9, 6) + 
			" different from " + Format.left(PI, 8, 6) + "."); //use Format ...
			//to print second line. 
		System.out.println("\t(This was printed with Format; Format " + 
			"gives an error when used this way (bug in Format's code).) "); 
			//describing Format's bug 
		System.out.printf("It is %-9.6f different from %8.6f. \n", diff2, PI); 
			//using printf to print first sentence
		System.out.println("\t(This was printed correctly with printf.) \n"); 
			//explaning further Format's bug and why I had to use printf
		/* 
		my old code: 
		System.out.println("This is the difference between the second approximation and our static PI value to 6 decimal points: " + Format.left(diff2, 5, 6));
		System.out.println("This is the second approximation to 6 decimal points: " + Format.left()
		System.out.println("This is the first approximation to 2 decimal points: " + Format.left()
		
		//test: 
		System.out.println(diff2);
		*/
	}
	public void thirdApprox()
	{
		float approx3 = 0.0f; //D&I approx3
		float diff3 = -100.0f;	//D&I diff3
		approx3 = 104348f/33215; //initialize approx3 to expected value
		diff3 = PI - approx3; //initialize diff3 to expected value
		//using printf for ChatGPT's approximation 
		System.out.printf("Another more accurate representation of PI" + 
			"is 104348/33215, which equates to around %-11.9f. \n", approx3);
		System.out.println("ChatGPT gave me this number when I asked for it" + 
			"(I asked Mr. Yu first, of course).");
		System.out.printf("This approximation is about %-9.6f different from " + 
			"our initial PI value -- however, it is more accurate. \n", diff3);
	}
}


