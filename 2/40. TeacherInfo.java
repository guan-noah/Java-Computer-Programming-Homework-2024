//TeacherInfo.java
/* Noah Guan
 * 01-31-2025
 * Per. 6 Java w/ Mr. Yu
 * TeacherInfo.java
 * Program #40
 * Pseudocode: uploaded (on whiteboard! :D)\
 */

import java.io.File;
import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import java.io.IOException; 
import java.util.Scanner;

public class TeacherInfo
{
	private int[] grades, scores;
	private String[] teacherData;
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
	}
	public static void main(String args[])
	{
		TeacherInfo ti = new TeacherInfo();
		ti.fakeMain();
	}
	public void fakeMain()
	{
		do
		{
			inFileName = getInput("next", "Please enter the name of the " + 
				"teacher’s file including the extension");				//initialize inFile name
			//move initialization of file in here 
			if (inFileName.endsWith(".txt")) //and if we can actually read the file then we move on
			{
				System.out.println("Please enter a valid file name.");
			}
		}
		while(inFileName.endsWith(".txt");								//keep going until we can move on
		outFileName = inFileName.substring(0, indexOf(".txt")) + 
			"-results.txt";												//initialize outFile name to inFile name substringed 
		
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
		//call readFile
		//call outputData
	}
	public void readFile()
	{
		//get name of teacher, course, and course # 
		String allCourseNums = "";
		while (fileInput.hasNext())
		{
			String nextLine = fileInput.nextLine().trim();				//get actual info
			String indicator = getLabel(1, nextLine);					//get label in header, return indicator, space, and indicator index 
			String indicatorIndex = indicator.substring(indicator.indexOf(' ' + 1); //index of indicator; use to get data (start at this point) 
			String nextIndicator = getLabel(indicatorIndex, nextLine); //next indicator, for determining data (things between 2 indicators)
			indicator = indicator.substring(0, nextLine.indexOf(' ')); //just the actual word with the colon now 
			if (nextIndicator.equals("no colon in line"))
				String data = nextLine.substring(indexOf(indicator), nextIndicator)).trim();//data after indicators
			else
				String data = nextLine.substring(indexOf(indicator)).trim(); //safeguard: only initializes between if there are 2 (in this case, there is no next indicator)
			
			if (indicator.equalsIgnoreCase("Teacher:"))
				teacherData[1] = nextLine.substring(indicatorIndex);	//get the teacher name 
			else if (indicator.equalsIgnoreCase("Class:"))
			{
				while(nextWord.charAt(nextWord.length()-1) != ':')		//while the last char is not a colon 
				{
					teacherData[2] = nextLine.substring(nextLine.indexOf(indicator), nextLine.indexOf('-'));
						//get everything before the dash (course number)
				}
			}
			else if (indicator.equalsIgnoreCase("Course:")
			{
				teacherData[0] = nextLine.substring(nextLine.indexOf(indicator));
			}
			else if (indicator.equalsIgnoreCase("Scores:"))
			{
				//gather all scores! 
				getScores(nextLine);
			}
			allCourseNums = allCourseNums + teacherData[2] + " ";
			//if none of the course numbers equal the user course number
			if((teacherData[2] != userCourseNum))
			{
				System.out.println("");
			}
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
	}
	
	public String getScores(String nextLine)							//returns the current line it's on with the next headers 
	{
	    //use nextDouble() to get scores, format it, and input into file 
	    while(!(nextLine.contains(':')))								//in between "scores:" and next header
	    {
	        Scanner lineScanner = new Scanner(nextLine);
	        int nextScore = Integer.parseInt(("" + 
    	        lineScanner.nextDouble()).substring(0, indexOf('.')));
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
            grade[0]++;
        else if(nextScoreIn >= 80)                                          //B
            grade[1]++;
        else if(nextScoreIn >= 70)                                          //C
            grade[2]++;
        else if(nextScoreIn >= 60)                                          //D
            grade[3]++;
        else                                                                //F
            grade[4]++;
    }
	
	public String getLabel(int labelNum, String total)
	{
		//d&i indices of space 
		int previous = 0; 
		int next = total.indexOf(' ');
		//return item with colon: where it's found
		for (labelNum = labelNum; labelNum < total.length; labelNum++)
		{
			nextWord = total.substring(previous, next);
			previous = next; 
			next = total.indexOf(' ', next);							//shift whole thing up 1 index of space 
			//start at labelNum then go through total and return anything 
			if(contains(nextWord, ':'))
				return nextWord + " " + labelNum; 
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
		//get class info 
		System.out.println("grades: ");
		for(grades: i)
		{
			System.out.println("\t " + grades[i]);
		}
		System.out.println("scores: ");
		for(scores: i)
		{
			if (scores[i] != 0)
			{
				for (int a = 0; a < scores[i]; a++
				{
					System.out.println("\t " + scores);
				}
				System.out.println();
			}
		}
		System.out.println("teacherData: ");
		for (teacherData: i)
		{
			System.out.println("\t" + teacherData[i]);
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
	private int[] grades, scores;
	private String[] teacherData;
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
	}
	
}
