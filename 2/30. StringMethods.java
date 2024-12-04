//StringMethods.java
/* Noah Guan
 * 11-15-2024
 * Per. 6 Java w/ Mr. Yu
 * StringMethods.java
 * Program #30 
 * 
 * 
 */
public class StringMethods
{
	
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
	{
	public char charAt(String str, int index) //user gives in the string they want
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
	public boolean endsWith(String fullString, String endCheck)
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
		//if the end of string equals the string user wants to check for 
		if (endOfString.equals(endCheck))
			return true;
		else
			return false;
	}
	
}
