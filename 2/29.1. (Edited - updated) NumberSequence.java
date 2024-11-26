//Notes: This version has the most updated version but the most unstable code. If I was to keep working on it, I would use this because it has the x3 looping worked on.
//If I was to pull working code snippets from it, I would use the 29. NumberSequence.java code. 

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
 * progress: 
 * work on multiple rounds and errors (?)
 * work on decreasing field variables 
 * finish userQNS method 
 * work on second half of program 
 * work on try again 
 * control-f "if the user is close" for formatting example 
 * 
*/
import java.util.Scanner;
class Main 
{
	private int startNum, sequenceRule, timesPlayed;
		//declare field variables 
		//I was going to add a timesWon but it seemed too harsh + I didn't have enough field variables left 
	//private Scanner keyboard;
		//field scanner! :D
		//didn't get to it :( too many FVs 
	private boolean done;
	    //to see if the user's done with the whole program
	public Main()//initialize all field variables 
	{
		startNum = 0;
		sequenceRule = 0; //this is the increment/decrement 
		timesPlayed = 0;
		//keyboard = new Scanner(System.in);
		done = false;
		    //initialize to user's not done with program 
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
	    System.out.println("Times Played: " + timesPlayed);
	        //this is the reason why I needed another layer - I only want the result to show at the very, very end of the program
		System.out.println("\n\n\n");
			//prints the required 3 new lines 
	}
	public void run() //this method runs the program. 
	{
		prompt();
		    //prints prompt 
	    chances(0, 0);
	        //gives in 0 for chances and 0 for answer (will get reinitialized)
	        //within this, calls first half of the program (guessing the number)
	        //this program runs nextNumProblem, and calls sequenceProblem if ready 
		
		//keep these commented out for now until first part works 
		if (!done)
		{
			run();
			    //run the program again 
			timesPlayed++;
			    //increase the times played by 1 
		}
		//System.out.print("Alright. Thank you for playing this round of NumberSequence. Would you like to try again?\t")
	}
	public void chances(int userChances, int numAnswer)//this method is for the user's 3 chances
	{
	    boolean userCorrect;
	    if (userChances == 0)
	    {
	        numAnswer = getNumAnswer();
	        userCorrect = nextNumProblem(numAnswer);
	            //runs the first half of the program (about guessing the next number) 
	            //if it's the user's first time 
	    }
        else
            userCorrect = nextNumProblem(numAnswer);
                //if we need to keep the number answer 
            
	    userChances++;
	    
	    //if the user gets it correct on the first try,
	    if (userChances == 1 && userCorrect)
	    {
	        System.out.println("Wow! You got it correct on the first try! ");
	    }
	    else if (userChances == 2 && userCorrect)
	    {
	        System.out.println("Nice! You got the answer correct on the second try! ");
	    }
	    else if (userChances == 3 && userCorrect)
	    {
	        System.out.println("Whew. You got the answer correct on the last try. ");
	    }
	    if (userChances <= 3 || userCorrect)
	    {
	        //sequenceProblem(); 
                //continue on with the game
	    }
	    if(!userCorrect)
	    {
	        boolean numUserQuit = userQNS(numAnswer);
		    //QNS = QuitNumSection
		    if (numUserQuit)
		    {
		        System.out.println("Would you like to keep playing this round (guess the sequence pattern), or try again? ");
		    }
		    else if (!numUserQuit)
		    {
		        nextNumProblem(numAnswer);
		            //give them another chance -- user did NOT quit the user section 
		    }
	        chances(userChances, numAnswer);
	            //user failed and we need to restart 
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
	public int getNumAnswer()
	{
	    ///this is the first half of the nextNumProblem method; it generates and prints the sequence 
		int numAnswer = 0;
		numAnswer = generateAndPrintSequence();
		    //generates and prints sequence out, and also returns the answer (last item in sequence)
		return numAnswer;
	}
	public boolean nextNumProblem(int numAnswerIn)//method for nextNum section 
	{
		int userNum = 0;
		    //be careful -- if something goes wrong in reinitializing 
			//numAnswer and userNum, it will both be the same 
			//(interpreted as user was correct).
		
		///this is the second half of the method: 
		boolean userCorrect;
		userNum = getNextNum(); 
		    //get the next number from user 
		userCorrect = decideIfNumCorrect(numAnswerIn, userNum);
		    //decide if the user number inputted is correct 
		    //also prints out resulting decision from this method
		return userCorrect; 
	}
	public int generateAndPrintSequence()
	{
	    //1st half of method: generate sequence
		startNum = (int)(Math.random()*22 - 10);
			//this is 22 because it includes -10 to 0, 0 included
		sequenceRule = (int)(Math.random()*10 + 1);
		    //this is the 10 different types of increment allotted 
		
		//2nd half of method: print sequence 
		System.out.println("Your sequence is:");
		int numAnswerReturn = outputSequence(0);
		System.out.println("___");
		return numAnswerReturn;
	}
	public int outputSequence(int countIn)
	    //this method is just an additional recursion method, supplementary to printSequence()
	{
		if (countIn < 5)//print 5 numbers to start out 
		{
			System.out.println((sequenceRule*countIn+startNum) + ", ");
			    //print the current number
			return outputSequence(countIn+1);
			    //run the method again to print the next number 
		}
		else
		{
			System.out.println();
			int generateNumAnswer = sequenceRule*countIn+startNum;
			return generateNumAnswer;
		}
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
		    //initialized based on if user is correct or not 
		boolean decideUserQuit; 
		    //the userQuit variable for the decideIfNumCorrect method 
		String wrongAnswer = "Not quite! ";
		    //to use for 
		int decide = (int)(Math.random()*9+1);
		if (numAnswer == userNum)
		{
			userCorrect = true;
			
			//make different answers; no arrays *sob*
			String correct1 = "Well done!";
			String correct2 = "Great job!";
			String correct3 = "You did it!";
			String correct4 = "Fantastic work!";
			String correct5 = "Bravo!";
			String correct6 = "Amazing effort!";
			String correct7 = "You nailed it!";
			String correct8 = "Congrats!";
			String correct9 = "Way to go!";
			String correct10 = "Impressive work!";
			String correct11 = "Correct! Success!";
			
			//send it into the checker method (even more painful because no arrays)
			checkAnswerChoices(decide, 1, correct1);
			    //1st parameter sent in: answer reference, 2nd:  checking variable, assigned to 3rd: actual output reference
			checkAnswerChoices(decide, 2, correct2);
			checkAnswerChoices(decide, 3, correct3);
			checkAnswerChoices(decide, 4, correct4);
			checkAnswerChoices(decide, 5, correct5);
			checkAnswerChoices(decide, 6, correct6);
			checkAnswerChoices(decide, 7, correct7);
			checkAnswerChoices(decide, 8, correct8);
			checkAnswerChoices(decide, 9, correct9);
			checkAnswerChoices(decide, 10, correct10);
			checkAnswerChoices(decide, 11, correct11);
		}
		else if (Math.abs(numAnswer - userNum) < sequenceRule)
			//if the user answer is near the generated answer (max: 
			//increment distance away); basically, if the user is close 
		{
				//generate a number that will correspond to an encouraging
				//message
			
			//make different answers (same logic)
			String user = "You're ";
			    //this is because I'm lazy and don't want to keep repeating it
			String answerNear1 = wrongAnswer + user + "nearly there!";
			String answerNear2 = wrongAnswer + user + "right there!";
			String answerNear3 = wrongAnswer + user + "almost there!";
			String answerNear4 = wrongAnswer + user + "right on the edge of it!";
			String answerNear5 = wrongAnswer + "Just a little more to go!";
			String answerNear6 = user + "so close!";
			String answerNear7 = user + "so near!";
			String answerNear8 = user + "within reach!";
			String answerNear9 = "Just a step away!";
			String answerNear10 = "Almost got it!";
			String answerNear11 = "On the brink of it!";
            
			//check answer for 1-9 
			//for (int i = 1, i < 12, i++)
			//{
			checkAnswerChoices(decide, 1, answerNear1);
			checkAnswerChoices(decide, 2, answerNear2);
			checkAnswerChoices(decide, 3, answerNear3);
			checkAnswerChoices(decide, 4, answerNear4);
			checkAnswerChoices(decide, 5, answerNear5);
			checkAnswerChoices(decide, 6, answerNear6);
			checkAnswerChoices(decide, 7, answerNear7);
			checkAnswerChoices(decide, 8, answerNear8);
			checkAnswerChoices(decide, 9, answerNear9);
			//}
			userCorrect = false;
		}
		else 
		{
			System.out.println(wrongAnswer + "\n");
			userCorrect = false;
		}
		return userCorrect;
	}
	/*
	public void loopMethod(int loopCount)
	{
	    if (loopCount < 12)
	    {
	        //String[] answerNear = {"You're nearly there!", "", ...};
	        checkAnswerChoices(decideAnswerNear, loopCount, array[0]);
	            //this would work if it was an array *sob*
	            //eyes bleeding right now 
	        loopCount++;
	    }
	}
	*/
	public void checkAnswerChoices(int decideAnswerNearIn, int checkNumIn, String answerResponseIn)
	    //this method is a tiny complementary one to decideIfNumCorrect();
	{
		if (decideAnswerNearIn == checkNumIn) 
			//if the answer reference equals the assigned number,
		{
			System.out.println(answerResponseIn + "\n");
				//print response entered
		}
	}
	//leave some space for organization 
	
	
	public boolean userQNS(int numAnswerIn)//QNS = QuitNumSection
	{
	    Scanner keyboard = new Scanner(System.in);
		boolean userQNS = false;
		System.out.print("Would you like to give up this question for the "
			+ "answer to the number? [Yes/No]\t");
		String userQNSIn = keyboard.next();
		if (userQNSIn.equalsIgnoreCase("yes"))
		{
		    userQNS = true;
		    System.out.println("Alright. The answer was " + numAnswerIn + ". \nWould you like to keep playing this round (to guess the sequence pattern)? [Yes/No]\t");
		    userQNSIn = keyboard.next();
		        //reuse the same variable name but reinitialize it to the answer to the second question 
		    if (userQNSIn.equalsIgnoreCase("yes"))
		    {
		        //sequenceProblem();
		    }
		    else if (userQNSIn.equalsIgnoreCase("no"))
		    {
		        System.out.println("\nWould you like to play again? [Yes/No]\t");
		        userQNSIn = keyboard.next();
		        if (userQNSIn.equalsIgnoreCase("yes"))
		        {
		            //start fresh 
		            startNum = 0;
		            sequenceRule = 0;
		            done = false;
		            chances(0, 0);
		                //rerun the chances with 0 tries and answer = 0 (will get reinitialized)
                    timesPlayed++;
		        }
		        else if (!userQNSIn.equalsIgnoreCase("no"))
		        {
		            //notify user and reprompt everything 
		            System.out.println("That response does not fit the prompt. Please try again. ");
		            userQNS = userQNS(numAnswerIn);
		        }
		    }
		    else
		    {
		        //notify user and reprompt everything 
		        System.out.println("That response does not fit the prompt. Please try again. ");
		        userQNS = userQNS(numAnswerIn);
		    }
		}
		else if (!userQNSIn.equalsIgnoreCase("no"))
		{
		    //notify user and reprompt everything 
		    System.out.println("That response does not fit the prompt. Please try again. ");
		    userQNS = userQNS(numAnswerIn);
		        //give them another chance to guess 
		}
		else
		{
		    //userQNS remains false. (user did not quit the number section)
		}
		return userQNS;
		    //user quits the guess the number section. 
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
}
/* Result1: 
Welcome to NumberSequence.java! 

This program will print out a number sequence; you will have to find the number that comes next (the number in the underscore. 
You will get at most 3 tries to guess the next number; after that, you will get one try to guess the rule for the sequence, whether you guessed the first number or not. 
Your sequence is:
10, 
11, 
12, 
13, 
14, 

___
What's the next number?	16
Not quite! 

Would you like to give up this question for the answer to the number? [Yes/No]	no
What's the next number?	14
Not quite! 

What's the next number?	15
Congrats!

Nice! You got the answer correct on the second try! 
Welcome to NumberSequence.java! 

This program will print out a number sequence; you will have to find the number that comes next (the number in the underscore. 
You will get at most 3 tries to guess the next number; after that, you will get one try to guess the rule for the sequence, whether you guessed the first number or not. 
Your sequence is:
-4, 
1, 
6, 
11, 
16, 

___
What's the next number?	
*/

/*Result2: 




Welcome to NumberSequence.java! 

This program will print out a number sequence; you will have to find the number that comes next (the number in the underscore. 
You will get at most 3 tries to guess the next number; after that, you will get one try to guess the rule for the sequence, whether you guessed the first number or not. 
Your sequence is:
4, 
11, 
18, 
25, 
32, 

___
What's the next number?	38
Just a step away!

Would you like to give up this question for the answer to the number? [Yes/No]	yes
Alright. The answer was 39. 
Would you like to keep playing this round (to guess the sequence pattern)? [Yes/No]	
yes
Would you like to keep playing this round (guess the sequence pattern), or try again? 
What's the next number?	39
Bravo!

Nice! You got the answer correct on the second try! 
Welcome to NumberSequence.java! 

This program will print out a number sequence; you will have to find the number that comes next (the number in the underscore. 
You will get at most 3 tries to guess the next number; after that, you will get one try to guess the rule for the sequence, whether you guessed the first number or not. 
Your sequence is:
-7, 
-5, 
-3, 
-1, 
1, 

___
What's the next number?	4
Just a step away!

Would you like to give up this question for the answer to the number? [Yes/No]	yes
Alright. The answer was 3. 
Would you like to keep playing this round (to guess the sequence pattern)? [Yes/No]	
yes
Would you like to keep playing this round (guess the sequence pattern), or try again? 
What's the next number?	2
You're so close!

Would you like to give up this question for the answer to the number? [Yes/No]	no
What's the next number?	4
Not quite! You're right there!

What's the next number?	9
Not quite! 

Would you like to give up this question for the answer to the number? [Yes/No]	yes
Alright. The answer was 3. 
Would you like to keep playing this round (to guess the sequence pattern)? [Yes/No]	
no

Would you like to play again? [Yes/No]	
no
Would you like to keep playing this round (guess the sequence pattern), or try again? 
What's the next number?	4
You're so near!

Would you like to give up this question for the answer to the number? [Yes/No]	no
What's the next number?	3
You did it!

What's the next number?	3
Well done!

Welcome to NumberSequence.java! 

This program will print out a number sequence; you will have to find the number that comes next (the number in the underscore. 
You will get at most 3 tries to guess the next number; after that, you will get one try to guess the rule for the sequence, whether you guessed the first number or not. 
Your sequence is:
10, 
20, 
30, 
40, 
50, 

___
What's the next number?	
*/
