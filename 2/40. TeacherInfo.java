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
		//outFileName = inFileName.substring(0, inFileName.indexOf('.txt') + "-results.txt";
		
		if(readFile(utilMethod("getInput", "int", "Please enter the course " + 
            "number for data you would like")))
		{
            outputData();                                               //call outputData
		}
		System.out.println("\n\n\n");
	}
	public boolean readFile(String userCourseNum)
	{
		//get name of teacher, course, and course #, given the course number  
		String allCourseNums = "";
		int numOfCourses = 0;
		reading:
		while (fileInput.hasNext())
		{
            String nextLine = "";
            do
            {
                nextLine = fileInput.nextLine().trim();				//get actual info
                /*
                String test = fileInput.nextLine(); //this worked so it's not the fileInput's problem
                System.out.println(test);
                System.out.println("nextLine before check: |" + nextLine + "|");///testing print line
                if(nextLine.equalsIgnoreCase(""))
                    System.out.println("nextLine = \"\""); <-- this one 
                else if(nextLine.equalsIgnoreCase(null))
                    System.out.println("nextLine = null");
                else if(nextLine.equalsIgnoreCase("\n"))
                    System.out.println("nextLine = \\n");
                else
                    System.out.println("what is nextLine?");
                System.out.println("|" + nextLine + "|" + String.valueOf(nextLine.equalsIgnoreCase("")));
                */
			}while(nextLine.equalsIgnoreCase(""));
			System.out.println("nextLine: |" + nextLine + "|");
			int lineSpace = nextLine.indexOf(' ');
			System.out.println("lineSpace before check: |" + lineSpace + "|");  ///testing print line
			if(lineSpace == -1)                                             //safeguard: if no space, the next word 
			{
                lineSpace = nextLine.length();
			}
			System.out.println("lineSpace after check: |" + lineSpace + "|");   ///testing print line
			String indicator = getLabel(1, nextLine);					//get label in header, return indicator, space, and indicator index 
			System.out.println("indicator before reformat: |" + indicator + "|");///testing print line
			int space = indicator.indexOf(' ');
			System.out.println("space (general) before check: |" + space + "|");///testing print line
			if(space == -1)                                             //safeguard: if no space, the next word 
			{
                space = indicator.length();
			}
			System.out.println("space (general) after check: |" + space + "|");  ///testing print line
			//convert getLabel return into indicator and indicator index 
			System.out.println("nextLine: |" + nextLine + "|");                 ///testing print line 
			System.out.println("indicator: |" + indicator + "|");               ///testing print line
			indicator = indicator.substring(0, space); //just the actual word with the colon now 
			int indicatorIndex = Integer.parseInt(indicator.substring(space + 1)); //index of indicator; use to get data (start at this point) 
			
			//get next indicator and data (in between indicator and next indicator)
			String nextIndicator = getLabel(indicatorIndex, nextLine); //next indicator, for determining data (things between 2 indicators)
			String data = "";
			
			if (nextIndicator.equals("no colon in line"))
				data = nextLine.substring((indicatorIndex + indicator.length()), nextLine.indexOf(nextIndicator)).trim();//data after indicators
			else
				data = nextLine.substring(nextLine.indexOf(indicator)).trim(); //safeguard: only initializes between if there are 2 (in this case, there is no next indicator)
			
			//start filtering data
			if (indicator.equalsIgnoreCase("Teacher:"))
				teacherData[1] = data;	//get the teacher name 
			else if (indicator.equalsIgnoreCase("Class:"))
			{
				teacherData[2] = data.substring(0, data.indexOf('-'));      //get everything before the dash (course number)
			}
			else if (indicator.equalsIgnoreCase("Course:"))
			{
				teacherData[0] = data;
			}
			else if (indicator.equalsIgnoreCase("Scores:"))
			{
				getScores(nextLine);                                        //gather all scores!
			}
			allCourseNums = allCourseNums + teacherData[2] + " ";
			///note: this logic ensures that at one point, we had all the information given to us.
			    //if we ever needed it again, it would be easy to input into another save fv. 
			
			//if not the user course
			if(!(teacherData[2] == userCourseNum))
            {
                storeCourseInfo(++numOfCourses);
                break reading;
                //essentially, break the loop; we got the correct info we needed 
            }
            else
            {
                for(String i: teacherData)
                    i = "";
                for(int i: scores)
                    scores[i] = 0;
                for(int i: grades)
                    grades[i] = 0;
                //keep going (reset fvs first)
            }
            
		}
		//if none of the course numbers equal the user course number
		if(!checkCourseNum(allCourseNums, Integer.parseInt(userCourseNum)))
		{
			System.out.println("Since " + teacherData[1] + " does not teach " + 
                "course number " + userCourseNum + ", there is no data to report.");
			return false;
		}
		
		
		
		//search through file for course number 
		//if no course, print no course 
		//else: 
		//while !endOfFile
		//get all blocks of info using header as separation 
			//course header = look for defining text: a colon or smth
			//using nextDouble and special marks in header 
			//use next() to avoid whitespace to get words 
		//once done with one block, keep checking until end of file 
		return true; //(keep going)
	}
	public void storeCourseInfo(int numOfCoursesIn)
	{
        //store in String[] fv fileNames bec. it's unused now
        
        //store teacherdata[0 to 2]
        for(int i = 0; i < fileNames.length; i++)
        {
            if (i < 101)                                                //store scores[0 to 100]
            {
                fileNames[i] = "" + (Integer.parseInt(fileNames[i]) + scores[i]);
            }
            else if (i < 107)                                           //store grades[0 to 5]
            {
                fileNames[i] = "" + (Integer.parseInt(fileNames[i]) + grades[i]);
            }
            else                                                        //store numOfCoursesIn
            {
                fileNames[i] = "" + numOfCoursesIn;
            }
        }
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
	
	public String getScores(String nextLine)							//returns the current line it's on with the next headers 
	{
        //use nextDouble() to get scores, format it, and input into file 
        while(!(contains(nextLine, ':')))								//in between "scores:" and next header
        {
            Scanner lineScanner = new Scanner(nextLine);
            String nextDoubleScore = "" + lineScanner.nextDouble();
            int nextScore = Integer.parseInt(nextDoubleScore.substring(0, nextDoubleScore.indexOf('.')));//gets next double from string and converts it to an int
            scores[nextScore]++;
            convertToGrades(nextScore);
            nextLine = fileInput.nextLine();
        }
        return nextLine;
	}
	
	//stores in grades[] array 
	public void convertToGrades(int nextScoreIn)
	{
        String grade = "";
        if (nextScoreIn >= 90)                                              //A
            grades[0]++;
        else if(nextScoreIn >= 80)                                          //B
            grades[1]++;
        else if(nextScoreIn >= 70)                                          //C
            grades[2]++;
        else if(nextScoreIn >= 60)                                          //D
            grades[3]++;
        else                                                                //F
            grades[4]++;
    }
	
	public String getLabel(int startAt, String total)
	{
		//d&i indices of space 
		int previous = 0; 
		int next = total.indexOf(' ');
		String nextWord = "";
		//return item with colon: where it's found
		for (startAt = startAt; startAt < total.length(); startAt++)
		{
			nextWord = total.substring(previous, next);
			previous = next; 
			next = total.indexOf(' ', (next + 1));							//shift whole thing up 1 index of space 
			//start at labelNum then go through total and return anything 
			if(contains(nextWord, ':'))
				return nextWord + " " + total.indexOf(nextWord); //returns label (indicator), space, and starting index
		}
		return "no colon in line";
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
	public void outputData()
	{
        //convert save back into fvs 
        int numOfCourses = getCourseInfo();
        
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
	public int getCourseInfo()
	{
        //reverse logic storeCourseInfo();
        for(int i = 0; (i < fileNames.length-1); i++)
        {
            if (i < 101)                                                //get scores[0 to 100]
            {
                scores[i] = Integer.parseInt(fileNames[i]);
            }
            else                                                        //get grades[0 to 5]
            {
                grades[i] = Integer.parseInt(fileNames[i]);
            }
        }
        return Integer.parseInt(fileNames[108]);                        //return numOfCourses
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
            else if (info1.equalsIgnoreCase("word")||info1.equalsIgnoreCase("next"))
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
