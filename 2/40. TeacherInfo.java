import java.io.File;
import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import java.io.IOException; 
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        TeacherInfo ti = new TeacherInfo();
        System.out.println("Ready.\n");
        ti.fakeMain();
    }
}
//TeacherInfo.java
/* Noah Guan
 * 01-31-2025
 * Per. 6 Java w/ Mr. Yu
 * TeacherInfo.java
 * Program #40
 * Pseudocode: uploaded (on whiteboard! :D)

output pseudocode: 

grades = {0, 3, 5, 1, 1}

letter:  {a, b, c, d, f}
index:   {1, 2, 3, 4, 5}

output: b b b c c c c c d f
for i < 0
print(a)
for i < 3 
print(b)
for i < 5 
print (c)
for i < 1 
print(d)
for i < 1
print(f)

for i = 0, i < 5 
    for a = 0, a < grades[i]
        if(a == 1)
            print a 
        else if (a == 2)
            print b 
        else if (a == 3)
            print c 
        etc 


 */
/*
import java.io.File;
import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import java.io.IOException; 
import java.util.Scanner;
*/
class TeacherInfo
{
	private int[] grades, scores;
	private String[] teacherData, fileNames;                            //for reading and writing to files 
	private Scanner fileInput;
	private PrintWriter fileOutput;
	private String inFileName, outFileName;								//for reading the file and for writing to file 
	public TeacherInfo()
	{
		grades = new int[5];
		scores = new int[101];
		teacherData = new String[3];
		//fileInput = new Scanner();									//simply reminders for myself to 
		//fileOutput = new PrintWriter(fileName);						//remember to instantiate it later
		inFileName = "";
		outFileName = "";												//instantiate to inFileName-results.txt
        fileNames = new String[] {inFileName, outFileName};
	}
	public static void main(String args[])
	{
		TeacherInfo ti = new TeacherInfo();
		ti.fakeMain();
	}
	public void fakeMain()
	{
		System.out.println("\n\n\nWelcome the TeacherInfo! This program will collect all of " + 
            "the information about the number of A's, B's ...etc to F's that you have in the " + 
            "course that you select.\n");                               //prompt user 
        
		do
		{
			inFileName = utilMethod("getInput", "next", "Please enter the name of the " + 
				"teacher’s file including the extension");				//initialize inFile name
			//move initialization of file in here 
			if (!inFileName.endsWith(".txt")) //and if we can actually read the file then we move on
			{
				System.out.println("Please enter a valid file name.");
			}
		}
		while(!inFileName.endsWith(".txt"));								//keep going until we can move on
		outFileName = inFileName.substring(0, inFileName.indexOf(".txt")) + 
			"-results.txt";												//initialize outFile name to inFile name substringed 
		fileNames = new String[109];                                    //to save later 
		File inFile = new File(inFileName);								//d&i input and output files 
		File outFile = new File(outFileName);
		
		try 															//try: fileInput = new Scanner(inFile);
		{
			fileInput = new Scanner(inFile);							//initializing fileInput 
		}
		catch(FileNotFoundException e)									//catch: FileNotFoundException; System.err.println(); System.exit(1);
		{
			System.err.println("");
			System.exit(1);
		}
		//try: fileOutput = new PrintWriter(outFile);
		//catch: IOException; System.err.println(); System.exit(2)
		//outFileName = inFileName.substring(0, inFileName.indexOf('.txt') + "-results.txt");
		int[] receive = readFile(utilMethod("getInput", "int", "Please enter the course " + 
            "number for data you would like"));
		boolean continueProgram = (receive[0] == 1);
		int numOfCourses = receive[1];
		
		if(continueProgram)
		{
            outputData(numOfCourses);                                           //call outputData
		}
		System.out.println("\n\n\n");
	}
	public int[] readFile(String userCourseNum)
	/*
	logic: 
	create instances of fvs to store info 
	store info by line (while loop in while loop)
        first one to get next line while there is a next line; second one to shift from one indicator to the next
        search for indicators (words with ':')
            first and second indicator; anything between them is info 
            if no second indicator, the second indicator is the end of file 
	
	if the course number matches 
        copy over ivs (add onto fvs)
        return true
    else 
        return false
	*/
	{
		//get name of teacher, course, and course #, given the course number  
		boolean continueProgram = true;                                     //the return value
		int numOfCourses = 0;
		String[] ivTeacherData = new String[3];
		String allCourseNums = "", nextLine = "";
		int[] ivGrades = new int[5], ivScores = new int[101];
		reading:
		while (fileInput.hasNext())                                 //all vars within this resets 
		{
            do
            {
                nextLine = fileInput.nextLine().trim();				//get actual info
			}while(nextLine.equalsIgnoreCase(""));
			//System.out.println("\n\nnextLine: \"" + nextLine + "\"");
			int pll = 0;                                            //processed line length 
			do
			{
                String indicator = getLabel(0, nextLine).trim();					//get label in line: return indicator, space, and indicator index in nextLine 
                //System.out.println("indicator before reformat: \"" + indicator + "\"");///testing print line
                
                //convert getLabel return into indicator and indicator index 
                String secondHalf = indicator.substring(indicator.indexOf(' ') + 1);
                int indicatorIndex = nextLine.length() - 1;                     //not needed; just needs to not throw an error when we use it 
                if(!secondHalf.equals("X(not_needed)"))
                    indicatorIndex = Integer.parseInt(secondHalf);
                                                                            //index of indicator; use to get data (start at this point) 
                indicator = indicator.substring(0, indicator.indexOf(' ')); //just the actual word with the colon now 
                int indicatorEnds = indicatorIndex + indicator.length();
                //System.out.println("indicator after reformat: \"" + indicator + "\"");
                                                                            ///testing print line
                
                //get next indicator and data (in between indicator and next indicator)
                String nextIndicator = getLabel((indicatorEnds), nextLine).trim();  //next indicator, for determining data (anything between 2 indicators)
                //System.out.println("nextIndicator before reformat: \"" + indicator + "\"");
                secondHalf = nextIndicator.substring(nextIndicator.indexOf(' ') + 1); //reuse same var (hopefully doesn't cause any errors)
                int nextIndicatorIndex = nextLine.length() - 1;                 //not needed; just needs not to throw an error 
                if(!secondHalf.equals("X(not_needed)"))
                    nextIndicatorIndex = Integer.parseInt(secondHalf);
                                                                            //next indicator index in nextLine
                //System.out.println("nextIndicator lastIndexOf(' '): " + nextIndicator.lastIndexOf(' '));
                nextIndicator = nextIndicator.substring(0, nextIndicator.indexOf(' '));
                //System.out.println("nextIndicator: \"" + nextIndicator + "\"");

                //get data from nextLine; guiding points = end of indicator and next indicator index 
                String data = "";
                if (nextIndicator.equals("no_colon_left_in_line"))
                    data = nextLine.substring(indicatorEnds, nextIndicatorIndex).trim();
                                                                            //no more data to gather because no more indicators 
                else
                    data = nextLine.substring(nextLine.indexOf(indicator)).trim(); 
                                                                            //safeguard: only initializes between if there are 2 (in this case, there is no next indicator)
                //System.out.println("data: \"" + data + "\"");
                pll += nextLine.indexOf(data);
                
                //start filtering data
                if (indicator.equalsIgnoreCase("Teacher:"))
                    ivTeacherData[1] = data;	//get the teacher name 
                else if (indicator.equalsIgnoreCase("Class:"))
                {
                    ivTeacherData[2] = data.substring(0, data.indexOf('-'));      //get everything before the dash (course number)
                }
                else if (indicator.equalsIgnoreCase("Course:"))
                {
                    ivTeacherData[0] = data;
                }
                else if (indicator.equalsIgnoreCase("Scores:"))
                {
                    String[] scoresGradesLine = getScores(nextLine);                         //gather all scores! returned nextLine bec. that entails the next header 
                    nextLine = scoresGradesLine[scoresGradesLine.length-1];
                    //reset field vars while 
                    for(int i = 0; i < scoresGradesLine.length; i++)
                    {
                        ivScores[i] = scores[i];
                    }
                    for(int i = 0; i < grades.length; i++)
                    {
                        ivGrades[i] = grades[i];
                    }
                    for(int i = 0; i < ivScores.length; i++)
                    {
                        if(i < ivScores.length)
                            ivScores[i] = Integer.parseInt(scoresGradesLine[i]);
                        else if(i < ivScores.length + ivGrades.length)
                            ivGrades[i] = Integer.parseInt(scoresGradesLine[i]);
                        else                                                //only one more option 
                            nextLine = scoresGradesLine[i];                 //this is the reason why we have to make the whole thing a String array 
                    }
                    //for (String i: scoresGradesLine)
                        //System.out.println(i);
                }
                allCourseNums = allCourseNums + ivTeacherData[2] + " ";
                ///note: this logic ensures that at one point, we had all the information given to us.
                    //if we ever needed it again, it would be easy to input into another save fv. 
                if(ivTeacherData[0] == userCourseNum)
                {
                    for(int i = 0; i < teacherData.length; i++)
                        teacherData[i] = ivTeacherData[i];
                    for(int i: grades)
                        grades[i] = grades[i] + ivGrades[i];
                    for(int i: scores)
                        scores[i] = scores[i] + ivScores[i];
                }
			}while(pll >= nextLine.length());
		}
		
		//if none of the course numbers equal the user course number
		if(!checkCourseNum(allCourseNums, Integer.parseInt(userCourseNum)))
		{
			System.out.println("Since " + teacherData[1] + " does not teach " + 
                "course number " + userCourseNum + ", there is no data to report.");
			return (new int[2]);                       //0 for false, 0 courses matching 
		}
		return (new int[] {1, numOfCourses}); //1 for true 
	}
	public String getLabel(int startAt, String total)
	{
		//d&i indices of space 
		int previous = startAt; 
		int next = indexOfWhiteSpace(total, previous + 1);
		String nextWord = "";
		
		//return label/indicator/item with colon, space, starting index
		for (startAt = startAt; startAt < total.length(); startAt++)        //iterate from starting index to end of total string 
		{
            //print out startAt, previous, next, total 
            //System.out.println("\n\tIn getLabel method: \nstartAt: " + startAt + "\nprevious: " + previous + "\nnext: " + next + "\ntotal: \"" + total + "\"");
			if (next == -1)
                next = total.length();                                      //when we run out of space indices 
			nextWord = total.substring(previous, next);
			//System.out.println("nextWord: \"" + nextWord + "\"");
			
			//shift whole sequence up 1 index of whitespace 
			previous = next; 
			next = indexOfWhiteSpace(total, (next + 1));
			
			//System.out.println("Before exiting getLabel method: \nprevious: " + previous + "\nnext: " + next);
			
			//start at labelNum then go through total and return anything 
			if(contains(nextWord, ':'))
			{
				//System.out.println("\tExiting getLabel method (return " + nextWord + " " + total.indexOf(nextWord) + ")\n");
				return nextWord + " " + total.indexOf(nextWord);                //returns nextWord, space, and the index of next word in total. 
			}
		}
		//System.out.println("\tExiting getLabel method (return no_colon_left_in_line) \n");
		return "no_colon_left_in_line X(not_needed)";
	}
	public int indexOfWhiteSpace(String totalIn, int startAt)
	{
        //System.out.println("\tIn indexOfWhiteSpace method:");
        //finds next index of white space (' ', '\n', '\t') in total String starting at startAt 
        //if no white space, returns -1 
        for(int i = 0; i < totalIn.length(); i++)                               //i for index, c for char
        {
            char c = totalIn.charAt(i);
            if((c == ' ')|| (c == '\n') || (c == '\t'))
            {
                //System.out.println("\tExiting indexOfWhiteSpace method (return = whitespace index = " + i + ")");
                return i;
            }
        }
        //System.out.println("\tExiting indexOfWhiteSpace method (return -1)");
        return -1;
	}
	public boolean checkCourseNum(String allCourseNums, int userCourseNum)
	{
        boolean output = false;                                         //output var
        int before = 0;
        int after = allCourseNums.indexOf(' ');                         //again, for getting course nums separated by spaces in between
        String compareNum = "";
        for(int i = 0; i < allCourseNums.length(); i++)
        {
            compareNum = allCourseNums.substring(before, after);
            output = (output || (Integer.parseInt(compareNum) == userCourseNum));   //output: if one of them are true, output will be true
            
            before = after;
            after = allCourseNums.indexOf(' ', after);
            if(after == -1)                                             //hit end of string (no more white spaces)
            {
                after = allCourseNums.length();
            }
        }
        return output;
	}
	
