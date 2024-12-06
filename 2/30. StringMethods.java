//StringMethods.java
/* Noah Guan
 * 11-15-2024
 * Per. 6 Java w/ Mr. Yu
 * StringMethods.java
 * Program #30 
 * Pseudocode: 
 * Testing Plan: 
 * 
 */
public class StringMethods
{
	/*
	public static void main(String[] args)
	{
		StringMethods sm = new StringMethods();
			//new instance of StringMethods to call non-static run method 
		sm.run();
	}
	public void run()
	{
		String str = new String("Algebra");
			//uses a basic string to compare to (class example) 
		System.out.println(charAt(str, 5));
			//uses the charAt method 
		System.out.println(endsWith(str, "ra"));
			//uses the endsWith method 
		System.out.println(trim("  Algebra\t\t\n"));
	}
	*/
	public static char charAt(String str, int index) //user gives in the string they want
		//and the index they want it at 
	{
		char returnChar = '/';
			//initialize to catch case if it fails 
		int difference = 0;
			//initialize to catch case 
		String returnString = str.substring(index, index + 1);
			//takes in the char at the index as a string 
		difference = returnString.compareTo(" ");
			//compare to the first ascii character 
			//stores the difference 
		returnChar = (char)(difference + 32);
			//stores the difference to the space added to 32, typecasted to char
		return returnChar;
			//return the value 
	}
	public static boolean endsWith(String fullString, String endCheck)
		//user gives in the full string they want to compare to and the string 
		//user wants to check for the end
	{
		String endOfString = new String("");
			//declare & initialize end of string to an empty string 
		endOfString = fullString.substring(fullString.length() - endCheck.length());
			//string length from the beginning equals the whole string's 
				//length subtracted by the end of the string's length; 
				//then use substring on the string length from the beginning 
				//to get the end of string 
				//reinitialize endOfString to this value 
		//if the end of string equals the string user wants to check for 
		if (endOfString.equals(endCheck))
			return true;
		else
			return false;
	}
	public static String trim(String fullStr)
	{
		boolean trimmed = false; 
		while(!trimmed)
		{
			if (charAt(fullStr, 0) == ' '|| 
				charAt(fullStr, 0) == '\t' ||
				charAt(fullStr, 0) == '\n')
			{
				fullStr = fullStr.substring(1, fullStr.length());
			}
			else if (charAt(fullStr, fullStr.length()-1) == ' '|| 
				charAt(fullStr, fullStr.length()-1) == '\t' ||
				charAt(fullStr, fullStr.length()-1) == '\n')
			{
				fullStr= fullStr.substring(0, fullStr.length()-1);
			}
			else
			{
				trimmed = true;
			}
		}
		return fullStr;
	}
	/*
	d&i fullstring 
	d&i charnum 
	
	while (!done) 
	//for simplicity, assume that only thing we need to remove is space 
	if fullstring has space in beginning, 
		fullstring = substring(1, fullstring.length()-1)
	else if fullstring has space at end, 
		fullstring = substring(0, fullstring.length()-2)
	else 
		done = true 
	*/
}
