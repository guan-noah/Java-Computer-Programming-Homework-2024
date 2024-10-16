// Stave.java	complete the heading!
/*
Noah Guan 
10-11-2024
P.6 Java w/ Mr. Yu 
Program #14 
Stave.java
*/


/// When you are done with the code, clean up any extra blank lines
import java.util.Scanner;

/* Declare all field variables here (6 int variables).  Be sure they are private. */
public class Stave 
{
	private int roll1, roll2, roll3, roll4, runningScore, score;
	public Stave () 
	{  
													// initialize all field variables
		roll1 = 0;
		roll2 = 0;
		roll3 = 0;
		roll4 = 0;
		runningScore = 0;
		score = 0;
	} 
	
	public static void main (String [] args) // main is complete 
	{ 
		Stave st = new Stave (); 
		st.runGame();
	}
	
	public void runGame()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("\tWelcome to Stave! \nThis game is named " + 
			"after any 2-sided object (called a stave); in this game, " + 
			"you will roll 4 staves (choices either '#' or '$'). \nIf " + 
			"you roll exactly 2 of each kind, you gain 1 point. \nIf you" + 
			" roll 4 of either kind, you gain 2 points. \nOtherwise, you " + 
			"gain 0 points.\n\n");
		for (int i = 0; i < 3; i++)	// this is a loop that runs 3 times, so it calls
		{								// all three methods in order 3 times.
			System.out.print("To roll the Staves, press Enter. ");
			keyboard.nextLine();
			playGame();
			scoreGame();
			updateScore();
		}
		System.out.println("\n\n\n");
	}
	
	public void playGame () 
	{
		Roll rollem = new Roll();
		ShowStave show = new ShowStave(); 
		roll1 = rollem.rollStave(); 
		show.displayStave(roll1); 
		
		roll2 = rollem.rollStave(); 
		show.displayStave(roll2); 
		
		roll3 = rollem.rollStave(); 
		show.displayStave(roll3); 
		
		roll4 = rollem.rollStave(); 
		show.displayStave(roll4); 
		
	} 
	
/* This method is complete.  scoreGame() builds a String of all 4 rolls as characters, then looks at all 4 characters, counting how many '$' there are.  If four or no '$' score increases by 2.  If two '$' score increases by 1.*/
	public void scoreGame() 
	{ 
		String rolls = new String("");
		rolls = "" + (char)roll1 + (char)roll2 + (char)roll3 + (char)roll4; 
		int count = 0;
		score = 0;		
		for (int i = 0; i < 4; i++) 
		{  
			if (rolls.charAt(i) == '$')
				count++;
		} 
		
		if (count == 4 || count == 0) 
			score+=2; 
		else if (count == 2) 
			score++; 
	}
	
	public void updateScore () 
	{
		runningScore += score; //creates runningScore def 
		System.out.println("This round earned a score of: " + score);
		System.out.println("Your total score is: " + runningScore + "\n");
	} 

} // end class Stave
//Be sure other two classes are written as well (both are very short).
// Neither class uses “static” in their methods – leave it out.  :)