	public String[] getScores(String nextLine)							//returns the current line it's on with the next headers 
	{
        //use nextDouble() to get scores, format it, and input into file 
        String[] outputIV = new String[scores.length + grades.length + 1];
        int[] scoresStorage = new int[scores.length];
        int[] gradesStorage = new int[grades.length];
        while(!(contains(nextLine, ':')))								//in between "scores:" and next header
        {
            Scanner lineScanner = new Scanner(nextLine);
            String nextDoubleScore = "" + lineScanner.nextDouble();
            int nextScore = Integer.parseInt(nextDoubleScore.substring(0, nextDoubleScore.indexOf('.')));//gets next double from string and converts it to an int
            scoresStorage[nextScore]++;
            gradesStorage = convertToGrades(nextScore);
            nextLine = fileInput.nextLine();
        }
        for(int i = 0; i < scoresStorage.length; i++)
        {
            if(i < scoresStorage.length)
                outputIV[i] = "" + scoresStorage[i];
            else if(i < scoresStorage.length + gradesStorage.length)
                outputIV[i] = "" + gradesStorage[i];
            else                                                            //only one more option 
                outputIV[i] = nextLine;                                     //this is the reason why we have to make the whole thing a String array 
        }
        return outputIV;
	}
	
	//stores in grades[] array 
	public int[] convertToGrades(int nextScoreIn)
	{
        int[] gradesStorage = new int[grades.length];
        if (nextScoreIn >= 90)                                              //A
            gradesStorage[0]++;
        else if(nextScoreIn >= 80)                                          //B
            gradesStorage[1]++;
        else if(nextScoreIn >= 70)                                          //C
            gradesStorage[2]++;
        else if(nextScoreIn >= 60)                                          //D
            gradesStorage[3]++;
        else                                                                //F
            gradesStorage[4]++;
        return gradesStorage;
    }
	
