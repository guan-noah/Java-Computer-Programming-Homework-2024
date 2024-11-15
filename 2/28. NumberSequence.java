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
		//
	private Scanner keyboard;
	public NumberSequence()
	{
		startNum = 0;
		sequenceRule = 0; //this is the increment/decrement 
		keyboard = new Scanner(System.in);
		numAnswer = 0;
		userNumAnswer = 0;
			//be careful -- if something goes wrong in reinitializing 
			//numAnswer and userNumAnswer, it will both be the same 
			//(interpreted as user was correct). 
	}
	public static void main(String[] args)
	{
		NumberSequence ns = new NumberSequence();
		ns.newLines();
		ns.play();
		ns.newLines();
	}
	public void newLines()
	{
		System.out.println("\n\n\n");
	}
	public void run() //this method runs the program. 
	{
		prompt();
		generateSequence();
		printSequence();
		getNum()
		
	}
	public void prompt()
	{
		System.out.println("Welcome to NumberSequence.java! ");
		System.out.println("\nThis program will print out a number sequence;"
			+ " you will have to find the number that comes next (the number"
			+ " in the underscore. \nYou will get at most 3 tries to guess the next"
			+ " number; after that, you will get one try to guess the rule"
			+ " for the sequence, whether you guessed the first number or not. ");
		
	}
	public void generateSequence()
	{
		startNum = (int)(Math.random()*22 - 10)
			//this is 22 because it includes -10 to 0, 0 included, 
	}
}
