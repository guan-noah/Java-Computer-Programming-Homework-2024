//Noah Guan
//8-28-24 Per 6
/*
HelloMrC.java
Our 1st program! This prints a simple message. It shows formatting --
Allman style always!! We will format every program this way. 
Working on: Using print, println and escape sequences for \t tab & \n new line.
*/

public class HelloMrC
{
	public static void main(String[] args)
	{//Allman style means to put the brace below the header
		System.out.println("\n\n\n"); //Always include 3 blank lines
		System.out.println("Hello Mr. C");
		System.out.println("Mr. Yu, is it possible not to retype \"system.out.println\"?");
		
		System.out.print("I am printing on ");//When you type "System.out.print", it doesn't create a new line for you
		System.out.println("the same line, then going ");
		System.out.println("to the next line.");
		
		
		System.out.println("\nHere is another way to combine two lines. " + 
			"Always indent the 2nd and following lines.");
		
		System.out.println("\n\"Escape Sequences\"");
		System.out.println("\tBackslash t gives a tab.");
		System.out.println("\nBackslash n gives a new line.");

		System.out.println("\n\n\n"); //Always include 3 blank lines
	}
}
