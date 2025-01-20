//GradeStats.java
/* Noah Guan
 * 01-15-2025
 * Per. 6 Java w/ Mr. Yu
 * GradeStats.java
 * Program #38
 * Pseudocode: 


main 
    d&i new instance of gradestats 
    instance.calculateIt 


calculateIt //essentially the run method (diff.name)
    print 3 lines 
	do 
		print welcome (header and intro) 
		<call printInfo (<call getInfo(<call getScores>)>)>
	while (<call> util method(userPlaying, "", ""))
	print 3 lines
String[] getInfo (String[] scoresIn
    

String[] calcScoresUnder75Perc (int[] scoresIn) //returns a list of all scores under 75 percent 
    //shorten and combine these 2 arrays later in a return String array
    int[] under75 = int[scoresIn.length];
    int[] indexNumFoundAt = int[scoresIn.length];
    //initialize the under75 int values to 0
    int i = 0; //reusable index var 
    int u75i = 0; //under75Index
    while(i < scoresIn.length)
    {
        
        if scoresIn[i] < 75 
        {
            //initialize under75 to the score + 1 then increment (go to the next one)
            under75[u75i] = scoresIn[i] + 1;
            indexNumFoundAt[u75i] = i;
            u75i++;
        }
        i++;
    }
    
    //print user data 
    for (int i = 0; i < scoresIn.length;)
        print(Here is the data you entered: Student #i's score: scoresIn[i]-1)
    
    //find actual length of under75 array by removing all units in array that are still 0 
    int realLength = 0;
    for(i = 0; i < under75.length; i++)
    {
        (under75[i])--;
        //System.out.println(under75[i]);
        if (under75[i] >= 0)
        {
            realLength++; 
        }
    }
    String[] rSA = String[realLength];
        //returnStringArray; d&i rSA (returnStringArray) to actual length to remove extra spaces 
        //initialize rSA to that score and the index number found at separated by a space
    for (u75i = 0; u75i < scoresIn.length; u75i++)
    {
        if (u75[i]-1 < 0)
        rSA[u75i] = "" + under75[u75i] + " " + i;
    }
    return rSA; //return the number and the index found at 


max(int[] scores, int iNI) //iNI=indexNumIn, assumes second number is the next indexNum 
    if(iNI == scores.length-1)
        return Math.max(firstScore, secondScore); //the max num of index and num after index 


int[] min(int[] remScoresIn, iNI, nextSmall) // better name = getLeastValue(), return the least value and the index found at input = remaining scores needed sorting
    int i = 0; here our friend is again 
    int least; //first num returned 
    int foundAt; 
    
    while(i < remScoresIn.length) //needs to run through whole array to get least value 
        if(i == scores.length-1)
            least = nextSmall;
        else 
            least = Math.min(nextSmall, remScoresIn[fINI]); //the min of them 
            foundAt = iNI; 
        //compare nextSmall to the next num; if the next num is smaller, store the next number in remSmall and store the index in foundAt. 
        //initialize number being swapped out to swapNum, initialize next smallest num to the next place 
        i++;
    return (int[] {least, foundAt})
        //note: boolean-like system, where (int)true = 1and (int)false = 0.
double avg (int[] finalScores)
    int totalNum = 0;
    for (int i = 0; i < finalScores.length; i++)
    {
        totalNum += finalScores[i];
    }
    return ((double)(totalNum/scores.length))
orderNums
    int i = 0; //index again 
    int nextSmall; //the next (remaining) smallest number left 
    int foundAt; //smallest num found at index __ 
    int swapNum; //holder for remaining num 
    int[] holdMin; //transfer variable
    //if scores[i] = 0
    while (scores is unsorted) //outer 
        holdMin = min(1)
        scores[i] = min(i)
        
    
median(int[] finalScores)
    if (finalScores.length%2=0)
        return (finalScores[scores.length/2-1]+scores[scores.length/2])/2; //take the middle 2 nums and divide by 2 
    else //odd 
        return (finalScores[scores.length/2]); //take the middle num


Sample run.		User input in bold.
< prompt info – make it clear! >
Type in	the	score.		Type	“Quit”	to	end	the	program	-->		95
Type in	the	score.		Type	“Quit”	to	end	the	program	-->		65
Type in	the	score.		Type	“Quit”	to	end	the	program	-->		70
Type in	the	score.		Type	“Quit”	to	end	the	program	-->		78
Type in	the	score.		Type	“Quit”	to	end	the	program	-->		85
Type in	the	score.		Type	“Quit”	to	end	the	program	-->		77
Type in	the	score.		Type	“Quit”	to	end	the	program	-->		QuiT
Here is the data you entered:
Student 1’s score:       95
Student 2’s score:       65
Student 3’s score:       70
Student 4’s score:       78
Student 5’s score:       85
Student 6’s score:       77
There were 2 students who scored below 75%: student 2, student 3.
Number of scores: 6
Minimum: 65
Maximum: 95
Average: 78.3
Median: 77.5

*/
import java.util.Scanner;
public class GradeStats
{
    public GradeStats()
    {
        //nothing
    }
    public static void main(String args[])
    {
        GradeStats gs = new GradeStats();
        gs.calculateIt("GradeStats", "essentially takes in your grades, formats and outputs them with extra data. " + 
                      "It takes in your grades and enters them into an array." + 
                      "\n\tThen, it will output the number of students who scored below 75%, " + 
                       "the number of scores, the minimum score, the maximum score, the average score, and the median score.", "", "", "");
    }
    public String calculateIt(String function, String programName, String programDescription, String promptIn, String get)
    {
        //run has programName and programDescription parameters, userPlaying has no parameters, getInput has prompt and "get" variables 
	    //all of them have function parameter 
        if(function.equalsIgnoreCase("run")) ///run section of it 
		{
			System.out.println("\n\n\n"); 
				//never forget your 3 lines :) 
			//declare variables outside of do-while loop 
			//ex. int var1 = 0; int var2 = 0; 
			
			do
	        {
				System.out.println("Welcome to " + programName + 
					". \nThis program " + programDescription);
					//header and intro section 
				//call the real meat of the program method here 
				//ex. var2 = method1(var1);
				
				System.out.println();
	        } while(Boolean.valueOf(utilMethod("userPlaying", "", "", "", "")));

			//close off with thank you section 
	        System.out.println("Thank you for using " + programName + ". ");
	        System.out.println("\n\n\n");
				//again, we need those 3 lines :D
		}
		else if(function.equalsIgnoreCase("userPlaying")) ///userPlaying section of it 
        {
            boolean userPlay;
            String userDecision = utilMethod("getInput", "", "", "Would you like to run again? [Yes/No]", "next");
            if (userDecision.equalsIgnoreCase("Yes"))
                userPlay = true;
            else if (userDecision.equalsIgnoreCase("No"))
                userPlay = false;
            else //user did not input yes or no 
            {
                System.out.println("Please enter either \"Yes\" or \"No\".\n");
                userPlay = Boolean.valueOf(utilMethod("userPlaying", "", "", "", ""));
                    //call userPlaying again 
            }
            return String.valueOf(userPlay);
                //warning: will need to Boolean.valueOf(____);
        }
        else if(function.equalsIgnoreCase("getInput")) ///getInput section of it 
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
                System.out.println("Internal error: util method unrecognized get parameter: " + 
	                "\n\tfunction: " + function + 
	                "\n\tprogramName: " + programName + 
	                "\n\tprogramDescription: " + programDescription + 
	                "\n\tpromptIn: " + promptIn + 
	                "\n\tget: " + get + 
	                "\n(return: null)");
                return null;
                    //return is literally just for program to run (not technically needed)
            }
        }
        //returns a String, no matter the parameters. beware. use parse____ if needed. 
        else ///catch section of it 
        {
            System.out.println("Internal error: util method unrecognized function parameter: " + 
            	"\n\tfunction: " + function + 
                "\n\tprogramName: " + programName + 
                "\n\tprogramDescription: " + programDescription + 
                "\n\tpromptIn: " + promptIn + 
                "\n\tget: " + get + 
                "\n(return: null)");
            return null; 
                //same purpose of return statement 
        }
        return null; 
            //same purpose of return statement :D
    }
    //method ends here 
}
