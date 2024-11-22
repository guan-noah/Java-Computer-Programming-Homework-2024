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
		ns.play();
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
		generateSequence();
		printSequence();
		//nextNumProblem();
		//nextSequenceProblem(); 
		if (!done)
		{
			run();
		}
		userQuit();
		if (userQuit == true)
		    System.out.println("Would you like to try again? ");
	}
	public void prompt()
	{
		System.out.println("Welcome to NumberSequence.java! ");
		System.out.println("\nThis program will print out a number sequence;"
			+ " you will have to find the number that comes next (the number"
			+ " in the underscore. \nYou will get at most 3 tries to guess the next"
			+ " number; after that, you will get one try to guess the rule"
			+ " for the sequence, whether you guessed the first number or not. ");
			//explains the game 
		
	}
	public void generateSequence()
	{
		startNum = (int)(Math.random()*22 - 10);
			//this is 22 because it includes -10 to 0, 0 included, 
		sequenceChange = (int)(Math.random()*10 + 1);
	}
	public void printSequence()
	{
		System.out.println("Your sequence is:");
		outputSequence(0);
	}
	public void outputSequence(int countIn)
	{
		if (count < 5)
		{
			System.out.println((sequenceRule*count+startNum) + "\t");
		}
		else
			System.out.println();
			numAnswer = sequenceRule*count+startNum;
	}
	public void nextNumProblem()//method for nextNum section 
	{
		int numAnswer = 0;
		int userNumAnswer = 0;
		boolean userCorrect;
			//be careful -- if something goes wrong in reinitializing 
			//numAnswer and userNumAnswer, it will both be the same 
			//(interpreted as user was correct). 
		numAnswer = getNextNum();  
		    //get the next number from user 
		userCorrect = decideIfNumCorrect(numAnswer, userNumAnswer);
		    //decide if the user number inputted is correct 
		
	}
	public int getNextNum()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("What's the next number?\t");
		int userNumAnswer = keyboard.nextInt();
		keyboard.nextLine();
		return userNumAnswer;
	}
	public boolean decideIfNumCorrect(int numAnswer, int userNumAnswer)
	{
		boolean userCorrect;
		String wrongAnswer = "Not quite! ";
		if (userNum == numAnswer)
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
		}
		else 
		{
			System.out.println(wrongAnswer);
		}
		return userCorrect;
	}
	public void checkAnswerNear(decideAnswerNearIn, checkNumIn, answerResponseIn)
	{
		if (decideAnswerNearIn == checkNumIn) 
			//if the answer reference equals the assigned number,
		{
			System.out.println(answerResponseIn);
				//print response entered
		}
	}
	//public void printOutput(int numAnswer, int userNumAnswer)
	//{
	//	System.out.println("");
	//}
	public void getRule()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("How do you go from one number to the next?\t");
		String userResponse = keyboard.next();
		//plus, add, increase by, increment by, + 
		if (userResponse.equalsIgnoreCase("plus")||
			userResponse.equalsIgnoreCase("add"))
		{
			
		}
		else if (userResponse.equalsIgnoreCase("increase")|| 
			userResponse.equalsIgnoreCase("increment"))
		{
			String userResponse2
		}
	}
	public void userQuit()
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
		    System.out.println("That response does not fit the prompt. Please try again. ");
		    userQuit();
		else
			//userQuit remains false. 
	}
}
