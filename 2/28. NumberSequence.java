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
public class NumberSequence
{
	private int startNum, sequenceRule, numAnswer, userNumAnswer;
		//declare field variables 
	private Scanner keyboard;
		//field scanner! :D
	private boolean done;
	public NumberSequence()//initialize all field variables 
	{
		startNum = 0;
		sequenceRule = 0; //this is the increment/decrement 
		keyboard = new Scanner(System.in);
		numAnswer = 0;
		userNumAnswer = 0;
			//be careful -- if something goes wrong in reinitializing 
			//numAnswer and userNumAnswer, it will both be the same 
			//(interpreted as user was correct). 
		done = false;
	}
	public static void main(String[] args)
	{
		NumberSequence ns = new NumberSequence();
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
		if (!done)
		{
			run()
		}
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
	public void nextNumProblem()
	{
		getNextNum();
		decideIfNumCorrect();
		printOutput();
	}
	public void getNextNum()
	{
		System.out.print("What's the next number?\t");
		userNum = keyboard.nextInt();
	}
	public void decideIfNumCorrect()
	{
		if (userNum == numAnswer)
		{
			correct = true;
		}
		else if (sequenceRule
	}
	public void printOutput()
	{
		System.out.println("");
	}
	public void getRule()
	{
		System.out.print("How do you go from one number to the next?\t");
		userNum = keyboard.nextInt();
	}
}
