//GradeStats.java
/* Noah Guan
 * 01-15-2025
 * Per. 6 Java w/ Mr. Yu
 * GradeStats.java
 * Program #38
 * Pseudocode/Instructions: 
Program GradeStats.java
- Print out the data, showing the student number and corresponding score.
- Print which students had a score below 75%.
- Then find the following statistics and print them:
  - Number of scores,
  - Minimum,
- Maximum,
- Average score,
- Median score, and 
- For either the minimum or the maximum, use a Math method. For the other one, do not use a 
Math method.
- How to compute the median score.
- The median score is the number where half of the scores are above it and half are 
below it. First, you need to put the scores in order from smallest to greatest. 
If the number of scores is odd, then it is the middle number. If the number 
of scores is even, it is the average of the two middle numbers.
- Do this last. Get everything else working first. This is a bit more involved.
Follow the formatting given in the sample run. If there are 10 scores (or any two digit 
number), the scores (or data) should begin at the same place for each line. Why did I include 
that? Two digit numbers need one more space than one digit numbers, which can shift the rest 
of the line!  When necessary, round to the nearest tenth.
Remember to break down your code with methods that are short and do what the method 
name states.  In addition to the standard methods such as main, a method to call the others, 
getting the user input, and printing the required information, you will have separate methods
to calculate the
- scores below 75%, 
- max,
- min, 
- average, 
- order the numbers (which is needed to find the median) and 
- median.
Do the methods in this order, i.e. write the max/min methods before you put the numbers in 
order to find the median. All printing will be done in the printing method(s), not the methods 
that do the calculations.
- Work an example out on paper before starting (using any reasonable data that is not the 
example provided). This will be turned along with your pseudocode.
*/
import java.util.Scanner;
public class Main
{
    public static void main(String[] args) 
    {
        /*
        OldGradeStats o = new OldGradeStats();
        o.run();
        */
        GradeStats gs = new GradeStats();
        gs.calculateIt("run", "GradeStats", "essentially takes in your grades, " + 
            "formats and outputs them with extra data. " + 
            "It takes in your grades and enters them into an array." + 
			"\n\tThen, it will output the number of students who scored below 75%, " + 
			"the number of scores, the minimum score, the maximum score, the " + 
			"average score, and the median score.");
    }
}
class GradeStats
{
    /*
    public void run()
    {
        int[] scores = getScores();
        calcResults(scores);
    }
    */
    public void calcResults(int[] scoresIn)                                     //
    {
        //print everywhere
        //prints out all scores 
        /*
        for(int i: scoresIn)
            System.out.print(i + "\t");
        */
        int[] sorted = mergeSort("mergeSort", scoresIn, null, null);
        int minNum = getMin(sorted);
        int maxNum = getMax(sorted);
        double average = getAverage(sorted);
        double median = getMedian(sorted);
        int[] under75 = getUnder75(sorted);
        printResults(scoresIn, sorted, minNum, maxNum, average, median, under75);
    }
    public void printResults(int[] scoresIn, int[] sortedIn, int min, int max, double averageIn, double medianIn, int[] u75In)
    {
        System.out.println("Here is the data you entered: ");
        for(int i = 0; i < scoresIn.length; i++)
        {
            System.out.println("Student " + (i+1) + "'s score:\t" + scoresIn[i]);
        }
        System.out.println();
        
        System.out.print("There were " + u75In.length + " students who scored below 75%:");
        for(int i = 0; i < u75In.length; i++)
        {
            System.out.print(" student " + (u75In[i]+1));
            if(i != u75In.length-1)                                             //not the last value
                System.out.print(",");
            else
                System.out.print(".");
        }
        System.out.println("\n");
        
        int[] finalOutputInts = new int[] {scoresIn.length, min, max};
        String[] finalOutputLabels = new String[] {"Number of scores:", "Minimum",
            "Maximum:", "Average:", "Median:"};
        int firstFew = 0;
        for(firstFew = 0; firstFew < finalOutputInts.length; firstFew++)
            System.out.printf("%-20s" + finalOutputInts[firstFew] + "\n", finalOutputLabels[firstFew]);
        
        double[] finalOutputDoubles = new double[] {averageIn, medianIn};
        for(int finish = 0; finish < finalOutputDoubles.length; finish++)
            System.out.printf("%-20s%,-30.1f\n", finalOutputLabels[firstFew + finish], finalOutputDoubles[finish]);
        
    }
    public int getMax(int[] sortedArray)
    {
        return sortedArray[sortedArray.length-1];
    }
    public int getMin(int[] sortedArray)
    {
        return sortedArray[0];
    }
    public double getMedian(int[] sortedArray)
    {
        if(sortedArray.length%2==0)                                             //even number of scores
            return ((double)(sortedArray[(sortedArray.length-1)/2]) + sortedArray[sortedArray.length/2])/2;
                //avg. of middle 2 nums
        else                                                                    //odd number of scores
            return sortedArray[sortedArray.length/2];
    }
    public double getAverage(int[] sortedArray)
    {
        int total = 0;
        for(int i = 0; i < sortedArray.length; i++)
        {
            total += sortedArray[i];
        }
        return ((double)(total)/sortedArray.length);
        
    }
    public int[] getUnder75(int[] scoresIn)
    {
        int u75length = 0;
        for(int i = 0; i < scoresIn.length; i++)//7
        {
            if(scoresIn[i] <= 75)
                u75length++;
        }
        
        int outputIndex = 0;
        int[] output = new int[u75length];
        for(int i = 0; i < scoresIn.length; i++)
        {
            if(scoresIn[i] <= 75)
            {
                output[outputIndex++] = i;                                      //gets the value at its own index
            }
        }
        return output;
    }
    
