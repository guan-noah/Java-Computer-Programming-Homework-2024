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
			File inFile = new File(inFileName);							//d&i input file 
			try 														//try: fileInput = new Scanner(inFile);
            {
                fileInput = new Scanner(inFile);						//initializing fileInput 
            }
            catch(FileNotFoundException)								//catch: FileNotFoundException; System.err.println(); System.exit(1);
            {
                System.err.println("Cannot find your file.");
                System.exit(1);
            }
			if (!(inFileName.endsWith(".txt"))) //and if we made it this far (can actually read the file) then we move on
			{
				System.out.println("Please enter a valid file name.");
			}
		}
		while(inFileName.endsWith(".txt");								//keep going until we can move on
		outFileName = inFileName.substring(0, indexOf(".txt")) + 
			"-results.txt";												//initialize outFile name to inFile name substringed 
		
		File outFile = new File(outFileName);                           //d&i output file
		
		
		//try: fileOutput = new PrintWriter(outFile);
		//catch: IOException; System.err.println(); System.exit(2)
		try
		{
		    fileOutput = new PrintWriter(outFile);
		}
		catch(IOException)
		{
		    System.err.println("");
		    System.exit(2);
		}
		
		//outFileName = inFileName.substring(0, inFileName.indexOf('.txt') + "-results.txt";
		//call readFile
		//call outputData
	}
/*
String indicator = fileInput.next().trim();					//get label in header 
String nextWord = fileInput.next().trim();					//get actual info
if (indicator.equalsIgnoreCase("Teacher:"))
	teacherData[1] = nextWord;								//get the teacher name 
else if (indicator.equalsIgnoreCase("Class:"))
{
	while(nextWord.charAt(nextWord.length()-1) != ':')
	{
		String className = fileInput.next();
		teacherData[2] = nextWord.substring(0, indexOf('-'));//get everything before the dash
	}
}
else if (indicator.equalsIgnoreCase("Scores:")
{
    
    //continue scores analysis (enter scores)
    if(fileInput.hasNextDouble())
        nextScore = fileInput.nextDouble();
    else
        inBlock = false;
}

inBlock = ();
*/

/* 
1. break up file in groups of header+body 
    a. header = all data between "teacher: " and "scores: ", only need data in teacher: and class:
2. in each header (getHeaderInfo): 
    a. getTeacherName() 
    b. getClassName()
    c. getClassNumber()
3. in each body, pull out data (getBodyInfo): 
    a. get scores count (getScores)
    b. keep going until next header starts 
*/
    //huge method group 1
	public void readFile()
	{
		//get name of teacher, course, and course # 
		//break up file in groups of header + body 
			//get all blocks of info using header as separation 
		//course header = look for defining text: a colon or smth
		//special marks in header 
		//use next() to avoid whitespace to get words 
		String firstWord = "";                                                   //for easier computing 
		boolean inBlock = true;                                                 //need to edit so it actually does something
		inBlock = (/*is in between "teacher:" and "scores:"*/);
		    //when you see "teacher:", you start it. when you see "scores:", you stop it. 
		while (inBlock && fileInput.hasNext())		                            //while inBlock and !endOfFile
		{
		    firstWord = fileInput.next();
		    inBlock = (firstWord.equalsIgnoreCase("Teacher:") /*&& !lastWord.equalsIgnoreCase("scores:")*/);
		    if(/*this is the class we want*/)
		    {
    			getHeaderInfo();
    			getScores();
		    }
		}
		//once done with one block, keep checking until end of file 
	}
	//method group 2 
	public void getHeaderInfo()
	{
	    
	    //search through file for course number 
		//if no course, print no course 
		//else: 
	}
	//method group 3
	//we assume at this point that the reader is right after "scores:"
	public void getScores()
	{
	    //use nextDouble() to get scores, format it, and input into file 
	    while(/*in between "scores:" and next header*/)
	    {
	        int nextScore = Integer.parseInt(("" + 
    	        fileInput.nextDouble()).substring(0, indexOf('.')));
    	    scores[nextScore]++;
            convertToGrades(nextScore);
	    }
	}
	//method group 3a
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
	
	
	//misc. util method 
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
	
	
	///huge method group 2
	public void outputData()
	{
		//get class info 
		
		//for loop: 
		//1. print class info to both 
			//sift through class info; print out to both 
		//2. print scores to terminal 
			//print scores 
		//3. print score "graph" to file 
			//print scores
		//4. print frequency table to both 
	}