	public boolean contains(String sequenceIn, char checkFor)
	{
		for(int i = 0; i < sequenceIn.length(); i++)
		{
			if (sequenceIn.charAt(i) == checkFor)
				return true;
		}
		//if we went through everything and we didn't get a match 
		return false;
	}
	public String getNextItem()											//assuming next item is before the word with a colon at the end 
	{
		String output = "";
		String word = fileInput.next();
		if (word.charAt(word.length()-1) != ':')
		{
			output = output + getNextItem();
		}
		return output;
	}
	public void outputData(int numOfCourses)
	{
        //convert save back into fvs 
        
		//get class info 
		System.out.println("grades: ");
		for(int i = 0; i < grades.length; i++)
		{
			for (int a = 0; a < grades[i]; a++)                             //prints how many there are 
			{
				if(a == 0)
                    System.out.print("\tA");
                else if(a == 1)
                    System.out.print("\tB");
                else if(a == 2)
                    System.out.print("\tC");
                else if(a == 3)
                    System.out.print("\tD");
                else
                    System.out.print("\tF");
			}
			System.out.println();
		}
		System.out.println("scores: ");
		for(int i = 0; i < scores.length; i++)
		{
			if (scores[i] != 0)                                             //prevents from printing zeros
			{
				for (int a = 0; a < scores[i]; a++)
				{
					System.out.println("\t " + scores);
				}
				System.out.println();                                       //prints a new line after every score number
			}
		}
		System.out.println("teacherData: ");
		for (String i: teacherData)
		{
			System.out.println("\t" + i);
		}
		System.out.println("inFileName: " + inFileName);
		System.out.println("outFileName: " + outFileName);
		//for loop: 
		//1. print class info to both 
			//sift through class info; print out to both 
		//2. print scores to terminal 
			//print scores 
		//3. print score "graph" to file 
			//print scores
		//4. print frequency table to both 
	}
	
