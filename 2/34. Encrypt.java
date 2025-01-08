//Encrypt.java
/* Noah Guan
 * 01-06-2025
 * Per. 6 Java w/ Mr. Yu
 * Encrypt.java
 * Program #34
 * 
 * Testing Plan: 
https://mvhs-fuhsd.org/john_conlin/Java/HW_folder_Java/Strings/Encrypt.pdf
 */
import java.util.Scanner;
public class Encrypt
{
	private String line;
	public Encrypt()
	{
		line = "";
	}
	public static void main(String[] args)
	{
		Encrypt e = new Encrypt();
		e.run();
	}
	public void run()
	{
		boolean userDone = false; 
			//user will still want to play 
		System.out.print("\n\n\n");
			//required 3 lines 
		String uDR = "";
			//d&i userDoneResponse   
		
		while(!userDone)
		{
			rotate13Times();
			
			//check if the user is done 
			Scanner keyboard = new Scanner(System.in);
			System.out.print("Would you like to encrypt another sentence? [Y/N] -->\t");
			uDR = keyboard.next();
			if (uDR.equalsIgnoreCase("Y"))
				//nothing
			else if (uDR.equalsIgnoreCase("N"))
				userDone = true;
			else 
				System.out.println("Please enter either 'y' (yes)or 'n' (no). ");
		}
		
		System.out.print("\n\n\n");
			//req 3 lines 
	}
	public String getString(String promptIn)
	{
		System.out.print(promptIn);
		Scanner keyboard = new Scanner(System.in);
			//d&i new Scanner object keyboard 
		String input = keyboard.nextLine();
			//gather user next line in input 
		keyboard.nextLine();
			//clear scanner 
		return input;
			//return input var
	}
	public String encryptString(String userInput, shiftBy)
	{
		int iOI = 0;
			//stands for indexOfInput, d&i to 0
		
		int encryptedNum = 0;
			//d&i to 0 as a default 
		char encryptedChar = ' ';
			//d&i to space as a default
		
		int difference = 0;
			//d&i difference between the last char and the encrypted num
		
		for (iOI = 0; iOI < userInput.lengthiOI++)
		{
			char iOIchar = (int)(userInput.charAt(iOI));
			
			//if the letter at the index is an uppercase letter 
			if (iOIchar >= 65 && iOIchar <= 90)
			{
				encryptedNum = (int)(iOIchar) + shiftBy;
				encryptedChar = (char)(encryptedNum);
					//encrypted char typecasts the iOIchar to int, adds the 
						//Caesar cipher code to it, then typecasts that back to a char
				
				//covering the cases where encrypted char goes out of bounds 
					//(bounds include more than 65 & less than 90 or 
					//more than 97 and less than 122) 
				if (encryptedNum < 65 || 
					(encryptedNum > 90 && encryptedNum < 97)
					//if the new encrypted number is less than 65 or 
						//in between 90 and 97 
					//basically if it's under the limit 
				{
					difference = encryptedNum - 65;
						//will be a negative number 
				}
				else if encryptedNum >
			}
			
			//if the letter at the index is a lowercase letter 
			if (iOIchar >= 97 && iOIchar <= 122)
		}
	}
	public void printString(String encryptedIn, shiftBy)
	{
		
		
		
	}
	public void rotate13Times()
	{
		getString("Welcome to Encrypt.java. " +
		"Please enter a word, phrase, or sentence. \n\n->\t")
	}
}
