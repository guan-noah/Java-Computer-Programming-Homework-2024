//StringMethodsTester.java
/* Noah Guan
 * 12-06-2024
 * Per. 6 Java w/ Mr. Yu
 * StringMethodsTester.java
 * Program #30.1
// DISCLAIMER: This is the teacher's code that I pulled from the website and 
	//edited/specialized to fit my StringMethods. (and yes, we were supposed to do that.)
// Date: 12/06/2024
// StringMethodsTester.java
// We are writing some of the String Methods.  This class runs StringMethods.java
// Here are examples of using at least three methods - charAt, ...., trim
*/
public class StringMethodsTester
{	
	public StringMethodsTester()
	{
	}
	
	public static void main(String[] args)
	{
		StringMethodsTester smt = new StringMethodsTester();
		smt.runIt();
	}
	
	public void runIt()
	{
		System.out.println("\n\n\n");
		
		String str = "Java is awesome";
		String strCap = "JaVa Is aWeSOME";
		System.out.println("\"Java is awesome\"");
		
		String strWithTab = "\t  Hello World!  \t";
//		System.out.println("The string is \\t Hello World!   \\t");

		System.out.println("\nExample using the charAt() in my StringMethods.java class.");
		for (int i = 0; i < strWithTab.length(); i++)
			System.out.print("\nStringMethods.charAt(strWithTab, " + i + ") = " + 
				StringMethods.charAt(strWithTab, i) + " " );
		// System.out.println(StringMethods.charAt(strWithTab, 50)  ); 
			// after it works, try this.  Record the response.
		
		System.out.println("\nExample using the endsWith() " 
			+ "in my StringMethods.java class.");
 		System.out.println("\nStringMethods.endsWith(strWithTab, \"ome\") = " + 
 			StringMethods.endsWith(strWithTab, "ome"));
			
		System.out.println("\n\nExample using trim(strWithTab), in my "
			+ "StringMethods.java class.");
		System.out.println("The string is \"\t Hello World!   \t\"");
		// Un-comment the following lines out after you write the trim method.
 		System.out.println("\nStringMethods.trim(strWithTab) = |" + 
 			StringMethods.trim(strWithTab) + "|\n\n" );
					
		System.out.println("\n\n\n");
	}
}	