    // method to sort an array using Merge Sort
    private int[] mergeSort(String function, int[] array, int[] left, int[] right) 
    {
        if (function.equalsIgnoreCase("mergeSort"))
        {
            if (array.length <= 1) 
            {
                return array; // Base case: Already sorted
            }
            
            int mid = array.length / 2;
            
            // manually create left and right subarrays
            int[] leftfv = new int[mid];
            int[] rightfv = new int[array.length - mid];
            
            for (int i = 0; i < mid; i++) 
            {
                leftfv[i] = array[i]; // Copy left half
            }
            for (int i = mid; i < array.length; i++) 
            {
                rightfv[i - mid] = array[i]; // copy right half
            }
            
            // recursively sort both halves
            leftfv = mergeSort("mergeSort", leftfv, null, null);
            rightfv = mergeSort("mergeSort", rightfv, null, null);
            
            // merge sorted halves and return result
            return mergeSort("merge", null, leftfv, rightfv);
        }
        else if(function.equalsIgnoreCase("merge"))                             //only using 2 sorted arrays
        {
            int[] result = new int[left.length + right.length];
            int i = 0, j = 0, k = 0;
            
            // merge array elements while both halves have elements
            while (i < left.length && j < right.length) 
            {
                if (left[i] <= right[j]) 
                {
                    result[k++] = left[i++];
                } else 
                {
                    result[k++] = right[j++];
                }
            }
            
            // copy remaining elements from left (if any)
            while (i < left.length) 
            {
                result[k++] = left[i++];
            }
            
            // copy remaining elements from right (if any)
            while (j < right.length) 
            {
                result[k++] = right[j++];
            }
            
            return result;
        }
        else
        {
            System.out.println("mergeSort Error: unrecognized function parameter" + 
				"\n\tfunction: " + function + 
				"\n\tarray: " + array.toString() + 
				"\n\tleft: " + left.toString() + 
				"\n\tright: " + right.toString() + 
				"\n\t(Your array will be stored as null)");
			return null;
        }
    }
    
