//NestingEx.java
/* Noah Guan
 * 10-28-2024
 * Per.6 Java w/ Mr. Yu
 * NestingEx.java
 * Program #XX
 * This program is from class today; it gives an example of if/else nesting.
 * no pseudocode required 
 * Testing Plan: 
 * 	Output:
 * 
 * 
 * 
 * 		yes1
 * 		yes3
 * 		yes4
 * 
 * 
 * 
 */
 public class NestingEx
 {
	 public static void main(String[] args)
	 {
		 NestingEx nex = new NestingEx();
		 nex.runIt();
		}
	public void runIt()
	{
		System.out.println("\n\n\n");
		int num = 1; 
		if(num == 1)
		{
			printIt("yes1");
			if(num == 2)
				printIt("yes2");
			else
			{
				printIt("yes3");
				if(num != 2)
				{
					printIt("yes4");
				}
			}
		}
		System.out.println("\n\n\n");
	}
	public void printIt(String word)
	{
		System.out.println(word);
	}
}
