//GrowthPattern.java
/* Noah Guan
 * 01-13-2025
 * Per. 6 Java w/ Mr. Yu
 * GrowthPattern.java
 * Program #37
 * Pseudocode: 

d&i fv height (in constructor) 
main 
	d&i new instance of class 
	use instance to call run method 
void growIt (program name, program description)
	print 3 lines 
	do 
		print welcome (header and intro) 
		call real meat of program method 
		for (int time = 0; time < height.length; time++)
    	{
    		height[time] = height[time] + 5 
    		printSymbols(time)
    		printRate()
    	}
	    all heights 
	while (<call> userPlaying)
	print 3 lines

boolean userPlaying 
	declare userPlay, 
	declare userdecision to <call> input of would you like to run again, next 
	if userdecision = yes, userPlay = true. 
	else if userdecision = no, userPlay = false. 
	else user didn't input yes or no, prompt them to answer correctly, <call> userPlaying again 
	return userPlay 
String getInput (promptIn, get)
	new instance of Scanner called keyboard 
	print promptIn and format for answer
	if get = "line", get .nextLine()
	else if get = "word" or "next", return .next()
	else if get = "int", return .nextInt()
	else if get = "double", return .nextDouble()
	else, print (internal) error, return null 
String decideSymbols(time)
	//type of symbols = if one to the next is decreasing, same, or increasing
	d&i returnString to ""
	find out the type (using if)
	returnString = 
	for(int i = 0, i < howManySymbols, i++)
	{
	    returnString = returnString + type 
	}
	return returnString
printSymbols(time)
	System.out.printf(%5d + "t " + decideSymbols(time) + "\n", time);
printRate()
    print out ("The average rate of change is " + (lastItem - initialItem / (height.length - 1) ))
 */
import java.util.Scanner;
public class GrowthPattern 
{
    private int[] height;
    public GrowthPattern()
    {
        height = new int[]{-1,	-3,	1,	2,	3,	4,	3,	4,	4,	6,	8,	6,	6,	7,	10};
    }
    public static void main(String args[]) 
    {
        GrowthPattern gp = new GrowthPattern();
        gp.growIt("GrowthPattern", "is a program to monitor the growth of a plant over time. ");
    }
    public void growIt(String programName, String programDescription)
    {
        System.out.println("\n\n\n");
        //declare variables outside of while loop 
        
		do
        {
            System.out.println("\nWelcome to " + programName + 
                ". \nThis program " + programDescription);
                //header and intro section 
            
            //call the real meat of the program method 
            for (int time = 1; time < height.length; time++)
        	{
        		height[time] = height[time] + 5;
        		    //adding 5 to the 
        		printSymbols(time);
        	}
        	printRate();
        	//reversing fv height to original 
        	for(int reverse = 1; reverse < height.length; reverse++)
        	{
        	    height[reverse] = height[reverse] - 5;
        	}
        	
        	System.out.println();
        } while(userPlaying());
        System.out.println("Thank you for using " + programName + ". ");
        System.out.println("\n\n\n");
    }
	public void printSymbols(int timeIn)
	{
		System.out.printf("%-5s " + decideSymbols(timeIn) + "\n", ("t" + (timeIn-1))); 
		    //prints time index - 1 (to symbolize initial state), the symbols, and the new line. 
	}
	public String decideSymbols(int timeIn)
	{
    	String returnString = "";
    	char type;
    	if (timeIn == 1)
    	    type = 'o';
    	else
    	{
        	if(height[timeIn] > height[(timeIn - 1)]) //increase as time increases
        	    type = '+';
        	else if(height[timeIn] < height[(timeIn - 1)]) //decrease as time increases
        	    type = '-';
        	else //no change 
        	    type = 'o';
    	}
    	for(int i = 0; i < height[timeIn]; i++)
    	{
    	    returnString = returnString + type;
    	}
    	return returnString;
	}
	public void printRate()
	{
		System.out.printf("The average rate of change is about %.2f units/measurement of time. \n", ((double)(height[(height.length-1)] - height[0]) / (height.length - 1)));
		    //last item - first item divided by length minus one (because of initial height)
	}
    public boolean userPlaying()
    {
        boolean userPlay;
        String userDecision = getInput("Would you like to run again? [Yes/No]", "next");
        if (userDecision.equalsIgnoreCase("Yes"))
            userPlay = true;
        else if (userDecision.equalsIgnoreCase("No"))
            userPlay = false;
        else //user did not input yes or no 
        {
            System.out.println("Please enter either \"Yes\" or \"No\".\n");
            userPlay = userPlaying();
                //call userPlaying again 
        }
        return userPlay;
    }
    public String getInput(String promptIn, String get)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print(promptIn + "\n\t-->\t");
        if (get.equalsIgnoreCase("line"))
            return keyboard.nextLine();
        else if (get.equalsIgnoreCase("word")||get.equalsIgnoreCase("next"))
            return keyboard.next();
        else if (get.equalsIgnoreCase("int"))
            return ("" + keyboard.nextInt());
                //will have to Integer.parseInt(getInput("prompt", "int")) to return integer 
        else if (get.equalsIgnoreCase("double"))
            return ("" + keyboard.nextDouble());
                //will also have to Double.parseDouble(getInput("prompt", "double")) to return double
        else 
        {
            System.out.println("Internal error: getInput method; unrecognized get parameter");
            return null;
        }
    }
}
