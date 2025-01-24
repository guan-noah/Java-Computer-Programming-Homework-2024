//GradeStats.java
/* Noah Guan
 * 01-15-2025
 * Per. 6 Java w/ Mr. Yu
 * GradeStats.java
 * Program #38

*/
import java.util.Scanner;
class Main
{
    public static void main(String args[])
    {
        GradeStats g = new GradeStats();
        g.calculateIt("run", "GradeStats", "having fun");
    }
}

class GradeStats
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
                       "the number of scores, the minimum score, the maximum score, the average score, and the median score.", "");
    }
public int[] getScores()
{
    String storeUserInput = "";
    String nextUserInput = ""; 
		//for getting user input in a manageable fashion 
    int[] scores = new int[Integer.parseInt(calculateIt("How many scores would you like to calculate for?", "int", ""))];
    boolean userStop = false; 
		//stop gathering input when this is true 
    do
    {
		storeUserInput = storeUserInput + nextUserInput + " | ";
				//store the next score (first one will start it off
		nextUserInput = calculateIt("Please enter " + 
			"your next score (enter \"Quit\" to stop): \t", "int", "");
				//get the next score 
		userStop = nextUserInput.equalsIgnoreCase("quit");
			//stop it when user enters quit 
    }while(!userStop);
    
    //d&i scoresAndI (stands for "scores and index", the return string) 
    int[] scoresAndI = new int[scores.length]; 
    //next and previous index of space; this is to get the number order 
		//skips over the 1st 3 chars (starts at the 4th) because the 1st 3 are buffers 
    int pIOS = 3;
    int nIOS = storeUserInput.indexOf("|", 3);
    for (int i = 0; i < scores.length; i++)
    {
        scoresAndI[i] = Integer.parseInt(storeUserInput.substring(pIOS, nIOS));
			//declare scoresAndI to user input, then space, then index number, 
				//then ';' followed by a new line
		pIOS = nIOS; 
			//the "previous one" now starts at the previous "next one"
		nIOS = storeUserInput.indexOf("|", (nIOS + 1));
			//the "next one" now starts at the next space after the previous "next one"
	}
    return scores;
        //at this point you're returning all of the user scores (unsorted)
}
public int[] calcScoresUnder75Perc(int[] scoresIn, String function, int exp, int max) 
    //also a sorting method; sorting works when they input function correctly (start with )
{
        if (function.equalsIgnoreCase("getMax")) 
        {
            // Find the maximum value in the array
            for (int num = 0; num < scoresIn.length; num++)//for (int num : scoresIn) 
            {
                if (scoresIn[num] > max) 
                {
                    max = num;
                }
            }
            return new int[] {max};
        } 
        else if (function.equalsIgnoreCase("countingSort")) 
        {
            // counting sort based on the current exponent
            int[] output = new int[scoresIn.length];
            int[] count = new int[10];

            // count how many times each digit shows up
            for (int num : scoresIn) 
            {
                int digit = (num / exp) % 10;
                count[digit]++;
            }

            // update count array to store total cumulative sums
            for (int i = 1; i < 10; i++) 
            {
                count[i] += count[i - 1];
            }
            
            // build output array in sorted order
            for (int i = scoresIn.length - 1; i >= 0; i--) 
            {
                int digit = (scoresIn[i] / exp) % 10;
                output[count[digit] - 1] = scoresIn[i];
                count[digit]--;
            }
            
            // copy sorted values back into original array
            for (int i = 0; i < scoresIn.length; i++) 
            {
                scoresIn[i] = output[i];
            }
            return scoresIn;
        } 
        else if (function.equalsIgnoreCase("radixSort")) 
        {
            // radix sort: uses countingSort & getMax operations
            int[] getMax = calcScoresUnder75Perc(scoresIn, "getMax", 0, 0);
            int maxVal = getMax[0]; 
            
            int exponent = 1;
            int[] output = new int[scoresIn.length];
				//make new output var the same length as scoresIn 
            while (maxVal / exponent > 0) 
            {
                output = calcScoresUnder75Perc(scoresIn, "countingSort", exponent, 0);
                exponent *= 10;
            }
            return output;
        }
        else if (function.equalsIgnoreCase("get array under 75 percent"))
        {
            for (int outer = 0; outer < scoresIn.length; outer++)
            {
                if (scoresIn[outer] < 75)
                {
                    int[] under75 = new int[scoresIn.length-outer];
                    int[] indices = new int[under75.length];
                    for (int inner = 0; inner < under75.length; inner++)
                    {
                        under75[inner] = scoresIn[outer+inner];
                        indices[inner] = outer; 
                        
					}
					int[] outputArray = new int[1+2*(under75.length)];
						//waste of space because we're not allowed 2D arrays 
					outputArray[1] = under75.length + 1;
						//first num = last index number where it switches 
					//initialize outputArray
					for (int i = 1; i < outputArray.length; i++)
					{
						if (i <= outputArray[1])
						{
							outputArray[i] = under75[i];
						}
						else //if (i > outputArray[1])
						{
							outputArray[i] = indices[i];
						}
					}
                    return outputArray;
                }
            }
        }
        else
        {
			System.out.println("Error: Unrecognized calcScoresUnder75Perc " + 
						"parameter (returned empty array)" + 
						"\n\tscoresIn: " + scoresIn + 
			            "\n\tinfo1: " + function + 
			            "\n\texp: " + exp + 
			            "\n\tmax: " + max + 
						"\n\t(Your number will be stored as \'0\')");
				//notify that we returned empty array 
			return new int[0];
		}
		
		
		//if it gets here I did something very wrong or the computer broke
		System.out.println("the computer broke. (end of calcScoresUnder75Perc method)");
		return new int[0];
}

