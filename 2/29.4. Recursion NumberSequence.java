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
 * Pseudocode Notes: pseudocode uploaded as a picture to GitHub. 
 * 
 * Working on: Completing the code with recursion. 
 * 
 * Testing: 
1. Input: 41; add 8; noh; nope; no




Welcome to NumberSequence.java! 

This program will print out a number sequence; you will have to find the number that comes next (the number in the underscore. 
You will get at most 3 tries to guess the next number; after that, you will get one try to guess the rule for the sequence, whether you guessed the first number or not. 
Your sequence is:
1, 9, 17, 25, 33, ___
What's the next number?	41
Correct! Well done. 
Wow! You got it correct on the first try! 
How do you go from one number to the next?	add 8
Correct! Congratulations! The pattern was add 8. 
Would you like to play again? [Yes/No] 	noh
Invalid answer response.
Would you like to play again? [Yes/No] 	nope
Invalid answer response.
Would you like to play again? [Yes/No] 	no
Alright. Thank you for playing NumberSequence! 




2. Input: 0; 40; plus 8; no




Welcome to NumberSequence.java! 

This program will print out a number sequence; you will have to find the number that comes next (the number in the underscore. 
You will get at most 3 tries to guess the next number; after that, you will get one try to guess the rule for the sequence, whether you guessed the first number or not. 
Your sequence is:
0, 8, 16, 24, 32, ___
What's the next number?	0
Not quite! 
What's the next number?	40
Correct! Well done. 
Nice! You got the answer correct on the second try! 
How do you go from one number to the next?	plus 8
Correct! Congratulations! The pattern was plus 8. 
Would you like to play again? [Yes/No] 	no
Alright. Thank you for playing NumberSequence! 




3. Input: 45; 61; 53; increment by 9; no, I would not; no I would not
        //this showcases the "close" part of the program. 




Welcome to NumberSequence.java! 

This program will print out a number sequence; you will have to find the number that comes next (the number in the underscore. 
You will get at most 3 tries to guess the next number; after that, you will get one try to guess the rule for the sequence, whether you guessed the first number or not. 
Your sequence is:
8, 17, 26, 35, 44, ___
What's the next number?	45
Close! 
What's the next number?	61
Close! 
What's the next number?	53
Correct! Well done. 
Whew. You got the answer correct on the last try. 
How do you go from one number to the next?	increment by 9
Correct! Congratulations! The pattern was increment by 9. 
Would you like to play again? [Yes/No] 	no, I would not.
Invalid answer response.
Would you like to play again? [Yes/No] 	no I would not.
Alright. Thank you for playing NumberSequence! 




4. Input: 0; 0; 0; +10; no




Welcome to NumberSequence.java! 

This program will print out a number sequence; you will have to find the number that comes next (the number in the underscore. 
You will get at most 3 tries to guess the next number; after that, you will get one try to guess the rule for the sequence, whether you guessed the first number or not. 
Your sequence is:
9, 19, 29, 39, 49, ___
What's the next number?	0
Not quite! 
What's the next number?	0
Not quite! 
What's the next number?	0
Not quite! 
None of the guesses were correct. 
How do you go from one number to the next?	+10
Your input, '+10' was not accepted. 
Sorry, your answer was incorrect. The pattern was increment by 10
Would you like to play again? [Yes/No] 	no
Alright. Thank you for playing NumberSequence! 




5. Input: twenty-seven




Welcome to NumberSequence.java! 

This program will print out a number sequence; you will have to find the number that comes next (the number in the underscore. 
You will get at most 3 tries to guess the next number; after that, you will get one try to guess the rule for the sequence, whether you guessed the first number or not. 
Your sequence is:
3, 8, 13, 18, 23, ___
What's the next number?	twenty-seven
ERROR!
Exception in thread "main" java.util.InputMismatchException
	at java.base/java.util.Scanner.throwFor(Scanner.java:947)
	at java.base/java.util.Scanner.next(Scanner.java:1602)
	at java.base/java.util.Scanner.nextInt(Scanner.java:2267)
	at java.base/java.util.Scanner.nextInt(Scanner.java:2221)
	at Main.getNextNum(Main.java:271)
	at Main.nextNumProblem(Main.java:260)
	at Main.tries(Main.java:214)
	at Main.run(Main.java:167)
	at Main.play(Main.java:158)
	at Main.main(Main.java:152)

6. Input: -3
    //this is showcasing a bug where you can't have a negative answer or else the nextInt only catches the negative sign. 



Welcome to NumberSequence.java! 

This program will print out a number sequence; you will have to find the number that comes next (the number in the underscore. 
You will get at most 3 tries to guess the next number; after that, you will get one try to guess the rule for the sequence, whether you guessed the first number or not. 
Your sequence is:
-8, -7, -6, -5, -4, ___-3
What's the next number?	
ERROR!
Exception in thread "main" java.util.InputMismatchException
	at java.base/java.util.Scanner.throwFor(Scanner.java:947)
	at java.base/java.util.Scanner.next(Scanner.java:1602)
	at java.base/java.util.Scanner.nextInt(Scanner.java:2267)
	at java.base/java.util.Scanner.nextInt(Scanner.java:2221)
	at Main.getNextNum(Main.java:296)
	at Main.nextNumProblem(Main.java:285)
	at Main.tries(Main.java:239)
	at Main.run(Main.java:192)
	at Main.play(Main.java:183)
	at Main.main(Main.java:177)

6. SPECIAL CASE -- Input: 32; add 6; yes; 20; increase by four
    //special case because I ran the program more than once 




Welcome to NumberSequence.java! 

This program will print out a number sequence; you will have to find the number that comes next (the number in the underscore. 
You will get at most 3 tries to guess the next number; after that, you will get one try to guess the rule for the sequence, whether you guessed the first number or not. 
Your sequence is:
2, 8, 14, 20, 26, ___
What's the next number?	32
Correct! Well done. 
Wow! You got it correct on the first try! 
How do you go from one number to the next?	add 6
Correct! Congratulations! The pattern was add 6. 
Would you like to play again? [Yes/No] 	yes


Welcome to NumberSequence.java! 

This program will print out a number sequence; you will have to find the number that comes next (the number in the underscore. 
You will get at most 3 tries to guess the next number; after that, you will get one try to guess the rule for the sequence, whether you guessed the first number or not. 
Your sequence is:
0, 4, 8, 12, 16, ___
What's the next number?	20
Correct! Well done. 
Wow! You got it correct on the first try! 
How do you go from one number to the next? increase by four
ERROR!
Exception in thread "main" java.util.InputMismatchException
	at java.base/java.util.Scanner.throwFor(Scanner.java:947)
	at java.base/java.util.Scanner.next(Scanner.java:1602)
	at java.base/java.util.Scanner.nextDouble(Scanner.java:2573)
	at Main.getRule(Main.java:448)
	at Main.sequenceProblem(Main.java:393)
	at Main.tries(Main.java:304)
	at Main.run(Main.java:217)
	at Main.userPlayAgain(Main.java:234)
	at Main.run(Main.java:222)
	at Main.play(Main.java:208)
	at Main.main(Main.java:202)

//this program ran twice; I broke it by trying to answer a nextInt() with a string. 
 * 	
 * 
 * took out: 
 * user quit number section (QNS method)
 * user encouraging messages 
 * how many times the user played the game (timesPlayed variable)
*/
import java.util.Scanner;
class Main 
{
	//declare field variables
	private int sequenceRule;
	    //the incrementing rule (rule that determines how to get to the next number)
	private String userResponse;
	    //this will be the combination of 1st and 2nd part of the user response 
	
	public Main()//initialize field variables 
	{
        sequenceRule = 0;
        userResponse = "";
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
		prompt();
            //prints prompt 
		tries(1, getSequence());
            //gives in 1 for tries and the number answer for the first try 
            //this "tries" method is for guessing the number 
		//section on user playing again! :D
		//keep these commented out for now until first part works
		userPlayAgain();
	}
	public boolean userPlayAgain()
	{
        Scanner keyboard = new Scanner(System.in);
        boolean done = false;
            //to see if the user's done with the whole program; initialize to user's not done with program
		System.out.print("Would you like to play again? [Yes/No] \t");
		String userPlayAgain = keyboard.next();
		if (userPlayAgain.equalsIgnoreCase("yes"))
		{
            System.out.println("\n");
            run();
		}
		else if (userPlayAgain.equalsIgnoreCase("no"))
		{
            System.out.println("Alright. Thank you for playing NumberSequence! ");
            done = true;
		}
		else //user didn't follow directions 
		{
            System.out.println("Invalid answer response.");
            done = userPlayAgain();
        }
		return done;
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
    public void tries(int userTriesIn, int numAnswerIn)
        //this method is for the user's 3 chances; acts kind of like a do-while loop because the check is at the end
        //within this, calls first half of the program (guessing the number)
        //this program runs nextNumProblem, and calls sequenceProblem if ready 
	{
        boolean userCorrect;
		userCorrect = nextNumProblem(numAnswerIn);
                //we need to keep the number answer 
        
        if(!userCorrect && userTriesIn < 3)
            //if user didn't guess correctly and has more tries 
        {
            tries(++userTriesIn, numAnswerIn);
                //give another chance
        }
        else //user needs to continue on to the game; only 2 scenarios at this point: user succeeded or user failed. 
        {
            if (userTriesIn >= 3 && !userCorrect)
                //if user used up all the tries and didn't guess it correctly (user failed)
            {
                System.out.println("None of the guesses were correct. ");
            }
            else if (userTriesIn <= 3 || userCorrect)
                //if user used up the allotted amount of tries (or lower) or if user guessed it correctly (if user succeeded)
            {
                if (userCorrect)
                    //first option: user guessed correctly 
                {
                    if (userTriesIn == 1)
                        //if the user gets it correct on the first try,
                    {
                        System.out.println("Wow! You got it correct on the first try! ");
                    }
                    else if (userTriesIn == 2)
                        //if the user gets it correct on the second try,
                    {
                        System.out.println("Nice! You got the answer correct on the second try! ");
                    }
                    else if (userTriesIn == 3)
                        //if the user gets it correct on the third (and final) try,
                    {
                        System.out.println("Whew. You got the answer correct on the last try. ");
                    }
                }
            }
            sequenceProblem(); 
                //continue on with the game
		}
	}
	public boolean nextNumProblem(int numAnswerIn)
	    //method to run nextNum section 
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
		int startNum = (int)(Math.random()*21 - 10);
			//this is 21 because it includes -10 to 0, 0 included (-10 to 10)
		sequenceRule = (int)(Math.random()*10 + 1);
            //this is the 10 different types of increment allotted (1 to 10)
		
		//2nd half of method: print sequence, and numAnswer 
		System.out.println("Your sequence is:");
		int numAnswerReturn = outputSequence(0, startNum);
		System.out.println("___");
		return numAnswerReturn;
	}
	public int outputSequence(int countIn, int startNumIn)
        //this method takes in the count (how many numbers outputted), the sequence rule generated, and the starting number 
        //and returns the number answer 
	{
		if (countIn < 5)//print 5 numbers to start out 
		{
			System.out.print((sequenceRule*countIn+startNumIn) + ", ");
                //print the current number
			return outputSequence(++countIn, startNumIn);
                //run the method again to print the next number 
		}
		else
		{
			int generateNumAnswer = sequenceRule*countIn+startNumIn;
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
            System.out.println("Correct! Well done. ");
		}
		else if (Math.abs(numAnswerIn - userNumIn) < sequenceRule)
			//if the user answer is near the generated answer (max: 
			//increment distance away); basically, if the user is close 
		{
			System.out.println("Close! ");
			userCorrect = false;
		}
		else 
		{
			System.out.println("Not quite! ");
			userCorrect = false;
		}
		return userCorrect;
	}
	//leave some space for organization, separating next number problem from sequence problem 
	
	
	
	
	
	
	
	
	public void sequenceProblem()
        //problem: how to get sequenceRule into this method 
	{
		double userRule = getRule();
		boolean userResponseFormat = true;
            //good format = true; bad format = false
		if (userRule == 0.5)//the code number; user 
		{
            userResponseFormat = false;
                //bad format; userRule != sequenceRule 
            System.out.println("Your input, '" + userResponse + "' was not accepted. ");
		}
		if (sequenceRule == userRule)
		{
		    System.out.println("Correct! Congratulations! The pattern was " + userResponse +" " + sequenceRule + ". ");
		}
		else //if (sequenceRule != userRule)
		{
		    System.out.print("Sorry, your answer was incorrect. The pattern was ");
		    if (userResponseFormat) //good format
		    {
		        System.out.println(userResponse + sequenceRule);
		    }
		    else //user response was incorrect, default to "increment by"
		    {
		        System.out.println("increment by " + sequenceRule);
		    }
		}
	}
	public double getRule()
	{
		String userResponse1 = "";
            //this will be the first word the user inputs
		String userResponse2 = "";
            //this will be the second word the user inputs
		userResponse = "";
            //as stated before, this will be the combination of 1st and 2nd part of the user response 
		double userSNR;
            // to return the "user's sequence (pattern) number response", shortened to userSNR. 
		Scanner keyboard = new Scanner(System.in);
            //new instance of Scanner 
		System.out.print("How do you go from one number to the next?\t");
            //prompt user
		userResponse1 = keyboard.next();
		//plus, add, increase by, increment by, + 
		//doesn't have to check for the single word keywords because it checks for everything later 
		if (userResponse1.equalsIgnoreCase("increase")|| 
			userResponse1.equalsIgnoreCase("increment"))
			//checks for the 2-word keywords
		{
			userResponse2 = " " + keyboard.next();
			    //if there are, takes the second word 
		}
		userResponse = userResponse1 + userResponse2;
            //adds first word to second word; this will not throw an error if there isn't a second word because the second word is initialized to an empty string 
		if (userResponse.equalsIgnoreCase("increase by") || userResponse.equalsIgnoreCase("increment by") || userResponse.equalsIgnoreCase("add") || userResponse.equalsIgnoreCase("plus"))
            //the only 4 word responses that work 
		{
            userSNR = keyboard.nextDouble();
                //accept the user answer
            return userSNR;
                //returns only the double value 
		}
		else
		{
		    //I would use this structure: 
            //System.out.println("Invalid response. Please try again, using \"add ___\", \"plus ___\", \"increase by ___\", or \"increment by ___\". ");
            //getRule();
            return 0.5; //code number for incorrect response
		}
	}
	/*
	//would have used this if I could have figured out how to convert a string with multiple ints to its value 
	public int singleStringToInt(String stringIn)
	{
        char toChar = stringIn.charAt(0);
        int toInt = (int)(toChar) - 48;
        return toInt;
	}
	public String intToString(int intIn);
	{
        String toString = "" + intIn;
        return toString;
	}
	*/
}
