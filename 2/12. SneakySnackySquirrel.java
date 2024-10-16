/* Remember your heading
SneakySnackySquirrel.java
Noah Guan
P.6 Java w/ Mr. Yu
Program #12
10/9/2024
SneakySnackySquirrel game handout in class; uses Player class to store 
	player information; feeds Player class the numbers and decides if game continues. 
*/

import java.util.Scanner;	//import library so have access to Scanner

public class SneakySnackySquirrel	// write header
{
	private Player holly;
	public static void main(String[] args)			// write main header 
	{
		 SneakySnackySquirrel squirrel = new SneakySnackySquirrel(); //create an instance of the class SneakySnackySquirrel use it to call run
		 squirrel.run();
	}
	
	public void run()
	{
		boolean again = true; //declare a boolean called again and initialize it with true.
		int rand = 56;   // declare the variable called rand and initialize it to 56.  You 
       						  // may have to read later to determine what data type to use.
		holly = new Player("Holly");   //call constructor for the Player class; 
							      					// construct the Player called holly
		while(again)  // this repeats the next four lines until you 
		{							 // win.  You do not need to add  anything to this line.
			rand = spin();   //call the method called spin and accept the result
			decide(rand);  //call the decide method, passing the random number as the parameter
			holly.printScore();  //call printScore() in the Player class
			again = playAgain();  //call playAgain and accept returned value (local boolean already declared)
		}
	}

	public boolean playAgain()	//this method lets us control 
	{												//playing again, creating user-interaction
		Scanner reader = new Scanner(System.in);  //declare and instantiate  
										      // a Scanner called reader	
		String typed = new String ("");	
		if (holly.getAcorn() < 5)  // this is so when the game ends, it does not ask to spin again.
		{
			System.out.println("Press return to spin again. ");
			typed = reader.nextLine();  
			return true;  //return so the game can continue
		
		}
		else								  
			return false;	// return so the game stops
	}
	
	/* spin() method generates random values from 1 to 8 and returns that value.  */
	public byte spin()  
	{
		byte spun = -6;
		// generate a random number btwn 1 and 8 inclusive
		spun = (byte)(Math.random()*8+1);  
		return spun;  // designate what will be sent back to  
											    // the method call that called spin()
	}

	/* This method is for deciding how to change the acorn count based on the spin. 
	 *  Finish the header.  The body is complete. */
	public void decide(int input)	
	{				
		System.out.println(holly.getPlayerName() + " spun " + input);
		if(input >= 1 && input <= 5)
			holly.setAcorn( 1 );  // adds 1 pt to acorn in Player class
		else if (input == 6)
			holly.setAcorn( -holly.getAcorn() );	// subtracts all points to make = 0
		else if ( input == 7 && (holly.getAcorn() > 0) )
			holly.setAcorn( -1 );	// subtracts 1 pt from acorn in Player class
		else if (input == 8)
			holly.setAcorn( 2 );	// adds 2 pts to acorn in Player class
	}	//close decide()
}	//close first class

