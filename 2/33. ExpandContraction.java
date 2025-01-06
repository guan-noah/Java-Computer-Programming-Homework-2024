//ExpandContraction.java
/* Noah Guan
 * 01-06-2025
 * Per. 6 Java w/ Mr. Yu
 * ExpandContraction.java
 * Program #33
 * 
 * Testing Plan: 
don't = do not
shouldn't = should not
didn't = did not
can't = can not
couldn't = could not
hadn't = had not
won't = will not
shant = shall not
 * 
// blank lines
What contraction would you like to expand? 
don't 
The expanded form of don't is do not. 
Would you like to expand another contraction? Type quit to stop. 
Yes 
What contraction would you like to expand? 
shant 
The expanded form of shant is shall not. 
Would you like to expand another contraction? Type quit to stop. 
Quit
 * 
// blank lines 
What contraction would you like to expand?
can't
The expanded form of can't is can not. 
Would you like to expand another contraction? Type quit to stop
noew
What contraction would you like to expand?
shouldn't can't should 
The expanded form of shouldn't is should not. 
Would you like to expand another contraction? Type quit to stop
qUit
 */
import java.util.Scanner;
public class ExpandContraction
{
	private String contracted; 
	private String expanded; 
	private Scanner kb;
	private boolean inputFormatCorrect;
	public ExpandContraction( ) //this is the constructor
	{
		//d&i field variables 
		contracted = "";
		expanded = "";
		kb = new Scanner(System.in);
		inputFormatCorrect = true; 
			//initialize at true 
	}
	public static void main(String [] args)
	{
		ExpandContraction ec = new ExpandContraction(); 
		ec.expandIt();
	}
	public void expandIt ()
	{
		boolean keepPlaying = true;
			//d&i a new var keepPlaying, initialize to true bec. player 
				//will have to keep playing
		String userDecide = "";
			//d&i a new var userDecide
		System.out.println("\n\n\n");
		do
		{
			getInput(); 
			processString(); 
			printResult(); 
			System.out.println("Would you like to expand another contraction? Type quit to stop"); 
			userDecide = kb.nextLine();
			if ("quit".equalsIgnoreCase(userDecide))
				//if any upper/lower case combo of "quit" equals user input
				//also, ONLY accepts "quit", not even "no"
				//(Your program should repetitively accept user input and
				    //produce expanded versions until the user types "quit" 
				    //or "QUIT" or any other non-case sensitive form of "quit." A)
			{
				keepPlaying = false;
					//else, keepPlaying stays true 
			}
		}while(keepPlaying); 
		System.out.println("\n\n\n");
	}
	public void getInput ( )
	{ 
		System.out.println("What contraction would you like to expand?");
		contracted = kb.next();
		kb.nextLine();
			//clear the scanner
	}
	public void processString ( )
	{
		///the following code is a bit convoluted because of polymorphism purposes 
		//hard coding 
		if (contracted.equalsIgnoreCase("won't"))
		{
			expanded = "would not";
		}
		if (contracted.equalsIgnoreCase("can't"))
		{
		    expanded = "can not";
		}
		
		//taking apostrophe and expanding it 
		else if (contracted.indexOf('\'') == -1)
			//if the contracted input doesn't have an apostrophe (one case) 
			//has to have an else because otherwise, we would reinitialize the "can't" case which does have an apostrophe
		{
			//more hard coding
			if (contracted.equalsIgnoreCase("shant"))
			{
				expanded = "shall not";
			}
			else
			{
				inputFormatCorrect = false;
			}
		}
		else if (contracted.substring((contracted.length()-3), contracted.length()).equals("n't"))
			//the contracted input has to have an apostrophe at this point onwards;
			//the contracted input also would end in "n't"
			//the contracted input 
		{
			expanded = contracted.substring(0, contracted.length()-3) + " not";
				//takes the last 3 characters off ("n't") and adds " not". 
		}
		else
		{
			inputFormatCorrect = false;
		}
	}
	/* unused extra method 
	public void changeExpanded()
		//another method for reinitializing expanded based on contracted (hard coding)
	{
		if (contracted.equals(contractedIn)
		{
			expanded = expandedIn;
		}
	}
	*/
	public void printResult()
	{
		if (inputFormatCorrect)
		{
			System.out.println("The expanded form of " + contracted + 
				" is " + expanded + ". ");
		}
		else
			// !inputFormatCorrect
		{
			System.out.println("That is not a valid contraction. Please try again.");
		}
	}
}// closes class
