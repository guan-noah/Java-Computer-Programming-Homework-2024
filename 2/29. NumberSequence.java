
//NumberSequence.java
/* Noah Guan
 * 11-15-2024
 * Per. 6 Java w/ Mr. Yu
 * NumberSequence.java
 * Program #29 
 * 
 * Output: 
 * 		input example: batman 7. 
 * 		It was not batman 7. It was add 2. 
 * plus, add, increase by, increment by, + 
 * 
 * Pseudocode Notes: 
 * Add pizazz for the congrats  
 * 		Wow! You got it on the first try! 
 * 		Great Job! 
 * 		Awesome! 
 * 		Superb! 
 * 		Nice! 
 * 		Amazing work! 
 * 
 * Working on: Completing 
 * 
 * Testing: 
 * 
 * 
*/
import java.util.Scanner;
class Main 
{
	private int startNum, sequenceRule;
		//declare field variables 
	//private Scanner keyboard;
		//field scanner! :D
	private boolean done;
	public Main()//initialize all field variables 
	{
		startNum = 0;
		sequenceRule = 0; //this is the increment/decrement 
		//keyboard = new Scanner(System.in);
		done = false;
	}
	public static void main(String[] args)
	{
		Main ns = new Main();
			//make a new instance of numbersequence
		ns.newLines();
			//call newlines (required) 
		ns.run();
			//call play to run the code 
		ns.newLines();
			//call newlines (required) 
	}
	public void newLines()
	{
		System.out.println("\n\n\n");
			//prints the required 3 new lines 
	}
	public void run() //this method runs the program. 
	{
		prompt();
		    //prints prompt 
		nextNumProblem();
		    //runs the first half of the program (about guessing the next number)
		//sequenceProblem(); 
		    //runs the next half of the program (about guessing the whole sequence)
		//keep these commented out for now until first part works 
		if (!done)
		{
			run();
		}
		boolean runUserQuit = userQuit();
		if (runUserQuit == true)
		    System.out.println("Would you like to try again? ");
	}
	public void prompt()
	{
		System.out.println("Welcome to NumberSequence.java! ");
		    //welcome message
		System.out.println("\nThis program will print out a number sequence;"
			+ " you will have to find the number that comes next (the number"
			+ " in the underscore. \nYou will get at most 3 tries to guess the next"
			+ " number; after that, you will get one try to guess the rule"
			+ " for the sequence, whether you guessed the first number or not. ");
			//explains the game 
		
	}
	public void nextNumProblem()//method for nextNum section 
	{
		int numAnswer = 0;
		int userNum = 0;
		boolean userCorrect;
			//be careful -- if something goes wrong in reinitializing 
			//numAnswer and userNum, it will both be the same 
			//(interpreted as user was correct).
		generateSequence();
		    //generates data that sequence needs with field variables (no need for parameter input/return storage)
		numAnswer = printSequence();
		    //prints the generated sequence out and also returns the answer (last item in sequence )
		userNum = getNextNum(); 
		    //get the next number from user 
		userCorrect = decideIfNumCorrect(numAnswer, userNum);
		    //decide if the user number inputted is correct 
	    
	}
	public void generateSequence()
	{
		startNum = (int)(Math.random()*22 - 10);
			//this is 22 because it includes -10 to 0, 0 included, 
		sequenceRule = (int)(Math.random()*10 + 1);
	}
	public int printSequence()
	{
		System.out.println("Your sequence is:");
		int numAnswerReturn = outputSequence(0);
		System.out.println("___");
		return numAnswerReturn;
	}
	public int outputSequence(int countIn)
	    //this method is just an additional recursion method, supplementary to printSequence()
	{
		int generateNumAnswer;
		if (countIn < 5)
		{
			System.out.println((sequenceRule*countIn+startNum) + ", ");
			outputSequence(countIn+1);
			generateNumAnswer = 0;
			    //might be problematic as the generateNumAnswer is actually deep in further recursions; how to retrieve it is difficult 
		}
		else
		{
			System.out.println();
			generateNumAnswer = sequenceRule*countIn+startNum;
		}
		return generateNumAnswer;
	}
	public int getNextNum()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("What's the next number?\t");
		int userNum = keyboard.nextInt();
		    //ooh we shouldn't be making another new one; we should just be passing the same one through
		keyboard.nextLine();
		return userNum;
	}
	public boolean decideIfNumCorrect(int numAnswer, int userNum)
	{
		boolean userCorrect;
		String wrongAnswer = "Not quite! ";
		if (numAnswer == userNum)
		{
			userCorrect = true;
		}
		else if (Math.abs(numAnswer - userNum) < sequenceRule)
			//if the user answer is near the generated answer (max: 
			//increment distance away)
		{
		    int decideAnswerNear = (int)(Math.random()*9+1);
				//generate a number that will correspond to an encouraging
				//message  
			//make different answers 
			String answerNear1 = wrongAnswer + "You're nearly there!";
			String answerNear2 = wrongAnswer + "You're right there!";
			String answerNear3 = wrongAnswer + "You're almost there!";
			String answerNear4 = wrongAnswer + "You're right on the edge of it!";
			String answerNear5 = wrongAnswer + "Just a little more to go!";
			String answerNear6 = "You're so close!";
			String answerNear7 = "Just a step away!";
			String answerNear8 = "You're within reach!";
			String answerNear9 = "Almost got it!";
			
			//check answer for 1-9 
			checkAnswerNear(decideAnswerNear, 1, answerNear1);
			checkAnswerNear(decideAnswerNear, 2, answerNear2);
			checkAnswerNear(decideAnswerNear, 3, answerNear3);
			checkAnswerNear(decideAnswerNear, 4, answerNear4);
			checkAnswerNear(decideAnswerNear, 5, answerNear5);
			checkAnswerNear(decideAnswerNear, 6, answerNear6);
			checkAnswerNear(decideAnswerNear, 7, answerNear7);
			checkAnswerNear(decideAnswerNear, 8, answerNear8);
			checkAnswerNear(decideAnswerNear, 9, answerNear9);
			userCorrect = false;
		}
		else 
		{
			System.out.println(wrongAnswer);
			userCorrect = false;
		}
		return userCorrect;
	}
	public void checkAnswerNear(int decideAnswerNearIn, int checkNumIn, String answerResponseIn)
	    //this method is a tiny complementary one to decideIfNumCorrect();
	{
		if (decideAnswerNearIn == checkNumIn) 
			//if the answer reference equals the assigned number,
		{
			System.out.println(answerResponseIn);
				//print response entered
		}
	}
	
	
	
	
	
	
	
	
	
	
	//public void printOutput(int numAnswer, int userNum)
	//{
	//	System.out.println("");
	//}
	public double getRule()
	{
		String userResponse1 = "";
		    //this will be the first word the user inputs
		String userResponse2 = "";
		    //this will be the second word the user inputs
		String userResponse = "";
		    //this will be the combination of 1st and 2nd part of the user response 
		double userSNR = 0.0;
            //we already have a "userNum" variable but this one is totally different; this one is just to return the "user's sequence (pattern) number response", shortened to userSNR. 
            ///caution: userSNR has to be initialized to something; if the pattern is +0 something might be ambiguous
		
		Scanner keyboard = new Scanner(System.in);
		    //new instance of Scanner 
		System.out.print("How do you go from one number to the next?\t");
		    //prompt user
		userResponse1 = keyboard.next();
		//plus, add, increase by, increment by, + 
		//doesn't have to check for the single word keywords because it checks for everything later 
		if (userResponse.equalsIgnoreCase("increase")|| 
			userResponse.equalsIgnoreCase("increment"))
			//checks for the 2-word keywords and if there are, takes the second word 
		{
			userResponse2 = keyboard.next();
		}
		userResponse = userResponse1 + userResponse2;
		    //adds first word to second word; this will not throw an error if there isn't a second word because the second word is initialized to an empty string 
		if (userResponse.equalsIgnoreCase("increase by") || userResponse.equalsIgnoreCase("increment by") || userResponse.equalsIgnoreCase("add") || userResponse.equalsIgnoreCase("plus"))
		    //the only 4 responses that work 
		{
		    userSNR = keyboard.nextDouble();
		        //accept the user input 
		}
		else
		{
		    System.out.println("Invalid response. Please try again. ");
		    getRule();
		}
		return userSNR; 
		    //returns only the double value 
	}
	public boolean userQuit()
	{
	    Scanner keyboard = new Scanner(System.in);
		boolean userQuit = false;
		System.out.print("Would you like to give up this question for the "
			+ "answer to the number? Yes/No\t");
		String userQuitIn = keyboard.next();
		if (userQuitIn.equalsIgnoreCase("yes"))
		{
		    userQuit = true;
		    done = true;
		    System.out.println("Alright. Thank you for playing this round of NumberSequence. Would you like to try again?\t");

		}
		else if (!userQuitIn.equalsIgnoreCase("no"))
		{
		    System.out.println("That response does not fit the prompt. Please try again. ");
		    userQuit();
		}
		else
		{
		    
		}	//userQuit remains false. 
		return userQuit;
	}
}