	public String utilMethod(String function, String info1, String info2)
	{
        if(function.equalsIgnoreCase("getInput")) ///getInput section of it; info1 = get, info2 = promptIn 
        {
            System.out.print(info2 + "\n\t-->\t");
            Scanner keyboard = new Scanner(System.in);
            String nextLine = keyboard.nextLine();
            Scanner numberScanner = new Scanner(nextLine);;
            
            if (info1.equalsIgnoreCase("line"))
                return nextLine;
            else if (info1.equalsIgnoreCase("word") || info1.equalsIgnoreCase("next"))
            {
                int endSubstring = nextLine.indexOf(' ');
                if(endSubstring == -1)
                    endSubstring = nextLine.length();
                return nextLine.substring(0, endSubstring);            //returns up to space
            }
            else if (info1.equalsIgnoreCase("int"))
            {
				if (true/*Boolean.valueOf(utilMethod("onlyHas", "int", nextLine))*/) // this returned false! uh oh
				{
                    int output = numberScanner.nextInt();
					return "" + output;
						//will have to Integer.parseInt(getInput("prompt", "int")) to return integer 
				}
                else 
                {
					System.out.println("Error: Received other data type when " + 
						"expected int" + 
						"\n\tfunction: " + function + 
                        "\n\tinfo1: " + info1 + 
                        "\n\tinfo2: " + info2 + 
                        "\n\tnextLine: " + nextLine + 
						"\n\t(Your number will be stored as \'0\')");
					return ("" + 0);
						//will still have to parse int
				}
            }
            else if (info1.equalsIgnoreCase("double"))
            {
				if (Boolean.parseBoolean(utilMethod("onlyHas", info1, "double")))
				{
                    double output = numberScanner.nextDouble();
					return "" + output;
						//will have to Double.parseDouble(getInput("prompt", "int")) to return double 
				}
                else 
                {
					System.out.println("Error: Received other data type when " + 
						"expected double" + 
						"\n\tfunction: " + function + 
                        "\n\tinfo1: " + info1 + 
                        "\n\tinfo2: " + info2 + 
                        "\n\tnextLine" + nextLine + 
						"\n\t(Your number will be stored as \'0.0\')");
					return ("" + 0);
						//will still have to parse double
				}
            }
            else 
            {
                System.out.println("Internal error: util method unrecognized info1 (get) parameter: " + 
                    "\n\tfunction: " + function + 
                    "\n\tinfo1: " + info1 + 
                    "\n\tinfo2: " + info2 + 
                    "\n(return: null)");
                return null;
                    //return is literally just for program to run (not technically needed)
            }
		}
		else if (function.equalsIgnoreCase("onlyHas")) // parameters: info1 = intOrDouble, info2 = getIn  
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
				return String.valueOf(false);
			}
		}
        //returns a String, no matter the parameters. beware. use parse____ if needed. 
        else ///catch section of it 
        {
            System.out.println("Internal error: util method unrecognized function parameter: " + 
                "\n\tfunction: " + function + 
                "\n\tinfo1: " + info1 + 
                "\n\tinfo2: " + info2 + 
                "\n(return: null)");
            return null; 
                //same purpose of return statement 
        }
	}
}
