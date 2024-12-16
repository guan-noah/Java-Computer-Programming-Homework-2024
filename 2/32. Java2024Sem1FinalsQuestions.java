/* Noah Guan
 * 12-11-2024
 * Per. 6 Java w/ Mr. Yu
 * Java2024Sem1FinalsQuestions.java
 * Program #32
 * 
 * ///COMMENTING GUIDE 
 * 
 * ExampleCode ec = new ExampleCode();
 * 		//if comment is indented off of a line 
 * 			//if a comment is indented off of a comment, it is a continuation
 * 			//of the previous comment (consistent with the rule above). 
 * 			//(unless, of course, the whole line is a comment, as now.)
 * 			//(and no, of course this is example code, not in my program.) 
 * //if I comment before a section of code, like so, 
 * ec.printLines();
 * ec.run();
 * ec.printLines();
 * 
 */
public class Java2024Sem1FinalsQuestions
{
	public Java2024Sem1FinalsQuestions()
	{
		//no field variables 
	}
	public static void main(String[] args)
	{
		Java2024Sem1FinalsQuestions j2slfq = new Java2024Sem1FinalsQuestions();
			//new instance of java 2024 semester 1 finals questions
		j2slfq.run();
			//use this instance to call method run 
	}
	public void run()
	{
		System.out.println("\n\n");	
			//3 new lines 
		System.out.println("");
		String[] topicsList = {"Introduction, Memory, History, Conversions" + 
			"(Decimal, Binary, Hexadecimal)", "Data types", "Math (class " + 
			"and calculations)", "Input/Output", "Control/Boolean logic", 
			"Algorithm [Design (?)]", "If/Else", "Methods/Classes", "Inheritance", 
			"Recursion", "Loops", "Strings"};
			//each topic is in order from beginning of the year to end of
				//the year
			//length 12, indices 0 - 11 
		int[] numOfQuestions = {10, 11, 8, 1, 11, 3, 5, 9, 14, 5, 12, 11};
			//each int is the # of questions corresponding to its index 
				//equivalent in topicsList 
			//length 12, indices 0 - 11 
		printfln("chronological order (beginning of school year to end):\t" + 
			"%-17s:", "number of questions");
			//printing example case 
		//printing each case based on its index in the array, + some funny 
			//printf stuff 
		for (int i = 0; i < 12; i++)
		{
			printfln("#" + (i+1) + ":\t%-2f" + topicsList[i], numToString(i));
		}
	}
	public void printfln(String stringIn, String numOfQuestionsIn)
		//has to accept 2 strings, not a string and an int simply because 
			//of polymorphism and first example explaining format *thumbsup*
	{
		System.out.printf(stringIn + "\n", numToString(i));
	}
	public String numToString(int numIn)
	{
		String returnString = "" + numIn;
		return returnString;
	}
}