    public int[] getScores()
    {
        /*
        int[] scores = new int[Integer.parseInt(calculateIt("getInput", "int", 
            "How many scores would you like to calculate for?"))];
            //d&i scores to user input
        */
        String storeUserInput = "";                                             //all user input 
        String nextUserInput = "";                                              //adds onto all user input one at a time 
            //for getting user input in a manageable fashion 
        boolean userStop = false; 
            //stop gathering input when this is true 
        int numOfItems = 0;
        do
        {
            nextUserInput = calculateIt("getInput", "double", "Please enter " + 
                "your next score (enter \"Quit\" to stop): \t");               //get next score 
            storeUserInput = storeUserInput + "|" + nextUserInput;              //concat next score (first one will start it off)
            userStop = nextUserInput.equalsIgnoreCase("quit");                  //stop when user enters quit 
            numOfItems++;
        }while(!userStop);
        numOfItems--;
        if(numOfItems == 0)
            return new int[0];
        storeUserInput = storeUserInput.substring(1, storeUserInput.lastIndexOf("|"));
            //cuts off first "|" and last "|quit"
        //System.out.println("Your scores: " + storeUserInput);
        
        //d&i scoreLength 
        int scoreLength = 0;
        String total = new String(storeUserInput);
        for(int i = 0; i < total.length(); i++)                                 //iterate through string
        {
            if(total.charAt(i) == '|')                                          //if there is still a char in total 
            {
                //increment output (signifying one item) and shorten total to trimmed length
                //System.out.println();
                scoreLength++;
                if(!(total.trim().length() == 1 && total.charAt(0) == '|'))     //charIn isn't the last char in total
                    total.substring(total.indexOf('|') + 1);
                else
                    total = "";
            }
        }
        
        //d&i score, the return int[]
        int[] score = new int[++scoreLength];                                   //adds the last item
        for (int i = 0; i < score.length; i++)
        {
            score[i] = Integer.parseInt(storeUserInput.substring(0, storeUserInput.indexOf('.')));
                //declare score to user input
            storeUserInput = storeUserInput.substring(storeUserInput.indexOf('|') + 1);
                //remove the number stored 
        }
        return score;
            //at this point you're returning all of the user scores (unsorted)
    }
    /*
    public int numOfCharsInString(String total, char charIn)
    {
        int output = 0;
        for(int i = 0; i < total.length(); i++)                                 //iterate through string
        {
            if(total.charAt(i) == charIn)                                       //if there is still a char in total 
            {
                //increment output (signifying one item) and shorten total to trimmed length
                //System.out.println();
                output++;
                if(!(total.trim().length() == 1 && total.charAt(0) == charIn))  //charIn isn't the last char in total
                    total.substring(total.indexOf(charIn) + 1);
                else
                    total = "";
            }
        }
        System.out.println("array length: " + (output + 1));
        return output;
    }
    
    public boolean hasChar(String total, char charIn)                           //returns true if there is at least 1 of the char in string 
    {
        //code for whole string: 
        boolean output = false; 
        for(int i = 0; i < total.length(); i++)                                 //i is the char index 
        {
            output = (output || total.charAt(i) == charIn);                     //output = previously true or this char matches 
            //System.out.print(i + "found = " + output);
            if(output)                                                          //cuts it short if output = true 
                return output;                                                  //technically don't need this
        }
        System.out.print("never gets here; found = false");
        return output;                                                          //return false if match not found by end of string; return true if found by end of string 
    }
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
				int[] receiveScores = getScores();
				if(receiveScores.length == 0)
				    System.out.println("No input. Program ending.");
				else
				    calcResults(receiveScores);
				
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
			String userDecision = calculateIt("getInput", "next", "Would you like to run again? [Yes/No]");
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
			System.out.print(info2 + "\n\t-->\t");
			
			Scanner keyboard = new Scanner(System.in);
			String toReturn = keyboard.nextLine();
			//keyboard.nextLine();
			
			if (toReturn.equalsIgnoreCase("quit"))
			{
				//System.out.println("Scores inputted. ");
				return toReturn;
			}
			else if (info1.equalsIgnoreCase("line"))
			{
				return toReturn;
			}
			else if (info1.equalsIgnoreCase("word")||info1.equalsIgnoreCase("next"))
			{
				if(info1.indexOf(' ') == -1)
					return toReturn;
				else
				{
					toReturn = toReturn.substring(0, toReturn.indexOf(' '));
					return toReturn;
				}
			}
			else if (info1.equalsIgnoreCase("int") || info1.equalsIgnoreCase("double"))
			{
				//System.out.print(info1 + " " + toReturn);
				Scanner infoScanner = new Scanner(toReturn);
				if (info1.equalsIgnoreCase("double")&&infoScanner.hasNextDouble())
                {
                    double output = infoScanner.nextDouble();
                    return "" + output;
                        //will have to Double.parseDouble(getInput("prompt", "double")) to return double 
                }
				else if (info1.equalsIgnoreCase("int")&&infoScanner.hasNextInt())
				{
					int output = infoScanner.nextInt();
					return "" + output;
						//will have to Integer.parseInt(getInput("prompt", "int")) to return integer 
				}
				else
				{
					System.out.println("getInput Error: Received other data type when " + 
						"expected int/double" + 
						"\n\tfunction: " + function + 
						"\n\tinfo1: " + info1 + 
						"\n\tinfo2: " + info2 + 
						"\n\t(Your number will be stored as \'0.0\')");
					return ("" + 0.0);
						//will still have to parse int/double
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