public int[] min(String function, int[] remScoresIn, int iNI, int nextSmall, int lSFA) 
	// better name = getLeastValue(), return the least value and the index found at 
	//input: remaining scores needed sorting, index num in, the previous small number, lastSmallFoundAt
{
    int least = 0; //first num returned; will be reinitialized
    int foundAt = remScoresIn.length - 1; //initialize foundAt to last item 
    
    int i = 0; //here our friend is again 
    while(i < remScoresIn.length) //needs to run through whole array to get least value 
    {
        if(i == remScoresIn.length-1)
        {
            least = nextSmall;
            foundAt = lSFA; 
        }
        else 
        {
            least = Math.min(nextSmall, remScoresIn[iNI]); //the min of them 
            foundAt = iNI; 
        }
        //compare nextSmall to the next num; if the next num is smaller, store the next number in remSmall and store the index in foundAt. 
        //initialize number being swapped out to swapNum, initialize next smallest num to the next place 
        i++;
    }
    return (new int[] {least, foundAt});
        //note: boolean-like system, where (int)true = 1and (int)false = 0.
}

public int max(int[] scores, int iNI) //iNI=indexNumIn, assumes second number is the next indexNum 
{
    if(iNI != scores.length-1)
        return Math.max(scores[iNI], scores[iNI + 1]); //the max num of index and num after index 
    else 
        return scores[iNI];
}

public int median(int[] finalScores)
{
    if (finalScores.length % 2 == 0)
        return (finalScores[finalScores.length/2-1]+finalScores[finalScores.length/2])/2; //take the middle 2 nums and divide by 2 
    else //odd 
        return (finalScores[finalScores.length/2]); //take the middle num
}

