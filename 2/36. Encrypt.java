//Encrypt.java
/* Noah Guan
 * 01-09-2025
 * Per. 6 Java w/ Mr. Yu
 * Encrypt.java
 * Program #34
 * Pseudocode: 
declare class 
d&i field var line //used for multi-method communication, just reinitialize
main 
	new Encrypt, call run 
run()
	prompt user if they're done (yes/no)
	get first word of user response (line.substring(0, indexOf(' ')))
	if user isn't done, run again
rotate13Times()
	d&i rotate in a for loop (x13, rotate changes each time) 
	call getString, encryptString, printString once done 
getString(prompt)
	simple get user input code (print the prompt) 
encryptString(line, shift)
	d&i difference 
	cycle through all chars in the line (charAt)
	for each char: 
		if uppercase (borders 65-90)
			typecast currentChar to int, add shift
			if (currentChar < 65) //currentChar below borders 
				difference = 65 - (int)currentChar;
				currentChar = 90 - difference;
			else if (currentChar > 90) //currentChar above borders 
				difference = (int)currentChar - 90;
				currentChar = 65 + difference;
			typecast back to char 
		if lowercase (borders 97-122)
			same thing except border change (add another method) 
*/				///MR.YU SAYS IT'S FINE 
/*
		if neither, don't change anything (no code needed) 
printLines()
	print encryption <rotateNum>
	print line 

 * Testing Plan: 

 */
import java.util.Scanner;
public class Encrypt
{
	private String line;
	public Encrypt()
	{
		line = "";
			//I didn't even have to use this 
	}
	public static void main(String[] args)
	{
		Encrypt e = new Encrypt();
		e.rotate13Times();
	}
	public void rotate13Times()
	{
		boolean userDone = false; 
			//user will still want to play 
		System.out.print("\n\n\n");
			//required 3 lines 
		String uDR = "";
			//d&i userDoneResponse
		boolean userCorrectInput = false; 
			//you want it to run so you d&i to false 
		int rotate; 
		int userChange; 
		
		while(!userDone)
		{
			userCorrectInput = false; 
				//we have to reinitialize if we want the end section to run
			String userIn = getString("Welcome to Encrypt.java. " +
				"Please enter a word, phrase, or sentence. \n\n->\t", "line");
				//prompt, d&i line to user answer  
			userChange = Integer.parseInt(getString("\nHow much do you want to rotate your " + 
				"line/sentence by? \n\n-->\t", "int"));
			String encryptedString; 
			
			rotateloop:
			for (rotate = 1; rotate <= 26; rotate++) //the 2 can be replaced with userChange (line 76 & 80)
			{
				encryptedString = encryptString(userIn, (rotate*userChange));
				printString(encryptString(userIn, (rotate*userChange)), (rotate));
					//print it out, using encrypted string and rotate number
				if (encryptedString.equals(userIn))
					break rotateloop;
			}
			//check if the user is done 
			while (!userCorrectInput)
			{
				uDR = getString("Would you like to encrypt another sentence? [Y/N] -->\t", "next");
				if (uDR.equalsIgnoreCase("Y"))
				{
					userCorrectInput = true;
						//so the loop won't run again 
				}
				else if (uDR.equalsIgnoreCase("N"))
				{
					userDone = true;
						//set userDone to true, loop won't run anymore 
					userCorrectInput = true;
						//so the loop won't run again 
				}
				else 
					System.out.println("Please enter either 'y' (yes) or 'n' (no). ");
					//loop will run again 
				System.out.println("\n");
			}
		}
		System.out.println("Thank you for running Encrypt. ");
			//thanks message 
		
		System.out.print("\n\n\n");
			//req 3 lines 
	}
	public String getString(String promptIn, String whatToGet)
	{
		System.out.print(promptIn);
		Scanner keyboard = new Scanner(System.in);
			//d&i new Scanner object keyboard 
		String input = "";
			//d&i input 
		if (whatToGet.equals("line"))
			input = keyboard.nextLine();
				//gather user next line in input 
		else if (whatToGet.equals("next"))
			input = keyboard.next();
				//gather user next word in input 
		else if (whatToGet.equals("int"))
			input = String.valueOf(keyboard.nextInt());
				//gather user next int in input 
		//keyboard.nextLine();
			//clear scanner 
		return input;
			//return input var, always a String. if we want an int, we're going 
				//to have to use String.valueOf(input)
	}
	public String encryptString(String userInput, int shiftBy)
	{
		String encryptedString = "";
			//the thing to return after encrypting  
		int iOI = 0;
			//stands for indexOfInput, d&i to 0
		
		int encryptedNum = 0;
			//d&i to 0 as a default 
		char encryptedChar = ' ';
			//d&i to space as a default
		
		int difference = 0;
			//d&i difference between the last char and the encrypted num
		
		// goes from iOI to encryptedNum to encryptedChar, add to encryptedString 
		
		for (int index = 0; index < userInput.length(); index++)
		{
			iOI = (int)(userInput.charAt(index));
				//char before checking bounds, not shifted yet typecasted to int (ascii)
			
			
			//if the letter at the index is an uppercase letter 
			if (iOI >= 65 && iOI <= 90)
			{
				encryptedNum = iOI + (shiftBy%26);
					//char before checking bounds, shifted 
				//covering the cases where encrypted char goes out of bounds 
					//(bounds include more than 65 & less than 90)
				//if it is above range, we will add the difference back to the lower range 
				//if it is below range, we will subtract the difference back to the range 
				if (encryptedNum < 65) //encryptedNum below borders 
				{
					difference = encryptedNum - 65;
						//difference will be negative 
					encryptedNum = 91 + difference;
						//essentially subtracting the difference
				}
				else if (encryptedNum > 90) //encryptedNum above borders 
				{
					difference = encryptedNum - 90;
						//difference will be positive 
					encryptedNum = 64 + difference;
						//adding difference to the lower bound 
				} 
			}
			
			//if the letter at the index is a lowercase letter 
			else if (iOI >= 97 && iOI <= 122)
			{
				encryptedNum = iOI + (shiftBy%26);
					//char before checking bounds, shifted 
				//covering the cases where encrypted char goes out of bounds 
					//(bounds include more than 97 and less than 122)
				//same logic 
				if (encryptedNum < 97) 
				{
					difference = encryptedNum - 97;
					encryptedNum = 123 + difference;
				}
				else if (encryptedNum > 122)  
				{
					difference = encryptedNum - 122;
					encryptedNum = 96 + difference;
				}
			}
			else //not within bounds 
			{
				encryptedNum = iOI;
			}
			encryptedChar = (char)(encryptedNum);
					//encrypted char typecasts the iOIchar to int, adds the 
						//Caesar cipher code to it, then typecasts that back to a char
			encryptedString += encryptedChar; 
				//appends encryptedChar onto encryptedString 
		}
		return encryptedString;
	}
	public void printString(String encryptedIn, int shiftBy)
	{
		System.out.println("Encryption " + shiftBy);
		System.out.println(encryptedIn + "\n");		
	}
}
