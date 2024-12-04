//StringMethods.java
/*
 * 
 * 
 */
public class StringMethods
{
	
	public static void main(String[] args)
	{
		StringMethods sm = new StringMethods();
		String str = new String("Algebra");
		System.out.println(sm.charAt(str, 5));
		System.out.println(sm.endsWith(str, "ra"));
	}
	public char charAt(String str, int x) //user gives in the string they want
		//and the index they want it at 
	{
		char returnChar = '/';
			//test case if it fails 
		String a = " ";
		int b = 0;
		String y = str.substring(x, x+1);
		b = y.compareTo(a);
		returnChar = (char)(b + 32);
		return returnChar;
	}
	public boolean endsWith(String fullString, String endCheck)
		//user gives in the string they want to compare to and the string 
		//they want to end with 
	{
		String endOfString = new String("");
		endOfString = fullString.substring(fullString.length() - endCheck.length());
		if (endOfString.equals(endCheck))
			return true;
		else
			return false;
	}
	
}