public void printInfo(String operation, int[] fSI) // finalScoresIn; 
{
    if (operation.equalsIgnoreCase("print"))
    System.out.println("Here is the data you entered: ");
    for (int i = 0; i < fSI.length; i++)
    {
        System.out.printf("Student " + (i+1) + "'s score:%7d\n", fSI[i]);
    }
    String studentsU75 = "";
		//for students under 75; append students 
	for (int i = 0; i < fSI.length; i++)
	{
		if (fSI[i] < 75)
		{
			studentsU75 += ("student " + i + ", ");
		}
		if (i == fSI.length - 1) //the last one 
		{
			studentsU75 = studentsU75.substring(0, fSI.length - 2);
				//taking off the comma and the space at the end 
		}
	}
    System.out.printf("There were " + calcScoresUnder75Perc(fSI, "operation", 0, 0) + 
		"students who scored below 75%: " + studentsU75);
    /*
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
}
/*

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
    public String calculateIt(String function, String info1, String info2)
    {
        //run has programName and programDescription parameters, userPlaying has no parameters, getInput has prompt and "get" variables 
	    //all of them have function parameter 
        if(function.equalsIgnoreCase("run")) ///run section of it, info1 = programName, info2 = programDescription 
		{
			System.out.println("\n\n\n"); 
				//never forget your 3 lines :) 
			//declare variables outside of do-while loop 
			//ex. int var1 = 0; int var2 = 0; 
			
			do
	        {
				System.out.println("Welcome to " + info1 + 
					". \nThis program " + info2);
					//header and intro section
				//call the real meat of the program method here 
				//ex. var2 = method1(var1);
				printInfo("print", getScores());
				
				System.out.println();
	        } while(Boolean.valueOf(calculateIt("userPlaying", "", "")));

			//close off with thank you section 
	        System.out.println("Thank you for using " + info1 + ". ");
	        System.out.println("\n\n\n");
				//again, we need those 3 lines :D
		}
		else if(function.equalsIgnoreCase("userPlaying")) ///userPlaying section of it; doesn't matter what info is (ex. info1 = "", info2 = "") 
        {
            boolean userPlay;
            String userDecision = calculateIt("getInput", "Would you like to run again? [Yes/No]", "next");
            if (userDecision.equalsIgnoreCase("Yes"))
                userPlay = true;
            else if (userDecision.equalsIgnoreCase("No"))
                userPlay = false;
            else //user did not input yes or no 
            {
                System.out.println("Please enter either \"Yes\" or \"No\".\n");
                userPlay = Boolean.valueOf(calculateIt("userPlaying", "", ""));
                    //call userPlaying again 
            }
            return String.valueOf(userPlay);
                //warning: will need to Boolean.valueOf(____);
        }
        else if(function.equalsIgnoreCase("getInput")) ///getInput section of it; info1 = get, info2 = promptIn 
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.print(info2 + "\n\t-->\t");
            if (info1.equalsIgnoreCase("line"))
                return keyboard.nextLine();
            else if (info1.equalsIgnoreCase("word")||info1.equalsIgnoreCase("next"))
                return keyboard.next();
            else if (info1.equalsIgnoreCase("int"))
	        {
				if (Boolean.valueOf(calculateIt("onlyHas", info1, "int")))
					return ("" + keyboard.nextInt());
						//will have to Integer.parseInt(getInput("prompt", "int")) to return integer 
	            else 
	            {
					System.out.println("Error: Received other data type when " + 
						"expected int" + 
						"\n\tfunction: " + function + 
			            "\n\tinfo1: " + info1 + 
			            "\n\tinfo2: " + info2 + 
						"\n\t(Your number will be stored as \'0\')");
					return ("" + 0);
						//will still have to parse int
				}
	        }
            else if (info1.equalsIgnoreCase("double"))
	        {
				if (Boolean.parseBoolean(calculateIt("onlyHas", info1, "double")))
					return ("" + keyboard.nextDouble());
						//will have to Double.parseDouble(getInput("prompt", "int")) to return double 
	            else 
	            {
					System.out.println("Error: Received other data type when " + 
						"expected double" + 
						"\n\tfunction: " + function + 
			            "\n\tinfo1: " + info1 + 
			            "\n\tinfo2: " + info2 + 
						"\n\t(Your number will be stored as \'0.0\')");
					return ("" + 0);
						//will still have to parse double
				}
	        }
            else 
            {
                System.out.println("Internal error: calculateIt method unrecognized info1 (get) parameter: " + 
	                "\n\tfunction: " + function + 
	                "\n\tinfo1: " + info1 + 
	                "\n\tinfo2: " + info2 + 
	                "\n(return: null)");
                return null;
                    //return is literally just for program to run (not technically needed)
            }
		}
		else if (function.equalsIgnoreCase("onlyHas")) // info1 = intOrDouble, info2 = getIn parameters: 
	    {
			if (info1.equalsIgnoreCase("int") || info1.equalsIgnoreCase("double"))
			{
				for (int i = 0; i < info2.length(); i++) //cycle through String info2 (getIn) 
				{
					if ((int)(info2.charAt(i)) >= 49 && (int)(info2.charAt(i)) <= 1)
					{
						//if within ascii range of numbers, continue with the program
					}
					else if (((int)(info2.charAt(i)) == 46) && info1.equalsIgnoreCase("double"))
					{
						//continue with the program but ONLY if it's in double 
					}
					else
						return String.valueOf(false); //no longer purely numbers 
				}
				return String.valueOf(true); //it made it out without returning false 
			}
			else
			{
				System.out.println("Internal error: onlyHas method unrecognized info2 (intOrDouble) parameter: " + 
					"\n\tfunction: " + function + 
		            "\n\tinfo1: " + info1 + 
		            "\n\tinfo2: " + info2 + 
					"\n\t(returned false)");
				return Boolean.toString(false);
			}
		}
        //returns a String, no matter the parameters. beware. use parse____ if needed. 
        else ///catch section of it 
        {
            System.out.println("Internal error: calculateIt method unrecognized function parameter: " + 
	                "\n\tfunction: " + function + 
	                "\n\tinfo1: " + info1 + 
	                "\n\tinfo2: " + info2 + 
	                "\n(return: null)");
            return null; 
                //same purpose of return statement 
        }
        return null; 
            //same purpose of return statement :D
    }
    //method ends here 
}
