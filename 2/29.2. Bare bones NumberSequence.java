//NumberSequence.java
/* Noah Guan
 * 11-15-2024
 * Per. 6 Java w/ Mr. Yu
 * NumberSequence.java
 * Program #29 
 * comment/indent guide: if comments are on the same indent (no indent) before a chunk of code, it's explaining the chunk of code. 
 *  if comments are on the same line as the code, they're explaining that line. if I need to continue something on the next line, I will indent it 1 tab from the original line. 
 * 
 * Output: 
 * 		input example: batman 7. 
 * 		It was not batman 7. It was add 2. 
 * plus, add, increase by, increment by, + 
 * 
 * Pseudocode Notes: 
 * 
 * Working on: Completing the darn code. 
 * 
 * Testing: 
 * 
 * progress: 
 * work on multiple rounds and errors (?)
 * finish userQNS method 
 * work on second half of program 
 * work on try again 
 * control-f "if the user is close" for formatting example 
 * how to get sequenceRule into decideIfNumCorrect and sequenceProblem if it's too deep in recursion (basically, try not to use any field variables)
 *      worst comes to worst, I use at least 1 field variable and try to work on it later (w/ Mr. Yu?)
 * 
 * took out: 
 * user quit number section 
 * user encouraging messages 
*/
import java.util.Scanner;
class Main 
{
	//add field variables when needed 
	
	public Main()//initialize all field variables 
	{
		 
	}
	public static void main(String[] args)
	{
		Main ns = new Main();
			//make a new instance of numbersequence
		ns.play();
	}
	public void play()
	{
	    System.out.println("\n\n\n");
			//prints the required 3 new lines 
		run();
			//call play to run the code 
		System.out.println("\n\n\n");
			//prints the required 3 new lines 
	}
	public void run() //this method runs the program. 
	{
		Scanner keyboard = new Scanner(System.in);
		prompt();
		    //prints prompt 
	    tries(0, getSequence());
	        //gives in 0 for tries and the number answer for the first try 
	        //this "tries" method is for guessing the number 
		//section on user playing again! :D
		//keep these commented out for now until first part works
		/*
		boolean done = false;
		    //to see if the user's done with the whole program; initialize to user's not done with program
		if (!done)
		{
			run();
			    //run the program again 
			//timesPlayed++;
			    //increase the times played by 1 
		}
		else if (done)
		{
		    System.out.print("Would you like to play again? [Yes/No] \t");
		    String userPlayAgain = keyboard.next();
		    if (userPlayAgain.equalsIgnoreCase("yes"))
		    {
		        run();
		    }
		    else if (userPlayAgain.equalsIgnoreCase("no"))
		    {
		        System.out.println("Alright. Thank you for playing NumberSequence! ");
		    }
		    else //user didn't follow directions 
		    {
		        System.out.println("Invalid answer response. If you would like to play again, please restart the program.");
		    }
		}
		*/
	}
	public void tries(int userTriesIn, int numAnswerIn)
	    //this method is for the user's 3 chances
        //within this, calls first half of the program (guessing the number)
        //this program runs nextNumProblem, and calls sequenceProblem if ready 
	{
        boolean userCorrect;
	    userCorrect = nextNumProblem(numAnswerIn);
                //if we need to keep the number answer 
	    
	    //if the user gets it correct on the first try,
	    if (userTriesIn <= 3 || userCorrect)
	    {
        	if (userCorrect)
    	    {
        	    if (userTriesIn == 1)
        	    {
        	        System.out.println("Wow! You got it correct on the first try! ");
        	    }
        	    else if (userTriesIn == 2)
        	    {
        	        System.out.println("Nice! You got the answer correct on the second try! ");
        	    }
        	    else if (userTriesIn == 3)
        	    {
        	        System.out.println("Whew. You got the answer correct on the last try. ");
        	    }
    	    }
	        //sequenceProblem(); 
                //continue on with the game
	    }
	    if(!userCorrect)
	    {
	        tries(userTriesIn++, numAnswerIn);
	            //user didn't get this one correct 
	    }
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
	
	public boolean nextNumProblem(int numAnswerIn)//method for nextNum section 
	{
		int userNum = getNextNum(); 
		    //get the next number from user
		boolean userCorrect = decideIfNumCorrect(numAnswerIn, userNum);
		    //decide if the user number inputted is correct 
		    //also prints out resulting decision from this method
		return userCorrect; 
	}
	public int getNextNum()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("What's the next number?\t");
		int getUserNum = keyboard.nextInt();
		return getUserNum;
	}
	public int getSequence()
	    //while generating and printing sequence, this method also returns number answer 
	{
	    //1st half of method: generate sequence
		int startNum = (int)(Math.random()*22 - 10);
			//this is 22 because it includes -10 to 0, 0 included
		int sequenceRule = (int)(Math.random()*10 + 1);
		    //this is the 10 different types of increment allotted 
		/*
		String returnReason = "";
		if (returnReason.equals("return sequenceRule"))
		{
		    return sequenceRule;
		}
		else if (returnReason.equals("return numAnswer"))
		{
		    //continue on with the program 
		    //honestly if this worked we wouldn't need this
		}
		//another idea: return a generalReturn (String) with both; then use an if statement combined with String word pulls to decide which one to pull out; then, convert what you need to a data type (ex. if we get String 2 and want int 2: String 2 --> char 2 --> int 2+shiftNum, subtract shiftNum)
		*/
		//2nd half of method: print sequence, and numAnswer 
		System.out.println("Your sequence is:");
		int numAnswerReturn = outputSequence(0, startNum, sequenceRule);
		System.out.println("___");
		return numAnswerReturn;
	}
	public int outputSequence(int countIn, int sequenceRuleIn, int startNumIn)
	    //this method takes in the count (how many numbers outputted), the sequence rule generated, and the starting number 
	    //and returns the number answer 
	{
		if (countIn < 5)//print 5 numbers to start out 
		{
			System.out.print((sequenceRuleIn*countIn+startNumIn) + ", ");
			    //print the current number
			return outputSequence(countIn+1, sequenceRuleIn, startNumIn);
			    //run the method again to print the next number 
		}
		else
		{
			System.out.println();
			int generateNumAnswer = sequenceRuleIn*countIn+startNumIn;
			return generateNumAnswer;
		}
	}
	public boolean decideIfNumCorrect(int numAnswerIn, int userNumIn)
	    //takes in the answer and the user input and returns if user correct (also prints it out to the screen)
	{
		boolean userCorrect;
		    //initialized based on if user is correct or not 
		if (numAnswerIn == userNumIn)
		{
		    userCorrect = true;
		    System.out.println("Correct! Well done. \n");
		}
		else if (Math.abs(numAnswerIn - userNumIn) < sequenceRule)
			//if the user answer is near the generated answer (max: 
			//increment distance away); basically, if the user is close 
		{
			System.out.println("Close! \n");
			userCorrect = false;
		}
		else 
		{
			System.out.println("Not quite! \n");
			userCorrect = false;
		}
		return userCorrect;
	}
	//leave some space for organization 
	
	
	
	
	
	
	
	
	public void sequenceProblem(int sequenceRuleIn)
	    //problem: how to get sequenceRule into this method 
	{
		double userRule = getRule();
		
	}
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
}
